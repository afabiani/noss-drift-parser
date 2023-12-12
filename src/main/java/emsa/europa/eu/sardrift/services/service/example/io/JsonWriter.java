package emsa.europa.eu.sardrift.services.service.example.io;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.databind.ObjectMapper;
import emsa.europa.eu.sardrift.services.service.example.data.spatial.FeatureCollection;
import org.postgresql.util.PSQLException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.*;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.nio.file.Paths;
import java.lang.reflect.Field;
import java.util.logging.Logger;

public class JsonWriter {

    private static final Logger LOGGER = Logger.getLogger(JsonWriter.class.getName());

    // Create an ObjectMapper instance
    static ObjectMapper objectMapper = new ObjectMapper();

    static {
        objectMapper.registerModule(new JavaTimeModule());
    }

    public static void publishSimulationModelToJsonFileJDBC(String dbName,
                                                            String dbUser,
                                                            String dbPassword,
                                                            String dbHost,
                                                            int dbPort,
                                                            String dbTable,
                                                            Object simulationModel) throws JsonProcessingException {
        String jdbcUrl = String.format("jdbc:postgresql://%s:%d/%s", dbHost, dbPort, dbName);
        try (Connection connection = DriverManager.getConnection(jdbcUrl, dbUser, dbPassword)) {
            // Parse GeoJSON and insert features
            for (FeatureCollection.Feature feature : ((FeatureCollection) simulationModel).getFeatures()) {
                // Insert geometry and properties
                insertFeature(connection, dbTable, feature);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void insertFeature(Connection connection, String tableName, FeatureCollection.Feature feature) throws SQLException, IllegalAccessException {
        String insertSql = buildInsertSql(tableName, feature);
        LOGGER.fine("Insert SQL: " + insertSql);
        try (PreparedStatement preparedStatement = connection.prepareStatement(insertSql)) {
            // Set geometry
            preparedStatement.setString(1, feature.getGeometry().toString());

            // Set properties using reflection (skip wkb_geometry)
            setProperties(preparedStatement, 2, feature.getProperties());

            LOGGER.fine("Insert SQL parameters: " + preparedStatement);
            preparedStatement.executeUpdate();
        }
    }

    private static String buildInsertSql(String tableName, FeatureCollection.Feature feature) throws IllegalAccessException {
        StringBuilder insertSql = new StringBuilder("INSERT INTO " + tableName + " (wkb_geometry");

        // Dynamically add property names to SQL
        for (Field field : FeatureCollection.Properties.class.getDeclaredFields()) {
            field.setAccessible(true);
            Object value = field.get(feature.getProperties());

            // Check if the value is not null before adding to the SQL statement
            if (value != null) {
                // Convert camel case to snake case for field names
                String snakeCaseFieldName = camelToSnakeCase(field.getName());
                JsonProperty jsonPropertyAnnotation = field.getAnnotation(JsonProperty.class);
                String fieldName = (jsonPropertyAnnotation != null) ? jsonPropertyAnnotation.value() : snakeCaseFieldName;
                insertSql.append(", ").append(fieldName);
            }
        }

        insertSql.append(") VALUES (ST_GeomFromGeoJSON(?)");

        // Dynamically add placeholders for property values to SQL
        for (Field field : FeatureCollection.Properties.class.getDeclaredFields()) {
            field.setAccessible(true);
            Object value = field.get(feature.getProperties());

            // Check if the value is not null before adding to the SQL statement
            if (value != null) {
                insertSql.append(", ?");
            }
        }

        insertSql.append(")");

        return insertSql.toString();
    }

    private static void setProperties(PreparedStatement preparedStatement, int startIndex, FeatureCollection.Properties properties) throws SQLException {
        Field[] fields = FeatureCollection.Properties.class.getDeclaredFields();

        int fieldCount = 0;
        for (int i = 0; i < fields.length; i++) {
            Field field = fields[i];
            field.setAccessible(true);

            try {
                Object value = field.get(properties);

                // Check if the value is not null before adding to the SQL statement
                if (value != null) {
                    if (value instanceof OffsetDateTime) {
                        // Set the OffsetDateTime in the PreparedStatement
                        preparedStatement.setObject(startIndex + fieldCount, value, Types.TIMESTAMP_WITH_TIMEZONE);
                    } else {
                        // Set the value in the PreparedStatement
                        preparedStatement.setObject(startIndex + fieldCount, value);
                    }
                    fieldCount++;
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (PSQLException e) {
                LOGGER.fine("Error setting property: " + field.getName());
                e.printStackTrace();
            }
        }
    }

    private static String camelToSnakeCase(String camelCase) {
        return camelCase.replaceAll("([a-z])([A-Z])", "$1_$2").toLowerCase();
    }

    public static void publishSimulationModelToJsonFileOgr2Ogr(String dbName,
                                                               String dbUser,
                                                               String dbPassword,
                                                               String dbHost,
                                                               int dbPort,
                                                               String dbTable,
                                                               String jsonFilePath) throws IOException, InterruptedException {
        // Write the JSON string to a DB table
        executeCommand("ogr2ogr", "-f", "PostgreSQL",
                createConnectionString(dbName, dbUser, dbPassword, dbHost, dbPort),
                Paths.get(jsonFilePath).toAbsolutePath().toString(), "-nln", dbTable, "-append");
    }

    public static void executeCommand(String... command) throws IOException, InterruptedException {
        // Create a list to store the command and its arguments
        // Add the command and its arguments to the list
        ArrayList<String> commandList = new ArrayList<>(Arrays.asList(command));
        LOGGER.fine("Command: " + commandList);

        // Use ProcessBuilder to execute the command
        ProcessBuilder processBuilder = new ProcessBuilder(commandList);
        Process process = processBuilder.start();

        // Read the output of the process
        InputStream inputStream = process.getInputStream();
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

        String line;
        while ((line = bufferedReader.readLine()) != null) {
            LOGGER.fine(line);
        }

        // Wait for the process to finish
        int exitCode = process.waitFor();
        LOGGER.fine("Process exited with code " + exitCode);
    }

    public static String createConnectionString(String dbname, String user, String password, String host, int port) {
        return "PG:\"dbname=" + dbname + " user=" + user + " password=" + password + " host=" + host + " port=" + port + "\"";
    }

    public static void writeSimulationModelToJsonFile(Object simulationModel, String jsonFilePath) throws IOException {
        // Write the JSON string to a file
        objectMapper.writeValue(Paths.get(jsonFilePath).toFile(), simulationModel);

        LOGGER.fine("JSON written to: " + jsonFilePath);
    }
}

-- Import the data into the database using GDAL Utilities
-- ogr2ogr -f "PostgreSQL" PG:"dbname=NOSS user=postgres password=postgres host=localhost port=5444" "noosdrift_1473_mothy_cmems-nws1.5_arpege.json" -nln noosdrift_model -append
-- ogr2ogr -f "PostgreSQL" PG:"dbname=NOSS user=postgres password=postgres host=localhost port=5444" "noosdrift_1473_mothy_cmems-nws1.5_arpege_cluster.json" -nln noosdrift_cluster -append

-- Import the data into the database using JDBC
CREATE TABLE IF NOT EXISTS public.noosdrift_cluster
(
	ogc_fid SERIAL,
    time_step character varying COLLATE pg_catalog."default",
    start_time timestamp with time zone,
    end_time timestamp with time zone,
    number_of_times integer,
    model_name character varying COLLATE pg_catalog."default",
    request_id integer,
    simulation character varying COLLATE pg_catalog."default",
    wind_forcing character varying COLLATE pg_catalog."default",
    ocean_forcing character varying COLLATE pg_catalog."default",
    "time" timestamp with time zone,
    latitude_of_center double precision,
    longitude_of_center double precision,
    ellipsis_major_axis double precision,
    ellipsis_minor_axis double precision,
    ellipsis_major_axis_azimuth_angle double precision,
    distance_of_center_from_start double precision,
    azimuth_direction_of_center_from_start double precision,
    wkb_geometry geometry,
    CONSTRAINT noosdrift_cluster_pkey PRIMARY KEY (ogc_fid)
);

CREATE TABLE IF NOT EXISTS public.noosdrift_model
(
    ogc_fid SERIAL,
    time_step character varying COLLATE pg_catalog."default",
    start_time timestamp with time zone,
    end_time timestamp with time zone,
    number_of_times integer,
    model_name character varying COLLATE pg_catalog."default",
    request_id integer,
    simulation character varying COLLATE pg_catalog."default",
    wind_forcing character varying COLLATE pg_catalog."default",
    ocean_forcing character varying COLLATE pg_catalog."default",
    "time" timestamp with time zone,
    latitude_of_center double precision,
    longitude_of_center double precision,
    ellipsis_major_axis double precision,
    ellipsis_minor_axis double precision,
    ellipsis_major_axis_azimuth_angle double precision,
    bbox_lonmin double precision,
    bbox_lonmax double precision,
    bbox_latmin double precision,
    bbox_latmax double precision,
    bbox_centerlon double precision,
    bbox_centerlat double precision,
    super_ellipsis_centerlon double precision,
    super_ellipsis_centerlat double precision,
    super_ellipsis_major_axis double precision,
    super_ellipsis_minor_axis double precision,
    super_ellipsis_major_axis_azimuth_angle double precision,
    wkb_geometry geometry,
    CONSTRAINT noosdrift_model_pkey PRIMARY KEY (ogc_fid)
);
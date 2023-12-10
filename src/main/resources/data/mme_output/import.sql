ogr2ogr -f "PostgreSQL" PG:"dbname=NOSS user=postgres" "noosdrift_1487_mothy_cmems-nws1.5_arpege.json" -nln noosdrift_1487_mothy_cmems-nws1 -append

ogr2ogr -f "PostgreSQL" PG:"dbname=NOOS user=postgres password=postgres" "sample_data_2.json" -nln sample_data_2 -overwrite
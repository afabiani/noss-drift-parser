ogr2ogr -f "PostgreSQL" PG:"dbname=NOSS user=postgres password=postgres host=localhost port=5444" "noosdrift_1473_mothy_cmems-nws1.5_arpege.json" -nln noosdrift_model -append
ogr2ogr -f "PostgreSQL" PG:"dbname=NOSS user=postgres password=postgres host=localhost port=5444" "noosdrift_1473_mothy_cmems-nws1.5_arpege_cluster.json" -nln noosdrift_cluster -append
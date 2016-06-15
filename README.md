# Keepass CSV importer
A small commandline tool to import CSV files into your keypass database

## Dependencies
You have to clone [KeePassJava2](https://github.com/jorabin/KeePassJava2) and build it to your local maven repository.

## config
- `database` (Optional) Set if you want to add to existin database, if not set a new database will be created
- `keyfile` (Optional) Set if you want a keyfile to be used for credentials
- `csvfile` The file to parse
- `order` A comma separated list of the structure of the CSV. Add an empty entry if you want to skip a value. `title,url,username,password,notes(optional),group(optional)`
- `output` Output database. Leave empty if `database` is entered and you want to override it with the new one

## build and run
- `mvn clean install`
- `java -jar keepass-csv-1.0-SNAPSHOT-jar-with-dependencies.jar` run from directory of config.properties
- `--dry-run` add to test without writing to database

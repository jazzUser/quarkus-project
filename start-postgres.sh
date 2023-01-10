#!/bin/bash

# IMPORTANT: If you change a value of these properties, please update the documentation in the README file
# Please choose an appropriate image tag from the official repository https://hub.docker.com/_/postgres/
dbVersion="14.4-alpine"
dbName=trainings
dbUser=trainingsuser
dbPass=trainings_NT

# Run the docker container for PostgreSQL
echo -e "\\nStarting docker container postgresql...\\n"
docker run --rm --name postgresql_academy -e POSTGRES_PASSWORD=${dbPass} -e POSTGRES_USER=${dbUser} -e POSTGRES_DB=${dbName}  -d -p 127.0.0.1:5432:5432 postgres:${dbVersion}
echo -e "\\nTo stop the database, execute: docker stop postgresql_academy\\n"

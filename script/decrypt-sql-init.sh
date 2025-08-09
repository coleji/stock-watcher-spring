mkdir -p ./build/resources/test/ignore; cat ./src/test/resources/sql-init-encrypt | gpg -d --batch --passphrase=$1 | xzcat > ./build/resources/test/ignore/test-data.sql

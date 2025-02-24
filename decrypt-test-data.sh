mkdir -p ./build/resources/test/ignore; cat ./src/test/resources/test-data-encrypt | gpg -d --batch --passphrase=$1 | xzcat > ./build/resources/test/ignore/test-data.sql

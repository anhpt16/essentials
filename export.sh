set -e
mvn -pl . clean install
mvn -pl essential-sdk clean install
mvn -pl essential-admin-plugin clean install -Pexport,\!test
mvn -pl essential-web-plugin clean install -Pexport,\!test
mvn -pl essential-theme clean install -Pexport,\!test
ezy.sh package
ezy.sh export

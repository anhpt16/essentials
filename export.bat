mvn -pl . clean install & ^
mvn -pl essential-sdk clean install & ^
mvn -pl essential-admin-plugin clean install -Pexport,\!test & ^
mvn -pl essential-web-plugin clean install -Pexport,\!test & ^
mvn -pl essential-theme clean install -Pexport,\!test & ^
ezy.bat package & ^
ezy.bat export

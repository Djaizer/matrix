## Build
`mvn clean package`

## Running Locally
* Download JDK 8, set `JAVA_HOME` environment variable
  * e.g. on MacOS: `export JAVA_HOME={Your JDK library location}`
* Build artifact with `mvn clean package`
* Start the service with `java -jar ./target/matrix-*.jar`
* Open browser, and type: `http://localhost:8085/index?N=9`. You should see the matrix diagram.


## Nota bene
Please, pay attention that you can move the services as you wish, to get a better view on a canvas.
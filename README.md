# face-detector-client-server
## A code-challange to join the Project "Emulate"

It is a client-server app that allows client nodes to detect faces on a server node.


## Compile instructions

in order to get a self-contained fat jar, just hit:

```bash
mvn package
```


## Test instructions

You can run the app tests using

```bash
mvn test 
```


## Server

Server can be run as

```bash
java -jar target/face-detector-server-1.0-SNAPSHOT.jar listen [port]
```

where port is an optional integer specifying the TCP/IP port listening for client requests.
If port is not specified, it defaults to 9998.

## Client

Client can be run as

```bash
java -jar target/face-detector-server-1.0-SNAPSHOT.jar send /path/to/test.png [host:port]
```

where /path/to/test.png is path to a photo file to detect a face on,
host:port is an optional TCP/IP host and port pair pointing to a server instance 
to send the photo to and to detect a face on.

The client also saves a copy of the input photo with the found face highlighted
to the result.jpg file in the current working directory.

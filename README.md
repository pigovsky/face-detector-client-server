# face-detector-client-server
## A code-challange to join the Project "Emulate"

It is a client-server app that allows client nodes to detect faces on a server node.


## Server

Server can be run as

```
java com.pigovsky.face_detector.Main listen [port]
```

where port is an optional integer specifying the TCP/IP port listening for client requests.
If port is not specified, it defaults to 9998.

## Client

Client can be run as

```
java com.pigovsky.face_detector.Main send /path/to/test.png [host:port]
```

where /path/to/test.png is path to a photo file to detect a face on,
host:port is an optional TCP/IP host and port pair pointing to a server instance 
to send the photo to and to detect a face on.

The client also saves a copy of the input photo with the found face highlighted
to the result.jpg file in the current working directory.

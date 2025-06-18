face-detector: target/face-detector-server-1.0-SNAPSHOT.jar
	docker compose build
target/face-detector-server-1.0-SNAPSHOT.jar:
	mvn package
clean:
	rm target/*.jar
up:
	docker compose up

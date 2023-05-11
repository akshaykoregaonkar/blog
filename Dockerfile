FROM maven:3.8.3-openjdk-17-slim
WORKDIR blog
COPY pom.xml .

RUN mvn dependency:go-offline

COPY . .

RUN mvn clean install

CMD mvn spring-boot:run

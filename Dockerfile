FROM openjdk:12-alpine
COPY ./out/artifacts/capital_gains_jar/capital-gains.jar /app/capital-gains.jar
COPY ./out/artifacts/capital_gains_jar/capital-gains-unit-test.jar /app/capital-gains-unit-test.jar
WORKDIR /app
ENTRYPOINT ["java", "-jar", "capital-gains.jar"]
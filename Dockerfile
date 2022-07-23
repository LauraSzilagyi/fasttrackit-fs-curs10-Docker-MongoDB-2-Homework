FROM eclipse-temurin:18 as BUILD_IMAGE
COPY . .
RUN ./mvnw clean package -DskipTests

FROM eclipse-temurin:18
COPY --from=BUILD_IMAGE /target/curs10-homework-*.jar curs10-homework.jar
ENTRYPOINT ["java", "-jar", "curs10-homework.jar"]
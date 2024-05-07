# Utiliser une base de JDK officielle
FROM openjdk:21-jdk AS build

# Définir le répertoire de travail dans le conteneur
WORKDIR /app

# Copier le fichier de build Maven (pom.xml) et les sources localement vers le conteneur
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .
COPY src src

# Construire l'application en utilisant Maven wrapper
RUN ./mvnw install -DskipTests

# Exécuter une nouvelle étape pour minimiser la taille de l'image finale
FROM openjdk:21-jdk
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar

# Exposer le port que l'application utilise
EXPOSE 8081

# Définir la commande pour démarrer l'application
CMD ["java", "-jar", "app.jar"]

# Usa la imagen específica de OpenJDK 21 como sustituto de la versión 23
FROM openjdk:23-jdk-slim

# Establece el directorio de trabajo
WORKDIR /app

# Copia el archivo JAR generado por Maven a este directorio
COPY target/Space.ship-0.0.1-SNAPSHOT.jar app.jar

# Expone el puerto de la aplicación
EXPOSE 8080

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]

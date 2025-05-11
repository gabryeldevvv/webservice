# Use uma imagem base com o JDK (Java Development Kit)
FROM openjdk:11-jdk-slim

# Instalar Maven
RUN apt-get update && apt-get install -y maven

# Definir o diretório de trabalho dentro do container
WORKDIR /app

# Copiar os arquivos do projeto para o diretório de trabalho
COPY . /app

# Executar o build com Maven
RUN mvn clean package

# Definir o comando de execução
CMD ["java", "-jar", "target/seu-app.jar"]

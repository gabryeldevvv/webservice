# Use uma imagem base com o JDK 21
# atualizar
FROM openjdk:21-jdk-slim

# Instalar Maven
RUN apt-get update && apt-get install -y maven

# Definir o diretório de trabalho dentro do container
WORKDIR /app

# Copiar os arquivos do projeto para o diretório de trabalho
COPY . /app

# Executar o build com Maven
RUN mvn clean package -DskipTests -X

# Definir o comando de execução
EXPOSE 8080

COPY --from-build "target/api-0.0.1-SNAPSHOT.jar"

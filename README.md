# IMLID - IML Identification System

[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-4.0.3-brightgreen)](https://spring.io/projects/spring-boot)
[![Java](https://img.shields.io/badge/Java-25-orange)](https://www.oracle.com/java/)
[![PostgreSQL](https://img.shields.io/badge/PostgreSQL-18-blue)](https://www.postgresql.org/)

O **IMLID** é um sistema especializado para a gestão e identificação de casos no Instituto Médico Legal (IML). O objetivo do projeto é fornecer uma plataforma robusta para o registro de cadáveres não identificados, permitindo o armazenamento de características físicas detalhadas, metadados de casos e imagens associadas.

## 🚀 Tecnologias Utilizadas

- **Linguagem:** Java 25
- **Framework:** Spring Boot 4.0.3
- **Persistência:** Spring Data JPA / Hibernate
- **Banco de Dados:** PostgreSQL 18
- **Migração de Dados:** Flyway
- **Armazenamento de Arquivos:** MinIO (Object Storage)
- **Segurança:** Spring Security com JWT (jjwt)
- **Utilitários:** Lombok, Jakarta Validation
- **Containerização:** Docker & Docker Compose

## 🛠️ Funcionalidades Principais

- **Gestão de Usuários:** Cadastro de perfis administrativos, peritos e auditores.
- **Registro de Casos:** Cadastro detalhado de cadáveres, incluindo:
  - Código do caso e número de registro IML.
  - Procedência e data/hora aproximada da morte.
  - Características físicas (etnia, cor de olhos, cor de cabelo, tatuagens).
  - Estimativa de idade (mínima e máxima).
- **Gestão de Imagens:** Upload de fotos associadas aos casos, armazenadas de forma segura no MinIO e indexadas no banco de dados.
- **Pesquisa e Listagem:** Recuperação de casos para facilitar o processo de identificação.

## 🏗️ Estrutura do Banco de Dados

O sistema utiliza o PostgreSQL com o auxílio do Flyway para versionamento. As principais tabelas são:
- `usuarios`: Armazena os dados de acesso e cargos (ADMIN, PERITO, AUDITOR).
- `casos`: Centraliza as informações técnicas e físicas dos cadáveres.
- `caso_imagens`: Gerencia os metadados das imagens (caminho no storage, hash SHA256, tipo de arquivo).

## ⚙️ Como Executar o Projeto

### Pré-requisitos
- Docker e Docker Compose instalados.
- JDK 25 configurado.
- Maven 3.9+ ou use o `mvnw` incluso.

### Passo 1: Subir a infraestrutura (PostgreSQL, pgAdmin, MinIO)
```bash
docker-compose up -d
```

### Passo 2: Configurar o ambiente
Verifique o arquivo `src/main/resources/application.properties` para garantir que as credenciais do banco de dados e do storage coincidam com o que foi definido no `docker-compose.yml`.

### Passo 3: Executar a aplicação
```bash
./mvnw spring-boot:run
```

A aplicação estará disponível em `http://localhost:8080`.

## 📍 Endpoints Principais (API)

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| POST | `/auth/cadastro` | Cadastra um novo usuário no sistema |
| POST | `/casos` | Registra um novo caso de identificação |
| GET | `/casos` | Lista todos os casos registrados |
| POST | `/casos/{id}/imagens` | Faz o upload de uma imagem para um caso específico |

## 🔗 Serviços Adicionais (Docker)
- **pgAdmin:** `http://localhost:5050` (Credenciais no `docker-compose.yml`)
- **MinIO Console:** `http://localhost:9001` (Credenciais no `docker-compose.yml`)

## 📝 Licença
Este projeto está sob licença proprietária/interna até que se defina uma licença de código aberto.

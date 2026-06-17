[README-granja-conectada.md](https://github.com/user-attachments/files/29067156/README-granja-conectada.md)
# 🐔 Granja Conectada — Backend

API REST para gerenciamento de granjas, desenvolvida em Java com Spring Boot. Parte de um sistema integrado com um serviço de Machine Learning que prevê o peso esperado das aves com base no consumo de ração.

Repositório do serviço de ML: [Granja-ml](https://github.com/portellaVinicius/Granja-ml)

---

## Arquitetura do sistema

```
                    ┌─────────────────────┐
                    │   Granja Conectada  │
                    │   (Spring Boot)     │
                    │                     │
                    │  - Gerencia aves    │
                    │  - Registra ração   │
                    │  - Persiste no DB   │
                    └─────────┬───────────┘
                              │ HTTP
                              ▼
                    ┌─────────────────────┐
                    │     Granja-ml       │
                    │     (FastAPI)       │
                    │                     │
                    │  - Modelo sklearn   │
                    │  - Previsão de peso │
                    └─────────────────────┘
                              │
                    ┌─────────────────────┐
                    │     PostgreSQL      │
                    └─────────────────────┘
```

---

## Tecnologias

- **Java 17**
- **Spring Boot 3.5**
- **Spring Data JPA**
- **PostgreSQL**
- **Lombok**
- **MapStruct**
- **Bean Validation**
- **Maven**

---

## Como rodar

**Pré-requisitos:** Java 17+, Maven, PostgreSQL rodando

**1. Configurar banco de dados**

Crie um banco PostgreSQL e configure as credenciais em `src/main/resources/application.properties`:
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/granja
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
spring.jpa.hibernate.ddl-auto=update
```

**2. Subir o serviço de ML** (necessário para as previsões)

Siga as instruções do [Granja-ml](https://github.com/portellaVinicius/Granja-ml) para subir a API Python na porta 8000.

**3. Rodar o projeto**
```bash
./mvnw spring-boot:run
```

A API estará disponível em `http://localhost:8080`.

---

## Relacionado

- [Granja-ml](https://github.com/portellaVinicius/Granja-ml) — Serviço de ML em Python/FastAPI para previsão de peso das aves

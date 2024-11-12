## Weather API
Uma API RESTful que permite aos usuários consultar informações meteorológicas de diferentes cidades e gerenciar uma lista de cidades favoritas. Integrada com OpenWeather API para dados meteorológicos em tempo real.
## 🚀 Funcionalidades

- Consulta de dados meteorológicos por cidade
- Sistema de autenticação JWT
- Gerenciamento de cidades favoritas
- Cache com Redis para melhor performance
- Paginação de resultados

## 🛠️ Tecnologias Utilizadas

- Java: Linguagem de programação utilizada para o backend
- Spring Boot: Framework para criação de aplicações Java baseadas em Spring
- Spring Security: Para autenticação e autorização de usuários
- JWT (JSON Web Token): Para autenticação de API com tokens
- Hibernate: ORM para gerenciamento do banco de dados
- MySQL: Banco de dados relacional utilizado
- Redis: Gerenciamento de cache
- Maven: Gerenciador de dependências e build
- Git Controle de versão

## 📋 Pré-requisitos

- [Docker](https://www.docker.com/products/docker-desktop) (certifique-se de que o Docker e o Docker Compose estão instalados e funcionando na sua máquina)
- [Git](https://git-scm.com/book/en/v2/Getting-Started-Installing-Git)
- [Postman]

## Passos para Rodar o Projeto com Docker

1. **Clone o Repositório**
   
Primeiro, clone este repositório na sua máquina:

```bash
  git clone https://github.com/Gustav0-12/weather-api.git
  cd weather-api
  ```

2. **Rodar o projeto com Docker**
```bash
 docker compose up 
 ```

3. **Acessar a API**
```bash
 Após a inicialização, você pode acessar a API em http://localhost:8080.
 ```

## 📌 Endpoints
**Autenticação**

POST api/auth/register
```json
{
    "username":"example",
    "email":"example@gmail.com",
    "password":"123",
    "userRole":"BASIC"
}
```

POST api/auth/login - Realizar login e obter token JWT
```json
{
    "email":"a@gmail.com",
    "password":"123"
}
```

**Cidades Favoritas**
```
GET api/favorite?page=0&size=2 - Listar cidades favoritas
```

```
POST api/favorites/add?cityName={NomeDaCidade} - Adicionar cidade aos favoritos
```

```
DELETE api/favorites/remove?cityName={NomeDaCidade} - Remover cidade dos favoritos
```

**Clima**
```
GET api/weather?cityName={NomeDaCidade} - Buscar informações meteorológicas de uma cidade
```

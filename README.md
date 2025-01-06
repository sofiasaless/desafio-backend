
# Desafio Back-end com API RESTful

Este projeto foi desenvolvido com o objetivo de apresentar uma solução com API RESTful para o [desafio técnico de pagamento simplificado.](https://github.com/PicPay/picpay-desafio-backend)
## Tecnologias adotadas

- [Spring Boot](https://spring.io/projects/spring-boot)
- [Maven](https://maven.apache.org/)
- [PostgreSQL](https://www.postgresql.org/)
- [Docker](https://www.docker.com/)
- [Swagger: API Documentation](https://swagger.io/)


## Instalação

1. Clone o repositório
```bash
  git clone https://github.com/sofiasaless/desafio-backend
```

2. Navegue até o diretório e execute o container docker
```bash
  cd desafio-backend
  docker-compose up
```

3. Instale as depedências e execute o projeto com o Maven
```bash
  mvn clean install
  mvn spring-boot:run
```

- Para executar os testes unitários
```bash
  mvn test
```
## Uso

Acesse a API em [http://localhost:8080](http://localhost:8080)

### End-points

#### Criação de usuários normais e usuários lojistas

```bash
POST - http://localhost:8080/cadastrar/usuario
```

```json
{
  "nome":"Eddard Stark",
  "documentacao":"029.322.394-09",
  "email":"winterfell@hotmail.com",
  "senha":"3900",
  "saldo":100,
  "tipoDoUsuario":"NORMAL"
}
```

```json
{
  "nome":"Cersei Lannister",
  "documentacao":"237.986.001-98",
  "email":"joffreyMyrcTomm@gmail.com",
  "senha":"832901l",
  "saldo":9200,
  "tipoDoUsuario":"LOJISTA"
}
```

#### Transferências de pagamentos entre usuários

```bash
POST - http://localhost:8080/transferencia/pagar
```

```json
{
  "valor":20,
  "pagadorId":3,
  "beneficiarioId":4
}
```

#### Acesso a documentação da API

```bash
GET - http://localhost:8080/swagger-ui/index.html
```
## Contribuições

Contribuições são sempre bem-vindas! Este foi meu primeiro desafio técnico resolvido, então sinta-se à vontade para abrir issues, enviar pull requests, propor melhorias de código ou encontrar erros que eu tenha deixado escapar!
## Licença

[MIT](https://choosealicense.com/licenses/mit/)


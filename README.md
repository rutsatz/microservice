# Sobre o projeto

O objetivo do projeto é gerenciar o cadastro de cidades e de clientes através de micro serviços. O desenvolvimento foi realizado utilizando o Spring Framework, que é bastante conhecido e utilizado devido a sua praticidade e facilidade de uso. O banco de dados utilizado foi o PostgreSQL, em conjunto com a JPA, que é um framework ORM que permite abstração da camada de persistência e nos traz a capacidade de independência de banco. Por ser um projeto de teste, a JPA foi configurada para criar as tabelas, no entanto, em ambientes reais existem ferramentas mais interessantes para o versionamento do banco de dados, como o Flyway, por exemplo.

# Sobre os serviços

Foram criados cinco serviços:

 - **Config Server**: Servidor de configuração, onde os demais serviços vão buscar as suas configurações quando estiverem subindo. Uma das vantagens em se utilizar essa estratégia é a capacidade de escalabilidade do projeto, uma vez que as configurações estão centralizadas num único servidor, facilitando a manutenção.
	Executa na porta 8888.

 - **Api Gateway**: Servidor que irá receber as requisições dos clientes. A principal vantagem de utilizar esse servidor é que os clientes precisam conhecer somente esse serviço, sem se preocupar com os endereços e portas dos outros serviços, pois o server irá fazer o direcionamento para o serviço correto.
Executa na porta 5555.

- **Eureka Server**: O Eureka é responsável por conhecer os serviços que estão rodando. Quando um serviço sobe, ele se registra no Eureka, permitindo que novos serviços sejam adicionados de maneira rápida e fácil.
Executa na porta 8761.

- **City Service**: Serviço responsável por gerenciar o cadastro das cidades. Executa na porta 8081. A documentação da API pode ser acessada no endereço [http://localhost:5555/city/swagger-ui.html](http://localhost:5555/city/swagger-ui.html).

- **Customer Service**: Serviço responsável por gerenciar o cadastro dos clientes. Ao efetuar um novo cadastro, ele se comunica com o city service para validar se a cidade informada está cadastrada. Executa na porta 8080. A documentação da API pode ser acessada no endereço [http://localhost:5555/customer/swagger-ui.html](http://localhost:5555/customer/swagger-ui.html).

# Compilando o projeto

O projeto foi desenvolvido utilizando o Eclipse como IDE. Dessa forma é possível baixá-lo e executar diretamente por ele.

Também é possível compilar cada projeto e executá-los manualmente. Para isso,  é utilizado o maven através do comando abaixo:

```mvn clean compile```

Executar os testes:

```mvn test```

Para gerar um binário executável, empacote o arquivo:

```mvn package```

Para rodar o projeto localmente, configure o banco de dados no application.properties. Por padrão, ele tenta acessar o database "city" no city service e customer no customer service, utilizando user e senha "postgres". Se for usar essas configurações padrões, crie os databases com os comandos:

```create database city;```
```create database customer;```

Para executar, compile e empacote cada projeto e para cada um deles acesse a pasta "target" e execute:

```java -jar <project name>.jar```

Exemplo rodando os 5 serviços:

```
java -jar config-server.jar
java -jar eureka-server.jar
java -jar zuul.jar
java -jar city.jar
java -jar customer.jar
```

# Consumindo a API

Abaixo serão detalhadas as informações para consumir os endpoints dos serviços.

## Consumindo o city service
Para consultar a documentação completa da API, acesse: [http://localhost:5555/city/swagger-ui.html](http://localhost:5555/city/swagger-ui.html)

Abaixo serão apresentados alguns exemplos de consumo do serviço.

### Cadastrando um nova cidade
```curl --location --request POST http://localhost:5555/city/api/v1/cities' --header 'Content-Type: application/json' --data-raw '{"name" : "Santa Cruz do Sul", "state" : "RS"}'```

### Consultar cidade pelo nome

```curl --location --request GET 'http://localhost:5555/city/api/v1/cities?name=Santa%20Cruz%20do%20Sul'```


## Consumindo o customer service
Para consultar a documentação completa da API, acesse:
[http://localhost:5555/customer/swagger-ui.html](http://localhost:5555/customer/swagger-ui.html)

Abaixo serão apresentados alguns exemplos de consumo do serviço.

### Cadastrando um novo cliente
```curl --location --request POST 'http://localhost:5555/customer/api/v1/customers' --header 'Content-Type: application/json' --data-raw '{"name" : "João da Silva","gender" : "MALE","birthDate" : "1990-11-03","age": 29, "city" : "Santa Cruz do Sul"}'```

### Consultar cliente pelo nome

```curl --location --request GET 'http://localhost:5555/customer/api/v1/customers?name=Maria'```

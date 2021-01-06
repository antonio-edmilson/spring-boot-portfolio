# Aplicação Digito Único

### 1- Tecnologias 

* Spring Boot 2.4.1
* Spring Data JPA
* Banco de dados h2
* Java 11
* JUnit
* Maven
* Postmon
* Swagger

### 2 Observações Gerais

## 2.1 Compilar
   2.2.1- Após baixar o projeto execute o comando maven
   mvn clean package 

## 2.2 Executar
   2.2.1- No diretório raiz do projeto executa comando maven: 
   		mvn spring-boot:run
   2.2.2
   		java.exe -jar ..\target\spring-boot-portifolio-0.0.1-SNAPSHOT.jar br.com.DasafioBancoInterApplication (outra maneira de executar aplicação)

## 2.3 Executar Test com Maven
   mvn test
   mvn -Dtest=DigitoUnicoControllerTests

## 2.4 Swagger (
   http://localhost:8080/swagger-ui/  (Obs: O link está acessivel com aplicação inicializada)
   
   
###  3 Funcionalidades

###  3.1 CRUD de usuário

* CRUD para usuários (Cadastrar, Alterar, Deletar e Consultar)

### 3.2 Dígito único

* Dado um número o mesmo deve ser calculado seu digito único seguindo as regras da seção 4.2
* Cadastrar dígito único para usuário cadastrado.
* Consulta digitos únicos por usuários

### 4 Regras de negócios

* Deverá ser criado um cache em memória para guardar os últimos 10 cálculos realizados de digito únicos.
* Dado dois números n e k, digito único deverá ser criado da concatenação da string n * k.
* Se N tem apenas um dígito, então o seu dígito único é N. Caso contrário, o dígito único de N é igual ao dígito único da soma dos dígitos de N.
* A função digitoUnico deverá ter os seguintes parâmetros; n: uma string representado um inteiro. 1<=n<=10ˆ1000000 e k: um inteiro representando o número de vezes da concatenação 1<=k<=10ˆ5.
* A função digitoUnico deverá obrigatoriamente retornar um inteiro.



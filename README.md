<b>API REST desenvolvida utilizando Spring Boot<br><br></b>
Gerar Vouchers de desconto para Destinatários, baseado em uma Oferta Especial e uma data de expiração.

Instruções para subir um ambiente de desenvolvimento:<br>
Necessário ter Java 17 ou alterar o pom.xml <br>
Configurar as propriedades do mongoDB em application.properties<br>
./mvnw clean install spring-boot:run 


Documentação SwaggerUI:<br>
http://127.0.0.1:8080/docs/swagger-ui <br>
Através daqui é possível utilizar todos os endpoints do sistema.<br>

Fluxo:<br>
<br>
-Cadastrar Destinatário (POST com nome e email): Cadastra um Destinatário no sistema. <br> 
-Listar Destinatários (GET): Lista dos Destinatários cadastrados. <br>
<br>
-Cadastrar Oferta Especial (POST com percentual de desconto, nome da oferta): Cadastra uma Oferta Especial no sistema.<br>
-Listar Oferta Especial (GET): Lista de todas Oferta Especial cadastradas<br>
<br>
-Gerar vouchers (POST com data de expiração do Voucher e nome da Oferta Especial cadastrada)<br>
Vai gerar um Voucher de 8 dígitos para cada Destinatário cadastrado<br>
<br>
O Voucher é atrelado a um Destinatário e uma Oferta Especial, o percentual de desconto é definido pela Oferta Especial.<br>
<br>
É possível fazer um POST  com o código do Voucher + email do Destinatário para verificar se o Voucher é válido e receber qual é a Oferta Especial e qual data de expiração do Voucher. <br>
Também é possível fazer um POST para consumir de fato o voucher (O que determina o voucher como consumido, é ter a propriedade dataUtilizacao diferente de null).<br>

<b>API REST desenvolvida utilizando Spring Boot<br><br></b>


Java 17

Instruções para subir um ambiente de desenvolvimento:<br>
Configurar as propriedades do mongoDB em application.properties<br>
./mvnw clean install spring-boot:run 

Documentação SwaggerUI:<br>
http://127.0.0.1:8080/docs/swagger-ui <br>
Através daqui é possível utilizar todos os endpoints do sistema.<br>

Fluxo:<br>
<br>
-Cadastrar Destinatário (POST com nome e email)<br>
-Cadastrar Oferta Especial (POST com percentual de desconto, nome da oferta)<br>
<br>
-Gerar vouchers (POST com data de expiração e nome da Oferta Especial)<br>
Vai gerar um voucher de 8 dígitos para cada Destinatário cadastrado<br>
É possível fazer um POST com o voucher + email do destinatário para conferir a promoção e data do voucher, e outro POST para consumir de fato o voucher (O que determina o voucher como consumido, é ter a propriedade dataUtilizacao diferente de null).<br>

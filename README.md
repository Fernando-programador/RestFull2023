# rest_full_2023
API completa para exemplo

o que foi feito na API:

1-> Value Object
2-> Mapeamento com Dozer Mapper
3-> Versionamento
4-> Migration
5-> json serialition
6-> content negotiation (xml)
na query param ->  http://localhost:8080/person/6?mediaType=xml
no header -> http://localhost:8080/person/6 (no postman no header acrescentar KEY "accept", VALUE "application/xml")+
7-> Hateoas
8-> Mockito para teste
clicar em PersonService e entre em junit test case e selecionar(New Junit jupter test e @BeforeEach setUp()) e selecionar todos os PersonService
digitar o import manual -> import static org.mockito.Mockito.when; na classe  PersonServicrTest.java

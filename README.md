# rest_full_2023
API completa para exemplo

o que foi feito na API:

1-> Value Object
![value Object](https://user-images.githubusercontent.com/95228196/212233346-aa0f68ce-0ae0-4133-a13c-6b2a9f2407f0.png)

2-> Mapeamento com Dozer Mapper
![dozer mapper](https://user-images.githubusercontent.com/95228196/212233367-a817abfc-c346-400e-9daa-5eafbdab6c85.png)

3-> Versionamento
![versionamento](https://user-images.githubusercontent.com/95228196/212233382-d273d713-b1cc-4e56-9d49-c1513401f68c.png)

4-> Migration
![migration](https://user-images.githubusercontent.com/95228196/212233395-dda79771-3174-4ded-94be-b885f5aac0ee.png)

5-> json serialition
![json serialition](https://user-images.githubusercontent.com/95228196/212233406-e72bbdcc-b3eb-4939-b22f-8d0e802804bc.png)

6-> content negotiation (xml)
na query param ->  http://localhost:8080/person/6?mediaType=xml
no header -> http://localhost:8080/person/6 (no postman no header acrescentar KEY "accept", VALUE "application/xml")+
![content negotiation](https://user-images.githubusercontent.com/95228196/212233447-d6374c83-7788-4bb9-8079-4a4201c620cd.png)

7-> Hateoas
![import do hateoas](https://user-images.githubusercontent.com/95228196/212233464-63c2495f-d494-42b3-8fdb-653b1f477a83.png)
![hateoas](https://user-images.githubusercontent.com/95228196/212233473-ee869b70-7a9e-4d30-97f9-ee5449511dab.png)

8-> Mockito para teste
clicar em PersonService e entre em junit test case e selecionar(New Junit jupter test e @BeforeEach setUp()) e selecionar todos os PersonService
digitar o import manual -> import static org.mockito.Mockito.when; na classe  PersonServicrTest.java
![mockito](https://user-images.githubusercontent.com/95228196/212233719-b8964645-e76b-45ca-938f-b8e3fc4a38cf.png)

9-> swagger

# dna-meli
Um projeto do Cesar do filme Planeta dos Macacos para classificar DNAs humanos e de símios [ABSTRAÇÃO]. 

URL da aplicação para acessar os serviços via SWAGGER: 
- http://ec2-3-82-5-211.compute-1.amazonaws.com/swagger-ui.html

Dados de acesso:
 - Usuário: admin
 - Senha: dna-api


Exemplos de chamadas via curl:

MÉTODO POST
- Verifica se é símio ou humano
- curl -X POST "http://ec2-3-82-5-211.compute-1.amazonaws.com/v1/simian" -H "accept: */*" -H "Content-Type: application/json" -d "{ "data": ["CTGAGA", "CTATGC", "TATTGT", "AGAGGG", "CCCCTA", "TCACTG"]}"


MÉTODO GET
- Calcula a proporção
- curl -X GET "http://ec2-3-82-5-211.compute-1.amazonaws.com/v1/stats" -H "accept: */*"


MÉTODO GET
- Chamada de serviço busca por ID
- curl -X GET "http://ec2-3-82-5-211.compute-1.amazonaws.com/v1/7" -H "accept: */*"


MÉTODO DELETE
- Exclusão de DNA
- curl -X DELETE "http://ec2-3-82-5-211.compute-1.amazonaws.com/v1/7" -H "accept: */*"


MÉTODO POST
- Salvar um DNA pré-determinado
- curl -X POST "http://ec2-3-82-5-211.compute-1.amazonaws.com/v1" -H "accept: */*" -H "Content-Type: application/json" -d "{ "data": ["CTGAGA", "CTATGC", "TATTGT", "AGAGGG", "CCCCTA", "TCACTG"], "is_simian": true}"


MÉTODO PUT
- Atualiza um DNA pré-determinado
- curl -X POST "http://ec2-3-82-5-211.compute-1.amazonaws.com/v1" -H "accept: */*" -H "Content-Type: application/json" -d "{ "data": ["CTGAGA", "CTATGC", "TATTGT", "AGAGGG", "CCCCTA", "TCACTG"], "is_simian": true, "id": "7"}"


MÉTODO GET
- Busca uma lista de DNAs por paginação
- curl -X GET "http://ec2-3-82-5-211.compute-1.amazonaws.com/v1?page=0&size=10" -H "accept: */*" .

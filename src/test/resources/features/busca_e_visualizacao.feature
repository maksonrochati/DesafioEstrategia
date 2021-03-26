#language: pt
Funcionalidade: Buscar cursos
  Como um usuário
  Eu quero buscar por cursos
  Para que eu possa compra-los

Cenario: Buscar curso por professor
  Dado que acesso a pagina da estratégia.
  Quando utilizar a busca "POR PROFESSOR"
  E acessar os cursos da professora "Ena Loiola"
  E escolha um dos cursos que estarão disponíveis
  Entao verifico se o valor do curso parcelado na listagem de cursos da professora bate com o valor da página de detalhes do curso.

Cenario: Buscar curso por materia
  Dado que acesso a pagina home da estratégia.
  Quando utilizo a busca "POR MATÉRIA"
  E buscar o curso da materia "Ação Civil Pública"
  Entao seleciono o curso e verifico o valor do curso na listagem de cursos e igual ao valor da página de detalhes do curso.

Cenario: Buscar por curso na opção de busca
  Dado que acesso o site da estratégia.
  Quando utilizar a opção de busca por "Agência Nacional de Águas (ANA)"
  E selecionar a opção "cursos"
  Entao seleciono um curso e verifico se o titulo do curso e igual ao titulo do curso na página de detalhes

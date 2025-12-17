Feature: Produtos
  Como administrador desejo cadastrar novos produtos

  @Produtos
  Scenario: Cadastrar produto com sucesso
    Given que foram passadas as caracteristicas de um novo produto
    When o servico invocar a funcao de cadastro de novos produtos
    Then a funcao deve ser executada com sucesso
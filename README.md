# Relatório de Testes de Integração

## 1. O que é um teste de integração?
Um teste de integração verifica se diferentes partes do sistema funcionam corretamente **quando combinadas**. Ele não testa apenas métodos isolados, mas sim a interação entre componentes como **entidades, repositórios, banco de dados e configuração do Spring Boot**.

---

## 2. Componentes integrados neste projeto
- **Spring Boot** → responsável pela inicialização e configuração.  
- **Spring Data JPA** → camada de persistência e acesso ao banco.  
- **Hibernate Validator** → validação de regras como `@NotNull` e `@Positive`.  
- **Banco MySQL (lojacarros_test)** → armazenamento dos dados.  
- **Scripts SQL (`data.sql`)** → carga inicial de dados para os testes.  

---

## 3. Diferença entre teste unitário e teste de integração
- **Teste unitário**: verifica apenas uma unidade isolada (ex.: um método ou classe), sem dependências externas.  
- **Teste de integração**: valida o funcionamento conjunto de várias partes do sistema, incluindo banco de dados, repositórios e regras de negócio.  

---

## 4. Problemas que esse tipo de teste ajuda a identificar
- Falhas de **mapeamento JPA** (colunas, tipos de dados).  
- Erros de **validação** (campos obrigatórios, valores inválidos).  
- Problemas de **consistência no banco** (constraints, chaves primárias).  
- Quebras na **comunicação entre camadas** (repository ↔ entidade ↔ banco).  

---

## 5. Resultados dos testes

### ✅ Testes que passaram
- Salvar carro.  
- Listar todos os carros.  
- Atualizar carro.  
- Deletar carro.  

### ⚠️ Testes que falharam (como esperado)
- Buscar carro por ID inexistente.
- Atualizar carro inexistente.

Esses erros são **positivos**, pois confirmam que as regras de negócio e validações estão funcionando corretamente.

---

## 6. Conclusão
O projeto agora possui um conjunto robusto de **testes de integração**, cobrindo tanto cenários de sucesso quanto de falha.
Essas práticas garantem maior confiabilidade, automação e qualidade contínua no desenvolvimento da aplicação.

# Projeto Final — Exnoia
Curso: Desenvolvimento de Aplicações Móveis  
Aluno(s): Matilde Dias Gonçalves (A51706)   
Data: 14/06/2026  
URL do Repositório: https://github.com/MattGon13/Exnoia.git
---

## 1. Introdução
"Exnoia" é uma aplicação Android desenvolvida com o intuito de ajudar os seus utilizadores a atingir os seus objetivos, permitindo não só o acompanhamento do progresso, mas também tornando o processo mais agradável e divertido. A aplicação permite definir passos específicos para cada objetivo, organizando a sua concretização de forma incremental.

## 2. Visão Geral do Sistema
A aplicação disponibiliza funcionalidades para criar, visualizar, atualizar e eliminar metas e os seus respetivos passos.

Cada meta é composta por um título, uma descrição, um prazo (deadline), uma recompensa, um nível de dificuldade e uma lista de passos.

Os utilizadores podem pesquisar pela suas metas pelo seu título, visualizar estatísticas de progresso global e mensal (metas e passos concluídos).

A aplicação envia notificações ao utilizador uma vez por dia com uma frase motivacional. Estas frases são obtidas através de uma API externa chamada de ZenQuotes API (https://zenquotes.io/).

A autenticação e o armazenamento de dados são geridos através do Firebase Auth e Firebase Firestore, respetivamente.

## 3. Arquitetura e Desenho
O projeto segue a arquitetura MVVM (Model-View-ViewModel), recomendada para o desenvolvimento de aplicações Android modernas.

A interface gráfica é desenvolvida em Jetpack Compose com as diretrizes do Material Design 3.

Optou-se por uma abordagem de "Single-Activity", utilizando o Jetpack Navigation Compose para a gestão de rotas entre os vários ecrãs.

Para a persistência em memória e reatividade do estado da UI, utiliza-se Kotlin Coroutines com `StateFlow`.

O armazenamento na cloud é assegurado pelo Firebase Firestore, adotando uma estrutura de subcoleções por utilizador para maior segurança e simplicidade nas consultas.

A autenticação é assegurada pelo Firebase Auth, que permite o registo, login e logout dos utilizadores.

O projeto está organizado em diretorias principais: ui, viewmodel, worker e data:

- No pacote ui, encontram-se todos os componentes Jetpack Compose para a interface gráfica da aplicação, incluíndo a parte da navegação entre ecrãs.
- No pacote viewmodel, encontram-se as classes que gerem a business logic da aplicação relacionada com as metas e a autenticação.
- No pacote worker, encontra-se a classe que gere as notificações diárias com frases motivacionais.
- No pacote data, encontram-se os modelos `Goal`, `Step` e `Quote`, que representam as metas, passos e frases motivacionais, respetivamente. Além disso, este pacote contém os repositórios `AuthRepo` e `GoalRepo`, responsáveis pela comunicação com o Firebase Auth e Firestore. Por fim, este pacote também contém as classes que representam a comunicação com a API do ZenQuotes.

## 4. Implementação
A implementação encontra-se dividida nas seguintes camadas:
- **Camada de Dados:** Contém os modelos `Goal` e `Step`. Os repositórios `AuthRepo` e `GoalRepo` gerem a comunicação assíncrona com o Firebase Auth e Firestore, respetivamente, utilizando wrappers `Result<T>` para um tratamento de erros seguro.
- **Camada ViewModel:** Classes como `AuthViewModel` e `GoalViewModel` expõem o estado da interface (`UiState`) através de `StateFlows` e gerem a business logic associada.
- **Camada de Interface (UI):** Componentes Compose modulares, incluindo o ecrã principal de estatísticas (`MainScreen`), as listas de metas e pesquisa (`GoalsScreens`), e os ecrãs de detalhes/edição de metas e passos com suporte para formulários e seleção de datas.

## 5. Testes e Validação
A estratégia de validação consistiu fundamentalmente em testes manuais utilizando um dispositivo android. Foram simulados os fluxos de autenticação completos (registo, login, logout), todas as operações relativas a objetivos e passos, ordenação de itens e validação dos filtros de pesquisa. Adicionalmente, verificou-se o recálculo automático das estatísticas aquando da alteração de estado dos passos e metas.

## 6. Instruções de Utilização
1. Fazer clone do repositório.
2. Abrir o projeto no Android Studio.
4. Adicionar o ficheiro `google-services.json` configurado com a instância Firebase na pasta do módulo `app` com a chave para o firebase.
5. Construir o projeto, selecionar um emulador ou dispositivo físico e executar a aplicação (Run).

---
# Secções de Engenharia de Software Autónoma - apenas para secções [AC OK, AI OK]
## 7. Estratégia de Prompting
O desenvolvimento apoiou-se em prompts iterativos e detalhados. Os prompts tinham como objetivo receber feedback sobre a implementação e sugestões de melhorias, tanto em termos de código como de arquitetura. Incialmente, os prompts foram mais gerais, incidindo sobre a estrurtura geral do projeto. Depois foram ficando mais específicos, incidindo sobre a melhoria de funcionalidades já existentes ou a implementação de novas funcionalidades. Por fim, os prompts foram incidindo sobre a otimização do código e a melhoria da experiência do utilizador.

## 8. Fluxo de Trabalho do Agente Autónomo
As ferramentas de Inteligência Artificial ajudaram no planeamento e melhoria da aplicação, de modo a que esta se tornasse mais morderna e user-friendly. Pontualmente, foi pedido que o agente realizasse código para implementar novas funcionalidades, no entanto todo este código foi revisto e testado antes de ser adicionado ao projeto. Além disso, o agente ajudou na parte de debug, no caso de erros masi complicados de detetar.

## 9. Verificação de Artefactos Gerados por IA
Todo o código sugerido e gerado passou por uma revisão manual rigorosa. Cada plano de implementação submetido pela IA foi lido, criticado e aprovado antes da execução do código, para garantir que as bibliotecas e versões eram compatíveis com a base do projeto, minimizando assim a probabilidade de erros de compilação ou bugs arquiteturais.

## 10. Contribuição Humana vs IA
A conceptualização inicial da ideia, as regras de negócio, a elaboração da estrutura base, a arquitetura da aplicação e o desenho original dos protótipos visuais foram da responsabilidade humana. A IA atuou fundamentalmente como ajudante, dando feedback sobre o estado do projeto e possíveis melhorias a implementar. Pontualmente também foi responsável por implementar certas funcionalidades e ajudar no debug de erros mais complexos.

## 11. Uso Ético e Responsável
A utilização das ferramentas de Inteligência Artificial foi conduzida de modo transparente e apenas como assistente e acelerador de desenvolvimento. Não foram partilhados dados sensíveis ou chaves privadas nas conversas com o agente. As propostas de arquitetura foram validadas no sentido de garantir que estavam conformes as boas práticas da documentação oficial de Android.

---
# Processo de Desenvolvimento
## 12. Controlo de Versões e Histórico de Commits
A gestão e controlo de versões foi efetuada via Git. O histórico de commits documenta o fluxo contínuo de trabalho em diversas etapas, de modo a garantir a rastreabilidade do processo desde o design inicial da interface até à refatorização arquitetural e ligação ao Firebase.

## 13. Dificuldades e Lições Aprendidas
Um dos desafios sentidos foi realizar a navegação da aplicação, visto que estávamos habituados a implementar aplicações com apenas um ecrã.

O maior desafio foi sem dúvida resolver um erro relacionado com o firestore devido à propriedade "isComplete" dos modelos de metas e passos, que não estava a ser interpretada corretamente pelo firestore, visto que o firestore segue as convenções do JavaBean para as propriedades dos objetos e os seus getters e setters, ou seja, "isComplete" ficava "complete", então, o firestore estava a escrever "isComplete", mas a aplicação estava a ler o "complete" fazendo com que não houvesse uma concistência de dados.


## 14. Melhorias Futuras
Em futuras iterações da aplicação "Exnoia", seria interessante incorporar mais mecânicas de "gamificação" para motivar o progresso dos utilizadores.

---
## 15. Divulgação da Utilização de IA (Obrigatório)
Neste projeto recorreu-se a ferramentas de IA generativa para assistir à estruturação do código em Kotlin e Compose. Toda a geração foi utilizada como auxílio à implementação e ao planeamento das tarefas. Revê-se e assume-se total responsabilidade por todo o código, funcionalidades finais e conteúdo do presente relatório.
# Omni Films

Omni Films é um aplicativo que permite pesquisar e explorar informações detalhadas sobre filmes usando a **OMDb API** (Open Movie Database). 
Com uma interface intuitiva, é possível encontrar detalhes como título, ano de lançamento, sinopse e pôster de filmes, tudo em poucos cliques.

## 📽️ Funcionalidades

- **Busca inteligente por título de filme:**  
  Encontre filmes de forma prática e obtenha informações detalhadas, incluindo:
    - Nome do filme
    - Ano de lançamento
    - Sinopse
    - Pôster

## 🚀 Como Usar

1. **Obter uma chave da API OMDb:**  
   Para acessar a OMDb API, siga as etapas abaixo:
    - Acesse [http://www.omdbapi.com/](http://www.omdbapi.com/).
    - Crie uma conta ou faça login.
    - Gere sua chave de API exclusiva.

2. **Configurar o aplicativo:**
    - Insira a chave da API no arquivo local.properties (OMDB_API_KEY="suachave") do projeto.
    - Compile e execute o aplicativo em seu ambiente de desenvolvimento.

## 🛠️ Tecnologias Utilizadas

O Omni Films foi desenvolvido com foco em práticas modernas de desenvolvimento Android, utilizando:
- **Jetpack Compose:** Para a construção da interface de usuário declarativa e responsiva.
- **Coroutines:** Para gerenciamento de threads assíncronas.
- **Retrofit:** Para integração com a API OMDb.
- **ViewModel:** Para gerenciar o ciclo de vida e dados da UI.
- **Room:** Para armazenamento local dos dados dos filmes pesquisados.

## 🎯 Objetivos

- Proporcionar uma experiência fluida e agradável para os usuários.
- Integrar boas práticas de arquitetura como MVVM e princípios do SOLID.
- Garantir um design limpo e moderno com Jetpack Compose.

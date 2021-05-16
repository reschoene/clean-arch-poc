# Clean Architecture - POC 

### Sobre a arquitetura *Clean*

Criada por Robert Cecil Martin (Uncle Bob), assim como as arquiteturas Onion e Hexagonal, a Clean Arch também tem por objetivo a separação entre responsabilidades (SoC) das partes do sistema, propiciando uma maior manutenibilidade. A arquitetura é organizada em camadas, onde cada uma representa uma área do software. De maneira geral, quanto mais a camada se aproxima do centro, mais alta a abstração se torna. 

#### Regra de dependência

. A regra geral que faz com que esta arquitetura funcione, diz que dependências somente podem apontar para dentro. Desta forma, nomes declarados numa camada mais externa não podem ser mencionados numa camada interna, ou seja, camadas mais internas não devem conhecer as camadas mais externas.



# ![Diagrama](./diagram.jpg) 



#### Princípio da Inversão de Dependência

Nos casos em que camadas externas dependem das camadas internas, a regra da dependência é cumprida. Mas e nos casos onde as camadas internas precisam fazer uso de recursos presentes nas camadas externas? ex.: um caso de uso precisa finalizar persistindo os dados num armazenamento externo, como um banco de dados. Podemos resolver isso sem violar a regra de dependência, fazendo uso do Princípio da Inversão de Dependência. Desta forma no exemplo citado, fazemos com que a camada de caso de uso dependa de uma abstração da persistência de dados, cujo implementação virá nas camadas mais externas.

<br>

### Sobre esta POC

A POC foi desenvolvida em Java e consiste em um projeto *gradle* multi-módulos, sendo eles: 

#### domain-entities
+ Módulo que define as classes de domínio (pojo + regras de negócio individuais). Corresponde ao núcleo da aplicação.

#### use-cases
+ Neste módulo virão as implementações dos casos de uso. Trata-se ainda de uma camada de negócio que não deve depender de forma direta de recursos externos como frameworks.

#### interface-adapters
+ Este módulo corresponde à camada dos adaptadores de interface. É nela onde são feitas as conversões de dados para o formato mais conveniente de cada camada. Este módulo é subdividos em basicamente dois grupos: os adaptadores de *repositories* e de *controllers*:
  1. RepositoryAdapters. Fazem a conversão de dados entre as entidades de negócio (domain entities) e entidades relacionais (mapeamento JPA).
  2. ControllerAdapters. Fazem a conversão de dados entre o formato recebido nos endpoints dos serviços REST (DTOs) e entidades de negócio (domain entities)

#### infrastructure
+ Somente nesta camada devem estar as implementações que possuem dependências diretas a *frameworks* e tecnologias. Nesta camada residem as classes que implementam os serviços de APIs REST, persistência de dados podendo fazer uso de mapeamento objeto-relacional (entities e repositories JPA) e dependências a *frameworks* como o *Spring*. Além disso, é nesta camada onde devem estar os testes unitários e de integração.  

<br>

### Passos para compilar e testar o projeto

1. Certifique-se que esteja no diretório `clean-arch-poc`
2. Execute o comando a seguir para baixar as dependências do projeto, executar seus testes automatizados e compilar o mesmo:   `./gradlew build`. Será gerado o jar da aplicação no diretório `./infrastructure/build/libs`
3. Ainda na raiz do projeto, execute o comando abaixo para iniciar a execução da aplicação na porta 8080
`./gradlew infrastructure:bootRun`
4. Na raíz do projeto disponibilizei o arquivo `CleanArch.postman_collection.json`, ele contem uma *collection* do Postman. Importe esta *collection* no Postman para testar os endpoints do serviço REST. <br><br>

[Clique aqui para visualizar o artigo que explica os princípios da arquitetura no blog do autor](https://blog.cleancoder.com/uncle-bob/2012/08/13/the-clean-architecture.html)


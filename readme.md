# Code Challenge: Ganho de Capital

### Projeto desenvolvido com o objetivo de de cumprir as demandas repassadas do desafio com as premissas de realizar um código: Simples, Elegante e Operacional 

## Sobre o projeto

O projeto foi feito com Java 11 puro, dispensando o uso de qualquer framework e/ou ferramenta adicional, alguns padrões foram adotados.

1. Código escrito 100% em inglês
2. Uso do designer pattern **facade**
3. Classes separadas seguindo o principio da responsabilidade única
4. Utilizar o menor número de else possível para facilitar a leitura do código (nenhum foi utilizado)
5. Uso de *Stdin* para entrada e *Stdout* para saída

## Stacks e Libs

Somente 2 libs foram usadas no projeto o resto foi feito apenas com Java 11

1. Java 11 (Linguagem)
2. Gson 2.9.0 (Lib) - Serialização e Deserialização de Json 
3. JUnit 4.12 + Hamcrest 1.3 (Lib) - Escrita e validações dos testes unitários
4. Dockerfile para  uso de build containerizada

## Estrutura
capital-gains **<- projeto**
* libs **<- libs utilizadas no projeto**
    1. gson-2.9.0.jar
    2. hamcrest-core-1.3.jar
    3. junit-4.12.jar
* src
    - main **<- pacote principal (br.com.nu)**
        * constants
            1. OperationType.java **<- constante responsavel por separar as operações buy e sell**
        * dtos
            1. OperationDTO.java **<- entrada de dados**
            2. TaxDTO.java **<- saida de dados**
        * processors
            1. BuilderOutputProcessor.java **<- classe responsável por garantir a formatação adequada de saida de dados**
            2. TaxBusinessProcessor.java **<- classe responsável por processar as operações e suas lógicas de negócio**
        1. Main.java **<- classe principal, recebe entrada (STDIN) de dados e devolve a resposta adequada (STDOUT)**
    - test **<- pacote relacionado a testes unitários**
        * processors
            1. BuilderOutputProcessorTest.java
            2. TaxBusinessProcessorTest.java
        * AllTests.java **<- centraliza todas as chamadas de teste**
        * TestRunner **<- executa todos os testes**
* Dockefile **<- configuração de build containerizada**
* readme.md **<- documentação do projeto**

## Executando o código
Existem 3 principais formas de executar o código após ele ser descompactado do .zip

1. Através do docker 
    - Abra o prompt de commando no contexto do projeto
    - Rode o comando **docker build . -t capital-gains** (você pode optar por colocar outro nome no lugar de capital-gains)Depois de rodar o comando anterior você gerou localmente uma imagem agora precisa subir a imagem em um container 
    - Rode o comando **docker run -i capital-gains** (ou com o nome que escolheu no comando anterior)
    - Pronto você esta no modo interativo e ja consegue rodar a aplicação via commandline, caso precise rodar novamente é so executar novamente o *segundo comando*
2. Através do .jar
    - Abra o prompt de commando no contexto do projeto
    - Navegue até a pasta /out/artifacts/capital_gains_jar/
    - rode o comando **java -jar capital-gains.jar** (é necessário ter o java 11 ou posterior instalado em sua máquina)
3. Você tambem pode abrir o projeto na sua IDE de preferência e executar o método main na classe *Main.java*

## Executando os testes unitários
Existem 2 principais formas de executar os testes unitários após descompactar o .zip

2. Através do .jar
    - Abra o prompt de commando no contexto do projeto
    - Navegue até a pasta /out/artifacts/capital_gains_jar/
    - rode o comando **java -jar capital-gains-unit-test.jar** (é necessário ter o java 11 ou posterior instalado em sua máquina)
3. Você tambem pode abrir o projeto na sua IDE de preferência e executar o método main na classe *MainTest.java*
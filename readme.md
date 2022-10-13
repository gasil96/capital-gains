# Code Challenge: Ganho de Capital

### O objetivo deste projeto é implementar um programa de linha de comando (CLI) que calcula o imposto a ser pago sobre lucros ou prejuízos de operações no mercado financeiro de ações. Com as premissas de realizar um código: Simples, Elegante e Operacional 

## Regras do Ganho de Capital

O programa deve lidar com dois tipos de operações ( buy e sell ) e ele deve seguir as seguintes regras:
O percentual de imposto pago é de 20% sobre o lucro obtido na operação. Ou seja, o imposto vai ser
pago quando há uma operação de venda cujo preço é superior ao preço médio ponderado de compra.
Para determinar se a operação resultou em lucro ou prejuízo, você pode calcular o preço médio
ponderado, então quando você compra ações você deve recalcular o preço médio ponderado
utilizando essa fórmula: nova-media-ponderada = ((quantidade-de-acoes-atual * media-ponderadaatual) + (quantidade-de-acoes * valor-de-compra)) / (quantidade-de-acoes-atual + quantidadede-acoes-compradas) . Por exemplo, se você comprou 10 ações por R$ 20,00, vendeu 5, depois
comprou outras 5 por R$ 10,00, a média ponderada é ((5 x 20.00) + (5 x 10.00)) / (5 + 5) =
15.00 .
Você deve usar o prejuízo passado para deduzir múltiplos lucros futuros, até que todo prejuízo seja
deduzido.
Prejuízos acontecem quando você vende ações a um valor menor do que o preço médio ponderado de
compra. Neste caso, nenhum imposto deve ser pago e você deve subtrair o prejuízo dos lucros
seguintes, antes de calcular o imposto.
Você não paga nenhum imposto se o valor total da operação (custo unitário da ação x quantidade) for
menor ou igual a R$ 20000,00. Use o valor total da operação e não o lucro obtido para determinar se o
imposto deve ou não ser pago. E não se esqueça de deduzir o prejuízo dos lucros seguintes.
Nenhum imposto é pago em operações de compra.
Você pode assumir que nenhuma operação vai vender mais ações do que você tem naquele momento

## Exemplo

### Este é um exemplo da entrada:
```json
[{"operation":"buy", "unit-cost":10.00, "quantity": 10000},
{"operation":"sell", "unit-cost":20.00, "quantity": 5000}]
[{"operation":"buy", "unit-cost":20.00, "quantity": 10000},
{"operation":"sell", "unit-cost":10.00, "quantity": 5000}]
```
### Este é um exemplo da saída:
```json
[{"tax":0.00}, {"tax":10000.00}]
[{"tax":0.00}, {"tax":0.00}]
```

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

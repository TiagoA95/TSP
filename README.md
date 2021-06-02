# TSP
Resolvendo o problema TSP - "Travelling Salesman Problem" utilizando algoritmos "Nearest Neighbour First", "Hill Climbing" e "Simulated Annealing".


É de notar que nem todos os algorismos não dão uma solução correta (mais um Observações).


Grafos gerados aleatóriamente, dados n pontos no intervalo [-m;m].

_________________________________________________________

Compilar e executar:
$javac Main.java && java Main

Será pedido o seguinte input para ser gerado, aleatoriamente, um grafo:
"n: " -> número de pontos do grafo
"m: " -> intervalo das coordenadas dos pontos a serem geradas aleatoriamente: (x,y) onde x e y [-m,m]

_________________________________________________________


# Observações:

Se o valor de n e m dados forem muito próximos o nosso programa demora bastante tempo a arranjar um caso aleatório, no entanto depois de gerado o caso teste é suficientemente rápido a encontrar as soluções pelos vários métodos.

No caso de n ser muito grande (por exemplo 100) o programa jé é mais lento.

Verifica-se que a heurística "nearest-neighbour first" nem sempre encontra um políggono simples (ciclo fechado). 


= = = = = = = = = 

Hill Climbing:

Optar por candidato que mais reduz o perimetro) - a estratégia usada é greedy e tenta reduzir o número deiteraçõoes no Hill Climbing. Pela forma como o problema foi definido, os candidatos da vizinhança têm sempre menor perímetro e por isso a solução converge para um polígono simples.

Optar pelo primeiro candidato) - a estratégia usada pode ser aplicada pois o primeiro candidato terá normalmente menos uma interseçãoao que o atual. Pode-se não encontrar a solução se um dos candidatos escolhidos tiver um número maior de interseções.

Optar pelo candidato que tiver menos conflitos de arestas) - escolher o candidato que tem menor núumero de cruzamento de arestas é uma estratégia que pode ser aplicada porque, para chegar a um polígono simples, o número de cruzamento de arestas é zero.

Optar por um candidato aleatório) - esta estratégia, ainda que possa atingir um polígono simples, não é uma boa estratégia porque não abstrai nenhuma propriedade do problema e falha muitas vezes pela sua natureza aleatória.


= = = = = = = = = 

Utilizando Simmulated annealing, verifica-se que é mais frequente encontrar um polígono simples uma vez que, ao contrário do Hill Climbing, permite sair dos máximos
locais e encontrar os máximos globais da variação.

A escolha da "temperatura" inicial e do "critério de arrefecimento" são muito importantes. Se a escolha da temperatura inicial for muito grande a probabilidade de mudar para um candidato pior, em grande partes das iterações, é muito próxima de 1, o que pode implicar uma pior solução comparando inicialmente. Por outro lado, se for muito pequena e/ou o critério de arrefecimento for muito rápido a solução quase nunca será ótima. Se o critério de arrefecimento for muito lento pode-se até não encontrar uma solução em tempo útil.


= = = = = = = = = 


Verifica-se experimentalmente que aplicar as heuríssticas "nearest-neighbour first" e Hill Climbing, escolhendo um candidato aleatório, chega a um tipo
de solução (número de interseções) muito semelhante.

Comparando o número de iterações para chegar a uma soluçãoao usando Hill Climbing, observa-se que é muito inferior ao número de iteraçõeses no Simula-ted Annealing, no entanto devido à sua natureza "greedy"pode não dar uma solução ótima ou, no pior dos casos, correta sequer.

___________________________________

# Mais sobre TSP
![image](https://user-images.githubusercontent.com/85238132/120527094-dab7ec80-c3d1-11eb-9686-d425b7dd8087.png)


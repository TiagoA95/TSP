import java.io.*;
import java.util.*;
import java.lang.*;

class Grafo{
  int n;          //numero de vertices
  Ponto[] pontos; //lista de vertices

  // Construtor de grafo com n pontos
  Grafo(int n){
    this.n = n;
    pontos = new Ponto[n+1];
    for(int i = 0; i < n+1; i++)
       pontos[i] = new Ponto();
  }

  @Override
  public String toString(){
    String s = "";
    for (int i = 0; i < n; i++)
      s += pontos[i] + " --> ";
    s += pontos[n];
    return s;
  }

  // Retorna um numero aleatorio entre [min,max]
  int aleatorio(int min, int max){
    min--; // caso contrario daria entre ]min,max]
    return (int)(Math.random() * (max - min + 1) + min);
  }
  
  // Falso se os pontos forem colineares, ou seja, nao servem para o nosso problema
  boolean verificarPontos(){
    for (int i = 0; i < n; i++){
      for (int j = i+1; j < n; j++)
        for (int k = j+1; k < n; k++)
          if (Ponto.colineares(pontos[i],pontos[j],pontos[k]))
            return false;
    }
    return true;
  }
  
  int perimetro(){
    int sum = 0;
    for(int i = 0; i < n; i++)
      sum += pontos[i].distancia(pontos[i+1]);
    return sum;
  }

  // Cria pontos com coordenadas aleatorias no grafo
  void gerapontos(int range){
    do {
      for(int i = 0; i < n; i++){
        Ponto p = new Ponto(aleatorio(-range,range),aleatorio(-range, range));
        //se for repetido nao acrescenta
        for(int j = 0; j < i; j++)
          if(p.x == pontos[i].x && p.y == pontos[i].y){
            i--;
            continue;
          } 
        pontos[i] = p;
      }
      pontos[n] = pontos[0];
    } while (!verificarPontos()); // nao aceita um grafo com 3 ou + pontos colineares
  }
  
  // Cria pontos de um grafo manualmente (para testes)
  void gerapontosmanual(Scanner in){
    boolean wrongInput = false;
    do{
      if (wrongInput)
        System.out.println("Insira pontos não colineares");
      for(int i = 0; i < n; i++){
        Ponto p = new Ponto(in.nextInt(),in.nextInt());
        pontos[i] = p;
      }
      pontos[n] = pontos[0]; //para o array representar um ciclo fechado    
      wrongInput = true;
    } while (!verificarPontos());
  }

  // Gerar permutacoes de um grafo
  public Grafo gerarPermut(){
    Random s = new Random();
    Grafo newGraph = new Grafo(n);
    newGraph.pontos = Arrays.copyOfRange(pontos, 0, n+1);
    for(int i = 0; i < n; i++){
      int x = s.nextInt(n-1);
      troca(newGraph.pontos,x,i);
    }
    newGraph.pontos[n] = newGraph.pontos[0]; //para o array representar um ciclo fechado    
    return newGraph;
  }
  
  // Troca a posicao de dois Pontos no array
  public void troca(Ponto[] arr, int i, int j){
    Ponto temp = arr[i];
    arr[i] = arr[j];
    arr[j] = temp;
  }
  
  // Aplicacao do algoritmo nearest-neighbour first
  public Grafo nnf(){
    Grafo nnf = new Grafo(n);
    nnf.pontos = Arrays.copyOfRange(pontos, 0, n+1); //"clona" o grafo pontos num novo

    //escolher um ponto inicial aleatorio
    Random s = new Random();
    int x = s.nextInt(n-1);
    troca(nnf.pontos,x,0);
    
    //encontrar o vertice mais perto do no anterior a cada iteracao
    for (int i = 1; i < n; i++){
      int min = i;
      int distMin = nnf.pontos[i].distancia(nnf.pontos[i-1]);
      for (int j = i+1; j < n; j++){
        int dist = nnf.pontos[j].distancia(nnf.pontos[i-1]);
        if (dist < distMin){
          distMin = dist;
          min = j;
        }
      }
      troca(nnf.pontos,i,min);
    }
    
    nnf.pontos[n] = nnf.pontos[0]; //para o array representar um ciclo fechado    
    return nnf;
  }

  // Aplicar o 2-exchange a todos os segmentos que se intersetam
  public LinkedList<Grafo> gerarCandidatos(){
    LinkedList<Grafo> list = new LinkedList<>();
    for (int i = 0; i < n-1; i++){
      for (int j = i + 1; j < n; j++){
        if (Ponto.segmentsIntersect(pontos[i],pontos[i+1],pontos[j],pontos[j+1])){
          Grafo temp = new Grafo(n);
          temp.pontos = Arrays.copyOfRange(pontos, 0, n+1);
          troca(temp.pontos,i+1,j);
          list.add(temp);
        }
      }
    }
    return list;
  }

  public int countIntersections(){
    return gerarCandidatos().size(); // numero de intersecoes = tamanho da lista de candidatos
  }

  // Retorna o indice do grafo na lista de candidatos com menor perimetro
  public int menorPerimetro(LinkedList<Grafo> list){
    int i = 0;
    int min = 0;
    int minPerimetro = Integer.MAX_VALUE;
    for (Grafo g : list){
      if (g.perimetro() < minPerimetro){
        minPerimetro = g.perimetro();
        min = i;
      }
      i++;
    }
    return min;
  }
  // Retorna o indice do grafo na lista de candidatos com menor numero de intersecoes
  public int menorNumIntersec(LinkedList<Grafo> list){
    int i = 0;
    int min = 0;
    int minIntersections = Integer.MAX_VALUE;
    for (Grafo g : list){
      if (g.countIntersections() < minIntersections){
        minIntersections = g.countIntersections();
        min = i;
      }
      i++;
    }
    return min;
  }

  /*
  Option 1- candidato que reduzia mais o perimetro
  Option 2- optar pelo primeiro candidato
  Option 3- optar pelo candidato que tiver menos conflitos de arestas
  Option 4- optar por um candidato aleatório
  */
  public Grafo escolherVizinho(LinkedList<Grafo> list, int option){
    if (list.size() == 0) return null;
    switch(option){
      case 1:
        return list.get(menorPerimetro(list));
      case 2:
        return list.getFirst();
      case 3:
        return list.get(menorNumIntersec(list));
      case 4:
        return list.get(aleatorio(0,list.size()-1));
      default:
        return null;
    }
  }

  public Grafo hillclimbing(Grafo g, int flag){
    Grafo atual = g;
    while (atual.countIntersections() > 0){
      
      //System.out.println("Grafo:" + g);
      Grafo neighbour = escolherVizinho(atual.gerarCandidatos(),flag);
      if (neighbour.countIntersections() > atual.countIntersections())
        return atual;
      atual = neighbour;
    }
    return atual;
  }

  public Grafo simulatedAnnealing(Grafo g){
    Grafo atual = g;
    int temp = 25 * atual.countIntersections(); //temperatura
    while(true){
      if (temp == 0) return atual;
      // Descomentar a linha seguinte se quiser ver a temperatura e o grafo a cada iteracao
      //System.out.println("temperatura: " + temp + " Grafo: " +  atual);
      Grafo neighbour = escolherVizinho(atual.gerarCandidatos(),4);
      if (neighbour == null) return atual; //retorna atual se nao houver mais vizinhos
      int delta = neighbour.countIntersections() - atual.countIntersections();
      if ( delta <= 0) { // o vizinho "melhora" o numero de intersecoes
        //System.out.println("Melhorou");
        atual = neighbour;
      }
      else {
        double p = Math.exp((-(double)delta) / temp);
        if (Math.random() < p) {
          atual = neighbour;
          //System.out.println("Probabilidade ocorreu");
        }
      }
      temp *= 0.95;
    }
  }
}
import java.io.*;
import java.util.*;
import java.lang.*;

class Main {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);

    System.out.print("n: ");
    int n = in.nextInt();

    // comentar as linhas 13,14,18, descomentar 17, se quiser gerar manualmente
    System.out.print("m: ");
    int m = in.nextInt();

    Grafo g = new Grafo(n);
    //g.gerapontosmanual(in);
    g.gerapontos(m);

    // 1)
    System.out.println("\nGrafo gerado aleatoriamente: \n" + g);
    // Descomentar se quiser imprimir o numero de intersecoes do candidato inicial
    //System.out.println("Numero de intersecoes do grafo: " + g.countIntersections());
    System.out.println("\n--------------------------------------------------------");

    // 2.a)
    g = g.gerarPermut();
    System.out.println("\nUma das permutações possíveis: \n" + g);
    System.out.println("\n--------------------------------------------------------");

    // 2.b)
    System.out.println("\nAplicando nearest-neighbour first: \n"+ g.nnf());
    System.out.println("\n--------------------------------------------------------");

    // 3)
    /*
    System.out.println("\nDeterminar a vizinhança: ");
    LinkedList<Grafo> list = new LinkedList<>();
    list = g.gerarCandidatos();
    for (Grafo c : list)
      System.out.println(c);
    System.out.println("--------------------------------------------------------");
    */
    
    // 4)
    System.out.println("\nSolução encontrada pelo hillclimbing: ");
    // 4.a)
    System.out.println("\n1- Optar por candidato que reduzia mais o perimetro\n" + g.hillclimbing(g,1));
    // 4.b)
    System.out.println("2- Optar pelo primeiro candidato\n" + g.hillclimbing(g,2));
    // 4.c)
    System.out.println("3- Optar pelo candidato que tiver menos conflitos de arestas\n" + g.hillclimbing(g,3));
    // 4.d)
    System.out.println("4: Optar por um candidato aleatório\n" + g.hillclimbing(g,4));
    System.out.println("\n--------------------------------------------------------");

    // 5)
    System.out.println("\nAplicando simulated annealing: \n" + g.simulatedAnnealing(g));
  }
}
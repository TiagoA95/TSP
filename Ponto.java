import java.io.*;
import java.util.*;
import java.lang.*;

class Ponto{
  int x;
  int y;

  Ponto(){
    this.x = 0;
    this.y = 0;
  }

  Ponto(int x, int y){
    this.x = x;
    this.y = y;
  } 

  @Override
  public String toString(){
    return "("+ x + "," + y + ")";
  }
  
  // Quadrado da distancia entre dois pontos
  int distancia(Ponto a){
    return (x - a.x)*(x - a.x) + (y - a.y)*(y - a.y);
  }
  
  // Verifica se um ponto esta entre os extremos
  static private boolean inBox(Ponto p1, Ponto p2, Ponto p3){
    return ((Math.min(p1.x,p2.x) < p3.x && p3.x < Math.max(p1.x,p2.x) ) &&
           (Math.min(p1.y,p2.y) < p3.y && p3.y < Math.max(p1.y,p2.y) ));
  }
  // Algoritmo para verificar se dois segmentos se intersectam
  static boolean segmentsIntersect(Ponto p1, Ponto p2, Ponto p3, Ponto p4){
    int d1 = (p3.x-p1.x)*(p2.y-p1.y) - (p2.x-p1.x)*(p3.y-p1.y);
    int d2 = (p4.x-p1.x)*(p2.y-p1.y) - (p2.x-p1.x)*(p4.y-p1.y);
    int d3 = (p1.x-p3.x)*(p4.y-p3.y) - (p4.x-p3.x)*(p1.y-p3.y);
    int d4 = (p2.x-p3.x)*(p4.y-p3.y) - (p4.x-p3.x)*(p2.y-p3.y);
    if (d1*d2 < 0 && d3*d4 < 0) return true;
    else if (d1 == 0 && inBox(p1,p2,p3)) return true;
    else if (d2 == 0 && inBox(p1,p2,p4)) return true;
    else if (d3 == 0 && inBox(p3,p4,p1)) return true;
    else if (d4 == 0 && inBox(p3,p4,p2)) return true;
    else return false; 
  }

  // 3 pontos são colineares se area do triangulo definido por eles for 0
  static boolean colineares(Ponto p1, Ponto p2, Ponto p3){
        int area = p1.x * (p2.y - p3.y) + 
                p2.x * (p3.y - p1.y) + 
                p3.x * (p1.y - p2.y);
      
        if (area == 0) return true;
        return false;
    } 
}
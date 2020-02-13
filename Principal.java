/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package semaforo;

// Classe Principal.java

import java.util.concurrent.Semaphore;

// mostra duas threads modificando um objeto compartilhado.

public class Principal {

   // execute application
   public static void main( String args[] )
   {
      ObjetoBuffer umBuffer = new ObjetoBuffer();
      //Semaforo s1 = new Semaforo(1);  // semaforo do produtor
      //Semaforo s2 = new Semaforo(0);  // semaforo do consumidor
       Semaphore s3 = new Semaphore(1);
       Semaphore s4 = new Semaphore(0);

      // criacao das threads
      //Produtor umProdutor = new Produtor(umBuffer,s1,s2);
      //Produtor umProdutor = new Produtor(umBuffer);
      //Consumidor umConsumidor = new Consumidor( umBuffer,s1,s2);
      Consumidor umConsumidor = new Consumidor( umBuffer,s3,s4);
      Produtor umProdutor = new Produtor(umBuffer,s3,s4);

      // start threads
      umProdutor.start();
      umConsumidor.start();
   }

}  // fim da classe Principal

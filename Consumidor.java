/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package semaforo;

// Classe Consumidor.java
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Consumidor extends Thread {

    private ObjetoBuffer um_Buffer;
    //private Semaforo s1, s2;

    private Semaphore s3;
    private Semaphore s4;

    // Construtores do Consumidor thread object
    public Consumidor(ObjetoBuffer dado) {
        super("Consumidor");
        um_Buffer = dado;
    }
    
    public Consumidor (ObjetoBuffer dado, Semaphore s3, Semaphore s4){
        super("Consumidor");
        um_Buffer = dado;
        this.s3 =s3;
        this.s4 = s4;
    }

    /* public Consumidor(ObjetoBuffer dado, Semaforo sem1, Semaforo sem2) {
        super("Consumidor");
        um_Buffer = dado;
        s1 = sem1;
        s2 = sem2;
    }*/
    // Thread Consumidor ler o buffer 10 vezes em intervalos aleatorios
    public void run() {
        int valor, soma = 0;

        do {

            // dorme por um intervalo aleatorio
            try {
                Thread.sleep((int) (Math.random() * 3000));
            } // Tratamento de excecao
            catch (InterruptedException exception) {
                System.err.println(exception.toString());
            }

            try {
                //s2.P();  // bloqueio da thread consumidora
                s4.acquire();
            } catch (InterruptedException ex) {
                Logger.getLogger(Consumidor.class.getName()).log(Level.SEVERE, null, ex);
            }
            valor = um_Buffer.lerBuffer();
            //s1.V();  // liberacao do threads produtora
            s3.release();
            soma += valor;

        } while (valor != 10);

        System.err.println(
                getName() + " terminou de consumir. Totalizou: " + soma);
    }

}

// fim da classe Consumidor

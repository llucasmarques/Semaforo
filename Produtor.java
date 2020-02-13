/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package semaforo;

// classe Produtor.java
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

// Definicao da classe Produtor
public class Produtor extends Thread {

    private ObjetoBuffer o_Buffer;
    //private Semaforo s1, s2;
    private Semaphore s3;
    private Semaphore s4;
    private int sum=0;

    // construtores da Thread Produtor
    public Produtor(ObjetoBuffer dado) {
        super("Produtor");
        o_Buffer = dado;
    }

    public Produtor(ObjetoBuffer dado, Semaphore s3, Semaphore s4) {
        super("Produtor");
        o_Buffer = dado;
        this.s3 = s3;
        this.s4 = s4;
    }

    /*public Produtor(ObjetoBuffer dado, Semaforo sem1, Semaforo sem2) {
        super("Produtor");
        o_Buffer = dado;
        s1 = sem1;
        s2 = sem2;
    }*/
    // Thread do Produtor escreverah 10 vezes no buffer em intervalos de tempo aleatorios 
    public void run() {
        for (int i = 1; i <= 10; i++) {

            // dorme por um tempo aleatorio
            try {
                Thread.sleep((int) (Math.random() * 3000));
            } // tratamento de excecao
            catch (InterruptedException exception) {
                System.err.println(exception.toString());
            }

            try {
                // chama metodo do objeto buffer
                //s1.P();
                s3.acquire();
            } catch (InterruptedException ex) {
                Logger.getLogger(Produtor.class.getName()).log(Level.SEVERE, null, ex);
            }
            o_Buffer.escreveBuffer(i);
            sum+=i;
            s4.release();
            //s2.V();
        }

        System.err.println(getName() + " terminou de produzir e produziu "+sum);
    }

}


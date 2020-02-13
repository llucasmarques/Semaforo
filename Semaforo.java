/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package semaforo;

public final class Semaforo {

    private int valor;	//valor do semaforo
    private int esperando = 0;	//quantidade de processos bloqueados por UM DETERMINADO semaforo

    public Semaforo(int i) { //construtor: inicia o semaforo adequadamente
        valor = i;
    } //fim do construtor

    synchronized void P() { //ou DOWN()
        if (valor > 0) //verifica o valor do semaforo
        {
            valor--; //se semaforo maior que zero, decrementa o valor do semaforo
        } else {
            esperando++;	//senao, incrementa o numero de threads suspensas
            try {
                wait();
            } catch (InterruptedException e) {
            } //senao o processo que executou a operacao P (ou DOWN) e' suspenso
            esperando--;
        }	//fim do else/if
    } //fim do metodo P()

    synchronized void V() { //ou UP()
        if (esperando > 0) //se tem processo na fila de espera
        {
            notify(); //tira processo da fila de espera
        } else {
            valor++;	//senao, incrementa o valor do semaforo
        }
    } //fim do metodo V()

} //fim da classe Semaforo


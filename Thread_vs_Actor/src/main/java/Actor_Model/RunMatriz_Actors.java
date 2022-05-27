package Actor_Model;

import Actor_Model.Object_Auxiliary_Class.MatrizA;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;


public class RunMatriz_Actors  {

    public static ActorRef mmatrices;

    static int[][] m1 = { //Primera matriz
            {2,0,1},
            {3,0,0},
            {5,1,1}
    };
    static int[][] m2 = { //Segunda matriz
            {1,0,1},
            {1,2,1},
            {1,1,0}
    };

    static ActorSystem system = ActorSystem.create("ActorSystem"); // Una configuración general que compartiran todos los actores

    public static void main(String[] args) throws InterruptedException {

        MatrizA matrices = new MatrizA(m1,m2);

        ActorRef multmatrices = system.actorOf(MultiplMatrices.props(),"multmatrices"); // Se crea la referencia de MultiplMatrices
        multmatrices.tell(matrices, ActorRef.noSender()); // Se envia un mensaje a MultiplMatrices

        Thread.sleep(2000); // Se asigan aun tiempo de espera para que culminen todos los actores
        system.stop(multmatrices); // Se detiene al ActorRef
        system.terminate(); // Se termina el ActorSystem para que no se siga ejecutando el programa

    }

}

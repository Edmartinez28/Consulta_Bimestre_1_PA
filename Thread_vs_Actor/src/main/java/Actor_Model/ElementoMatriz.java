package Actor_Model;


import Actor_Model.Object_Auxiliary_Class.ElementDetails;
import Actor_Model.Object_Auxiliary_Class.ReturnElement;
import akka.actor.AbstractActor;
import akka.actor.Props;


public class ElementoMatriz extends AbstractActor {

    public static Props props(){ // Se crea el props que permitira crear un ActorRef con este tipo de clase
        return Props.create(ElementoMatriz.class);
    }

    @Override
    public Receive createReceive() { // Metodo que actua como buzon del Actor
        return receiveBuilder().create()
                .match(ElementDetails.class , this::calcElement) //Mensaje recibido que servira para calcular el elemento y saber su posicion.
                .build();
    }

    public void calcElement(ElementDetails x){ // Metodo que calcula el valor del elemento de la matriz
        int suma = 0;

        for(var i=0 ; i<x.m1.length ;i++){
            suma += x.m1[x.rowindex][i] * x.m2[i][x.colindex];
        }

        getSender().tell(new ReturnElement(x.rowindex,x.colindex,suma),getSelf()); //Envia un mensaje al actor MultiplMatrices para colocarlo en la matriz final
    }
}

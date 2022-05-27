package Actor_Model;

import Actor_Model.Object_Auxiliary_Class.ElementDetails;
import Actor_Model.Object_Auxiliary_Class.MatrizA;
import Actor_Model.Object_Auxiliary_Class.ReturnElement;
import akka.actor.*;

public class MultiplMatrices extends AbstractActor {

    ActorSystem system = ActorSystem.create("system"); // Una configuración general que compartiran todos los actores



    private MatrizA matrices;
    private int[][] mresultante;

    public static Props props() { // Se crea el props que permitira crear un ActorRef con este tipo de clase
        return Props.create(MultiplMatrices.class);
    }

    @Override
    public Receive createReceive() { // Metodo que actua como buzon del Actor
        return receiveBuilder().create()
                .match(MatrizA.class,this::setMatrices)//Mensaje recibido que servira para ingresar las matrices
                .match(ReturnElement.class,this::setElements)//Mensaje recibido que servira para ingresar los elementos calculados en la posicion correcta
                .build();
    }

    public void setMatrices(MatrizA matrices) throws InterruptedException {
        this.matrices = matrices;
        if(this.matrices.m1.length == this.matrices.m2[0].length){

            mresultante = new int[this.matrices.m1.length][this.matrices.m2[0].length]; // Se le asigna el tamaño a la matriz resultante

            ActorRef element = system.actorOf(Props.create(ElementoMatriz.class), "elemento"); // Se crea un Actor del tipo ElementoMatriz

            for(var i=0 ; i<mresultante.length ; i++){
                for(var j=0 ; j<mresultante[0].length ; j++){
                    element.tell(new ElementDetails(this.matrices.m1,this.matrices.m2,i,j),getSelf()); // Se manda uun mensaje al actor para que calcule el elemento
                    system.stop(element);
                }
            }
            system.terminate(); // Se termina el ActorSystem para que no se siga ejecutando el programa

        } else {
            System.out.println("No se puede realizar la multiplicacion de estas matrices");
        }
    }

    public void setElements(ReturnElement e){

        mresultante[e.rowIndex][e.colIndex] = e.value; //Se coloca el elemento calculado en la posicion de la matriz final

        // Verifica que ya se hayan colocado todos los elementos en la matriz final y cuando ya lo estan la imprime en pantalla
        if(e.rowIndex == mresultante.length-1 && e.colIndex == mresultante[0].length-1){
            System.out.println("El resultado de la multiplicación de matrices es:");
            for(var i=0 ; i<mresultante.length ; i++){
                System.out.println("+----+----+----+");
                for(var j=0 ; j<mresultante[0].length ; j++){
                    System.out.printf("| %2d ",mresultante[i][j]);
                }
                System.out.println("|");
            }
            System.out.println("+----+----+----+");
        }
    }

}

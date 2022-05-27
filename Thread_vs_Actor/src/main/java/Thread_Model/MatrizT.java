package Thread_Model;

import java.util.ArrayList;
import java.util.List;

public class MatrizT {
    int[][] values;

    public MatrizT(int[][] matriz) {
        this.values = matriz;
    }

    public MatrizT multiplyWithThreads (MatrizT mat2) throws InterruptedException {
        List<TaskCalcElement> threads; //Se crea una lista de Threads para sincronizarlos

        if(values.length == mat2.values[0].length){
            int[][] output = new int[values.length][mat2.values[0].length];//Se asigana el tamaño de la matriz final
            threads = new ArrayList<>();

            for(var i=0 ; i< output.length ; i++){
                for(var j=0 ; j< output[0].length ; j++){
                    TaskCalcElement thread = new TaskCalcElement(this , mat2 , i , j); // Creamos un nuevo hilo para que calcule el valor del elemento
                    thread.start();//Inizializamos el hilo
                    threads.add(thread);
                }
            }

            setWaitThreads(threads);

            for(var t : threads){
                output[t.getRowIndex()][t.getColIndex()] = t.getElement(); //Colocamos los elementos calculados por los hilos en la matriz final
            }

            return new MatrizT(output);

        }else{
            throw new IllegalArgumentException("No se puede multiplicar");
        }
    }

    //Metodo que se utiliza para sincronizar los hilos con join()
    public static void setWaitThreads(List<TaskCalcElement> threads) throws InterruptedException {
        for(var thread:threads){
            thread.join();
        }
    }
}

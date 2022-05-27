package Thread_Model;

public class RunTaskCalc {
    int[][] values;
    public static void main(String[] args) throws InterruptedException {
        int[][] m1 = { //Primera matriz
                {2,0,1},
                {3,0,0},
                {5,1,1}
        };
        int[][] m2 = { //Segunda matriz
                {1,0,1},
                {1,2,1},
                {1,1,0}
        };

        MatrizT matriz1 = new MatrizT(m1); // Se asigna los arrglos a la clase matriz
        MatrizT matriz2 = new MatrizT(m2);

        MatrizT resultado = matriz1.multiplyWithThreads(matriz2); //Matriz final con los elementos calculados

        // Imprimo la matriz resultante de la multiplicación ---------------------------------------------------

        System.out.println("El resultado de la multiplicación de matrices es:");
        for(var i=0 ; i<resultado.values.length ; i++){
            System.out.println("+----+----+----+");
            for(var j=0 ; j<resultado.values[0].length ; j++){
                System.out.printf("| %2d ",resultado.values[i][j]);
            }
            System.out.println("|");
        }
        System.out.println("+----+----+----+");
    }


}

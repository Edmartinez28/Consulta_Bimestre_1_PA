package Thread_Model;

public class TaskCalcElement extends Thread {
    private MatrizT m1;
    private MatrizT m2;
    private int rowIndex;
    private int colIndex;
    private int element;

    public TaskCalcElement(MatrizT m1, MatrizT m2, int rowIndex, int colIndex) {
        this.m1 = m1;
        this.m2 = m2;
        this.rowIndex = rowIndex;
        this.colIndex = colIndex;
    }

    // Se ejecuta
    @Override
    public void run() {
        int suma = 0;

        for(var i=0 ; i<m1.values.length ;i++){
            suma += m1.values[rowIndex][i] * m2.values[i][colIndex];
        }

        //Calculamos el elemento y lo guardamos en una variable propia de la clase
        element = suma;
    }

    // Getters de los parametros que se necesitan consultar
    public int getRowIndex() {
        return rowIndex;
    }

    public int getColIndex() {
        return colIndex;
    }

    public int getElement() {
        return element;
    }
}

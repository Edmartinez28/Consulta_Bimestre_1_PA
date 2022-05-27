package Actor_Model.Object_Auxiliary_Class;

public class ElementDetails { //Clase creada para enviar mensajes entre actores
    public int[][] m1;
    public int[][] m2;
    public int rowindex;
    public int colindex;

    public ElementDetails(int[][] m1, int[][] m2, int rowindex, int colindex) {
        this.m1 = m1;
        this.m2 = m2;
        this.rowindex = rowindex;
        this.colindex = colindex;
    }
}

package Actor_Model.Object_Auxiliary_Class;

public class ReturnElement {//Clase creada para enviar mensajes entre actores
    public int rowIndex;
    public int colIndex;
    public int value;

    public ReturnElement(int rowIndex, int colIndex, int value) {
        this.rowIndex = rowIndex;
        this.colIndex = colIndex;
        this.value = value;
    }
}

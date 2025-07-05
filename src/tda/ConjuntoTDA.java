package tda;

public interface ConjuntoTDA {

    public void inicializar();
    public void sacar(int valor); //saca un valor determinado
    public int elegir(); //saca valores random
    public boolean estaVacia();
    public void agregar(int valor);
    public boolean pertenece(int valor);

}

package tda;

public interface DiccionarioMultipleTDA {

    public void inicializar();
    public void agregar(int clave, int valor);
    public void eliminar(int clave);
    public ConjuntoTDA recuperar(int clave);
    public ConjuntoTDA clave();
    public void eliminarValor(int clave, int valor);
    public boolean estaVacio();

}


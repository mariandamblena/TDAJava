package tda;

public interface DiccionarioSimpleTDA {

    public void inicializar();
    public void agregar(int clave, int valor);
    public void eliminar(int clave);
    public int recuperar(int clave);
    public ConjuntoTDA obtenerClave();
}

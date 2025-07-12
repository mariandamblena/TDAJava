package tda;

public interface ConjuntoRangoTDA {

    void inicializarConjunto(int limiteInferior, int limiteSuperior); // inicializa el conjunto en el rango dado
    void agregar(int x); // agrega un numeor al conjunto correspondiente (dentro o fuera del rango)
    ConjuntoTDA obtenerEnRango(); //Devuelve el conjunto de numeros dentro del rango
    ConjuntoTDA obtenerFueraDeRango(); // Devuelve el conjunto de numeros fuera del rango


}

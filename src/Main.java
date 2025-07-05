import implementacion.ConjuntoEstatico;
import tda.ConjuntoTDA;

public class Main {
    public static void main(String[] args) {
        ConjuntoTDA original = new ConjuntoEstatico();
        original.agregar(13);
        original.agregar(4);
        original.agregar(6);
        original.agregar(17);
        original.agregar(25);

        ConjuntoTDA resultado = generarConjuntoDeSumas(original);

        // Mostrar resultado
        System.out.println("Conjunto resultado:");
        imprimir(resultado);

        // Verificar que el original quedó igual
        System.out.println("\nConjunto original después del proceso:");
        imprimir(original);
    }

    /** Genera el conjunto de sumas sin alterar el conjunto de entrada. */
    private static ConjuntoTDA generarConjuntoDeSumas(ConjuntoTDA origen){
        ConjuntoTDA aux       = new ConjuntoEstatico(); // para restaurar el original
        ConjuntoTDA resultado = new ConjuntoEstatico();

        while(!origen.estaVacia()){
            int suma   = 0;
            int ultimo = Integer.MIN_VALUE;  // flag que indica “no hay último”

            boolean continuar = true;
            while(continuar && !origen.estaVacia()){
                int v = origen.elegir();          // elegir NO saca
                if (ultimo == Integer.MIN_VALUE || v > ultimo){
                    // Forma parte de la sumatoria
                    origen.sacar(v);
                    aux.agregar(v);               // Copia para restaurar después
                    suma   += v;
                    ultimo  = v;
                } else {
                    // Encontramos un valor menor → fin de este bloque
                    continuar = false;
                }
            }
            resultado.agregar(suma);
        }

        // Restaurar el conjunto original con lo que quedó en aux
        while(!aux.estaVacia()){
            int v = aux.elegir();
            origen.agregar(v);
            aux.sacar(v);
        }

        return resultado;
    }

    /** Utilidad para imprimir un ConjuntoTDA sin vaciarlo permanentemente */
    private static void imprimir(ConjuntoTDA c){
        ConjuntoTDA tmp = new ConjuntoEstatico();
        while(!c.estaVacia()){
            int v = c.elegir();
            System.out.print(v + " ");
            c.sacar(v);
            tmp.agregar(v);        // Guardar para reinsertar
        }
        while(!tmp.estaVacia()){   // Devolver los elementos
            int v = tmp.elegir();
            c.agregar(v);
            tmp.sacar(v);
        }
    }
}

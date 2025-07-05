package test;

import implementacion.ConjuntoEstatico;
import implementacion.DiccionarioSimpleEstatico;
import tda.ConjuntoTDA;
import tda.DiccionarioSimpleTDA;

public class SegundaTareaDiccionariosSimples {

    public static void main(String[] args) {
        DiccionarioSimpleTDA d1 = new DiccionarioSimpleEstatico();
        DiccionarioSimpleTDA d2 = new DiccionarioSimpleEstatico();

        d1.inicializar();

        // Acá vas a ir agregando claves/valores de prueba, por ejemplo:
        d1.agregar(1, 23);
        d1.agregar(3, 56);
        d1.agregar(4, 564);

        // Y luego desarrollás la lógica para llenar d2 con los que cumplen la condición

        ConjuntoTDA claves = new ConjuntoEstatico();
        claves.inicializar();
        claves = d1.obtenerClave();

        int[] clavesArray = new int[100]; // asumimos un máximo de 100 claves
        int n = 0;

        while (!claves.estaVacia()) {
            int clave = claves.elegir();
            claves.sacar(clave);
            clavesArray[n++] = clave;
        }

        for (int i = 0; i < n; i++) {
            int clave1 = clavesArray[i];
            int valor1 = d1.recuperar(clave1);

            for (int j = 0; j < n; j++) {
                if (i == j) continue; // no comparar el mismo

                int clave2 = clavesArray[j];
                int valor2 = d1.recuperar(clave2);

                // Convertimos a string y comparamos
                if (String.valueOf(valor2).contains(String.valueOf(valor1))) {
                    // Si el valor1 está dentro de valor2, agregamos ambos al resultado
                    d2.agregar(clave1, valor1);
                    d2.agregar(clave2, valor2);
                }
            }
        }

    }
}

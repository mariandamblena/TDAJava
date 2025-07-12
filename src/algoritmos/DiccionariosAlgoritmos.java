package algoritmos;

import implementacion.ConjuntoDinamico;
import implementacion.DiccionarioMultipleDinamico;
import tda.ConjuntoTDA;
import tda.DiccionarioMultipleTDA;

public class DiccionariosAlgoritmos {

    /**
     * Devuelve un diccionario con la intersección de a y b:
     * para cada clave común guarda solo los valores compartidos.
     */
    public DiccionarioMultipleTDA interseccionDiccionarios(DiccionarioMultipleTDA a, DiccionarioMultipleTDA b) {
        // 1) Sacar conjuntos de claves sin mutar los originales
        ConjuntoTDA clavesA = a.clave();
        ConjuntoTDA clavesB = b.clave();
        ConjuntoTDA comunes = interseccionConjuntos(clavesA, clavesB);

        // 2) Crear diccionario resultado
        DiccionarioMultipleTDA resultado = new DiccionarioMultipleDinamico();
        resultado.inicializar();

        // 3) Para cada clave común, intersectar sus valores
        while (!comunes.estaVacia()) {
            int clave = comunes.elegir();
            comunes.sacar(clave);

            ConjuntoTDA valsA = a.recuperar(clave);
            ConjuntoTDA valsB = b.recuperar(clave);
            ConjuntoTDA inter = interseccionConjuntos(valsA, valsB);

            while (!inter.estaVacia()) {
                int v = inter.elegir();
                inter.sacar(v);
                resultado.agregar(clave, v);
            }
        }
        return resultado;
    }

    /**
     * Intersección de dos ConjuntoTDA sin mutar ninguno de los dos.
     */
    private ConjuntoTDA interseccionConjuntos(ConjuntoTDA a, ConjuntoTDA b) {
        // Copiar 'a' en copiaA y restaurar 'a'
        ConjuntoTDA copiaA = new ConjuntoDinamico();
        copiaA.inicializar();
        ConjuntoTDA aux = new ConjuntoDinamico();
        aux.inicializar();

        while (!a.estaVacia()) {
            int x = a.elegir();
            a.sacar(x);
            copiaA.agregar(x);
            aux.agregar(x);
        }
        while (!aux.estaVacia()) {
            int x = aux.elegir();
            aux.sacar(x);
            a.agregar(x);
        }

        // Construir la intersección real
        ConjuntoTDA res = new ConjuntoDinamico(); res.inicializar();
        while (!copiaA.estaVacia()) {
            int x = copiaA.elegir();
            copiaA.sacar(x);
            if (b.pertenece(x)) {
                res.agregar(x);
            }
        }
        return res;
    }
}

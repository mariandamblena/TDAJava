package implementacion;

import tda.ConjuntoTDA;
import tda.DiccionarioMultipleTDA;

public class DiccionarioMultipleEstatico implements DiccionarioMultipleTDA {

    class Elemento{
        int clave;
        int[] valores;
        int cantidadValores;
    }

    Elemento[] elementos;
    int cantClaves;


    @Override
    public void inicializar() {
        elementos = new Elemento[100];
        cantClaves = 0;
    }

    @Override
    public void agregar(int clave, int valor) {
        int posC = Clave2Indice(clave);
        if (posC==-1) {
            posC = cantClaves;
            elementos[posC]= new Elemento();
            elementos[posC].clave = clave;
            elementos[posC].cantidadValores = 0;
            elementos[posC].valores = new int[100];
            cantClaves++;
        }
        Elemento e = elementos[posC];
        int posV = Valor2Indice(e, valor);
        if (posV==-1) {
            e.valores[e.cantidadValores] = valor;
            e.cantidadValores++;
        }
    }

    private int Clave2Indice(int clave){
        int i = cantClaves-1;
        while(i>=0 && elementos[i].clave!=clave)
            i--;
        return i;
    }

    @Override
    public void eliminar(int clave) {
        int pos = Clave2Indice(clave);
        if (pos!=-1) {
            elementos[pos] = elementos[cantClaves-1];
            cantClaves--;
        }
    }

    @Override
    public ConjuntoTDA recuperar(int clave) {
        ConjuntoTDA c=new ConjuntoDinamico();
        c.inicializar();
        int pos = Clave2Indice(clave);
        if (pos!=-1) {
            Elemento e= elementos[pos];
            for(int i=0; i<e.cantidadValores; i++){
                c.agregar(e.valores[i]);
            }
        }
        return c;
    }

    @Override
    public ConjuntoTDA clave() {
        ConjuntoTDA c=new ConjuntoDinamico();
        c.inicializar();
        for(int i=0; i<cantClaves; i++){
            c.agregar(elementos[i].clave);
        }
        return c;
    }

    @Override
    public void eliminarValor(int clave, int valor) {
        int posC = Clave2Indice(clave);
        if (posC!=-1) {
            Elemento e = elementos[posC];
            int posV = Valor2Indice(e, valor);
            if (posV!=-1) {
                e.valores[posV] = e.valores[e.cantidadValores-1];
                e.cantidadValores--;
                if (e.cantidadValores==0) {
                    eliminar(clave);
                }
            }
        }
    }

    @Override
    public boolean estaVacio() {
        return false;
    }

    private int Valor2Indice(Elemento e, int valor) {
        int i = e.cantidadValores-1;
        while(i>=0 && e.valores[i]!=valor)
            i--;
        return i;
    }
}

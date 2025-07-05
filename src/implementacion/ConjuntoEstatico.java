package implementacion;

import tda.ConjuntoTDA;

import java.util.Random;

public class ConjuntoEstatico implements ConjuntoTDA {

    private int[] valores;
    private int cantidad;
    private Random r;

    @Override
    public void inicializar() {
        cantidad = 0;
        valores = new int[100];
    }

    @Override
    public void sacar(int valor) {
        boolean encontrado = false;
        for(int i=0; i<cantidad && !encontrado;i++){
            if(valores[i] == valor ){
                valores[i] = valores[cantidad-1];
                cantidad--;
                encontrado = true;
            }
        }
    }

    @Override
    public int elegir() {
        r = new Random();
        int pos = r.nextInt(cantidad);
        return valores[pos];
    }

    @Override
    public boolean estaVacia() {
        return cantidad == 0;
    }

    @Override
    public void agregar(int valor) {
        if(!pertenece(valor)){
            valores[cantidad] = valor;
            cantidad++;
        }
    }

    @Override
    public boolean pertenece(int valor) {
        boolean encontrado = false;
        for(int i=0; i<cantidad && !encontrado;i++){
            if(valores[i] == valor ){
                encontrado = true;
            }
        }
        return encontrado;
    }}

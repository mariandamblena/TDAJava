package implementacion;

import tda.ColaTDA;

public class ColaEstatica implements ColaTDA {

    private int[] valores;
    private int cantidad;

    @Override
    public void inicializar() {
        valores = new int[100];
        cantidad = 0;
    }

    @Override
    public void acolar(int valor) {
        valores[cantidad] = valor;
        cantidad++;
    }

    @Override
    public void desacolar() {
        if (!estaVacia()){
            for(int i=0; i<cantidad;i++){
                valores[i] = valores[i+1];
            }
            cantidad--;
        }
    }

    @Override
    public boolean estaVacia() {
        return cantidad == 0;
    }

    @Override
    public int primero() {
        return valores[0];
    }
}

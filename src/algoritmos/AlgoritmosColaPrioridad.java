package algoritmos;

import implementacion.ColaPrioridadDinamica;
import tda.ColaPrioridadTDA;

public class AlgoritmosColaPrioridad {

    public static ColaPrioridadTDA unirColas(ColaPrioridadTDA pq1, ColaPrioridadTDA pq2){

        ColaPrioridadTDA pq = new ColaPrioridadDinamica();
        pq.InicializarCola();

        while(!pq1.ColaVacia()){
            pq.AcolarPrioridad( pq1.Primero(),pq1.Prioridad());
            pq1.Desacolar();
        }

        while(!pq2.ColaVacia()){
            pq.AcolarPrioridad( pq2.Primero(),pq2.Prioridad());
            pq2.Desacolar();
        }
        return pq;
    }

}

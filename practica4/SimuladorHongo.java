import fundamentos.*;

/**
 * Programa que simula el crecimiento de hongos en un alimento. 
 * Dibuja una grafica del crecimiento segun el tiempo
 * 
 * @author (Michael)) 
 * @version (mar 2016)
 */
public class SimuladorHongo
{
    /**
     * Programa principal que crea el sistema para el hongo Rhizopus Orizae
     * y dibuja la grafica
     */
    public static void main(String[] args) {
        // Se crea el sistema  Rhizopus Orizae con estos parametros:
        // y0=0: diámetro inicial de las colonias (mm)
        // yMax=3.5: diámetro máximo de las colonias (mm)
        // velMax=0.36: velocidad de crecimiento (mm/h)
        // m=0.06: parámetro de curvatura de transición de la fase 
        //   exponencial (1/mm)
        // v=10/24: parámetro de curvatura de transición a la fase 
        //   exponencial (1/h)
        // h0=2.757: estado fisiológico inicial de las células (sin dimensiones)
        // condiciones: T=20C, pH=3, aW=0.895
        RhizopusOryzae sist=new RhizopusOryzae
          (0.0,3.5,0.36,0.06,10.0/24.0,2.757,
           "T=20C, pH=3, aW=0.895");

       Grafica g= new Grafica
          ("Crecimiento del hongo RhizopusOryzae para"+
           sist.getCondiciones(),"t(h)","diam.(mm)");

        g.ponColor(Grafica.rojo);

        //bucle para incrementar el tiempo entre 0 y 200 h, con 1h de incremento
        for (double t=0.0; t<=200; t+=1.0) {
            // se simula el sistema
            g.inserta(t,sist.diametro(t));
        }
        // dibujar la grafica
        g.pinta();
    }
}
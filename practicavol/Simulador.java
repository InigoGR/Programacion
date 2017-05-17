
/**
 * Programa que simula el funcionamiento de un satelite artificial
 * que orbita alrededor de la tierra
 * 
 * @author (Michael) 
 * @version (8 marzo 2012)
 */
public class Simulador
{
    // Espera un rato 
    private static void esperaUnRato() {
        try {
            // espera 50 milisegundos
            Thread.sleep(50); 
        } catch (InterruptedException e) {
            // ignorar
        }
    }

    /**
     * Programa principal que hace la simulacion
     */
    public static void main(String[] args) {
        // Creamos un satelite con velocidad inicial=4000 m/s
        // en direccion horizontal y ac=20m/s^2 y otro con 
        //velocidad inicial=4200 m/s en dirección horizontal
        //y misma ac
        Satelite sat1 = new Satelite(4000.0,0.0,20.0);
        Satelite sat2 = new Satelite(4200.0,0.0,20.0);
        // Creamos la ventana para dibujar
        Espacio espacio=new Espacio();

        // variable que cuenta el numero de veces que se enciende el cohete
        int veces1=0;
        int veces2=0;
        // inciamos un bucle que se repite un millón de veces
        int contador=0;
        while (contador<1e6) {
            // cada 50 veces pintamos los satélites y esperamos un rato
            if (contador%50==0) {
                espacio.pintaSatelite(sat1.posX(),sat1.posY(),Espacio.ROJO);
                espacio.pintaSatelite(sat2.posX(),sat2.posY(),Espacio.AZUL);
                esperaUnRato();
            }

            // almacenamos la posición de los satélites antes de moverse
            double posAnterior1=sat1.posX();
            double posAnterior2=sat2.posX();
            // avanzamos 1 segundo (simuladamente)
            sat1.avanzaTiempo(1.0);
            sat2.avanzaTiempo(1.0);
            // Si hemos completado una Orbita se enciende el cohete, 
            // hasta dos veces
            // Detectamos cuando posX del sat1 pasa de negativo a positivo
            if (posAnterior1<0.0 && sat1.posX()>=0 && veces1<=1) {
               sat1.enciende();
               veces1++;
            }
            // Detectamos cuando posX del sat2 pasa de positivo a negativo
             if (posAnterior2>0.0 && sat2.posX()<=0 && veces2<=1) {
               sat2.enciende();
               veces2++;
            }
            // si hemos avanzado 200 km horizontalmente se apaga el cohete
            if (posAnterior1<2.0e5 && sat1.posX()>=2.0e5) {
               sat1.apaga();
            }
             if (posAnterior2>-2.0e5 && sat2.posX()<=-2.0e5) {
               sat2.apaga();
            }
            // cada 100000 veces borramos las trayectorias 
            // para limpiar la pantalla
            if (contador%1e5==0) {
                espacio.borra();
            }
            contador++;
        }

    }
}

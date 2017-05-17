
/**
 * Programa que simula el funcionamiento de un satelite artificial
 * que orbita alrededor de la tierra
 * 
 * @author (Michael) 
 * @version (8 marzo 2012)
 */
public class Simuladorstd
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
        // Creamos el primer satelite con velocidad inicial=4000 m/s
        // en direccion horizontal y ac=20m/s^2
        Satelite sat1 = new Satelite(4000.0,0.0,20.0);
        // Creamos la ventana para dibujar
        Espacio espacio=new Espacio();

        // variable que cuenta el numero de veces que se enciende el cohete
        int veces=0;
        
        // inciamos un bucle que se repite un millón de veces
        int contador=0;
        while (contador<1e6) {
            // cada 50 veces pintamos el satelite y esperamos un rato
            if (contador%50==0) {
                espacio.pintaSatelite(sat1.posX(),sat1.posY(),Espacio.ROJO);
                esperaUnRato();
            }

            // almacenamos la posición del satélite antes de moverse
            double posAnterior=sat1.posX();
            // avanzamos 1 segundo (simuladamente)
            sat1.avanzaTiempo(1.0); 
            // Si hemos completado una Orbita se enciende el cohete, 
            // hasta dos veces
            // Detectamos cuando posX pasa de negativo a positivo
            if (posAnterior<0.0 && sat1.posX()>=0 && veces<=1) {
               sat1.enciende();
               veces++;
            }
            
            // si hemos avanzado 200 km horizontalmente se apaga el cohete
            if (posAnterior<2.0e5 && sat1.posX()>=2.0e5) {
               sat1.apaga();
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

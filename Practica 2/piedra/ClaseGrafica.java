import fundamentos.*;
/**
 * La clase Grafica crea una grafica de la trayectoria de una piedra.
 * 
 * @author Iñigo González) 
 * @version 01/3/16
 */

public class ClaseGrafica
{
    //Constructor de la clase ClaseGrafica
    public static void main(String nombre,double posX, double posY,
    double velX, double velY)
    /**El metodo crea un objeto de la clase Grafica y otro de la clase Piedra.
     * Utiliza un bucle para calcular 10 puntos de la trayectoria y
     * representarlos en la grafica
     */
    {
        Grafica graph=new Grafica(nombre,"x","y");
        Piedra NuevaPiedra= new Piedra(posX,posY,velX,velY);
        graph.inserta(NuevaPiedra.getPosX(),NuevaPiedra.getPosY());
        int n;
        for(n=0;n<10;n=n+1)
        {
            graph.inserta(NuevaPiedra.getPosX(),NuevaPiedra.getPosY());
            NuevaPiedra.avanzaTiempo(0.5); 
        }    
        graph.pinta();
    }
}

import fundamentos.*;

/**
 * Esta clase contiene una ventana donde se dibuja la tierra y uno
 * o dos satelites que orbitan alrededor
 * 
 * @author (Michael) 
 * @version (20 marzo 2014)
 */
public class Espacio
{
    // colores de las orbitas
    public static final int ROJO=1;
    public static final int AZUL=2;

    // ventana para hacer el dibujo
    private Dibujo dib;

    // limites de las coordenadas X e Y, en pixeles
    private int limiteX=400;
    private int limiteY=400;

    // factor de escala entre los metros y los pixeles
    private double escala=100000; // metros por pixel

    /**
     * Constructor que crea la ventana y pinta la tierra
     */
    public Espacio() {
        dib=new Dibujo("Simulacion de satelites", 800,800);
        dib.dibujaImagen(limiteX-60,limiteY-60,"tierra.png");
        dib.pinta();
    }

    /**
     * Pinta el satelite cuyo color se indica (ROJO o AZUL) en la ventana,
     * con las coordenadas indicadas por x e y, en metros
     */
    public void pintaSatelite(double x, double y, int numSatelite){
        dib.ponGrosorLapiz(3);
        if (numSatelite==ROJO) {
            dib.ponColorLapiz(ColorFig.rojo);
        } else {
            // AZUL
            dib.ponColorLapiz(ColorFig.azul);
        }
        dib.dibujaPunto((int)(x/escala)+limiteX, limiteY-(int)(y/100000));
        dib.pinta();
    }

    /**
     * Borra las trayectorias de los satelites del dibujo
     */
    public void borra() {
        dib.borra();
        dib.dibujaImagen(limiteX-60,limiteY-60,"tierra.png");
        dib.pinta();        
    }
}

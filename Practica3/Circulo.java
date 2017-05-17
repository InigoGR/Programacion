import fundamentos.Dibujo;
import fundamentos.ColorFig;

/**
 * Clase que representa un circulo que se pinta en una ventana de la clase Dibujo
 */
public class Circulo extends Figura
{
    // coordenadas del cuadrado
    private int x,y,r;

    /**
     * Constructor al que se le pasan las coordenadas del centro y el radio, 
     * en pixels
     */
    public Circulo (int x, int y, int r)
    {
        this.x=x;
        this.y=y;
        this.r=r;
        Figura.lista.anadeFigura(this);
        Figura.lista.pinta();
    }

    /**
     * Pinta el circulo
     */
    protected void pinta(Dibujo dib)
    {
        dib.ponColorLapiz(ColorFig.rojo);
        dib.dibujaElipse(x-r,y-r,x+r,y+r);
        dib.ponColorLapiz(ColorFig.negro);
    }
    
    /**
     * Mueve el rectangulo en la cantidad relativa indicada en Pixels
     */
    public  void mueve(int deltaX, int deltaY) {
        x=x+deltaX;
        y=y+deltaY;
        Figura.lista.pinta();
    }

}

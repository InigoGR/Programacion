import fundamentos.*;

/**
 * Clase que representa un cuadrado que se pinta en una ventana de la clase Dibujo
 */
public class Linea extends Figura
{
    // coordenadas del cuadrado
    private int x1,y1,x2,y2;
    static private int x;
    

    /**
     * Constructor al que se le pasan los extremos de la linea en pixels
     */
    public Linea (int x1, int y1, int x2, int y2)
    {
        this.x1=x1;
        this.x2=x2;
        this.y1=y1;
        this.y2=y2;
        Figura.lista.anadeFigura(this);
        Figura.lista.pinta();
    }

    /**
     * Pinta la linea
     */
    protected void pinta(Dibujo dib)
    {
        dib.dibujaLinea(x1,y1,x2,y2);
    }
    
    /**
     * Mueve la linea en la cantidad relativa indicada en Pixels
     */
    public  void mueve(int deltaX, int deltaY) {
        x1=x1+deltaX;
        y1=y1+deltaY;
        x2=x2+deltaX;
        y2=y2+deltaY;
        Figura.lista.pinta();
    }

}

import fundamentos.Dibujo;

/**
 * Clase que representa un rectangulo que se pinta en una ventana de la clase Dibujo
 */
public class Rectangulo extends Figura
{
    // coordenadas del cuadrado
    private int x1,y1,x2,y2;

    /**
     * Constructor al que se le pasan las esquinas superior izquierda e inferior derecha
     * en pixels
     */
    public Rectangulo (int x1, int y1, int x2, int y2)
    {
        this.x1=x1;
        this.x2=x2;
        this.y1=y1;
        this.y2=y2;
        Figura.lista.anadeFigura(this);
        Figura.lista.pinta();
    }

    /**
     * Pinta el rectangulo
     */
    protected void pinta(Dibujo dib)
    {
        dib.dibujaRectangulo(x1,y1,x2,y2);
    }
    
    /**
     * Mueve el rectangulo en la cantidad relativa indicada en Pixels
     */
    public  void mueve(int deltaX, int deltaY) {
        x1=x1+deltaX;
        y1=y1+deltaY;
        x2=x2+deltaX;
        y2=y2+deltaY;
        Figura.lista.pinta();
    }

}

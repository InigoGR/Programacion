
/**
 * Write a description of class Piedra here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Piedra
{
    //atributos que definen el movimiento
    private double posX, posY; //posición, metros
    private double velX, velY; //velocidad, m/s
    
    //constante que define la gravedad
    public final double g=9.8; //m/s^2
    
    /**
     * Constructor que pone las condiciones iniciales, copiándolas de los argumentos. Unidades: m y m/s
     */
    public Piedra
        (double posX, double posY,
         double velX, double velY)
    {
        this.posX=posX;
        this.posY=posY;
        this.velX=velX;
        this.velY=velY;
    }
    
    /**
     * Retorna la posición X (m)
     */
    public double getPosX() {
        return posX;
    }
    /**
     * Retorna la posición Y (m)
     */
    public double getPosY() {
        return posY;
    }
    
    /**
     * Avanza el tiempo en la cantidad t(s), actualiza las posiciones y las velocidades
     */
    public void avanzaTiempo(double t)
    {
        posX=posX+velX*t;
        posY=posY+velY*t-g*t*t/2.0;
        //velx no cambia
        velY=velY-g*t;
    }
}    

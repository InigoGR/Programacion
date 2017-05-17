
/**
 * Clase que permite crear objetos satelite, obtener su posicion en cada eje, encender y apagar su 
 * motor y calcular su velocidad y posición transcurrido un tiempo determinado.
 * 
 * @author Iñigo Gonzalez 
 * @version 27/03/16
 */
public class Satelite
{
    //datos de la órbita del satélite
    private double x,y,vx,vy,ac; //m,m,m/s,m/s,m/s^2
    //estado del motor del satelite
    private boolean encendido;
    //constantes
    private static final double G=6.674e-11; //N*m^2/kg^2
    private static final double  MT=5.98e24; //kg
    /**
     * Constructor de la clase Satelite a partir de la velocidad en el eje x e y, y la 
     * aceleración centrípeta en unidades del S.I. Los objetos Satelite tienen por defecto 
     * altura 15000km y posición 0 en el eje x.
     */
    public Satelite(double vx,double vy,double ac)
    {
        //Se da el valor de los parámetros a los atributos
        this.x=0.0;
        this.y=15000000.0;
        this.vx=vx;
        this.vy=vy;
        this.ac=ac;
        this.encendido=false;
    }
    /**
     * Metodo que retorna la posicion en el eje x del satelite como double en unidades del S.I
     */
    public double posX()
    {
        return x;
    }
    /**
     * Metodo que retorna la posicion en el eje y del satelite como double en unidades del S.I
     */
    public double posY()
    {
        return y;
    }
    /**
     * Metodo que enciende el motor del satelite
     */
    public void enciende()
    {
        encendido=true;
    }
     /**
     * Metodo que apaga el motor del satelite
     */
    public void apaga()
    {
        encendido=false;
    }
    /**
     * Metodo que calcula la posicion y velocidad del satelite en unidades S.I transcurrido el 
     * tiempo en segundos introducido por teclado. El metodo tiene en cuenta si el motor del
     * satelite esta encendido o apagado
     */
    public void avanzaTiempo(double t)
    {
        double agx=((G*MT)/(x*x+y*y))*(-x/Math.sqrt(x*x+y*y));
        double agy=((G*MT)/(x*x+y*y))*(-y/Math.sqrt(x*x+y*y));
        double ax;
        double ay;
        if(encendido==true){
             double acx=ac*vx/Math.sqrt(vx*vx+vy*vy);
             double acy=ac*vy/Math.sqrt(vx*vx+vy*vy);
             ax=acx+agx;
             ay=acy+agy;
            }
        else{
             ax=agx;
             ay=agy;
            }   
        x=x+vx*t+0.5*ax*t*t;
        y=y+vy*t+0.5*ay*t*t;
        vx=vx+ax*t;
        vy=vy+ay*t;
    }
}

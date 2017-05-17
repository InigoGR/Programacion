
/**
 * Write a description of class RhizopusOryzae here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class RhizopusOryzae
{
    private double y0,yMax,velMax,m,v,h0;
    private String condiciones;
    public RhizopusOryzae(double y0,double yMax,double velMax,double m,double v,double h0, String condiciones)
    {
        this.y0=y0;
        this.yMax=yMax;
        this.velMax=velMax;
        this.m=m;
        this.v=v;
        this.h0=h0;
        this.condiciones=condiciones;
    }

    public double ajuste(double t)
    {
        double q0;
        q0=1/(Math.exp(h0)-1);
        return t+(1/v)*Math.log((Math.exp(-v*t)+q0)/(1+q0));
    }

    public double diametro(double t)
    {
        return y0+velMax*ajuste(t)-(1/m)*Math.log(1+((Math.exp(m*velMax*ajuste(t))-1)/(Math.exp(m*(yMax-y0)))));
    }

    public String getCondiciones()
    {
        return condiciones;
    }
}

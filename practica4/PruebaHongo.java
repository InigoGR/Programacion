
/**
 * Write a description of class PruebaHongo here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class PruebaHongo
{
  public void main()
  {
   RhizopusOryzae Hongo1=new RhizopusOryzae(0,3.5,0.36,0.06,(10.0/24.0),2.757,"T=20C, pH=3, aW=0.895");
   RhizopusOryzae Hongo2=new RhizopusOryzae(0,3.0,0.16,0.06,(10.0/33.0),2.757,"T=30C, pH=5, aW=0.99");
   
   System.out.println("Sistema 1 para "+Hongo1.getCondiciones()); 
   System.out.println("diametro(t=0h): "+Hongo1.diametro(0)+"mm");
   System.out.println("diametro(t=50h): "+Hongo1.diametro(50)+"mm");
   System.out.println("diametro(t=150h): "+Hongo1.diametro(150)+"mm");
                      
   System.out.println("Sistema 2 para "+Hongo2.getCondiciones()); 
   System.out.println("diametro(t=0h): "+Hongo2.diametro(0)+"mm");
   System.out.println("diametro(t=50h): "+Hongo2.diametro(50)+"mm");
   System.out.println("diametro(t=150h): "+Hongo2.diametro(150)+"mm");
  }
                      
}

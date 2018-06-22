/**
 * Created by dajie on 18-1-30.
 */
public class ParentClass {
    private int a=1;
   public  static int b=111;
    static {
        System.out.print("ParentClass static");
    }
    ParentClass(){
        System.out.print("ParentClass---");
    }
    public void eat(){
        System.out.print("eatfather----");
    }
 public int getA(){
     return a;
 }

}

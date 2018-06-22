/**
 * Created by dajie on 18-1-30.
 */
public class SonClass extends ParentClass implements Cloneable {

    private int a=2;
    public static int b=222;
    static {
        System.out.print("SonClass static");
    }
    SonClass(){
        System.out.print("sonclass---");
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public void eat(){
        System.out.print("soneat---");
    }
public int getA(){
    return a;
}


}

/**
 * Created by dajie on 17-8-22.
 */
public class people implements person {
    static int ab= 2;
    static final int ac=3;
    int a=100;
    static {
        System.out.println("-------");
        System.out.println(ab);
        System.out.println(ac);
    }
    {
        System.out.println(a);
    }

    public people(){
        System.out.print(a);
        a=200;
    }
    @Override
    public void eat() {
System.out.print("1111");
    }

    @Override
    public void set() {

    }
    public void meet(){

    }
}

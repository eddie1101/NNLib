package demo.xor;

public class LabledBoolean {

    double x;
    double y;
    Double[] label;

    public LabledBoolean(double x, double y) {
        this.x = x;
        this.y = y;

        label = new Double[1];

        if(x == 1 && y == 1) {
            label[0] = 0d;
        }else if(x == 1 || y == 1) {
            label[0] = 1d;
        }else{
            label[0] = 0d;
        }
    }

}

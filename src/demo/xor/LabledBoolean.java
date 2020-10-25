package demo.xor;

import java.util.concurrent.ThreadLocalRandom;

public class LabledBoolean {

    double x;
    double y;
    Double[] label;

    public LabledBoolean() {
        x = ThreadLocalRandom.current().nextBoolean() ? 1 : 0;
        y = ThreadLocalRandom.current().nextBoolean() ? 1 : 0;

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

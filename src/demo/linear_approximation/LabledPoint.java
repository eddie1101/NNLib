package demo.linear_approximation;

import java.util.concurrent.ThreadLocalRandom;

public class LabledPoint {

    public double x, y;
    public double label;

    public LabledPoint() {
        this.x = ThreadLocalRandom.current().nextDouble(-10, 10);
        this.y = ThreadLocalRandom.current().nextDouble(-10, 10);

        if(x < y)
            this.label = -1;
        else
            this.label = 1;
    }

}

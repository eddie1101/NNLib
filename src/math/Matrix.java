package math;

import java.text.DecimalFormat;
import java.util.concurrent.ThreadLocalRandom;

public class Matrix {

    int numRows;
    int numCols;

    Double[][] data;

    public Matrix(int numRows, int numCols) {
        this.numRows = numRows;
        this.numCols = numCols;

        this.data = new Double[numCols][numRows];

        for(int col = 0; col < numCols; col++){
            for(int row = 0; row < numRows; row++) {
                this.data[row][col] = 0d;
            }
        }
    }

    public Matrix(int numRows, int numCols, Double[][] data) {
        this.data = data;
        this.numRows = numRows;
        this.numCols = numCols;
    }

    public void randomInitialization(double min, double max) {
        for(int col = 0; col < numCols; col++){
            for(int row = 0; row < numRows; row++) {
                this.data[row][col] = ThreadLocalRandom.current().nextDouble(min, max);
            }
        }
    }

    public void add(Object o) {
        if(o instanceof Double) {
            Double od = (Double) o;
            addScalar(od);
        }else if(o instanceof Integer) {
            Integer oi = (Integer) o;
            addScalar(oi);
        }else if(o instanceof Float) {
            Float of = (Float) o;
            addScalar(of);
        }else if(o instanceof Matrix) {
            Matrix om = (Matrix) o;
            addMatrix(om);
        }
    }

    private void addScalar(double o) {
        for(int col = 0; col < numCols; col++){
            for(int row = 0; row < numRows; row++) {
                data[row][col] += o;
            }
        }
    }

    private void addMatrix(Matrix o) {
        assert o.numRows == this.numRows;
        assert o.numCols == this.numCols;

        for(int col = 0; col < numCols; col++){
            for(int row = 0; row < numRows; row++) {
                data[row][col] += o.data[row][col];
            }
        }
    }

    public void mult(Object o) {
        if(o instanceof Double) {
            Double od = (Double) o;
            multScalar(od);
        }else if(o instanceof Integer) {
            Integer oi = (Integer) o;
            multScalar(oi);
        }else if(o instanceof Float) {
            Float of = (Float) o;
            multScalar(of);
        }else if(o instanceof Matrix) {
            Matrix om = (Matrix) o;
            multMatrix(om);
        }
    }

    private void multScalar(double o) {
        for(int col = 0; col < numCols; col++){
            for(int row = 0; row < numRows; row++) {
                data[row][col] *= o;
            }
        }
    }

    private void multMatrix(Matrix o) {
        assert validMult(this, o);

        int length = this.numRows;

        Double[][] newData = new Double[o.numCols][this.numRows];

        for(int row = 0; row < this.numRows; row++){
            for(int col = 0; col < o.numCols; col++) {
                newData[col][row] = dot(getRowAtIndex(row, this), o.data[col]);
            }
        }

        this.data = newData;
        this.numCols = data.length;
        this.numRows = data[0].length;
    }

    private Double[] getRowAtIndex(int idx, Matrix m) {

        Double[] row = new Double[m.numCols];

        for(int i = 0; i < m.numCols; i++) {
            row[i] = m.data[i][idx];
        }

        return row;
    }

    public static double dot(Double[] v1, Double[] v2) {
        assert v1.length == v2.length;

        double sum = 0;
        for(int i = 0; i < v1.length; i++) {
            sum += v1[i] * v2[i];
        }

        return sum;
    }

    public static boolean validMult(Matrix m1, Matrix m2) {
        return m1.numCols == m2.numRows;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        for(int col = 0; col < numCols; col++) {
            for (int row = 0; row < numRows; row++) {
                builder.append(String.format("%5.2f", data[col][row])).append(" ");
            }
            builder.append("\n");
        }

        builder.deleteCharAt(builder.lastIndexOf("\n"));
        return builder.toString();
    }

}

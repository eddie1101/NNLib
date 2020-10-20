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
                this.data[col][row] = 0d;
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
                this.data[col][row] = ThreadLocalRandom.current().nextDouble(min, max);
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

    public void transpose() {
        Matrix m = transpositionOf(this);
        this.data = m.data;
        this.numRows = m.numRows;
        this.numCols = m.numCols;
    }

    private void multScalar(double o) {
        for(int col = 0; col < numCols; col++){
            for(int row = 0; row < numRows; row++) {
                data[row][col] *= o;
            }
        }
    }

    private void multMatrix(Matrix o) {
        Matrix m = multiplcationOf(this, o);
        this.data = m.data;
        this.numCols = m.numCols;
        this.numRows = m.numRows;
    }

    public static Matrix multiplcationOf(Matrix a, Matrix b) {
        assert validMult(a, b);

        Double[][] newData = new Double[b.numCols][a.numRows];

        for(int row = 0; row < a.numRows; row++){
            for(int col = 0; col < b.numCols; col++) {
                newData[col][row] = dot(getRowAtIndex(row, a), b.data[col]);
            }
        }

        return new Matrix(newData[0].length, newData.length, newData);

    }

    public static Matrix transpositionOf(Matrix m) {
        Matrix result = new Matrix(m.numCols, m.numRows);

        for(int row = 0; row < result.numRows; row++) {
            for(int col = 0; col < result.numCols; col++) {
                result.data[col][row] = m.data[row][col];
            }
        }
        return result;
    }

    public static Double[] getRowAtIndex(int idx, Matrix m) {

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

    @Override
    public boolean equals(Object o) {
        if(!(o instanceof Matrix)) return false;
        Matrix other = (Matrix) o;

        if(this.numCols != other.numCols || this.numRows != other.numRows) return false;

        for(int i = 0; i < this.numRows; i++) {
            for(int n = 0; n < this.numCols; n++) {
                if(this.data[i][n] != other.data[i][n]) return false;
            }
        }
        return true;
    }

}

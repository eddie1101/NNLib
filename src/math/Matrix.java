package math;

import function.OneParameterFunction;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.concurrent.ThreadLocalRandom;

public class Matrix {

    int numRows;
    int numCols;

    Double[][] data;

    public Matrix() {
        numRows = 0;
        numCols = 0;
        this.data = new Double[0][0];
    }

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

    public Matrix(Matrix m) {
        this.data = m.data;
        this.numCols = m.numCols;
        this.numRows = m.numRows;
    }

    public Matrix(Double[] array) {
        this.numCols = 1;
        this.numRows = array.length;
        this.data = new Double[numCols][numRows];
        System.arraycopy(array, 0, this.data[0], 0, array.length);
    }

    public Double[] toArray() {

        ArrayList<Double> arr = new ArrayList<>();

        for(int i = 0; i < this.numCols; i++) {
            arr.addAll(Arrays.asList(this.data[i]).subList(0, this.numRows));
        }

        Double[] doubleArray = new Double[this.numCols * this.numRows];
        arr.toArray(doubleArray);

        return doubleArray;

    }

    public void randomInitialization(double min, double max) {
        for(int col = 0; col < numCols; col++){
            for(int row = 0; row < numRows; row++) {
                this.data[col][row] = ThreadLocalRandom.current().nextDouble(min, max);
            }
        }
    }

    public void randomInitialization() {
        for(int col = 0; col < numCols; col++){
            for(int row = 0; row < numRows; row++) {
//                this.data[col][row] = Math.min(Math.max(ThreadLocalRandom.current().nextGaussian(), -1), 1);
                this.data[col][row] = ThreadLocalRandom.current().nextGaussian();
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
                data[col][row] += o;
            }
        }
    }

    private void addMatrix(Matrix o) {
//        assert o.numRows == this.numRows;
//        assert o.numCols == this.numCols;

        for(int col = 0; col < numCols; col++){
            for(int row = 0; row < numRows; row++) {
                data[col][row] += o.data[col][row];
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
                data[col][row] *= o;
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
//        assert validMult(a, b);

        Double[][] newData = new Double[b.numCols][a.numRows];

        for(int row = 0; row < a.numRows; row++){
            for(int col = 0; col < b.numCols; col++) {
                newData[col][row] = dot(getRowAtIndex(row, a), b.data[col]);
            }
        }

        return new Matrix(newData[0].length, newData.length, newData);

    }

    public static Double[] getRowAtIndex(int idx, Matrix m) {

        Double[] row = new Double[m.numCols];

        for(int i = 0; i < m.numCols; i++) {
            row[i] = m.data[i][idx];
        }

        return row;
    }

    public static double dot(Double[] v1, Double[] v2) {
//        assert v1.length == v2.length;

        double sum = 0;
        for(int i = 0; i < v1.length; i++) {
            sum += v1[i] * v2[i];
        }

        return sum;
    }

    public static boolean validMult(Matrix m1, Matrix m2) {
        return m1.numCols == m2.numRows;
    }

    public void transpose() {
        Matrix m = transpositionOf(this);
        this.data = m.data;
        this.numRows = m.numRows;
        this.numCols = m.numCols;
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

    public void map(OneParameterFunction func) {
        Matrix m = mappingOf(this, func);
        this.data = m.data;
        this.numRows = m.numRows;
        this.numCols = m.numCols;
    }

    public static Matrix mappingOf(Matrix m, OneParameterFunction func) {

        Matrix result = new Matrix(m);

        for(int col = 0; col < m.numCols; col++){
            for(int row = 0; row < m.numRows; row++) {
                result.data[col][row] = func.compute(m.data[col][row]);
            }
        }

        return result;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        for(int row = 0; row < numRows; row++) {
            for (int col = 0; col < numCols; col++) {
                builder.append(String.format("%8.5f", data[col][row])).append(" ");
            }
            builder.append("\n");
        }
        return builder.toString();
    }

    @Override
    public boolean equals(Object o) {
        if(!(o instanceof Matrix)) return false;
        Matrix other = (Matrix) o;

        if(this.numCols != other.numCols || this.numRows != other.numRows) return false;

        for(int i = 0; i < this.numRows; i++) {
            for(int n = 0; n < this.numCols; n++) {
                if(!this.data[i][n].equals(other.data[i][n])) return false;
            }
        }
        return true;
    }

}

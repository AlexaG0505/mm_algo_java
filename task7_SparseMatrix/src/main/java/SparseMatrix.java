import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SparseMatrix {
    private final int[] rowIndex; // row index pointers
    private final int[] colIndex; // column indices
    private final double[] values;// non-zero values
    private final int size;

    public SparseMatrix(int size, int[] rowIndex, int[] colIndex, double[] values) {
        this.size = size;
        this.rowIndex = rowIndex;
        this.colIndex = colIndex;
        this.values = values;
    }

    public int getSize() {
        return size;
    }

    public int[] getRowIndex() {
        return rowIndex;
    }

    public int[] getColIndex() {
        return colIndex;
    }

    public double[] getValues() {
        return values;
    }

    public double get(int row, int col) {
        if (row < 0 || row >= size || col < 0 || col >= size) {
            throw new IllegalArgumentException("Invalid matrix indices");
        }
        int rowStart = rowIndex[row];
        int rowEnd = rowIndex[row + 1];

        for (int i = rowStart; i < rowEnd; i++) {
            if (colIndex[i] == col) {
                return values[i];
            }
        }
        return 0.0;
    }

    public SparseMatrix multiplySparseMatrix(SparseMatrix secondMatrix) {
        if (size != secondMatrix.size) {
            throw new IllegalArgumentException("Matrix sizes must be equal");
        }

        ArrayList<Integer> resultRowIndex = new ArrayList<>();
        ArrayList<Integer> resultColIndex = new ArrayList<>();
        ArrayList<Double> resultValues = new ArrayList<>();

        int[] colMarker = new int[size];

        int resultIndex = 0;
        resultRowIndex.add(resultIndex);

        for (int row = 0; row < size; row++) {
            Arrays.fill(colMarker, -1);

            int rowStartA = rowIndex[row];
            int rowEndA = rowIndex[row + 1];

            for (int i = rowStartA; i < rowEndA; i++) {
                int colA = colIndex[i];
                double valueA = values[i];

                int rowStartB = secondMatrix.rowIndex[colA];
                int rowEndB = secondMatrix.rowIndex[colA + 1];

                for (int j = rowStartB; j < rowEndB; j++) {
                    int colB = secondMatrix.colIndex[j];
                    double valueB = secondMatrix.values[j];

                    if (colMarker[colB] != row) {
                        colMarker[colB] = row;
                        resultColIndex.add(colB);
                        resultValues.add(valueA * valueB);
                        resultIndex++;
                    } else {
                        int lastIndex = resultIndex - 1;
                        resultValues.set(lastIndex, resultValues.get(lastIndex) + valueA * valueB);
                    }
                }
            }
            resultRowIndex.add(resultIndex);
        }

        int[] resultRowIndexArray = resultRowIndex.stream().mapToInt(Integer::intValue).toArray();
        int[] resultColIndexArray = resultColIndex.stream().mapToInt(Integer::intValue).toArray();
        double[] resultValuesArray = resultValues.stream().mapToDouble(Double::doubleValue).toArray();

        return new SparseMatrix(size, resultRowIndexArray, resultColIndexArray, resultValuesArray);
    }

    public static SparseMatrix toCompressedMatrix(double[][] data) {
        int size = data.length;
        List<Integer> rowIndexList = new ArrayList<>();
        List<Integer> colIndexList = new ArrayList<>();
        List<Double> valueList = new ArrayList<>();

        rowIndexList.add(0);

        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                double value = data[row][col];
                if (value != 0.0) {
                    colIndexList.add(col);
                    valueList.add(value);
                }
            }
            rowIndexList.add(colIndexList.size());
        }

        int[] rowIndex = new int[rowIndexList.size()];
        int[] colIndex = new int[colIndexList.size()];
        double[] values = new double[valueList.size()];

        for (int i = 0; i < rowIndexList.size(); i++) {
            rowIndex[i] = rowIndexList.get(i);
        }

        for (int i = 0; i < colIndexList.size(); i++) {
            colIndex[i] = colIndexList.get(i);
            values[i] = valueList.get(i);
        }
        return new SparseMatrix(size, rowIndex, colIndex, values);
    }

    public double[][] fromCompressedMatrix() {
        double[][] Matrix = new double[size][size];

        for (int row = 0; row < size; row++) {
            int rowStart = rowIndex[row];
            int rowEnd = rowIndex[row + 1];

            for (int i = rowStart; i < rowEnd; i++) {
                int col = colIndex[i];
                double value = values[i];
                Matrix[row][col] = value;
            }
        }
        return Matrix;
    }

    public static void main(String[] args) {
        // 0 1 2
        // 3 0 4
        // 5 6 0
        int size = 3;
        int[] rowIndexA = {0, 2, 4, 6};
        int[] colIndexA = {1, 2, 0, 2, 0, 1};
        double[] valuesA = {1.0, 2.0, 3.0, 4.0, 5.0, 6.0};

        // 1 0 0
        // 0 2 7
        // 0 0 3
        int[] rowIndexB = {0, 1, 3, 4};
        int[] colIndexB = {0, 1, 2, 2};
        double[] valuesB = {1, 2, 7, 3};

        SparseMatrix matrixA = new SparseMatrix(size, rowIndexA, colIndexA, valuesA);
        SparseMatrix matrixB = new SparseMatrix(size, rowIndexB, colIndexB, valuesB);

        SparseMatrix result = matrixA.multiplySparseMatrix(matrixB);

        System.out.println("Result:");
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                System.out.print(result.get(row, col) + " ");
            }
            System.out.println();
        }
    }
}
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

public class SparseMatrixTest {
    @Test
    @DisplayName("Test case: sparse matrix multiplication (sparse form)")
    public void testMatrixMultiplicationSparse() {
        // 1 0 0
        // 0 2 7
        // 0 0 3
        int[] rowIndexA = {0, 1, 3, 4};
        int[] colIndexA = {0, 1, 2, 2};
        double[] valuesA = {1, 2, 7, 3};
        SparseMatrix matrixA = new SparseMatrix(3, rowIndexA, colIndexA, valuesA);

        // 4 0 0
        // 0 5 0
        // 0 0 6
        int[] rowIndexB = {0, 1, 2, 3};
        int[] colIndexB = {0, 1, 2};
        double[] valuesB = {4, 5, 6};
        SparseMatrix matrixB = new SparseMatrix(3, rowIndexB, colIndexB, valuesB);

        SparseMatrix result = matrixA.multiplySparseMatrix(matrixB);

        // 4 0 0
        // 0 10 42
        // 0 0 18
        int[] expectedRowIndex = {0, 1, 3, 4};
        int[] expectedColIndex = {0, 1, 2, 2};
        double[] expectedValues = {4, 10, 42, 18};
        SparseMatrix expectedMatrix = new SparseMatrix(3, expectedRowIndex, expectedColIndex, expectedValues);

        Assert.assertArrayEquals(expectedMatrix.getRowIndex(), result.getRowIndex());
        Assert.assertArrayEquals(expectedMatrix.getColIndex(), result.getColIndex());
        Assert.assertArrayEquals(expectedMatrix.getValues(), result.getValues(), 0.0001);
    }

    @Test
    @DisplayName("Test case: sparse matrix multiplication (list form)")
    public void testMatrixMultiplicationList() {
        double[][] MatrixA = {
                {1.0, 0.0, 2.0},
                {0.0, 3.0, 0.0},
                {4.0, 0.0, 5.0}
        };

        double[][] MatrixB = {
                {1.0, 2.0, 0.0},
                {0.0, 3.0, 4.0},
                {0.0, 0.0, 5.0}
        };

        double[][] expectedResult = {
                {1.0, 2.0, 10.0},
                {0.0, 9.0, 12.0},
                {4.0, 8.0, 25.0}
        };

        SparseMatrix sparseMatrixA = SparseMatrix.toCompressedMatrix(MatrixA);
        SparseMatrix sparseMatrixB = SparseMatrix.toCompressedMatrix(MatrixB);

        SparseMatrix resultSparseMatrix = sparseMatrixA.multiplySparseMatrix(sparseMatrixB);

        double[][] denseResult = resultSparseMatrix.fromCompressedMatrix();

        Assert.assertArrayEquals(expectedResult, denseResult);
    }

    @Test
    @DisplayName("Test case: sparse matrix multiplication with zero (list form)")
    public void testMatrixMultiplicationListZeros() {
        double[][] MatrixA = {
                {0.0, 0.0, 0.0},
                {0.0, 0.0, 0.0},
                {0.0, 0.0, 0.0}
        };

        double[][] MatrixB = {
                {1.0, 2.0, 0.0},
                {0.0, 3.0, 4.0},
                {0.0, 0.0, 5.0}
        };

        double[][] expectedResult = {
                {0.0, 0.0, 0.0},
                {0.0, 0.0, 0.0},
                {0.0, 0.0, 0.0}
        };

        SparseMatrix sparseMatrixA = SparseMatrix.toCompressedMatrix(MatrixA);
        SparseMatrix sparseMatrixB = SparseMatrix.toCompressedMatrix(MatrixB);

        SparseMatrix resultSparseMatrix = sparseMatrixA.multiplySparseMatrix(sparseMatrixB);

        double[][] denseResult = resultSparseMatrix.fromCompressedMatrix();

        Assert.assertArrayEquals(expectedResult, denseResult);
    }
}

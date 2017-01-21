package L12;

public class Matrix {

    private static boolean multiplyMatrix(int[][] matrix1, int[][] matrix2, int[][] matrix3){
        if(matrix1[0].length == matrix2.length) {
            Thread[] tmp = new Thread[matrix1.length];
            for(int i=0; i<matrix1.length; i++){
                tmp[i] = new rowMultiplier(matrix1,matrix2,matrix3,i);
                tmp[i].start();
            }
            try {
                for(int i=0; i<tmp.length; i++) tmp[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return true;
        }
        return false;
    }

    public static void main(String[] args){
        int[][] M1 = {{-2,-3,1},{-1,4,0}};
        int[][] M2 = {{-2,-1,2},{3,0,1},{2,2,-1}};
        int[][] M3 = new int[2][3];

        multiplyMatrix(M1,M2,M3);

        print(M3);
    }

    private static class rowMultiplier extends Thread{
        int[][] matrix1;
        int[][] matrix2;
        int[][] resultMatrix;
        final int row;

        public rowMultiplier(int[][] matrix1, int[][] matrix2, int[][] resultMatrix, int row) {
            this.matrix1 = matrix1;
            this.matrix2 = matrix2;
            this.resultMatrix = resultMatrix;
            this.row = row;
        }

        @Override
        public void run() {
            for (int i=0; i<matrix2[0].length; i++)
                for (int j=0; j<matrix1[0].length; j++)
                    resultMatrix[row][i] += (matrix1[row][j] * matrix2[j][i]);
        }
    }

    private static void print(int[][] arr){
        for(int i=0; i < arr.length; i++){
            for(int j=0; j < arr[i].length; j++)
                System.out.print(arr[i][j]+", ");
            System.out.print("\n");
        }
    }
}

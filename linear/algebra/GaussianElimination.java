package linear.algebra;

public class GaussianElimination{

    private int cols;
    private int rows;
    private double[][] matrix;

    public int getCols(){
        return this.cols;
    }

    public int getRows() {
        return this.rows;
    }

    public double[][] getMatrix(){
        return this.matrix;
    }

    public void setMatrix(double[][] matrix) {
        if ((matrix[0].length != cols) || (matrix.length != rows)) {
            throw new IllegalArgumentException();
        }
        else{
            for (int i = 0; i < this.rows; i++) {
                for (int j = 0; j < this.cols; j++) {
                    this.matrix[i][j] = matrix[i][j];
                }
            }
        }
    }
    
    public GaussianElimination(int rows, int cols, double[][] matrix) {
        this.rows = rows;
        this.cols = cols;
        this.matrix = new double[rows][cols];
        setMatrix(matrix);
    }

    public void rowEchelonForm() {
        int k = 0;
        int h = 0;

        while ((h<this.rows) && (k<this.cols)) {
            int max = argMax(h,k);
            if (matrix[max][k]==0) {
                k++;
            }
            else{
                swapRows(h, max);
                for (int i = h+1; i < this.rows; i++) {
                    multiplyAndAddRow(i, h, k);
                }
                multiplyRow(h, k);
                h++;
                k++;
            }
        }

    }

    private void checkMatrixDimensions(double[][] matrix){
        
    }

    private int argMax(int h, int k) {
        int max = h;
        double maxert = Math.abs(matrix[h][k]);
        for (int i = h+1; i < this.rows; i++) {
            if (Math.abs(matrix[i][k])>maxert) {
                max=i;
                maxert = Math.abs(matrix[i][k]);
            }
        }

        return max;     
    }

    private void swapRows(int h, int k){
        for (int i = 0; i < this.cols; i++) {
            double tmp = matrix[h][i];
            matrix[h][i] = matrix[k][i];
            matrix[k][i] = tmp;
        }
    }

    private void multiplyAndAddRow(int addRow, int mulRow, int colIndex) {
        double f = matrix[addRow][colIndex]/matrix[mulRow][colIndex];
        //matrix[addRow][colIndex] = 0;
        for (int i = colIndex; i < this.cols; i++) {
            matrix[addRow][i]=matrix[addRow][i]-matrix[mulRow][i]*f;
        }
    }

    private void multiplyRow(int h, int k) {
        double f=matrix[h][k];
        for (int i = k; i < cols; i++) {
            matrix[h][i] = matrix[h][i]/f;
        }
    }

    public void backSubstitution() {
        for (int i = rows-1; i >=0; i--) {
            if (this.matrix[i][i] == 0) {
                throw new IllegalArgumentException();
            }

            for (int j = i-1; j >= 0; j--) {
                multiplyAndAddRow(j, i, i);
            }
        }
    }

    public void print(){
        char[] betuk = {'a','b','c','d','e','f','g','i','j','k','l','m','n','o','p','q','r','s','t'}; 
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if(j==cols-1){
                    System.out.println("="+matrix[i][j]);
                }
                else{
                    if (matrix[i][j]>0) {
                        System.out.print("+" + matrix[i][j]+betuk[j]);
                    }
                    else{
                        System.out.print("-" + Math.abs(matrix[i][j])+betuk[j]);
                    }
                }
            }  
        }
    }
}
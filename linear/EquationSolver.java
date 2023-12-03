package linear;

import linear.algebra.GaussianElimination;

public class EquationSolver {

    public static void main(String[] args) {

        double[][] matrix= stringsToDouble(args);

        GaussianElimination gauss =new GaussianElimination(matrix.length,matrix[0].length,matrix);
        gauss.print();
        gauss.rowEchelonForm();
        gauss.print();
        gauss.backSubstitution();
        gauss.print();
    }

    static double[][] stringsToDouble(String[] strings) {
        double[][] array = new double[strings.length][];
    
        for (int i = 0; i < strings.length; i++) {
            String[] parts = strings[i].split(",");
            array[i] = new double[parts.length];
    
            for (int j = 0; j < parts.length; j++) {
                array[i][j] = Double.parseDouble(parts[j]);
            }
        }
        return array;
    }
}
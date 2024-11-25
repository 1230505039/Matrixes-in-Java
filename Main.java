import java.util.Scanner;
import java.util.Vector;

class Matrix{   //Creating matrix class

    private double[][] resultMatrix = new double[3][3]; //Creates result matrix to be able returns

    Scanner scanner = new Scanner(System.in);   //Creates scanner for input

    public void createMatrix(double[][] matrix, int columnRow) {    //Creates metod to create matrixes

        for (int i = 0; i < columnRow; i++) {
            for (int j = 0; j < columnRow; j++) {
                System.out.print("Enter " + i + j + " elemen: ");
                matrix[i][j] = scanner.nextDouble();
            }
        }

        System.out.println();
    }

    public void displayMatrix(double[][] matrix, int columnRow) {   //Creates metod to display matrixes
        for (int i = 0; i < columnRow; i++) {
            for (int j = 0; j < columnRow; j++) {
                System.out.printf("%.2f ", matrix[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }

    public void multiplyMatrix(double[][] matrix1, double[][] matrix2) {    //Creates metod to multiply matrixes
        int product = 0;    //Creates a variable to keep results of multiply
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                for (int k = 0; k < 3; k++) {
                    product += matrix1[i][k] * matrix2[k][j];
                }
                resultMatrix[i][j] = product;   //Add products to resultMatrix
                product = 0;
            }
        }
    }

    public double determinant(double[][] matrix, int columnRow) {   //Creates metod to calculate determinant of matrixes

        double total = 0;
        if (columnRow == 3) {   //If type of matrix is 3x3
            double resultFirst = 0; //A variable to keep result for "00". elemen (first elemen of matrix)
            double resultSecond = 0;    //A variable to keep result for "01". elemen
            double resultThird = 0; //A variable to keep result for "02". elemen

            for (int i = 0; i < matrix.length; i++) {

                if (i == 0){
                    resultFirst = matrix[i+1][i+1] * matrix[i+2][i+2] - matrix[i+1][i+2] * matrix[i+2][i+1];
                    resultFirst *= matrix[i][i];
                }

                else if (i == 1){
                    resultSecond = -1 * (matrix[i][i-1] * matrix[i+1][i+1] - matrix[i][i+1] * matrix[i+1][i-1]);    //Calculating determinant with conditional block
                    resultSecond *= matrix[i-1][i];
                }

                else if (i == 2){
                    resultThird = matrix[i-1][i-2] * matrix[i][i-1] - matrix[i-1][i-1] * matrix[i][i-2];
                    resultThird *= matrix[i-2][i];
                }
            }
            total = resultFirst + resultSecond + resultThird;
        }

        else if (columnRow == 2) {  //If type of matrix is 2x2
            total = matrix[0][0] * matrix[1][1] - matrix[0][1] * matrix[1][0];
        }

        else{
            System.out.println("Invalid rowColumn! (2 or 3");
        }
        return total;
    }

    public void transpose(double[][] matrix) {  //Creates metod to transpose a matrix
        for (int i = 0; i < matrix.length; i++) {
            for (int j = i + 1; j < matrix.length; j++) {
                double temp = matrix[i][j]; //Creating a temp variable to keep first elemen
                matrix[i][j] = matrix[j][i];    //Changing elemens
                matrix[j][i] = temp;    //Equalize second elemen to temp(first elemen)
            }
        }
    }

    public void reverseMatrix(int columnRow) {  //Creates metod to reverses matrixes
        double[][] myMatrix = new double[columnRow][columnRow]; //Create new matrix
        createMatrix(myMatrix, columnRow);  //Adds elemen
        System.out.println("Matrix:");
        displayMatrix(myMatrix, columnRow); //Displays new matrix
        double[][] reversedMatrix = new double[columnRow][columnRow];   //Creates reversedMatrix to return
        if (columnRow == 3) {   //If matrix type is 3x3
            if (determinant(myMatrix, columnRow) != 0){ //If determinant of matrix is not "0"
                for (int i = 0; i < columnRow; i++) {
                    for (int j = 0; j < columnRow; j++) {
                        switch (i){
                            case 0:                              //Changing elemens by using for and switch-case blocks
                                switch (j){
                                    case 0:
                                        reversedMatrix[i][j] = myMatrix[i+1][j+1] * myMatrix[i+2][j+2] - myMatrix[i+1][j+2] * myMatrix[i+2][j+1];
                                        break;
                                    case 1:
                                        reversedMatrix[i][j] = -1 * (myMatrix[i+1][j-1] * myMatrix[i+2][j+1] - myMatrix[i+1][j+1] * myMatrix[i+2][j-1]);
                                        break;
                                    case 2:
                                        reversedMatrix[i][j] = myMatrix[i+1][j-2] * myMatrix[i+2][j-1] - myMatrix[i+1][j-1] * myMatrix[i+2][j-2];
                                        break;
                                    default:
                                        break;
                                }
                                break;
                            case 1:
                                switch (j){
                                    case 0:
                                        reversedMatrix[i][j] = -1 * (myMatrix[i-1][j+1] * myMatrix[i+1][j+2] - myMatrix[i-1][j+2] * myMatrix[i+1][j+1]);
                                        break;
                                    case 1:
                                        reversedMatrix[i][j] = myMatrix[i-1][j-1] * myMatrix[i+1][j+1] - myMatrix[i-1][j+1] * myMatrix[i+1][j-1];
                                        break;
                                    case 2:
                                        reversedMatrix[i][j] = -1 * (myMatrix[i-1][j-2] * myMatrix[i+1][j-1] - myMatrix[i-1][j-1] * myMatrix[i+1][j-2]);
                                        break;
                                    default:
                                        break;
                                }
                                break;
                            case 2:
                                switch (j){
                                    case 0:
                                        reversedMatrix[i][j] = myMatrix[i-2][j+1] * myMatrix[i-1][j+2] - myMatrix[i-2][j+2] * myMatrix[i-1][j+1];
                                        break;
                                    case 1:
                                        reversedMatrix[i][j] = -1 * (myMatrix[i-2][j-1] * myMatrix[i-1][j+1] - myMatrix[i-2][j+1] * myMatrix[i-1][j-1]);
                                        break;
                                    case 2:
                                        reversedMatrix[i][j] = myMatrix[i-2][j-2] * myMatrix[i-1][j-1] - myMatrix[i-2][j-1] * myMatrix[i-1][j-2];
                                        break;
                                    default:
                                        break;
                                }
                                break;
                            default:
                                break;
                        }
                    }
                }
                System.out.println("Cofactor of Matrix");
                displayMatrix(reversedMatrix, columnRow);   //Displays matrix after change elemens
                transpose(reversedMatrix);  //Transposes matrix
                System.out.println("Transpose of Matrix:");
                displayMatrix(reversedMatrix, columnRow);   //Displays matrix after transpose

                for (int i = 0 ; i < columnRow; i++) {
                    for (int j = 0; j < columnRow; j++) {   //For loops to do last operation
                        reversedMatrix[i][j] *= 1 / determinant(myMatrix, columnRow);
                    }
                }
                System.out.println("Reversed Matrix:");
                displayMatrix(reversedMatrix, columnRow);   //And the reversed matrix
            }

            else {
                System.out.println("Determinant of Matrix is '0'");
            }
        }

        else if (columnRow == 2) {  //If matrix type is 2x2
            if (determinant(myMatrix, columnRow) != 0){ //If determinant of matrix is not "0"
                reversedMatrix[0][0] = myMatrix[1][1];
                reversedMatrix[0][1] = -1 * myMatrix[0][1];
                reversedMatrix[1][0] = -1 * myMatrix[1][0];
                reversedMatrix[1][1] = myMatrix[0][0];

                System.out.println("Cofactor of Matrix");
                displayMatrix(reversedMatrix, columnRow);   //Display matrix after changing elemens

                for (int i = 0; i < columnRow; i++) {
                    for (int j = 0; j < columnRow; j++) {   //For loops to do last operation
                        reversedMatrix[i][j] *= 1 / determinant(myMatrix, columnRow);
                    }
                }

                System.out.println("Reversed Matrix:");
                displayMatrix(reversedMatrix, columnRow);   //And the reversed matrix
            }

            else {
                System.out.println("Determinant of Matrix is '0'");
            }
        }

        else {
            System.out.println("Invalid rowColumn! (2 or 3)");
        }
    }

    public double[][] getResultMatrix() {
        return resultMatrix;            //Returns resultMatrix to "main"
    }
}

public class Main {
    public static void main(String[] args) {

        double[][] matrix1 = new double[3][3];
        double[][] matrix2 = new double[3][3];

        Matrix matrix = new Matrix(); //Create instance of Matrix class

        String option; //Create "option" variable

        Scanner scanner = new Scanner(System.in);   //Scanner for input

        while (true) {  //While loop for dynamic using

            System.out.println("""
                    [1] Multiply Matrix
                    [2] Calculate Determinant
                    [3] Reverse Matrix              
                    [4] Check Linear Independence
                    [Q] Quit
                    """);   //Prints menu

            System.out.println("Enter your choice: ");

            option = scanner.nextLine();    //Gets option

            if (option.equals("1")) {
                System.out.println("First Matrix!");
                matrix.createMatrix(matrix1, 3);  //Create matrix1
                System.out.println("Second Matrix!");
                matrix.createMatrix(matrix2, 3); //Create matrix2
                System.out.println("First Matrix:");
                matrix.displayMatrix(matrix1, 3); //Displays first matrix
                System.out.println("Second Matrix:");
                matrix.displayMatrix(matrix2, 3); //Displays second matrix
                matrix.multiplyMatrix(matrix1, matrix2); //Multiplying matrixes
                System.out.println("Product of Matrixes:");
                matrix.displayMatrix(matrix.getResultMatrix(), 3); //Displays the product of matrixes
            }

            else if (option.equals("2")) {
                matrix.createMatrix(matrix1, 3);  //Create matrix
                double result = matrix.determinant(matrix1, 3); //Calculates and displays determinant of Matrix
                System.out.println("First Matrix:");
                matrix.displayMatrix(matrix1, 3);   //Displays matrix1
                System.out.println("Determinant of Matrix: " + result);
            }

            else if (option.equals("3")) {
                int numberOfRowAndColumn;
                System.out.println("Enter number of row and column number (2 or 3): ");
                numberOfRowAndColumn = scanner.nextInt();   //Gets number of row and column
                matrix.reverseMatrix(numberOfRowAndColumn); //Reverses matrix
            }

            else if (option.equals("4")) {
                Vector vector1 = new Vector();
                Vector vector2 = new Vector();  //Create 3 vectors
                Vector vector3 = new Vector();

                double elemen;

                System.out.println("First Vector");
                for (int i = 0; i < 3; i++) {
                    System.out.println("Enter " + i + ". elemen: ");
                    elemen = scanner.nextInt(); //Gets vectors elemen from user
                    vector1.add(elemen);    //Adds vectors elemen to vector1
                }
                System.out.println("\nSecond Vector");
                for (int i = 0; i < 3; i++) {
                    System.out.println("Enter " + i + ". elemen: ");
                    elemen = scanner.nextInt(); //Gets vectors elemen from user
                    vector2.add(elemen);    //Adds vectors elemen to vector2
                }
                System.out.println("\nThird Vector");
                for (int i = 0; i < 3; i++) {
                    System.out.println("Enter " + i + ". elemen: ");
                    elemen = scanner.nextInt(); //Gets vectors elemen from user
                    vector3.add(elemen);    //Adds vectors elemen to vector3
                }

                double[][] vectorToMatrix = new double[3][3];   //Creates matrix that has elemens that vector's

                for (int i = 0; i < 3; i++) {
                    vectorToMatrix[0][i] = (double) vector1.get(i); //Changing type of vector's elemens to double and add in matrix
                }

                for (int i = 0; i < 3; i++) {
                    vectorToMatrix[1][i] = (double) vector2.get(i); //Changing type of vector's elemens to double and add in matrix
                }

                for (int i = 0; i < 3; i++) {
                    vectorToMatrix[2][i] = (double) vector3.get(i); //Changing type of vector's elemens to double and add in matrix
                }

                if (matrix.determinant(vectorToMatrix, 3) == 0){    //If determinant of matrix equals "0" ...
                    System.out.println("Matris determinantı '0' olduğundan bu vektörler doğrusal bağımlıdır.");
                }

                else {
                    System.out.println("Matris determinantı '0' olmadığından bu vektör doğrusal bağımlı değildir.\n");
                }

            }

            else if (option.equals("q") || option.equals("Q")) {    //On menu, if you press "q" or "Q" program will shut down
                System.out.println("Quitting");
                break;
            }

            else {
                System.out.println("Invalid option!");
            }
        }
    }
}
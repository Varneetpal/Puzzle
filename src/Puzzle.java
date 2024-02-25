import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Puzzle {
    private List<String> path = new ArrayList<>();
    private boolean[][] visitedBlock = createInitialArray(); //2D array keeping track of all the visited blocks
    private boolean solutionFound = false;
    private List<List<String>> allPaths = new ArrayList<>(); //all viable paths, that is the list of all the paths

    public boolean[][] createInitialArray(){
        /**
         * Creates the initial version of array to keep
         * track of visited blocks
         */

        int rowCount = 9, colCount = 13; //size of array
        // Indices of poles
        int pole1X = 1, pole1Y = 6;
        int pole2X = 3, pole2Y = 4;
        int pole3X = 3, pole3Y = 8;
        int pole4X = 6, pole4Y = 6;

        boolean[][] arrLocation = new boolean[rowCount][colCount];

        for (int i = 0; i < rowCount; i++) {
            for (int j = 0; j < colCount; j++) {
                arrLocation[i][j] = false;
            }
        }
        //Setting the poles as visited blocks
        arrLocation[pole1X][pole1Y] = true;
        arrLocation[pole2X][pole2Y] = true;
        arrLocation[pole3X][pole3Y] = true;
        arrLocation[pole4X][pole4Y] = true;

        return arrLocation;
    }

    public boolean checkTrue(boolean[][] arrLocation) {
        /**
         * Checks if all the blocks have been visited and only the destination block remains
         */
        int destRowBlock = 3;
        int destColBlock = 6;
        int falseCount = countFalse(arrLocation);

        if((falseCount == 1) && !arrLocation[destRowBlock][destColBlock]) {
            return true;
        }
        return false;

    }

    public int countFalse(boolean[][] arrLocation) { //returns the total number of false elements in the array
        /**
         * Counts the total number of false elements in the array
         */
        int rowCount = 9, colCount = 13;
        int countFalseElements = 0;
        for (int i = 0; i < rowCount; i++) {
            for (int j = 0; j < colCount; j++) {
                if (!arrLocation[i][j]) {
                    countFalseElements++;
                }
            }
        }
        return countFalseElements;
    }


    public boolean finalState(boolean[][] arrLocation, int currentY, int currentX){
        /**
         * checks if the function is at its final state (diagonal) & only dest block is unvisited
         */
        int destX = 3;
        int destY = 6;
        if((checkTrue(arrLocation)) && (Math.abs(currentX - destX) + Math.abs(currentY - destY) == 1)){
            return true;
        }
        return false;
    }


    public void findPath(boolean[][] arrLocation, int currentY, int currentX){
        /**
         * Finds the path to reach the destination
         */
//        System.out.println("PATH: " + path);
//        System.out.println("ARR LOCATION: " + arrLocation.toString());

        if (finalState(arrLocation, currentY, currentX)){
            arrLocation[3][6] = true;
            solutionFound = true;
            path.add("DIAGONAL");
            allPaths.add(new ArrayList<>(path));
            System.out.println("Solution FOUND!!"+ path);
            return;
        }

        if (visitedBlock[currentY][currentX] == false){
            visitedBlock[currentY][currentX] = true;
            checkAllPossibilities(currentX, currentY, arrLocation); //Checking for all the possible steps
            visitedBlock[currentY][currentX] = false; //Setting it back to false for the backtracking step
        }

    }


    public void checkAllPossibilities(int currentX, int currentY, boolean[][] arrLocation){
        /**
         * Checks all the possible steps and calls the function findPath with the new steps
         */

        if (currentY+1 < 9){
            path.add("DOWN");
            findPath(arrLocation, currentY + 1, currentX);
            path.remove(path.size() - 1);
        }
        if (currentY - 1 > -1) {
            path.add("UP");
            findPath(arrLocation, currentY - 1, currentX);
            path.remove(path.size() - 1);
        }
        if (currentX+1 < 13){
            path.add("RIGHT");
            findPath(arrLocation, currentY, currentX + 1);
            path.remove(path.size() - 1);
        }
        if (currentX-1 > -1){
            path.add("LEFT");
            findPath(arrLocation, currentY, currentX - 1);
            path.remove(path.size() - 1);
        }
    }

    public static void main(String[] args) {
        Puzzle puzzle = new Puzzle();
        boolean[][] arr = puzzle.createInitialArray();

        int rowCount = 9, colCount = 13;

        for (int i = 0; i < rowCount; i++) {
            for (int j = 0; j < colCount; j++) {
                System.out.print(arr[i][j] ? "T " : "F "); // Print "T" for true and "F" for false
            }
            System.out.println(); // Move to the next line after printing each row
        }
        int destY = 4;
        int destX = 12;
        puzzle.findPath(arr, destY, destX);


        if (puzzle.solutionFound){
            List<String> solution = puzzle.allPaths.get(0);
            System.out.println(solution);
        }

    }
}

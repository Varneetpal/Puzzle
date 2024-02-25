import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Puzzle {

    private List<String> path;
    private boolean[][] visitedBlock = createInitialArray(); //2D array keeping track of all the visited blocks
    private boolean solutionFound;
    private List<List<String>> allPaths; //all viable paths, that is the list of all the paths
    public Puzzle(){
        super();
        visitedBlock = createInitialArray();
        this.path = new ArrayList<>();

        this.solutionFound = false;
        this.allPaths = new ArrayList<>();
    }

    public boolean[][] createInitialArray(){

        int rowCount = 9, colCount = 13;
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
        int destRowBlock = 3;
        int destColBlock = 6;
        int falseCount = countFalse(arrLocation);
        if (falseCount == 1) {
            if (!arrLocation[destRowBlock][destColBlock]){ //checks if the destination block is the only unvisited block
                return true;
            }
        }
        return false;

    }


    public boolean finalState(boolean[][] arrLocation, int currentY, int currentX){ //checks if the function is at its final state & only dest is unvisited
        int destX = 3;
        int destY = 6;
        if((checkTrue(arrLocation)) && (Math.abs(currentX - destX) + Math.abs(currentY - destY) == 1)){
            return true;
        }
        return false;
    }

    public boolean indexOutOfBlock(boolean[][] arrLocation, int currentY, int currentX) { // checking if we are going out of the block
        int lowerEndX = -1, lowerEndY = -1;
        int upperEndX = 3, upperEndY = 9;
        if (currentY < lowerEndY || currentY >= upperEndY || currentX < lowerEndX || currentX >= upperEndX || visitedBlock[currentY][currentX]) {
            return true;
        }
        return false;
    }

    public void findPath(boolean[][] arrLocation, int currentY, int currentX){
//        System.out.println("PATH: " + path);
//        System.out.println("ARR LOCATION: " + arrLocation.toString());

        if (finalState(arrLocation, currentY, currentX)){ //if statement moved to a fxn
            arrLocation[3][6] = true;
            solutionFound = true;
            path.add("DIAGONAL");
            allPaths.add(new ArrayList<>(path));
            System.out.println("Solution FOUND!!"+ path);
            return;
        }

//        if (!indexOutOfBlock(arrLocation, currentY, currentX)){
//            // not out of block
//
//        }
        if (visitedBlock[currentY][currentX] == false){
            visitedBlock[currentY][currentX] = true;

            //Checking for all the possible steps
            checkAllPossibilities(currentX, currentY, arrLocation);

            visitedBlock[currentY][currentX] = false;
        }

    }


    public void checkAllPossibilities(int currentX, int currentY, boolean[][] arrLocation){
//        System.out.println("check possibilities");

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


    public int countFalse(boolean[][] arrLocation) { //returns the total number of false elements in the array
        int countFalseElements = 0;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 13; j++) {
                if (!arrLocation[i][j]) {
                    countFalseElements++;
                }
            }
        }
        return countFalseElements;
    }


    public static void main(String[] args) {
        Puzzle puzzle = new Puzzle();
        boolean[][] arr = puzzle.createInitialArray();

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 13; j++) {
                System.out.print(arr[i][j] ? "T " : "F "); // Print "T" for true and "F" for false
            }
            System.out.println(); // Move to the next line after printing each row
        }

        puzzle.findPath(arr, 4, 12);







//        if (puzzle.solutionFound){
//            List<String> solution = puzzle.allPaths.get(0);
//            System.out.println(solution);
//        }

//        puzzle.checkTrue(arrLocation);

//        System.out.println(puzzle.checkTrue(arrLocation));

    }
}

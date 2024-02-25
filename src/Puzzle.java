import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Puzzle {

    private List<String> path;
    private boolean[][] visitedBlock; //2D array keeping track of all the visited blocks
    private boolean solutionFound;
    private List<List<String>> allPaths; //all viable paths, that is the list of all the paths
    public Puzzle(){
        super();
        this.path = new ArrayList<>();
        int rowCount = 9;
        int colCount = 13;
        this.solutionFound = false;
        this.visitedBlock = new boolean[rowCount][colCount];  //TODO:  no magic numbers
        // Marking all blocks as false, that is as unvisited
        //TODO: Make a function
        for (int i = 0; i < rowCount; i++){
            for (int j = 0; j < colCount; j++){
                visitedBlock[i][j] = false;
            }
        }
        // Handling the poles
        //TODO: No magic numbers
        this.visitedBlock[3][4] = true;
        this.visitedBlock[3][8] = true;
        this.visitedBlock[6][6] = true;

        this.allPaths = new ArrayList<>();
    }

    public void findPath(boolean[][] arrLocation, int currentY, int currentX){
        System.out.println("arrLocation: "+ arrLocation);

        //TODO: Make the if condition readable
        if (checkTrue(arrLocation) && ((((currentX+1)==3) && (currentY==6)) ||(((currentX-1)==3)&& (currentY==6))||(((currentX)==3)&& ((currentY+1)==6))||(((currentX)==3)&& ((currentY-1)==6)) )){
            arrLocation[3][6] = true;
            solutionFound = true;
            path.add("DIAGONAL");
            allPaths.add(new ArrayList<>(path));
            System.out.println("Solution FOUND!!"+ path);
            return;
        }

        // TODO: Make it more readable
        //If the index is out of the block
        if (currentY < -1 || currentY >= 9 || currentX < -1 || currentX >= 13 || visitedBlock[currentY][currentX]) {
            return;
        }

        visitedBlock[currentY][currentX] = true;

        //Checking for all the possible steps
        //TODO: New function lookout
        if (currentY+1 < 9){
            path.add("DOWN");
            System.out.println(path);

            findPath(arrLocation, currentY + 1, currentX);
            path.remove(path.size() - 1);

        }
        if (currentY - 1 > -1) {
            path.add("UP");
            System.out.println(path);

            findPath(arrLocation, currentY - 1, currentX);
            path.remove(path.size() - 1);
        }
        if (currentX+1 < 13){
            path.add("RIGHT");
            System.out.println(path);

            findPath(arrLocation, currentY, currentX + 1);
            path.remove(path.size() - 1);

        }
        if (currentX-1 > -1){
            path.add("LEFT");
            System.out.println(path);
            findPath(arrLocation, currentY, currentX - 1);
            path.remove(path.size() - 1);

        }


        visitedBlock[currentY][currentX] = false;

    }

    public boolean checkTrue(boolean[][] arrLocation) {
        int destRowBlock = 3;
        int destColBlock = 6;
        int falseCount = countFalse(arrLocation);
        if (falseCount == 1) {
            if (arrLocation[destRowBlock][destColBlock] == false){ //checks if the destination block is the only unvisited block
                return true;
            }
        }
        return false;

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
        boolean[][] arrLocation = new boolean[9][13];
//        //Creating a 2d array of the size of the puzzle and making all index have false value,
//        //that is marking all the blocks as unvisited
//
//        for (int i = 0; i < 9; i++){
//            for (int j = 0; j < 13; j++){
//                arrLocation[i][j] = false;
//            }
//        }
//        System.out.println(arrLocation);
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 13; j++) {
                arrLocation[i][j] = false;
            }
        }
//        arrLocation[3][6] = false;

// Print the array
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 13; j++) {
                System.out.print(arrLocation[i][j] ? "T " : "F "); // Print "T" for true and "F" for false
            }
            System.out.println(); // Move to the next line after printing each row
        }




        Puzzle puzzle = new Puzzle();
        System.out.println(puzzle.checkTrue(arrLocation));
        puzzle.findPath(arrLocation, 4, 12);

        if (puzzle.solutionFound){
            List<String> solution = puzzle.allPaths.get(0);
            System.out.println(solution);
        }

//        puzzle.checkTrue(arrLocation);

        System.out.println(puzzle.checkTrue(arrLocation));

    }
}

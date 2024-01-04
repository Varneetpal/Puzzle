import java.util.ArrayList;
import java.util.List;

public class Puzzle {

    private List<String> path;
    private boolean[][] visitedBlock;
    private boolean solutionFound;
    private List<List<String>> allPaths; //all viable paths
    public Puzzle(){
        super();
        this.path = new ArrayList<>();
        this.solutionFound = false;
        this.visitedBlock = new boolean[9][13];
        // Marking all blocks as false, that is as unvisited
        for (int i = 0; i < 9; i++){
            for (int j = 0; j < 13; j++){
                visitedBlock[i][j] = false;
            }
        }
        // Handling the poles
        this.visitedBlock[3][4] = true;
        this.visitedBlock[3][8] = true;
        this.visitedBlock[6][6] = true;

        this.allPaths = new ArrayList<>();
    }

    public boolean checkTrue(boolean[][] arrLocation){
        int rowCount = 9;
        int colCount = 13;
        //Checking if all the blocks are covered and if the puzzle is at its last step,
        //that is where all the blocks are covered and oly the target block is not visited
        for (int i = 0; i < rowCount; i++){
            for (int j = 0; j<colCount; j++){
                if (i == 3 && j == 6){
                    if (arrLocation[i][j]){
                        return false;
                    }
                    else if(!arrLocation[i][j]){
                        return false;

                    }
                }
            }
        }
        return true;
    }



    public void findPath(boolean[][] arrLocation, int currentY, int currentX){
        System.out.println("arrLocation: "+ arrLocation);

        if (checkTrue(arrLocation) && ((((currentX+1)==3) && (currentY==6)) ||(((currentX-1)==3)&& (currentY==6))||(((currentX)==3)&& ((currentY+1)==6))||(((currentX)==3)&& ((currentY-1)==6)) )){
            arrLocation[3][6] = true;
            solutionFound = true;
            path.add("DIAGONAL");
            allPaths.add(new ArrayList<>(path));
            System.out.println("Solution FOUND!!"+ path);
            return;
        }

        if (currentY < -1 || currentY >= 9 || currentX < -1 || currentX >= 13 || visitedBlock[currentY][currentX]) {
            return;
        }

        visitedBlock[currentY][currentX] = true;

        //Checking for all the possible steps
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


    public static void main(String[] args) {
        boolean[][] arrLocation = new boolean[9][13];
        //Creating a 2d array of the size of the puzzle and making all index have false value,
        //that is marking all the blocks as unvisited

        for (int i = 0; i < 9; i++){
            for (int j = 0; j < 13; j++){
                arrLocation[i][j] = false;
            }
        }
//        arrLocation[1][6] = true;
//        arrLocation[3][4] = true;
//        arrLocation[3][8] = true;
//        arrLocation[6][6] = true;

        Puzzle puzzle = new Puzzle();
        puzzle.findPath(arrLocation, 4, 12);

        if (puzzle.solutionFound){
            List<String> solution = puzzle.allPaths.get(0);
            System.out.println(solution);
        }

//        puzzle.checkTrue(arrLocation);

        System.out.println(puzzle.checkTrue(arrLocation));

    }
}

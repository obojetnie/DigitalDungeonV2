/**
 * Created by Krzysio on 2016-09-01.
 */
public class Board {
    private int size;
    private char[][] board;
    private int maxCorridorLenght;
    private int minCorridorLength;
    private int maxRoomX;
    private int maxRoomY;
    private int corridorCount;

    public Board(int size) {
        corridorCount = 0;
        this.size = size;
        createEmptyBoard(size);
        generateLimits();
        generateCorridorWithEntrance();
        printBoard();
    }

    private void createEmptyBoard(int size){
        board = new char[size][size];
        for(int i=0; i<size; i++){
            for(int j=0; j<size; j++){
                board[i][j]=0;
            }
        }
    }

    private void generateLimits(){
        maxCorridorLenght = size/2;
        minCorridorLength = 3;
        maxRoomX = size/3;
        maxRoomY = size/3;
    }
    private void generateCorridorWithEntrance(){
        int side = (int)(Math.random()*1000%4);
        //0 - EAST, 1 - SOUTH, 2 - WEST, 3 - NORTH
        int start = (int)((Math.random()*1000%(size-2))+1);

        System.out.println("START: "+start + " side: "+side);

        switch (side){
            case 0:
                generateCorridor(0, start, Direction.EAST);
                break;
            case 1:
                generateCorridor(start, 0, Direction.SOUTH);
                break;
            case 2:
                generateCorridor(size-1, start, Direction.WEST);
                break;
            case 3:
                generateCorridor(start, size-1, Direction.NORTH);
                break;
        }
    }

    private void generateCorridor(int y, int x, Direction direction){
        corridorCount++;
        int corridorLength = (int)(Math.random()*1000%maxCorridorLenght);
        if(corridorLength<minCorridorLength){
            corridorLength=minCorridorLength;
        }
        System.out.println("Corridor length: "+corridorLength);

        board[y][x]=(char)corridorCount;
        for(int i=0; i<corridorLength-1; i++){
            if(direction.equals(Direction.EAST)){
                y++;
            }
            else if(direction.equals(Direction.SOUTH)){
                x++;
            }
            else if(direction.equals(Direction.WEST)){
                y--;
            }
            else if(direction.equals(Direction.NORTH)){
                x--;
            }
            board[y][x]=(char)corridorCount;
        }
    }

    private void printBoard(){
        for(int y=0; y<size; y++){
            for(int x=0; x<size; x++){
                System.out.print(Integer.toString(board[x][y]));
                System.out.print(" ");
            }
            System.out.println();
        }
        System.out.println("maxCorridorLength: "+maxCorridorLenght);
        System.out.println("maxRoom: "+maxRoomX+"x"+maxRoomY);
    }
}

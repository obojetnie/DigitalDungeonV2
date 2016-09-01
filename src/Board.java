/**
 * Created by Krzysio on 2016-09-01.
 */
public class Board {
    private int size;
    private char[][] board;
    private int maxCorridorLength;
    private int minCorridorLength;
    private int maxCorridorWidth;
    private int maxRoomX;
    private int maxRoomY;
    private int corridorCount;
    private int roomCount;

    public Board(int size) {
        corridorCount = 0;
        roomCount = 150;
        this.size = size;
        createEmptyBoard(size);
        generateLimits();
        generateCorridorWithEntrance();
        generateRoom(18,17,5,4);
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
        maxCorridorLength = size/2;
        minCorridorLength = 3;
        maxCorridorWidth = 3;
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
        int corridorLength = (int)(Math.random()*1000% maxCorridorLength);
        if(corridorLength<minCorridorLength){
            corridorLength=minCorridorLength;
        }
        System.out.println("Corridor length: "+corridorLength);

        int corridorWidth = (int)(Math.random()*1000%(maxCorridorWidth-1)+1);

        for(int i=0; i<corridorLength; i++){
            if(direction.equals(Direction.EAST)){
                board[y][x]=(char)corridorCount;
                if(corridorWidth>1) {
                    if (x < size / 2) {
                        board[y][x + 1] = (char) corridorCount;
                    }else{
                        board[y][x - 1] = (char) corridorCount;
                    }
                }
                if(corridorWidth>2) {
                    if (x < size / 2) {
                        board[y][x + 2] = (char) corridorCount;
                    }else{
                        board[y][x - 2] = (char) corridorCount;
                    }
                }
                y++;
            }
            else if(direction.equals(Direction.SOUTH)){
                board[y][x]=(char)corridorCount;
                if(corridorWidth>1) {
                    if (y < size / 2) {
                        board[y+1][x] = (char) corridorCount;
                    }else{
                        board[y-1][x] = (char) corridorCount;
                    }
                }
                if(corridorWidth>2) {
                    if (y < size / 2) {
                        board[y+2][x] = (char) corridorCount;
                    }else{
                        board[y-2][x] = (char) corridorCount;
                    }
                }
                x++;
            }
            else if(direction.equals(Direction.WEST)){
                board[y][x]=(char)corridorCount;
                if(corridorWidth>1) {
                    if (x < size / 2) {
                        board[y][x + 1] = (char) corridorCount;
                    }else{
                        board[y][x - 1] = (char) corridorCount;
                    }
                }
                if(corridorWidth>2) {
                    if (x < size / 2) {
                        board[y][x + 2] = (char) corridorCount;
                    }else{
                        board[y][x - 2] = (char) corridorCount;
                    }
                }
                y--;
            }
            else if(direction.equals(Direction.NORTH)){
                board[y][x]=(char)corridorCount;
                if(corridorWidth>1) {
                    if (y < size / 2) {
                        board[y+1][x] = (char) corridorCount;
                    }else{
                        board[y-1][x] = (char) corridorCount;
                    }
                }
                if(corridorWidth>2) {
                    if (y < size / 2) {
                        board[y+2][x] = (char) corridorCount;
                    }else{
                        board[y-2][x] = (char) corridorCount;
                    }
                }
                x--;
            }
        }
    }

    private void generateRoom(int y, int x, int width, int height){
        roomCount++;
        if(y<size/2 && x<size/2){
            //II-ga cwiartka
            for(int i=0; i<height; i++){
                for(int j=0; j<width; j++){
                    board[i+x][j+y] = (char)roomCount;
                }
            }
        }
        else if(y<size/2 && x>size/2){
            //I-ga cwiartka
            for(int i=0; i<height; i++){
                for(int j=0; j<width; j++){
                    board[x-width+i][j+y] = (char)roomCount;
                }
            }
        }
        else if(y>size/2 && x<size/2){
            //III-cia cwiartka
            for(int i=0; i<height; i++){
                for(int j=0; j<width; j++){
                    board[i+x][y-height+j] = (char)roomCount;
                }
            }
        }
        else if(y>size/2 && x>size/2){
            //IV-ta cwiartka
            for(int i=0; i<height; i++){
                for(int j=0; j<width; j++){
                    board[x-width+i][y-height+j] = (char)roomCount;
                }
            }
        }
    }

    private void printBoard(){
        for(int y=0; y<size; y++){
            for(int x=0; x<size; x++){
                System.out.printf("%02X",(int)board[x][y]);
                System.out.print(" ");
            }
            System.out.println();
        }
        System.out.println("maxCorridorLength: "+ maxCorridorLength);
        System.out.println("maxRoom: "+maxRoomX+"x"+maxRoomY);
    }
}

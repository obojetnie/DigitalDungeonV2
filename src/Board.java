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
    EndPoint endPoint;

    public Board(int size) {
        corridorCount = 1;
        roomCount = 150;
        endPoint = new EndPoint();
        this.size = size;
        createEmptyBoard(size);
        generateLimits();
        generateCorridorWithEntrance();
        generateCorridorMesh(15);
        //generateRoom(18,17,5,4);
        printBoard();
    }

    private void createEmptyBoard(int size) {
        board = new char[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                board[i][j] = 0;
            }
        }
    }

    private void generateLimits() {
        maxCorridorLength = size / 3;
        minCorridorLength = 3;
        maxCorridorWidth = 2;
        maxRoomX = size / 3;
        maxRoomY = size / 3;
    }

    private void generateCorridorWithEntrance() {
        int side = (int) (Math.random() * 1000 % 4);
        //0 - EAST, 1 - SOUTH, 2 - WEST, 3 - NORTH
        int start = (int) ((Math.random() * 1000 % (size - 2)) + 1);

        System.out.println("START: " + start + " side: " + side);

        switch (side) {
            case 0:
                generateCorridor(0, start, Direction.EAST);
                break;
            case 1:
                generateCorridor(start, 0, Direction.SOUTH);
                break;
            case 2:
                generateCorridor(size - 1, start, Direction.WEST);
                break;
            case 3:
                generateCorridor(start, size - 1, Direction.NORTH);
                break;
        }
    }

    private void generateCorridor(int y, int x, Direction direction) {
        int corridorLength = (int) (Math.random() * 1000 % maxCorridorLength);
        if (corridorLength < minCorridorLength) {
            corridorLength = minCorridorLength;
        }
        System.out.println("Corridor length: " + corridorLength);

        int corridorWidth = (int) (Math.random() * 1000 % maxCorridorWidth + 1);

        for (int i = 0; i < corridorLength; i++) {
            if (y < 0 || x < 0 || y > size - 1 || x > size - 1 || board[y][x] != 0) {
                endPoint.x = x;
                endPoint.y = y;
                endPoint.reverseAndSaveDirections(direction);
                return;
            }
            if (direction.equals(Direction.EAST)) {
                board[y][x] = (char) corridorCount;
                for (int j = 1; j < corridorWidth; j++) {
                    if (x < size / 2) {
                        board[y][x + j] = (char) corridorCount;
                    } else {
                        board[y][x - j] = (char) corridorCount;
                    }
                }
                y++;
            } else if (direction.equals(Direction.SOUTH)) {
                board[y][x] = (char) corridorCount;
                for (int j = 1; j < corridorWidth; j++) {
                    if (y < size / 2) {
                        board[y + j][x] = (char) corridorCount;
                    } else {
                        board[y - j][x] = (char) corridorCount;
                    }
                }
                x++;
            } else if (direction.equals(Direction.WEST)) {
                board[y][x] = (char) corridorCount;
                for (int j = 1; j < corridorWidth; j++) {
                    if (x < size / 2) {
                        board[y][x + j] = (char) corridorCount;
                    } else {
                        board[y][x - j] = (char) corridorCount;
                    }
                }
                y--;
            } else if (direction.equals(Direction.NORTH)) {
                board[y][x] = (char) corridorCount;
                for (int j = 1; j < corridorWidth; j++) {
                    if (y < size / 2) {
                        board[y + j][x] = (char) corridorCount;
                    } else {
                        board[y - j][x] = (char) corridorCount;
                    }
                }
                x--;
            }
        }
        endPoint.x = x;
        endPoint.y = y;
        endPoint.reverseAndSaveDirections(direction);
        corridorCount++;
    }

    private void generateCorridorMesh(int numberOfCorridorsToGenerate) {
        for (int i = 0; i < numberOfCorridorsToGenerate; i++) {
            nextCorridor();
        }
    }

    private void nextCorridor() {
        System.out.println(endPoint.x + " " + endPoint.y + " " + endPoint.whereWeCameFrom);
        Direction newDirection = endPoint.whereWeCameFrom;

        while (newDirection.equals(endPoint.whereWeCameFrom) || newDirection.equals(endPoint.whereWereWeGoing)) {
            int newDirectionNumber = (int) (Math.random() * 1000 % 10);
            //0 - EAST, 1 - SOUTH, 2 - WEST, 3 - NORTH
            switch (newDirectionNumber) {
                case 0:
                    newDirection = Direction.EAST;
                    break;
                case 1:
                    newDirection = Direction.SOUTH;
                    break;
                case 2:
                    newDirection = Direction.WEST;
                    break;
                case 3:
                    newDirection = Direction.NORTH;
                    break;
                case 4:
                case 5:
                case 6:
                    //System.out.println("HAHA1");
                    if (endPoint.y < size / 2 && endPoint.x < size / 2) {
                        //II-ga cwiartka
                        newDirection = Direction.SOUTH;
                    } else if (endPoint.y < size / 2 && endPoint.x > size / 2) {
                        //I-ga cwiartka
                        newDirection = Direction.WEST;
                    } else if (endPoint.y > size / 2 && endPoint.x < size / 2) {
                        //III-cia cwiartka
                        newDirection = Direction.EAST;
                    } else if (endPoint.y > size / 2 && endPoint.x > size / 2) {
                        //IV-ta cwiartka
                        newDirection = Direction.NORTH;
                    }
                    break;
                case 7:
                case 8:
                case 9:
                    //System.out.println("HAHA2");
                    if (endPoint.y < size / 2 && endPoint.x < size / 2) {
                        //II-ga cwiartka
                        newDirection = Direction.EAST;
                    } else if (endPoint.y < size / 2 && endPoint.x > size / 2) {
                        //I-ga cwiartka
                        newDirection = Direction.SOUTH;
                    } else if (endPoint.y > size / 2 && endPoint.x < size / 2) {
                        //III-cia cwiartka
                        newDirection = Direction.NORTH;
                    } else if (endPoint.y > size / 2 && endPoint.x > size / 2) {
                        //IV-ta cwiartka
                        newDirection = Direction.WEST;
                    }
                    break;
                default:
                    System.out.println("DUUUUUPAAAAAAAAAA");
                    newDirection = endPoint.whereWeCameFrom;
            }
        }
        System.out.println(newDirection);
        generateCorridor(endPoint.y, endPoint.x, newDirection);
    }

    private void generateRoom(int y, int x, int width, int height) {
        roomCount++;
        if (y < size / 2 && x < size / 2) {
            //II-ga cwiartka
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    board[i + x][j + y] = (char) roomCount;
                }
            }
        } else if (y < size / 2 && x > size / 2) {
            //I-ga cwiartka
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    board[x - width + i][j + y] = (char) roomCount;
                }
            }
        } else if (y > size / 2 && x < size / 2) {
            //III-cia cwiartka
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    board[i + x][y - height + j] = (char) roomCount;
                }
            }
        } else if (y > size / 2 && x > size / 2) {
            //IV-ta cwiartka
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    board[x - width + i][y - height + j] = (char) roomCount;
                }
            }
        }
    }

    private void printBoard() {
        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {
                if (board[x][y] == 0) {
                    System.out.print("__");
                } else {
                    System.out.printf("%02X", (int) board[x][y]);
                }
                System.out.print(" ");
            }
            System.out.println();
        }
        System.out.println("maxCorridorLength: " + maxCorridorLength);
        System.out.println("maxRoom: " + maxRoomX + "x" + maxRoomY);
    }
}

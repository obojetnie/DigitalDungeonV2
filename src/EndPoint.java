/**
 * Created by Krzysio on 2016-09-01.
 */
public class EndPoint {
    public int x;
    public int y;
    public Direction whereWeCameFrom;
    public Direction whereWereWeGoing;

    public void reverseAndSaveDirections(Direction direction){
        whereWereWeGoing = direction;
        if(direction.equals(Direction.SOUTH)){
            whereWeCameFrom = Direction.NORTH;
        }
        else if(direction.equals(Direction.NORTH)){
            whereWeCameFrom = Direction.SOUTH;
        }
        else if(direction.equals(Direction.EAST)){
            whereWeCameFrom = Direction.WEST;
        }
        else if(direction.equals(Direction.WEST)){
            whereWeCameFrom = Direction.EAST;
        }
    }
}

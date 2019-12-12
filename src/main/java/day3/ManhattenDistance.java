package day3;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
/**
 * This needs to be redone. Please don't look at it.
 * #shame
 */
public class ManhattenDistance {
    private WirePosition currentPosition;
    List<WirePosition> positions;

    public ManhattenDistance(WirePosition startPosition) {
        this.positions = new ArrayList<>();
        this.currentPosition = startPosition;
    }

    public void moveCommand(String command, int offset) {
        for(int i = 0 ; i < offset; i++) {
            switch(command){
                case "U":
                    currentPosition = currentPosition.moveUp();
                    break;
                case "D":
                    currentPosition = currentPosition.moveDown();
                    break;
                case"L":
                    currentPosition = currentPosition.moveLeft();
                    break;
                case "R":
                    currentPosition = currentPosition.moveRight();
                    break;
            }
            positions.add(currentPosition);
        }
    }

    public boolean commandIntersectWithFirstLine(WirePosition position) {
        return intersectionPoint(position).isPresent();
    }

    private Optional<WirePosition> intersectionPoint(WirePosition position) {
        return positions.stream().filter(p -> p.getX() == position.getX() && p.getY() == position.getY())
                .findFirst();
    }

    public WirePosition applyCommand(WirePosition position, String command, int offset) {
        for(int i = 0 ; i < offset; i++) {
            switch(command){
                case "U":
                    return position.moveUp();
                case "D":
                    return position.moveDown();
                case"L":
                    return position.moveLeft();
                case "R":
                    return position.moveRight();
            }
        }
        return null;
    }

    public int commandIntersectWithFirstLineIndex(WirePosition position) {
        for(int i = 0; i < positions.size(); i++) {
            if(positions.get(i).getX() == position.getX() &&
            positions.get(i).getY()  == position.getY()){
                return i;
            }
        }
        return -1;
    }
}

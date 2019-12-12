package day3;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * This needs to be redone. Please don't look at it.
 * #shame
 */
public class ManhattenDistanceTest {

    @Nested
    class TrackLinesTest {
        @Test
        public void trackLineUpMovement() {
            WirePosition p = WirePosition.defaultPosition();
            WirePosition result = p.moveUp();
            assertThat(result.getX()).isEqualTo(0);
            assertThat(result.getY()).isEqualTo(1);
        }
        @Test
        public void trackLineDownMovement() {
            WirePosition p = WirePosition.defaultPosition();
            WirePosition result = p.moveDown();
            assertThat(result.getX()).isEqualTo(0);
            assertThat(result.getY()).isEqualTo(-1);
        }
        @Test
        public void trackLineLeftMovement() {
            WirePosition p = WirePosition.defaultPosition();
            WirePosition result = p.moveLeft();
            assertThat(result.getX()).isEqualTo(-1);
            assertThat(result.getY()).isEqualTo(0);
        }

        @Test
        public void trackLineRightMovement() {
            WirePosition p = WirePosition.defaultPosition();
            WirePosition result = p.moveRight();
            assertThat(result.getX()).isEqualTo(1);
            assertThat(result.getY()).isEqualTo(0);
        }
        @Test
        public void trackLineWithTwoUPMovementsResultInListWithTwoPositions() {
            ManhattenDistance unitUnderTest =
                    new ManhattenDistance(WirePosition.defaultPosition());
            unitUnderTest.moveCommand("U",2);
            assertThat(unitUnderTest.positions.size()).isEqualTo(2);
        }
        @Test
        public void trackLineWithTwoDownMovementsResultInListWithTwoPositions() {
            ManhattenDistance unitUnderTest =
                    new ManhattenDistance(WirePosition.defaultPosition());
            unitUnderTest.moveCommand("D",2);
            assertThat(unitUnderTest.positions.size()).isEqualTo(2);
        }
        @Test
        public void trackLineWithTwoLEFTMovementsResultInListWithTwoPositions() {
            ManhattenDistance unitUnderTest =
                    new ManhattenDistance(WirePosition.defaultPosition());
            unitUnderTest.moveCommand("L",2);
            assertThat(unitUnderTest.positions.size()).isEqualTo(2);
        }
        @Test
        public void trackLineWithTwoRightMovementsResultInListWithTwoPositions() {
            ManhattenDistance unitUnderTest =
                    new ManhattenDistance(WirePosition.defaultPosition());
            unitUnderTest.moveCommand("R",2);
            assertThat(unitUnderTest.positions.size()).isEqualTo(2);
        }
    }

    @Test
    public void verification_star_one_example_one() {
       /* String[] commands = {"R75","D30","R83","U83","L12","D49","R71","U7","L72"};
        String[] commandLineTwo = {"U62","R66","U55","R34","D71","R55","D58","R83"};*/
        String[] commands = {"R98","U47","R26","D63","R33","U87","L62","D20","R33","U53","R51"};
        String[] commandLineTwo = {"U98","R91","D20","R16","D67","R40","U7","R15","U6","R7"};
        ManhattenDistance unitUnderTest = new ManhattenDistance(WirePosition.defaultPosition());
        for(String command: commands) {
            unitUnderTest.moveCommand(command.substring(0,1),Integer.parseInt(command.substring(1)));
        }
        int closestDistance = Integer.MAX_VALUE;
        int steps = Integer.MAX_VALUE;
        WirePosition position = WirePosition.defaultPosition();
        int x = 0;
        for(String command2: commandLineTwo){
            for(int i = 0; i < Integer.parseInt(command2.substring(1)); i++) {
                position = unitUnderTest.applyCommand(position, command2.substring(0, 1),1 );
                if(unitUnderTest.commandIntersectWithFirstLine(position)) {
                    int distance = Math.abs(position.getX()) + Math.abs(position.getY());
                    int newSteps = i + x + unitUnderTest.commandIntersectWithFirstLineIndex(position);
                    if(newSteps < steps) {
                        steps = newSteps;
                    }
                    if(distance < closestDistance) {
                        closestDistance = distance;
                    }
                }
            }
            x+= Integer.parseInt(command2.substring(1));
        }
        System.out.println(steps);
        System.out.println(closestDistance);
    }

    @Test
    public void verification_star_one() throws IOException {
     /*   String[] commands = {"R75","D30","R83","U83","L12","D49","R71","U7","L72"};
        String[] commandLineTwo = {"U62","R66","U55","R34","D71","R55","D58","R83"};*/
        Path inputPath = Paths.get("src/test/java/day3/input"); //Input must only have valid long values
        assertThat(Files.exists(inputPath)).isTrue();
        List<String> result = Files.readAllLines(inputPath);
        String[] commands = result.get(0).split(",");
        String[] commandLineTwo = result.get(1).split(",");
        ManhattenDistance unitUnderTest = new ManhattenDistance(WirePosition.defaultPosition());
        for(String command: commands) {
            unitUnderTest.moveCommand(command.substring(0,1),Integer.parseInt(command.substring(1)));
        }
        int closestDistance = Integer.MAX_VALUE;
        int steps = Integer.MAX_VALUE;
        WirePosition position = WirePosition.defaultPosition();
        int x = 0;
        for(String command2: commandLineTwo){
            for(int i = 0; i < Integer.parseInt(command2.substring(1)); i++) {
                position = unitUnderTest.applyCommand(position, command2.substring(0, 1),1 );
                if(unitUnderTest.commandIntersectWithFirstLine(position)) {
                    int distance = Math.abs(position.getX()) + Math.abs(position.getY());
                    int newSteps = i + x + unitUnderTest.commandIntersectWithFirstLineIndex(position);
                    if(newSteps < steps) {
                        steps = newSteps;
                    }
                    if(distance < closestDistance) {
                        closestDistance = distance;
                    }
                }
            }
            x+= Integer.parseInt(command2.substring(1));
        }
        System.out.println(steps);
        System.out.println(closestDistance);
    }
}

package day2;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class IntCodeComputerTest {

    private IntCodeComputer unitUnderTest = new IntCodeComputer();

    @Test
    public void processIntCode_With_NullInput_Throws_IllegalArgumentException() {
       assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() ->  unitUnderTest.processIntCode(null));
    }

    @Test
    public void proccessIntCode_With_Input_With_less_than_4_Throws_illegalArgumentException() {
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> unitUnderTest.processIntCode(new int[2]));
    }

    @Test
    public void proccessIntCode_With_Input_With_Addition1_1_plus_1_is_two_in_space_0() {
        int[] input = {1, 1, 1, 0};
        assertThat(unitUnderTest.processIntCode(input)[0]).isEqualTo(2);
    }

    @Test
    public void proccessIntCode_With_OpCode3_is_unknown() {
        int[] input = {3, 1, 1, 0};
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> unitUnderTest.processIntCode(input));
    }

    @Test
    public void proccessIntCode_With_Two_Addition_Inputs_Result_In_space_0_and_1() {
        int[] input = {1, 1, 1, 0, 1, 1, 1, 1};
        int[] result = unitUnderTest.processIntCode(input);
        assertThat(result[0]).isEqualTo(2);
        assertThat(result[1]).isEqualTo(2);
    }

    @Test
    public void proccessIntCode_With_Input_as_Multiply2_Results_in_space_0_equals_4() {
        int[] input = {2,2,2,0};
        assertThat(unitUnderTest.processIntCode(input)[0]).isEqualTo(4);
    }

    @Test
    public void proccessIntCode_With_Input_as_firstAddition_Than_multiply() {
        int[] input = {1,1,1,0, 2,0,0,1};
        int[] result = unitUnderTest.processIntCode(input);
        assertThat(result[0]).isEqualTo(2);
        assertThat(result[1]).isEqualTo(4);
    }

    @Test
    public void proccessIntCode_With_99_resultsInTermination() {
        int[] input = {99,2,2,2,0};
        assertThat(unitUnderTest.processIntCode(input)).isEqualTo(input);
    }

    @Test
    public void proccessIntCode_with_addition_than_halt_than_Multiply_only_executes_addition() {
        int[] input = {1,1,1,0,99, 2,0,0,1};
        int[] expectedResult = {2,1,1,0,99,2,0,0,1};
        int[] result = unitUnderTest.processIntCode(input);
        assertThat(result[0]).isEqualTo(2);
        assertThat(result).isEqualTo(expectedResult);
    }
    private void logValues(int[] intCode) {
        String s = "{";
        for (int i : intCode) {
            s += i + ",";
        }
        s += "}";
        System.out.println(s);
    }
    @Test
    public void verification_star_one() {
        IntCodeComputer intCodeComputer = new IntCodeComputer();
        int[] intCode = { 1,12,2,3,1,1,2,3,1,3,4,3,1,5,0,3,2,1,10,19,1,19,6,23,2,23,13,27,1,27,5,31,2,31,10,35,1,9,35,39,1,39,9,43,2,9,43,47,1,5,47,51,2,13,51,55,1,55,9,59,2,6,59,63,1,63,5,67,1,10,67,71,1,71,10,75,2,75,13,79,2,79,13,83,1,5,83,87,1,87,6,91,2,91,13,95,1,5,95,99,1,99,2,103,1,103,6,0,99,2,14,0,0};
        assertThat(intCodeComputer.processIntCode(intCode)[0]).isEqualTo(3790645);
    }

    @Test
    public void verification_example() {

        IntCodeComputer intCodeComputer = new IntCodeComputer();
        int[] intCode = { 1,12,2,3,1,1,2,3,1,3,4,3,1,5,0,3,2,1,10,19,1,19,6,23,2,23,13,27,1,27,5,31,2,31,10,35,1,9,35,39,1,39,9,43,2,9,43,47,1,5,47,51,2,13,51,55,1,55,9,59,2,6,59,63,1,63,5,67,1,10,67,71,1,71,10,75,2,75,13,79,2,79,13,83,1,5,83,87,1,87,6,91,2,91,13,95,1,5,95,99,1,99,2,103,1,103,6,0,99,2,14,0,0};
        assertThat(intCodeComputer.processIntCode(intCode)[0]).isEqualTo(1202);
    }
    @Test
    public void verification_star_two() {
        for(int noun = 0; noun < 99; noun ++) {
            for(int verb = 0; verb < 99; verb++) {
                IntCodeComputer intCodeComputer = new IntCodeComputer();
                int[] intCode = { 1,noun,verb,3,1,1,2,3,1,3,4,3,1,5,0,3,2,1,10,19,1,19,6,23,2,23,13,27,1,27,5,31,2,31,10,35,1,9,35,39,1,39,9,43,2,9,43,47,1,5,47,51,2,13,51,55,1,55,9,59,2,6,59,63,1,63,5,67,1,10,67,71,1,71,10,75,2,75,13,79,2,79,13,83,1,5,83,87,1,87,6,91,2,91,13,95,1,5,95,99,1,99,2,103,1,103,6,0,99,2,14,0,0};
                int[] result = intCodeComputer.processIntCode(intCode);
                if(result[0] == 19690720) {
                    System.out.println(noun +" " + verb);
                }
            }
        }
    }
}

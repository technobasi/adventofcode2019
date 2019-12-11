package day2;

public class IntCodeComputer {

    private static final int OFFSET = 4;

    public int[] processIntCode(int[] intCode) {
        if (intCode == null) {
            throw new IllegalArgumentException("intCode must not be null");
        }
        if (intCode.length < 4) {
            throw new IllegalArgumentException("intCode must have at least 4 values");
        }
        int times = intCode.length / OFFSET;
        for (int i = 0; i < times; i++) {
            int opCode = intCode[(OFFSET * i)];

            switch (opCode) {
                case 99:
                    return intCode;
                default:
                    manipulateIntCode(intCode,i,opCode);
            }
        }

        return intCode;
    }
    private void manipulateIntCode(int[] intCode, int index, int opCode) {
        int firstPosition = intCode[1 + (OFFSET * index)];
        int secondPosition = intCode[2 + (OFFSET * index)];
        int outputPosition = intCode[3 + (OFFSET * index)];

        switch(opCode) {
            case 1:
                intCode[outputPosition] = intCode[firstPosition] + intCode[secondPosition];
                break;
            case 2:
                intCode[outputPosition] = intCode[firstPosition] * intCode[secondPosition];
                break;
            default:
                throw new IllegalArgumentException("unknown opcode = " + opCode);
        }

    }
}

// 终端输入 java ParityBitAdder 1010011 0 运行
public class ParityBitAdder {
    public static void main(String[] args) {
        if (args.length != 2 || args[0].length() != 7 || !args[0].matches("[01]+") || !args[1].matches("[01]")) {
            System.out.println("Error: Invalid input. Please provide a 7-bit pattern and a parity type (0 or 1).");
            System.exit(1);
        }

        String bitPattern = args[0];
        int parityType = Integer.parseInt(args[1]);
        String result = calculateParity(bitPattern, parityType);
        System.out.println("Adding " + (parityType == 0 ? "even" : "odd") + " parity to '" + bitPattern +
                "' results in the binary pattern '" + result + "'.");
    }

    public static String calculateParity(String bitPattern, int parityType) {
        int onesCount = 0;
        for (int i = 0; i < bitPattern.length(); i++) {
            if (bitPattern.charAt(i) == '1') {
                onesCount++;
            }
        }

        boolean isEven = onesCount % 2 == 0;
        // If parity type is 0 (even), and ones count is already even, no need to add 1,
        // else add 1
        // If parity type is 1 (odd), and ones count is odd, no need to add 1, else add
        // 1
        char parityBit = (parityType == 0 == isEven) ? '0' : '1';

        return parityBit + bitPattern;
    }
}

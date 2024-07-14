
public class StringConverter {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Error: Please provide one input string.");
            System.exit(1);
        }
        
        String input = args[0];
        String output = convertString(input);
        System.out.println(output);
    }

    /**
     * Converts the input string based on the specified rules:
     * - Vowels are converted to lowercase.
     * - Consonants are converted to uppercase.
     * - Non-letter characters are converted to '*'.
     *
     * @param input The input string to be converted.
     * @return The converted string.
     */
    public static String convertString(String input) {
        StringBuilder converted = new StringBuilder();
        for (char c : input.toCharArray()) {
            if (isVowel(c)) {
                converted.append(Character.toLowerCase(c));
            } else if (Character.isLetter(c)) {
                converted.append(Character.toUpperCase(c));
            } else {
                converted.append('*');
            }
        }
        return converted.toString();
    }

    /**
     * Checks if a character is a vowel.
     *
     * @param c The character to check.
     * @return true if the character is a vowel, false otherwise.
     */
    private static boolean isVowel(char c) {
        return "aeiouyAEIOUY".indexOf(c) != -1;
    }
}

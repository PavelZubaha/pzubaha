package pzubaha.classes.inner.start;

/**
 * Chapter 2. OOP.
 * Lesson 7. Exceptions.
 *
 * Validating user input.
 * Solution of task 789.
 *
 * @author Pavel Zubaha (mailto:Apximar@gmail.com)
 * @since 29.06.17
 * @version 2
 */
public class ValidateInput extends ConsoleInput {
    /**
     * method for validate user input.
     * @param question - question prompt.
     * @param range - array of valid values.
     * @return valid inputed by user value.
     */
    public int ask(String question, int[] range) {
        int value = -1;
        boolean invalid = true;
        do {
            try {
                value = super.ask(question, range);
                invalid = false;
            } catch (MenuOutException moe) {
                System.out.println("Please Enter key form menu");
            } catch (NumberFormatException nfe) {
                System.out.println("Please enter correct value.");
            }
        } while (invalid);

        return value;
    }
}

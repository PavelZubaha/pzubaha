package pzubaha.collection.bank;

import static java.lang.String.format;

/**
 * Chapter_003. Collections. Lite.
 * Test task.
 * <p>
 * Contains solution of task 10038.
 * User class.
 * Created 12.10.2017.
 *
 * @author Pavel Zubaha (mailto:Apximar@gmail.com)
 * @version 1
 */
public class User {
    /**
     * User name.
     */
    private String name;
    /**
     * User passport.
     */
    private Passport passport;

    /**
     * Constructor for User.
     * @param passport user passport.
     */
    public User(Passport passport) {
        this.name = passport.getFirstName();
        this.passport = passport;
    }
    /**
     * Hash code method.
     * @return hash code.
     */
    @Override
    public int hashCode() {
        return passport.hashCode();
    }

    /**
     * Equals method.
     * @param obj another user object reference.
     * @return true if this user equals another, else false.
     */
    @Override
    public boolean equals(Object obj) {
        boolean result = false;
        if (this == obj) {
            result = true;
        } else if (obj != null && this.getClass() == obj.getClass()) {
            User other = (User) obj;
            result = passport.equals(other.getPassport());
        }
        return result;
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return this.passport.toString();
    }

    /**
     * User name getter.
     * @return name.
     */
    public String getName() {
        return name;
    }

    /**
     * User passport getter.
     * @return passport.
     */
    public Passport getPassport() {
        return passport;
    }

    /**
     * Class passport
     * for user identity.
     */
    static class Passport {
        /**
         * Passport id.
         */
        private int iD;
        /**
         * First name.
         */
        private final String firstName;
        /**
         * Second name.
         */
        private final String lastName;

        /**
         * Next id value.
         */
        private static int nextId = 1000000000;
        /**
         * Constructor for passport.
         * @param firstName user first name.
         * @param lastName user last name.
         */
        private Passport(String firstName, String lastName) {
            this.iD = nextId++;
            this.firstName = firstName;
            this.lastName = lastName;
        }

        /**
         * Default constructor.
         */
        private Passport() {
            this.iD = 0;
            this.firstName = "";
            this.lastName = "";
        }

        /**
         * Factory method for Passport.
         * @param firstName user first name.
         * @param lastName user last name.
         * @return passport instance reference.
         */
        public static Passport newPassport(String firstName, String lastName) {
            return firstName != null && lastName != null ? new Passport(firstName, lastName) : new Passport();
        }

        /**
         * getting id.
         * @return id.
         */
        public int getiD() {
            return iD;
        }

        /**
         * Getter for first name.
         * @return first name.
         */
        public String getFirstName() {
            return firstName;
        }

        /**
         * Getter last name.
         * @return last name.
         */
        public String getLastName() {
            return lastName;
        }

        /**
         * Equals method for passport.
         * @param obj another passport instance.
         * @return true if this passport equals another, and false if no.
         */
        @Override
        public boolean equals(Object obj) {
            boolean result = false;
            if (this == obj) {
                result = true;
            } else if (obj != null && this.getClass() == obj.getClass()) {
                Passport other = (Passport) obj;
                result = iD == other.getiD();
            }
            return result;
        }

        /**
         * Hash code for passport.
         * @return hash code.
         */
        @Override
        public int hashCode() {
            return iD;
        }

        /**
         * String representation of  passport.
         * @return passport as String.
         */
        @Override
        public String toString() {
            return format("First name: %s%nLast name: %s%nPassport №: %d", firstName, lastName, iD);
        }
    }
}

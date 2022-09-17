package assignment;

class RegisterIndex {

    // stores which register we are referencing
    private int n;

    public RegisterIndex(String argument) {
        if (argument == null || argument.length() == 0) {
            throw new IllegalArgumentException("Register index must be a non-empty string.");
        }

        if (argument.charAt(0) != 'r') {
            throw new IllegalArgumentException("Register index must begin with r.");
        }

        argument = argument.substring(1);

        try {
            this.n = Integer.parseInt(argument);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Register index must be an integer.");
        }

        if (n < 1 || n > Critter.REGISTERS) {
            throw new IllegalArgumentException("Register index must be between r1 and r10.");
        }
    }

    public int getIndex() {
        return n;
    }

    public String toString() {
        return "r" + n;
    }
}

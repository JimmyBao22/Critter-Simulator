package assignment;

class Bearing {

    private int n;

    public Bearing(String argument) {
        try {
            this.n = Integer.parseInt(argument);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Bearing must be an integer.");
        }

        if (n < 0 || n > 315 || n % 45 != 0) {
            throw new IllegalArgumentException("Bearing must be a multiple of 45 between 0 and 315.");
        }
    }

    public int getIntValue() {
        return n;
    }

    public String toString() {
        return Integer.toString(n);
    }
}

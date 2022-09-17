package assignment;

class InstructionJump {

    private boolean isRelative;
    private boolean isRegister;

    // the integer portion of the instruction jump
    private int n;

    public InstructionJump(String argument) {
        if (argument == null || argument.length() == 0) {
            throw new IllegalArgumentException("Instruction jump must be a non-empty string.");
        }

        // determine if the jump is relative
        if (argument.charAt(0) == '+' || argument.charAt(0) == '-') {
            this.isRelative = true;
            this.isRegister = false;
        }
        // determine if the jump is a register index
        else if (argument.charAt(0) == 'r') {
            this.isRelative = false;
            this.isRegister = true;
            // removes the r from the register index, while setting respective boolean to true
            argument = argument.substring(1);
        }
        // determine if the jump is an absolute jump
        else {
            this.isRelative = false;
            this.isRegister = false;
        }

        try {
            this.n = Integer.parseInt(argument);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid instruction jump integer portion.");
        }

        if (isRegister && (n < 1 || n > Critter.REGISTERS)) {
            throw new IllegalArgumentException("Register index must be between r1 and r10.");
        }
    }

    // gets the specific instruction line number after this jump
    public int getResultantLineNumber(Critter c) {
        if (isRelative) {
            return c.getNextCodeLine() + n;
        }
        else if (isRegister) {
            return c.getReg(n);
        }
        else {
            return n;
        }
    }

    public String toString() {
        if (isRelative) {
            if (n >= 0) {
                return "+" + n;
            } else {
                return Integer.toString(n);
            }
        } else if (isRegister) {
            return "r" + Integer.toString(n);
        } else {
            // absolute jump
            return Integer.toString(n);
        }
    }
}

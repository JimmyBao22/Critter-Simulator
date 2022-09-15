package assignment;

public class InstructionJump extends SpeciesInstructionArgument {
    private boolean isRelative;
    private boolean isRegister;

    int n;

    public InstructionJump(String argument) {
        verifyArgumentCanBeParsed(argument);
        // TODO verify that the argument can be parsed into a instruction delta/absolute, store it, otherwise return
    }

    public int getResultantLineNumber(Critter c) {
        if (isRelative) {
            return c.getNextCodeLine() + n;
        } else if (isRegister) {
            return c.getReg(n);
        } else {
            return n;
        }
    }

    boolean verifyArgumentCanBeParsed(String argument) {
        // TODO check if it is relative/absolute jump
        return false;
    }

}

package assignment;

public class RegisterIndex extends SpeciesInstructionArgument {
    public RegisterIndex(String argument) {
        verifyArgumentCanBeParsed(argument);
        // TODO verify that the argument can be parsed into a register, store it, otherwise return
    }

    boolean verifyArgumentCanBeParsed(String argument) {
        // TODO make sure register is between 1 and 10
        return false;
    }

}

package assignment;

public class Bearing extends SpeciesInstructionArgument {
    public Bearing(String argument) {
        verifyArgumentCanBeParsed(argument);
        // TODO verify that the argument can be parsed into a bearing, store it, otherwise return
    }

    boolean verifyArgumentCanBeParsed(String argument) {
        // TODO make sure that the bearing is a multiple of 45 between 0 and 315
        return false;
    }
}

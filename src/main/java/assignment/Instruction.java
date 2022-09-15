package assignment;

public abstract class Instruction {
    public abstract void run(Critter c);

    public abstract boolean isTerminatingInstruction();
}

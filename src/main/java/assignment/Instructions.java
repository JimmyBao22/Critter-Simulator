package assignment;

// The critter moves forward if the faced square is empty
class Hop extends Instruction {
    public Hop(String[] arguments) {
        if (arguments.length != 0) {
            throw new IllegalArgumentException("Hop does not take arguments");
        }
    }

    public void run(Critter c) {
        c.hop();
        c.setNextCodeLine(c.getNextCodeLine() + 1);
    }

    public boolean isTerminatingInstruction() {
        return true;
    }
}

// The critter turns left 45 degrees to face a new direction.
class Left extends Instruction {
    public Left(String[] arguments) {
        if (arguments.length != 0) {
            throw new IllegalArgumentException("Left does not take arguments");
        }
    }
    public void run(Critter c) {
        c.left();
        c.setNextCodeLine(c.getNextCodeLine() + 1);
    }

    public boolean isTerminatingInstruction() {
        return true;
    }
}

// The critter turns right 45 degrees to face a new direction.
class Right extends Instruction {
    public Right(String[] arguments) {
        if (arguments.length != 0) {
            throw new IllegalArgumentException("Right does not take arguments");
        }
    }

    public void run(Critter c) {
        c.right();
        c.setNextCodeLine(c.getNextCodeLine() + 1);
    }
    
    public boolean isTerminatingInstruction() {
        return true;
    }
}

/*
    If the square immediately in front of this critter is occupied by an enemy, the enemy
    critter is infected and becomes a member of the infecting critter’s species (converting
    an enemy to an ally). The infected critter retains the same position and heading, but
    behaves like the infecting species starting at the n
    th instruction. The parameter n is an
    optional parameter; if missing, the infected species begins executing its new program
    at the first instruction.
 */
class Infect extends Instruction {
    private final InstructionJump n;

    public Infect(String[] arguments) {
        if (arguments.length != 0 && arguments.length != 1) {
            throw new IllegalArgumentException("Infect takes an optional instruction jump");
        }

        if (arguments.length == 0) {
            n = new InstructionJump("1");
        } else {
            n = new InstructionJump(arguments[0]);
        }
    }

    public void run(Critter c) {
        c.infect(n.getResultantLineNumber(c));
        c.setNextCodeLine(c.getNextCodeLine() + 1);
    }
    
    public boolean isTerminatingInstruction() {
        return true;
    }
}

/*
    If the square immediately in front of this critter is occupied by an enemy, the enemy
    critter is lightly sauteed and treated as a comestible. After this turn the enemy critter
    no longer exists, and this critter gains a spurt of energy, taking two actions every step
    instead of 1 for a modest duration; the duration can be found as a constant in the Critter
    interface.
 */
class Eat extends Instruction {
    public Eat(String[] arguments) {
        if (arguments.length != 0) {
            throw new IllegalArgumentException("Eat does not take arguments");
        }
    }
    public void run(Critter c) {
        c.eat();
        c.setNextCodeLine(c.getNextCodeLine() + 1);
    }
    
    public boolean isTerminatingInstruction() {
        return true;
    }
}

/*
    This instruction jumps to another line in the behavior code. If n is a number preceded
    by a '+' or '-' character, then the jump is a relative jump, and execution should continue
    at the current instruction +/- n. If n is a number with no prefix, then the instruction is
    an absolute jump, and execution should continue at the n
    th instruction in the critter’s behavior code. If n is a register number, then the instruction is an absolute jump to
    the line number in the register. All instructions that perform jumps may specify the
    target line number using any of these notations.
 */
class Go extends Instruction {
    private final InstructionJump n;

    public Go(String[] arguments) {
        if (arguments.length != 1) {
            throw new IllegalArgumentException("Go takes one instruction jump");
        }

        n = new InstructionJump(arguments[0]);
    }

    public void run(Critter c) {
        // Set the next code line to the result of the instruction jump
        c.setNextCodeLine(n.getResultantLineNumber(c));
    }
    
    public boolean isTerminatingInstruction() {
        return false;
    }
}

/*
    This instruction randomly jumps to the nth instruction half of the time and is a no-op
    the rest of the time.
 */
class IfRandom extends Instruction {
    private final InstructionJump n;

    public IfRandom(String[] arguments) {
        if (arguments.length != 1) {
            throw new IllegalArgumentException("IfRandom takes one instruction jump");
        }

        n = new InstructionJump(arguments[0]);
    }

    public void run(Critter c) {
        if (Math.random() < 0.5) {
            // jump to the nth instruction
            c.setNextCodeLine(n.getResultantLineNumber(c));
        } else {
            // otherwise continue as usual
            c.setNextCodeLine(c.getNextCodeLine() + 1);
        }
    }
    
    public boolean isTerminatingInstruction() {
        return false;
    }
}

/*
    If the critter is currently either hungry or starving, the critter will jump to the nth instruction.
 */
class IfHungry extends Instruction {
    private final InstructionJump n;

    public IfHungry(String[] arguments) {
        if (arguments.length != 1) {
            throw new IllegalArgumentException("IfHungry takes one instruction jump");
        }

        n = new InstructionJump(arguments[0]);
    }

    public void run(Critter c) {
        if (c.getHungerLevel() != Critter.HungerLevel.SATISFIED) {
            // either hungry or starving
            c.setNextCodeLine(n.getResultantLineNumber(c));
        } else {
            c.setNextCodeLine(c.getNextCodeLine() + 1);
        }
    }
    
    public boolean isTerminatingInstruction() {
        return false;
    }
}

/*
    If the critter is currently starving (as in, the explicit starving condition—if the critter is only in the hungry condition,
    it doesn't count) the critter will jump to the nth instruction
 */
class IfStarving extends Instruction {
    private final InstructionJump n;

    public IfStarving(String[] arguments) {
        if (arguments.length != 1) {
            throw new IllegalArgumentException("IfStarving takes one instruction jump");
        }

        n = new InstructionJump(arguments[0]);
    }

    public void run(Critter c) {
        if (c.getHungerLevel() == Critter.HungerLevel.STARVING) {
            // either hungry or starving
            c.setNextCodeLine(n.getResultantLineNumber(c));
        } else {
            c.setNextCodeLine(c.getNextCodeLine() + 1);
        }
    }
    
    public boolean isTerminatingInstruction() {
        return false;
    }
}

/*
    The first parameter, b, is a bearing. If the adjacent square at the specified bearing is
    unoccupied, the critter will jump to the nth instruction.
 */
class IfEmpty extends Instruction {
    private final Bearing b;
    private final InstructionJump n;

    public IfEmpty(String[] arguments) {
        if (arguments.length != 2) {
            throw new IllegalArgumentException("IfRandom takes one bearing and one instruction jump");
        }

        b = new Bearing(arguments[0]);
        n = new InstructionJump(arguments[1]);
    }

    public void run(Critter c) {
        if (c.getCellContent(b.getIntValue()) == Critter.EMPTY) {
            // either hungry or starving
            c.setNextCodeLine(n.getResultantLineNumber(c));
        } else {
            c.setNextCodeLine(c.getNextCodeLine() + 1);
        }
    }
    
    public boolean isTerminatingInstruction() {
        return false;
    }
}

/*
    If the adjacent square at the specified bearing b is occupied by a critter of the same
    species, the critter will jump to the n
    th instruction; otherwise, continue execution with
    the next instruction.
 */
class IfAlly extends Instruction {
    private final Bearing b;
    private final InstructionJump n;

    public IfAlly(String[] arguments) {
        if (arguments.length != 2) {
            throw new IllegalArgumentException("ifAlly takes one bearing and one instruction jump");
        }

        b = new Bearing(arguments[0]);
        n = new InstructionJump(arguments[1]);
    }

    public void run(Critter c) {
        if (c.getCellContent(b.getIntValue()) == Critter.ALLY) {
            // either hungry or starving
            c.setNextCodeLine(n.getResultantLineNumber(c));
        } else {
            c.setNextCodeLine(c.getNextCodeLine() + 1);
        }
    }

    public boolean isTerminatingInstruction() {
        return false;
    }
}

/*
    If the adjacent square at the specified bearing is occupied by a critter of a different
    species, execution will jump to the n
    th instruction; otherwise, continue execution with
    the next instruction
 */
class IfEnemy extends Instruction {
    private final Bearing b;
    private final InstructionJump n;

    public IfEnemy(String[] arguments) {
        if (arguments.length != 2) {
            throw new IllegalArgumentException("IfEnemy takes one bearing and one instruction jump");
        }

        b = new Bearing(arguments[0]);
        n = new InstructionJump(arguments[1]);
    }

    public void run(Critter c) {
        if (c.getCellContent(b.getIntValue()) == Critter.ENEMY) {
            // either hungry or starving
            c.setNextCodeLine(n.getResultantLineNumber(c));
        } else {
            c.setNextCodeLine(c.getNextCodeLine() + 1);
        }
    }

    public boolean isTerminatingInstruction() {
        return false;
    }
}

/*
    If the critter is adjacent to the border of the world at the specified bearing (that is,
    there are no more squares in that direction), the critter will jump to the n
    th instruction;
    otherwise, continue execution with the next instruction
 */
class IfWall extends Instruction {
    private final Bearing b;
    private final InstructionJump n;

    public IfWall(String[] arguments) {
        if (arguments.length != 2) {
            throw new IllegalArgumentException("IfWall takes one bearing and one instruction jump");
        }

        b = new Bearing(arguments[0]);
        n = new InstructionJump(arguments[1]);
    }

    public void run(Critter c) {
        if (c.getCellContent(b.getIntValue()) == Critter.WALL) {
            // either hungry or starving
            c.setNextCodeLine(n.getResultantLineNumber(c));
        } else {
            c.setNextCodeLine(c.getNextCodeLine() + 1);
        }
    }

    public boolean isTerminatingInstruction() {
        return false;
    }

    public String toString() {
        return "ifwall " + b.toString() + " " + n.toString();
    }
}

/*
    If the off-angle (difference in heading) between the executing critter and the critter at
    bearing b1 matches bearing b2, the critter will jump to the n
    th instruction; otherwise,
    continue execution with the next instruction.
 */
class IfAngle extends Instruction {
    private final Bearing b1;
    private final Bearing b2;
    private final InstructionJump n;

    public IfAngle(String[] arguments) {
        if (arguments.length != 3) {
            throw new IllegalArgumentException("IfAngle takes two bearings and one instruction jump");
        }

        b1 = new Bearing(arguments[0]);
        b2 = new Bearing(arguments[1]);
        n = new InstructionJump(arguments[2]);
    }

    public void run(Critter c) {
        int offAngle = c.getOffAngle(b1.getIntValue());
        if (offAngle == b2.getIntValue()) {
            // either hungry or starving
            c.setNextCodeLine(n.getResultantLineNumber(c));
        } else {
            c.setNextCodeLine(c.getNextCodeLine() + 1);
        }
    }

    public boolean isTerminatingInstruction() {
        return false;
    }

    public String toString() {
        return "ifangle " + b1.toString() + " " + b2.toString() + " " + n.toString();
    }
}

// Write the integer v into register r.
class Write extends Instruction {
    private final RegisterIndex r;
    private final Integer v;

    public Write(String[] arguments) {
        if (arguments.length != 2) {
            throw new IllegalArgumentException("Write takes one register index and one integer");
        }

        r = new RegisterIndex(arguments[0]);
        v = Integer.parseInt(arguments[1]);
    }

    public void run(Critter c) {
        c.setReg(r.getIndex(), v);
        c.setNextCodeLine(c.getNextCodeLine() + 1);
    }

    public boolean isTerminatingInstruction() {
        return false;
    }

    public String toString() {
        return "write " + r.toString() + " " + v.toString();
    }
}

// Add the value of register r2 to that of r1 and store the result in r1.
class Add extends Instruction {
    private final RegisterIndex r1;
    private final RegisterIndex r2;

    public Add(String[] arguments) {
        if (arguments.length != 2) {
            throw new IllegalArgumentException("Add takes two register indices");
        }

        r1 = new RegisterIndex(arguments[0]);
        r2 = new RegisterIndex(arguments[1]);
    }

    public void run(Critter c) {
        int result = c.getReg(r1.getIndex()) + c.getReg(r2.getIndex());
        c.setReg(r1.getIndex(), result);
        c.setNextCodeLine(c.getNextCodeLine() + 1);
    }

    public boolean isTerminatingInstruction() {
        return false;
    }

    public String toString() {
        return "add " + r1.toString() + " " + r2.toString();
    }
}

// Subtract the value of register r2 from that of r1 and store the result in r1.
class Sub extends Instruction {
    private final RegisterIndex r1;
    private final RegisterIndex r2;

    public Sub(String[] arguments) {
        if (arguments.length != 2) {
            throw new IllegalArgumentException("Sub takes two register indices");
        }

        r1 = new RegisterIndex(arguments[0]);
        r2 = new RegisterIndex(arguments[1]);
    }

    public void run(Critter c) {
        int result = c.getReg(r1.getIndex()) - c.getReg(r2.getIndex());
        c.setReg(r1.getIndex(), result);
        c.setNextCodeLine(c.getNextCodeLine() + 1);
    }

    public boolean isTerminatingInstruction() {
        return false;
    }

    public String toString() {
        return "sub " + r1.toString() + " " + r2.toString();
    }
}

// Increment the value of register r1.
class Inc extends Instruction {
    private final RegisterIndex r1;

    public Inc(String[] arguments) {
        if (arguments.length != 1) {
            throw new IllegalArgumentException("Inc takes one register index");
        }

        r1 = new RegisterIndex(arguments[0]);
    }

    public void run(Critter c) {
        int result = c.getReg(r1.getIndex()) + 1;
        c.setReg(r1.getIndex(), result);
        c.setNextCodeLine(c.getNextCodeLine() + 1);
    }

    public boolean isTerminatingInstruction() {
        return false;
    }

    public String toString() {
        return "inc " + r1.toString();
    }
}

// Decrement the value of register r1.
class Dec extends Instruction {
    private final RegisterIndex r1;

    public Dec(String[] arguments) {
        if (arguments.length != 1) {
            throw new IllegalArgumentException("Dec takes one register index");
        }

        r1 = new RegisterIndex(arguments[0]);
    }

    public void run(Critter c) {
        int result = c.getReg(r1.getIndex()) - 1;
        c.setReg(r1.getIndex(), result);
        c.setNextCodeLine(c.getNextCodeLine() + 1);
    }

    public boolean isTerminatingInstruction() {
        return false;
    }

    public String toString() {
        return "dec " + r1.toString();
    }
}

/*
    If the value of register r1 is less than the value of r2, jump to the n
    th instruction;
    otherwise continue execution with the next instruction.
 */
class IfLt extends Instruction {
    private final RegisterIndex r1;
    private final RegisterIndex r2;
    private final InstructionJump n;

    public IfLt(String[] arguments) {
        if (arguments.length != 3) {
            throw new IllegalArgumentException("IfLt takes two register indices and one instruction jump");
        }

        r1 = new RegisterIndex(arguments[0]);
        r2 = new RegisterIndex(arguments[1]);
        n = new InstructionJump(arguments[2]);
    }

    public void run(Critter c) {
        if (c.getReg(r1.getIndex()) < c.getReg(r2.getIndex())) {
            c.setNextCodeLine(n.getResultantLineNumber(c));
        } else {
            c.setNextCodeLine(c.getNextCodeLine() + 1);
        }
    }

    public boolean isTerminatingInstruction() {
        return false;
    }

    public String toString() {
        return "iflt " + r1.toString() + " " + r2.toString() + " " + n.toString();
    }
}

/*
    If the value of register r1 is equal to the value of r2, jump to the n
    th instruction;
    otherwise continue execution with the next instruction
 */
class IfEq extends Instruction {
    private final RegisterIndex r1;
    private final RegisterIndex r2;
    private final InstructionJump n;

    public IfEq(String[] arguments) {
        if (arguments.length != 3) {
            throw new IllegalArgumentException("IfEq takes two register indices and one instruction jump");
        }

        r1 = new RegisterIndex(arguments[0]);
        r2 = new RegisterIndex(arguments[1]);
        n = new InstructionJump(arguments[2]);
    }

    public void run(Critter c) {
        if (c.getReg(r1.getIndex()) == c.getReg(r2.getIndex())) {
            c.setNextCodeLine(n.getResultantLineNumber(c));
        } else {
            c.setNextCodeLine(c.getNextCodeLine() + 1);
        }
    }

    public boolean isTerminatingInstruction() {
        return false;
    }

    public String toString() {
        return "ifeq " + r1.toString() + " " + r2.toString() + " " + n.toString();
    }
}

/*
    If the value of register r1 is greater than the value of r2, jump to the n
    th instruction;
    otherwise continue execution with the next instruction
 */
class IfGt extends Instruction {
    private final RegisterIndex r1;
    private final RegisterIndex r2;
    private final InstructionJump n;

    public IfGt(String[] arguments) {
        if (arguments.length != 3) {
            throw new IllegalArgumentException("IfGt takes two register indices and one instruction jump");
        }

        r1 = new RegisterIndex(arguments[0]);
        r2 = new RegisterIndex(arguments[1]);
        n = new InstructionJump(arguments[2]);
    }

    public void run(Critter c) {
        if (c.getReg(r1.getIndex()) > c.getReg(r2.getIndex())) {
            c.setNextCodeLine(n.getResultantLineNumber(c));
        } else {
            c.setNextCodeLine(c.getNextCodeLine() + 1);
        }
    }

    public boolean isTerminatingInstruction() {
        return false;
    }

    public String toString() {
        return "ifgt " + r1.toString() + " " + r2.toString() + " " + n.toString();
    }
}
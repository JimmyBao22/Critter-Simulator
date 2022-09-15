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
    }
}

// The critter turns right 45 degrees to face a new direction.
class Right extends Instruction {
    public Right(String[] arguments) {
        if (arguments != null && arguments.length != 0) {
            throw new IllegalArgumentException("Right does not take arguments");
        }
    }

    public void run(Critter c) {
        c.left();
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
    private InstructionJump n;

    public Infect(String[] arguments) {
        if (arguments == null || arguments.length != 1) {
            throw new IllegalArgumentException("Infect takes one instruction jump");
        }

        n = new InstructionJump(arguments[0]);
    }

    public void run(Critter c) {
        c.infect();
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
        if (arguments == null || arguments.length != 0) {
            throw new IllegalArgumentException("Eat does not take arguments");
        }
    }
    public void run(Critter c) {
        c.eat();
    }
}

/*
    This instruction jumps to another line in the behavior code. If n is a number preceded
    by a ’+’ or ’-’ character, then the jump is a relative jump, and execution should continue
    at the current instruction +/- n. If n is a number with no prefix, then the instruction is
    an absolute jump, and execution should continue at the n
    th instruction in the critter’s behavior code. If n is a register number, then the instruction is an absolute jump to
    the line number in the register. All instructions that perform jumps may specify the
    target line number using any of these notations.
 */
class Go extends Instruction {
    private InstructionJump n;

    public Go(String[] arguments) {
        if (arguments == null || arguments.length != 1) {
            throw new IllegalArgumentException("Go takes one instruction jump");
        }

        n = new InstructionJump(arguments[0]);
    }

    public void run(Critter c) {
        // TODO
    }
}

/*
    This instruction randomly jumps to the nth instruction half of the time and is a no-op
    the rest of the time.
 */
class IfRandom extends Instruction {
    private InstructionJump n;

    public IfRandom(String[] arguments) {
        if (arguments == null || arguments.length != 1) {
            throw new IllegalArgumentException("IfRandom takes one instruction jump");
        }

        n = new InstructionJump(arguments[0]);
    }

    public void run(Critter c) {
        // TODO
    }
}

/*
    If the critter is currently either hungry or starving, the critter will jump to the nth instruction.
 */
class IfHungry extends Instruction {
    private InstructionJump n;

    public IfHungry(String[] arguments) {
        if (arguments == null || arguments.length != 1) {
            throw new IllegalArgumentException("IfHungry takes one instruction jump");
        }

        n = new InstructionJump(arguments[0]);
    }

    public void run(Critter c) {
        // TODO
    }
}

/*
    If the critter is currently starving (as in, the explicit starving condition—if the critter is only in the hungry condition,
    it doesn’t count) the critter will jump to the nth instruction
 */
class IfStarving extends Instruction {
    private InstructionJump n;

    public IfStarving(String[] arguments) {
        if (arguments == null || arguments.length != 1) {
            throw new IllegalArgumentException("IfStarving takes one instruction jump");
        }

        n = new InstructionJump(arguments[0]);
    }

    public void run(Critter c) {
        // TODO
    }
}

/*
    The first parameter, b, is a bearing. If the adjacent square at the specified bearing is
    unoccupied, the critter will jump to the nth instruction.
 */
class IfEmpty extends Instruction {
    private Bearing b;
    private InstructionJump n;

    public IfEmpty(String[] arguments) {
        if (arguments == null || arguments.length != 2) {
            throw new IllegalArgumentException("IfRandom takes one bearing and one instruction jump");
        }

        b = new Bearing(arguments[0]);
        n = new InstructionJump(arguments[1]);
    }

    public void run(Critter c) {
        // TODO
    }
}

/*
    If the adjacent square at the specified bearing is occupied by a critter of the same
    species, the critter will jump to the n
    th instruction; otherwise, continue execution with
    the next instruction.
 */
class IfAlly extends Instruction {
    private Bearing b;
    private InstructionJump n;

    public IfAlly(String[] arguments) {
        if (arguments == null || arguments.length != 2) {
            throw new IllegalArgumentException("ifAlly takes one bearing and one instruction jump");
        }

        b = new Bearing(arguments[0]);
        n = new InstructionJump(arguments[1]);
    }

    public void run(Critter c) {
        // TODO
    }
}

/*
    If the adjacent square at the specified bearing is occupied by a critter of a different
    species, execution will jump to the n
    th instruction; otherwise, continue execution with
    the next instruction
 */
class IfEnemy extends Instruction {
    private Bearing b;
    private InstructionJump n;

    public IfEnemy(String[] arguments) {
        if (arguments == null || arguments.length != 2) {
            throw new IllegalArgumentException("IfEnemy takes one bearing and one instruction jump");
        }

        b = new Bearing(arguments[0]);
        n = new InstructionJump(arguments[1]);
    }

    public void run(Critter c) {
        // TODO
    }
}

/*
    If the critter is adjacent to the border of the world at the specified bearing (that is,
    there are no more squares in that direction), the critter will jump to the n
    th instruction;
    otherwise, continue execution with the next instruction
 */
class IfWall extends Instruction {
    private Bearing b;
    private InstructionJump n;

    public IfWall(String[] arguments) {
        if (arguments == null || arguments.length != 2) {
            throw new IllegalArgumentException("IfWall takes one bearing and one instruction jump");
        }

        b = new Bearing(arguments[0]);
        n = new InstructionJump(arguments[1]);
    }

    public void run(Critter c) {
        // TODO
    }
}

/*
    If the off-angle (difference in heading) between the executing critter and the critter at
    bearing b1 matches bearing b2, the critter will jump to the n
    th instruction; otherwise,
    continue execution with the next instruction.
 */
class IfAngle extends Instruction {
    private Bearing b1, b2;
    private InstructionJump n;

    public IfAngle(String[] arguments) {
        if (arguments == null || arguments.length != 3) {
            throw new IllegalArgumentException("IfAngle takes two bearings and one instruction jump");
        }

        b1 = new Bearing(arguments[0]);
        b2 = new Bearing(arguments[1]);
        n = new InstructionJump(arguments[2]);
    }

    public void run(Critter c) {
        // TODO
    }
}

// Write the integer v into register r.
class Write extends Instruction {
    private RegisterIndex r;
    private Integer v;

    public Write(String[] arguments) {
        if (arguments == null || arguments.length != 2) {
            throw new IllegalArgumentException("Write takes one register index and one integer");
        }

        r = new RegisterIndex(arguments[0]);
        v = new Integer(arguments[1]);
    }

    public void run(Critter c) {
        // TODO
    }
}

// Add the value of register r2 to that of r1 and store the result in r1.
class Add extends Instruction {
    private RegisterIndex r1, r2;

    public Add(String[] arguments) {
        if (arguments == null || arguments.length != 2) {
            throw new IllegalArgumentException("Add takes two register indices");
        }

        r1 = new RegisterIndex(arguments[0]);
        r2 = new RegisterIndex(arguments[1]);
    }

    public void run(Critter c) {
        // TODO
    }
}

// Subtract the value of register r2 from that of r1 and store the result in r1.
class Sub extends Instruction {
    private RegisterIndex r1, r2;

    public Sub(String[] arguments) {
        if (arguments == null || arguments.length != 2) {
            throw new IllegalArgumentException("Sub takes two register indices");
        }

        r1 = new RegisterIndex(arguments[0]);
        r2 = new RegisterIndex(arguments[1]);
    }

    public void run(Critter c) {
        // TODO
    }
}

// Increment the value of register r1.
class Inc extends Instruction {
    private RegisterIndex r1;

    public Inc(String[] arguments) {
        if (arguments == null || arguments.length != 1) {
            throw new IllegalArgumentException("Inc takes one register index");
        }

        r1 = new RegisterIndex(arguments[0]);
    }

    public void run(Critter c) {
        // TODO
    }
}

// Decrement the value of register r1.
class Dec extends Instruction {
    private RegisterIndex r1;

    public Dec(String[] arguments) {
        if (arguments == null || arguments.length != 1) {
            throw new IllegalArgumentException("Dec takes one register index");
        }

        r1 = new RegisterIndex(arguments[0]);
    }

    public void run(Critter c) {
        // TODO
    }
}

/*
    If the value of register r1 is less than the value of r2, jump to the n
    th instruction;
    otherwise continue execution with the next instruction.
 */
class IfLt extends Instruction {
    private RegisterIndex r1, r2;
    private InstructionJump n;

    public IfLt(String[] arguments) {
        if (arguments == null || arguments.length != 3) {
            throw new IllegalArgumentException("IfLt takes two register indices and one instruction jump");
        }

        r1 = new RegisterIndex(arguments[0]);
        r2 = new RegisterIndex(arguments[1]);
        n = new InstructionJump(arguments[2]);
    }

    public void run(Critter c) {
        // TODO
    }
}

/*
    If the value of register r1 is equal to the value of r2, jump to the n
    th instruction;
    otherwise continue execution with the next instruction
 */
class IfEq extends Instruction {
    private RegisterIndex r1, r2;
    private InstructionJump n;

    public IfEq(String[] arguments) {
        if (arguments == null || arguments.length != 3) {
            throw new IllegalArgumentException("IfEq takes two register indices and one instruction jump");
        }

        r1 = new RegisterIndex(arguments[0]);
        r2 = new RegisterIndex(arguments[1]);
        n = new InstructionJump(arguments[2]);
    }

    public void run(Critter c) {
        // TODO
    }
}

/*
    If the value of register r1 is greater than the value of r2, jump to the n
    th instruction;
    otherwise continue execution with the next instruction
 */
class IfGt extends Instruction {
    private RegisterIndex r1, r2;
    private InstructionJump n;

    public IfGt(String[] arguments) {
        if (arguments == null || arguments.length != 3) {
            throw new IllegalArgumentException("IfGt takes two register indices and one instruction jump");
        }

        r1 = new RegisterIndex(arguments[0]);
        r2 = new RegisterIndex(arguments[1]);
        n = new InstructionJump(arguments[2]);
    }

    public void run(Critter c) {
        // TODO
    }
}
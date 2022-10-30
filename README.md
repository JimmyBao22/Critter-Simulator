# Critter-Simulator

**Goal**: Simulate critters that behave according to some well-defined set of instructions.

The simulated world is a 2D grid of squares, where each square can hold one critter. Each critter has a graphical
representation, has a heading, has a hunger level, belongs to a particular species, and behaves according to a set of
species-specific instructions. After some random initial placement of critters, each critter takes a turn at performing
some actions, such as moving to another square or eating another critter.

Each critter has a simulated hunger level, which increases the longer the critter goes without eating another critter.
If a critter goes a while without eating, it becomes hungry and can only act every other tick. If a critters goes without
eating for a very long while, it becomes starving and can only act every third tick.

Terminology:
- A heading is the direction a critter is facing. Measured in degrees, the possible headings are 0, 45, 90, 135, 180,
225, 270, and 315.
- A bearing is an angular difference between two headings, measured in degrees clockwise. The possible bearings
are 0, 45, 90, 135, 180, 225, 270, and 315.
- An enemy of a critter is any critter of a different species.
- An ally of a critter is any critter of the same species.
- A behavior file is a file that contains the specification of a critter species’ behavior. Similarly, the behavior code
is the set of instructions in a behavior file that controls the species’ behavior.
- A register is one of ten registers, named r1 though r10, that can hold an integral value. Note: If you do not
name your registers as specified, your program will be incorrect. In particular, the r is part of the register name.
- The hunger level of a critter is one of satisfied, hungry, or starving. Hungry critters can only act
every other tick, while starving critters can only act every third tick. By default, a critter becomes hungry if it
doesn’t eat something in 100 ticks, and it becomes starving it if doesn’t eat something in 300 ticks.

A critter executes any number of if, go or state-manipulation instructions without relinquishing its turn. That is,
the instructions listed on this page do not terminate a turn. A critter’s turn ends when it executes any of the following
instructions: hop, left, right, infect, or eat. On subsequent turns, the critter resumes execution from the
point at which its previous turn ended. If for any reason a critter tries to resume execution at a line number outside the
bounds of its program, it simply does nothing for the remainder of its turns

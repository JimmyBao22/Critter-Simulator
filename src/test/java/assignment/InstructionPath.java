package assignment;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class InstructionPath {
    static private class TreeNode {
        private Instruction instruction;
        private TreeNode nextInstrIfTrue;
        private TreeNode nextInstrIfFalse;
        private int[] registerValues;

        TreeNode(String instr, int[] previousRegisters) {
            instruction = Instruction.makeInstruction(instr);
            nextInstrIfTrue = null;
            nextInstrIfFalse = null;

            // handle register modification if it occurs
            modifyRegisters(previousRegisters);
        }

        TreeNode getNextNode() {
            return getNextNode(true);
        }

        TreeNode getNextNode(boolean condition) {
            return condition ? nextInstrIfTrue : nextInstrIfFalse;
        }

        void modifyRegisters(int[] previousRegisters) {
            if (instruction.modifiesRegisters()) {
                registerValues = Arrays.copyOf(previousRegisters, previousRegisters.length);
                // apply the actual transformation to the registers
                if (instruction instanceof Add) {
                    Add addInstr = (Add) (instruction);
                    int r1Index = addInstr.getR1().getIndex() - 1;
                    int r2Index = addInstr.getR2().getIndex() - 1;

                    registerValues[r1Index] += registerValues[r2Index];
                } else if (instruction instanceof Sub) {
                    Sub subInstr = (Sub) (instruction);
                    int r1Index = subInstr.getR1().getIndex() - 1;
                    int r2Index = subInstr.getR2().getIndex() - 1;

                    registerValues[r1Index] -= registerValues[r2Index];
                } else if (instruction instanceof Inc) {
                    Inc incInstr = (Inc) (instruction);
                    int rIndex = incInstr.getR1().getIndex() - 1;

                    registerValues[rIndex]++;
                } else if (instruction instanceof Dec) {
                    Dec decInstr = (Dec) (instruction);
                    int rIndex = decInstr.getR1().getIndex() - 1;

                    registerValues[rIndex]--;
                } else if (instruction instanceof Write) {
                    Write writeInstr = (Write) (instruction);
                    int rIndex = writeInstr.getReg().getIndex() - 1;
                    int val = writeInstr.getV();

                    registerValues[rIndex] = val;
                } else {
                    throw new IllegalArgumentException("Instruction cannot modify registers and not be one of Add, Sub, Inc, Dec, Write");
                }
            } else {
                registerValues = previousRegisters;
            }
        }

        void addNextInstr(String instruction, boolean condition) {
            if (condition) {
                nextInstrIfTrue = new TreeNode(instruction, registerValues);
            } else {
                nextInstrIfFalse = new TreeNode(instruction, registerValues);
            }
        }

        // Adds the true and false instructions to the node, used with branching
        void addNextInstr(String trueInstruction, String falseInstruction) {
            addNextInstr(trueInstruction, true);
            addNextInstr(falseInstruction, false);
        }

        // Adds only one instruction to the node, used when there is no branching
        void addNextInstr(String onlyInstruction) {
            addNextInstr(onlyInstruction, true);
        }

        // TODO: report for the conditionals what the result was (in pseudocritter)
        boolean programOutputMatchesTreePath(List<String> output) {
            // TODO: traverse the tree and confirm the sequence of instructions matches a path down the tree
            return false;
        }

        static TreeNode makeProgramTree(String filename) throws IOException {
            BufferedReader br = new BufferedReader(new FileReader(filename));
            String line = br.readLine();
            List<String> instructions = new ArrayList<String>();
            while (line != null) {
                instructions.add(line);
                line = br.readLine();
            }
            br.close();

            Stack<TreeNode> toParse = new Stack<TreeNode>();
            // TODO: add line number instance var to each treenode
            toParse.add(new TreeNode(instructions.get(0), new int[Critter.REGISTERS]));
            TreeNode root = toParse.peek();

            while (!toParse.isEmpty()) {
                TreeNode curr = toParse.pop();
                // add the next instruction(s) to this node
                if (curr.instruction.hasBranch()) {
                    // if true it's the jump to line, if false it is the next line
                    int trueNextLine = manualResultantLineNumber(curr.registerValues, instructions.indexOf(curr.instruction), curr.instruction.getInstructionJump());
                    int falseNextLine = instructions.indexOf(curr.instruction) + 1;

                    curr.addNextInstr(instructions.get(trueNextLine), instructions.get(falseNextLine));

                    toParse.push(curr.nextInstrIfTrue);
                    toParse.push(curr.nextInstrIfFalse);
                } else if (curr.instruction instanceof Go) {
                    // only one branch but is not next line
                    Go g = (Go) (curr.instruction);
                    // find the resultant line number without access to the critter at runtime
                    int nextLine = manualResultantLineNumber(curr.registerValues, instructions.indexOf(curr.instruction), g.getInstructionJump());
                    curr.addNextInstr(instructions.get(nextLine));

                    toParse.push(curr.nextInstrIfTrue);
                } else {
                    int nextLine = instructions.indexOf(curr.instruction) + 1;
                    curr.addNextInstr(instructions.get(nextLine));

                    toParse.push(curr.nextInstrIfTrue);
                }
            }


            return root;
        }

       static private int manualResultantLineNumber(int[] registers, int currLine, InstructionJump ij) {
            if (ij.getIsRelative()) {
                return currLine + ij.getN();
            } else if (ij.getIsRegister()) {
                return registers[ij.getN() - 1];
            } else {
                return ij.getN();
            }
        }

    }
}

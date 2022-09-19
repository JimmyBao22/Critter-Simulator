package assignment;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class InstructionPath {
    private class TreeNode {
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

        void addNextInstr(String nextInstructionText) {
            if (this.instruction.hasBranch()) {
                // TODO
            }

            this.addNextInstr(instruction, true);
        }

        TreeNode makeProgramTree(String filename) throws IOException {
            BufferedReader br = new BufferedReader(new FileReader(filename));
            String line = br.readLine();
            List<String> instructions = new ArrayList<String>();
            while (line != null) {
                instructions.add(line);
                line = br.readLine();
            }
            br.close();

            Stack<TreeNode> toParse = new Stack<TreeNode>();
            toParse.add(new TreeNode(instructions.get(0), new int[Critter.REGISTERS]));

            while (!toParse.isEmpty()) {
                TreeNode curr = toParse.pop();
            }
            return null;
        }

    }
}

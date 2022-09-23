package assignment;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * Responsible for loading critter species from text files and interpreting the
 * simple Critter language.
 * 
 * For more information on the purpose of the below two methods, see the
 * included API/ folder and the project description.
 */
public class Interpreter implements CritterInterpreter {
	public void executeCritter(Critter c) {
		List<Instruction> instructions = c.getCode();
		// Decrement to convert from 1-indexed to 0-indexed
		int nextLine = c.getNextCodeLine() - 1;

		try {
			// Do nothing if we try to access invalid instructions
			// while the current line is not a terminating instruction, run the line.
			while (!isInvalidLine(nextLine, instructions.size()) &&
					!instructions.get(nextLine).isTerminatingInstruction()) {
				instructions.get(nextLine).run(c);
				nextLine = c.getNextCodeLine() - 1;
			}

			// need to run last the terminating instruction
			if (!isInvalidLine(nextLine, instructions.size()) &&
					instructions.get(nextLine).isTerminatingInstruction()) {
				instructions.get(nextLine).run(c);
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
		}
	}

	private boolean isInvalidLine(int nextLine, int instructionSize) {
		return (nextLine < 0 || nextLine >= instructionSize);
	}


	public CritterSpecies loadSpecies(String filename) throws IOException {
		try {
			BufferedReader br = new BufferedReader(new FileReader(filename));
			List<Instruction> instructions = new ArrayList<Instruction>();

			// First line is the species name
			String speciesName = br.readLine();
			if (speciesName == null) {
				return null;
			}

			String line = br.readLine();

			while (line != null) {
				if (line.length() == 0) {
					// Program ends with a blank line
					break;
				}

				// Split the operation name and its arguments
				String[] arguments = line.split(" ");
				String operationName = arguments[0];
				arguments = Arrays.copyOfRange(arguments, 1, arguments.length);

				// Call the appropriate constructor based on the operation name, pass the arguments
				instructions.add(Instruction.makeInstruction(operationName, arguments));

				line = br.readLine();
			}

			br.close();

			return new CritterSpecies(speciesName, instructions);
		} catch (IllegalArgumentException e) {
			// user made an issue in their instruction file
			System.err.println("Error parsing instructions: " + e.getMessage());
			return null;
		} catch (IOException e) {
			// some file read error
			System.err.println("Error reading instruction file: " + e.getMessage());
			return null;
		} catch (Exception e) {
			// any other issues
			System.err.println("Error while trying to load instructions: " + e.getMessage());
			return null;
		}
	}
}

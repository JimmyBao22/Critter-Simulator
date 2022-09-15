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

	}

	public CritterSpecies loadSpecies(String filename) throws IOException {
		// TODO improve file read security
		BufferedReader br = new BufferedReader(new FileReader(filename));
		String line = br.readLine();
		List<Instruction> instructions = new ArrayList<Instruction>();

		while (line != null) {
			String[] arguments = line.split(" ");

			String operation = arguments[0];
			arguments = Arrays.copyOfRange(arguments, 1, arguments.length);

			switch (operation) {
				case "hop":
					instructions.add(new Hop(arguments.sub))
					break;
			}
			line = br.readLine();
		}

		return null;
	}
}

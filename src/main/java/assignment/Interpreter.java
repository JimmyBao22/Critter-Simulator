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
					instructions.add(new Hop(arguments));
					break;
				case "left":
					instructions.add(new Left(arguments));
					break;
				case "right":
					instructions.add(new Right(arguments));
					break;
				case "infect":
					instructions.add(new Infect(arguments));
					break;
				case "eat":
					instructions.add(new Eat(arguments));
					break;
				case "go":
					instructions.add(new Go(arguments));
					break;
				case "ifrandom":
					instructions.add(new IfRandom(arguments));
					break;
				case "ifhungry":
					instructions.add(new IfHungry(arguments));
					break;
				case "ifstarving":
					instructions.add(new IfStarving(arguments));
					break;
				case "ifempty":
					instructions.add(new IfEmpty(arguments));
					break;
				case "ifally":
					instructions.add(new IfAlly(arguments));
					break;
				case "ifenemy":
					instructions.add(new IfEnemy(arguments));
					break;
				case "ifwall":
					instructions.add(new IfWall(arguments));
					break;
				case "ifangle":
					instructions.add(new IfAngle(arguments));
					break;
				case "write":
					instructions.add(new Write(arguments));
					break;
				case "add":
					instructions.add(new Add(arguments));
					break;
				case "sub":
					instructions.add(new Sub(arguments));
					break;
				case "inc":
					instructions.add(new Inc(arguments));
					break;
				case "dec":
					instructions.add(new Dec(arguments));
					break;
				case "iflt":
					instructions.add(new IfLt(arguments));
					break;
				case "ifeq":
					instructions.add(new IfEq(arguments));
					break;
				case "ifgt":
					instructions.add(new IfGt(arguments));
					break;
				default:
					// TODO: throw error
					break;
			}
			line = br.readLine();
		}

		return null;
	}
}

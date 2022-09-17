package assignment;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class ParseRandomCritters {
    @Test
    void createAndParseRandomCritters() throws IOException {
        int numCritters = 10;
        CreateCritters.createRandomCritters(numCritters);

        String filePrefix = "species/TestCritter";
        String fileSuffix = ".cri";

        CritterInterpreter ci = new Interpreter();

        for (int i = 0; i < numCritters; i++) {
            String filename = filePrefix + i + fileSuffix;
            StringBuilder text = new StringBuilder();
            BufferedReader br = new BufferedReader(new FileReader(filename));
            String line = br.readLine();
            while (line != null) {
                text.append(line);
                text.append('\n');
                line = br.readLine();
            }
            br.close();

            CritterSpecies cs = ci.loadSpecies(filename);
            List<Instruction> instructions = cs.getCode();
            StringBuilder outputInstructions = new StringBuilder();
            outputInstructions.append(cs.getName());
            outputInstructions.append("\n");
            for (Instruction inst : instructions) {
                outputInstructions.append(inst.toString());
                outputInstructions.append("\n");
            }
            outputInstructions.append("\n\n");

            Assertions.assertEquals(text.compareTo(outputInstructions), 0);
        }
    }
}

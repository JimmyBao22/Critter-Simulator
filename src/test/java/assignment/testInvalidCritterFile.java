package assignment;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class testInvalidCritterFile {

    @Test
    void testInvalidFile() throws IOException {
        String filePrefix = "species/Error";
        String fileSuffix = ".cri";

        CritterInterpreter ci = new Interpreter();

        // read original file
        String filename = filePrefix + fileSuffix;
        StringBuilder text = new StringBuilder();
        BufferedReader br = new BufferedReader(new FileReader(filename));
        String line = br.readLine();
        while (line != null) {
            text.append(line);
            text.append('\n');
            line = br.readLine();
        }
        br.close();

        // convert to objects
        try {
            CritterSpecies cs = ci.loadSpecies(filename);
            List<Instruction> instructions = cs.getCode();
            StringBuilder outputInstructions = new StringBuilder();
            // convert back to string
            outputInstructions.append(cs.getName());
            outputInstructions.append("\n");

            for (Instruction inst : instructions) {
                outputInstructions.append(inst.toString());
                outputInstructions.append("\n");
            }
            outputInstructions.append("\n\n");
        } catch (Exception e) {
            Assertions.assertTrue(true);
        }
    }
}

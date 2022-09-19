package assignment;

import java.io.*;
import java.util.ArrayList;

public class ReproduceCritters {

    public static void main(String[] args) throws IOException {
        int n = CreateCritters.arr.length;

        BufferedReader in = new BufferedReader(new FileReader("species/BestTestCritter.cri"));
        ArrayList<String> bestCritterInstructions = new ArrayList<>();
        in.readLine();
        String line = in.readLine();
        while (line != null && !line.equals("\n")) {
            bestCritterInstructions.add(line);
            line = in.readLine();
        }
        int k = bestCritterInstructions.size();
        int numInstructions = k;

        for (int i=0; i<10; i++) {
            PrintWriter out = new PrintWriter("species/TestCritter" + i + ".cri");
            out.println("TestCritter" + i );

            for (int j=0; j<k; j++) {

                if (Math.random() < 0.86) {
                    // use previous critters
                    out.println(bestCritterInstructions.get(j));
                    continue;
                }

                int index = (int)(Math.random() * n);

                switch (CreateCritters.arr[index]) {
                    case "hop":
                        out.println("hop");
                        break;
                    case "left":
                        out.println("left");
                        break;
                    case "right":
                        out.println("right");
                        break;
                    case "infect":
                        out.println("infect " + CreateCritters.createInstructionJump(numInstructions));
                        break;
                    case "eat":
                        out.println("eat");
                        break;
                    case "go":
                        out.println("go " + CreateCritters.createInstructionJump(numInstructions));
                        break;
                    case "ifrandom":
                        out.println("ifrandom " + CreateCritters.createInstructionJump(numInstructions));
                        break;
                    case "ifhungry":
                        out.println("ifhungry "+ CreateCritters.createInstructionJump(numInstructions));
                        break;
                    case "ifstarving":
                        out.println("ifstarving "+ CreateCritters.createInstructionJump(numInstructions));
                        break;
                    case "ifempty":
                        out.println("ifempty " + CreateCritters.createBearing() + " " + CreateCritters.createInstructionJump(numInstructions));
                        break;
                    case "ifally":
                        out.println("ifally " + CreateCritters.createBearing() + " " + CreateCritters.createInstructionJump(numInstructions));
                        break;
                    case "ifenemy":
                        out.println("ifenemy " + CreateCritters.createBearing() + " " + CreateCritters.createInstructionJump(numInstructions));
                        break;
                    case "ifwall":
                        out.println("ifwall " + CreateCritters.createBearing() + " " + CreateCritters.createInstructionJump(numInstructions));
                        break;
                    case "ifangle":
                        out.println("ifangle " + CreateCritters.createBearing() + " " + CreateCritters.createBearing() + " " + CreateCritters.createInstructionJump(numInstructions));
                        break;
                    case "write":
                        out.println("write " + CreateCritters.createRegister() + " " + (int)(Math.random() * 10));
                        break;
                    case "add":
                        out.println("add " + CreateCritters.createRegister() + " " + CreateCritters.createRegister());
                        break;
                    case "sub":
                        out.println("sub " + CreateCritters.createRegister() + " " + CreateCritters.createRegister());
                        break;
                    case "inc":
                        out.println("inc " + CreateCritters.createRegister());
                        break;
                    case "dec":
                        out.println("dec " + CreateCritters.createRegister());
                        break;
                    case "iflt":
                        out.println("iflt " + CreateCritters.createRegister() + " " + CreateCritters.createRegister() + " " + CreateCritters.createInstructionJump(numInstructions));
                        break;
                    case "ifeq":
                        out.println("ifeq " + CreateCritters.createRegister() + " " + CreateCritters.createRegister() + " " + CreateCritters.createInstructionJump(numInstructions));
                        break;
                    case "ifgt":
                        out.println("ifgt " + CreateCritters.createRegister() + " " + CreateCritters.createRegister() + " " + CreateCritters.createInstructionJump(numInstructions));
                        break;
                }
            }
            out.println("go 1");
            out.println();
            out.println();
            out.close();
        }
    }
}
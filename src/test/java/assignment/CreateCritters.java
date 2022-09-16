package assignment;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class CreateCritters {

    public static void main(String[] args) throws FileNotFoundException {
        String[] arr = {"hop", "left", "right", "infect", "eat", "go", "ifrandom", "ifhungry", "ifstarving", "ifempty",
            "ifally", "ifenemy", "ifwall", "ifangle", "write", "add", "sub", "inc", "dec", "iflt", "ifeq", "ifgt"};
        int n = arr.length;

        for (int i=0; i<10; i++) {
            PrintWriter out = new PrintWriter("species/TestCritter" + i + ".cri");
            out.println("TestCritter" + i );

            for (int j=0; j<100; j++) {
                int index = (int)(Math.random() * n);

                switch (arr[index]) {
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
                        out.println("infect " + createInstructionJump());
                        break;
                    case "eat":
                        out.println("eat");
                        break;
                    case "go":
                        out.println("go " + createInstructionJump());
                        break;
                    case "ifrandom":
                        out.println("ifrandom " + createInstructionJump());
                        break;
                    case "ifhungry":
                        out.println("ifhungry "+ createInstructionJump());
                        break;
                    case "ifstarving":
                        out.println("ifstarving "+ createInstructionJump());
                        break;
                    case "ifempty":
                        out.println("ifempty " + createBearing() + " " + createInstructionJump());
                        break;
                    case "ifally":
                        out.println("ifally " + createBearing() + " " + createInstructionJump());
                        break;
                    case "ifenemy":
                        out.println("ifenemy " + createBearing() + " " + createInstructionJump());
                        break;
                    case "ifwall":
                        out.println("ifwall " + createBearing() + " " + createInstructionJump());
                        break;
                    case "ifangle":
                        out.println("ifangle " + createBearing() + " " + createBearing() + " " + createInstructionJump());
                        break;
                    case "write":
                        out.println("write " + createRegister() + " " + (int)(Math.random() * 10));
                        break;
                    case "add":
                        out.println("add " + createRegister() + " " + createRegister());
                        break;
                    case "sub":
                        out.println("sub " + createRegister() + " " + createRegister());
                        break;
                    case "inc":
                        out.println("inc " + createRegister());
                        break;
                    case "dec":
                        out.println("dec " + createRegister());
                        break;
                    case "iflt":
                        out.println("iflt " + createRegister() + " " + createRegister() + " " + createInstructionJump());
                        break;
                    case "ifeq":
                        out.println("ifeq " + createRegister() + " " + createRegister() + " " + createInstructionJump());break;
                    case "ifgt":
                        out.println("ifgt " + createRegister() + " " + createRegister() + " " + createInstructionJump());
                        break;
                }
            }
            out.println();
            out.println();
            out.close();
        }
    }

    public static int createBearing() {
        return 45 * (int)(Math.random() * 8);
    }

    public static String createRegister() {
        return "r" + (int)(Math.random() * 10 + 1);
    }

    public static int createInstructionJump() {
        return (int)(Math.random() * 50);
    }
}
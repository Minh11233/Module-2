import java.io.File;
import java.io.IOException;
public class WriteData {
    public static void main(String[] args) throws IOException {
            java.io.File file = new File("scores.txt");
            if (file.exists()) {
                System.out.println("Files already exist");
                System.exit(1);
            }

        java.io.PrintWriter output = new java.io.PrintWriter(file);
            output.print(" John T Smith");
            output.println(90);
            output.close();
    }
}

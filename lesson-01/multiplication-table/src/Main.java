public class Main {
    public static void main(String[] args) {
        System.out.println("Multiplication table");
        System.out.print("");
        for (int j = 1; j <= 9; j++) {
            System.out.print("" + j);
        }
        System.out.println("\n--------------------------------------");

        for (int i = 1; i <= 9; i++) {
            System.out.print(i + " | ");
            for (int j = 1; j <= 9; j++) {
                System.out.print((i + "x" + j + "=" + i*j + " " ));
            }
            System.out.println();
        }
    }
}
import java.util.Scanner;

public class calc {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean exit = false;

        System.out.println("Welcome to the Advance Calculator!");

        while (!exit) {
            System.out.println("\n--- Main Menu ---");
            System.out.println("1. Arithmetic Operations");
            System.out.println("2. Scientific Calculations");
            System.out.println("3. Unit Conversions");
            System.out.println("4. Exit");
            System.out.print("Choose option (1-4): ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1 -> {
                    System.out.println("\n-- Arithmetic Operations --");
                    System.out.println("1. Addition (+)");
                    System.out.println("2. Subtraction (-)");
                    System.out.println("3. Multiplication (*)");
                    System.out.println("4. Division (/)");
                    System.out.print("Select operation (1-4): ");
                    int op = sc.nextInt();

                    System.out.print("Enter first number: ");
                    double a = sc.nextDouble();
                    System.out.print("Enter second number: ");
                    double b = sc.nextDouble();

                    switch (op) {
                        case 1 -> System.out.println("Result: " + (a + b));
                        case 2 -> System.out.println("Result: " + (a - b));
                        case 3 -> System.out.println("Result: " + (a * b));
                        case 4 -> System.out.println(b != 0 ? "Result: " + (a / b) : "Error: Division by zero!");
                        default -> System.out.println("Invalid arithmetic operation.");
                    }
                }
                case 2 -> {
                    System.out.println("\n-- Scientific Calculations --");
                    System.out.println("1. Square Root (√x)");
                    System.out.println("2. Exponentiation (x^y)");
                    System.out.print("Select operation (1-2): ");
                    int op = sc.nextInt();

                    if (op == 1) {
                        System.out.print("Enter a non-negative number: ");
                        double x = sc.nextDouble();
                        System.out.println(x >= 0 ? "Result: √" + x + " = " + Math.sqrt(x) : "Error: Negative input.");
                    } else if (op == 2) {
                        System.out.print("Enter base: ");
                        double base = sc.nextDouble();
                        System.out.print("Enter exponent: ");
                        double exp = sc.nextDouble();
                        System.out.println("Result: " + base + "^" + exp + " = " + Math.pow(base, exp));
                    } else {
                        System.out.println("Invalid scientific operation.");
                    }
                }
                case 3 -> {
                    System.out.println("\n-- Unit Conversions --");
                    System.out.println("1. Temperature Conversion");
                    System.out.println("2. Currency Conversion");
                    System.out.print("Select option (1-2): ");
                    int conv = sc.nextInt();

                    if (conv == 1) {
                        System.out.println("1. Celsius to Fahrenheit");
                        System.out.println("2. Fahrenheit to Celsius");
                        System.out.print("Choose conversion: ");
                        int op = sc.nextInt();
                        System.out.print("Enter temperature: ");
                        double temp = sc.nextDouble();

                        if (op == 1) {
                            System.out.printf("%.2f°C = %.2f°F%n", temp, (temp * 9 / 5 + 32));
                        } else if (op == 2) {
                            System.out.printf("%.2f°F = %.2f°C%n", temp, (temp - 32) * 5 / 9);
                        } else {
                            System.out.println("Invalid temperature option.");
                        }
                    } else if (conv == 2) {
                        double usdToInr = 83.5;
                        System.out.println("1. USD to INR");
                        System.out.println("2. INR to USD");
                        System.out.print("Choose conversion: ");
                        int op = sc.nextInt();
                        System.out.print("Enter amount: ");
                        double amt = sc.nextDouble();

                        if (op == 1) {
                            System.out.printf("$%.2f = ₹%.2f%n", amt, amt * usdToInr);
                        } else if (op == 2) {
                            System.out.printf("₹%.2f = $%.2f%n", amt, amt / usdToInr);
                        } else {
                            System.out.println("Invalid currency option.");
                        }
                    } else {
                        System.out.println("Invalid conversion type.");
                    }
                }
                case 4 -> {
                    exit = true;
                    System.out.println("Thank you for using the Advance Calculator. Goodbye!");
                }
                default -> System.out.println("Invalid option. Please select between 1 and 4.");
            }
        }

        sc.close();
    }
}



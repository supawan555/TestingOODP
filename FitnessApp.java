import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.*;
import Plan.MuscleGainPlan;
import Plan.WeightLossPlan;

public class FitnessApp {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // Input user details
        System.out.println("Welcome to FitnessApp!");
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();
        System.out.print("Enter your gender (M/F): ");
        char gender = scanner.next().charAt(0);
        System.out.print("Enter your weight (kg): ");
        double weight = scanner.nextDouble();
        System.out.print("Enter your height (cm): ");
        double height = scanner.nextDouble();

        // Calculate BMI
        double bmi = calculateBMI(weight, height);
        System.out.print("Your BMI is:");
        System.out.format(" %.2f",bmi);
        System.out.println();

        // Calculate body fat percentage
        double bodyFat = calculateBodyFatPercentage(weight, height, gender);
        System.out.println("Your body fat percentage is: " + bodyFat);

        // Ask user for exercise preference
        System.out.print("How many times do you want to exercise per week? ");
        int exercisePerWeek = scanner.nextInt();

        // Ask user for their goal
        System.out.println("What is your goal?");
        System.out.println("1. Weight Loss");
        System.out.println("2. Muscle Gain");
        System.out.print("Enter your choice: ");
        int goalChoice = scanner.nextInt();

        MuscleGainPlan newMusclePlan = new MuscleGainPlan();
        WeightLossPlan newWeightPlan = new WeightLossPlan();

        // Display workout plan
        System.out.println("\nDear " + name + ", here is your personalized workout plan:");
        if (goalChoice == 1) {
            newWeightPlan.displayPlan(exercisePerWeek);
        } else if ( goalChoice == 2){
            newMusclePlan.displayPlan(exercisePerWeek);;
        } else {
            System.out.println("Please input only 1 - 2 ");

        }

        // Save workout plan to the file
        try (BufferedWriter filewrite = new BufferedWriter(new FileWriter("User_data.txt"))){
            filewrite.write(name);
            filewrite.newLine();
            filewrite.write("BMI :" + bmi);
            filewrite.newLine();
            filewrite.write("Body fat percentage :" + bodyFat);
            filewrite.close();

            System.out.println("Info saved to file: " + name + "User_data.txt");
        } catch (IOException e) {
            System.err.println("Error writing Info to file: " + e.getMessage());
        }


        try (BufferedReader fileread = new BufferedReader(new FileReader("User_data.txt"))){
            String line = null;
            while((line = fileread.readLine()) != null){
                System.out.println(line);
            }
        } catch (IOException e) {
            // TODO: handle exception
            System.err.println("Error Reading workout plan to file: " + e.getMessage());
        }
        scanner.close();

    }

    public static double calculateBMI(double weight, double height) {
        
        return weight / ((height / 100) * (height / 100));
    }

    public static double calculateBodyFatPercentage(double weight, double height, char gender) {
        // Body fat calculation logic can be implemented here
        // For simplicity, let's assume a basic formula for demonstration purposes
        if (gender == 'M') {
            return weight * 0.15; // Example calculation for males
        } else {
            return weight * 0.25; // Example calculation for females
        }
    }
}

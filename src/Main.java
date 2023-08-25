import java.io.FileWriter;
import java.io.IOException;
import javax.swing.*;
public class Main {
    public static void main(String[] args){

        //this line of code print the current working directory to the console
        System.out.println("Current working directory: " + System.getProperty("user.dir"));

        String name = null;

        while (name == null || name.trim().isEmpty()) {

            name = JOptionPane.showInputDialog("Enter your name: ");

            if (name.trim().isEmpty()) {

                JOptionPane.showMessageDialog(null, "Name can't be empty. Please Enter your name. ");
            }
        }

        // Ask the user for their weight and height in either kilograms/meters or pounds/inches
        String[] units = {"Kilograms/Meters", "Pounds/Inches"};
        int selectedUnits = JOptionPane.showOptionDialog(null, "Select units of measurement: ", "BMI Calculator",
                            JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, units, units[0]);

        double weight;
        double height;

        if(selectedUnits == 0){

            weight = Double.parseDouble(JOptionPane.showInputDialog("Enter your weight in kilograms: "));
            height = Double.parseDouble(JOptionPane.showInputDialog("Enter your height in meters: "));
        }
        else{

            weight = Double.parseDouble(JOptionPane.showInputDialog("Enter your weight in pounds: "));
            height = Double.parseDouble(JOptionPane.showInputDialog("Enter your height in inches: "));

            weight *= 0.453592;
            height *= 0.0254;
        }
        String[] genders = {"Male","Female"};
        int selectedGenders = JOptionPane.showOptionDialog(null,"Select your gender: ", "BMI Calculator",
                              JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, genders, genders);

        double bmi = calculateBMI(weight, height);

        String category;

        //MALE
        if(selectedGenders == 0) {
            if (bmi < 20.7) {
                category = ("Underweight");
            } else if (bmi < 26.4) {
                category = ("Normal weight");
            } else if (bmi < 27.8) {
                category = ("Slightly overweight");
            } else if (bmi<31.1) {
                category = ("Overweight");
            } else {
                category = ("OBESE");
            }
        }

        //FEMALE
        else{
            if (bmi < 19.1) {
                category = ("Underweight");
            } else if (bmi < 25.8) {
                category = ("Normal weight");
            } else if (bmi < 27.3) {
                category = ("Slightly overweight");
            } else if (bmi<32.3) {
                category = ("Overweight");
            } else {
                category = ("OBESE");
            }
        }
        // Save the user's BMI into a .txt file
        try{
            FileWriter writer = new FileWriter("BMI_history.txt", true);
            writer.write(name + ", " + genders[selectedGenders] + "\n" + weight + "kg" + ", " + height + "m" + ", " + category + ", " + bmi + "\n");
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        String message = String.format("Your BMI is %.2f (%s) ", bmi, category);

        JOptionPane.showMessageDialog (null,message);
    }
    public static double calculateBMI(double weight, double height){
        return weight / (height * height);
    }
}

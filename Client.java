/*
 * David Shi and Luke Fugere
 * Dec 18th 2024
 * Crumbl project
 */

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Client {
    public static Scanner ear = new Scanner(System.in);
    public static File myFile = new File("past.txt");
    public static WeeklyCrumbl currWeek;

    public static void main(String[] args)
    {
        System.out.println("Welcome to WeeklyCrumbl! This program will display the Crumbl Cookie "
                           + "menu in order of highest rated to lowest.");
        while (true)
        {
            try
            {
                String choice = options(prompt());
                if (choice.equals("quit"))
                {
                    return;
                }
                System.out.println(choice);
            } catch (InputMismatchException e)
            {
                System.out.println("Invalid input. Please enter a valid number.");
                ear.nextLine();
            } catch (IOException e)
            {
                System.out.println("An error occurred while accessing the file: " + e.getMessage());
            } catch (Exception e)
            {
                System.out.println("An unexpected error occurred: " + e.getMessage());
            }
        }
    }

    public static String currentMenu() throws IOException
    {
        try
        {
            if (currWeek != null)
            {
                return currWeek.toString();
            }
            System.out.print("What is the current month of the year?(Enter a number 1-12): ");
            int currentMonth = ear.nextInt();
            System.out.print("What day? (Enter a number 1-31): ");
            int currentDay = ear.nextInt();
            System.out.print("What year? (Enter a number 2025-present): ");
            int currentYear = ear.nextInt();

            String currentDate = currentMonth + "/" + currentDay + "/" + currentYear;
            currentDate = currentDate.concat(":");

            Scanner read = new Scanner(myFile);
            String week = "";
            boolean found = false;
            while (read.hasNextLine())
            {
                String line = read.nextLine();
                if (line.indexOf(currentDate) > -1)
                {
                    found = true;
                    week = line.substring(24);
                }
            }
            read.close();

            currWeek = new WeeklyCrumbl(currentMonth, currentDay, currentYear);

            if (!found)
            {
                FileWriter writer = new FileWriter(myFile, true);
                writer.write(currWeek.toString());
                writer.close();
            }
            return currWeek.toString();
        } catch (InputMismatchException e)
        {
            ear.nextLine();
            return "Invalid input. Please enter numbers only.";
        }
    }

    public static String pastMenu() throws IOException
    {
        try
        {
            System.out.print("What the desired month of the year?(Enter a number 1-12): ");
            int desiredMonth = ear.nextInt();
            System.out.print("What day? (Enter a number 1-31): ");
            int desiredDay = ear.nextInt();
            System.out.print("What year? (Enter a number 2025-present): ");
            int desiredYear = ear.nextInt();

            String desiredDate = desiredMonth + "/" + desiredDay + "/" + desiredYear + ":";

            Scanner reader = new Scanner(myFile);
            String line = "";
            String week = "";
            boolean found = false;

            while (reader.hasNextLine())
            {
                line = reader.nextLine();
                if (line.indexOf(desiredDate) > -1) // exists
                {
                    found = true;
                    week = line.substring(24); // Date location on line
                    break;
                }
            }

            if (found)
            {
                String lines = line;
                for (int i = 1; i < 42; i++)
                {
                    if (reader.hasNextLine())
                    {
                        lines += "\n" + reader.nextLine();
                    }
                    else
                    {
                        break;
                    }
                }
                reader.close();
                return lines.toString();
            }
            else
            {
                reader.close();
                return "Sorry, the week is not in our database.";
            }
        } catch (InputMismatchException e)
        {
            ear.nextLine();
            return "Invalid input. Please enter numbers only.";
        }
    }

    public static String options(int n) throws IOException
    {

        switch (n)
        {
            case 1:
                return currentMenu();
            case 2:
                return pastMenu();
            case 3:
                return "quit";
            default:
                return "Invalid option. Please choose between 1, 2, and 3.";
        }
    }

    public static int prompt()
    {
        System.out.print("\n1. View current week's menu\n2. View past week's "
                         + "menu (NOTE: database is small so there's a high chance nothing is "
                         + "returned)\n3. Quit\nWhat would you like to do?: ");
        try
        {
            return ear.nextInt();
        } catch (InputMismatchException e)
        {
            ear.nextLine(); // Clear bad input
            System.out.println("Invalid input. Please enter a valid number.");
            return -1; // Indicate bad input
        }
    }
}

package ist411induvidualassignment2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class IST411InduvidualAssignment2 
{

    public static void main(String[] args) 
    {
        Boolean validInput = false;
        int input = 0;
        System.out.println(" ____________________________");
        System.out.println("| Welcome to my server!      |");
        System.out.println("| Options:                   |");
        System.out.println("| 1: Log the current date    |");
        System.out.println("| 2: Request log of all date |");
        System.out.println(" ----------------------------");
        while(validInput != true)
        {
            System.out.print("Select an option: ");
            Scanner reader = new Scanner(System.in);
            input = Integer.valueOf(reader.next());
            if (input == 1 || input == 2)
            {
                validInput = true;
            }
            else
            {
                System.out.println("Invalid input");
            }
        }
        

        if (input == 1)
        {           
            try
            {
                URL url = new URL("http://127.0.0.1:3000/logdate");
                System.out.println("Client: Connecting to Server...");
                HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
                httpConn.setDoOutput(true);
                httpConn.setRequestMethod("POST");                                 
                httpConn.setRequestProperty("Accept-Charset", "UTF-8");
                httpConn.setRequestProperty("Content-type", "application/x-www-form-urlencoded");                   
                BufferedReader in = new BufferedReader(new InputStreamReader(httpConn.getInputStream()));
                if (httpConn.getResponseCode() == 200)
                {
                    System.out.println("Client: Connected");
                }
                else
                {
                    System.out.println("Resource not availible");
                }               
            }
            catch(IOException e)
            {
                System.out.println("Error occured");
                e.printStackTrace();
            }
        }
        else
        {
            try
            {       
                URL url = new URL("http://127.0.0.1:3000/dates");
                System.out.println("Client: Connecting to Server...");
                HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();

                httpConn.setDoOutput(true);
                httpConn.setRequestMethod("GET");
                httpConn.setRequestProperty("Accept-Charset", "UTF-8");
                httpConn.setRequestProperty("Content-type", "application/x-www-form-urlencoded");
                BufferedReader in = new BufferedReader(new InputStreamReader(httpConn.getInputStream()));
                if (httpConn.getResponseCode() == 200)
                {
                    System.out.println("Client: Connected");
                }
                else
                {
                    System.out.println("Resource not availible");
                } 

                Scanner sc = new Scanner(in);  
                while (sc.hasNextLine()) 
                {
                    System.out.println(sc.nextLine());
                }
            }
            catch(IOException e)
            {
                System.out.println("Error occured");
                e.printStackTrace();
            }
        }
    }
}


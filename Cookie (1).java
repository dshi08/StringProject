/*
 * David Shi and Luke Fugere
 * Dec 18th 2024
 * Cookie Class
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;

public class Cookie {
    private String name = "";
    private double rating;
    private String review = "", description = "";
    public static final String CRUMBL_PAGE = "https://crumblcookies.com";
    public Cookie(int day, int month, int year, int num)
    {
        // TO-DO:
        // 1. Make a file with all the weeks of crumbl and then scrape it looking for what the
        // cookies are with the given date
        // 2. Set cookie information depending on cookie number (maybe go based on rating)
        name = null;
        rating = 0.0;
        review = null;
        description = null;
        String link2="";

        String inputLine;

        String line = "";
        try{
            URL crumbl = new URI(CRUMBL_PAGE).toURL();
        URLConnection cc = crumbl.openConnection();
        BufferedReader in = new BufferedReader(new InputStreamReader(

        cc.getInputStream()));
        while ((inputLine = in.readLine()) != null)

            line += inputLine;

        in.close();
        int findSecondLink = 0;
        for (int i = 0; i < num; i++)
        {
            findSecondLink = line.indexOf(
                "rounded-full py-3 transition-all duration-300 ease-in group-hover:lg:pl-[30px] " +
                "pl-[0px] pr-[30px] group-hover:lg:border border-transparent");
            line = line.substring(findSecondLink);
        }
        int findSecondLink1 = line.indexOf("https");
        int findSecondLink2 = line.indexOf("Cookie") + 7;
        link2 = line.substring(findSecondLink1, findSecondLink2);
        link2=link2.substring(0,link2.length()-1);
    }   catch (FileNotFoundException ex)

            {

                System.out.println("unknown");

            }

            catch (MalformedURLException e)

            {

                System.out.println("badly formed url exception occurred");

                return;

            }

            catch (URISyntaxException e) {
                                System.out.println("Bad URI syntax");

                return;

            }

            catch (IOException e)

            {

                System.out.println("IO exception occurred");

                return;
            }
            try{

                URL cookie = new URI(link2).toURL();
        URLConnection c = cookie.openConnection();

        BufferedReader in2 = new BufferedReader(new InputStreamReader(

            c.getInputStream()));

        String inputLine2;

        String line2 = "";

        while ((inputLine2 = in2.readLine()) != null)

            line2 += inputLine2;

        in2.close();
        int cookieName1 = line2.indexOf("alt=") + 5;
        int cookieName2 = line2.indexOf("loading=")-2;
        name = line2.substring(cookieName1, cookieName2);
        int ampChecker = name.indexOf("&amp");
        if (ampChecker != -1)
        {
            name = name.substring(0, ampChecker) + " and" +
                   name.substring(ampChecker + 4, name.length());
        }
    }
    catch (FileNotFoundException ex)

    {

        System.out.println("unknown");

    }

    catch (MalformedURLException e)

    {

        System.out.println("badly formed url exception occurred");

        return;

    }

    catch (URISyntaxException e) {
                        System.out.println("Bad URI syntax");

        return;

    }

    catch (IOException e)

    {

        System.out.println("IO exception occurred");

        return;
    }
    }

    public String getName()
    {
        return name;
    }

    public double getRating()
    {
        return rating;
    }

    public String getReview()
    {
        return review;
    }

    public String toString()
    {
        return "Name: " + name + "\nRating: " + rating + "\nDescription: " + description +
            "\nTop Review: " + review;
    }
}

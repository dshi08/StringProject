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
        String link2 = "";

        String inputLine;
        // Saves the html code
        String line = "";
        try
        {
            URL crumbl = new URI(CRUMBL_PAGE).toURL();
            URLConnection cc = crumbl.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(

                // Gets html code
                cc.getInputStream()));
            while ((inputLine = in.readLine()) != null)

                line += inputLine;

            in.close();
            int findSecondLink = 0;
            String finder = "\"id\":\"";
            for (int i = 0; i < num; i++)
            {
                findSecondLink = line.indexOf(finder);
                if (i > 0)
                {
                    line = line.substring(findSecondLink + finder.length());
                    findSecondLink = line.indexOf(finder);
                }

                line = line.substring(findSecondLink);
            }
            int findSecondLink2 = line.indexOf("\",");
            link2 = line.substring(finder.length(), findSecondLink2);
            // This is the link to each individual cookie's website
            link2 = "https://crumblcookies.com/profiles/" + link2;
            // Catches all exceptions
        } catch (FileNotFoundException ex)

        {

            System.out.println("unknown");

        }

        catch (MalformedURLException e)

        {

            System.out.println("badly formed url exception occurred");

            return;

        }

        catch (URISyntaxException e)
        {
            System.out.println("Bad URI syntax");

            return;

        }

        catch (IOException e)

        {

            System.out.println("IO exception occurred");

            return;
        }
        try
        {

            URL cookie = new URI(link2).toURL();
            URLConnection c = cookie.openConnection();

            BufferedReader in2 = new BufferedReader(new InputStreamReader(

                c.getInputStream()));

            String inputLine2;

            String line2 = "";

            while ((inputLine2 = in2.readLine()) != null)

                line2 += inputLine2;

            in2.close();
            // Gets the name
            String fixingCodeIndex1 = "\"nameWithoutPartner\":\"";
            int cookieName1 =
                line2.indexOf("\"nameWithoutPartner\":\"") + fixingCodeIndex1.length();
            int cookieName2 = line2.indexOf("\",\"featuredPartner");
            name = line2.substring(cookieName1, cookieName2);
            int ampChecker = name.indexOf("&amp");
            if (ampChecker != -1)
            {
                name = name.substring(0, ampChecker) + " and" +
                       name.substring(ampChecker + 4, name.length());
            }
            // Gets the rating

            int rating1 = line2.indexOf("\"averageRating\":");
            String textLocater = "\"averageRating\":";
            String ratingS = line2.substring(rating1 + textLocater.length());
            int rating2 = ratingS.indexOf(",\"totalReviews");
            ratingS = ratingS.substring(0, rating2);
            rating = (double) (Double.parseDouble(ratingS));
            // Gets the description
            String descriptionLocater = "\"description\":\"";
            int description1 = line2.indexOf("\"description\":\"");
            String descript = line2.substring(description1 + descriptionLocater.length());
            int description2 = descript.indexOf(".");
            description = descript.substring(0, description2 + 1);
            if (description.length() > 200)
            {
                description = "N/A";
            }
            // Gets the review
            String topRLocater = "reviewText\":\"";
            int topR1 = line2.indexOf(topRLocater);
            String topR = line2.substring(topR1 + topRLocater.length());
            int topR2 = topR.indexOf("\",\"customerName\"");
            review = topR.substring(0, topR2);
            // Catches all exceptions
        } catch (FileNotFoundException ex)

        {

            System.out.println("unknown");

        }

        catch (MalformedURLException e)

        {

            System.out.println("badly formed url exception occurred");

            return;

        }

        catch (URISyntaxException e)
        {
            System.out.println("Bad URI syntax");

            return;

        }

        catch (IOException e)

        {

            System.out.println("IO exception occurred");

            return;
        }
    }
    // Main methods of cookie method
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

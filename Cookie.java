/*
 * David Shi and Luke Fugere
 * Dec 18th 2024
 * Cookie Class
 */

import java.io.BufferedReader;
import java.io.FileNotFoundException;
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

    public Cookie(int num)
    {
        String link2 = "";
        String inputLine;
        String line = "";
        try
        {
            URL crumbl = new URI(CRUMBL_PAGE).toURL();
            URLConnection cc = crumbl.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(cc.getInputStream()));
            while ((inputLine = in.readLine()) != null)
                line += inputLine;
            in.close();
            int findSecondLink = 0;
            String finder =
                "rounded-full py-3 transition-all duration-300 ease-in group-hover:lg:pl-[30px] "
                + "pl-[0px] pr-[30px] group-hover:lg:border border-transparent";
            for (int i = 0; i < num; i++)
            {
                findSecondLink =
                    line.indexOf("rounded-full py-3 transition-all duration-300 ease-in "
                                 + "group-hover:lg:pl-[30px] "
                                 + "pl-[0px] pr-[30px] group-hover:lg:border border-transparent");
                if (i > 0)
                {
                    line = line.substring(findSecondLink + finder.length());
                    findSecondLink = line.indexOf(
                        "rounded-full py-3 transition-all duration-300 ease-in "
                        + "group-hover:lg:pl-[30px] "
                        + "pl-[0px] pr-[30px] group-hover:lg:border border-transparent");
                }
                line = line.substring(findSecondLink);
            }
            int findSecondLink1 = line.indexOf("https");
            link2 = line.substring(findSecondLink1);
            int findSecondLink2 = link2.indexOf("\"");
            link2 = link2.substring(0, findSecondLink2);

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
            int cookieName1 = line2.indexOf("alt=") + 5;
            int cookieName2 = line2.indexOf("loading=") - 2;
            name = apostropheDecoder(line2.substring(cookieName1, cookieName2));
            int ampChecker = name.indexOf("&amp");
            if (ampChecker != -1)
            {
                name = name.substring(0, ampChecker) + " and" +
                       name.substring(ampChecker + 4, name.length());
            }

            int rating1 = line2.indexOf("text-nowrap\">");
            String textLocater = "text-nowrap\">";
            String ratingS = line2.substring(rating1 + textLocater.length());
            int rating2 = ratingS.indexOf(" ");
            ratingS = ratingS.substring(0, rating2);
            rating = (double) (Double.parseDouble(ratingS));

            String descriptionLocater = "2xl:leading-[22px] md:max-w-[700px] text-xl leading-6\">";
            int description1 =
                line2.indexOf("2xl:leading-[22px] md:max-w-[700px] text-xl leading-6\">");
            String descript = line2.substring(description1 + descriptionLocater.length());
            int description2 = descript.indexOf(".");
            description = apostropheDecoder(descript.substring(0, description2 + 1));
            if (description.length() > 200)
            {
                description = "N/A";
            }

            String topRLocater = "ext-ellipsis line-clamp-5\">";
            int topR1 = line2.indexOf(topRLocater);
            String topR = line2.substring(topR1 + topRLocater.length());
            int topR2 = topR.indexOf("<");
            review = apostropheDecoder(topR.substring(0, topR2));

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

    public static String apostropheDecoder(String in)
    {
        /*
         * This method is complete
         * Webscraping apostrophe returns symbols
         * This method decodes the symbols
         */
        if (in.indexOf("&#x27;") != -1)
        {
            int index = in.indexOf("&#x27;");
            String out = in.substring(0, index);
            out += "'";
            out += in.substring(index + 6);
            return out;
        }
        return in;
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

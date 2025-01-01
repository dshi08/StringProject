/*
 * David Shi and Luke Fugere
 * Dec 18th 2024
 * Cookie Class
 */

 import java.net.URI;
 import java.net.URL;
 import java.net.URLConnection;
 import java.io.IOException;

 public class Cookie {
    private String name = "";
    private double rating;
    private String review = "", description = "";
    public static final String CRUMBL_PAGE = "https://crumblcookies.com";

    public Cookie(int day, int month, int year, int num) {
        // TO-DO:
        // 1. Make a file with all the weeks of crumbl and then scrape it looking for what the cookies are with the given date
        // 2. Set cookie information depending on cookie number (maybe go based on rating)
        name = null;
        rating = 0.0;
        review = null;
        description = null;
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
        return "Name: " + name + "\nRating: " + rating + "\nDescription: " + description + "\nTop Review: " + review;
    }
}

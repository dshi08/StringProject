/*
 * David Shi and Luke Fugere
 * Dec 18th 2024
 * Crumbl project
 */

public class WeeklyCrumbl {

    private int year;
    private int month;
    private int day;

    // 8 cookies a week
    private Cookie cookie1;
    private Cookie cookie2;
    private Cookie cookie3;
    private Cookie cookie4;
    private Cookie cookie5;
    private Cookie cookie6;
    private Cookie cookie7;
    private Cookie cookie8;

    public WeeklyCrumbl(int month, int day, int year)
    {
        this.day = day;
        this.month = month;
        this.year = year;

        cookie1 = new Cookie(1);
        cookie2 = new Cookie(2);
        cookie3 = new Cookie(3);
        cookie4 = new Cookie(4);
        cookie5 = new Cookie(5);
        cookie6 = new Cookie(6);
        cookie7 = new Cookie(7);
        cookie8 = new Cookie(8);

        sort();
    }

    public void sort()
    {
        for (int i = 0; i < 7; i++)
        {
            for (int j = 1; j <= 8 - i - 1; j++)
            {
                Cookie current = getCookie(j);
                Cookie next = getCookie(j + 1);

                if (current.getRating() - next.getRating() < 0.0001)
                {
                    setCookie(j, next);
                    setCookie(j + 1, current);
                }
            }
        }
    }

    public Cookie getCookie(int index)
    {
        switch (index)
        {
            case 1:
                return cookie1;
            case 2:
                return cookie2;
            case 3:
                return cookie3;
            case 4:
                return cookie4;
            case 5:
                return cookie5;
            case 6:
                return cookie6;
            case 7:
                return cookie7;
            case 8:
                return cookie8;
            default:
                throw new IllegalArgumentException("Invalid cookie index: " + index);
        }
    }

    private void setCookie(int index, Cookie cookie)
    {
        switch (index)
        {
            case 1:
                cookie1 = cookie;
                break;
            case 2:
                cookie2 = cookie;
                break;
            case 3:
                cookie3 = cookie;
                break;
            case 4:
                cookie4 = cookie;
                break;
            case 5:
                cookie5 = cookie;
                break;
            case 6:
                cookie6 = cookie;
                break;
            case 7:
                cookie7 = cookie;
                break;
            case 8:
                cookie8 = cookie;
                break;
            default:
                throw new IllegalArgumentException("Invalid cookie index: " + index);
        }
    }

    public String toString()
    {
        return "Top cookies of the week " + month + "/" + day + "/" + year +
            (":\n--------------------------------------------------------------------------------"
             + "-----------------------------------------------------------------------------------"
             + "--------------------\n") +
            cookie1 +
            ("\n---------------------------------------------------------------------------------"
             + "-----------------------------------------------------------------------------------"
             + "-------------------\n") +
            cookie2 +
            ("\n---------------------------------------------------------------------------------"
             + "-----------------------------------------------------------------------------------"
             + "-------------------\n") +
            cookie3 +
            ("\n---------------------------------------------------------------------------------"
             + "-----------------------------------------------------------------------------------"
             + "-------------------\n") +
            cookie4 +
            ("\n---------------------------------------------------------------------------------"
             + "-----------------------------------------------------------------------------------"
             + "-------------------\n") +
            cookie5 +
            ("\n---------------------------------------------------------------------------------"
             + "-----------------------------------------------------------------------------------"
             + "-------------------\n") +
            cookie6 +
            ("\n---------------------------------------------------------------------------------"
             + "-----------------------------------------------------------------------------------"
             + "-------------------\n") +
            cookie7 +
            ("\n---------------------------------------------------------------------------------"
             + "-----------------------------------------------------------------------------------"
             + "-------------------\n") +
            cookie8 +
            ("\n---------------------------------------------------------------------------------"
             + "-----------------------------------------------------------------------------------"
             + "-------------------\n");
    }
}

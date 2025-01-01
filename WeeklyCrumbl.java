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

    public WeeklyCrumbl(int day, int month, int year)
    {
        this.day = day;
        this.month = month;
        this.year = year;
        cookie1 = new Cookie(day, month, year, 1);
        cookie2 = new Cookie(day, month, year, 2);
        cookie3 = new Cookie(day, month, year, 3);
        cookie4 = new Cookie(day, month, year, 4);
        cookie5 = new Cookie(day, month, year, 5);
        cookie6 = new Cookie(day, month, year, 6);
        cookie7 = new Cookie(day, month, year, 7);
        cookie8 = new Cookie(day, month, year, 8);
    }

    public Cookie getCookie1() {
        return cookie1;
    }

    public Cookie getCookie2() {
        return cookie2;
    }

    public Cookie getCookie3() {
        return cookie3;
    }

    public Cookie getCookie4() {
        return cookie4;
    }

    public Cookie getCookie5() {
        return cookie5;
    }

    public Cookie getCookie6() {
        return cookie6;
    }

    public Cookie getCookie7() {
        return cookie7;
    }

    public Cookie getCookie8() {
        return cookie8;
    }

    public String toString()
    {
        return "Cookies of the week " + month + "/" + day + "/" + year +
            ":\n-------------------------------------------------------------\n" + cookie1 +
            "\n-------------------------------------------------------------\n" + cookie2 +
            "\n-------------------------------------------------------------\n" + cookie3 +
            "\n-------------------------------------------------------------\n" + cookie4 +
            "\n-------------------------------------------------------------\n" + cookie5 +
            "\n-------------------------------------------------------------\n" + cookie6 +
            "\n-------------------------------------------------------------\n" + cookie7 +
            "\n-------------------------------------------------------------\n" + cookie8 +
            "\n-------------------------------------------------------------\n";
    }
}

/*
 * David Shi and Luke Fugere
 * Dec 18th 2024
 * Crumbl project
 */

 public class Client {
    public static void main(String[] args) {
        WeeklyCrumbl cookies  = new WeeklyCrumbl(30, 9, 2022);
        Cookie cookie = new Cookie(30, 9, 2022, 1);
        System.out.println(cookies);
    }
 }

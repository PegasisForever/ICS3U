/*
 * Class to calculate area of circles
 * Kevin JianQing Liu
 */
public class CircleAreaKevin {
    public static void main(String[] args) {
        float r = 2;
        float area = (float) (Math.PI * Math.pow(r, 2));
        System.out.println("The area of a circle that r=" + r + " is " + area);

        r = 5.6f;
        area = (float) (Math.PI * Math.pow(r, 2));
        System.out.println("The area of a circle that r=" + r + " is " + area);

        r = 11;
        area = (float) (Math.PI * Math.pow(r, 2));
        System.out.println("The area of a circle that r=" + r + " is " + area);

    }
}

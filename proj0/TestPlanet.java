/**
 *  Tests Planet's pairwiseForce() method
 */
public class TestPlanet {
    /**
     *  Tests pairwiseForce
     */
    public static void main(String[] args) {
        pairwiseForce();
    }
    /**
     *  Checks whether or not two Doubles are equal and prints the result.
     *
     *  @param  expected    Expected double
     *  @param  actual      Double received
     *  @param  label       Label for the 'test' case
     *  @param  eps         Tolerance for the double comparison.
     */
    private static void checkEquals(double expected, double actual, String label, double eps) {
        if (Math.abs(expected - actual) <= eps * Math.max(expected, actual)) {
            System.out.println("PASS: " + label + ": Expected " + expected + " and you gave " + actual);
        } else {
            System.out.println("FAIL: " + label + ": Expected " + expected + " and you gave " + actual);
        }
    }


    /**
     *  Checks the Planet class to make sure pairwise Force works.
     */
    private static void pairwiseForce() {
        System.out.println("Checking update...");

        Planet p1 = new Planet(1.0 * 1e12, 2.0 * 1e11, 0, 0, 2.0 * 1e30, "sun.gif");
        Planet p2 = new Planet(2.3 * 1e12, 9.5 * 1e11, 0, 0, 6.0 * 1e26, "saturn.gif");

        checkEquals(3.6 * 1e22, p1.calcForceExertedBy(p2), "Pairwise Force", 100);
    }
}

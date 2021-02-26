/**
 * This class is to run the simulation.
 */

public class NBody {
    // Read the radius from the file.
    public static double readRadius(String path) {
        In in = new In(path);
        int numPlanets = in.readInt();
        double radius = in.readDouble();

        return radius;
    }

    // Read in the planets.
    public static Planet[] readPlanets(String path) {
        In in = new In(path);
        int numPlanets = in.readInt();
        double radius = in.readDouble();

        Planet[] planets = new Planet[numPlanets];

        // Iterate the file and read all planets into array.
        for (int i = 0; i < numPlanets; i++) {

            double xP = in.readDouble();
            double yP = in.readDouble();
            double xV = in.readDouble();
            double yV = in.readDouble();
            double mass = in.readDouble();
            String name = in.readString();

            Planet p = new Planet(xP, yP, xV, yV, mass, name);
            planets[i] = p;
        }
        return planets;
    }

    public static void main(String[] args) {
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];

        Planet[] planets = readPlanets(filename);
        double radius = readRadius(filename);

        StdDraw.setScale(-radius, radius);
        StdDraw.picture(0, 0, "images/starfield.jpg");

        int numPlanets = planets.length;
        double[] xForces = new double[numPlanets];
        double[] yForces = new double[numPlanets];

        double t = 0;

        while (t <= T) {
            for (int i = 0; i < numPlanets; i++) {
                xForces[i] = planets[i].calcNetForceExertedByX(planets);
                yForces[i] = planets[i].calcNetForceExertedByY(planets);
            }
            for (int i = 0; i < numPlanets; i++) {
                planets[i].update(dt, xForces[i], yForces[i]);
            }
            StdDraw.enableDoubleBuffering();
            StdDraw.clear();
            for (Planet p : planets) {
                p.draw();
                //StdDraw.pause(10);
            }
            StdDraw.show();
            StdDraw.pause(1);
            t += dt;
        }

        StdOut.printf("%d\n", planets.length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < planets.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                    planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
                    planets[i].yyVel, planets[i].mass, planets[i].imgFileName);
        }

    }
}

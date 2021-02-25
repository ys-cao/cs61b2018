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

        double radius = readRadius(filename);
        Planet[] allPlanets = readPlanets(filename);

        int numPlanets = allPlanets.length;
        double time = 0.0;
        double[] xForces = new double[numPlanets];
        double[] yForces = new double[numPlanets];

        while (time < T) {
            for (int i = 0; i < numPlanets; i++) {
                double fx = allPlanets[i].calcNetForceExertedByX(allPlanets);
                double fy = allPlanets[i].calcNetForceExertedByX(allPlanets);

                xForces[i] = fx;
                yForces[i] = fy;
            }

            for (int i = 0; i < numPlanets; i++) {
                allPlanets[i].update(dt, xForces[i], yForces[i]);
            }

            StdDraw.setScale(-radius, radius);
            StdAudio.play("audio/2001.mid");

            StdDraw.enableDoubleBuffering();

            StdDraw.clear();

            StdDraw.picture(0, 0, "images/starfield.jpg");

            for (Planet p : allPlanets) {
                p.draw();
            }

            StdDraw.show();
            StdDraw.pause(10);

            time += dt;
        }

        StdOut.printf("%d\n", allPlanets.length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < allPlanets.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                    allPlanets[i].xxPos, allPlanets[i].yyPos, allPlanets[i].xxVel,
                    allPlanets[i].yyVel, allPlanets[i].mass, allPlanets[i].imgFileName);
        }
    }
}

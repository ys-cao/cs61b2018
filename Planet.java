
public class Planet {
    // Instance variables
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;

    // Gravitational Constant
    private static final double G = 6.67 * 1e-11;

    // Initialize an instance of Planet by passing values.
    public Planet(double xP, double yP, double xV,
                  double yV, double m, String img) {
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }

    // Initialize an identical Planet object.
    public Planet(Planet p) {
        xxPos = p.xxPos;
        yyPos = p.yyPos;
        xxVel = p.xxVel;
        yyVel = p.yyVel;
        mass = p.mass;
        imgFileName = p.imgFileName;
    }

    // Calculate the distance between two planets.
    public double calcDistance(Planet p) {
        double dd = (xxPos - p.xxPos) * (xxPos - p.xxPos) + (yyPos - p.yyPos) * (yyPos - p.yyPos);
        double r = Math.sqrt(dd);
        return r;
    }

    // Calculate the Force Exerted By Planet p.
    public double calcForceExertedBy(Planet p) {
        double r = calcDistance(p);
        double f = G * mass * p.mass / (r * r);
        return f;
    }

    // calcForceExertedByX
    public double calcForceExertedByX(Planet p) {
        double x = p.xxPos - xxPos;
        double f = calcForceExertedBy(p);
        double r = calcDistance(p);
        double fx = f * x / r;
        return fx;
    }

    // calcForceExertedByY
    public double calcForceExertedByY(Planet p) {
        double y = p.yyPos - yyPos;
        double f = calcForceExertedBy(p);
        double r = calcDistance(p);
        double fy = f * y / r;
        return fy;
    }

    // calcNetForceExertedByX
    public double calcNetForceExertedByX(Planet[] ps) {
        double fs = 0.0;
        for (Planet p : ps) {
            if (!equals(p)) {
                double f = calcForceExertedByX(p);
                fs = fs + f;
            }
        }
        return fs;
    }

    // calcNetForceExertedByY
    public double calcNetForceExertedByY(Planet[] ps) {
        double fs = 0.0;
        for (Planet p : ps) {
            if (!equals(p)) {
                double f = calcForceExertedByY(p);
                fs = fs + f;
            }
        }
        return fs;
    }

    // Update the planet's position and velocity instance variables.
    public void update(double dt, double fX, double fY) {
        // Calculate acceleration.
        double xa = fX / mass;
        double ya = fY / mass;

        // Update the velocity.
        xxVel = xxVel + xa * dt;
        yyVel = yyVel + ya * dt;

        // Update the positions.
        xxPos = xxPos + dt * xxVel;
        yyPos = yyPos + dt * yyVel;
    }

    // Draw planet at its position.
    public void draw() {
        StdDraw.enableDoubleBuffering();
        StdDraw.clear();
        StdDraw.picture(xxPos, yyPos, "images/" + imgFileName);
        StdDraw.show();
    }
}


package robotrace;

/**
 * Implementation of RaceTrack, creating a track from a parametric formula
 */
public class ParametricTrack extends RaceTrack {
    double pi = Math.PI;
    @Override
    protected Vector getPoint(double t) {
        double x;
        double y;
        x = 10*Math.cos(2*pi*t);
        y = 14*Math.sin(2*pi*t);
        Vector p = new Vector(x,y,1);
        return p;

    }

    @Override
    protected Vector getTangent(double t) {
        double x;
        double y;
        x = -20*pi*Math.sin(2*pi*t);
        y = 28*pi*Math.cos(2*pi*t);
        Vector p = new Vector(x,y,1);
        return p;

    }
    
}

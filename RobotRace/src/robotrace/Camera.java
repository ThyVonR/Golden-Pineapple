package robotrace;

import static java.lang.Math.cos;
import static java.lang.Math.sin;

/**
 * Implementation of a camera with a position and orientation. 
 */
class Camera {

    /** The position of the camera. */
    public Vector eye = new Vector(3f, 6f, 5f);

    /** The point to which the camera is looking. */
    public Vector center = Vector.O;

    /** The up vector. */
    public Vector up = Vector.Z;

    /**
     * Updates the camera viewpoint and direction based on the
     * selected camera mode.
     */
    public void update(GlobalState gs, Robot focus) {

        switch (gs.camMode) {
            
            // First person mode    
            case 1:
                setFirstPersonMode(gs, focus);
                break;
                
            // Default mode    
            default:
                setDefaultMode(gs);
        }
    }

    /**
     * Computes eye, center, and up, based on the camera's default mode.
     */
    private void setDefaultMode(GlobalState gs) {
        double x;
        double y;
        double z;
        double xy;
        center=gs.cnt;
        z=sin(gs.phi)*gs.vDist;
        xy=cos(gs.phi)*gs.vDist;
        x=sin(gs.theta)*xy;
        y=cos(gs.theta)*xy;
        eye.x=center.x+x;
        eye.y=center.y+y;
        eye.z=center.z+z;
    }

    /**
     * Computes eye, center, and up, based on the first person mode.
     * The camera should view from the perspective of the robot.
     */
    private void setFirstPersonMode(GlobalState gs, Robot focus) {
        
        eye=focus.getPosition().add(new Vector(0,0,focus.getHeight()));
        center=eye.add(focus.getDirection());
        
    }
}

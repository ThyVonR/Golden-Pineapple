package robotrace;

import com.jogamp.opengl.util.gl2.GLUT;
import javax.media.opengl.GL2;
import javax.media.opengl.glu.GLU;
import static javax.media.opengl.GL2.*;

/**
* Represents a Robot, to be implemented according to the Assignments.
*/
class Robot {
    
    /** The position of the robot. */
    public Vector position = new Vector(0, 0, 0);
    
    /** The direction in which the robot is running. */
    public Vector direction = new Vector(1, 0, 0);

    /** The material from which this robot is built. */
    private final Material material;
    
    

    /**
     * Constructs the robot with initial parameters.
     */
    public Robot(Material material) {
        this.material = material;

        
    }
    Robot() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Draws this robot (as a {@code stickfigure} if specified).
     */
    public void draw(GL2 gl, GLU glu, GLUT glut, float tAnim) {
        boolean whichArm;
        gl.glPushMatrix();
            gl.glScaled(2,2,2);
            gl.glTranslated(0,0,0.65);
            gl.glScaled(0.4,0.10,0.6);
            glut.glutSolidCube(1);
            gl.glScaled(5/2,10,5/3);
            gl.glTranslated(0,0,0.45);
            drawRobotHead(gl, glu, glut, tAnim);
            //glut.glutSolidSphere(0.25, 10, 10);
            gl.glTranslated(0,0,-0.45);
            gl.glTranslated(0.15,0,-0.5);
            drawRobotLeg(gl, glu, glut, tAnim);
            gl.glTranslated(-0.3, 0, 0);
            drawRobotLeg(gl, glu, glut, tAnim);
            gl.glTranslated(0.15,0,0.5);
            whichArm=true;
            
            
        gl.glPopMatrix();
    }
    
    private void drawRobotHead(GL2 gl, GLU glu, GLUT glut, float tAnim) {
        gl.glScaled(0.4, 0.1, 0.2);
        gl.glColor3d(255,0,0);
        glut.glutSolidSphere(1, 10, 10);
        gl.glColor3d(0,0,0);
        gl.glScaled(5/2,10,5);
        gl.glTranslated(0.4,0,0);
        gl.glRotated(90,0,1,0);
        drawRobotHeadCone(gl, glu, glut, tAnim);
        gl.glRotated(-90,0,1,0);
        gl.glTranslated(-0.8,0,0);
        gl.glRotated(-90,0,1,0);
        drawRobotHeadCone(gl, glu, glut, tAnim);
         gl.glRotated(90,0,1,0);
        gl.glTranslated(0.4,0,0);
    }
    
     private void drawRobotHeadCone(GL2 gl, GLU glu, GLUT glut, float tAnim) {
        gl.glScaled(0.075,0.075,0.2);
        glut.glutSolidCone(1, 1, 10, 10);
        gl.glScaled(40/3, 40/3, 5);
    }
     
    private void drawRobotLeg(GL2 gl, GLU glu, GLUT glut, float tAnim) {
        gl.glScaled(0.1,0.1,0.4);
        glut.glutSolidCube(1);
        gl.glScaled(10,10,5/2);
        gl.glTranslated(0,0,-0.4);
        gl.glScaled(0.1,0.1,0.4);        
        glut.glutSolidCube(1);
        gl.glScaled(10,10,5/2);
        gl.glTranslated(0,0.15,-0.2);
        gl.glScaled(0.2,0.3,0.1);
        glut.glutSolidCube(1);
        gl.glScaled(5,10/3,10);
        gl.glTranslated(0,-0.15,0.6);
    }        
    private void drawArm(GL2 gl, GLU glu, GLUT glut, float tAnim, boolean whichArm ) {
        
    }
    
    
}

package robotrace;

import com.jogamp.opengl.util.gl2.GLUT;
import java.util.Random;
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
    double directionRobot;
    double height;
    double widthX;
    double widthY;
    int speed;
    int lane;
    Random random = new Random();
    
    

    /**
     * Constructs the robot with initial parameters.
     */
    public Robot(Material material, double widthX, double widthY, double height) {
        this.material = material;
        this.height=height;
        this.widthX=widthX;
        this.widthY=widthY;
        speed=random.nextInt(10)+10;
        
        
    }
    Robot() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    //translates the oroginal variables to the scaled variables
    // int XYZ is used to determine which coördniate needs to be changed
    // 0=x;1=y;2=z
    public double scaleX(double original) {
        return (original/0.7)*widthX;
    }
    public double scaleY(double original) {
        return (original/0.1)*widthY;
    }
    public double scaleZ(double original) {
        return (original/1.65)*height;
    }
        /**
     * Draws this robot (as a {@code stickfigure} if specified).
     */
    public void draw(GL2 gl, GLU glu, GLUT glut, float tAnim, GlobalState gs ) {
        boolean whichArm;// used to determin which arm to draw, true is right, false is left from the point of view of the robot
        boolean whichLeg;// same as for the arm
        gl.glPushMatrix();

            gl.glTranslated(position.x,position.y,position.z);
                        gl.glRotated(directionRobot,0,0,1);
            gl.glTranslated(0,0,scaleZ(1.1));
            gl.glScaled(scaleX(0.4),scaleY(0.1),scaleZ(0.6));
            glut.glutSolidCube(1);
            gl.glScaled(1/scaleX(0.4),1/scaleY(0.1),1/scaleZ(0.6));
            gl.glTranslated(0,0,scaleZ(0.45));
            drawRobotHead(gl, glu, glut, tAnim);
            //glut.glutSolidSphere(0.25, 10, 10);
            gl.glTranslated(0,0,scaleZ(-0.45));
            gl.glTranslated(scaleX(0.15),0,scaleZ(-0.5));
            whichLeg=true;
            drawRobotLeg(gl, glu, glut, tAnim, whichLeg, gs);
            gl.glTranslated(scaleX(-0.3), 0, 0);
            whichLeg=false;
            drawRobotLeg(gl, glu, glut, tAnim, whichLeg, gs);
            gl.glTranslated(scaleX(0.15),0,scaleZ(0.5));
            gl.glTranslated(scaleX(0.2),0,scaleZ(0.3));
            whichArm=true;
            drawArm(gl, glu, glut, tAnim, whichArm);
            whichArm=false;
            gl.glTranslated(scaleX(-0.4),0,0);
            drawArm(gl, glu, glut, tAnim, whichArm);
            
        gl.glPopMatrix();
    }
    
    private void drawRobotHead(GL2 gl, GLU glu, GLUT glut, float tAnim) {
        gl.glPushMatrix();
        //gl.glRotated(directionRobot,0,0,1);
        gl.glScaled(scaleX(0.4),scaleY(0.1), scaleZ(0.2));
        gl.glColor3d(255,0,0);
        glut.glutSolidSphere(1, 10, 10);
        gl.glColor3d(0,0,0);
        gl.glScaled(1/scaleX(0.4),1/scaleY(0.1),1/scaleZ(0.2));
        gl.glTranslated(scaleX(0.4),0,0);
        gl.glRotated(90,0,1,0);
        drawRobotHeadCone(gl, glu, glut, tAnim);
        gl.glRotated(-90,0,1,0);
        gl.glTranslated(scaleX(-0.8),0,0);
        gl.glRotated(-90,0,1,0);
        drawRobotHeadCone(gl, glu, glut, tAnim);
         gl.glRotated(90,0,1,0);
        gl.glTranslated(scaleX(0.4),0,0);
        gl.glPopMatrix();
    }
    
     private void drawRobotHeadCone(GL2 gl, GLU glu, GLUT glut, float tAnim) {
        gl.glPushMatrix();
        //gl.glRotated(directionRobot,0,0,1);
        gl.glScaled(scaleX(0.075),scaleY(0.075),scaleZ(0.2));
        glut.glutSolidCone(1, 1, 10, 10);
        gl.glScaled(1/scaleX(0.075),1/scaleY(0.075),1/scaleZ(0.2));
        gl.glPopMatrix();
    }
     
    private void drawRobotLeg(GL2 gl, GLU glu, GLUT glut, float tAnim, boolean whichLeg, GlobalState gs) {
        gl.glPushMatrix();
        //gl.glRotated(directionRobot,0,0,1);
        //testSphere(gl, glut);
        if (whichLeg) {
            gl.glRotated(20*Math.sin(tAnim),1,0,0);
        }else{
            gl.glRotated(20*Math.cos(tAnim),1,0,0);
        }
        gl.glScaled(scaleX(0.1),scaleY(0.1),scaleZ(0.4));
        glut.glutSolidCube(1);
        gl.glScaled(1/scaleX(0.1),1/scaleY(0.1),1/scaleZ(0.4));
        gl.glTranslated(0,0,scaleZ(-0.4));
        //testSphere(gl, glut);
        gl.glScaled(scaleX(0.1),scaleY(0.1),scaleZ(0.4));        
        glut.glutSolidCube(1);
        gl.glScaled(1/scaleX(0.1),1/scaleY(0.1),1/scaleZ(0.4));
        gl.glTranslated(0,scaleY(0.15),scaleZ(-0.2));
        //testSphere(gl, glut);
        gl.glScaled(scaleX(0.2),scaleY(0.3),scaleZ(0.1));
        glut.glutSolidCube(1);
        gl.glScaled(1/scaleX(0.2),1/scaleY(0.3),1/scaleZ(0.1));
        gl.glTranslated(0,scaleY(-0.15),scaleZ(0.6));
        gl.glPopMatrix();
    }
    
    private void testSphere(GL2 gl, GLUT glut) {
        gl.glColor3d(255, 0, 0);
        //gl.glRotated(directionRobot,0,0,1);
        glut.glutSolidSphere(0.125, 10, 10);
        gl.glColor3d(0, 0, 0);
    }
    private void drawArm(GL2 gl, GLU glu, GLUT glut, float tAnim, boolean whichArm ) {
        gl.glPushMatrix();
        //gl.glRotated(directionRobot,0,0,1);
        gl.glScaled(scaleX(0.1),scaleY(0.1),scaleZ(0.1));
        gl.glColor3d(0,255,0);
        glut.glutSolidSphere(1,10,10);
        gl.glScaled(1/scaleX(0.1),1/scaleY(0.1),1/scaleZ(0.1));
        if (whichArm) {
            gl.glRotated(-22,0,1,0); 
        }else {
            gl.glRotated(22,0,1,0); 
        }
        gl.glTranslated(0,0,scaleZ(-0.2));
        gl.glScaled(scaleX(0.1),scaleY(0.1),scaleZ(0.4));
        glut.glutSolidCube(1);
        gl.glScaled(1/scaleX(0.1),1/scaleY(0.1),1/scaleZ(0.4));
        gl.glTranslated(0,0,scaleZ(-0.2));
        if (whichArm) {
            gl.glRotated(22,0,1,0);
        }else {
            gl.glRotated(-22,0,1,0); 
        }
        drawSecondArm(gl, glu, glut, tAnim, whichArm);
        if (whichArm){
            gl.glRotated(-22,0,1,0);
        }else {
            gl.glRotated(22,0,1,0); 
        }
        gl.glTranslated(0,0,scaleZ(0.4));
         if (whichArm) {
            gl.glRotated(22,0,1,0);
        }else {
            gl.glRotated(-22,0,1,0); 
        }
        gl.glPopMatrix();
    }
    private void drawSecondArm(GL2 gl, GLU glu, GLUT glut, float tAnim, boolean whichArm) {
        gl.glPushMatrix();
        //gl.glRotated(directionRobot,0,0,1);
        gl.glTranslated(0,0,scaleZ(-0.2));
        gl.glScaled(scaleX(0.1),scaleY(0.1),scaleZ(0.4));
        glut.glutSolidCube(1);
        gl.glScaled(1/scaleX(0.1),1/scaleY(0.1),1/scaleZ(0.4));
        gl.glTranslated(0,0,scaleZ(-0.25));
        gl.glScaled(scaleX(0.075),scaleY(0.1),scaleZ(0.075));
        glut.glutSolidSphere(1,10,10);
        gl.glScaled(1/scaleX(0.075),1/scaleY(0.1),1/scaleZ(0.075));
        gl.glTranslated(0,0,scaleZ(0.45));
        gl.glPopMatrix();
    }
    public double nextT(double tAnim) {
        return (tAnim%speed)/speed;
    }
    public void setPosition(Vector position) {
        this.position = position;
    }
    public void setDirection(Vector direction) {
        this.direction = direction;
        directionRobot=-90+Math.toDegrees(Math.atan2(direction.y,direction.x));
        
    }
    public Vector getDirection() {
        return direction;
    }
    public Vector getPosition() {
        return position;
    }
    public double getHeight() {
        return height;
    }
}

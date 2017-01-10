
package robotrace;

import com.jogamp.opengl.util.gl2.GLUT;
import static java.lang.Math.abs;
import javax.media.opengl.GL2;
import javax.media.opengl.glu.GLU;

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
    @Override
    public void draw(GL2 gl, GLU glu, GLUT glut) {
        Vector position = new Vector(0,0,0); 
        Vector secondPosition = new Vector(0,0,0);
        Vector tangentPosition = new Vector(0,0,0);
        double rc=0;
        double difX=0;
        double difY=0;
        gl.glTranslated(0,0,1);
        position= getPoint(0);
        for (double i=0.001;i<=1;i=i+0.01) {
            secondPosition = getPoint(i);
            tangentPosition = getTangent(i);
            rc=Math.atan(tangentPosition.y/tangentPosition.x);
            difX=abs(secondPosition.y-position.y)*Math.sin(90-rc);
            difY=abs(secondPosition.y-position.y)*Math.cos(90-rc);
            gl.glBegin(GL2.GL_TRIANGLES);
                gl.glColor3d(255,0,0);
                gl.glVertex3d(position.x,position.y,position.z);
                gl.glVertex3d(secondPosition.x,secondPosition.y,position.z);
                if(position.y>=0||secondPosition.y>=0) {
                    gl.glVertex3d(position.x-difX,position.y-difY,position.z);
                }else{
                    gl.glVertex3d(position.x+difX,position.y+difY,position.z);
                }
                
            gl.glEnd();
            gl.glBegin(GL2.GL_TRIANGLES);
                gl.glColor3d(0,255,0);
                gl.glVertex3d(secondPosition.x, secondPosition.y, position.z);
                if(position.y>0||secondPosition.y>0) {
                    gl.glVertex3d(position.x-difX,position.y-difY,position.z);
                    gl.glVertex3d(secondPosition.x-difX,secondPosition.y-difY,position.z);
                }else{
                    gl.glVertex3d(position.x+difX,position.y+difY,position.z);
                    gl.glVertex3d(secondPosition.x+difX,secondPosition.y+difY,position.z);
                }
            gl.glEnd();
            position=secondPosition;
        }
    }
}

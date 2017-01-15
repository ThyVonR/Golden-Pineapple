package robotrace;

import com.jogamp.opengl.util.gl2.GLUT;
import static java.lang.Math.cos;
import static java.lang.Math.sin;
import javax.media.opengl.GL2;
import javax.media.opengl.glu.GLU;

/**
 * Represents the terrain, to be implemented according to the Assignments.
 */
class Terrain {

    GlobalState gs;
    
    public Terrain(GlobalState gs) {
        this.gs = gs;
    }
    
    public double calcH(double varh1, double varh2) {
        double h = 0.3*cos(-0.4*varh1+0.5*varh2) + 0.7*cos(0.2*varh1-0.7*varh2);
        return h;
    }
    
    /**
     * Draws the terrain.
     * 0.3*cos(-0.4*(i-0.5*tileSize)-0.5*(j-0.5*tileSize)) + 0.7*cos(0.2*(i-0.5*tileSize)-0.7*(j-0.5*tileSize))
     */
    public void draw(GL2 gl, GLU glu, GLUT glut) {
        Vector minHPlane = new Vector(-20,-20,-1);
        Vector maxHPlane = new Vector(20,20,1);
        double tileSize =0.5;
        
        gl.glTranslated(-20,-20,0);
        gl.glTranslated(0.5*tileSize,0.5*tileSize,0);
        
        double i=-20;
        for (double j=-20; j<=20; j= j+tileSize) {
            double h = 0.3*cos(-0.4*i+0.5*j) + 0.7*cos(0.2*i-0.7*j);
            while (i<20) {
                gl.glBegin(GL2.GL_TRIANGLES);
                    gl.glNormal3d(0, 0, 1);
                    gl.glColor3d(1,0,1);
                    gl.glVertex3d(0.5*tileSize, 0.5*tileSize, calcH(i+0.5*tileSize,j+0.5*tileSize));
                    gl.glVertex3d(-0.5*tileSize, 0.5*tileSize, calcH(i-0.5*tileSize,j+0.5*tileSize));
                    gl.glVertex3d(0.5*tileSize, -0.5*tileSize, calcH(i+0.5*tileSize,j-0.5*tileSize));
                    gl.glColor3d(1,1,1);
                    gl.glVertex3d(-0.5*tileSize, -0.5*tileSize, calcH(i-0.5*tileSize,j-0.5*tileSize));
                    gl.glVertex3d(-0.5*tileSize, 0.5*tileSize, calcH(i-0.5*tileSize,j+0.5*tileSize));
                    gl.glVertex3d(0.5*tileSize, -0.5*tileSize, calcH(i+0.5*tileSize,j-0.5*tileSize));
                gl.glEnd();
                gl.glTranslated(tileSize,0,0);
                i = i+tileSize;
            }
            gl.glTranslated(-40,tileSize,0);
            i=-20;
        }
    }
}
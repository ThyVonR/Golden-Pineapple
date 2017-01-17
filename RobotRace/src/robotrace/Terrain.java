package robotrace;

import com.jogamp.opengl.util.gl2.GLUT;
import static java.lang.Math.cos;
import javax.media.opengl.GL2;
import javax.media.opengl.glu.GLU;

/**
 * Represents the terrain, to be implemented according to the Assignments.
 */
class Terrain {
    GlobalState gs;
    double[] treeArray;
    double[] randomCoord;
    int trees = 20;
    
    public Terrain(GlobalState gs) {
        this.gs = gs;
        treeArray = new double[3*trees];
        for (int treeW=0; treeW<3*trees; treeW=treeW+3) {
            treeArray[treeW] = Math.random()*0.2;
        }
        for (int treeH=1; treeH<3*trees; treeH=treeH+3) {
            treeArray[treeH] = Math.random()*0.5;
        }
        for (int treeC=2; treeC<3*trees; treeC=treeC+3) {
            treeArray[treeC] = Math.random()*0.5;
        }
        
        randomCoord = new double[2*trees];
        for (int randX=0; randX <2*trees; randX=randX+2){
            randomCoord[randX] = 40*(Math.random()-0.5);
        }
        for (int randY=1; randY <2*trees; randY=randY+2){
            randomCoord[randY] = 40*(Math.random()-0.5);
        }
    }
    
    public double calcH(double varh1, double varh2) {
        double h = 0.3*cos(-0.4*varh1+0.5*varh2) + 0.7*cos(0.2*varh1-0.7*varh2);
        return h;
    }
    
    public double calcW(double varw1, double varw2) {
        double w = 0.05*cos(varw1+varw2+gs.tAnim);
        return w;
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
            while (i<20) {
                gl.glBegin(GL2.GL_TRIANGLES);
                    //terrain
                    gl.glNormal3d(0, 0, 1);
                    gl.glColor3d(1,0,1);
                    gl.glVertex3d(0.5*tileSize, 0.5*tileSize, calcH(i+0.5*tileSize,j+0.5*tileSize));
                    gl.glVertex3d(-0.5*tileSize, 0.5*tileSize, calcH(i-0.5*tileSize,j+0.5*tileSize));
                    gl.glVertex3d(0.5*tileSize, -0.5*tileSize, calcH(i+0.5*tileSize,j-0.5*tileSize));
                    gl.glColor3d(1,1,1);
                    gl.glVertex3d(-0.5*tileSize, -0.5*tileSize, calcH(i-0.5*tileSize,j-0.5*tileSize));
                    gl.glVertex3d(-0.5*tileSize, 0.5*tileSize, calcH(i-0.5*tileSize,j+0.5*tileSize));
                    gl.glVertex3d(0.5*tileSize, -0.5*tileSize, calcH(i+0.5*tileSize,j-0.5*tileSize));
                    
                    //water
                    gl.glColor4d(1,0.8,0.8,0.8);
                    gl.glVertex3d(0.5*tileSize, 0.5*tileSize, calcW(i+0.5*tileSize,j+0.5*tileSize));
                    gl.glVertex3d(-0.5*tileSize, 0.5*tileSize, calcW(i-0.5*tileSize,j+0.5*tileSize));
                    gl.glVertex3d(0.5*tileSize, -0.5*tileSize, calcW(i+0.5*tileSize,j-0.5*tileSize));
                    gl.glColor4d(1,0.8f,0.8f,0.8f);
                    gl.glVertex3d(-0.5*tileSize, -0.5*tileSize, calcW(i-0.5*tileSize,j-0.5*tileSize));
                    gl.glVertex3d(-0.5*tileSize, 0.5*tileSize, calcW(i-0.5*tileSize,j+0.5*tileSize));
                    gl.glVertex3d(0.5*tileSize, -0.5*tileSize, calcW(i+0.5*tileSize,j-0.5*tileSize));
                gl.glEnd();
                gl.glTranslated(tileSize,0,0);
                i = i+tileSize;
            }
            gl.glTranslated(-40,tileSize,0);
            i=-20;
        }
        
    gl.glTranslated(20,-20,0.5);
    //width = (treeArray[tree] + 0.9), height = (treeArray[tree+1] + 0.75), color = (treeArray[tree+2] + 0.75)
    for (int tree=0; tree<trees; tree++) {
        gl.glPushMatrix();
        gl.glTranslated(randomCoord[tree],randomCoord[tree+1],0);
        if (calcH(randomCoord[tree],randomCoord[tree+1])>0.5){
        gl.glColor3d(0.8*(treeArray[tree+2] + 0.75),0.5*(treeArray[tree+2] + 0.75),0.4*(treeArray[tree+2] + 0.75));
        glut.glutSolidCylinder(0.5*(treeArray[tree] + 0.9),1.5*(treeArray[tree+1] + 0.75),10,10);
        gl.glTranslated(0,0,0.5*(treeArray[tree+1] + 0.75));
        gl.glColor3d(0.1,0.6*(treeArray[tree+2] + 0.75),0.1);
        glut.glutSolidCone(1*(treeArray[tree] + 0.9),2*(treeArray[tree+1] + 0.75),10,10);
        gl.glTranslated(0,0,1*(treeArray[tree+1] + 0.75));
        glut.glutSolidCone(0.75*(treeArray[tree] + 0.9),1.5*(treeArray[tree+1] + 0.75),10,10);
        gl.glPopMatrix();
        } else{
            gl.glPopMatrix();
        }
    }
    }
}

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
    double laneWith = 3.66;
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
        Vector secondTangentPosition = new Vector(0,0,0);
        double rc=0;
        double secondRc=0;
        double difX=0;
        double difY=0;
        double difSecondX=0;
        double difSecondY=0;
        gl.glTranslated(0,0,1);
        position= getPoint(0);
        tangentPosition = getTangent(0);
        for (double i=0.01;i<=1;i=i+0.01) {
            secondPosition = getPoint(i);
            secondTangentPosition = getTangent(i);
            rc=Math.atan(tangentPosition.y/tangentPosition.x);
            secondRc=Math.atan(secondTangentPosition.y/secondTangentPosition.x);
            difX=3.66*Math.sin(90-rc);
            difY=3.66*Math.cos(90-rc);
            difSecondX=3.66*Math.sin(90-secondRc);
            difSecondY=3.66*Math.cos(90-secondRc);
            //makes the track
            for(int j=0;j<4;j++) {
                
                gl.glBegin(GL2.GL_TRIANGLES);
                    gl.glColor3d(255,0,0);
                    if(position.y>=0) {
                        if (position.y<1&&position.x<0&&j>0) {
                            gl.glVertex3d(secondPosition.x+j*difSecondX,secondPosition.y+j*difSecondY,position.z);
                        }else{
                            gl.glVertex3d(secondPosition.x-j*difSecondX,secondPosition.y-j*difSecondY,position.z);
                        }
                        gl.glVertex3d(position.x-j*difX,position.y-j*difY,position.z);
                        
                        gl.glVertex3d(position.x-(j+1)*difX,position.y-(j+1)*difY,position.z);
                    }else{
                        gl.glVertex3d(position.x+j*difX,position.y+j*difY,position.z);
                        gl.glVertex3d(secondPosition.x+j*difSecondX,secondPosition.y+j*difSecondY,position.z);                        
                        gl.glVertex3d(position.x+(j+1)*difX,position.y+(j+1)*difY,position.z);
                    }
                    
                gl.glEnd();
                gl.glBegin(GL2.GL_TRIANGLES);
                    gl.glColor3d(0,255,0);
                    if(position.y>=0) {
                        //System.out.println(position.y+"if");
                        gl.glVertex3d(position.x-(j+1)*difX,position.y-(j+1)*difY,position.z);   
                        if(position.y<1&&position.x<0) {
                            gl.glVertex3d(secondPosition.x+(j+1)*difSecondX,secondPosition.y+(j+1)*difSecondY,position.z);
                            if(j>0) {
                                gl.glVertex3d(secondPosition.x+j*difSecondX,secondPosition.y+j*difSecondY,position.z);
                            }else{
                                gl.glVertex3d(secondPosition.x-j*difSecondX,secondPosition.y-j*difSecondY,position.z);
                            }
                        }else {
                            gl.glVertex3d(secondPosition.x-(j+1)*difSecondX,secondPosition.y-(j+1)*difSecondY,position.z);
                            gl.glVertex3d(secondPosition.x-j*difSecondX,secondPosition.y-j*difSecondY,position.z);                    
                        }
                    }else{
                        //System.out.println(position.y+"else");
                        gl.glVertex3d(secondPosition.x+j*difSecondX,secondPosition.y+j*difSecondY,position.z);
                        gl.glVertex3d(position.x+(j+1)*difX,position.y+(j+1)*difY,position.z);
                        gl.glVertex3d(secondPosition.x+(j+1)*difSecondX,secondPosition.y+(j+1)*difSecondY,position.z);
                    }
                 gl.glEnd();
            }
            //makes the wall
            gl.glBegin(GL2.GL_TRIANGLES);
                gl.glColor3d(255,0,0);
                gl.glVertex3d(position.x,position.y,1);
                gl.glVertex3d(position.x,position.y,-1);
                gl.glVertex3d(secondPosition.x,secondPosition.y,1);
                
            gl.glEnd();
            gl.glBegin(GL2.GL_TRIANGLES);
                gl.glColor3d(255,0,0);
                gl.glVertex3d(position.x,position.y,-1);
                gl.glVertex3d(secondPosition.x,secondPosition.y,-1);
                gl.glVertex3d(secondPosition.x,secondPosition.y,1);
            gl.glEnd();
            if (position.y<0) {
                gl.glBegin(GL2.GL_TRIANGLES);
                    gl.glColor3d(255,0,0);
                    gl.glVertex3d(position.x+4*difX,position.y+4*difY,1);
                    gl.glVertex3d(position.x+4*difX,position.y+4*difY,-1);
                    gl.glVertex3d(secondPosition.x+4*difSecondX,secondPosition.y+4*difSecondY,1);
                
                gl.glEnd();
                gl.glBegin(GL2.GL_TRIANGLES);
                    gl.glColor3d(255,0,0);
                    gl.glVertex3d(position.x+4*difX,position.y+4*difY,-1);
                    gl.glVertex3d(secondPosition.x+4*difSecondX,secondPosition.y+4*difSecondY,-1);
                    gl.glVertex3d(secondPosition.x+4*difSecondX,secondPosition.y+4*difSecondY,1);
                gl.glEnd();
            }else {
                gl.glBegin(GL2.GL_TRIANGLES);
                    gl.glColor3d(255,0,0);
                    gl.glVertex3d(position.x-4*difX,position.y-4*difY,1);
                    gl.glVertex3d(position.x-4*difX,position.y-4*difY,-1);
                    if (position.y<1&&position.x<0) {
                        gl.glVertex3d(secondPosition.x+4*difSecondX,secondPosition.y+4*difSecondY,1);
                    }else {
                        gl.glVertex3d(secondPosition.x-4*difSecondX,secondPosition.y-4*difSecondY,1);
                    }
                
                gl.glEnd();
                gl.glBegin(GL2.GL_TRIANGLES);
                    gl.glColor3d(255,0,0);
                    gl.glVertex3d(position.x-4*difX,position.y-4*difY,-1);
                    if (position.y<1&&position.x<0) {
                        gl.glVertex3d(secondPosition.x+4*difSecondX,secondPosition.y+4*difSecondY,1);
                        gl.glVertex3d(secondPosition.x+4*difSecondX,secondPosition.y+4*difSecondY,-1);
                    }else {
                        gl.glVertex3d(secondPosition.x-4*difSecondX,secondPosition.y-4*difSecondY,1);
                        gl.glVertex3d(secondPosition.x-4*difSecondX,secondPosition.y-4*difSecondY,-1);
                    }
                gl.glEnd();
            }
            position=secondPosition;
            tangentPosition = secondTangentPosition;
            
        }
        //makes the last part of the track
        for (int j=0;j<4;j++) {
            position=getPoint(0);
            tangentPosition=getTangent(0);
            rc=Math.atan(tangentPosition.y/tangentPosition.x);
            secondRc=Math.atan(secondTangentPosition.y/secondTangentPosition.x);
            difX=3.66*Math.sin(90-rc);
            difY=3.66*Math.cos(90-rc);
            difSecondX=3.66*Math.sin(90-secondRc);
            difSecondY=3.66*Math.cos(90-secondRc);
            gl.glBegin(GL2.GL_TRIANGLES);
                    gl.glColor3d(0,255,0);
                    gl.glVertex3d(position.x-j*difX,position.y-j*difY,1);
                    gl.glVertex3d(secondPosition.x+(j+1)*difSecondX,secondPosition.y+(j+1)*difSecondY,1);
                    gl.glVertex3d(position.x-(j+1)*difX,position.y-(j+1)*difY,1);
            gl.glEnd();
            gl.glBegin(GL2.GL_TRIANGLES);
                    gl.glColor3d(255,0,0);
                    gl.glVertex3d(position.x-j*difX,position.y-j*difY,1);
                    gl.glVertex3d(secondPosition.x+j*difSecondX,secondPosition.y+j*difSecondY,1);
                    gl.glVertex3d(secondPosition.x+(j+1)*difSecondX,secondPosition.y+(j+1)*difSecondY,1);
            gl.glEnd();
        }
        //makes the last part of the wall
        gl.glBegin(GL2.GL_TRIANGLES);
            gl.glColor3d(255,0,0);
            gl.glVertex3d(position.x,position.y,1);
            gl.glVertex3d(position.x,position.y,-1);
            gl.glVertex3d(secondPosition.x,secondPosition.y,1);                
        gl.glEnd();
        gl.glBegin(GL2.GL_TRIANGLES);
            gl.glColor3d(255,0,0);
            gl.glVertex3d(position.x,position.y,-1);
            gl.glVertex3d(secondPosition.x,secondPosition.y,-1);
            gl.glVertex3d(secondPosition.x,secondPosition.y,1);
        gl.glEnd();
        gl.glBegin(GL2.GL_TRIANGLES);
            gl.glColor3d(255,0,0);
            gl.glVertex3d(position.x-4*difX,position.y-4*difY,1);
            gl.glVertex3d(position.x-4*difX,position.y-4*difY,-1);
            gl.glVertex3d(secondPosition.x+4*difSecondX,secondPosition.y+4*difSecondY,1);                
        gl.glEnd();
        gl.glBegin(GL2.GL_TRIANGLES);
            gl.glColor3d(255,0,0);
            gl.glVertex3d(position.x-4*difX,position.y-4*difY,-1);
            gl.glVertex3d(secondPosition.x+4*difSecondX,secondPosition.y+4*difSecondY,-1);
            gl.glVertex3d(secondPosition.x+4*difSecondX,secondPosition.y+4*difSecondY,1);
        gl.glEnd();
            
    }
    
    @Override
    public Vector getLanePoint(int lane, double t){
        Vector position = new Vector(0,0,0);
        Vector tangent = new Vector(0,0,0);
        position = getPoint(t);
        tangent = getTangent(t);
        double rc=0;
        double difX=0;
        double difY=0; 
        position.z=3;
        rc=Math.atan(tangent.y/tangent.x);
        difX=3.66*Math.sin(90-rc);
        difY=3.66*Math.cos(90-rc);
        if(position.y>=0) {
            position.x=position.x-(lane+0.5)*difX;
            position.y=position.y-(lane+0.5)*difY;
        }else{
            position.x=position.x+(lane+0.5)*difX;
            position.y=position.y+(lane+0.5)*difY;
        }        
        return position;
    }
    
    /**
     * Returns the tangent of a lane at 0 <= t < 1.
     * Use this method to find the orientation of a robot on the track.
     */
    @Override
    public Vector getLaneTangent(int lane, double t){
        
        return getTangent(t);

    }
}

// simple vertex shader

void main()
{
    gl_Position    = gl_ModelViewProjectionMatrix * gl_Vertex;      // model view transform
    if (Terrain.calcH(Terrain.i,Terrain.j) >= 0.5) {
        gl_Color = vec4(1,0,0,0);
    }
    else if (Terrain.calcH(Terrain.i,Terrain.j) <= 0) {
        gl_Color = vec4(0,0,1,0);
    }
    else if (Terrain.calcH(Terrain.i,Terrain.j) > 0 && Terrain.calcH(Terrain.i,Terrain.j) < 0.5) {
        gl_Color = vec4(1,1,0,0);
    }
gl_FrontColor = gl_Color;
}
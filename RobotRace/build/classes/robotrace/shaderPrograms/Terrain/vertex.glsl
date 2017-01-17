// simple vertex shader

    vec4 trueColor (gl_Position) {
    float h = 0.3*cos(-0.4*gl_Position.x+0.5*gl_Position.y) + 0.7*cos(0.2*gl.Position.x-0.7*gl_Position.y);
    if (h <= 0.0) {
        trueColor = vec4(0,0,1,1);
        }
    else if (h > 0.0 && h <= 0.5) {
        trueColor = vec4(1,1,0,1);
        }
    else if (h > 0.5) {
        trueColor = vec4(0,1,0,1);
        }
    }
void main()
{
    gl_Position    = gl_ModelViewProjectionMatrix * gl_Vertex;      // model view transform
    gl_Color = trueColor;
    gl_FrontColor = gl_Color;
}
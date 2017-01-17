
varying float h = 0.3*cos(-0.4*gl_Position.x+0.5*gl_Position.y) + 0.7*cos(0.2*gl.Position.x-0.7*gl_Position.y);
    if (h <= 0) {
        gl_Color = vec4(0,0,1,1);
        }
    if (h > 0 && h <= 0.5) {
        gl_Color = vec4(1,1,0,1);
        }
    if (h > 0.5) {
        gl_Color = vec4(0,1,0,1);
        }

void main()
{
    gl_FragColor = gl_Color;
}

// simple vertex shader

vec4 trueColor (vec4 Position) {
    float h = 0.3*cos(-0.4*Position.x+0.5*Position.y) + 0.7*cos(0.2*Position.x-0.7*Position.y);
    if (h <= 0.0) {
        return vec4(0,0,-2*h,1);
    }
    else if (h > 0.0 && h <= 0.5) {
        return vec4(2*h,2*h,0,1);
    }
    else if (h > 0.5) {
        return vec4(-2*h+2,1,0,1);
    }
}

void main() {
    gl_Position    = gl_ModelViewProjectionMatrix * gl_Vertex;
    gl_FrontColor = trueColor(gl_Position);
}
void main()
{
    gl_MaterialParameters mat = gl_FrontMaterial;
    vec4 color = vec4(mat.ambient, 1);
    gl_FragColor = color + gl_FrontLightModelProduct.sceneColor;
}

varying vec3 N;
varying vec3 v;    
void main (void)  {     vec3 L = normalize(gl_LightSource[0].position.xyz - v);      
vec3 E = normalize(-v); // we are in Eye Coordinates, so EyePos is (0,0,0)     
vec3 R = normalize(-reflect(L,N));  
 
   //calculate Ambient Term:     
vec4 Iamb = gl_FrontLightProduct[0].ambient; 
   // write Total Color:     
gl_FragColor = gl_FrontLightModelProduct.sceneColor + Iamb}

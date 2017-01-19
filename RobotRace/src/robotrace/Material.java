package robotrace;

/**
* Materials that can be used for the robots.
*/
public enum Material {

    /** 
     * Gold material properties.
     * Modify the default values to make it look like gold.
     */
    GOLD (
            
        new float[] {(float)255/255, (float)215/255, (float)0/255, 1},
        new float[] {1, 1, 1, 1},
        80

    ),

    /**
     * Silver material properties.
     * Modify the default values to make it look like silver.
     */
    SILVER (
            
        new float[] {(float)180/255, (float)180/255, (float)180/255, 1},
        new float[] {1, 1, 1, 1},
        80

    ),

    /** 
     * Orange material properties.
     * Modify the default values to make it look like orange.
     */
    ORANGE (
            
        new float[] {(float)255/255, (float)140/255, (float)0/255, 1},
        new float[] {1, 1, 1, 1},
        100

    ),

    /**
     * Wood material properties.
     * Modify the default values to make it look like Wood.
     */
    WOOD (

        new float[] {(float)130/255, (float)82/255, (float)1/255, 1},
        new float[] {1, 1, 1, 1},
        120

    );

    /** The diffuse RGBA reflectance of the material. */
    float[] diffuse;

    /** The specular RGBA reflectance of the material. */
    float[] specular;
    
    /** The specular exponent of the material. */
    float shininess;

    /**
     * Constructs a new material with diffuse and specular properties.
     */
    private Material(float[] diffuse, float[] specular, float shininess) {
        this.diffuse = diffuse;
        this.specular = specular;
        this.shininess = shininess;
    }
}

package GraphicsObjects;


public class Vector4f {

    public float x = 0;
    public float y = 0;
    public float z = 0;
    public float a = 0;

    public Vector4f() {
        x = 0.0f;
        y = 0.0f;
        z = 0.0f;
        a = 0.0f;
    }

    public Vector4f(float x, float y, float z, float a) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.a = a;
    }

    //implement Vector plus a Vector
    public Vector4f PlusVector(Vector4f additional) {
        // Implement the addition of two vectors.
        // Return a new Vector4f object.
        // The components of the new vector are the sum of the components of
        // the current vector and the components of the given vector.
        return new Vector4f(x + additional.x, y + additional.y, z + additional.z, a + additional.a);
    }

    //implement Vector minus a Vector
    public Vector4f MinusVector(Vector4f minus) {
        // Implement the subtraction of two vectors.
        // Return a new Vector4f object.
        // The components of the new vector are the difference between the components of
        // the current vector and the components of the given vector.
        return new Vector4f(x - minus.x, y - minus.y, z - minus.z, a - minus.a);
    }

    //implement Vector plus a Point
    public Point4f PlusPoint(Point4f additional) {
        // Implement the addition of a vector and a point.
        // Return a new Point4f object.
        // The coordinates of the new point are the sum of the components of
        // the current vector and the coordinates of the given point.
        return new Point4f(x + additional.x, y + additional.y, z + additional.z, a + additional.a);
    }
    //Do not implement Vector minus a Point as it is undefined

    //Implement a Vector * Scalar
    public Vector4f byScalar(float scale) {
        // Implement the multiplication of a vector by a scalar.
        // Return a new Vector4f object.
        // The components of the new vector are the components of
        // the current vector multiplied by the given scalar.
        return new Vector4f(x * scale, y * scale, z * scale, a * scale);
    }

    //implement returning the negative of a  Vector
    public Vector4f NegateVector() {
        // Implement the negation of a vector.
        // Return a new Vector4f object.
        // The components of the new vector are the negation of the components of
        // the current vector.
        return new Vector4f(-x, -y, -z, -a);
    }

    //implement getting the length of a Vector
    public float length() {
        // L = sqrt(x^2 + y^2 + z^2 + a^2)
        return (float) Math.sqrt(x * x + y * y + z * z + a * a);
    }

    //implement getting the Normal  of a Vector
    public Vector4f Normal() {
        // Implement the normalization of a vector.
        // Return a new normalized Vector4f object.
        // The components of the new vector are the components of
        // the current vector divided by the length of the current vector.
        float len = length();
        if (len != 0) {
            return new Vector4f(x / len, y / len, z / len, a / len);
        }
        return new Vector4f();
    }

    //implement getting the dot product of Vector.Vector

    public float dot(Vector4f v) {
        // Implement the dot product of two vectors.
        // Return a scalar value.
        // The dot product of two vectors is the sum of
        // the products of their corresponding components.
        return (this.x * v.x + this.y * v.y + this.z * v.z + this.a * v.a);
    }

    // Implemented this for you to avoid confusion
    // as we will not normally  be using 4 float vector
    public Vector4f cross(Vector4f v) {
        // Implement the cross product of two vectors.
        // Return a new Vector4f object.
        float u0 = (this.y * v.z - z * v.y);
        float u1 = (z * v.x - x * v.z);
        float u2 = (x * v.y - y * v.x);
        float u3 = 0; //ignoring this for now
        return new Vector4f(u0, u1, u2, u3);
    }

}
	 
	   

/*

										MMMM                                        
										MMMMMM                                      
 										MM MMMM                                    
 										MMI  MMMM                                  
 										MMM    MMMM                                
 										MMM      MMMM                              
  										MM        MMMMM                           
  										MMM         MMMMM                         
  										MMM           OMMMM                       
   										MM             .MMMM                     
MMMMMMMMMMMMMMM                        MMM              .MMMM                   
MM   IMMMMMMMMMMMMMMMMMMMMMMMM         MMM                 MMMM                 
MM                  ~MMMMMMMMMMMMMMMMMMMMM                   MMMM               
MM                                  OMMMMM                     MMMMM            
MM                                                               MMMMM          
MM                                                                 MMMMM        
MM                                                                   ~MMMM      
MM                                                                     =MMMM    
MM                                                                        MMMM  
MM                                4 D                                      MMMMMM 
MM                                                                     MMMMMMMM 
MM                                                                  :MMMMMMMM   
MM                                                                MMMMMMMMM     
MM                                                              MMMMMMMMM       
MM                             ,MMMMMMMMMM                    MMMMMMMMM         
MM              IMMMMMMMMMMMMMMMMMMMMMMMMM                  MMMMMMMM            
MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM               ZMMMMMMMM              
MMMMMMMMMMMMMMMMMMMMMMMMMMMMM          MM$             MMMMMMMMM                
MMMMMMMMMMMMMM                       MMM            MMMMMMMMM                  
  									MMM          MMMMMMMM                     
  									MM~       IMMMMMMMM                       
  									MM      DMMMMMMMM                         
 								MMM    MMMMMMMMM                           
 								MMD  MMMMMMMM                              
								MMM MMMMMMMM                                
								MMMMMMMMMM                                  
								MMMMMMMM                                    
  								MMMM                                      
  								MM                                        
                             GlassGiant.com */

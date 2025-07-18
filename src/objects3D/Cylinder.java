package objects3D;

import org.lwjgl.opengl.GL11;

public class Cylinder {


    public Cylinder() {
    }

    // remember to use Math.PI isntead PI
    // Implement using notes and examine Tetrahedron to aid in the coding  look at lecture  7 , 7b and 8
    // Parameters:
    // - radius: the radius of the cylinder
    // - height: the height of the cylinder
    // - nSegments: the number of segments to approximate the cylinder
    public void drawCylinder(float radius, float height, int nSegments) {
        // Draw the top of the cylinder
        GL11.glBegin(GL11.GL_TRIANGLE_FAN);
        GL11.glNormal3f(0.0f, 0.0f, 1.0f);
        GL11.glVertex3f(0.0f, 0.0f, height);
        for (int i = 0; i <= nSegments; i++) {
            double angle = Math.PI * 2 * i / nSegments;
            float x = (float) Math.cos(angle) * radius;
            float y = (float) Math.sin(angle) * radius;
            GL11.glVertex3f(x, y, height);
        }
        GL11.glEnd();

        // Draw the bottom of the cylinder
        GL11.glBegin(GL11.GL_TRIANGLE_FAN);
        GL11.glNormal3f(0.0f, 0.0f, -1.0f);
        GL11.glVertex3f(0.0f, 0.0f, 0.0f);
        for (int i = nSegments; i >= 0; i--) {
            double angle = Math.PI * 2 * i / nSegments;
            float x = (float) Math.cos(angle) * radius;
            float y = (float) Math.sin(angle) * radius;
            GL11.glVertex3f(x, y, 0.0f);
        }
        GL11.glEnd();

        // Draw the sides of the cylinder
        GL11.glBegin(GL11.GL_QUAD_STRIP);
        for (int i = 0; i <= nSegments; i++) {
            double angle = Math.PI * 2 * i / nSegments;
            float x = (float) Math.cos(angle) * radius;
            float y = (float) Math.sin(angle) * radius;
            float nx = (float) Math.cos(angle);
            float ny = (float) Math.sin(angle);

            GL11.glNormal3f(nx, ny, 0.0f);
            GL11.glVertex3f(x, y, 0.0f);
            GL11.glVertex3f(x, y, height);
        }
        GL11.glEnd();
    }
}

package objects3D;

import org.lwjgl.opengl.GL11;

public class Sphere {

    public Sphere() {
    }

    /**
     * Draws a sphere using the specified radius, number of slices, and number of stacks.
     *
     * @param radius  The radius of the sphere.
     * @param nSlices The number of slices (longitudinal divisions) of the sphere.
     * @param nStacks The number of stacks (latitudinal divisions) of the sphere.
     */
    public void drawSphere(float radius, int nSlices, int nStacks) {
        float phi, theta;
        float dphi = (float) Math.PI / nStacks;
        float dtheta = 2.0f * (float) Math.PI / nSlices;

        // Draw the sphere
        for (int i = 0; i < nStacks; i++) {
            phi = -((float) Math.PI / 2.0f) + i * dphi;

            GL11.glBegin(GL11.GL_QUAD_STRIP);
            for (int j = 0; j <= nSlices; j++) {
                theta = j * dtheta;

                // First point
                float x1 = radius * (float) (Math.cos(phi) * Math.cos(theta));
                float y1 = radius * (float) (Math.cos(phi) * Math.sin(theta));
                float z1 = radius * (float) (Math.sin(phi));

                // Second point
                float x2 = radius * (float) (Math.cos(phi + dphi) * Math.cos(theta));
                float y2 = radius * (float) (Math.cos(phi + dphi) * Math.sin(theta));
                float z2 = radius * (float) (Math.sin(phi + dphi));

                // Set normal and vertices
                GL11.glNormal3f(x1 / radius, y1 / radius, z1 / radius);
                GL11.glVertex3f(x1, y1, z1);

                GL11.glNormal3f(x2 / radius, y2 / radius, z2 / radius);
                GL11.glVertex3f(x2, y2, z2);
            }
            GL11.glEnd();
        }
    }
}

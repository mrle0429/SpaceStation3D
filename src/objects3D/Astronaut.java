package objects3D;

import org.newdawn.slick.opengl.Texture;

import static org.lwjgl.opengl.GL11.*;

public class Astronaut {
    private Sphere sphere = new Sphere();
    private Cylinder cylinder = new Cylinder();
    private TexSphere texSphere = new TexSphere();
    private Texture helmetTexture;

    // Base Color
    private float[] white = {1.0f, 1.0f, 1.0f, 1.0f};
    private float[] visorColor = {0.2f, 0.4f, 0.8f, 0.6f};
    private float[] jointColor = {0.3f, 0.3f, 0.3f, 1.0f};
    private float[] metalColor = {0.8f, 0.8f, 0.8f, 1.0f};

    public Astronaut(Texture helmetTexture) {
        this.helmetTexture = helmetTexture;
    }

    public void drawAstronaut(float delta, boolean isMoving) {
        float walkAnimation = isMoving ? (float) Math.sin(delta) : 0;

        glPushMatrix();
        {
            // Body
            glColor3f(white[0], white[1], white[2]);
            sphere.drawSphere(1.0f, 32, 32);

            // Head
            glPushMatrix();
            {
                glTranslatef(0.0f, 1.5f, 0.0f);

                // Helmet
                glEnable(GL_TEXTURE_2D);
                glColor3f(white[0], white[1], white[2]);
                helmetTexture.bind();
                texSphere.drawSphere(0.5f, 32, 32, helmetTexture);
                glDisable(GL_TEXTURE_2D);
                //sphere.drawSphere(0.5f, 32, 32);

                // Visor
                glPushMatrix();
                {
                    glTranslatef(0.0f, 0.0f, 0.3f);
                    glEnable(GL_BLEND);
                    glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
                    glColor4f(visorColor[0], visorColor[1], visorColor[2], visorColor[3]);
                    sphere.drawSphere(0.4f, 32, 32);
                    glDisable(GL_BLEND);
                }
                glPopMatrix();
            }
            glPopMatrix();

            // Arm (with swinging animation)
            drawArm(-0.8f, 0.5f, 0.0f, 20 * walkAnimation);  // Left arm
            drawArm(0.8f, 0.5f, 0.0f, -20 * walkAnimation);  // Right arm

            // Leg (with swinging animation)    
            drawLeg(-0.4f, -1.0f, 0.0f, 30 * walkAnimation);  // Left leg
            drawLeg(0.4f, -1.0f, 0.0f, -30 * walkAnimation);  // Right leg

            drawOxygenSystem();
        }
        glPopMatrix();
    }

    private void drawArm(float x, float y, float z, float rotation) {
        glPushMatrix();
        {
            glTranslatef(x, y, z);
            glRotatef(rotation, 1, 0, 0); 
            glColor3f(white[0], white[1], white[2]);
            cylinder.drawCylinder(0.2f, 1.0f, 16);
        }
        glPopMatrix();
    }

    private void drawLeg(float x, float y, float z, float rotation) {
        glPushMatrix();
        {
            glTranslatef(x, y, z);
            glRotatef(rotation, 1, 0, 0);  // 前后摆动
            glColor3f(white[0], white[1], white[2]);
            cylinder.drawCylinder(0.25f, 1.2f, 16);
        }
        glPopMatrix();
    }

    private void drawOxygenSystem() {
        glPushMatrix();
        {
            glTranslatef(0.0f, 0.0f, -0.8f);

            // Main oxygen tank
            glColor3f(metalColor[0], metalColor[1], metalColor[2]);
            cylinder.drawCylinder(0.4f, 1.5f, 20);

            // Top connector
            glPushMatrix();
            {
                glTranslatef(0.0f, 0.8f, 0.0f);
                glColor3f(jointColor[0], jointColor[1], jointColor[2]);
                sphere.drawSphere(0.3f, 16, 16);
            }
            glPopMatrix();

            // Side pipe
            for (float offset : new float[]{-0.3f, 0.3f}) {
                glPushMatrix();
                {
                    glTranslatef(0.2f, offset, 0.2f);
                    glRotatef(45, 0, 0, 1);
                    glColor3f(jointColor[0], jointColor[1], jointColor[2]);
                    cylinder.drawCylinder(0.1f, 0.4f, 12);
                }
                glPopMatrix();
            }
        }
        glPopMatrix();
    }
}
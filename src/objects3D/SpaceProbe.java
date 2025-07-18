package objects3D;

import org.newdawn.slick.opengl.Texture;

import static org.lwjgl.opengl.GL11.*;

public class SpaceProbe {
    private TexSphere sphere = new TexSphere();
    private Cube cube = new Cube();
    private Cylinder cylinder = new Cylinder();
    private Texture probeTexture;
    private float rotation = 0;

    public SpaceProbe(Texture probeTexture) {
        this.probeTexture = probeTexture;
    }

    public void draw(float delta) {
        glPushMatrix();
        {
            // Main body
            glPushMatrix();
            {
                glEnable(GL_TEXTURE_2D);
                probeTexture.bind();
                glColor3f(0.8f, 0.8f, 0.8f);
                glScalef(1.0f, 0.8f, 0.8f);
                sphere.drawSphere(1.0f, 32, 32, probeTexture);
                glDisable(GL_TEXTURE_2D);
            }
            glPopMatrix();

            // Solar panel
            glPushMatrix();
            {
                glTranslatef(0.0f, 0.0f, 1.5f);
                glScalef(2.0f, 0.05f, 0.8f);
                glColor3f(0.2f, 0.2f, 0.8f);
                cube.drawCube();
            }
            glPopMatrix();

            // Symmetric solar panel
            glPushMatrix();
            {
                glTranslatef(0.0f, 0.0f, -1.5f);
                glScalef(2.0f, 0.05f, 0.8f);
                glColor3f(0.2f, 0.2f, 0.8f);
                cube.drawCube();
            }
            glPopMatrix();

            // Rotating antenna
            glPushMatrix();
            {
                rotation += delta * 45;  
                glTranslatef(1.2f, 0.0f, 0.0f);
                glRotatef(rotation, 1, 0, 0);

                glColor3f(0.6f, 0.6f, 0.6f);
                cylinder.drawCylinder(0.1f, 0.3f, 16);

                glTranslatef(0.0f, 0.3f, 0.0f);
                glRotatef(90, 1, 0, 0);
                glScalef(1.0f, 0.1f, 1.0f);
                glColor3f(0.7f, 0.7f, 0.7f);
                cylinder.drawCylinder(0.5f, 0.1f, 32);
            }
            glPopMatrix();

            // Scientific instruments
            glPushMatrix();
            {
                glTranslatef(-1.0f, 0.0f, 0.0f);
                glRotatef(90, 0, 1, 0);
                glColor3f(0.3f, 0.3f, 0.3f);
                cylinder.drawCylinder(0.2f, 0.8f, 16);
            }
            glPopMatrix();

            // Add probe glow effect
            drawProbeGlow(delta);
        }
        glPopMatrix();
    }

    private void drawProbeGlow(float delta) {
        float glowIntensity = (float) (Math.sin(delta * 2) * 0.2 + 0.8);

        glPushMatrix();
        {
            glEnable(GL_BLEND);
            glBlendFunc(GL_SRC_ALPHA, GL_ONE);

            // Status indicator
            glPushMatrix();
            {
                glTranslatef(0.8f, 0.5f, 0.0f);
                glColor4f(0.0f, 1.0f, 0.0f, glowIntensity);
                sphere.drawSphere(0.1f, 8, 8, probeTexture);
            }
            glPopMatrix();

            glDisable(GL_BLEND);
        }
        glPopMatrix();
    }
} 
package objects3D;

import org.newdawn.slick.opengl.Texture;

import static org.lwjgl.opengl.GL11.*;

public class SpaceElevator {
    private Cylinder cylinder = new Cylinder();
    private Cube cube = new Cube();
    private TexCube texCube = new TexCube();
    private TexSphere sphere = new TexSphere();
    private Texture elevatorTexture;
    private Texture elevatorTexture2;
    private float capsulePosition = 0;
    private float rotation = 0;

    public SpaceElevator(Texture texture, Texture texture2) {
        this.elevatorTexture = texture;
        this.elevatorTexture2 = texture2;
    }

    public void draw(float delta) {
        glPushMatrix();
        {
            // Bottom base
            drawBase(delta);

            // Main cable
            drawMainCable();

            // Moving elevator capsule
            capsulePosition = (float) (Math.sin(delta) * 30.0f);
            drawCapsule(capsulePosition, delta);

            // Top terminal station
            drawTopStation(delta);
        }
        glPopMatrix();
    }

    private void drawBase(float delta) {
        glPushMatrix();
        {
            // Base body
            glEnable(GL_TEXTURE_2D);
            elevatorTexture.bind();
            glScalef(2.0f, 0.5f, 2.0f);
            sphere.drawSphere(1.0f, 32, 32, elevatorTexture);
            glDisable(GL_TEXTURE_2D);

            // Four support columns
            for (int i = 0; i < 4; i++) {
                glPushMatrix();
                {
                    glRotatef(i * 90, 0, 1, 0);
                    glTranslatef(1.0f, -1.0f, 0.0f);
                    glRotatef(30, 0, 0, 1);
                    glColor3f(0.6f, 0.6f, 0.6f);
                    cylinder.drawCylinder(0.1f, 1.5f, 8);
                }
                glPopMatrix();
            }

            // Base antenna
            glPushMatrix();
            {
                rotation += delta * 45;
                glTranslatef(0.0f, 0.5f, 0.0f);
                glRotatef(rotation, 0, 1, 0);
                glColor3f(0.7f, 0.7f, 0.7f);
                cylinder.drawCylinder(0.05f, 0.5f, 8);

                // Antenna disk
                glTranslatef(0.0f, 0.5f, 0.0f);
                glRotatef(90, 1, 0, 0);
                glScalef(0.5f, 0.05f, 0.5f);
                cylinder.drawCylinder(0.5f, 0.1f, 16);
            }
            glPopMatrix();
        }
        glPopMatrix();
    }

    private void drawMainCable() {
        glPushMatrix();
        {
            glColor3f(0.7f, 0.7f, 0.7f);
            glRotatef(-90, 1, 0, 0);
            cylinder.drawCylinder(0.1f, 30.0f, 16);

            // Support ring
            for (int i = 0; i < 6; i++) {
                glPushMatrix();
                {
                    float height = i * 5.0f;
                    glTranslatef(0.0f, 0.0f, height);
                    glColor3f(0.5f, 0.5f, 0.5f);
                    cylinder.drawCylinder(0.3f, 0.2f, 16);
                }
                glPopMatrix();
            }
        }
        glPopMatrix();
    }

    private void drawCapsule(float height, float delta) {
        glPushMatrix();
        {
            glTranslatef(0.0f, height, 0.0f);

            // Capsule body
            glEnable(GL_TEXTURE_2D);
            elevatorTexture.bind();
            glScalef(0.8f, 0.8f, 0.8f);
            sphere.drawSphere(0.5f, 16, 16, elevatorTexture);
            glDisable(GL_TEXTURE_2D);

            // Solar panel
            glPushMatrix();
            {
                glTranslatef(0.0f, 0.0f, 0.8f);
                glScalef(1.0f, 0.05f, 0.4f);
                glColor3f(0.2f, 0.2f, 0.8f);
                texCube.drawTexCube(elevatorTexture2);
            }
            glPopMatrix();

            // Symmetrical solar panel
            glPushMatrix();
            {
                glTranslatef(0.0f, 0.0f, -0.8f);
                glScalef(1.0f, 0.05f, 0.4f);
                glColor3f(0.2f, 0.2f, 0.8f);
                texCube.drawTexCube(elevatorTexture2);
            }
            glPopMatrix();

            // Running indicator light
            drawGlowEffect(delta);
        }
        glPopMatrix();
    }

    private void drawTopStation(float delta) {
        glPushMatrix();
        {
            glTranslatef(0.0f, 30.0f, 0.0f);

            // Top station body
            glEnable(GL_TEXTURE_2D);
            elevatorTexture.bind();
            glScalef(1.0f, 0.3f, 1.0f);
            sphere.drawSphere(1.0f, 32, 32, elevatorTexture);
            glDisable(GL_TEXTURE_2D);

            // Top antenna
            glPushMatrix();
            {
                glTranslatef(0.0f, 0.5f, 0.0f);
                glRotatef(delta * 30, 0, 1, 0);
                glColor3f(0.6f, 0.6f, 0.6f);
                cylinder.drawCylinder(0.05f, 0.5f, 8);
            }
            glPopMatrix();
        }
        glPopMatrix();
    }

    private void drawGlowEffect(float delta) {
        float glowIntensity = (float) (Math.sin(delta * 5) * 0.3f + 0.7f);

        glPushMatrix();
        {
            glEnable(GL_BLEND);
            glBlendFunc(GL_SRC_ALPHA, GL_ONE);

            glTranslatef(0.5f, 0.0f, 0.0f);
            glColor4f(0.0f, 1.0f, 0.0f, glowIntensity);
            sphere.drawSphere(0.1f, 8, 8, null);

            glDisable(GL_BLEND);
        }
        glPopMatrix();
    }
}
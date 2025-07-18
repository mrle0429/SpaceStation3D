package objects3D;

import static org.lwjgl.opengl.GL11.*;

public class Spaceship {
    private TexSphere texSphere = new TexSphere();
    private Cube cube = new Cube();

    public void drawSpaceship(float delta) {
        glPushMatrix();
        {
            
            glScalef(70.0f, 70.0f, 70.0f);  

            // Main body
            glPushMatrix();
            {
                // Main body (elliptical)
                glPushMatrix();
                {
                    glScalef(2.0f, 1.0f, 1.0f);  
                    glColor3f(0.7f, 0.7f, 0.7f);
                    texSphere.drawSphere(1.0f, 32, 32, null);
                }
                glPopMatrix();

                // Decorative ring structure
                glPushMatrix();
                {
                    glScalef(2.1f, 1.05f, 1.05f);
                    glColor3f(0.4f, 0.4f, 0.4f);  // 深灰色装饰环
                    for (float offset : new float[]{-0.3f, 0.0f, 0.3f}) {
                        glPushMatrix();
                        glTranslatef(offset, 0.0f, 0.0f);
                        glScalef(0.05f, 0.95f, 0.95f);
                        texSphere.drawSphere(1.0f, 32, 32, null);
                        glPopMatrix();
                    }
                }
                glPopMatrix();

                drawWings();

                drawArmorPlates();

                // Cockpit (hemispherical)
                glPushMatrix();
                {
                    glTranslatef(1.5f, 0.5f, 0.0f);
                    glColor3f(0.3f, 0.5f, 0.8f);  
                    glScalef(0.5f, 0.5f, 0.5f);
                    texSphere.drawSphere(1.0f, 32, 32, null);

                    // Cockpit glass
                    glColor4f(0.3f, 0.5f, 0.8f, 0.8f);
                    glScalef(0.9f, 0.9f, 0.9f);
                    texSphere.drawSphere(1.0f, 32, 32, null);
                }
                glPopMatrix();

                // Left engine
                glPushMatrix();
                {
                    glTranslatef(-2.0f, -0.3f, 0.7f);
                    glRotatef(90, 0, 1, 0);
                    glColor3f(0.4f, 0.4f, 0.4f);
                    texSphere.drawSphere(0.5f, 16, 16, null);
                }
                glPopMatrix();

                // Right engine
                glPushMatrix();
                {
                    glTranslatef(-2.0f, -0.3f, -0.7f);
                    glRotatef(90, 0, 1, 0);
                    glColor3f(0.4f, 0.4f, 0.4f);
                    texSphere.drawSphere(0.5f, 16, 16, null);
                }
                glPopMatrix();

                // Engine effect
                drawEngineEffect(delta);
            }
            glPopMatrix();
        }
        glPopMatrix();
    }

    // Draw engine effect
    private void drawEngineEffect(float delta) {
        float engineGlow = (float) (Math.sin(delta * 10) * 0.2 + 0.8);  // Engine glow effect

        // Left engine effect
        glPushMatrix();
        {
            glTranslatef(-2.4f, -0.3f, 0.7f);
            glRotatef(90, 0, 1, 0);
            glColor4f(1.0f, 0.6f, 0.0f, engineGlow);  

            glEnable(GL_BLEND);
            glBlendFunc(GL_SRC_ALPHA, GL_ONE);
            texSphere.drawSphere(0.4f, 16, 16, null);
            glDisable(GL_BLEND);
        }
        glPopMatrix();

        // Right engine effect
        glPushMatrix();
        {
            glTranslatef(-2.4f, -0.3f, -0.7f);
            glRotatef(90, 0, 1, 0);
            glColor4f(1.0f, 0.6f, 0.0f, engineGlow);

            glEnable(GL_BLEND);
            glBlendFunc(GL_SRC_ALPHA, GL_ONE);
            texSphere.drawSphere(0.4f, 16, 16, null);
            glDisable(GL_BLEND);
        }
        glPopMatrix();
    }

    private void drawWings() {
        // Left wing
        glPushMatrix();
        {
            glTranslatef(-0.5f, 0.0f, 1.2f);
            glRotatef(20, 0, 1, 0);
            glScalef(1.5f, 0.1f, 0.8f);
            glColor3f(0.2f, 0.2f, 0.2f);
            cube.drawCube();
        }
        glPopMatrix();

        // Right wing
        glPushMatrix();
        {
            glTranslatef(-0.5f, 0.0f, -1.2f);
            glRotatef(-20, 0, 1, 0);
            glScalef(1.5f, 0.1f, 0.8f);
            glColor3f(0.2f, 0.2f, 0.2f);
            cube.drawCube();
        }
        glPopMatrix();
    }

    //  Draw armor plates
    private void drawArmorPlates() {
        // Top armor plate
        glPushMatrix();
        {
            glTranslatef(0.0f, 1.0f, 0.0f);
            glScalef(1.8f, 0.1f, 0.7f);
            glColor3f(0.5f, 0.2f, 0.2f);
            cube.drawCube();
        }
        glPopMatrix();


    }
} 
package objects3D;

import org.newdawn.slick.opengl.Texture;

import static org.lwjgl.opengl.GL11.*;

public class SpaceMiningStation {
    private TexSphere sphere = new TexSphere();
    private Cube cube = new Cube();
    private TexCube texCube = new TexCube();
    private Cylinder cylinder = new Cylinder();
    private TexCylinder texCylinder = new TexCylinder();  // 添加纹理圆柱体
    private Texture miningTexture;
    private Texture mininigTexture2;
    private float drillRotation = 0;
    private float armRotation = 0;

    public SpaceMiningStation(Texture texture, Texture texture2) {
        this.miningTexture = texture;
        this.mininigTexture2 = texture2;
    }

    public void draw(float delta) {
        glPushMatrix();
        {
            // Central processing unit (hexagon)
            drawProcessingUnit();

            // Three drilling arms
            for (int i = 0; i < 3; i++) {
                glPushMatrix();
                {
                    glRotatef(120 * i + armRotation, 0, 1, 0);
                    drawMiningArm(delta);
                }
                glPopMatrix();
            }

            // Ore storage units
            drawStorageUnits();

            // Energy collectors (similar to solar but different shape)
            drawEnergyCollectors(delta);

            // Status indicator
            drawStatusLights(delta);
        }
        glPopMatrix();
    }

    private void drawProcessingUnit() {
        glPushMatrix();
        {
            // Hexagon body
            for (int i = 0; i < 6; i++) {
                glPushMatrix();
                {
                    glEnable(GL_TEXTURE_2D);
                    miningTexture.bind();
                    glRotatef(60 * i, 0, 1, 0);
                    glTranslatef(1.0f, 0.0f, 0.0f);
                    glScalef(1.0f, 2.0f, 0.5f);
                    glColor3f(0.6f, 0.6f, 0.6f);
                    texCube.drawTexCube(miningTexture);
                    glDisable(GL_TEXTURE_2D);
                }
                glPopMatrix();
            }

            // Top and bottom lids
            glPushMatrix();
            {
                glEnable(GL_TEXTURE_2D);
                miningTexture.bind();
                glTranslatef(0.0f, 1.5f, 0.0f);
                glScalef(1.5f, 0.2f, 1.5f);
                sphere.drawSphere(1.0f, 32, 32, miningTexture);
                glDisable(GL_TEXTURE_2D);
            }
            glPopMatrix();

            // Add ring texture gear at the bottom
            glPushMatrix();
            {
                glEnable(GL_TEXTURE_2D);
                miningTexture.bind();
                glTranslatef(0.0f, -1.0f, 0.0f);
                glRotatef(90, 1, 0, 0);
                glColor3f(0.7f, 0.7f, 0.7f);
                texCylinder.drawCylinder(1.5f, 0.2f, 32, miningTexture);
                glDisable(GL_TEXTURE_2D);
            }
            glPopMatrix();
        }
        glPopMatrix();
    }

    private void drawMiningArm(float delta) {
        glPushMatrix();
        {
            // Mechanical arm base
            glTranslatef(2.0f, 0.0f, 0.0f);
            glColor3f(0.5f, 0.5f, 0.5f);
            cylinder.drawCylinder(0.3f, 0.5f, 16);

            // Extensible arm
            glPushMatrix();
            {
                float extension = (float) (Math.sin(delta * 2) * 0.5f + 1.5f);
                glTranslatef(0.0f, 0.0f, 0.0f);
                glScalef(0.2f, 0.2f, extension);
                cube.drawCube();
            }
            glPopMatrix();

            // Drill
            glPushMatrix();
            {
                drillRotation += delta * 320; // High speed rotation
                glTranslatef(0.0f, 0.0f, 2.0f);
                glRotatef(drillRotation, 0, 0, 1);
                glColor3f(0.7f, 0.7f, 0.7f);

                // Drill cone
                for (int i = 0; i < 8; i++) {
                    glPushMatrix();
                    {
                        glRotatef(45 * i, 0, 0, 1);
                        glTranslatef(0.2f, 0.0f, 0.0f);
                        glScalef(0.4f, 0.1f, 0.4f);
                        cube.drawCube();
                    }
                    glPopMatrix();
                }
            }
            glPopMatrix();
        }
        glPopMatrix();
    }

    private void drawStorageUnits() {
        glPushMatrix();
        {
            // Six storage units, arranged in a ring around the center
            for (int i = 0; i < 6; i++) {
                glPushMatrix();
                {
                    glRotatef(60 * i, 0, 1, 0);
                    glTranslatef(2.5f, -1.0f, 0.0f);

                    // Storage unit body (octagon)
                    glColor3f(0.4f, 0.4f, 0.4f);
                    for (int j = 0; j < 8; j++) {
                        glPushMatrix();
                        {
                            glEnable(GL_TEXTURE_2D);
                            mininigTexture2.bind();
                            glRotatef(45 * j, 1, 0, 0);
                            glTranslatef(0.0f, 0.3f, 0.0f);
                            glScalef(0.5f, 0.5f, 0.2f);
                            texCube.drawTexCube(mininigTexture2);
                            glDisable(GL_TEXTURE_2D);
                        }
                        glPopMatrix();
                    }

                    // Connection pipe
                    glColor3f(0.5f, 0.5f, 0.5f);
                    glRotatef(90, 0, 0, 1);
                    cylinder.drawCylinder(0.1f, 1.0f, 8);
                }
                glPopMatrix();
            }
        }
        glPopMatrix();
    }

    private void drawEnergyCollectors(float delta) {
        glPushMatrix();
        {
            // Three energy collectors, arranged in a Y shape
            for (int i = 0; i < 3; i++) {
                glPushMatrix();
                {
                    glRotatef(120 * i, 0, 1, 0);
                    glTranslatef(0.0f, 2.0f, 0.0f);

                    // Collector base
                    glColor3f(0.3f, 0.3f, 0.3f);
                    cylinder.drawCylinder(0.2f, 0.3f, 16);

                    // Energy collection panel (diamond)
                    glPushMatrix();
                    {
                        // Panel swings slightly over time, tracking the energy source
                        float panelAngle = (float) Math.sin(delta * 1.5) * 15;
                        glRotatef(panelAngle, 0, 0, 1);
                        glTranslatef(0.0f, 1.0f, 0.0f);

                        glColor3f(0.2f, 0.6f, 0.8f);
                        // Draw diamond panel
                        glBegin(GL_TRIANGLES);
                        {
                            // Upper triangle
                            glVertex3f(0.0f, 1.0f, 0.0f);
                            glVertex3f(-0.7f, 0.0f, 0.0f);
                            glVertex3f(0.7f, 0.0f, 0.0f);

                            // Lower triangle
                            glVertex3f(0.0f, -1.0f, 0.0f);
                            glVertex3f(-0.7f, 0.0f, 0.0f);
                            glVertex3f(0.7f, 0.0f, 0.0f);
                        }
                        glEnd();

                        // Energy flow effect
                        float energyGlow = (float) (Math.sin(delta * 5) * 0.3 + 0.7);
                        glEnable(GL_BLEND);
                        glBlendFunc(GL_SRC_ALPHA, GL_ONE);
                        glColor4f(0.2f, 0.8f, 1.0f, energyGlow * 0.3f);
                        sphere.drawSphere(0.3f, 8, 8, null);
                        glDisable(GL_BLEND);
                    }
                    glPopMatrix();
                }
                glPopMatrix();
            }
        }
        glPopMatrix();
    }

    private void drawStatusLights(float delta) {
        float workingGlow = (float) (Math.sin(delta * 3) * 0.2 + 0.8);

        glPushMatrix();
        {
            glEnable(GL_BLEND);
            glBlendFunc(GL_SRC_ALPHA, GL_ONE);

            // Add status indicator lights at each corner of the hexagon body
            for (int i = 0; i < 6; i++) {
                glPushMatrix();
                {
                    glRotatef(60 * i, 0, 1, 0);
                    glTranslatef(1.2f, 0.0f, 0.0f);

                    // Display different colors based on position
                    if (i % 2 == 0) {
                        glColor4f(1.0f, 0.5f, 0.0f, workingGlow); // Orange for working state
                    } else {
                        glColor4f(0.0f, 1.0f, 0.5f, workingGlow); // Green for energy state
                    }

                    sphere.drawSphere(0.1f, 8, 8, null);
                }
                glPopMatrix();
            }

            glDisable(GL_BLEND);
        }
        glPopMatrix();
    }
} 
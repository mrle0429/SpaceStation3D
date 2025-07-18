package objects3D;

import org.newdawn.slick.opengl.Texture;

import static org.lwjgl.opengl.GL11.*;

public class SpaceStation {
    private TexSphere texSphere = new TexSphere();
    private Cube cube = new Cube();
    private Cylinder cylinder = new Cylinder();
    private Texture stationTexture;
    private Texture solarPanelTexture;

    public SpaceStation(Texture stationTexture, Texture solarPanelTexture) {
        this.stationTexture = stationTexture;
        this.solarPanelTexture = solarPanelTexture;
    }

    public void drawSpaceStation(float delta) {
        glPushMatrix();
        {
            // Main body
            glPushMatrix();
            {
                glEnable(GL_TEXTURE_2D);
                stationTexture.bind();
                glColor3f(1.0f, 1.0f, 1.0f);
                glScalef(3.0f, 1.0f, 1.0f);
                texSphere.drawSphere(1.0f, 32, 32, stationTexture);
                glDisable(GL_TEXTURE_2D);
            }
            glPopMatrix();

            // Solar panel 1
            glPushMatrix();
            {
                glEnable(GL_TEXTURE_2D);
                solarPanelTexture.bind();
                glTranslatef(0.0f, 0.0f, 2.0f);
                glScalef(4.0f, 0.1f, 1.0f);
                cube.drawCube();
                glDisable(GL_TEXTURE_2D);
            }
            glPopMatrix();

            // Solar panel 2
            glPushMatrix();
            {
                glEnable(GL_TEXTURE_2D);
                solarPanelTexture.bind();
                glTranslatef(0.0f, 0.0f, -2.0f);
                glScalef(4.0f, 0.1f, 1.0f);
                cube.drawCube();
                glDisable(GL_TEXTURE_2D);
            }
            glPopMatrix();

            // Connection cabin
            glPushMatrix();
            {
                glEnable(GL_TEXTURE_2D);
                stationTexture.bind();
                glRotatef(90, 0, 0, 1);
                cylinder.drawCylinder(0.3f, 2.0f, 16);
                glDisable(GL_TEXTURE_2D);
            }
            glPopMatrix();

            // Add rotating antenna
            glPushMatrix();
            {
                glTranslatef(2.0f, 1.0f, 0.0f);
                glRotatef(delta * 45, 0, 0, 1); // 天线旋转
                glColor3f(0.7f, 0.7f, 0.7f);
                cylinder.drawCylinder(0.1f, 1.5f, 8);
            }
            glPopMatrix();
        }
        glPopMatrix();
    }
}
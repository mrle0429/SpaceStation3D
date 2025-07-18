package objects3D;

import org.newdawn.slick.opengl.Texture;

import static org.lwjgl.opengl.GL11.*;

public class Meteor {
    private TexSphere sphere = new TexSphere();
    private float rotation = 0;
    private Texture meteorTexture;
    private float orbitAngle = 0;  // 轨道角度

    public Meteor(Texture meteorTexture) {
        this.meteorTexture = meteorTexture;
    }

    public void draw(float delta) {
        glPushMatrix();
        {

            orbitAngle += delta * 30;  // Control orbital movement speed
            float radius = 3.0f;  // Orbital radius

            // Move along the orbit
            float moveX = (float) Math.cos(Math.toRadians(orbitAngle)) * radius;
            float moveY = (float) Math.sin(Math.toRadians(orbitAngle)) * radius;
            glTranslatef(moveX, moveY, 0);

            // Meteor rotation
            rotation += delta * 100;  // Increase rotation speed
            glRotatef(rotation, 1, 0.5f, 0.2f);

            // Draw meteor body
            glEnable(GL_TEXTURE_2D);
            meteorTexture.bind();
            sphere.drawSphere(1.0f, 16, 16, meteorTexture);
            glDisable(GL_TEXTURE_2D);

            // Add flame tail effect
            glPushMatrix();
            {
                glEnable(GL_BLEND);
                glBlendFunc(GL_SRC_ALPHA, GL_ONE);

                // Main tail
                glBegin(GL_TRIANGLE_STRIP);
                for (int i = 0; i < 20; i++) {
                    float alpha = 1.0f - (i / 20.0f);
                    glColor4f(1.0f, 0.3f + (1.0f - i / 20.0f) * 0.7f, 0.0f, alpha * 0.6f);

                    float offset = -i * 0.2f;
                    float width = 0.8f * (1.0f - i / 20.0f);

                    glVertex3f(offset, width, 0);
                    glVertex3f(offset, -width, 0);
                }
                glEnd();

                // Mars effect
                glPointSize(2.0f);
                glBegin(GL_POINTS);
                for (int i = 0; i < 30; i++) {
                    float sparkOffset = -(float) (Math.random() * 4.0f);
                    float sparkY = (float) (Math.random() * 1.0f - 0.5f);
                    float sparkZ = (float) (Math.random() * 1.0f - 0.5f);
                    float sparkAlpha = (float) Math.random();

                    glColor4f(1.0f, 0.6f, 0.0f, sparkAlpha * 0.8f);
                    glVertex3f(sparkOffset, sparkY, sparkZ);
                }
                glEnd();

                // Glow core
                glPushMatrix();
                {
                    glTranslatef(0.2f, 0, 0);
                    glColor4f(1.0f, 1.0f, 0.3f, 0.8f);
                    sphere.drawSphere(1.1f, 12, 12, null);
                }
                glPopMatrix();

                glDisable(GL_BLEND);
            }
            glPopMatrix();
        }
        glPopMatrix();
    }
}
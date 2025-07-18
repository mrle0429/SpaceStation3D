package objects3D;

import org.newdawn.slick.opengl.Texture;

import static org.lwjgl.opengl.GL11.*;

public class SingleTexCube {
    private static final float[] grassGreen = {
            0.365f,  // R (93/255)
            0.494f,  // G (126/255)
            0.133f   // B (34/255)
    };

    public void drawCube(Texture texture, int face) {
        float size = 1.0f;

        glBegin(GL_QUADS);
        {
            // 前面 (z = size)
            if (face == 0) {
                glEnable(GL_TEXTURE_2D);
                texture.bind();
                glColor3f(1.0f, 1.0f, 1.0f);
                glTexCoord2f(0.0f, 1.0f);
                glVertex3f(-size, -size, size);
                glTexCoord2f(1.0f, 1.0f);
                glVertex3f(size, -size, size);
                glTexCoord2f(1.0f, 0.0f);
                glVertex3f(size, size, size);
                glTexCoord2f(0.0f, 0.0f);
                glVertex3f(-size, size, size);
                glDisable(GL_TEXTURE_2D);
            } else {
                glColor3f(grassGreen[0], grassGreen[1], grassGreen[2]);
                glVertex3f(-size, -size, size);
                glVertex3f(size, -size, size);
                glVertex3f(size, size, size);
                glVertex3f(-size, size, size);
            }

            // 后面 (z = -size)
            if (face == 1) {
                glEnable(GL_TEXTURE_2D);
                texture.bind();
                glColor3f(1.0f, 1.0f, 1.0f);
                glTexCoord2f(1.0f, 1.0f);
                glVertex3f(-size, -size, -size);
                glTexCoord2f(1.0f, 0.0f);
                glVertex3f(-size, size, -size);
                glTexCoord2f(0.0f, 0.0f);
                glVertex3f(size, size, -size);
                glTexCoord2f(0.0f, 1.0f);
                glVertex3f(size, -size, -size);
                glDisable(GL_TEXTURE_2D);
            } else {
                glColor3f(grassGreen[0], grassGreen[1], grassGreen[2]);
                glVertex3f(-size, -size, -size);
                glVertex3f(-size, size, -size);
                glVertex3f(size, size, -size);
                glVertex3f(size, -size, -size);
            }

            // 顶面 (y = size)
            if (face == 2) {
                glEnable(GL_TEXTURE_2D);
                texture.bind();
                glColor3f(1.0f, 1.0f, 1.0f);
                glTexCoord2f(0.0f, 1.0f);
                glVertex3f(-size, size, -size);
                glTexCoord2f(0.0f, 0.0f);
                glVertex3f(-size, size, size);
                glTexCoord2f(1.0f, 0.0f);
                glVertex3f(size, size, size);
                glTexCoord2f(1.0f, 1.0f);
                glVertex3f(size, size, -size);
                glDisable(GL_TEXTURE_2D);
            } else {
                glColor3f(grassGreen[0], grassGreen[1], grassGreen[2]);
                glVertex3f(-size, size, -size);
                glVertex3f(-size, size, size);
                glVertex3f(size, size, size);
                glVertex3f(size, size, -size);
            }

            // 底面 (y = -size)
            if (face == 3) {
                glEnable(GL_TEXTURE_2D);
                texture.bind();
                glColor3f(1.0f, 1.0f, 1.0f);
                glTexCoord2f(1.0f, 1.0f);
                glVertex3f(-size, -size, -size);
                glTexCoord2f(0.0f, 1.0f);
                glVertex3f(size, -size, -size);
                glTexCoord2f(0.0f, 0.0f);
                glVertex3f(size, -size, size);
                glTexCoord2f(1.0f, 0.0f);
                glVertex3f(-size, -size, size);
                glDisable(GL_TEXTURE_2D);
            } else {
                glColor3f(grassGreen[0], grassGreen[1], grassGreen[2]);
                glVertex3f(-size, -size, -size);
                glVertex3f(size, -size, -size);
                glVertex3f(size, -size, size);
                glVertex3f(-size, -size, size);
            }

            // 右面 (x = size)
            if (face == 4) {
                glEnable(GL_TEXTURE_2D);
                texture.bind();
                glColor3f(1.0f, 1.0f, 1.0f);
                glTexCoord2f(1.0f, 1.0f);
                glVertex3f(size, -size, -size);
                glTexCoord2f(0.0f, 1.0f);
                glVertex3f(size, size, -size);
                glTexCoord2f(0.0f, 0.0f);
                glVertex3f(size, size, size);
                glTexCoord2f(1.0f, 0.0f);
                glVertex3f(size, -size, size);
                glDisable(GL_TEXTURE_2D);
            } else {
                glColor3f(grassGreen[0], grassGreen[1], grassGreen[2]);
                glVertex3f(size, -size, -size);
                glVertex3f(size, size, -size);
                glVertex3f(size, size, size);
                glVertex3f(size, -size, size);
            }

            // 左面 (x = -size)
            if (face == 5) {
                glEnable(GL_TEXTURE_2D);
                texture.bind();
                glColor3f(1.0f, 1.0f, 1.0f);
                glTexCoord2f(0.0f, 1.0f);
                glVertex3f(-size, -size, -size);
                glTexCoord2f(0.0f, 0.0f);
                glVertex3f(-size, -size, size);
                glTexCoord2f(1.0f, 0.0f);
                glVertex3f(-size, size, size);
                glTexCoord2f(1.0f, 1.0f);
                glVertex3f(-size, size, -size);
                glDisable(GL_TEXTURE_2D);
            } else {
                glColor3f(grassGreen[0], grassGreen[1], grassGreen[2]);
                glVertex3f(-size, -size, -size);
                glVertex3f(-size, -size, size);
                glVertex3f(-size, size, size);
                glVertex3f(-size, size, -size);
            }
        }
        glEnd();
    }
} 
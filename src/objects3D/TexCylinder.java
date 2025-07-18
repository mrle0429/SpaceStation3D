package objects3D;

import org.newdawn.slick.opengl.Texture;

import static org.lwjgl.opengl.GL11.*;

public class TexCylinder {
    private float height = 1.0f;
    private float radius = 1.0f;
    private int slices = 32;

    public void drawCylinder(float radius, float height, int slices, Texture texture) {
        float angleStep = 360.0f / slices;
        float texStep = 1.0f / slices;

        // 侧面
        glBegin(GL_QUAD_STRIP);
        for (int i = 0; i <= slices; i++) {
            float angle = (float) Math.toRadians(i * angleStep);
            float x = (float) Math.cos(angle) * radius;
            float z = (float) Math.sin(angle) * radius;
            float texX = i * texStep;

            glTexCoord2f(texX, 0);
            glVertex3f(x, 0, z);

            glTexCoord2f(texX, 1);
            glVertex3f(x, height, z);
        }
        glEnd();

        // 顶面和底面
        for (int j = 0; j < 2; j++) {
            float y = j * height;
            float texY = j;

            glBegin(GL_TRIANGLE_FAN);
            glTexCoord2f(0.5f, 0.5f);
            glVertex3f(0, y, 0);

            for (int i = 0; i <= slices; i++) {
                float angle = (float) Math.toRadians(i * angleStep);
                float x = (float) Math.cos(angle) * radius;
                float z = (float) Math.sin(angle) * radius;
                float texX = (float) Math.cos(angle) * 0.5f + 0.5f;
                float texZ = (float) Math.sin(angle) * 0.5f + 0.5f;

                glTexCoord2f(texX, texZ);
                glVertex3f(x, y, z);
            }
            glEnd();
        }
    }
} 
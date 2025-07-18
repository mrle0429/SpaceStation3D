package objects3D;

import GraphicsObjects.Point4f;
import GraphicsObjects.Vector4f;
import org.newdawn.slick.opengl.Texture;

import static org.lwjgl.opengl.GL11.*;

public class TexCube {

    /**
     * Draws a textured cube using the provided texture.
     *
     * @param myTexture The texture to be applied to the cube.
     */
    public void drawTexCube(Texture myTexture) {
        // Define the vertices of the cube
        Point4f vertices[] = {
                new Point4f(-1.0f, -1.0f, -1.0f, 1.0f), // 0
                new Point4f(-1.0f, -1.0f, 1.0f, 1.0f),  // 1
                new Point4f(-1.0f, 1.0f, -1.0f, 1.0f),  // 2
                new Point4f(-1.0f, 1.0f, 1.0f, 1.0f),   // 3
                new Point4f(1.0f, -1.0f, -1.0f, 1.0f),  // 4
                new Point4f(1.0f, -1.0f, 1.0f, 1.0f),   // 5
                new Point4f(1.0f, 1.0f, -1.0f, 1.0f),   // 6
                new Point4f(1.0f, 1.0f, 1.0f, 1.0f)     // 7
        };

        // Define the texture coordinates for each face
        float[][][] faceTexCoords = {
                // Front face
                {{0.0f, 0.0f}, {1.0f, 0.0f}, {1.0f, 1.0f}, {0.0f, 1.0f}},
                // Back face
                {{1.0f, 0.0f}, {0.0f, 0.0f}, {0.0f, 1.0f}, {1.0f, 1.0f}},
                // Left face
                {{1.0f, 0.0f}, {0.0f, 0.0f}, {0.0f, 1.0f}, {1.0f, 1.0f}},
                // right face
                {{0.0f, 0.0f}, {1.0f, 0.0f}, {1.0f, 1.0f}, {0.0f, 1.0f}},
                // Top face
                {{0.0f, 1.0f}, {0.0f, 0.0f}, {1.0f, 0.0f}, {1.0f, 1.0f}},
                // Bottom face
                {{0.0f, 0.0f}, {0.0f, 1.0f}, {1.0f, 1.0f}, {1.0f, 0.0f}}
        };

        int faces[][] = {
                {0, 1, 3, 2},
                {4, 6, 7, 5},
                {0, 2, 6, 4},
                {1, 5, 7, 3},
                {2, 3, 7, 6},
                {0, 4, 5, 1}
        };

        // Enable texturing
        glEnable(GL_TEXTURE_2D);
        myTexture.bind();

        // Draw the cube
        glBegin(GL_QUADS);
        for (int face = 0; face < 6; face++) {
            // Calculate the normal for the face
            Vector4f v1 = vertices[faces[face][1]].MinusPoint(vertices[faces[face][0]]);
            Vector4f v2 = vertices[faces[face][3]].MinusPoint(vertices[faces[face][0]]);
            Vector4f normal = v1.cross(v2).Normal();
            glNormal3f(normal.x, normal.y, normal.z);

            // Set the texture coordinates and vertices for the face
            for (int i = 0; i < 4; i++) {
                glTexCoord2f(faceTexCoords[face][i][0], faceTexCoords[face][i][1]);
                glVertex3f(vertices[faces[face][i]].x, vertices[faces[face][i]].y, vertices[faces[face][i]].z);
            }
        }
        glEnd();

        glDisable(GL_TEXTURE_2D);
    }
}
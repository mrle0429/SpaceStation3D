package objects3D;

import GraphicsObjects.Utils;
import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;

import static org.lwjgl.opengl.GL11.*;


public class Human {

    // basic colours
    static float black[] = {0.0f, 0.0f, 0.0f, 1.0f};
    static float white[] = {1.0f, 1.0f, 1.0f, 1.0f};

    static float grey[] = {0.5f, 0.5f, 0.5f, 1.0f};
    static float spot[] = {0.1f, 0.1f, 0.1f, 0.5f};

    // primary colours
    static float red[] = {1.0f, 0.0f, 0.0f, 1.0f};
    static float green[] = {0.0f, 1.0f, 0.0f, 1.0f};
    static float blue[] = {0.0f, 0.0f, 1.0f, 1.0f};

    // secondary colours
    static float yellow[] = {1.0f, 1.0f, 0.0f, 1.0f};
    static float magenta[] = {1.0f, 0.0f, 1.0f, 1.0f};
    static float cyan[] = {0.0f, 1.0f, 1.0f, 1.0f};

    // other colours
    static float orange[] = {1.0f, 0.5f, 0.0f, 1.0f, 1.0f};
    static float brown[] = {0.5f, 0.25f, 0.0f, 1.0f, 1.0f};
    static float dkgreen[] = {0.0f, 0.5f, 0.0f, 1.0f, 1.0f};
    static float pink[] = {1.0f, 0.6f, 0.6f, 1.0f, 1.0f};

    private Texture skinTexture;
    private Texture footTexture;

    public Human(Texture skinTexture, Texture footTexture) {
        this.skinTexture = skinTexture;
        this.footTexture = footTexture;

    }


    public void drawHuman(float delta, boolean GoodAnimation) {
        float theta = (float) (delta * 2 * Math.PI);
        float LimbRotation;
        float bodyRotation;
        float headRotation;
        float kneeRotation;
        float bodyBob;

        if (GoodAnimation) {
            // limb swing
            LimbRotation = (float) Math.cos(theta) * 45;
            // body swing
            bodyRotation = (float) Math.cos(theta) * 10;
            // head swing
            headRotation = (float) Math.cos(theta) * 5;
            // knee swing
            kneeRotation = Math.abs((float) Math.cos(theta) * 30);
            // body bob
            bodyBob = (float) Math.sin(theta) * 0.1f;
        } else {
            LimbRotation = 0;
            bodyRotation = 0;
            headRotation = 0;
            kneeRotation = 0;
            bodyBob = 0;
        }

        Sphere sphere = new Sphere();
        Cylinder cylinder = new Cylinder();
        TexSphere texSphere = new TexSphere();

        TexSphere texSphere1 = new TexSphere();

        // begin draw
        glPushMatrix();
        {
            // base position and body bob, body rotation
            glTranslatef(0.0f, 0.5f + bodyBob, 0.0f);
            glRotatef(bodyRotation, 0.0f, 1.0f, 0.0f);


            // chest
            glColor3f(green[0], green[1], cyan[2]);
            glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(green));
            sphere.drawSphere(0.48f, 32, 32);
            glPushMatrix();
            {
                glTranslatef(0.0f, 0.4f, 0.0f);
                glColor3f(green[0], green[1], green[2]);
                sphere.drawSphere(0.45f, 32, 32);

                // neck
                glColor3f(orange[0], orange[1], orange[2]);
                glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(orange));
                glPushMatrix();
                {
                    glTranslatef(0.0f, 0.1f, 0.0f);
                    glRotatef(-90.0f, 1.0f, 0.0f, 0.0f);
                    cylinder.drawCylinder(0.15f, 1.0f, 32);

                    // textured head
                    glPushMatrix();
                    {
                        glTranslatef(0.0f, 0.0f, 1.0f);

                        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_CLAMP);
                        Color.white.bind();
                        skinTexture.bind();

                        glEnable(GL_TEXTURE_2D);
                        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);

                        glDisable(GL_COLOR_MATERIAL);

                        texSphere.drawSphere(0.45f, 32, 32, skinTexture);
                        glRotatef(headRotation, 0.0f, 1.0f, 0.0f);

                        glEnable(GL_COLOR_MATERIAL);
                        glDisable(GL_TEXTURE_2D);

                        glPopMatrix();
                    }
                    glPopMatrix();

                    // left shoulder
                    glColor3f(blue[0], blue[1], blue[2]);
                    glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(blue));
                    glPushMatrix();
                    {
                        glTranslatef(0.5f, 0.38f, 0.0f);
                        sphere.drawSphere(0.22f, 32, 32);
                        glRotatef(LimbRotation * 0.2f, 0.0f, 0.0f, 1.0f);

                        // left arm
                        glColor3f(yellow[0], yellow[1], pink[2]);
                        glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(orange));
                        glPushMatrix();
                        {
                            glTranslatef(0.0f, 0.0f, 0.0f);
                            glRotatef(90.0f, 1.0f, 0.0f, 0.0f);

                            glRotatef(-LimbRotation, 1.0f, 0.0f, 0.0f);
                            cylinder.drawCylinder(0.15f, 0.7f, 32);

                            // left elbow
                            glColor3f(blue[0], blue[1], blue[2]);
                            glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(blue));
                            glPushMatrix();
                            {
                                glTranslatef(0.0f, 0.0f, 0.75f);
                                sphere.drawSphere(0.18f, 32, 32);

                                // left forearm
                                glColor3f(orange[0], orange[1], orange[2]);
                                glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(orange));
                                glPushMatrix();
                                {
                                    glTranslatef(0.0f, 0.0f, 0.0f);
                                    glRotatef(90.0f, 1.0f, 0.0f, 0.0f);
                                    cylinder.drawCylinder(0.11f, 0.7f, 32);

                                    // left hand
                                    glColor3f(blue[0], blue[1], blue[2]);
                                    glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(blue));
                                    glPushMatrix();
                                    {
                                        glTranslatef(0.0f, 0.0f, 0.75f);
                                        sphere.drawSphere(0.2f, 32, 32);

                                    }
                                    glPopMatrix();
                                }
                                glPopMatrix();
                            }
                            glPopMatrix();
                        }
                        glPopMatrix();
                    }
                    glPopMatrix();


                    // right shoulder
                    glColor3f(blue[0], blue[1], blue[2]);
                    glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(blue));
                    glPushMatrix();
                    {
                        glTranslatef(-0.5f, 0.38f, 0.0f);
                        sphere.drawSphere(0.22f, 32, 32);
                        glRotatef(-LimbRotation * 0.2f, 0.0f, 0.0f, 1.0f);

                        // right arm
                        glColor3f(yellow[0], yellow[1], pink[2]);
                        glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(orange));
                        glPushMatrix();
                        {

                            glRotatef(90.0f, 1.0f, 0.0f, 0.0f);
                            glRotatef(LimbRotation, 1.0f, 0.0f, 0.0f);
                            cylinder.drawCylinder(0.15f, 0.7f, 32);

                            // right elbow
                            glColor3f(blue[0], blue[1], blue[2]);
                            glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(blue));
                            glPushMatrix();
                            {
                                glTranslatef(0.0f, 0.0f, 0.75f);
                                sphere.drawSphere(0.18f, 32, 32);

                                // right forearm
                                glColor3f(orange[0], orange[1], orange[2]);
                                glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(orange));
                                glPushMatrix();
                                {
                                    glTranslatef(0.0f, 0.0f, 0.0f);
                                    glRotatef(90.0f, 1.0f, 0.0f, 0.0f);
                                    cylinder.drawCylinder(0.11f, 0.7f, 32);

                                    // right hand
                                    glColor3f(blue[0], blue[1], blue[2]);
                                    glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(blue));
                                    glPushMatrix();
                                    {
                                        glTranslatef(0.0f, 0.0f, 0.75f);
                                        sphere.drawSphere(0.2f, 32, 32);
                                    }
                                    glPopMatrix();
                                }
                                glPopMatrix();
                            }
                            glPopMatrix();
                        }
                        glPopMatrix();
                    }
                    glPopMatrix();
                }
                glPopMatrix();

                // left hip
                glColor3f(magenta[0], magenta[1], blue[2]);
                glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(blue));
                glPushMatrix();
                {
                    glTranslatef(-0.5f, -0.2f, 0.0f);

                    sphere.drawSphere(0.25f, 32, 32);

                    // left high leg
                    glColor3f(orange[0], orange[1], orange[2]);
                    glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(orange));
                    glPushMatrix();
                    {

                        glRotatef(90.0f, 1.0f, 0.0f, 0.0f);
                        glRotatef(-LimbRotation, 1.0f, 0.0f, 0.0f);
                        cylinder.drawCylinder(0.15f, 1.1f, 32);
                        // left knee
                        glColor3f(blue[0], blue[1], blue[2]);
                        glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(blue));
                        glPushMatrix();
                        {
                            glTranslatef(0.0f, 0.0f, 1.0f);
                            glRotatef(-kneeRotation, 1.0f, 0.0f, 0.0f);
                            sphere.drawSphere(0.22f, 32, 32);

                            // left low leg
                            glColor3f(orange[0], orange[1], orange[2]);
                            glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(orange));
                            glPushMatrix();
                            {
                                glTranslatef(0.0f, 0.0f, 0.0f);
                                cylinder.drawCylinder(0.12f, 0.8f, 32);

                                // left foot
                                glColor3f(blue[0], blue[1], blue[2]);
                                glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(blue));
                                glPushMatrix();
                                {

                                    glTranslatef(0.0f, 0.0f, 0.75f);
                                    glEnable(GL_TEXTURE_2D);
                                    Color.white.bind();
                                    footTexture.bind();
                                    glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_CLAMP);
                                    glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
                                    glDisable(GL_COLOR_MATERIAL);
                                    texSphere.drawSphere(0.25f, 32, 32, footTexture);
                                    glEnable(GL_COLOR_MATERIAL);
                                    glDisable(GL_TEXTURE_2D);


                                }
                                glPopMatrix();
                            }
                            glPopMatrix();
                        }
                        glPopMatrix();
                    }
                    glPopMatrix();
                }
                glPopMatrix();


                // right hip
                glColor3f(magenta[0], magenta[1], blue[2]);
                glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(blue));
                glPushMatrix();
                {
                    glTranslatef(0.5f, -0.2f, 0.0f);
                    sphere.drawSphere(0.25f, 32, 32);

                    // right high leg
                    glColor3f(orange[0], orange[1], orange[2]);
                    glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(orange));
                    glPushMatrix();
                    {
                        glRotatef(90.0f, 1.0f, 0.0f, 0.0f);
                        glRotatef(LimbRotation, 1.0f, 0.0f, 0.0f);
                        cylinder.drawCylinder(0.15f, 1.1f, 32);

                        // right knee
                        glColor3f(blue[0], blue[1], blue[2]);
                        glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(blue));
                        glPushMatrix();
                        {
                            glTranslatef(0.0f, 0.0f, 1.0f);
                            sphere.drawSphere(0.22f, 32, 32);
                            glRotatef(-kneeRotation, 1.0f, 0.0f, 0.0f);

                            // right low leg
                            glColor3f(orange[0], orange[1], orange[2]);
                            glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(orange));
                            glPushMatrix();
                            {
                                glTranslatef(0.0f, 0.0f, 0.0f);
                                cylinder.drawCylinder(0.12f, 0.8f, 32);

                                // right foot
                                glColor3f(blue[0], blue[1], blue[2]);
                                glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(blue));
                                glPushMatrix();
                                {
                                    glTranslatef(0.0f, 0.0f, 0.75f);
                                    glEnable(GL_TEXTURE_2D);
                                    Color.white.bind();
                                    footTexture.bind();
                                    glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_CLAMP);
                                    glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
                                    glDisable(GL_COLOR_MATERIAL);
                                    texSphere.drawSphere(0.25f, 32, 32, footTexture);
                                    glEnable(GL_COLOR_MATERIAL);
                                    glDisable(GL_TEXTURE_2D);
                                }
                                glPopMatrix();
                            }
                            glPopMatrix();
                        }
                        glPopMatrix();
                    }
                    glPopMatrix();
                }
                glPopMatrix();
            }
            glPopMatrix();

        }

    }

}



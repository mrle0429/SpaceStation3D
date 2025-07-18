import GraphicsObjects.Arcball;
import GraphicsObjects.Utils;
import objects3D.*;
import org.lwjgl.BufferUtils;
import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

import java.io.IOException;
import java.nio.FloatBuffer;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.util.glu.Project.gluLookAt;
import static org.lwjgl.util.glu.Project.gluPerspective;


public class MainWindow {


    // basic colours
    static float[] black = {0.0f, 0.0f, 0.0f, 1.0f};
    static float[] white = {1.0f, 1.0f, 1.0f, 1.0f};
    static float[] grey = {0.5f, 0.5f, 0.5f, 1.0f};
    static float[] spot = {0.1f, 0.1f, 0.1f, 0.5f};
    // primary colours
    static float[] red = {1.0f, 0.0f, 0.0f, 1.0f};
    static float[] green = {0.0f, 1.0f, 0.0f, 1.0f};
    static float[] blue = {0.0f, 0.0f, 1.0f, 1.0f};
    // secondary colours
    static float[] yellow = {1.0f, 1.0f, 0.0f, 1.0f};
    static float[] magenta = {1.0f, 0.0f, 1.0f, 1.0f};
    static float[] cyan = {0.0f, 1.0f, 1.0f, 1.0f};
    // other colours
    static float[] orange = {1.0f, 0.5f, 0.0f, 1.0f, 1.0f};
    static float[] brown = {0.5f, 0.25f, 0.0f, 1.0f, 1.0f};
    static float[] dkgreen = {0.0f, 0.5f, 0.0f, 1.0f, 1.0f};
    static float[] pink = {1.0f, 0.6f, 0.6f, 1.0f, 1.0f};
    /**
     * position of pointer
     */
    float x = 400, y = 300;
    /**
     * angle of rotation
     */
    float rotation = 0;
    /**
     * time at last frame
     */
    long lastFrame;
    /**
     * frames per second
     */
    int fps;
    /**
     * last fps time
     */
    long lastFPS;
    long myDelta = 0; // to use for animation
    float Alpha = 0; // to use for animation
    long StartTime; // beginAnimiation
    Arcball MyArcball = new Arcball();
    boolean DRAWGRID = false;
    boolean waitForKeyrelease = true;
    /**
     * Mouse movement
     */
    int LastMouseX = -1;
    int LastMouseY = -1;
    float pullX = 0.0f; // arc ball X cord.
    float pullY = 0.0f; // arc ball Y cord.
    int OrthoNumber = 2000; // using this for screen size, making a window of 1200 x 800 so aspect ratio 3:2

    // Textures
    Texture texture;    // earth texture
    Texture texture2;   // color texture
    Texture signTexture;  // bjut logo
    Texture footTexture;  // color2 texture
    Texture Texture2024;  // 2024 texture
    Texture skyBoxFront;
    Texture skyBoxBack;
    Texture skyBoxLeft;
    Texture skyBoxRight;
    Texture skyBoxTop;
    Texture skyBoxBottom;
    Texture nightSky;
    Texture rock;
    Texture sun, mercury, venus, mars, jupiter, saturn, uranus, neptune;

    private Texture stationTexture;
    private Texture probeTexture;
    private Texture elevatorTexture;
    private Texture elevatorTexture2;
    private Texture miningTexture;
    private Texture miningTexture2;
    private Texture helmetTexture;
    private Texture bodyTexture;

    private boolean MouseOnepressed = true;
    private boolean dragMode = false;
    private boolean BadAnimation = true;
    private boolean Earth = false;
    private boolean nl = false;

    // Astronaut
    private Astronaut astronaut;
    private float astronautX = -700.0f;
    private float astronautY = 200.0f;
    private float astronautZ = -1200.0f;
    private float astronautSpeed = 0.5f;
    private float astronautScale = 50.0f;
    private float astronautTime = 0.0f;
    private float astronautAnimSpeed = 0.5f;
    private float astronautOrbitRadius = 1500.0f;
    private float astronautVerticalRange = 800.0f;
    private float astronautRotation = 0.0f;


    // SpaceShip
    private Spaceship spaceship;
    private float spaceshipX = -700.0f;
    private float spaceshipY = 200.0f;
    private float spaceshipZ = -1200.0f;
    private float spaceshipTime = 0.0f;
    private float spaceshipSpeed = 2.0f;
    private float spaceshipRadius = 1000.0f;  

    // Camera
    private float cameraX = 0.0f;
    private float cameraY = 2000.0f;
    private float cameraZ = -3000.0f;
    private float cameraSpeed = 5.0f;             
    private float cameraTime = 0.0f;
    private float cameraAnimSpeed = 0.01f;       
    private float cameraOrbitRadius = 5000.0f;  
    private float cameraHeight = 2000.0f;       
    private float cameraLookAtX = 0.0f;         
    private float cameraLookAtY = 0.0f;         
    private float cameraLookAtZ = 0.0f;          
    private boolean autoCamera = false;         
    private boolean waitForKeyReleaseO = true;

    // Observer
    private Human observerHuman;
    private float humanOffset = 500.0f;       
    private float humanSideOffset = 0.0f;       
    private float humanScale = 20.0f;           
    private SpaceStation spaceStation;


    // SpaceProbe
    private SpaceProbe spaceProbe;
    private float probeTime = 0;
    private float probeSpeed = 1.0f;

    // SpaceElevator
    private SpaceElevator spaceElevator;

    // SpaceMiningStation
    private SpaceMiningStation miningStation;



    public static void main(String[] argv) {
        MainWindow hello = new MainWindow();
        hello.start();
    }

    public void start() {

        StartTime = getTime();
        try {
            Display.setDisplayMode(new DisplayMode(1200, 800));
            Display.create();
        } catch (LWJGLException e) {
            e.printStackTrace();
            System.exit(0);
        }

        initGL(); // init OpenGL
        getDelta(); // call once before loop to initialise lastFrame
        lastFPS = getTime(); // call before loop to initialise fps timer

        while (!Display.isCloseRequested()) {
            int delta = getDelta();
            update1();
            renderGL1();                                    
            Display.update();                               
            Display.sync(120); 
        }

        Display.destroy();
    }


    /**
     * Update game state and handle user input
     */
    public void update1() {
        // Update astronaut animation time and position
        astronautTime += astronautAnimSpeed;
        updateAstronautPosition();

        float moveSpeed = 5.0f;

        // Handle astronaut movement controls (WASD keys)
        if (Keyboard.isKeyDown(Keyboard.KEY_W)) {
            astronautZ += moveSpeed;  // Move forward
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_S)) {
            astronautZ -= moveSpeed;  // Move backward
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_A)) {
            astronautX += moveSpeed;  // Move left
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_D)) {
            astronautX -= moveSpeed;  // Move right
        }

        // Toggle auto camera mode with 'O' key
        // Using waitForKeyRelease to prevent multiple toggles from a single key press
        if (waitForKeyReleaseO) {
            if (Keyboard.isKeyDown(Keyboard.KEY_O)) {
                autoCamera = !autoCamera;
                waitForKeyReleaseO = false;
            }
        } else {
            waitForKeyReleaseO = !Keyboard.isKeyDown(Keyboard.KEY_O);
        }

        if (autoCamera) {
            // Update camera position automatically
            updateCameraPosition();
        } else {
            // Manual camera controls using arrow keys and R/E for elevation
            if (Keyboard.isKeyDown(Keyboard.KEY_UP)) {
                cameraZ += cameraSpeed;  // Move camera forward
            }
            if (Keyboard.isKeyDown(Keyboard.KEY_DOWN)) {
                cameraZ -= cameraSpeed;  // Move camera backward
            }
            if (Keyboard.isKeyDown(Keyboard.KEY_LEFT)) {
                cameraX -= cameraSpeed;  // Move camera left
            }
            if (Keyboard.isKeyDown(Keyboard.KEY_RIGHT)) {
                cameraX += cameraSpeed;  // Move camera right
            }
            if (Keyboard.isKeyDown(Keyboard.KEY_R)) {
                cameraY += cameraSpeed;  // Move camera up
            }
            if (Keyboard.isKeyDown(Keyboard.KEY_E)) {
                cameraY -= cameraSpeed;  // Move camera down
            }
        }
    }



    /**
     * Updates the astronaut's position using parametric equations to create a complex orbital motion
     * Combines circular orbit, twisting, vertical oscillation, and small wobble effects
     */
    private void updateAstronautPosition() {
        // Calculate basic orbital parameters
        float angle = astronautTime * 0.02f;  // Base orbital angle
        float twist = (float) Math.sin(astronautTime * 0.01f) * 0.5f;  // Twist effect for orbit variation

        // Calculate main orbital position with twist effect
        astronautX = (float) (astronautOrbitRadius * Math.cos(angle) * Math.cos(twist));
        astronautZ = (float) (astronautOrbitRadius * Math.sin(angle) * Math.cos(twist));
        // Calculate vertical position with sinusoidal oscillation
        astronautY = astronautVerticalRange * (float) Math.sin(angle * 0.5f);

        // Add small wobble effect to create more natural movement
        float wobble = 50.0f * (float) Math.sin(astronautTime * 0.1f);
        astronautX += wobble * (float) Math.sin(astronautTime * 0.03f);  // X-axis wobble
        astronautY += wobble * (float) Math.cos(astronautTime * 0.05f);  // Y-axis wobble
        astronautZ += wobble * (float) Math.sin(astronautTime * 0.04f);  // Z-axis wobble

        // Update astronaut's rotation to face movement direction
        astronautRotation = (float) Math.toDegrees(Math.atan2(
                -Math.cos(angle),
                Math.sin(angle)
        ));
    }

    /**
     * Updates camera position and look-at point through a series of cinematic phases
     * Total animation cycle is divided into 5 distinct phases over 90 seconds
     */
    private void updateCameraPosition() {
        cameraTime += cameraAnimSpeed;

        // Divide time into 9 segments (1+4*2)
        float totalTime = (cameraTime % 40) / 5.0f;  

        if (totalTime < 1.0f) {  // Phase 1 (0-10s): Static Overview
            // Fixed camera position with slight downward angle
            cameraX = 3000.0f;
            cameraY = 2000.0f;
            cameraZ = -2000.0f;

            // Look at center point with slight elevation
            cameraLookAtX = 0;
            cameraLookAtY = 500.0f;
            cameraLookAtZ = 0;
        } else if (totalTime < 3.0f) {  // Phase 2 (10-30s): Orbital View
            float t = (totalTime - 1.0f);
            float angle = cameraTime * 0.5f;
            cameraX = (float) (cameraOrbitRadius * Math.cos(angle));
            cameraZ = (float) (cameraOrbitRadius * Math.sin(angle));
            cameraY = cameraHeight + (float) (Math.sin(angle * 0.5f) * 500);

            // Smooth transition to orbital perspective
            float transition = Math.min(1.0f, (t * 2));
            cameraLookAtX = 0;
            cameraLookAtY = 500.0f * (1.0f - transition);
            cameraLookAtZ = 0;
        } else if (totalTime < 5.0f) {  // Phase 3 (30-50s): Close-up View
            float t = (totalTime - 3.0f);
            float radius = cameraOrbitRadius * (1.0f - t * 0.6f);
            float angle = cameraTime * 0.5f;

            cameraX = (float) (radius * Math.cos(angle));
            cameraZ = (float) (radius * Math.sin(angle));
            cameraY = cameraHeight * (1.0f - t * 0.3f);

            // Dynamic look-at point for more engaging view
            cameraLookAtX = (float) (radius * 0.3f * Math.cos(angle + Math.PI * 0.2f));
            cameraLookAtY = cameraHeight * 0.2f;
            cameraLookAtZ = (float) (radius * 0.3f * Math.sin(angle + Math.PI * 0.2f));
        } else if (totalTime < 7.0f) {  // Phase 4 (50-70s): High Altitude View
            float t = (totalTime - 5.0f);
            float radius = cameraOrbitRadius * (0.4f + t * 0.6f);
            float angle = cameraTime * 0.3f;

            cameraX = (float) (radius * Math.cos(angle));
            cameraZ = (float) (radius * Math.sin(angle));
            cameraY = cameraHeight * (0.7f + t * 1.3f);

            // Gradually return to center view
            cameraLookAtX *= (1.0f - t);
            cameraLookAtY *= (1.0f - t);
            cameraLookAtZ *= (1.0f - t);
        } else {  // Phase 5 (70-90s): Free Roaming
            float t = (totalTime - 7.0f);
            float angle = cameraTime * 0.2f;

            // Lissajous curve trajectory for complex smooth motion
            cameraX = (float) (cameraOrbitRadius * Math.sin(angle * 0.5f) * Math.cos(angle * 0.3f));
            cameraZ = (float) (cameraOrbitRadius * Math.sin(angle * 0.4f) * Math.sin(angle * 0.2f));
            cameraY = cameraHeight + (float) (Math.sin(angle * 0.6f) * 1000);

            // Dynamic look-at points for interesting perspective changes
            cameraLookAtX = (float) (cameraOrbitRadius * 0.2f * Math.cos(angle * 0.7f));
            cameraLookAtY = (float) (cameraHeight * 0.3f * Math.sin(angle * 0.4f));
            cameraLookAtZ = (float) (cameraOrbitRadius * 0.2f * Math.sin(angle * 0.5f));
        }
    }

    /**
     * Calculate how many milliseconds have passed since last frame.
     *
     * @return milliseconds passed since last frame
     */
    public int getDelta() {
        long time = getTime();
        int delta = (int) (time - lastFrame);
        lastFrame = time;

        return delta;
    }

    /**
     * Get the accurate system time
     *
     * @return The system time in milliseconds
     */
    public long getTime() {
        return (Sys.getTime() * 1000) / Sys.getTimerResolution();
    }

    /**
     * Calculate the FPS and set it in the title bar
     */
    public void updateFPS() {
        if (getTime() - lastFPS > 1000) {
            Display.setTitle("FPS: " + fps);
            fps = 0;
            lastFPS += 1000;
        }
        fps++;
    }

    /**
     * Initialize OpenGL settings including projection, lighting, and material properties
     */
    public void initGL() {
        // Set projection matrix mode
        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        changeOrth();

        // Initialize Arcball for mouse drag rotation
        MyArcball.startBall(0, 0, 1200, 800);
        MyArcball.reset();

        // Set modelview matrix mode
        glMatrixMode(GL_MODELVIEW);

        // Define light source positions
        FloatBuffer lightPos = BufferUtils.createFloatBuffer(4);
        lightPos.put(10000f).put(1000f).put(1000).put(0).flip();

        FloatBuffer lightPos2 = BufferUtils.createFloatBuffer(4);
        lightPos2.put(0f).put(1000f).put(0).put(-1000f).flip();

        FloatBuffer lightPos3 = BufferUtils.createFloatBuffer(4);
        lightPos3.put(-10000f).put(1000f).put(1000).put(0).flip();

        FloatBuffer lightPos4 = BufferUtils.createFloatBuffer(4);
        lightPos4.put(1000f).put(1000f).put(1000f).put(0).flip();

        // Set ambient and diffuse lighting
        FloatBuffer ambientLight = BufferUtils.createFloatBuffer(4);
        ambientLight.put(0.15f).put(0.15f).put(0.15f).put(1.0f).flip();

        FloatBuffer diffuseLight = BufferUtils.createFloatBuffer(4);
        diffuseLight.put(0.4f).put(0.4f).put(0.4f).put(1.0f).flip();

        // Configure Light 0 (Primary light)
        glLight(GL_LIGHT0, GL_POSITION, lightPos);
        glLight(GL_LIGHT0, GL_AMBIENT, ambientLight);
        glLight(GL_LIGHT0, GL_DIFFUSE, diffuseLight);
        glEnable(GL_LIGHT0);

        // Configure Light 1 (Secondary light)
        glLight(GL_LIGHT1, GL_POSITION, lightPos2);
        glLight(GL_LIGHT1, GL_AMBIENT, BufferUtils.createFloatBuffer(4).put(0.1f).put(0.1f).put(0.1f).put(1.0f).flip());
        glLight(GL_LIGHT1, GL_DIFFUSE, BufferUtils.createFloatBuffer(4).put(0.3f).put(0.3f).put(0.3f).put(1.0f).flip());
        glEnable(GL_LIGHT1);
        glLight(GL_LIGHT1, GL_DIFFUSE, Utils.ConvertForGL(spot));

        // Configure Light 2 (Additional lighting)
        glLight(GL_LIGHT2, GL_POSITION, lightPos3);
        glLight(GL_LIGHT2, GL_AMBIENT, BufferUtils.createFloatBuffer(4).put(new float[]{0.1f, 0.1f, 0.1f, 1.0f}).flip());
        glLight(GL_LIGHT2, GL_DIFFUSE, BufferUtils.createFloatBuffer(4).put(new float[]{0.3f, 0.3f, 0.3f, 1.0f}).flip());
        glEnable(GL_LIGHT2);
        glLight(GL_LIGHT2, GL_DIFFUSE, Utils.ConvertForGL(grey));

        // Configure Light 3 (Additional lighting)
        glLight(GL_LIGHT3, GL_POSITION, lightPos4);
        glLight(GL_LIGHT3, GL_AMBIENT, BufferUtils.createFloatBuffer(4).put(new float[]{0.1f, 0.1f, 0.1f, 1.0f}).flip());
        glLight(GL_LIGHT3, GL_DIFFUSE, BufferUtils.createFloatBuffer(4).put(new float[]{0.3f, 0.3f, 0.3f, 1.0f}).flip());
        glEnable(GL_LIGHT3);
        glLight(GL_LIGHT3, GL_DIFFUSE, Utils.ConvertForGL(grey));

        // Enable lighting and material properties
        glEnable(GL_LIGHTING);
        glEnable(GL_COLOR_MATERIAL);
        glColorMaterial(GL_FRONT_AND_BACK, GL_AMBIENT_AND_DIFFUSE);

        // Enable depth testing and normal normalization
        glEnable(GL_DEPTH_TEST);
        glEnable(GL_NORMALIZE);
        glEnable(GL_COLOR_MATERIAL);

        // Enable transparency blending
        glEnable(GL_BLEND);
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);

        // Initialize textures
        try {
            init();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Sets up the perspective projection and camera view matrix
     */
    public void changeOrth() {
        // Switch to projection matrix mode
        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();

        // Set perspective projection parameters:
        // 1. fovy: Field of view angle (typically 45-60 degrees)
        // 2. aspect: Aspect ratio (window width/height)
        // 3. zNear: Near clipping plane (positive value)
        // 4. zFar: Far clipping plane (positive value)
        float aspectRatio = 1200.0f / 800.0f;  // Window aspect ratio
        gluPerspective(
                45.0f,           // Field of view
                aspectRatio,     // Aspect ratio
                1.0f,           // Near clipping plane
                100000.0f       // Far clipping plane
        );

        // Switch to modelview matrix mode
        glMatrixMode(GL_MODELVIEW);
        glLoadIdentity();

        // Set up camera view matrix
        gluLookAt(
                cameraX, cameraY, cameraZ,     // Camera position
                cameraLookAtX, cameraLookAtY, cameraLookAtZ,     // Look-at point
                0.0f, 1.0f, 0.0f              // Up vector
        );
    }

    // 绘制坐标轴辅助线
    private void drawCoordinateSystem() {
        glPushAttrib(GL_CURRENT_BIT);  // 保存当前颜色状态
        glPushMatrix();
        {
            // 应用与场景相同的摄像机变换
            gluLookAt(0.0f, 2000.0f, -3000.0f,  // 摄像机位置
                    0.0f, 0.0f, 0.0f,          // 观察目标点
                    0.0f, 1.0f, 0.0f);         // 向上向量

            // X轴 - 红色
            glBegin(GL_LINES);
            glColor3f(1.0f, 0.0f, 0.0f);
            glVertex3f(-2000.0f, 0.0f, 0.0f);
            glVertex3f(2000.0f, 0.0f, 0.0f);
            glEnd();

            // Y轴 - 绿色
            glBegin(GL_LINES);
            glColor3f(0.0f, 1.0f, 0.0f);
            glVertex3f(0.0f, -2000.0f, 0.0f);
            glVertex3f(0.0f, 2000.0f, 0.0f);
            glEnd();

            // Z轴 - 蓝色
            glBegin(GL_LINES);
            glColor3f(0.0f, 0.0f, 1.0f);
            glVertex3f(0.0f, 0.0f, -2000.0f);
            glVertex3f(0.0f, 0.0f, 2000.0f);
            glEnd();
        }
        glPopMatrix();
        glPopAttrib();
    }


    public void renderGL1() {
        FloatBuffer dynamicLightPos = BufferUtils.createFloatBuffer(4);
        dynamicLightPos.put(cameraX).put(cameraY).put(cameraZ).put(1.0f).flip();

        FloatBuffer dynamicDiffuse = BufferUtils.createFloatBuffer(4);
        dynamicDiffuse.put(0.2f).put(0.2f).put(0.2f).put(1.0f).flip();

        glLight(GL_LIGHT4, GL_POSITION, dynamicLightPos);
        glLight(GL_LIGHT4, GL_DIFFUSE, dynamicDiffuse);
        glEnable(GL_LIGHT4);

        // Set up the camera view matrix
        changeOrth();

        // Clear the buffer and set the background color to dark space black
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
        glClearColor(0.0f, 0.0f, 0.02f, 1.0f);


        // Draw the starry sky background
        drawSkybox();

        // Get the time increment for animation
        myDelta = getTime() - StartTime;
        float delta = ((float) myDelta) / 50000;


        // Enable lighting
        glEnable(GL_LIGHTING);
        glEnable(GL_LIGHT0);


        // Main 
        glPushMatrix();
        {
            // Adjust the overall scene position
            glTranslatef(-1200, 100, 0);
            glScalef(1.6f, 1.6f, 1.6f);

            // Draw the sun
            glPushMatrix();
            {
                glEnable(GL_TEXTURE_2D);
                sun.bind();
                TexSphere sunSphere = new TexSphere();
                glScalef(160f, 160f, 160f);  // Sun size
                glRotatef(delta * 360.0f, 0, 1, 0);  // Sun rotation
                sunSphere.drawSphere(1.0f, 64, 64, sun);
                glDisable(GL_TEXTURE_2D);
            }
            glPopMatrix();

            // Draw Mercury
            glPushMatrix();
            {
                float angle = delta * 360.0f * 4.15f;  // Mercury rotation speed
                glRotatef(angle, 0, 1, 0);
                glTranslatef(193.5f, 0, 0);  // Orbit radius

                glEnable(GL_TEXTURE_2D);
                mercury.bind();
                TexSphere mercurySphere = new TexSphere();
                glScalef(15f, 15f, 15f);
                glRotatef(delta * 360.0f * 6f, 0, 1, 0);  // Rotation
                mercurySphere.drawSphere(1.0f, 32, 32, mercury);
                glDisable(GL_TEXTURE_2D);
            }
            glPopMatrix();

            // Draw Venus
            glPushMatrix();
            {
                float angle = delta * 360.0f * 1.62f;
                glRotatef(angle, 0, 1, 0);
                glTranslatef(360.0f, 0, 0);

                glEnable(GL_TEXTURE_2D);
                venus.bind();
                TexSphere venusSphere = new TexSphere();
                glScalef(38f, 38f, 38f);
                glRotatef(delta * 360.0f * -1.5f, 0, 1, 0);
                venusSphere.drawSphere(1.0f, 32, 32, venus);
                glDisable(GL_TEXTURE_2D);
            }
            glPopMatrix();

            // Draw Earth
            glPushMatrix();
            {
                float angle = delta * 360.0f;
                glRotatef(angle, 0, 1, 0);
                glTranslatef(500.0f, 0, 0);

                glEnable(GL_TEXTURE_2D);
                texture.bind();
                TexSphere earthSphere = new TexSphere();
                glScalef(40f, 40f, 40f);
                glRotatef(23.5f, 0, 0, 1);  // Earth axis tilt
                glRotatef(delta * 365.0f, 0, 1, 0);
                earthSphere.drawSphere(1.0f, 32, 32, texture);
                glDisable(GL_TEXTURE_2D);
            }
            glPopMatrix();

            // Draw Space Station
            spaceStation = new SpaceStation(stationTexture, texture2);
            glPushMatrix();
            {
                float earthAngle = delta * 360.0f;
                glRotatef(earthAngle, 0, 1, 0);
                glTranslatef(500.0f, 0, 0);  // Same orbit radius as Earth

                // Then revolve around Earth
                float stationAngle = delta * 360.0f * 0.5f;  // Much slower than Earth
                glRotatef(stationAngle, 0, 1, 0);
                glTranslatef(100.0f, 0, 0);  // Relative orbit radius to Earth

                // Space station attitude
                glRotatef(15, 0, 0, 1);  // Tilt angle
                glRotatef(delta * 360.0f, 0, 1, 0);  // Rotation speed

                // Adjust space station size
                glScalef(5.0f, 5.0f, 5.0f);  

                // Draw space station
                spaceStation.drawSpaceStation(delta);
            }
            glPopMatrix();


            // Draw Mars
            glPushMatrix();
            {
                float angle = delta * 360.0f * 0.53f;
                glRotatef(angle, 0, 1, 0);
                glTranslatef(600.0f, 0, 0);

                glEnable(GL_TEXTURE_2D);
                mars.bind();
                TexSphere marsSphere = new TexSphere();
                glScalef(21f, 21f, 21f);
                glRotatef(delta * 360.0f * 1.03f, 0, 1, 0);
                marsSphere.drawSphere(1.0f, 32, 32, mars);
                glDisable(GL_TEXTURE_2D);
            }
            glPopMatrix();

            // Draw Jupiter
            glPushMatrix();
            {
                float angle = delta * 360.0f * 0.3f;
                glRotatef(angle, 0, 1, 0);
                glTranslatef(-700.0f, 0, 0);

                glEnable(GL_TEXTURE_2D);
                jupiter.bind();
                TexSphere jupiterSphere = new TexSphere();
                glScalef(80f, 80f, 80f);
                glRotatef(delta * 360.0f * 2.4f, 0, 1, 0);
                jupiterSphere.drawSphere(1.0f, 32, 32, jupiter);
                glDisable(GL_TEXTURE_2D);
            }
            glPopMatrix();

            // Draw Saturn
            glPushMatrix();
            {
                float angle = delta * 360.0f * 0.1f;
                glRotatef(angle, 0, 1, 0);
                glTranslatef(1000.0f, 0, 0);

                glEnable(GL_TEXTURE_2D);
                saturn.bind();
                TexSphere saturnSphere = new TexSphere();
                glScalef(70f, 70f, 70f);
                glRotatef(26.7f, 0, 0, 1);  // Saturn axis tilt
                glRotatef(delta * 360.0f * 2.25f, 0, 1, 0);
                saturnSphere.drawSphere(1.0f, 32, 32, saturn);
                glDisable(GL_TEXTURE_2D);
            }
            glPopMatrix();

            // Draw Uranus
            glPushMatrix();
            {
                float angle = delta * 360.0f * 0.1f;
                glRotatef(angle, 0, 1, 0);
                glTranslatef(-1250.0f, 0, 0);

                glEnable(GL_TEXTURE_2D);
                uranus.bind();
                TexSphere uranusSphere = new TexSphere();
                glScalef(56f, 56f, 56f);
                glRotatef(97.8f, 0, 0, 1);  // Uranus axis tilt
                glRotatef(delta * 360.0f * 0.72f, 0, 1, 0);
                uranusSphere.drawSphere(1.0f, 32, 32, uranus);
                glDisable(GL_TEXTURE_2D);
            }
            glPopMatrix();

            // Draw Neptune
            glPushMatrix();
            {
                float angle = delta * 360.0f * 0.08f;
                glRotatef(angle, 0, 1, 0);
                glTranslatef(1500.0f, 0, 0);

                glEnable(GL_TEXTURE_2D);
                neptune.bind();
                TexSphere neptuneSphere = new TexSphere();
                glScalef(54f, 54f, 54f);
                glRotatef(28.3f, 0, 0, 1);  // Neptune axis tilt
                glRotatef(delta * 360.0f * 0.67f, 0, 1, 0);
                neptuneSphere.drawSphere(1.0f, 32, 32, neptune);
                glDisable(GL_TEXTURE_2D);
            }
            glPopMatrix();


        }
        glPopMatrix();




        astronaut = new Astronaut(helmetTexture);

        // Draw astronaut
        glPushMatrix();
        {
            glTranslatef(astronautX, astronautY, astronautZ);
            glScalef(80.0f, 80.0f, 80.0f);

            glRotatef(astronautRotation, 0, 1, 0);

            // Add a slight swaying effect
            float swayAngle = 15.0f * (float) Math.sin(astronautTime * 0.1f);
            glRotatef(swayAngle, 0, 0, 1);


            astronaut.drawAstronaut(myDelta, true);  // Always keep the animation state

        }
        glPopMatrix();

        spaceship = new Spaceship();

        // Draw spaceship
        glPushMatrix();
        {

            spaceshipTime += spaceshipSpeed;
            float angle = spaceshipTime * 0.001f;  // Control rotation speed

            // Calculate spaceship position on the orbit
            float shipX = spaceshipRadius * (float) Math.cos(angle);
            float shipZ = spaceshipRadius * (float) Math.sin(angle);

            // Calculate next position (for direction determination)
            float nextAngle = angle + 0.01f;
            float nextX = spaceshipRadius * (float) Math.cos(nextAngle);
            float nextZ = spaceshipRadius * (float) Math.sin(nextAngle);

            // Calculate direction vector
            float dirX = nextX - shipX;
            float dirZ = nextZ - shipZ;

            glTranslatef(shipX, spaceshipY, shipZ);

            // Calculate rotation angle 
            float rotationAngle = (float) Math.toDegrees(Math.atan2(-dirZ, dirX));

            glTranslatef(shipX, spaceshipY, shipZ);
            glRotatef(rotationAngle, 0, 1, 0);

            spaceship.drawSpaceship(myDelta);
        }
        glPopMatrix();

       


        spaceProbe = new SpaceProbe(probeTexture);  


        // Draw space probe
        glPushMatrix();
        {
            // Update time
            probeTime += probeSpeed;

            // Calculate probe position on the elliptical orbit
            float angle = probeTime * 0.005f;  // Control movement speed
            float radius = 800.0f;  // Orbit radius

            // Elliptical orbit
            float probeX = -1000.0f + (float) Math.cos(angle) * radius;
            float probeY = 500.0f + (float) Math.sin(angle) * radius * 0.5f;  // Vertical compression
            float probeZ = -1500.0f + (float) Math.sin(angle * 2) * 200.0f;  // Add Z-axis fluctuation

            glTranslatef(probeX, probeY, probeZ);
            glScalef(50.0f, 50.0f, 50.0f);

            // Make the probe face the direction of movement
            float rotationAngle = (float) Math.toDegrees(Math.atan2(
                    Math.cos(angle) * radius * 0.5f,
                    -Math.sin(angle) * radius
            ));
            glRotatef(rotationAngle, 0, 1, 0);


            spaceProbe.draw(delta);
        }
        glPopMatrix();


        spaceElevator = new SpaceElevator(elevatorTexture, elevatorTexture2);

        // Draw space elevator
        glPushMatrix();
        {
            // Set space elevator position
            glTranslatef(-500.0f, 300.0f, -1800f);
            glRotatef(-5, 0, 0, 1);  // Slightly tilted
            glScalef(30.0f, 30.0f, 30.0f);

            // Draw space elevator
            spaceElevator.draw(delta);
        }
        glPopMatrix();

        miningStation = new SpaceMiningStation(miningTexture, miningTexture2);

        // Draw mining station
        glPushMatrix();
        {

            float angle = delta * 360.0f * 0.3f;
            glRotatef(angle, 0, 1, 0);
            glTranslatef(-1600.0f, 300.0f, -1200.0f);

            // Mining station attitude
            glRotatef(0, 0, 0, 1);
            glScalef(80.0f, 80.0f, 80.0f);

            miningStation.draw(delta);
        }
        glPopMatrix();

        // 2024 Texture
        glPushMatrix();
        {
            // Move the coordinate system to the screen center
            glTranslatef(0, -1000, 2200);
            glRotatef(30, 1, 0, 0);

            // Set the appropriate size
            glScalef(1000f, 1000f, 100f);  // Use a smaller z value to make it flat

            glEnable(GL_TEXTURE_2D);

            // 绘制单面纹理立方体，使用前面(0)显示纹理
            SingleTexCube texCube = new SingleTexCube();
            Texture2024.bind();
            texCube.drawCube(Texture2024, 1);

            glDisable(GL_TEXTURE_2D);
        }
        glPopMatrix();

    }



    private void drawSkybox() {
        float size = 15000.0f;

        glEnable(GL_TEXTURE_2D);
        glDisable(GL_DEPTH_TEST);
        glColor3f(1.0f, 1.0f, 1.0f);

        glTranslatef(600, 400, 0);


        // Front face
        nightSky.bind();
        glBegin(GL_QUADS);
        glTexCoord2f(0, 0);
        glVertex3f(-size, -size, -size);
        glTexCoord2f(1, 0);
        glVertex3f(size, -size, -size);
        glTexCoord2f(1, 1);
        glVertex3f(size, size, -size);
        glTexCoord2f(0, 1);
        glVertex3f(-size, size, -size);
        glEnd();

        // Back face
        nightSky.bind();
        glBegin(GL_QUADS);
        glTexCoord2f(0, 0);
        glVertex3f(size, -size, size);
        glTexCoord2f(1, 0);
        glVertex3f(-size, -size, size);
        glTexCoord2f(1, 1);
        glVertex3f(-size, size, size);
        glTexCoord2f(0, 1);
        glVertex3f(size, size, size);
        glEnd();

        // Left face
        nightSky.bind();
        glBegin(GL_QUADS);
        glTexCoord2f(0, 0);
        glVertex3f(-size, -size, size);
        glTexCoord2f(1, 0);
        glVertex3f(-size, -size, -size);
        glTexCoord2f(1, 1);
        glVertex3f(-size, size, -size);
        glTexCoord2f(0, 1);
        glVertex3f(-size, size, size);
        glEnd();

        // Right face
        nightSky.bind();
        glBegin(GL_QUADS);
        glTexCoord2f(0, 0);
        glVertex3f(size, -size, -size);
        glTexCoord2f(1, 0);
        glVertex3f(size, -size, size);
        glTexCoord2f(1, 1);
        glVertex3f(size, size, size);
        glTexCoord2f(0, 1);
        glVertex3f(size, size, -size);
        glEnd();

        // Top face
        nightSky.bind();
        glBegin(GL_QUADS);
        glTexCoord2f(0, 0);
        glVertex3f(-size, size, -size);
        glTexCoord2f(1, 0);
        glVertex3f(size, size, -size);
        glTexCoord2f(1, 1);
        glVertex3f(size, size, size);
        glTexCoord2f(0, 1);
        glVertex3f(-size, size, size);
        glEnd();

        // Bottom face
        nightSky.bind();
        glBegin(GL_QUADS);
        glTexCoord2f(0, 0);
        glVertex3f(-size, -size, size);
        glTexCoord2f(1, 0);
        glVertex3f(size, -size, size);
        glTexCoord2f(1, 1);
        glVertex3f(size, -size, -size);
        glTexCoord2f(0, 1);
        glVertex3f(-size, -size, -size);
        glEnd();

        glEnable(GL_DEPTH_TEST);
        glDisable(GL_TEXTURE_2D);
    }


    /*
     * Any additional textures for your assignment should be written in here. Make a
     * new texture variable for each one so they can be loaded in at the beginning
     */
    public void init() throws IOException {

        texture = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/earthspace.png"));
        texture2 = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/color.png"));
        signTexture = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/bjut.png"));
        footTexture = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/color2.png"));
        Texture2024 = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/Texture2024.png"));

        skyBoxFront = TextureLoader.getTexture("JPG", ResourceLoader.getResourceAsStream("res/PereaBeach1/posz.jpg"));
        skyBoxBack = TextureLoader.getTexture("JPG", ResourceLoader.getResourceAsStream("res/PereaBeach1/negz.jpg"));
        skyBoxLeft = TextureLoader.getTexture("JPG", ResourceLoader.getResourceAsStream("res/PereaBeach1/negx.jpg"));
        skyBoxRight = TextureLoader.getTexture("JPG", ResourceLoader.getResourceAsStream("res/PereaBeach1/posx.jpg"));
        skyBoxTop = TextureLoader.getTexture("JPG", ResourceLoader.getResourceAsStream("res/PereaBeach1/negy.jpg"));
        skyBoxBottom = TextureLoader.getTexture("JPG", ResourceLoader.getResourceAsStream("res/PereaBeach1/posy.jpg"));

        nightSky = TextureLoader.getTexture("JPG", ResourceLoader.getResourceAsStream("res/NightSkyHDRI008_8K-TONEMAPPED.jpg"));
        rock = TextureLoader.getTexture("JPG", ResourceLoader.getResourceAsStream("res/rocky_terrain_02_spec_1k.png"));

        // 太阳系
        sun = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/2k_sun.jpg"));
        mercury = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/2k_mercury.jpg"));
        venus = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/2k_venus_atmosphere.jpg"));
        mars = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/2k_mars.jpg"));
        jupiter = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/2k_jupiter.jpg"));
        saturn = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/2k_saturn.jpg"));
        uranus = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/2k_uranus.jpg"));
        neptune = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/2k_neptune.jpg"));

        stationTexture = TextureLoader.getTexture("JPG", ResourceLoader.getResourceAsStream("res/SpaceStation.jpg"));

        probeTexture = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/metal_plate_02_disp_4k.png"));

        elevatorTexture = TextureLoader.getTexture("JPG", ResourceLoader.getResourceAsStream("res/2K-metal_grid.jpg-diffuse.jpg"));
        elevatorTexture2 = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/PaintedMetal002_1K-PNG_Color.png"));

        miningTexture = TextureLoader.getTexture("JPG", ResourceLoader.getResourceAsStream("res/2K-metal_grid.jpg-diffuse.jpg"));
        miningTexture2 = TextureLoader.getTexture("JPG", ResourceLoader.getResourceAsStream("res/rusty_metal_03_diff_4k.jpg"));

        helmetTexture = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/metal_plate_02_disp_4k.png"));
        bodyTexture = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/beige_wall_001_diff_4k.jpg"));


        System.out.println("Texture loaded okay ");
    }


}

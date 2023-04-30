import org.newdawn.slick.*;

import java.util.Random;

public class Main extends BasicGame {

    private GameContainer gameContainer;
    private float horizon = 0.75f;

    private Player player;
    private EntityManager entityManager;

    private Wave1 wave1;
    private Wave2 boss;
    private Wave current_wave;

    private float camera_speed = 0.2f;
    private Random random;

    private SurfaceBackground surfaceBackground;
    private CavernBackground cavernBackground;

    private Background current_background;

    private boolean pause = false;

    private boolean ended = false;

    private java.awt.Font font;
    private TrueTypeFont ttf;

    public Main(String title) {
        super(title);
    }

    @Override
    public void init(GameContainer gc) throws SlickException {
        font = new java.awt.Font("Verdana", java.awt.Font.BOLD, 40);
        ttf = new TrueTypeFont(font, true);
        Assets.init();
        gameContainer = gc;
        entityManager = new EntityManager();
        random = new Random();
        surfaceBackground = new SurfaceBackground(this);
        cavernBackground = new CavernBackground(this);

        current_background = surfaceBackground;
        current_background.init(gc);

        player = new Player(this, 0, 0, 1);

        wave1 = new Wave1(this, 750);
        boss = new Wave2(this, 3000);
        current_wave = wave1;
        current_wave.start(gc);
    }

    @Override
    public void update(GameContainer gc, int delta) throws SlickException {
        if (!ended) {
            if (!pause) {
                current_background.update(gc, delta);
                player.update(gc, delta);
                current_wave.update(gc, delta);
                entityManager.update(gc, delta);
            }
            else {
                if (gc.getInput().isKeyPressed(Input.KEY_RETURN)) {
                    pause = false;
                }
            }
        }
        else {
            if (gc.getInput().isKeyPressed(Input.KEY_ESCAPE)) {
                gc.exit();
            }
        }
    }

    @Override
    public void render(GameContainer gc, Graphics g) throws SlickException {
        if (!ended) {
            current_background.render(gc, g);

            entityManager.render(gc, g);
            player.render(gc, g);

            if (pause) {
                g.setColor(Color.yellow);
                ttf.drawString(gc.getWidth()/2.0f - 256.0f, gc.getHeight()/2.0f - 256.0f, "Your ship was destroyed!");
                ttf.drawString(gc.getWidth()/2.0f - 256.0f, gc.getHeight()/2.0f - 128.0f, "Press return to continue");
            }
        }
        else {
            g.setColor(Color.white);

            ttf.drawString(gc.getWidth()/2.0f - 512.0f, gc.getHeight()/2.0f - 256.0f, "Payload delivered!        The beast is defeated!");
            ttf.drawString(gc.getWidth()/2.0f - 256.0f, gc.getHeight()/2.0f - 128.0f, "Press escape to close");
        }
    }

    public void nextWave(GameContainer gc) {
        entityManager.clear();
        current_wave = boss;
        current_wave.start(gc);
        current_background = cavernBackground;
    }

    public CavernBackground getCavernBackground() {
        return cavernBackground;
    }

    public SurfaceBackground getSurfaceBackground() {
        return surfaceBackground;
    }

    public void setBackground(Background background) {
        this.current_background = background;
    }

    public float getCameraSpeed() {
        return camera_speed;
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public GameContainer getGameContainer() {
        return gameContainer;
    }

    public Player getPlayer() {
        return player;
    }

    public float getHorizon() {
        return horizon;
    }

    public Random getRandom() {
        return random;
    }

    public Background getCurrentBackground() {
        return current_background;
    }

    public void setPause(boolean pause) {
        this.pause = pause;
    }

    public void end() {
        ended = true;
    }

    public static void main(String[] args) {
        try {
            AppGameContainer appgc = new AppGameContainer(new Main("Watching"));
                appgc.setDisplayMode(1920,1080,false);
            appgc.setShowFPS(false);
            appgc.start();
        } catch (SlickException e) {
            throw new RuntimeException(e);
        }

    }
}
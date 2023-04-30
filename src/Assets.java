import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;

public class Assets {

    public static Image winged_eye1, winged_eye2,
                        winged_eye_gold1, winged_eye_gold2,
                        engine, dune,
                        watcher, blast, cross, rocks, payload, projectile,
                        watcher_dead, watcher_headless;

    public static Sound hurt, hurt2, laser, shoot, kill;

    public static void init() {
        try {
            winged_eye1 = new Image("res/winged eye1.png", false, Image.FILTER_NEAREST);
            winged_eye2 = new Image("res/winged eye2.png", false, Image.FILTER_NEAREST);
            winged_eye_gold1 = new Image("res/winged_eye_gold1.png", false, Image.FILTER_NEAREST);
            winged_eye_gold2 = new Image("res/winged_eye_gold2.png", false, Image.FILTER_NEAREST);
            engine = new Image("res/engine.png", false, Image.FILTER_NEAREST);
            dune = new Image("res/dune.png", false, Image.FILTER_NEAREST);
            watcher = new Image("res/watcher1.png", false, Image.FILTER_NEAREST);
            watcher_dead = new Image("res/watcher2.png", false, Image.FILTER_NEAREST);
            watcher_headless = new Image("res/watcher3.png", false, Image.FILTER_NEAREST);
            blast = new Image("res/blast.png", false, Image.FILTER_NEAREST);
            cross = new Image("res/cross.png", false, Image.FILTER_NEAREST);
            rocks = new Image("res/rocks.png", false, Image.FILTER_NEAREST);
            payload = new Image("res/payload.png", false, Image.FILTER_NEAREST);
            projectile = new Image("res/projectile.png", false, Image.FILTER_NEAREST);

            hurt = new Sound("res/hurt.wav");
            hurt2 = new Sound("res/hurt2.wav");
            laser = new Sound("res/laser.wav");
            shoot = new Sound("res/shoot.wav");
            kill = new Sound("res/kill.wav");

        } catch (SlickException e) {
            throw new RuntimeException(e);
        }
    }
}

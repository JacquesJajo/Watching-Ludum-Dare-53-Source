import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

public class SurfaceBackground extends Background {

    public SurfaceBackground(Main game) {
        super(game);
    }

    @Override
    public void init(GameContainer gc) {
        for (int i = 0; i < 10; i++) {
            game.getEntityManager().add(new SceneryObject(game,
                    8.0f*game.getRandom().nextFloat() - 4.0f, game.getHorizon(),
                    game.getRandom().nextFloat() * 50.0f, Assets.dune));
        }
    }

    @Override
    public void update(GameContainer gc, int delta) {

    }

    @Override
    public void render(GameContainer gc, Graphics g) {
        for (int x = 0; x < gc.getWidth(); x++) {
            g.drawGradientLine(x, gc.getHeight() * game.getHorizon(), new Color(0xc8b496), x, gc.getHeight(), new Color(0xbe9668));
        }

        for (int x = 0; x < gc.getWidth(); x++) {
            g.drawGradientLine(x, 0, new Color(0x606a), x, gc.getHeight() * game.getHorizon(), new Color(0xaeb0b2));
        }
    }
}

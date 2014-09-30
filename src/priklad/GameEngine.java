package priklad;

import java.applet.Applet;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.List;

public class GameEngine extends Applet implements Runnable {

    Graphics bufferGraphics;
    Image offscreen;
    int highscore;

    final int MOVEPX = 10;
    final int SPEED = 90;
    final int WIDTH = 50;
    final int HEIGHT = WIDTH;

    final Random random = new Random();

    final static int GAME_WIDTH = 720;
    final static int GAME_HEIGHT = 720;

    public List<Rectangle> cubes;
    public Rectangle player;

    @Override
    public void run() {
        List<Rectangle> removeCubes = new ArrayList<>();
        for (int i = 0; true; i++) {
            if (i % (100 - SPEED) == 0) {
                i = 0;
                cubes.add(new Rectangle(GAME_WIDTH - WIDTH, random.nextInt(GAME_HEIGHT - HEIGHT), WIDTH, HEIGHT));
            }
            for (Rectangle cube : cubes) {
                cube.x -= MOVEPX;
                if (cube.x + WIDTH < 0) {
                    removeCubes.add(cube);
                    highscore++;
                }
            }
            cubes.removeAll(removeCubes);
            removeCubes.clear();
            redraw();
        }
    }


    private void sleep(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void redraw() {
        if (isColliding()) {
            bufferGraphics.clearRect(0, 0, GAME_WIDTH, GAME_HEIGHT);
            bufferGraphics.drawString("Game Over! Your highscore is " + highscore, 200, 200);
            getGraphics().drawImage(offscreen, 0, 0, this);

            sleep(2000);
            System.exit(0);
        }

        repaint();
        sleep(20);
    }

    private boolean isColliding() {
        for (Rectangle cube : cubes) {
            if (player.intersects(cube)) {
                return true;
            }
        }
        return false;
    }

}

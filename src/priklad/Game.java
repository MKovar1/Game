package priklad;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.*;

public class Game extends GameEngine {

    @Override
    public void init() {
        /*
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                if (e.getKeyCode() == KeyEvent.VK_UP) {
                    player.y -= MOVE_PX*2;
                } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    player.y += MOVE_PX*2;
                }
            }
        });
        */

        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                player.y = e.getY() - player.height / 2;
            }
        });

        cubes = new ArrayList<>();
        player = new Rectangle(200, 200, WIDTH, HEIGHT);
        setSize(GAME_WIDTH, GAME_HEIGHT);
        offScreen = createImage(GAME_WIDTH, GAME_HEIGHT);
        bufferGraphics = offScreen.getGraphics();
        Thread th = new Thread(this);
        th.start();
    }

    @Override
    public void update(Graphics g) {
        paint(g);
    }

    @Override
    public void paint(Graphics g) {
        bufferGraphics.clearRect(0, 0, GAME_WIDTH, GAME_HEIGHT);
        Color original = g.getColor();
        bufferGraphics.setColor(Color.BLUE);
        bufferGraphics.fillRect(player.x, player.y, player.width, player.height);
        bufferGraphics.setColor(original);

        for (Rectangle cube : cubes) {
            bufferGraphics.fillRect(cube.x, cube.y, cube.width, cube.height);
        }
        g.drawImage(offScreen, 0, 0, this);
    }
}

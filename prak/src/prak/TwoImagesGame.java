package prak;

import javax.swing.JFrame;
import javax.swing.WindowConstants;
import java.awt.Graphics;
import javax.swing.JPanel;
import java.awt.Image;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class TwoImagesGame extends JFrame {
    private static long last_frame_time;
    private static Image vadya;
    private static Image kinder;
    private static Random random = new Random();

    // Класс для одной падающей Kinder
    private static class FallingKinder {
        float x;
        float y;
        float velocity;

        FallingKinder() {
            reset();
        }

        void reset() {
            this.x = random.nextInt(700);
            this.y = -100 - random.nextInt(500);
            this.velocity = 100 + random.nextInt(150);
        }

        void update(float delta_time) {
            y += velocity * delta_time;
            if (y > 600) {
                reset();
            }
        }
    }

    private static ArrayList<FallingKinder> fallingKinders = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        // Загружаем обе картинки
        vadya = ImageIO.read(TwoImagesGame.class.getResourceAsStream("Vadya.png"));
        kinder = ImageIO.read(TwoImagesGame.class.getResourceAsStream("Kinder.png"));
        
        // Создаем падающие Kinder
        for (int i = 0; i < 15; i++) {
            fallingKinders.add(new FallingKinder());
        }
        
        TwoImagesGame game = new TwoImagesGame();
        game.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        game.setLocation(200, 50);
        game.setSize(1000, 600);
        game.setResizable(false);
        last_frame_time = System.nanoTime();
        GameField game_field = new GameField();
        game.add(game_field);
        game.setVisible(true);
    }
    
    public static void onRepaint(Graphics g) {
        long current_time = System.nanoTime();
        float delta_time = (current_time - last_frame_time) * 0.000000001f;
        last_frame_time = current_time;
        
        // Рисуем Vadya слева (статично)
        g.drawImage(vadya, 100, 100, null);
        
        // Рисуем Kinder справа (статично)
        g.drawImage(kinder, 450, 150, null);
        
        // Обновляем и рисуем все падающие Kinder
        for (FallingKinder fk : fallingKinders) {
            fk.update(delta_time);
            g.drawImage(kinder, (int)fk.x, (int)fk.y, null);
        }
    }
    
    public static class GameField extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            onRepaint(g);
            repaint();
        }
    }
}
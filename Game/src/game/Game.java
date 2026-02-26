package game;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

/**
 * @author HYPERPC
 */
public class Game extends JFrame {
    private static Game game_game;
    private BufferedImage backgroundImage;
    private BufferedImage foregroundImage;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        game_game = new Game();
        game_game.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        game_game.setLocation(200, 50);
        game_game.setSize(900, 600);
        game_game.setResizable(false);
        GameField game_field = game_game.new GameField();
        game_game.add(game_field);
        game_game.setVisible(true);
    }

    public class GameField extends JPanel {
        
        public GameField() {
            // Загрузка изображений
            try {
                // Попробуем несколько вариантов путей
                File backgroundFile = new File("nalog.png");
                File foregroundFile = new File("Dexter.png");

                // Выведем информацию о путях для отладки
                System.out.println("Текущая директория: " + System.getProperty("user.dir"));
                System.out.println("Путь к nalog.png: " + backgroundFile.getAbsolutePath());
                System.out.println("Существует ли nalog.png: " + backgroundFile.exists());
                System.out.println("Путь к Dexter.png: " + foregroundFile.getAbsolutePath());
                System.out.println("Существует ли Dexter.png: " + foregroundFile.exists());

                backgroundImage = ImageIO.read(backgroundFile);
                foregroundImage = ImageIO.read(foregroundFile);

                System.out.println("Изображения успешно загружены!");
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Ошибка загрузки изображений. Проверьте пути к файлам.");
            }
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            
            // Отрисовка фонового изображения (если загружено)
            if (backgroundImage != null) {
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            } else {
                // Если изображение не загружено, закрашиваем фон серым цветом
                g.setColor(Color.LIGHT_GRAY);
                g.fillRect(0, 0, getWidth(), getHeight());
            }
            
            // Отрисовка переднего изображения (если загружено)
            if (foregroundImage != null) {
                g.drawImage(foregroundImage, 100, 100, this);
            }
            
            // Рисуем овал
            Graphics2D g2d = (Graphics2D) g;
            g2d.setColor(Color.GREEN);
            g2d.setStroke(new BasicStroke(3)); // Толщина линии 3 пикселя
            
            // Рисуем заполненный овал
            g2d.setColor(new Color(255, 0, 0, 100)); // Красный с прозрачностью
            g2d.fillOval(300, 200, 200, 150);
            
            // Рисуем контур овала
            g2d.setColor(Color.GREEN);
            g2d.setStroke(new BasicStroke(2));
            g2d.drawOval(300, 200, 200, 150);
            
            // Добавляем текст внутри овала
            g2d.setColor(Color.RED);
            g2d.setFont(new Font("Arial", Font.BOLD, 16));
            g2d.drawString("Шайдт", 350, 280);
        }
    }
}
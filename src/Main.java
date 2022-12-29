import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        EventQueue.invokeLater(() ->{
            JFrame frame = new FractalsFrame();
            frame.setTitle("Конструктивные фракталы");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
            frame.setResizable(true); // Разрешаем менять разрешение окна
        });
    }
}
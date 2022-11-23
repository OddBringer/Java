package fractals;

import javax.swing.*;
import java.awt.*;

public class SerpinskiyFractalPanel extends JPanel {
    private int step = 0;

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (step == 0) {
            //g.setColor(Color.BLACK); // устанавливаем цвет рисования
            g.fillPolygon(new int[]{12, 292, 572}, new int[]{470, 10, 470}, 3);
        }
        draw(step, new int[]{12, 292, 572}, new int[]{470, 10, 470}, g); // рекурсивный метод
    }

    private void draw(int n, int[] x, int[] y, Graphics gc) {
        if (this.step > 9) this.step--; // Более 9 шагов не рисуем
        if (n > 0) {
            // высчитываем точки
            int x1 = (x[0] + x[1]) / 2;
            int y1 = (y[0] + y[1]) / 2;

            int x2 = (x[1] + x[2]) / 2;
            int y2 = (y[1] + y[2]) / 2;

            int x3 = (x[2] + x[0]) / 2;
            int y3 = (y[2] + y[0]) / 2;

            // отрисовка первого треугольника
            gc.setColor(Color.BLACK);
            gc.fillPolygon(x, y, 3);

            // отрисовка следующего шага
            gc.setColor(Color.WHITE);
            gc.fillPolygon(new int[]{x1, x2, x3}, new int[]{y1, y2, y3}, 3);

            draw(n - 1, new int[]{x[0], x1, x3}, new int[]{y[0], y1, y3}, gc);
            draw(n - 1, new int[]{x1, x[1], x2}, new int[]{y1, y[1], y2}, gc);
            draw(n - 1, new int[]{x3, x2, x[2]}, new int[]{y3, y2, y[2]}, gc);
        }
    }

    public int getStep() {
        return step;
    }

    public void stepIncrement() {
        step++;
    }// метод для увеличения шагов

    public void setNullStep() {
        step = 0;
    }// метод обнуления шагов для кнопки "авто воспроизведение'
}
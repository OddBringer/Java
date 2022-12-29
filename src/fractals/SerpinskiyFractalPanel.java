package fractals;

import javax.swing.*;
import java.awt.*;

public class SerpinskiyFractalPanel extends JPanel {
    private int step = 0;
    public int MaxStep = 9;
    public int sizeOfPanel = 650;
    private int paddingX = 12;
    private int paddingY = sizeOfPanel - 130;

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (step == 0) {
            g.fillPolygon(new int[]{paddingX, sizeOfPanel/2, sizeOfPanel - 2*paddingX}, new int[]{paddingY, paddingX, paddingY}, 3);
        }
        draw(step, new int[]{paddingX, sizeOfPanel/2, sizeOfPanel - 2*paddingX}, new int[]{paddingY, paddingX, paddingY}, g); // рекурсивный метод
    }

    private void draw(int n, int[] x, int[] y, Graphics gc) {
        if (this.step > MaxStep) this.step--;
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
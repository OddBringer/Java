package fractals;

import javax.swing.*;
import java.awt.*;

public class CantorFractal extends JPanel {
    public final int paddingX = 170;
    public final int paddingY = 15;
    public final int height = 25;
    public int step = 0;
    public final int width = 243;

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.BLACK);            // устанавливаем цвет рисования
        paintItems(g, width, paddingX, paddingY, step); // вызываем рекурсивный метод отрисовки шагов
        g.fillRect(paddingX, paddingY, width, height);  // отрисовка первого шага
    }

    private void paintItems(Graphics g, int width, int paddingX, int paddingY, int step) {
        if (this.step > 13) this.step--; // Более рисовать нет смысла
        if (step == 0) return;

        g.fillRect(paddingX, paddingY + height + 7, width > 1 ? width / 3 : 1, height);
        g.fillRect(paddingX + (width / 3) * 2, paddingY + height + 7, width > 1 ? width / 3 : 1, height);
        step--;
        paintItems(g, width / 3, paddingX, paddingY + height + 7, step);
        paintItems(g, width / 3, paddingX + (width / 3) * 2, paddingY + height + 7, step);
    }

    public void stepIncrement() {
        step++;
    }

    public void setNullStep() {
        step = 0;
    }

    public int getStep() {
        return step;
    }
}
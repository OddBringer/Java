package fractals;

import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;

public class MinkowskiFractalPanel extends JPanel {
    public static final int sizeOfPanel = 650;
    public int MaxRange = 3; // Указываем начальные переменные
    private int step = 0;
    public boolean flagToContinue = true;
    public static ArrayList<MyLine> lines = new ArrayList<>();
    public static double[][] pattern = { // Указываем координаты точек
            {0, 0},
            {0.25, 0},
            {0.25, -0.25},
            {0.5, -0.25},
            {0.5, 0.25},
            {0.75, 0.25},
            {0.75, 0},
            {1, 0}
    };
    static Point A = new Point(150, 105);
    static int size = sizeOfPanel / 2;

    public void draw() {
        if (flagToContinue) {
            if (lines.size() == 0) { // Отрисовка первых линий
                lines.add(new MyLine(A.x, A.y, A.x + size, A.y));
                lines.add(new MyLine(A.x + size, A.y, A.x + size, A.y + size));
                lines.add(new MyLine(A.x + size, A.y + size, A.x, A.y + size));
                lines.add(new MyLine(A.x, A.y + size, A.x, A.y));
                //выключаем необходимость продолжения
                flagToContinue = false;
                return;
            }
            ArrayList<MyLine> bufferLines = new ArrayList<>(); // буферный лист, новых линий
            ArrayList<Point> bufferPoints = new ArrayList<>(); // буферный лист полученных точек

            // перебираем все линии и преобразовываем их
            for (MyLine line :
                    lines) {
                bufferPoints.clear(); // очищаем лист точек, так как иначе он заполнится не нужными точками от предыдущих линий
                for (double[] doubles : pattern) {
                    double xRes = (line.X - line.x) * doubles[0] - (line.Y - line.y) * doubles[1] + line.x;
                    double yRes = (line.Y - line.y) * doubles[0] + (line.X - line.x) * doubles[1] + line.y;
                    bufferPoints.add(new Point((int) xRes, (int) yRes)); // получи точку запоминаем ее
                }
                //в этом цикле проходим по существующим точкам и создаем линии, добавляем их в буфер линий
                for (int i = 0; i < bufferPoints.size() - 1; i++) {
                    bufferLines.add(new MyLine(bufferPoints.get(i).x,
                            bufferPoints.get(i).y,
                            bufferPoints.get(i + 1).x,
                            bufferPoints.get(i + 1).y));
                }
            }
            flagToContinue = false; // отключаем флаг прохода

            if (step > MaxRange) return;

            lines = bufferLines; // забываем про старые линии, так как они не актуальны, и запоминаем новые
        }
    }

    @Override
    protected void paintComponent(Graphics g) { // этот метод вызывается у компонента при каждом обновлении кадра
        super.paintComponent(g);
        this.setBackground(Color.WHITE);
        draw();
        //цикл перебирает все существующие линии и рисует их
        for (MyLine line : lines) {
            g.drawLine(line.x, line.y, line.X, line.Y);
        }
    }

    public void stepIncrement() {
        step++;
    }

    public void setNullStep() {
        step = 0;
        lines.clear();
    }

    public int getStep() {
        return step;
    }
}
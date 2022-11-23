package fractals;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class LevyFractalPanel extends JPanel {
    public static int sizeOfPanel = 590;
    private int step = 0;
    public boolean flagToContinue = true;
    public static ArrayList<MyLine> lines = new ArrayList<>();
    public static double[][] pattern = {
            {0, 0},
            {0.5, 0.5},
            {1, 0}
    };
    static Point A = new Point(2 * sizeOfPanel / 3, sizeOfPanel / 2);
    static Point B = new Point(sizeOfPanel / 3, sizeOfPanel / 2);

    public void draw() {
        if (flagToContinue) {
            if (lines.size() == 0) {
                lines.add(new MyLine(A.x, A.y, B.x, B.y));
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
            if (step > 20) return;
            lines = bufferLines; // забываем про старые линии, так как они не актуальны, и запоминаем новые
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
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
import fractals.CantorFractal;
import fractals.LevyFractalPanel;
import fractals.MinkowskiFractalPanel;
import fractals.SerpinskiyFractalPanel;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class FractalsFrame extends JFrame {
    private final static int sizeOfFrame = 600;
    private final AtomicBoolean key = new AtomicBoolean(false);

    FractalsFrame() {
        JMenu fileMenu = new JMenu("Выбрать фрактал");
        //Фрактал Леви
        JMenuItem LevyFractalItem = new JMenuItem("Фрактал Леви");
        LevyFractalItem.addActionListener(e -> {
            LevyFractalPanel panel = new LevyFractalPanel();
            panel.setLayout(null);
            // кнопка update
            JButton updateButton = new JButton("Еще одна итерация");
            updateButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            updateButton.setFocusable(false);
            updateButton.addActionListener(e13 -> {
                panel.flagToContinue = true; //позволяем рисовать
                panel.stepIncrement();
                panel.repaint();
            });

            // reset
            JButton resetButton = new JButton("Сброс");
            resetButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            resetButton.setFocusable(false);
            resetButton.addActionListener(e1 -> {
                key.set(false);
                panel.setNullStep();
                panel.flagToContinue = true;
                panel.repaint();
            });

            // кнопка auto
            JButton autoButton = new JButton("Авто воспроизведение");
            autoButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            autoButton.setFocusable(false);
            autoButton.addActionListener(e1 -> new Thread(() -> {
                key.set(true);
                while (key.get()) {
                    try {
                        Thread.sleep(1000);
                        if (!key.get()) return;
                        panel.stepIncrement();
                        panel.flagToContinue = true;
                        panel.repaint();
                        if (panel.getStep() > 20) panel.setNullStep();
                    } catch (InterruptedException ignored) {
                        return;
                    }
                }
            }).start());

            panel.add(updateButton).setBounds(17, 500, 170, 30);
            panel.add(resetButton).setBounds(207, 500, 170, 30);
            panel.add(autoButton).setBounds(397, 500, 170, 30);

            getContentPane().removeAll();
            getContentPane().add(panel);
            pack();
        });

        JMenuItem minkowskiFractalItem = new JMenuItem("Фрактал Минковского");
        minkowskiFractalItem.addActionListener(e -> {
            MinkowskiFractalPanel panel = new MinkowskiFractalPanel();
            panel.setLayout(null);

            JButton updateButton = new JButton("Еще одна итерация");
            updateButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            updateButton.setFocusable(false);
            updateButton.addActionListener(e13 -> {
                panel.flagToContinue = true;
                panel.stepIncrement();
                panel.repaint();
            });

            JButton resetButton = new JButton("Сброс");
            resetButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            resetButton.setFocusable(false);
            resetButton.addActionListener(e1 -> {
                key.set(false);
                panel.setNullStep();
                panel.flagToContinue = true;
                panel.repaint();
            });

            JButton autoButton = new JButton("Авто воспроизведение");
            autoButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            autoButton.setFocusable(false);
            autoButton.addActionListener(e1 -> new Thread(() -> {
                key.set(true);
                while (key.get()) {
                    try {
                        Thread.sleep(1000);
                        if (!key.get()) return;
                        panel.stepIncrement();
                        panel.flagToContinue = true;
                        panel.repaint();
                        if (panel.getStep() > 6) panel.setNullStep();
                    } catch (InterruptedException ignored) {
                        return;
                    }
                }
            }).start());

            panel.add(updateButton).setBounds(17, 500, 170, 30);
            panel.add(resetButton).setBounds(207, 500, 170, 30);
            panel.add(autoButton).setBounds(397, 500, 170, 30);

            getContentPane().removeAll();
            getContentPane().add(panel);
            pack();
        });

        JMenuItem cantorFractalItem = new JMenuItem("Фрактал Кантора");
        cantorFractalItem.addActionListener(e -> {
            CantorFractal cantorPanel = new CantorFractal();
            cantorPanel.setLayout(null);

            JButton updateButton = new JButton("Еще одна итерация");
            updateButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            updateButton.setFocusable(false);
            updateButton.addActionListener(e1 -> {
                cantorPanel.stepIncrement();
                cantorPanel.repaint();
            });

            JButton resetButton = new JButton("Сброс");
            resetButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            resetButton.setFocusable(false);
            resetButton.addActionListener(e1 -> {
                key.set(false);
                cantorPanel.setNullStep();
                cantorPanel.repaint();
            });

            JButton autoButton = new JButton("Авто воспроизведение");
            autoButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            autoButton.setFocusable(false);
            autoButton.addActionListener(e1 -> new Thread(() -> {
                key.set(true);
                while (key.get()) {
                    try {
                        Thread.sleep(1000);
                        if (!key.get()) return;
                        cantorPanel.stepIncrement();
                        System.out.print("Before repaint = " + cantorPanel.getStep() + " ");
                        cantorPanel.repaint();
                        if (cantorPanel.getStep() == 14) {
                            cantorPanel.setNullStep();
                        }
                    } catch (InterruptedException ignored) {
                        return;
                    }
                }
            }).start());


            cantorPanel.add(updateButton).setBounds(17, 500, 170, 30);
            cantorPanel.add(resetButton).setBounds(207, 500, 170, 30);
            cantorPanel.add(autoButton).setBounds(397, 500, 170, 30);

            getContentPane().removeAll();
            getContentPane().add(cantorPanel);
            pack();
        });

        JMenuItem serpinskiyTriangleItem = new JMenuItem("Фрактал Серпинского");
        serpinskiyTriangleItem.addActionListener(e -> {
            SerpinskiyFractalPanel serpinskiyPanel = new SerpinskiyFractalPanel();
            serpinskiyPanel.setLayout(null);
            getContentPane().removeAll();
            getContentPane().add(serpinskiyPanel);
            JButton updateButton = new JButton("Еще одна итерация");
            updateButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            updateButton.setFocusable(false);
            updateButton.addActionListener(e12 -> {
                serpinskiyPanel.stepIncrement();
                serpinskiyPanel.repaint();
            });

            JButton resetButton = new JButton("Сброс");
            resetButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            resetButton.setFocusable(false);
            resetButton.addActionListener(e1 -> {
                key.set(false);
                serpinskiyPanel.setNullStep();
                serpinskiyPanel.repaint();
            });

            JButton autoButton = new JButton("Авто воспроизведение");
            autoButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            autoButton.setFocusable(false);
            autoButton.addActionListener(e1 -> new Thread(() -> {
                key.set(true);
                while (key.get()) {
                    try {
                        Thread.sleep(1000);
                        if (!key.get()) return;
                    } catch (InterruptedException interruptedException) {
                        return;
                    }
                    serpinskiyPanel.stepIncrement();
                    serpinskiyPanel.repaint();
                    if (serpinskiyPanel.getStep() == 10) serpinskiyPanel.setNullStep();
                }
            }).start());


            serpinskiyPanel.add(updateButton).setBounds(17, 500, 170, 30);
            serpinskiyPanel.add(resetButton).setBounds(207, 500, 170, 30);
            serpinskiyPanel.add(autoButton).setBounds(397, 500, 170, 30);

            pack();
        });

        JMenuItem exitItem = new JMenuItem("Выход");
        exitItem.addActionListener(e -> System.exit(0));


        fileMenu.add(minkowskiFractalItem);
        fileMenu.addSeparator();
        fileMenu.add(cantorFractalItem);
        fileMenu.addSeparator();
        fileMenu.add(serpinskiyTriangleItem);
        fileMenu.add(LevyFractalItem);
        fileMenu.addSeparator();
        fileMenu.add(exitItem);


        JMenuBar menuBar = new JMenuBar();
        menuBar.add(fileMenu);
        setJMenuBar(menuBar);

        setPreferredSize(new Dimension(sizeOfFrame, sizeOfFrame)); // задаем размеры окна
        pack();

    }
}
import fractals.CantorFractal;
import fractals.LevyFractalPanel;
import fractals.MinkowskiFractalPanel;
import fractals.SerpinskiyFractalPanel;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class FractalsFrame extends JFrame {
    private final static int sizeOfFrame = 650;
    private final AtomicBoolean key = new AtomicBoolean(false);
    private int steps = 4;
    private int range = 5;


    private void CheckRange(){ // Проверка шагов и диапазона
        if (range < 2) {range = 5;}
        if (steps < 0) {steps = 0;}
        if (steps >= range) {
            steps = range;
        }
    }

    FractalsFrame() {
        JMenu fileMenu = new JMenu("Выбрать фрактал");
        //Фрактал Леви
        JMenuItem LevyFractalItem = new JMenuItem("Леви");
        LevyFractalItem.setSize(this.getWidth(), this.getHeight());
        LevyFractalItem.addActionListener(e -> {
            LevyFractalPanel panel = new LevyFractalPanel();
            panel.setLayout(null);
            panel.MaxStep = range;

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
                        if (steps > 0) {
                            Thread.sleep(10);
                            steps -= 1;
                            if (steps <= 0) {
                                panel.stepIncrement();
                                panel.flagToContinue = true;
                                panel.repaint();
                                key.set(false);
                            }
                        } else {
                            Thread.sleep(1000);
                        }
                        if (!key.get()) return;
                        panel.stepIncrement();
                        panel.flagToContinue = true;
                        panel.repaint();
                        if (panel.getStep() > range) panel.setNullStep();
                    } catch (InterruptedException ignored) {
                        return;
                    }
                }
            }).start());

            panel.add(updateButton).setBounds(17, sizeOfFrame - 100, 170, 30);
            panel.add(resetButton).setBounds(sizeOfFrame / 2 - 170/2, sizeOfFrame - 100, 170, 30);
            panel.add(autoButton).setBounds(sizeOfFrame - 203, sizeOfFrame - 100, 170, 30);

            getContentPane().removeAll();
            getContentPane().add(panel);
            pack();

            DrawSteps(steps, autoButton);

        });


        JMenuItem minkowskiFractalItem = new JMenuItem("Минковского");
        minkowskiFractalItem.addActionListener(e -> {
            MinkowskiFractalPanel panel = new MinkowskiFractalPanel();
            panel.setLayout(null);
            panel.MaxRange = range;


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
                        if (steps > 0) {
                            Thread.sleep(10);
                            steps -= 1;
                            if (steps <= 0) {
                                panel.stepIncrement();
                                panel.flagToContinue = true;
                                panel.repaint();
                                key.set(false);
                            }
                        } else {
                            Thread.sleep(1000);
                        }
                        if (!key.get()) return;
                        panel.stepIncrement();
                        panel.flagToContinue = true;
                        panel.repaint();
                        if (panel.getStep() > range) panel.setNullStep();
                    } catch (InterruptedException ignored) {
                        return;
                    }
                }
            }).start());

            panel.add(updateButton).setBounds(17, sizeOfFrame - 100, 170, 30);
            panel.add(resetButton).setBounds(sizeOfFrame / 2 - 170/2, sizeOfFrame - 100, 170, 30);
            panel.add(autoButton).setBounds(sizeOfFrame - 203, sizeOfFrame - 100, 170, 30);

            getContentPane().removeAll();
            getContentPane().add(panel);
            pack();

            DrawSteps(steps, autoButton);

        });

        JMenuItem cantorFractalItem = new JMenuItem("Кантора");
        cantorFractalItem.addActionListener(e -> {
            CantorFractal panel = new CantorFractal();
            panel.width = sizeOfFrame - 2*panel.paddingX;
            panel.setLayout(null);

            panel.step = steps;
            panel.maxRange = range;


            JButton updateButton = new JButton("Еще одна итерация");
            updateButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            updateButton.setFocusable(false);
            updateButton.addActionListener(e1 -> {
                panel.stepIncrement();
                panel.repaint();
            });

            JButton resetButton = new JButton("Сброс");
            resetButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            resetButton.setFocusable(false);
            resetButton.addActionListener(e1 -> {
                key.set(false);
                panel.setNullStep();
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
                        System.out.print("Before repaint = " + panel.getStep() + " ");
                        panel.repaint();
                        if (panel.getStep() == range) {
                            Thread.sleep(1000);
                            panel.repaint();
                            panel.setNullStep();
                        }
                    } catch (InterruptedException ignored) {
                        return;
                    }
                }
            }).start());


            panel.add(updateButton).setBounds(17, sizeOfFrame - 100, 170, 30);
            panel.add(resetButton).setBounds(sizeOfFrame / 2 - 170/2, sizeOfFrame - 100, 170, 30);
            panel.add(autoButton).setBounds(sizeOfFrame - 203, sizeOfFrame - 100, 170, 30);

            getContentPane().removeAll();
            getContentPane().add(panel);
            pack();
        });

        JMenuItem serpinskiyTriangleItem = new JMenuItem("Серпинского");
        serpinskiyTriangleItem.addActionListener(e -> {
            SerpinskiyFractalPanel panel = new SerpinskiyFractalPanel();
            panel.sizeOfPanel = sizeOfFrame;
            panel.setLayout(null);
            panel.MaxStep = range;

            getContentPane().removeAll();
            getContentPane().add(panel);
            JButton updateButton = new JButton("Еще одна итерация");
            updateButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            updateButton.setFocusable(false);
            updateButton.addActionListener(e12 -> {
                panel.stepIncrement();
                panel.repaint();
            });

            JButton resetButton = new JButton("Сброс");
            resetButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            resetButton.setFocusable(false);
            resetButton.addActionListener(e1 -> {
                key.set(false);
                panel.setNullStep();
                panel.repaint();
            });

            JButton autoButton = new JButton("Авто воспроизведение");
            autoButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            autoButton.setFocusable(false);
            autoButton.addActionListener(e1 -> new Thread(() -> {
                key.set(true);
                while (key.get()) {
                    try {
                        if (steps > 0) {
                            Thread.sleep(10);
                            steps -= 1;
                            if (steps <= 0) {
                                panel.stepIncrement();
                                panel.repaint();
                                key.set(false);
                            }
                        } else {
                            Thread.sleep(1000);
                        }
                        //Thread.sleep(1000);
                        if (!key.get()) return;
                    } catch (InterruptedException interruptedException) {
                        return;
                    }
                    panel.stepIncrement();
                    panel.repaint();
                    if (panel.getStep() == (range + 1)) panel.setNullStep();
                }
            }).start());


            panel.add(updateButton).setBounds(17, sizeOfFrame - 100, 170, 30);
            panel.add(resetButton).setBounds(sizeOfFrame / 2 - 170/2, sizeOfFrame - 100, 170, 30);
            panel.add(autoButton).setBounds(sizeOfFrame - 203, sizeOfFrame - 100, 170, 30);
            pack();

            DrawSteps(steps, autoButton);

        });

        JMenuItem exitItem = new JMenuItem("Выход");
        exitItem.addActionListener(e -> System.exit(0));


        fileMenu.add(minkowskiFractalItem);
        fileMenu.addSeparator();
        fileMenu.add(cantorFractalItem);
        fileMenu.addSeparator();
        fileMenu.add(serpinskiyTriangleItem);
        fileMenu.addSeparator();
        fileMenu.add(LevyFractalItem);
        fileMenu.addSeparator();
        fileMenu.add(exitItem);


        JToolBar tbStyle = new JToolBar();
        tbStyle.setFloatable(false);
        tbStyle.add(LevyFractalItem);
        tbStyle.add(minkowskiFractalItem);
        tbStyle.add(cantorFractalItem);
        tbStyle.add(serpinskiyTriangleItem);
        //================================================================
        JTextField stepField = new JTextField("Steps = " + steps, 12);
        stepField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                System.out.println("insertUpdate");
                try {
                    steps = (int)Double.parseDouble(stepField.getText());
                    System.out.println(steps);
                } catch (Exception exp) {}
                finally {
                    CheckRange();
                }

            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                System.out.println("removeUpdate");
                try {
                    steps = (int)Double.parseDouble(stepField.getText());
                    System.out.println(steps);
                } catch (Exception exp) {}
                finally {
                    CheckRange();
                }
            }
            @Override
            public void changedUpdate(DocumentEvent e) {}
        });
        tbStyle.add(stepField);

        JTextField RangeField = new JTextField("Range = " + range, 12);
        RangeField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                System.out.println("insertUpdate");
                try {
                    range = (int)Double.parseDouble(RangeField.getText());
                    System.out.println(range);
                } catch (Exception exp) {}
                finally {
                    CheckRange();
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                System.out.println("removeUpdate");
                try {
                    range = (int)Double.parseDouble(RangeField.getText());
                    System.out.println(range);
                } catch (Exception exp) {}
                finally {
                    CheckRange();
                }
            }
            @Override
            public void changedUpdate(DocumentEvent e) {}
        });
        tbStyle.add(RangeField);

        //================================================================

        JMenuBar menuBar = new JMenuBar();
        menuBar.add(tbStyle);
        setJMenuBar(menuBar);

        setPreferredSize(new Dimension(sizeOfFrame, sizeOfFrame)); // задаем размеры окна
        pack();
    }
    public void DrawSteps(int temp, JButton button) {
        if (temp > 1) {
            button.doClick();
        } else {return;}
    }
}
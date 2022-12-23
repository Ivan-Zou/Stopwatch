package stopwatch;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.Box;

import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.Font;

public class RunStopwatch implements Runnable {
    @Override
    public void run() {
        final JFrame frame = new JFrame("Stopwatch");
        frame.setPreferredSize(new Dimension(Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT));

        final JPanel timePanel = new JPanel();
        frame.add(timePanel, BorderLayout.CENTER);
        final JLabel stopwatchTime = new JLabel("00:00:00.00");
        stopwatchTime.setFont(new Font(null, Font.PLAIN, Constants.TIME_FONT_SIZE));
        final StopwatchMechanics stopwatch = new StopwatchMechanics(stopwatchTime);
        timePanel.add(stopwatchTime);

        final JPanel controlPanel = new JPanel();
        frame.add(controlPanel, BorderLayout.SOUTH);

        final JButton reset = new JButton("           ");
        reset.setFocusable(false);
        reset.addActionListener(actionEvent -> {
            if (stopwatch.isStopped()) {
                reset.setText("           ");
                stopwatch.resetStatus();
            }
        });
        controlPanel.add(reset);

        // Spacer
        controlPanel.add(Box.createRigidArea(new Dimension(Constants.SPACER_WIDTH, 0)));

        final JButton startStop = new JButton("Start");
        startStop.setFocusable(false);
        startStop.addActionListener(actionEvent -> {
            if (stopwatch.isReset() || stopwatch.isStopped()) {
                startStop.setText("Stop");
                reset.setText("           ");
                stopwatch.runStatus();
            } else {
                startStop.setText("Start");
                reset.setText("Reset");
                stopwatch.stopStatus();
            }
        });
        controlPanel.add(startStop);

        frame.pack();
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
    }
}

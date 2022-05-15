import stopwatch.RunStopwatch;

import javax.swing.SwingUtilities;

public class OpenStopwatch {
    public static void main(String[] args) {
        Runnable stopwatch = new RunStopwatch();
        SwingUtilities.invokeLater(stopwatch);
    }
}

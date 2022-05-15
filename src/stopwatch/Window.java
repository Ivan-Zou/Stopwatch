package stopwatch;

import javax.swing.JLabel;
import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Window {
    private final JLabel time;
    private final Stopwatch stopwatch;
    private boolean clickedStart, clickedStop;
    private Status status;

    public Window(JLabel time) {
        this.time = time;
        status = Status.RESET;
        stopwatch = new Stopwatch();
        clickedStart = false;
        clickedStop = false;

        Timer timer = new Timer(Constants.REFRESH_RATE, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (isReset()) {
                    reset();
                } else if (isRunning()) {
                    run();
                } else {
                    stop();
                }
            }
        });

        timer.start();
    }

    public boolean isReset() {
        return status == Status.RESET;
    }

    private void reset() {
        time.setText("00:00:00.00");
        stopwatch.reset();
        clickedStart = false;
        clickedStop = false;
    }

    public boolean isRunning() {
        return status == Status.RUNNING;
    }

    private void run() {
        if (!clickedStart) {
            stopwatch.start();
            clickedStart = true;
            clickedStop = false;
        }
        time.setText(stopwatch.getCurrentTime());
    }

    public boolean isStopped() {
        return status == Status.STOPPED;
    }

    private void stop() {
        if (!clickedStop) {
            stopwatch.stop();
            clickedStart = false;
            clickedStop = true;
        }

        time.setText(stopwatch.getStoppedTime());
    }

    public void resetStatus() {
        status = Status.RESET;
    }

    public void runStatus() {
        status = Status.RUNNING;
    }

    public void stopStatus() {
        status = Status.STOPPED;
    }
}

package model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

import eventHandling.Subject;

/**
 * GameTimer
 * <p>
 * pre-condition: Subject eventHandler instance passed
 * 
 * @param void
 *        <p>
 *        Invariant:
 *        <p>
 *        Post-Condition: Initialises the Game timer and creates a listener for
 *        the tick event
 * @return void
 * 
 * @author Brett Robertson (s3437164)
 */
public class GameTimer {
	int oneSecond;
	int time;
	int defaultTime;
	private Timer timer;
	private Subject eventHandler;
	ActionListener listener;

	public GameTimer(Subject eventHandler) {
		this.eventHandler = eventHandler;
		oneSecond = 1000; // 1000 ms
		defaultTime = 30; // seconds
		time = defaultTime;
		this.listener = new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				action();
				// this.eventHandler.setEvent("tick" + startTimer(false));
				// add observer here, note add timeout observer in timeout
				// controller can call startTime(true) to reset timer
			}
		};
		this.timer = new Timer(oneSecond, this.listener);
	}

	private void action() {
		this.eventHandler.setEvent("tick" + startTimer(false));
	}

	/**
	 * decrementTime
	 * <p>
	 * Invariant: Result equals time > 0
	 * <p>
	 * Post-Condition: timer decrement by 1 second
	 * 
	 * @return int: the decremented result of the current time
	 */
	private int decrementTime() {
		time--;
		return time;
	}

	/**
	 * resetTime
	 * <p>
	 * 
	 * @param void
	 *        <p>
	 *        Invariant: time equals default time
	 *        <p>
	 *        Post-Condition: current time equals the default time (30 seconds)
	 * @return void
	 */
	private int resetTime() {
		time = defaultTime;
		return time;
	}

	/**
	 * stopTimer
	 * <p>
	 * Pre-condition: timer instance exists
	 * 
	 * @param void
	 *        <p>
	 *        Invariant: timer events cleared and timer stopped
	 *        <p>
	 *        Post-Condition: timer stopped
	 * @return void
	 */
	public void stopTimer() {
		timer.stop();
	}

	/**
	 * getTime
	 * <p>
	 * 
	 * @param void
	 *        <p>
	 *        Invariant: time is the current time
	 *        <p>
	 *        Post-Condition:
	 * @return int: get the current time in seconds
	 */
	private int getTime() {
		return time;
	}

	/**
	 * timeout
	 * <p>
	 * Pre-condition: Timeout Observer must be attached to the eventHandler
	 * 
	 * @param void
	 *        <p>
	 *        Invariant: the subject will get event "timeout"
	 *        <p>
	 *        Post-Condition: the timeout observer will be called
	 * @return void
	 */
	private void timeout() {
		// add timeout observer
		eventHandler.setEvent("timeout");
	}

	/**
	 * startTimer
	 * <p>
	 * Pre-condition: timer instance exists
	 * 
	 * @param boolean: True; restart timer to default, false; start timer from
	 *        current time
	 *        <p>
	 *        Invariant: timer != null <br>
	 *        timer.getTime() > 0
	 *        <p>
	 *        Post-Condition: timer will be running until zero
	 * @return void
	 */
	public int startTimer(boolean reset) {
		if (reset) {
			resetTime();
			timer.start();
		} else {
			if (decrementTime() < 1) {
				timeout();
				timer.stop();
			}
			timer.start();
		}
		return getTime();

	}

}
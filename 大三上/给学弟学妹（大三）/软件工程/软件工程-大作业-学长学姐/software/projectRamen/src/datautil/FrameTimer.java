package datautil;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.Timer;

import pages.PageController;

/**
*  The FrameTimer class provides a timer service.
*  Title      : FrameTimer.java
*  <p>
*  Description: Basic timer functionality, including actionlisteners
*  <p>
*  @author  Tianyi Liu
*  @version 1.0
*/
public class FrameTimer {

	private int nowtime;
	private int originTime;
	private JLabel timerLabel;
	private PageController pc;
	
	/** Constructor of FrameTimer
	* @param time The initial time of the timer
	* @param timerLabel Timer controlled label
	* @param pc The page controller of the parent page
	*/
	public FrameTimer(int time, JLabel timerLabel, PageController pc) {
		this.nowtime=time;
		this.originTime=time;
		this.timerLabel=timerLabel;
		this.pc=pc;
	}

	public ActionListener timeTaskPerformer = new ActionListener() {
	    public void actionPerformed(ActionEvent evt) {
	    	if(getNowTime()==0) {
				pc.nowstop();
				pc.nostop = false;
	    	}
	        decreaseTime();
	        timerLabel.setText(nowtime+"s");
	    }
	  };
	  
	/** 
	 * ActionPerformed function of the mouse event
	*/  
	public void mouseActionPerformed() {
		  setNowTime(originTime);
		  timerLabel.setText(nowtime+"s");
	}
	  
    synchronized private void decreaseTime() {
  	  nowtime--;
    }
    
    synchronized private void setNowTime(int time) {
  	  nowtime=time;
    }
    
    synchronized private int getNowTime() {
    	  return nowtime;
    }
    

}

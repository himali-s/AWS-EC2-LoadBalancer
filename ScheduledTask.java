package ec2;


import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;

import java.io.*;  

import javax.servlet.*;  
import javax.servlet.http.*;  
	
	
	// Create a class extends with TimerTask
	public class ScheduledTask  extends Thread {
		public static void main(String[] args) {
		final long timeInterval = 5000*60;
		  Runnable runnable = new Runnable() {
		  public void run() {
		    while (true) {
		    	System.out.println("Refreshing...");
		    	Linuxcommand l = new Linuxcommand();
		    	l.display();
		    	
		      try {
		    	
		       Thread.sleep(timeInterval);
		       
		      } catch (InterruptedException e) {
		        e.printStackTrace();
		      }
		      }
		    }
		  };
		  Thread thread = new Thread(runnable);
		  thread.start();
		}
	}
	
	
	
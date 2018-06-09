package ec2;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.io.InputStream;
import java.net.URL;
import java.util.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.jcraft.jsch.*;


/**
 * Servlet implementation class Getlinuxcommand
 */
public  class Linuxcommand extends HttpServlet    {
	private static final long serialVersionUID = 1L;
    int temp;
  static   List<Integer> lm = new ArrayList<>();
  static List<Integer> li = new ArrayList<>();
  static  List<String> last = new ArrayList<>();
  static List<String> ips = new ArrayList<>();
  static List<String> lm1 = new ArrayList<>();
  static List<Integer> intusg = new ArrayList<>();
  static List<Float> ramusg = new ArrayList<>();
  static List<Float> fltusg = new ArrayList<>();
   static List<Float> k = new ArrayList<>();
   
  static int ab=1;
  public void display()
  {
	  
		
		float a;
		boolean check = true;
		System.out.println("Values of Cpu Utilization");
		for (int i = 0; i < ips.size(); i++) {
			try {
				JSch jsch = new JSch();
				String user = "ubuntu";
				String host = ips.get(i);
				int port = 22;
				String privateKey = "E:\\keymy.pem";
				String command1 = "grep 'cpu' /proc/stat| awk '{usage=($2+$4)*100/($2+$5+$4)}END {print usage}' ";
				String command2 = "free -m | awk 'NR==2{printf  $3,$2,$3*100/$2 }'";
				
				jsch.addIdentity(privateKey); 
				com.jcraft.jsch.Session session = jsch.getSession(user, host, port);
				java.util.Properties config = new java.util.Properties();
				config.put("StrictHostKeyChecking", "no");
				session.setConfig(config);
				session.connect();
				com.jcraft.jsch.Channel channel = session.openChannel("exec");
				((ChannelExec) channel).setCommand(command1 );
				
				channel.setInputStream(null);
				((ChannelExec) channel).setErrStream(System.err);
				InputStream in = channel.getInputStream();
				channel.connect();
				byte[] tmp = new byte[1024];
				while (check) {
					while (in.available() > 0) {
						int m = in.read(tmp, 0, 1024);
						if (m < 0)
							break;
						String g=(new String(tmp, 0, m));
						System.out.println(" cpu usage float value :"+ g);
						a = Float.parseFloat(g);
						float mi=(float) (a *0.6);
						
						k.add(mi);
						System.out.println("cpu usage list is "+k);
						
				}
					if (channel.isClosed()) {
						//System.out.println(check);
						//System.out.println("exit-status: " + channel.getExitStatus());
						break;
					}
					//try {
					//	Thread.sleep(1000);
					//} catch (Exception ee) {
					//}
				}
				
//  ...........new channel start ...............................................
				com.jcraft.jsch.Channel channel1 = session.openChannel("exec");
				((ChannelExec) channel1).setCommand(command2 );
				
				channel1.setInputStream(null);
				((ChannelExec) channel1).setErrStream(System.err);
				InputStream inn = channel1.getInputStream();
				channel1.connect();
				byte[] tmp1 = new byte[1024];
				while (check) {
					while (inn.available() > 0) {
						int m = inn.read(tmp1, 0, 1024);
						if (m < 0)
							break;
						String g=(new String(tmp1, 0, m));
						System.out.println("ram usage value  :"+ g);
						a = Float.parseFloat(g);
						float mi=(float) (a*0.4);
						ramusg.add(mi);
						System.out.println("ram usage is :"+ramusg);
						/*						System.out.println("1st" + k.get(0));
*/					}
					if (channel1.isClosed()) {
						//System.out.println(check);
						//System.out.println("exit-status: " + channel.getExitStatus());
						break;
					}
					//try {
					//	Thread.sleep(1000);
					//} catch (Exception ee) {
					//}
				}
				channel.disconnect();
				channel1.disconnect();
				session.disconnect();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		float y=0;
		for(int i=0;i<ips.size();i++)
		{
			
			y=k.get(i)+ramusg.get(i);
			intusg.add((int) y);
			fltusg.add(y);
			System.out.println("the final int usage is "+intusg.get(i));
			System.out.println("the float final usage is :"+fltusg);
		}
		Linuxcommand d = new Linuxcommand();
		 int n=d.getGCD(intusg);
		 double ceilingValue = Math.ceil(n);
	        int intValue = (int) ceilingValue;
		 try{
			 for(int p=0;p<ips.size();p++)
			 {
			 int c=lm.get(p);
			 for(int u=0;u<c;u++)
			 {
            last.add(lm1.get(p));
            System.out.println(lm1.get(p));
            }
			 }
		 }
			 catch (Exception e) {
					e.printStackTrace();
				}      
  }
	public int getGCD(int a, int b) {
		 int temp =  0;
	        while(b!=0){
	           temp=b;
	            b=a%b;
	            a=temp;
	        }
	        a=a<0 ? a * (-1):a;
	        
	        return a;
		}
	public int getGCD(List<Integer> k) {
		  // the GCD of a number with itself is... itself
		  int gcd = k.get(0);
		  // compute incrementally
		  for( int i=1; i<k.size(); i++ ) {
		    gcd = getGCD( gcd, k.get(i));
		  }
		  int size=k.size();
			 int[] ratio = new int[size];
			  for( int o=0; o<k.size(); o++ ) {
				  ratio[o]=(int) ((k.get(o))/gcd);
		
						  }
			 decrease(ratio);
			  for( int o=0; o<k.size(); o++ ) {
				  ratio[o]=(int) ((k.get(o))/gcd);
			  }
			 int max=getMax(ratio);
			 int min=getMin(ratio);
			 int add=max+min;
			int sum=0;
			 for( int num : ratio) {
		          sum = sum+num;
		      }
			 int store[]= new int[sum];
			 for(int s=0;s<ips.size();s++)
			 {
				 int f=ratio[s];
				 store[s]=(add-f);   
				 System.out.print("the  Array  is : ");
				 System.out.println(store[s]);
				
			 }
			 
			 decrease(store);
			
		//	 System.out.println("li is :"+li);
			 Map m1= new HashMap();
			
				for(int i=0;i<ips.size();i++)
				{
					
					m1.put(fltusg.get(i),ips.get(i));
					//System.out.println("m1 is "+m1);
				}
				System.out.println("mi is "+m1); 
				 Collections.sort(fltusg);
				 Collections.sort(li, Collections.reverseOrder());
				 System.out.println(" reverse li is :"+fltusg);
				 
				 for(int j =0; j<ips.size();j++){
					 String val=(String)m1.get(fltusg.get(j));
					 lm1.add(val);
					 System.out.println("lm1 is "+lm1.get(j));
				}
				 decrease(store);
				 for(int i =0; i<ips.size();i++){																		
						lm.add(store[i]);
					}
					System.out.println("New list of ips" +lm1);
		  return gcd; 
	}
		  
	public  void decrease(int arr[])
		  {
			  int l=arr.length;
		  for (int i = 0; i <arr.length ; i++) 
	        {
	            for (int j = i + 1; j < arr.length; j++) 
	            {
	                if (arr[i] < arr[j]) 
	                {
	                    temp = arr[i];
	                    arr[i] = arr[j];
	                    arr[j] = temp;
	                }
	            }
	        }
		  }
	        public static int getMax(int[] inputArray){ 
	            int maxValue = inputArray[0]; 
	            for(int i=1;i < inputArray.length;i++){ 
	              if(inputArray[i] > maxValue){ 
	                 maxValue = inputArray[i]; 
	              } 
	            } 
	            return maxValue; 
	          }
	         
	          // Method for getting the minimum value
	          public static int getMin(int[] inputArray){ 
	            int minValue = inputArray[0]; 
	            for(int i=1;i<inputArray.length;i++){ 
	              if(inputArray[i] < minValue){ 
	                minValue = inputArray[i]; 
	              } 
	            } 
	            return minValue; 
	          } 
	       

	public Linuxcommand() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// TODO Auto-generated method stub
		// response.getWriter().append("Served at:
		// ").append(request.getContextPath());
		 
		HttpSession h = request.getSession();
		h.setAttribute("final", last);
		
		ips = (List<String>) h.getAttribute("list");
		response.sendRedirect("red.jsp");
		
		 }	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		doGet(request, response);
	}

}
package ec2;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.ec2.AmazonEC2Client;
import com.amazonaws.services.ec2.model.DescribeInstancesResult;
import com.amazonaws.services.ec2.model.Instance;
import com.amazonaws.services.ec2.model.Reservation;

/**
 * Servlet implementation class Getlinuxcommand
 */
public class Getlinuxcommand extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private  static String acessKey="AKIAIWXGK3X5WQQULCSQ";
	private static String secretKey="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";
	   private static Regions region=Regions.AP_SOUTH_1;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Getlinuxcommand() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		{
			AWSCredentials awsCredentials = new BasicAWSCredentials(acessKey, secretKey);
			AmazonEC2Client  ec2client = new AmazonEC2Client(awsCredentials);
			

			ec2client.setRegion(Region.getRegion(region));
				
				List<String> instanceIps = new ArrayList<String>(); 
				
			    DescribeInstancesResult result = ec2client.describeInstances();

				List<Reservation> reservations = result.getReservations();
				for (Reservation reservation : reservations) {
					for (Instance instance : reservation.getInstances()) {
						
							instanceIps.add(instance.getPublicIpAddress());	
							System.out.println(instance.getPublicIpAddress());
						}
					}
				HttpSession s=request.getSession();
				s.setAttribute("list", instanceIps);
				response.sendRedirect("Linuxcommand");
		}
		}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

}

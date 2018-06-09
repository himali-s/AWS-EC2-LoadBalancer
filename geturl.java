package ec2;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class geturl
 */
public class geturl extends HttpServlet {
	private static final long serialVersionUID = 1L;
	 static int a=0;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public geturl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String s=request.getParameter("text");
		HttpSession h = request.getSession();
		List<String> ips = new ArrayList<>();
		ips = (List<String>) h.getAttribute("list");
		List<Integer> lastt = new ArrayList<>();
		lastt=(List<Integer>) h.getAttribute("final");
		
		
		/*requests=(List<Integer>) h.getAttribute("final");
		System.out.println(requests.get(a));
		int a1 = requests.get(a);
		System.out.println(a);*/
		try{
			
				 response.sendRedirect("http://" + lastt.get(a) + ":8080/html/"+s);
				
			
			a++;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	
		
		 //URL urll = new URL("http",ips.get(0), 8080, "/html/ab.html");
		 //response.sendRedirect("http://" + ips.get(0) + ":8080/html/"+s);
		 
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}

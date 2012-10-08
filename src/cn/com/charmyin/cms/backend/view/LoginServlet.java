package cn.com.charmyin.cms.backend.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import cn.com.charmyin.cms.backend.dao.AdminDao;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

public class LoginServlet extends BaseServlet {
	private Logger logger = Logger.getLogger(this.getClass());
	private AdminDao adminDao;

	private int width;//验证码图片长度和宽度
	private int height;
	private int number;//设置验证码长度
	private String codes;//有哪些字符可以给选择
	
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		
		super.init(config);
		
		width=Integer.parseInt(config.getInitParameter("width"));
		height = Integer.parseInt(config.getInitParameter("height"));
		number = Integer.parseInt(config.getInitParameter("number"));
		codes = config.getInitParameter("codes");
	}
	
	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void loginCodePic(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("image/jpeg");
		
		BufferedImage image = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
		Graphics2D g = image.createGraphics();
		
		g.setColor(Color.WHITE);
		g.fillRect(0,0,width+width/4,height);
		
		g.setColor(Color.BLACK);
		g.drawRect(0, 0, width+width/4, height);
		
		Random random = new Random();
		
		int x = (width - 2)/number;
		int y = height - 4;
		
		StringBuffer sb = new StringBuffer();
		
		//随机产生字符，并添加至sb中
		
		for(int i=0; i<number; i++){
			String code = String.valueOf(codes.charAt(random.nextInt(codes.length())));
			
			int red = random.nextInt(255);
			int green = random.nextInt(255);
			int blue = random.nextInt(255);
			
			g.setColor(new Color(red,green,blue));
			
			Font font = new Font("Arial",Font.BOLD+Font.ITALIC,random(height/2,height-height/4));
			
			g.setFont(font);
			double rd = random.nextInt(2)%2==0?random.nextDouble()/10:-random.nextDouble()/10;
			g.rotate(rd);
			g.drawString(code, i*x+1, Math.round(y*0.9d));
			g.rotate(-rd);
			sb.append(code);
		}
		
		request.getSession().setAttribute("codes", sb.toString());
		
		for(int i=0; i<50; i++){
			int red = random.nextInt(255);
			int green = random.nextInt(255);
			int blue = random.nextInt(255);
			g.setColor(new Color(red,green,blue));
			g.drawOval(random.nextInt(width), random.nextInt(height), 1, 1);
		}
		
		OutputStream out = response.getOutputStream();
		JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
		encoder.encode(image);
		
		out.flush();
		out.close();
	}

	/**
	 * 产生一个介于min和max之间的随机数
	 * @param min
	 * @param max
	 * @return
	 */
	private int random(int min, int max) {
		int m = new Random().nextInt(999999)%(max-min);
		return min+m;
	}


	//用于注入adminDao
	public void setAdminDao(AdminDao adminDao) {
		this.adminDao = adminDao;
	}

}

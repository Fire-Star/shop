package exam.domain;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Random;


public class VertifyCode {
	String vecode;
	Random random=new Random();
	String orain="23456789ABCDEFGHJKLMNPQRSTUVWXYZabcdefghijkmnpqrstuvwxyz";
	String fonts[]={"宋体","黑体","华文楷体","微软雅黑","楷体_GB2312"};
	int w=80,h=37;
	
	public VertifyCode(){
		
	}
	private int getRandomSize(){
		return random.nextInt(3)+18;
	}
	private String getRandomFont(){
		return fonts[random.nextInt(fonts.length)];
	}
	private int getRandomStyle(){
		return random.nextInt(4);
	}
	private Color getRandomColor(){
		int rgb[]=new int[3];
		for(int i=0;i<3;i++){
			rgb[i]=random.nextInt(100);
		}
		return new Color(rgb[0],rgb[1],rgb[2]);
	}
	private String getRandomText(){
		StringBuilder str=new StringBuilder();
		for(int i=1;i<=4;i++){
			str.append(orain.charAt(random.nextInt(orain.length())));
		}
		vecode=str.toString();
		return vecode;
	}
	private void setLine(Graphics2D graphics2d){
		int point[][]=new int[2][2];
		
		for(int i=1;i<=3;i++){
			point[0][0]=random.nextInt(20);
			point[0][1]=random.nextInt(22)+8;
			point[1][0]=80-random.nextInt(20);
			point[1][1]=random.nextInt(22)+8;
			
			graphics2d.setFont(new Font(getRandomFont(),getRandomStyle(),getRandomSize()));
			graphics2d.setColor(getRandomColor());
			graphics2d.drawLine(point[0][0], point[0][1], point[1][0], point[1][1]);
		}
	}
	private void setPoint(BufferedImage image){
		for(int i=1;i<=300;i++){
			int x=random.nextInt(80);
			int y=random.nextInt(30);
			image.setRGB(x, y, getRandomColor().getRGB());
		}
	}
	public BufferedImage _getImage() {
		getRandomText();
		BufferedImage image=new BufferedImage(w, h, BufferedImage.TYPE_INT_BGR);
		Graphics2D graphics2d=(Graphics2D)image.getGraphics();
		graphics2d.setColor(Color.lightGray);
		graphics2d.fillRect(0, 0, w, h);
		for(int j=1;j<=4;j++){
			int x=(int)((j-1)*w/4.0);
			graphics2d.setColor(getRandomColor());
			graphics2d.setFont(new Font(getRandomFont(), getRandomStyle(), getRandomSize()));
			graphics2d.drawString(vecode.charAt(j-1)+"", x, 22);
		}
		setLine(graphics2d);
		setPoint(image);
		return image;
	}
	public String getVertifyCode(){
		return vecode;
	}
}

package exam.service;

import java.awt.image.BufferedImage;

import exam.domain.VertifyCode;

public class VertifyCodeService {
	private static VertifyCode vertifyCode=new VertifyCode();
	
	public BufferedImage getImage(){
		return vertifyCode._getImage();
	}
	public String getVertifyCode(){
		return vertifyCode.getVertifyCode();
	}
}

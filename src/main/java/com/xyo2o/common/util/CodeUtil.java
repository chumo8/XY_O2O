package com.xyo2o.common.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;

public class CodeUtil {
	public static String createCode(int width,int height,int number,HttpServletResponse resp){
		//在内存中创建图像
		BufferedImage image = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
		//获取图形上下�?
		Graphics g = image.getGraphics();
		//随机�?
		Random random = new Random();
		//设定背景
//		   g.setColor(getRandColor(200, 250)); 
		g.fillRect(0, 0, width, height);
		//设定字体
		g.setFont(new Font("微软雅黑",Font.PLAIN,18));
		//随机产生干扰�?
		g.setColor(getRandColor(160, 200));
		for (int i = 0; i < 100; i++) {
			int x = random.nextInt(width);
			int y = random.nextInt(height);
			int xl = random.nextInt(12);
			int yl = random.nextInt(12);
			g.drawLine(x, y, x + xl, y + yl);
		}
		//随机产生4位验证码
//		   String[] codes = {"�?,"�?,"�?,"�?,"�?,"�?,"�?,"�?,"�?,"�?,"�?,"�?,"�?,"�?,"�?,"�?,"�?,"�?,"�?,"�?,"�?,"�?,"�?,"�?,"�?,"�?,"�?,"�?,"�?,"�?,"�?,"�?,"�?,"�?,"�?,"�?,"�?,"�?,"�?,"�?,"�?,"�?,"�?,"�?,"�?,"�?,"�?,"�?,"�?,"�?,"�?,"�?,"�?,"�?,"�?,"�?,"�?,"�?,"�?,"�?,"�?,"�?}; 
		String[] codes = new String[62];
		//A-Z
		for (int i = 0; i < 26; i++) {
			codes[i] = (char)(65+i)+"";
		}
		//a-z
		for (int i = 0; i < 26; i++) {
			codes[26+i] = (char)(97+i)+"";
		}
		for (int i = 0; i < 10; i++) {
			codes[52+i] = i+"";
		}
		String code = "";
		for(int i=0;i<number;i++){
			String str = codes[random.nextInt(codes.length)];
			code += str;
			// 将认证码显示到图象中
			g.setColor(new Color(50 + random.nextInt(110), 20 + random.nextInt(110), 30 + random.nextInt(110)));
			//调用函数出来的颜色相同，可能是因为种子太接近，所以只能直接生�?
			g.drawString(str, 18 * i +8, 22); //文字间距*i+距离左边距，上边�?
		}
		// 图象生效
		g.dispose();
		try {
			// 输出图象到页�?
			ImageIO.write(image, "JPEG", resp.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return code;
	}

	//获取随机颜色
	private static Color getRandColor(int fc,int bc){
		Random random = new Random();
		if(fc>255) fc=255;
		if(bc>255) bc=255;
		int r = fc + random.nextInt(bc - fc);
		int g = fc + random.nextInt(bc - fc);
		int b = fc + random.nextInt(bc - fc);
		return new Color(r,g,b);
	}
}

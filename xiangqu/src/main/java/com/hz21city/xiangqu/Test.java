/**
 * 
 */
package com.hz21city.xiangqu;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Random;

import javax.imageio.ImageIO;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			for (int i = 1; i <= 188; i++) {
				createQR(String.valueOf(i));
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (WriterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void createImg(String str) throws IOException {
		int width = 120;
		int height = 30;
		File file = new File("D:/ttt/" + str + ".jpg");
		Font font = new Font("Serif", Font.BOLD, 10);
		BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics2D g2 = (Graphics2D) bi.getGraphics();
		g2.setBackground(Color.WHITE);
		g2.clearRect(0, 0, width, height);
		g2.setPaint(Color.RED);
		FontRenderContext context = g2.getFontRenderContext();
		Rectangle2D bounds = font.getStringBounds(str, context);
		double x = (width - bounds.getWidth()) / 2;
		double y = (height - bounds.getHeight()) / 2;
		double ascent = -bounds.getY();
		double baseY = y + ascent;
		g2.drawString(str, (int) x, (int) baseY);
		ImageIO.write(bi, "jpg", file);
	}

	public static void createQR(String str) throws IOException, WriterException {
		String text = "xq_" + (new Random().nextInt(8999) + 1000) + str;
		int width = 200;
		int height = 200;
		String format = "png";
		Hashtable<EncodeHintType, String> hints = new Hashtable<>();
		hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
		BitMatrix bitMatrix = new MultiFormatWriter().encode(text, BarcodeFormat.QR_CODE, width, height, hints);
		File outputFile = new File("D:\\xq_qr\\" + str + ".png");
		MatrixToImageWriter.writeToFile(bitMatrix, format, outputFile);
	}
}

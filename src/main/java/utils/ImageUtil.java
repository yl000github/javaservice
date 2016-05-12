package utils;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageUtil {
	public static void write(BufferedImage image,String filepath) throws IOException{
		String fileType=filepath.substring(filepath.lastIndexOf(".")+1,filepath.length());
		ImageIO.write(image, fileType, new File(filepath));
	}
}

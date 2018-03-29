package ThumbnaillatorTest;
import java.awt.image.BufferedImage;  
import java.io.File;  
import java.io.FileOutputStream;  
import java.io.IOException;  
import java.io.OutputStream;  
  
import javax.imageio.ImageIO;  
  
import net.coobird.thumbnailator.Thumbnails;  
import net.coobird.thumbnailator.Thumbnails.Builder;  
import net.coobird.thumbnailator.geometry.Positions;  
import net.coobird.thumbnailator.name.Rename;
    
public class ThumbTest {
	public void test(String path) throws IOException {
		File dir = new File(path);
	    File[] fs = dir.listFiles();
	    Thumbnails.of(fs)
	              .size(400, 700)
	              .watermark(Positions.BOTTOM_RIGHT,
	                         ImageIO.read(new File("F:\\前端编程文件\\huadian\\waterPrint.png")), 0.5f)
	              .outputFormat("jpg")
	              .outputQuality(1.0f)
	              .toFiles(Rename.PREFIX_HYPHEN_THUMBNAIL);
	    Thumbnails.of(fs)
        .size(200,150)
        .outputFormat("jpg")
        .outputQuality(1.0f)
        .toFiles(Rename.PREFIX_DOT_THUMBNAIL);
/*	    Thumbnails.of(new File("F:\\前端编程文件\\huadian\\1.jpg"))
	              .size(480, 480)
	              .watermark(Positions.BOTTOM_RIGHT,
	                         ImageIO.read(new File("F:\\前端编程文件\\huadian\\waterPrint.png")), 0.5f)
	              .toFile(new File("F:\\前端编程文件\\huadian\\1a_water_1.jpg"));*/
	}
	public static void main(String[] args) {
		ThumbTest thumbTest=new ThumbTest();
		try {
			thumbTest.test("E:\\test");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

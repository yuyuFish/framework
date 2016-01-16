package org.framework.authorize.base.utils;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.Binarizer;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.EncodeHintType;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;

public class QrCodeUtils {
	protected static final Log LOG=LogFactory.getLog(QrCodeUtils.class);

	/**
	 * 
	 * 功能简述：生成二维码(QR)
	 * <br>
	 * 功能详细描述：<功能详细描述>
	 * 
	 * @param contents 二维码内容
	 * @param width	二维码图片宽度
	 * @param height 二维码图片高度
	 * @param format 图片格式 jpg png...
	 * @param stream 将二维码图片写到输出流
	 * @return 是否成功
	 */
	public static boolean generate(String contents,int width,int height,String format,OutputStream stream){
		LOG.debug(">>>>{\"contents\":\""+contents+"\",\"width\":"+width+",\"height\":"+height+",\"format\":\""+format+"\",\"stream\":\""+stream+"\"}");
		boolean result=false;
		try {
			Map<EncodeHintType, Object> hints = new HashMap<EncodeHintType, Object>();  
			hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
			hints.put(EncodeHintType.MARGIN, 0);
			BitMatrix bm=new MultiFormatWriter()
				.encode(contents, BarcodeFormat.QR_CODE, width, height,hints);
			
			MatrixToImageWriter.writeToStream(bm, format, stream);
			result=true;
		} catch (WriterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			LOG.error(e.getMessage());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			LOG.error(e.getMessage());
		}
		LOG.debug(">>>>result:"+result);
		return result;
	}

	/**
	 * 
	 * 功能简述：解析二维码内容
	 * <br>
	 * 功能详细描述：<功能详细描述>
	 * 
	 * @param qrCodePath 二维码图片路径
	 * @return 二维码记录的信息
	 */
	public static String decode(String qrCodePath){
		LOG.debug(">>>>qrCodePath:"+qrCodePath);
		String result=null;
		BufferedImage image=null;

		try {
			image=ImageIO.read(new File(qrCodePath));
			LuminanceSource source = new BufferedImageLuminanceSource(image);  
			Binarizer binarizer = new HybridBinarizer(source);  
			BinaryBitmap binaryBitmap = new BinaryBitmap(binarizer);  
			Map<DecodeHintType, Object> hints = new HashMap<DecodeHintType, Object>();  
			hints.put(DecodeHintType.CHARACTER_SET, "utf-8");  
			Result re = new MultiFormatReader().decode(binaryBitmap, hints);
			result=re.getText();
		} catch (NotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			LOG.error(e.getMessage());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			LOG.error(e.getMessage());
		}
		LOG.debug(">>>>result:"+result);
		return result;
	}

}

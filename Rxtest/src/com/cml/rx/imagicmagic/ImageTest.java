package com.cml.rx.imagicmagic;

import java.io.IOException;

import org.im4java.core.ConvertCmd;
import org.im4java.core.IM4JavaException;
import org.im4java.core.IMOperation;

public class ImageTest {
	public static void main(String[] args) throws IOException, InterruptedException, IM4JavaException {
		//
		// String imPath = "E:\\Program Files\\ImageMagick-6.9.3-Q16";
		// ConvertCmd cc = new ConvertCmd();
		// cc.setSearchPath(imPath);

		System.out.println("===============");
		// create command
		ConvertCmd cmd = new ConvertCmd();

		// create the operation, add images and operators/options
		IMOperation op = new IMOperation();
		op.addImage("/imagemagic/*.jpg");
		op.resize(800, 600);
		// op.addImage("myimage_small.jpg");

		// execute the operation
		cmd.run(op);
	}
}

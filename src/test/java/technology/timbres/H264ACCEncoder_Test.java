package technology.timbres;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;



/**
 * Checks that the contract of the IH264ACCEncoder is implemented correctly. Does not validate the content of the output stream, this is validated manually.
 */
@Ignore // remove this to validate acceptance criteria
public class H264ACCEncoder_Test {



	@Test
	public void test() throws FileNotFoundException, IOException {

		// prepare test data
		InputStream wavStream = getClass().getResourceAsStream("/moog.wav");
		assertNotNull("Did not find WAV test file", wavStream);

		// run the typical scenario
		H264ACCEncoder encoder = new H264ACCEncoder();
		encoder.setWavInputStream(wavStream);
		encoder.setFrameRate(29.97);
		encoder.setResolution(1280, 720);
		encoder.setFrameGenerator(new SimpleFrameGenerator());
		InputStream mp4Stream = encoder.encode();

		// store output to a file
		assertNotNull("MP4 output is null", mp4Stream);
		File mp4Output = new File("moog.mp4");
		IOUtils.copy(mp4Stream, new FileOutputStream(mp4Output));
		assertTrue(mp4Output.exists());
		assertTrue(mp4Output.length() > 0);
	}

	private class SimpleFrameGenerator implements FrameGenerator {



		public BufferedImage createFrame(long framePositionInAudioSamples, long totalLengthInAudioSamples, int width, int height) {
			BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
			Graphics g = image.getGraphics();
			g.setColor(Color.GREEN);
			int x = (int) (framePositionInAudioSamples / (double) (totalLengthInAudioSamples - 1) * width);
			g.drawLine(x, 0, x, height - 1);
			return image;
		}

	}

}

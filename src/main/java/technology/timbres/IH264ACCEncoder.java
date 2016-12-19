package technology.timbres;

import java.io.InputStream;



/**
 * Encoder for WAV content into H264 video and ACC audio format, output as MP4 data. Instances are not thread safe, parallel processing of multiple movies should not share instances.
 *
 */
public interface IH264ACCEncoder {



	/**
	 * Sets the input stream containing all the bytes of a WAV sound resource. This must be called prior to the start of the encoding. This method assumes that the input stream is correclty formated,
	 * this will not be checked. Invalid WAV content may cause exceptions.
	 */
	public void setWavInputStream(InputStream wavContent);



	/**
	 * Set the resolution of the output movie. This must be called prior to the start of the encoding. Supports 1280x720, 640x640 and 480x480
	 *
	 * @param width
	 *            horizontal resolution of the output movie, measured in pixels
	 * @param height
	 *            vertical resolution of the output movie, measured in pixels
	 */
	public void setResolution(int width, int height);



	/**
	 * Set the resolution of the output movie. This must be called prior to the start of the encoding.
	 *
	 * @param framerate
	 *            measured in frames per second. Supports at least 25 and 29.97.
	 */
	public void setFrameRate(double framerate);



	/**
	 * Set the callback Object that is responsible for generating the visual content of each movie frame.
	 *
	 * @param callback
	 *            non-null implementation of the {@code FrameGenerator} interface.
	 */
	public void setFrameGenerator(FrameGenerator callback);



	public InputStream encode();
}

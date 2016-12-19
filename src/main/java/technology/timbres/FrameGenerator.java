package technology.timbres;

import java.awt.image.BufferedImage;



public interface FrameGenerator {



	/**
	 * Returns a 8-bit ARGB AWT BufferedImage for the current frame.
	 *
	 * @param framePositionInAudioSamples
	 *            the current position of the frame in the movie, measured via the audio samples of the WAV input. For example, positionInAudioSamples=44100 represents the frame after exactly 44100
	 *            audio samples (1second). With a WAV sample frequency of 44100 and a movie frame rate of 25fps this value will for example increment by 1764 with each call.
	 * @param totalLengthInAudioSamples
	 *            the total length of the WAV file measured in audio samples
	 * @param width
	 *            horizontal resolution of the image to be created, measured in pixels
	 * @param height
	 *            vertical resolution of the image to be created, measured in pixels
	 * @return
	 */
	public BufferedImage createFrame(long framePositionInAudioSamples, long totalLengthInAudioSamples, int width, int height);
}

package util;

import javax.swing.JFrame;

import curves.FunctionVariations;

public interface FramesController {

	public void quit();

	public JFrame createFrame();

	public void deleteFrame(JFrame frame);
}
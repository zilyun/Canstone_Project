package com.project.homepage.remote.service;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.net.Socket;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class ReceiveThread extends Thread {

	private Socket socket;
	private JFrame frame;
	int w = 1920, h = 1080;

	@Override
	public void run() {
		super.run();

		System.out.println("Ŭ���̾�Ʈ ���� �Ϸ�! - ����" + socket);

		// Ŭ���̾�Ʈ�� ������

		try {
			BufferedInputStream bin = new BufferedInputStream(socket.getInputStream());

			// ������ �Է½�Ʈ���� ���۽�Ʈ������

			while (true) {

				w = frame.getWidth();
				h = frame.getHeight();
				
				// ���� �̹��� ��������
				Image image = ImageIO.read(ImageIO.createImageInputStream(bin));
				System.out.println(image);
				String imgTargetPath= "C:\\Users\\Admin\\desktop\\test.jpg";  
				// �̹��� ��������
				// Image.SCALE_DEFAULT : �⺻ �̹��� �����ϸ� �˰��� ���
				// Image.SCALE_FAST : �̹��� �ε巯�򺸴� �ӵ� �켱
				// Image.SCALE_REPLICATE : ReplicateScaleFilter Ŭ������ ��üȭ �� �̹��� ũ�� ���� �˰���
				// Image.SCALE_SMOOTH : �ӵ����� �̹��� �ε巯���� �켱
				// Image.SCALE_AREA_AVERAGING : ��� �˰��� ���

				/*
				 * Image resizeImage = image.getScaledInstance(w, h, Image.SCALE_SMOOTH);
				 * 
				 * // �� �̹��� �����ϱ� BufferedImage newImage = new BufferedImage(w, h,
				 * BufferedImage.TYPE_INT_RGB); Graphics g = newImage.getGraphics();
				 * g.drawImage(resizeImage, 0, 0, null); g.dispose(); ImageIO.write(newImage,
				 * "jpg", new File(imgTargetPath));
				 */
				//System.out.println(newImage);
				//frame.getGraphics().drawImage(ImageIO.read(new File(imgTargetPath)), 0, 30, w, h, frame);
				
				frame.getGraphics().drawImage(ImageIO.read(ImageIO.createImageInputStream(bin)), 0, 30, w, h, frame);

				Thread.sleep(10);

				// �̹����� �޾ƿ��� ���ÿ� ȭ�鿡 �׸�
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("���� ����͸� �ý����� �����ϹǷ� �������� ����˴ϴ�.");
			// e.printStackTrace();
		}
	}

	public void setSocket(Socket socket) {
		this.socket = socket;
	}

	public void setJFrame(JFrame frame) {
		this.frame = frame;
	}
}

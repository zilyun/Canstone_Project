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

		System.out.println("클라이언트 연결 완료! - 서버" + socket);

		// 클라이언트와 접속함

		try {
			BufferedInputStream bin = new BufferedInputStream(socket.getInputStream());

			// 소켓의 입력스트림을 버퍼스트림으로

			while (true) {

				w = frame.getWidth();
				h = frame.getHeight();
				
				// 원본 이미지 가져오기
				Image image = ImageIO.read(ImageIO.createImageInputStream(bin));
				System.out.println(image);
				String imgTargetPath= "C:\\Users\\Admin\\desktop\\test.jpg";  
				// 이미지 리사이즈
				// Image.SCALE_DEFAULT : 기본 이미지 스케일링 알고리즘 사용
				// Image.SCALE_FAST : 이미지 부드러움보다 속도 우선
				// Image.SCALE_REPLICATE : ReplicateScaleFilter 클래스로 구체화 된 이미지 크기 조절 알고리즘
				// Image.SCALE_SMOOTH : 속도보다 이미지 부드러움을 우선
				// Image.SCALE_AREA_AVERAGING : 평균 알고리즘 사용

				/*
				 * Image resizeImage = image.getScaledInstance(w, h, Image.SCALE_SMOOTH);
				 * 
				 * // 새 이미지 저장하기 BufferedImage newImage = new BufferedImage(w, h,
				 * BufferedImage.TYPE_INT_RGB); Graphics g = newImage.getGraphics();
				 * g.drawImage(resizeImage, 0, 0, null); g.dispose(); ImageIO.write(newImage,
				 * "jpg", new File(imgTargetPath));
				 */
				//System.out.println(newImage);
				//frame.getGraphics().drawImage(ImageIO.read(new File(imgTargetPath)), 0, 30, w, h, frame);
				
				frame.getGraphics().drawImage(ImageIO.read(ImageIO.createImageInputStream(bin)), 0, 30, w, h, frame);

				Thread.sleep(10);

				// 이미지를 받아오는 동시에 화면에 그림
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("원격 모니터링 시스템을 종료하므로 웹서버가 종료됩니다.");
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

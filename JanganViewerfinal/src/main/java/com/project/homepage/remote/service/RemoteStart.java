package com.project.homepage.remote.service;

import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutionException;

import javax.swing.JFrame;

public class RemoteStart implements WindowListener {

	public JFrame frame = null;
	ServerSocket socket_s = null;
	Socket socket = null;
	ReceiveThread receiveThread = new ReceiveThread();
	SendThread sendThread = new SendThread();
	final int w = 1920, h = 1080;
	int x;
	int y;
	int click = 0;
	String r_str = "";
	final int x_in = Toolkit.getDefaultToolkit().getScreenSize().width / 2 - w / 2,
	y_in = Toolkit.getDefaultToolkit().getScreenSize().height / 2 - h / 2;

	public void remoteStart(String auth_str) throws ExecutionException {
		int auth_int = Integer.parseInt(auth_str);

		// 사용자의 해상도를 받아오고 창생성
		frame = new JFrame("JANGAN VIEWER");
		frame.setBounds(x_in, y_in, w, h);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);

		frame.addWindowListener(this);
		frame.addMouseMotionListener(new MouseMotionListener() {

			@Override
			public void mouseMoved(MouseEvent e) {
				// System.out.println(e.getX() + " and " + e.getY());
				x = e.getX();
				y = e.getY();
				sendThread.setX(x);
				sendThread.setY(y);

			}

			@Override
			public void mouseDragged(MouseEvent e) {

			}
		});

		frame.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// System.out.println("떼다");
				click = 0;
				sendThread.setC_str(click);
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// System.out.println("누르다");
				click = 1;
				sendThread.setC_str(click);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				sendThread.setX(0);
				sendThread.setY(0);
			}

			@Override
			public void mouseEntered(MouseEvent e) {

			}

			@Override
			public void mouseClicked(MouseEvent e) {

			}
		});

		try {

			socket_s = new ServerSocket(auth_int);
			socket = socket_s.accept();

			receiveThread.setSocket(socket);
			receiveThread.setJFrame(frame);
			receiveThread.start();

			sendThread.setSocket(socket);
			sendThread.start();

		} catch (Exception e) {
			e.printStackTrace();
		}

		frame.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {

			}

			@Override
			public void keyReleased(KeyEvent e) {
			
			}

			@Override
			public void keyPressed(KeyEvent e) {
				switch (e.getKeyChar()) {
				case 'a':
					sendThread.setKey(1);
					break;
				case 'b':
					sendThread.setKey(2);
					break;
				case 'c':
					sendThread.setKey(3);
					break;
				case 'd':
					sendThread.setKey(4);
					break;
				case 'e':
					sendThread.setKey(5);
					break;
				case 'f':
					sendThread.setKey(6);
					break;
				case 'g':
					sendThread.setKey(7);
					break;
				case 'h':
					sendThread.setKey(8);
					break;
				case 'i':
					sendThread.setKey(9);
					break;
				case 'j':
					sendThread.setKey(10);
					break;
				case 'k':
					sendThread.setKey(11);
					break;
				case 'l':
					sendThread.setKey(12);
					break;
				case 'm':
					sendThread.setKey(13);
					break;
				case 'n':
					sendThread.setKey(14);
					break;
				case 'o':
					sendThread.setKey(15);
					break;
				case 'p':
					sendThread.setKey(16);
					break;
				case 'q':
					sendThread.setKey(17);
					break;
				case 'r':
					sendThread.setKey(18);
					break;
				case 's':
					sendThread.setKey(19);
					break;
				case 't':
					sendThread.setKey(20);
					break;
				case 'u':
					sendThread.setKey(21);
					break;
				case 'v':
					sendThread.setKey(22);
					break;
				case 'w':
					sendThread.setKey(23);
					break;
				case 'x':
					sendThread.setKey(24);
					break;
				case 'y':
					sendThread.setKey(25);
					break;
				case 'z':
					sendThread.setKey(26);
					break;
				}

				if (e.getKeyCode() == 10) { // enter
					sendThread.setSpKey(27);
				}

				if (e.getKeyCode() == 32) { // space
					sendThread.setSpKey(28);
				}

				if (e.getKeyCode() == 17) { // ctrl
					sendThread.setSpKey(29);
				}

				if (e.getKeyCode() == 18) { // alt
					sendThread.setSpKey(30);
					if (e.getKeyCode() == 17) { // ctrl
						sendThread.setSpKey2(29);
					}
				}

				if (e.getKeyCode() == 16) { // shift
					sendThread.setSpKey(31);
				}

				if (e.getKeyCode() == 8) { // bakcspace
					sendThread.setSpKey(32);
				}

			}

		});

	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowClosing(WindowEvent e) {
		//System.exit(0); // 자원이 해제가 되면서 웹서버 꺼짐.
		System.out.println("원격 모니터링 시스템을 종료하므로 웹서버가 종료됩니다."); // 실행안됨.
		return;

	}

	@Override
	public void windowClosed(WindowEvent e) {
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub

	}

}

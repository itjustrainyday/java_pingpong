package test;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;

class MyFrame extends JFrame {
	int x = 0, y = 0;
	int z = 0;
	int ball_x = 20;
	int ball_y = 20;
	int stick_x = 10;
	int stick_y = 40;
	int y_increment = 1;
	int x_increment = 1;
	class MyPanel extends JPanel {//패널 클래스를 새로 만들어서 여기서 그림을 그린다.
		@Override
		public void paintComponent(Graphics g) {//그래픽스에 해당하는 객체인 g에 그리는 메소드
			// TODO Auto-generated method stub
			//super.paintComponent(g);
			g.setColor(Color.RED);
			g.fillOval(x, y, ball_x, ball_y);
			g.setColor(Color.BLUE);
			g.fillRect(0, z, stick_x, stick_y);
			x+= x_increment; y+= y_increment;
			if(y >= 140) y_increment = -1;
			if(x >= 360) x_increment = -1;
			if(x <= stick_x && y<=z+stick_y && y >= z) {
				x_increment = 1;
			}
			if(y <= 0) y_increment = 1;
			if(x < 0)
			{
				System.out.println("Out!");
				System.exit(-1);
			}
			}
		}	

	MyFrame(){
		setSize(400, 200);
		setTitle("정동한 20191105");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		Timer t = new Timer(1, this);
//		t.start();
		MyPanel p = new MyPanel();
		
		p.requestFocus();
		p.setFocusable(true);
		p.addKeyListener(new KeyListener() {
		
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				int code = e.getKeyCode();
				switch(code) {
				case KeyEvent.VK_UP:
					z -= 8;
					break;
				case KeyEvent.VK_DOWN:
					z += 8;
					break;
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
	
			
		});
		this.add(p);
		setVisible(true);

		while (true) {
			try {
			Thread.sleep(5);
			} catch (InterruptedException e) {
			}
			repaint(); // 새로운 프레임을 그린다.
		}
	}

}

public class test {
	public static void main(String[] args) {
		MyFrame frame1 = new MyFrame();
	}
}

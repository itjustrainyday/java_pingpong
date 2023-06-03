package test;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;
class MyFrame extends JFrame{
	JPanel p;
	JPanel up;
	int z = 20;
	int bar_x = 10;
	int bar_y = 80;
	int ballcount = 0;
	ball[] th = new ball[10];
	class bar extends JLabel{
		bar(){
			JLabel l = new JLabel("I");
			l.setBackground(Color.black);
			l.setBounds(0, z, bar_x, bar_y);
			l.setOpaque(true);
			//l.setFont(new Font("courier",Font.BOLD,50));
			l.requestFocus();
			l.setFocusable(true);
			l.addKeyListener(new KeyListener() {
				@Override
				public void keyTyped(KeyEvent e) {
					// TODO Auto-generated method stub
				}
				@Override
				public void keyPressed(KeyEvent e) {
					// TODO Auto-generated method stub
					int code = e.getKeyCode();
					//System.out.println(code);
					switch(code) {
					case KeyEvent.VK_UP:
						z -= 8;
						l.setBounds(0, z, bar_x, bar_y);
						if(z<0) z+=8;
						break;
					case KeyEvent.VK_DOWN:
						z += 8;
						l.setBounds(0, z, bar_x, bar_y);
						if(z>340) z -= 8;
						break;
					}
				}

				@Override
				public void keyReleased(KeyEvent e) {
					// TODO Auto-generated method stub
					
				}
				
			});
			p.add(l);
		}
	}	
	class ball extends Thread{
		JLabel l;
		int interval; // 매개변수 받아서 담을 interval 선언
		int x = 0, y = 0;
		int y_increment = 1;
		int x_increment = 1; //쓰레드 안에서 좌표가 각 때마다 움직이게 하기 위해 멤버 변수로 선언
		ball(int interval) {
			this.interval = interval;
			ballcount++;
			ImageIcon icon = new ImageIcon("image//ball.png");
			l = new JLabel();
			l.setIcon(icon);
			x =(int)(Math.random()*340);//랜덤변수를 줘서 랜덤으로 움직이게 만듬
			y =(int)(Math.random()*220);
			l.setBounds(x, y, 30, 30);
			//l.setBackground(Color.BLUE);
			l.setOpaque(false);
			p.add(l);
		}
		@Override
		public void run() {//마우스 우클릭 source -> 오버라이드 클릭
			while(true) {//Thread의 기본 템플릿
				try {
					//System.out.println("run");
					x+= x_increment; y+= y_increment;
					if(y >= 400) y_increment = -1;
					if(x >= 550) x_increment = -1;
					if(x <= bar_x && y<=z+bar_y && y >= z) {
						x_increment = 1;
					}
					if(y < 20) y_increment = 1;
					if(x < -40) {
						//System.exit(0);
						ballcount--;
						if(ballcount == 0) {
							System.out.println("----------------------------");
							System.out.println("Game Over!");
							System.exit(0);
						}
						throw new InterruptedException("공이 나감");
					}
					l.setBounds(x, y, 30, 30);
					repaint();
					Thread.sleep(interval);
				} catch(InterruptedException e) {
					break;
				}
			}
			//System.exit(-1);
		}
	}
	class score extends Thread{
		int interval;
		JLabel l;
		int score = 0;
		score(int interval){
			this.interval = interval;
			l = new JLabel();
			l.setFont(new Font("courier",Font.BOLD,20));
			//l.setBounds(300, 0, 50, 50);
			l.setBackground(Color.yellow);
			l.setForeground(Color.GREEN);
			l.setOpaque(true);
			up.add(l);
		}
		@Override
		public void run() {
			while(true) {
				score++;
				l.setText("score : " + (score * 10) +"         "+ "지난 시간 :" + score+ "초");
				try {
					Thread.sleep(interval);
					if(ballcount == 0) {
						System.out.println("Your Score : " +(score*10));
						if((score*10)<500) {
							System.out.println("try again!");
						}
						else if((score*10) <1500) {
							System.out.println("Nice Try!");
						}
						else if((score*10) < 2500) {
							System.out.println("Cool!");
						}
						else if((score*10) < 3500) {
							System.out.println("awesome!");
						}
						else if((score*10)< 5000) {
							System.out.println("Your master!");
						}
						System.out.println("----------------------------");
					}
				}catch(InterruptedException e) {
					break;
				}
			}
		}
	}

	MyFrame() throws InterruptedException{
		setSize(600,500);
		setTitle("pingpong");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		p = new JPanel() {
			Image back = new ImageIcon("image//mario.png").getImage();
			@Override
			protected void paintComponent(Graphics g) {
				// TODO Auto-generated method stub
				g.drawImage(back, 0,0,getWidth(),getHeight(),null);
			}	
		};
		up = new JPanel();
		bar side = new bar();
		score sc = new score(100);
		ball tenis = new ball(5);
		ball tenis1 = new ball(5);
		ball tenis2 = new ball(5);
		ball tenis3 = new ball(5);
		tenis.start(); tenis1.start(); tenis2.start(); tenis3.start();
		sc.start();
		p.setLayout(null);
		up.setBackground(Color.yellow);
		this.add(p);
		this.add(up,BorderLayout.NORTH);
		setVisible(true);
	}
}
public class pingpong {
	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		MyFrame pingpong = new MyFrame();
	}
}

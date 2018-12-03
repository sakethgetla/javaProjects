package evolution;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;



public class Main extends Canvas implements Runnable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	boolean running = false;
	static int WIDTH = 900, HEIGHT = (WIDTH/4)*3 ;
	int  boneWidth = 200 , boneHeight  = 50 ;
	Thread thread ;
	double bone[][][] = new double[3][2][4];
	
	public static void main(String args[]) {
		new Main();
		
	

	}
	public Main(){
		
		for (int i = 0;i<= 2;i++){
			for (int q = 0 ; q<= 3; q++){
				bone[i][0][q] = 0 ;
				bone[i][1][q] = 0 ;
			}	
		}
		

		

		
		String title = "evolution";

		JFrame frame = new JFrame(title);
// /*		
		frame.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		frame.setMaximumSize(new Dimension(WIDTH, HEIGHT));
		frame.setMinimumSize(new Dimension(WIDTH, HEIGHT));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.add(this);
		
		
		start();
	}
	private void start(){
		System.out.println("alw");
		thread = new Thread();
		thread.start();
		running =true;
		run();
		//System.out.println("akwdh");


	}
	private void stop(){
		try{
			thread.join();
			running =false;
			
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
	
	
	public void run() {
		
		int fps = 40 , FPScounter=0 , compRefresh =0;
		double then = System.nanoTime(), amountOfNanos = 1000000000/ fps, 
				now, timeDiff ,timer = System.currentTimeMillis();
		while(running){
			
			now = System.nanoTime();
			timeDiff = now - then  ;
			while (timeDiff > amountOfNanos ){
				//System.out.println("akwdh");
				then += amountOfNanos;
				timeDiff = now - then ;
				FPScounter ++;
				tick();	
			}
			render();
			compRefresh ++;
			if (System.currentTimeMillis() - timer >= 1000 ){
				timer += 1000;
				System.out.printf("%s %s \n",FPScounter, compRefresh );
				//System.out.println("FPS >" +FPScounter + "  computer refresh >" + compRefresh);
				FPScounter =0 ;
				compRefresh =0 ;
			}
			
		}
		stop();
				
	}
	private void render() {
		
		
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null){
			this.createBufferStrategy(3);
			return;
			   
		}
		
		Graphics g = bs.getDrawGraphics();
		
		///////////////////////
		
		graphics(g);
		
		
		
		///////////////////////
		
		g.dispose();
		bs.show();
		
	}
	private void graphics(Graphics g) {
		// TODO Auto-generated method stub
		g.setColor(Color.BLUE);
		
		
		//for(int i =0 ;i <= 2 ; i++){
		
			int[]  p = new int [4], o = new int[4]  ;
			
			

			
			
			p[0] = (int) bone[0][0][0] ;
			o[0] = (int) bone [0][1][0] ;
			
			p[1] = (int) bone[0][0][1] ;
			o[1] = (int) bone [0][1][1] ;
			
			p[2] = (int) bone[0][0][2] ;
			o[2] = (int) bone [0][1][2] ;
			
			p[3] = (int) bone[0][0][3] ;
			o[3] = (int) bone [0][1][3] ;
			
			
			g.drawPolygon( p , o,  4);
				
		//}  
	}
	private void tick() {
		
		
		bone[0][0][0] = 300 ;
		bone[0][1][0] = 300 ;
		
		bone[0][0][1] = bone[0][0][0] + boneWidth ;
		bone[0][1][1] = bone[0][1][0] ;
		

		bone[0][0][3] = bone[0][0][0]  ;
		bone[0][1][3] = bone[0][1][0] + boneHeight;

		bone[0][0][2] = bone[0][0][0] + boneWidth ;
		bone[0][1][2] = bone[0][1][0] + boneHeight ;
		
				
		
	}
	
}

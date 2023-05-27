package Model;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class Fenetre extends JFrame {
	


	private JPanel grille = new Grille();
	private JPanel panStart = new JPanel();
	private JButton start ;
	private JButton restart;
	private boolean jeux =false;
	private Thread t;
	
	private int dimx = ((Grille) grille).getDimx();
	private int dimy = ((Grille) grille).getDimy();


	public Fenetre() {
		this.start = new JButton("start");
		this.panStart.add(start);
		panStart.setBackground(Color.gray);
		this.start.setBackground(panStart.getBackground());

		this.restart=new JButton("restart");			
		this.panStart.add(restart);
		this.restart.setBackground(panStart.getBackground());

		this.getContentPane().add(grille,BorderLayout.CENTER);
		this.getContentPane().add(panStart,BorderLayout.NORTH);

		initFenetre();

		start.addActionListener((ActionEvent e)->action(e));
		restart.addActionListener((ActionEvent e)->actionRestart(e));

		this.go();

	}


	private void initFenetre() {
		this.setTitle("le jeux de la vie\\Game of life ");
		this.setSize(new Dimension(700,500));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}



	public void go() {

		while(jeux) {
			boolean[][] boolTampon = new boolean[dimx][dimy];
			for(int i =0;i<dimx;i++) {
				for(int j = 0;j<dimy;j++) {

					boolTampon[i][j]=gameRule(((Grille) grille).getLesCel()[i][j], i, j);

				}

			}

			for(int i =0;i<dimx;i++) {
				for(int j = 0;j<dimy;j++) {

					((Grille) grille).getLesCel()[i][j].setVie(boolTampon[i][j]);

				}
			}

			((Grille) grille).repaint();

			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

	public void actionRestart(ActionEvent e) {

		for(int i = 0;i<dimx;i++) {
			for(int j = 0;j<dimy;j++) {

				((Grille) grille).getLesCel()[i][j].setClickable(true);
				((Grille) grille).getLesCel()[i][j].setVie(false);

			}
		}
		t.stop();
		((Grille) grille).repaint();
	}

	public void action(ActionEvent e) {
		setToUnclickable();
		this.jeux=true;
		t = new Thread(new PlayAnimation());
		t.start();



	}

	public void setToUnclickable() {

		for(int i = 0;i<dimx;i++) {
			for(int j = 0;j<dimy;j++) {

				((Grille) grille).getLesCel()[i][j].setClickable(false);
			}
		}

	}

	public boolean gameRule(Cellule cel,int i, int j) {
		int x=0,y=0,nbre =0;
		boolean [][] tabBool = new boolean[3][3];

		if(i>0&&j>0&&i<dimx-1&&j<dimy-1) {


			tabBool[0][0] =  ( (Grille) grille).getLesCel()[i-1][j-1].isVie();

			tabBool[1][0] =  ((Grille) grille).getLesCel()[i][j-1].isVie();

			tabBool[0][1] =  ((Grille) grille).getLesCel()[i-1][j].isVie();
			tabBool[2][0] =  ((Grille) grille).getLesCel()[i+1][j-1].isVie();

			tabBool[2][1] =  ((Grille) grille).getLesCel()[i+1][j].isVie();


			tabBool[0][2] =  ((Grille) grille).getLesCel()[i-1][j+1].isVie();

			tabBool[1][2] =  ((Grille) grille).getLesCel()[i][j+1].isVie();

			tabBool[2][2] =  ((Grille) grille).getLesCel()[i+1][j+1].isVie();

		}else if(i==0&&j>0&&i<dimx-1&&j<dimy-1) {

			tabBool[0][0] = false;
			tabBool[1][0] =    ((Grille) grille).getLesCel()[i][j-1].isVie() ;
			tabBool[0][1] =false;
			tabBool[2][0] =    ((Grille) grille).getLesCel()[i+1][j-1].isVie();
			tabBool[2][1] =  ((Grille) grille).getLesCel()[i+1][j].isVie();
			tabBool[0][2] = false;
			tabBool[1][2] =  ((Grille) grille).getLesCel()[i][j+1].isVie();
			tabBool[2][2] =  ((Grille) grille).getLesCel()[i+1][j+1].isVie();

		}else if(i>0&&j==0&&i<dimx-1&&j<dimy-1) {
			tabBool[0][0] = false;
			tabBool[1][0] = false ;
			tabBool[0][1] =((Grille) grille).getLesCel()[i-1][j].isVie();
			tabBool[2][0] =   false;
			tabBool[2][1] =  ((Grille) grille).getLesCel()[i+1][j].isVie();
			tabBool[0][2] = ((Grille) grille).getLesCel()[i-1][j+1].isVie();
			tabBool[1][2] =  ((Grille) grille).getLesCel()[i][j+1].isVie();
			tabBool[2][2] =  ((Grille) grille).getLesCel()[i+1][j+1].isVie();

		}else if(i==0&&i<dimx-1&&j==0&&j<dimy-1) {
			tabBool[0][0] = false;
			tabBool[1][0] = false ;
			tabBool[0][1] =false;
			tabBool[2][0] =   false;
			tabBool[2][1] =  ((Grille) grille).getLesCel()[i+1][j].isVie();
			tabBool[0][2] = false;
			tabBool[1][2] =  ((Grille) grille).getLesCel()[i][j+1].isVie();
			tabBool[2][2] =  ((Grille) grille).getLesCel()[i+1][j+1].isVie();


		}else if(i==0&&i<dimx-1&&j>0&&j==dimy-1) {
			tabBool[0][0] = false;
			tabBool[1][0] = ((Grille) grille).getLesCel()[i][j-1].isVie() ;
			tabBool[0][1] =false;
			tabBool[2][0] = ((Grille) grille).getLesCel()[i+1][j-1].isVie();
			tabBool[2][1] =  ((Grille) grille).getLesCel()[i+1][j].isVie();
			tabBool[0][2] = false;
			tabBool[1][2] = false;
			tabBool[2][2] =  false;


		}else if(i>0&&i==dimx-1&&j>0&&j==dimy-1) {

			tabBool[0][0] = ((Grille) grille).getLesCel()[i-1][j-1].isVie();
			tabBool[1][0] = ((Grille) grille).getLesCel()[i][j-1].isVie() ;
			tabBool[0][1] =((Grille) grille).getLesCel()[i-1][j].isVie();
			tabBool[2][0] = false;
			tabBool[2][1] = false;
			tabBool[0][2] = false;
			tabBool[1][2] = false;
			tabBool[2][2] =  false;

		}else if(i>0&&i==dimx-1&&j>0&&j<dimy-1 ) {

			tabBool[0][0] = ((Grille) grille).getLesCel()[i-1][j-1].isVie();
			tabBool[1][0] = ((Grille) grille).getLesCel()[i][j-1].isVie() ;
			tabBool[0][1] =((Grille) grille).getLesCel()[i-1][j].isVie();
			tabBool[2][0] =    false;
			tabBool[2][1] =  false;
			tabBool[0][2] = ((Grille) grille).getLesCel()[i-1][j+1].isVie();
			tabBool[1][2] =  ((Grille) grille).getLesCel()[i][j+1].isVie();
			tabBool[2][2] = false;
		}else if(i>0&&i<dimx-1&&j>0&&j==dimy-1) {

			tabBool[0][0] = ((Grille) grille).getLesCel()[i-1][j-1].isVie();
			tabBool[1][0] = ((Grille) grille).getLesCel()[i][j-1].isVie() ;
			tabBool[0][1] =((Grille) grille).getLesCel()[i-1][j].isVie();
			tabBool[2][0] =((Grille) grille).getLesCel()[i+1][j-1].isVie();
			tabBool[2][1] =  ((Grille) grille).getLesCel()[i+1][j].isVie();
			tabBool[0][2] = false;
			tabBool[1][2] =  false;
			tabBool[2][2] = false;

		}else if(i>0&&i==dimx-1&&j==0&&j<dimy-1) {
			tabBool[0][0] = false;
			tabBool[1][0] = false ;
			tabBool[0][1] =((Grille) grille).getLesCel()[i-1][j].isVie();
			tabBool[2][0] =    false;
			tabBool[2][1] =  false;
			tabBool[0][2] = ((Grille) grille).getLesCel()[i-1][j+1].isVie();
			tabBool[1][2] =  ((Grille) grille).getLesCel()[i][j+1].isVie();
			tabBool[2][2] = false;
		}


		tabBool[1][1]=false;

		for(int k = 0;k<3;k++) {

			for(int l = 0;l<3;l++) {

				if(tabBool[k][l])
					nbre = nbre+1;
			}
		}

		if(nbre==2&&cel.isVie() ||nbre==3&&cel.isVie()|| nbre==3&&(!(cel.isVie())))
			return true;
		else	
			return false;
	}


	class PlayAnimation implements Runnable{
		public void run() {
			go();
		}
	}
}

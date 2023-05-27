package Model;

import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

public class Grille extends JPanel {
	
	private Cellule[][] lesCel;
	private int dimx =200, dimy = 200;
	

	public Grille() {
		
		lesCel = new Cellule[this.getDimx()][this.getDimy()];
		
		this.setLayout(new GridLayout(dimx,dimy,1,1));
		initGrille();
	}
	
	public void initGrille(){
		
		for(int i =0;i<dimx;i++){
			for(int j=0;j <dimy;j++){
				
				lesCel[i][j] =  new Cellule();
				this.add(lesCel[i][j]);
				lesCel[i][j].addMouseListener(lesCel[i][j]);
				
				
			}
		}
		
	}
	
	
	
	public Cellule[][] getLesCel() {
		return lesCel;
	}

	public void setLesCel(Cellule[][] lesCel) {
		this.lesCel = lesCel;
	}
	public int getDimx() {
		return dimx;
	}

	public void setDimx(int dimx) {
		this.dimx = dimx;
	}

	public int getDimy() {
		return dimy;
	}

	public void setDimy(int dimy) {
		this.dimy = dimy;
	}


}

package Model;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

public class Cellule extends JPanel implements MouseListener {
	
	
	
	private boolean vie= false, clickable = true ;
	private Dimension dim;
	
	public Cellule() {
		this.dim = new Dimension(20,20);
		this.setPreferredSize(dim);		
	}

	
	
	public void paintComponent(Graphics g) {
		
		if(vie)
			g.setColor(Color.black);
		else
			g.setColor(Color.pink);
		
		g.fillRect(0,0,this.getWidth(), this.getHeight());
		
	}
	
	
	public static void main(String[] args) {
		Cellule cel = new Cellule();
	

	}
	
	
	public boolean isVie() {
		return vie;
	}

	public void setVie(boolean vie) {
		this.vie = vie;
	}

	public boolean isClickable() {
		return clickable;
	}

	public void setClickable(boolean clickable) {
		this.clickable = clickable;
	}

	public Dimension getDim() {
		return dim;
	}

	public void setDim(Dimension dim) {
		this.dim = dim;
	}



	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
		if(clickable) {
		this.vie = !vie;
		this.repaint();
		}
		
	}



	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	
}

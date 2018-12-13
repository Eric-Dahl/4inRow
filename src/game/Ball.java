package game;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.scene.shape.Ellipse;
import javafx.scene.paint.Color;
import javafx.util.Duration;


public class Ball {
	
	Ellipse visiball;
	public int row;
	public final int column;
	public Color color;//0-blue, 1-red


	public Ball(byte column, boolean color){// boolean color
		this.column = column;
		if(color){ this.color = Color.BLUE; }
		else{ this.color = Color.RED; }
		MakeBall();
		DropBall();
		if (Is4inRow()){
			Main.gui.paintAtWin(this.color);
		}
	}
	
	
	public void MakeBall(){
		visiball = new Ellipse(Const.XPosToPixels(column), Const.DROPHEIGHT, Const.BALL_RADIUS, Const.BALL_RADIUS);
		visiball.setVisible(true);
			visiball.setFill(color);
	}
	

	private void DropBall(){
		row = Main.ballcontainer.get(column).ballvec.size();
		System.out.println("size"+ Main.ballcontainer.get(column).ballvec.size());
		
		KeyValue keyval = new KeyValue(visiball.centerYProperty(), Const.YPosToPixels(row));
		KeyFrame keyframe = new KeyFrame(Duration.millis((10 - (double)row) * 100), keyval);
														//animation går snabbare ju kortare bollarna färdas
		Main.Animation(keyframe);
	}
	
	int ballsinrow = 1;
	Color evaluationColor;
	
	private boolean Is4inRow(){
		
		if (isVerticalWin()){
			return true;
		}
		ballsinrow = 1;
		if (isHorisontalWin()){
			return true;
		}
		ballsinrow = 1;
		if (isDiagonal1Win()){
			return true;
		}
		ballsinrow = 1;
		if (isDiagonal2Win()){
			return true;
		}
		return false;
	}
	
	
	private boolean isVerticalWin(){
		
		for(int i = -3; i <= 3; i++){
			
			if(i == 0){ continue; }
			try{
				evaluationColor = Main.ballcontainer.get(column).ballvec.get(row + i).color;
			}
			catch(NullPointerException  | ArrayIndexOutOfBoundsException e){
				evaluationColor = null;
				ballsinrow = 1;
				continue;
			}
			
			if(evaluationColor == color){
				ballsinrow++;
				if(ballsinrow >= 4){
					return true;
				}
			}
			else { ballsinrow = 1; }
		}
		return false;
	}
	
	private boolean isHorisontalWin(){
		
		for(int i = -3; i <= 3; i++){
			
			if(i == 0){ continue; }
			//if(column + i < 0 || column + i > 9){ continue; } // for preventing ArrayIndexOutOfBoundsException
			try{
				evaluationColor = Main.ballcontainer.get(column + i).ballvec.get(row).color;
			}
			catch(NullPointerException | ArrayIndexOutOfBoundsException e){
				evaluationColor = null;
				ballsinrow = 1;
				continue;
			}
			
			if(evaluationColor == color){
				ballsinrow++;
				if(ballsinrow >= 4){
					return true;
				}
			}
			else { ballsinrow = 1; }
		}
		return false;
	}
	
	private boolean isDiagonal1Win(){	//foreslash diagonal ( / )
		
		for(int i = -3; i <= 3; i++){
			
			if(i == 0){ continue; }
			try{
				evaluationColor = Main.ballcontainer.get(column + i).ballvec.get(row + i).color;
			}
			catch(NullPointerException  | ArrayIndexOutOfBoundsException e){
				evaluationColor = null;
				ballsinrow = 1;
				continue;
			}
			
			if(evaluationColor == color){
				ballsinrow++;
				if(ballsinrow >= 4){
					return true;
				}
			}
			else { ballsinrow = 1; }
		}
		return false;
	}
	
	private boolean isDiagonal2Win(){	//backslash diagonal ( \ )
		
		for(int i = -3; i <= 3; i++){
			
			if(i == 0){ continue; }
			try{
				evaluationColor = Main.ballcontainer.get(column + i).ballvec.get(row - i).color;
			}
			catch(NullPointerException  | ArrayIndexOutOfBoundsException e){
				evaluationColor = null;
				ballsinrow = 1;
				continue;
			}
			
			if(evaluationColor == color){
				ballsinrow++;
				if(ballsinrow >= 4){
					return true;
				}
			}
			else { ballsinrow = 1; }
		}
		return false;
	}
	

}
	/*
	private void DropBall(){
		//size + 1 för att "räkna bort" [0]
		//row = Main.ball[column].ballvec.size() + 1;
		for(int currentrow = 13; 1 <= currentrow; currentrow--){
			visiball.setCenterY(visiball.getCenterY() - Const.DROP_1_STEP);
			repaint(30000L, (int)(Const.PosToPixels(column) - BALL_RADIUS),
				   (int)(Const.DROPHEIGHT - BALL_RADIUS), (int)BALL_RADIUS*2, Const.MAX_FALLHEIGHT);
		}
	}
	*/


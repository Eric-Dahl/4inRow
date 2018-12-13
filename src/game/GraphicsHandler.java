package game;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.effect.Reflection;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;


public class GraphicsHandler {
	
	public Group root = new Group();
	private static final double SPACE_BETWEEN_PANEBUTTONS = 5;
	private static boolean currentColor = true;
	public FlowPane fpane = new FlowPane(Orientation.HORIZONTAL, SPACE_BETWEEN_PANEBUTTONS, 0);

	
	public GraphicsHandler() {	
		root.setTranslateY(Main.height - (Main.height / 2 + Const.SCENEHEIGHT / 2));
        root.setTranslateX(Main.width - (Main.width / 2 + Const.SCENEWIDTH / 2));

	    fpane.setTranslateY(50);
	    
		root.getChildren().add(getButtonPane());
        root.getChildren().addAll(getGameField());
	}
	
	
	public FlowPane getButtonPane(){
		fpane.setPrefWidth(800); 
		fpane.setCenterShape(true);
		fpane.setAlignment(Pos.TOP_CENTER);
		
		
		for (byte i = 0; i < 10; i++) {
			fpane.getChildren().add(btnActionInit(i));
		}
		return fpane;
	}
	private Button btnActionInit(final byte I){
		
		final Button btn = new Button("" + (I + 1));
		btn.setPrefWidth(45);
		btn.setFont(Font.font(java.awt.Font.SANS_SERIF, FontWeight.BLACK, 11));
		btn.setVisible(true);
		btn.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent event) {
				Main.ballcontainer.get(I).ballvec.add(new Ball(I, getColor()));
				root.getChildren().add(Main.ballcontainer.get(I).ballvec.lastElement().visiball);
				
				if(Main.ballcontainer.get(I).ballvec.size() > 9){
					btn.setDisable(true);
				}
			}
		});
		return btn;
	}
	private Line[] getGameField(){
		
		Line[] lines = new Line[12];
		
		int i = 0;
		for(int xval = 150; i < 11; xval += Const.X_BETWEEN_BALLS){
			lines[i] = new Line(xval, Const.yTOP, xval, Const.yBOTTOM);
			i++;
		}
		
		lines[11] = new Line(124, Const.yBOTTOM, 676, Const.yBOTTOM);
		lines[11].setStrokeWidth(10);
		
		return lines;
	}
	
	
	public void paintAtWin(Color color){/** @calls getWinText(Color color), getResetButton() 	
											does the new painting when someone has won*/
		
		root.getChildren().addAll(getWinText(color));
		root.getChildren().add(getResetButton());
		
	}
	private static Text[] getWinText(Color color){/** @returns the fancy text that displays which color has won*/
		
		String colorstr;
		double halftextwidth;
		final double FONTSIZE = 120;
		final double DIST_TO_GAMEFIELD = 20;
		
		if(color == Color.BLUE) 	{ colorstr = "Blue Win!"; }
		else { colorstr = "Red  Win!"; }
		Text[] wintext = new Text[2];
		
		for(int i = 0; i <= 1; i++){
			
			wintext[i] = new Text(colorstr);		
			
			wintext[i].setRotate(270 + i * -180);
			wintext[i].setFill(color);
			wintext[i].setFont(Font.font(java.awt.Font.DIALOG, FontWeight.EXTRA_BOLD, FONTSIZE));
			Reflection r = new Reflection(130, 1, 0.5, 0.0);
			wintext[i].setEffect(r);
		}
		halftextwidth = wintext[0].getLayoutBounds().getWidth() / 2;
		wintext[0].setTranslateY(halftextwidth + 50);// +50 för att pane startar där
		wintext[1].setTranslateY(halftextwidth + 50);// +50 för att pane startar där

		wintext[0].setTranslateX(Const.SCENEWIDTH / 2 - Const.GAMEFIELDWIDTH / 2 - halftextwidth - FONTSIZE / 2 - DIST_TO_GAMEFIELD);
		wintext[1].setTranslateX(wintext[0].getTranslateX() + Const.GAMEFIELDWIDTH + FONTSIZE + DIST_TO_GAMEFIELD * 2);
		
		return wintext;
	}
	private Button getResetButton(){/** @returns the button that on clicking resets the gamefield (new game)*/
		
		Button resetbtn = new Button("Play again!");
		
		resetbtn.setFont(Font.font(java.awt.Font.SANS_SERIF, FontWeight.LIGHT, 20));
		resetbtn.setPrefWidth(200);
		resetbtn.setLayoutX(Const.SCENEWIDTH / 2 - resetbtn.getPrefWidth() / 2);		// set button to middle of screen (x-coordinates)
		resetbtn.setLayoutY(Const.yBOTTOM + 10);

		
		resetbtn.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent event) {
				Main.ResetProgram();
			}
			
		});
		
		return resetbtn;
	}
	
	
	public static boolean getColor(){ // The color-generator that makes every second ball blue/red
		if (currentColor){
			currentColor = false;
			return true;
		}	//else
			currentColor = true;
			return false;
	}
	
	
	public void removeVisiBalls(){	// removes the balls (and the win text and 'play again'-button) from the group 'root'
		root.getChildren().remove(13, root.getChildren().size());
	}
	
}



















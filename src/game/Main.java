package game;

import java.util.Vector;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application {
	
	public static Vector<Ballvec> ballcontainer = new Vector<Ballvec>(9);
	public static GraphicsHandler gui;

	public static double width = 800;
	public static double height = 638;
	

	@Override
	public void start(Stage primaryStage) {
		primaryStage.setWidth(width);
		primaryStage.setHeight(height);
		primaryStage.setResizable(true);
		primaryStage.setMinWidth(800);
		primaryStage.setMinHeight(638);
		primaryStage.setTitle("4inRow");
				
		for (int i = 0; i <= 9; i++){
			ballcontainer.addElement(new Ballvec());
		}

		gui = new GraphicsHandler();
        Scene scene = new Scene(gui.root, Const.SCENEWIDTH, Const.SCENEHEIGHT, Color.WHITE);
        primaryStage.widthProperty().addListener(new ChangeListener<Number>() {
		    @Override 
		    public void changed(ObservableValue<? extends Number> observableValue, Number oldStageWidth, Number newStageWidth) {
		        width = (double)newStageWidth;
		        gui.root.setTranslateX(width - (width / 2 + Const.SCENEWIDTH / 2));
		    }
		});
        primaryStage.heightProperty().addListener(new ChangeListener<Number>() {
		    @Override 
		    public void changed(ObservableValue<? extends Number> observableValue, Number oldStageHeight, Number newStageHeight) {
		        height = (double)newStageHeight;
		        gui.root.setTranslateY(height - (height / 2 + Const.SCENEHEIGHT / 2));
		    }
		});
		
        primaryStage.setScene(scene);
        primaryStage.show();
	}
	
	
	public static void Animation(KeyFrame keyframe){
		Timeline Timeln = new Timeline();

        Timeln.getKeyFrames().add(keyframe);
        Timeln.play();
	}
	
	
	public static void ResetProgram(){
		
		gui.removeVisiBalls();
		ClearBallContainer();
		
	}
	
	
	private static void ClearBallContainer(){
		
		for(int i = 0; i <= 9; i++){
			
			try{
				ballcontainer.get(i).ballvec.clear();
			}catch(UnsupportedOperationException e){
				e.printStackTrace();
			}
		}
	}
	
	
	public static void main(String[] args) {
		launch(args);
	}

}

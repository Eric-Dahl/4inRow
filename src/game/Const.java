package game;

public enum Const {;
	
	// width and height of the group 'root'
	static double SCENEWIDTH = 800;
	static double SCENEHEIGHT = 600;
	static double GAMEFIELDWIDTH = 500;

	
	static double xLEFT = 175;		//	var bollens mitt i kolumn 0 ligger i x-led
	static double yBOTTOM = 520;	//	den tjocka bottenlinjens mitt i y-led
	static double yTOP = 75;		//	h�gst upp p� "kulbeh�llaren" 
	static double DROPHEIGHT = 95;	//	varifr�n i y-led kulorna ska sl�ppas (f�rst visas p� sk�rmen)
	
	static double BALL_RADIUS = 20;			//The ball radius
	static double X_BETWEEN_BALLS = 50;		//distance between the horisontal balls' centres
	static double Y_BETWEEN_BALLS = BALL_RADIUS*2 + 2; // = 40 		distance between the vertical balls' centres
	
	public static double XPosToPixels(int column){
		return column * X_BETWEEN_BALLS + xLEFT;
		//column * kolumnavst�nd + xLEFT
	}
	
	
	public static double YPosToPixels(int row){
		return (10 - row) * Y_BETWEEN_BALLS + yTOP;
		// (jag m�ter rader ner->upp, datorn upp->ner) * boll o mellanrum + yTOP
	}
	
}

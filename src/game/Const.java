package game;

public enum Const {;
	
	// width and height of the group 'root'
	static double SCENEWIDTH = 800;
	static double SCENEHEIGHT = 600;
	static double GAMEFIELDWIDTH = 500;

	
	static double xLEFT = 175;		//	var bollens mitt i kolumn 0 ligger i x-led
	static double yBOTTOM = 520;	//	den tjocka bottenlinjens mitt i y-led
	static double yTOP = 75;		//	högst upp på "kulbehållaren" 
	static double DROPHEIGHT = 95;	//	varifrån i y-led kulorna ska släppas (först visas på skärmen)
	
	static double BALL_RADIUS = 20;			//The ball radius
	static double X_BETWEEN_BALLS = 50;		//distance between the horisontal balls' centres
	static double Y_BETWEEN_BALLS = BALL_RADIUS*2 + 2; // = 40 		distance between the vertical balls' centres
	
	public static double XPosToPixels(int column){
		return column * X_BETWEEN_BALLS + xLEFT;
		//column * kolumnavstånd + xLEFT
	}
	
	
	public static double YPosToPixels(int row){
		return (10 - row) * Y_BETWEEN_BALLS + yTOP;
		// (jag mäter rader ner->upp, datorn upp->ner) * boll o mellanrum + yTOP
	}
	
}

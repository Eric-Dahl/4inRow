package game;

	import java.util.Vector;

	public class Ballvec {
		
		public Vector<Ball>ballvec;
		

		public Ballvec(){
			ballvec = new Vector<Ball>();
			//enables a vector array, which I made in the Main class
		}
		
		public int getSize(){
			return ballvec.size();
		}
	}

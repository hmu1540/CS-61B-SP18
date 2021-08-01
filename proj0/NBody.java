/**
 *  NBody is a class that will actually run your simulation. 
 *  This class will have NO constructor. 
 *  The goal of this class is to simulate a universe specified in one of the data files.
 */

public class NBody {
	public static double readRadius (String fileName) {
		In inFile = new In(fileName);
		
		int N = inFile.readInt();
		double radius = inFile.readDouble();
		return radius;
	}
	public static Planet[] readPlanets (String fileName) {

		/**
		 *  The input format is a text file that contains 
		 *  the information for a particular universe (in SI units).
		 *  The first value is an integer N which represents the number of planets. 
		 *  The second value is a real number R which represents the radius of the universe
		 *  used to determine the scaling of the drawing window
		 */

		
		In inFile = new In(fileName);
		
		int N = inFile.readInt();
		double radius = inFile.readDouble();

		// read 1 row - N; read 2 row - radius of u; read 3 row- planet 1; read 4 row - planet 2....read 7 row
		// -planet 5; planet arrays
		Planet[] allPlanets = new Planet[N];
		for(int i = 0; i < N; i += 1) {

			/**
		     *  There are N rows, and each row contains 6 values.
			 *  The first two values are the x- and y-coordinates of the initial position; 
			 *  the next pair of values are the x- and y-components of the initial velocity; 
			 *  the fifth value is the mass; 
			 *  the last value is a String that is the name of an image file used to display the planets. 
			 *  Image files can be found in the images directory. 
			 *  The file above contains data for our own solar system (up to Mars).
			 */
			double xxPos = inFile.readDouble();
			double yyPos = inFile.readDouble();
			double xxVel = inFile.readDouble();
			double yyVel = inFile.readDouble();
			double mass = inFile.readDouble();	
			String imgFileName = inFile.readString();
			allPlanets[i] = new Planet(xxPos, yyPos, xxVel, yyVel, mass, imgFileName);
		}
		return allPlanets;
	}
	public static void main(String[] args) {
		double T = Double.valueOf(args[0]); // the arguments come in as Strings. 
		double dt = Double.valueOf(args[1]);
		String filename = args[2];

		/** 
		 *  After your main method has read everything from the files, it’s time to get drawing. 
		 *  First, set the scale so that it matches the radius of the universe. 
		 *  Then draw the image starfield.jpg as the background. 
		 */
		double radius = readRadius(filename);
		StdDraw.setScale(-radius, radius); // too large number?
		StdDraw.clear();
		StdDraw.picture(0, 0, "images/starfield.jpg");
		StdDraw.show();
		StdDraw.pause(1000);
		Planet[] allPlanets = readPlanets(filename);
		for (Planet p : allPlanets) {
			p.draw();
			StdDraw.show();
		}
		StdDraw.enableDoubleBuffering();
		/** When double buffering is enabled by calling enableDoubleBuffering(), 
		 *  all drawing takes place on the offscreen canvas. The offscreen canvas is not displayed. 
		 *  Only when you call show() does your drawing get copied from the offscreen canvas to the onscreen canvas, 
		 *  where it is displayed in the standard drawing window. You can think of double buffering as collecting all of the lines,
		 *  points, shapes, and text that you tell it to draw, and then drawing them all simultaneously, upon request.
		 */
		double time = 0;
		while (time < T) {
			double[] xForces = new double[N];
			double[] yForces = new double[N];
			int i = 0;
			for (Planet p : allPlanets) {
				xForces[i] = p.calcNetForceExertedByX(allPlanets);
				yForces[i] = p.calcNetForceExertedByY(allPlanets);
				i += 1;
			}
			i = 0;
			for (Planet p : allPlanets) {
				p.update(dt, xForces[i], yForces[i]);
				i += 1;
			}
			StdDraw.picture(0, 0, "images/starfield.jpg");
			for (Planet p : allPlanets) {
				p.draw();
			}
			StdDraw.show();
			StdDraw.pause(10);
			
			time += dt;	   
		} 
		StdOut.printf("%d\n", allPlanets.length);
        StdOut.printf("%.2e\n", radius);
		for (int i = 0; i < allPlanets.length; i++) {
    		StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
            allPlanets[i].xxPos, allPlanets[i].yyPos, allPlanets[i].xxVel,
            allPlanets[i].yyVel, allPlanets[i].mass, allPlanets[i].imgFileName);   
		}
	}
}
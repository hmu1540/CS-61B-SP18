/** 
 *  Simulating the motion of N objects in a plane, 
 *  accounting for the gravitational forces mutually affecting each object 
 *  as demonstrated by Sir Isaac Newton’s Law of Universal Gravitation.
 */
public class Planet {
	public double xxPos;
	// The reason we call them by double letters, e.g. xxPos rather than xPos is to reduce the chance of typos. In past semesters, students have accidentally pressed x when they meant y, and this has caused significant debugging hassle. 
	public double yyPos;
	public double xxVel;
	public double yyVel;
	public double mass;
	public String imgFileName; 
	// The name of the file that corresponds to the image that depicts the body
	public static double gConstant = 6.67e-11;
	// Hint: It is good practice to declare any constants as a ‘static final’ variable in your class, and to use that variable anytime you wish to use the constant.
	// ??
	public Planet (double xP, double yP, double xV,
              double yV, double m, String img) {
		xxPos = xP;
		xxVel = xV;
		yyPos = yP;
		yyVel = yV;
		mass = m;
		imgFileName = img;

	}
	public Planet (Planet b) {
		xxPos = b.xxPos;
		xxVel = b.xxVel;
		yyPos = b.yyVel;
		yyVel = b.yyVel;
		mass = b.mass;
		imgFileName = b.imgFileName;
	}
	// Your Planet class should NOT have a main method, because we’ll never run the Body class directly (i.e. we will never run java Body). Also, all methods should be non-static.
	public double calcDistance (Planet b) {
		double dx = b.xxPos - this.xxPos;
		double dy = b.yyPos - this.yyPos;
		double rSquare = dx * dx + dy * dy; 
		// Hint: In Java, there is no built in operator that does squaring or exponentiation. We recommend simply multiplying a symbol by itself instead of using Math.pow, 
		return Math.sqrt(rSquare);
	}
	public double calcForceExertedBy (Planet b) {
		double rSquare = this.calcDistance(b) * this.calcDistance(b);
		return gConstant * this.mass * b.mass / rSquare;
	}
	public double calcForceExertedByX (Planet b) {
		double dx = b.xxPos - this.xxPos;
		return calcForceExertedBy (b) * dx / this.calcDistance(b);
	}
	public double calcForceExertedByY (Planet b) {
		double dy = b.yyPos - this.yyPos;
		return calcForceExertedBy (b) * dy / this.calcDistance(b);
	}
	public double calcNetForceExertedByX (Planet[] allPlanets) {
		/**
		 * Take in an array of Planets and calculate the net X force 
		 * exerted by all planets in that array upon the current Planet
		 */
		double netForceX = 0;
		for (Planet p : allPlanets) {
			if (!this.equals(p)) {
				netForceX += this.calcForceExertedByX(p);
			}
		}
		return netForceX;
	}
	public double calcNetForceExertedByY (Planet[] allPlanets) {
		/**
		 * Take in an array of Planets and calculate the net X force 
		 * exerted by all planets in that array upon the current Planet
		 */
		double netForceY = 0;
		for (Planet p : allPlanets) {
			if (!this.equals(p)) {
				netForceY += this.calcForceExertedByY(p);
			}
		}
		return netForceY;
	}
}
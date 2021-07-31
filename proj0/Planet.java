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
}
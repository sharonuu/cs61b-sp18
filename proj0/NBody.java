/**
 * NBody is a class that will actually run your simulation.
 */
public class NBody{

	public static double readRadius(String filename){
		/**
		 * Given a file name, it should return a double corresponding to the radius of the universe in that file
		 * @param filenam  given file
		 * @return radius the secondline in file
		 */
		In in = new In(filename);
		in.readInt();
		double radius = in.readDouble();
		return radius;
	}

	public static Planet[] readPlanets(String filename){
		/**
		 * Given a file name, it should return an array of Planets corresponding to the planets in the file
		 * get data from the third line,put data in an array
		 * @return Body    an array contains message from planet
		 */
		
		In in = new In(filename);
		int num = in.readInt();
		in.readDouble();
		Planet[] Body = new Planet[num];
		
		int i = 0;
		while( i < num ){
			double xP = in.readDouble();
			double yP = in.readDouble();
			double xV = in.readDouble();
			double yV = in.readDouble();
			double m = in.readDouble();
			String img = in.readString();
			Body[i++] = new Planet( xP , yP , xV , yV , m , img);
		}

		return Body;
	}

	public static void main(String[] args) {
		
		/**
		 * Store the 0th and 1st command line arguments as doubles named T and dt
		 */
		double T = Double.parseDouble( args[0] );
		double dt = Double.parseDouble( args[1] );
		
		/**
		 * Store the 2nd command line argument as a String named filename
		 */
		String filename = args[2];

		/**
		 * Read in the planets and the universe radius from the file described by filename using your methods from earlier in this assignment.
		 */
		double uniradius = NBody.readRadius( filename );
		Planet[] Body = NBody.readPlanets( filename );

		/**
		 * draw the background
		 */
		StdDraw.setScale( -uniradius , uniradius );
		StdDraw.clear();
		StdDraw.picture( 0 , 0 , "images/starfield.jpg" );

		/**
		 * draw all planet
		 */
		for (Planet planet : Body) {
			planet.draw();
		}

		/**
		 * create an animation
		 */
		StdDraw.enableDoubleBuffering();

		/**
		 * every discrete interval,do one calculation
		 * Set up a loop to loop until this time variable is T.
		 */
		for (double t = 0 ; t <= T ; t += dt ) {
			/**
			 * Create an xForces array and yForces array.
			 */
			double[] xForces = new double[Body.length];
			double[] yForces = new double[Body.length];
			
			/**
			 * Calculate the net x and y forces for each planet, storing these in the xForces and yForces arrays respectively.
			 */
			for (int i = 0; i < Body.length ; i++ ) {
				xForces[i] = Body[i].calcNetForceExertedByX(Body);
				yForces[i] = Body[i].calcNetForceExertedByY(Body);
			}

			/**
			 * update each planet’s position, velocity, and acceleration.
			 */
			for (int i = 0; i < Body.length ; i++ ) {
				Body[i].update( dt , xForces[i] , yForces[i]);
			}

			/**
			 * Draw the background image.
			 */
		    StdDraw.picture( 0 , 0 , "images/starfield.jpg" );

		    /**
		     * Draw all of the planets.
		     */
		    for (Planet planet : Body) {
			    planet.draw();
		    }

		    /**
		     * Show the offscreen buffer
		     */
		    StdDraw.show();

		    /**
		     * Pause the animation for 10 milliseconds
		     */
		    StdDraw.pause(10);
		}

		/**
		 * print out the final state of the universe in the same format as the input
		 */
		StdOut.printf("%d\n", Body.length);
        StdOut.printf("%.2e\n", uniradius);
        for (int i = 0; i < Body.length; i++) {
                  StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                  Body[i].xxPos, Body[i].yyPos, Body[i].xxVel,
                  Body[i].yyVel, Body[i].mass, Body[i].imgFileName);   
        }
	}
}
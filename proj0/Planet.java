/**
  *   Planet
  */
public class Planet{
	
  public double xxPos;//Its current x position
	
  public double yyPos;//Its current y position
	
  public double xxVel;//Its current velocity in the x direction
	
  public double yyVel;//Its current velocity in the y direction
	
  public double mass;//Its mass
	
  public String imgFileName;//The name of the file that corresponds to the image that depicts the planet
   
  static final double G = 6.67e-11;
  
 
 public Planet(double xP,double yP,double xV,double yV,double m,String img)
 {
    /** 
      * initialize an instance of the Planet class
      *  @param   xP      Its current x position,
      *  @param   yP      Its current y position,
      *  @param   xV      Its current velocity in the x direction
      *  @param   yV      Its current velocity in the y direction,
      *  @param   m       Its mass,
      *  @param   img     The name of the file that corresponds to the image that depicts the planet
      */
 
    xxPos = xP;
    yyPos = yP;
    xxVel = xV;
    yyVel = yV;
    mass = m;
    imgFileName = img;
 }

 
  
 public Planet(Planet p)
  {
     /** 
       * take in a Planet object and initialize an identical Planet object
       * @param    p      a copy of Plant p
       */
  
     xxPos = p.xxPos;
     yyPos = p.yyPos;
     xxVel = p.xxVel;
     yyVel = p.yyVel;
     mass = p.mass;
     imgFileName = p.imgFileName;
  }


  public double calcDistance(Planet p){
    /** 
      * calculates the distance between two Planets
      * @param    p      a copy of Plant p
      * @return   distance    a double equal to the distance between the supplied planet and the planet that is doing the calculation
      */

    double dx = this.xxPos-p.xxPos;
    double dy = this.yyPos-p.yyPos;
    double r = Math.hypot(dx,dy);
    return r;
  }

  
   public double calcForceExertedBy(Planet p){
     /** 
       * calculate pairwise force 
       * @param     p      a copy of Plant p
       * @return    F      a double describing the force exerted on this planet by the given planet
       */
      
      double r = calcDistance(p);
      double F = ( G * mass * p.mass ) / (r*r);
      return F;
   }

   public double calcForceExertedByX(Planet p){
      /** 
       * the force exerted in the X directions
       * @param   p      a copy of Plant p
       * @return  Fx     the force exerted in the X directions
       */
      
      double Fx= this.calcForceExertedBy(p) * ( p.xxPos - this.xxPos ) / calcDistance(p);
      return Fx;
   }

   public double calcForceExertedByY(Planet p){
    /**
     * calculate the force exerted in the Y directions
     * @param   p      a copy of Plant p
     * @return  Fx     the force exerted in the Y directions
     */
    
      double Fy= this.calcForceExertedBy(p) * ( p.yyPos - this.yyPos ) / calcDistance(p);
      return Fy;
   }

   public double calcNetForceExertedByX(Planet[] ps){
    /** 
     * calculate the net X force exerted by all planets in that array upon the current Planet
     * @param   p      a copy of Plant p
     * @return  Fnetx  the net X force
     */
    
     double Fnetx = 0;
     for (Planet p : ps ) {
      /**
       * lanets cannot exert gravitational forces on themselves
       */
       if(!this.equals(p))
       {
         Fnetx += calcForceExertedByX(p);
       }
     }
     return Fnetx;
   }

   public double calcNetForceExertedByY(Planet[] ps){
    /** 
     * calculate the net Y force exerted by all planets in that array upon the current Planet
     * @param   ps      a copy of Plant p
     * @return  Fnety  the net Y force
     */
    
     double Fnety = 0;
     
     for(Planet p : ps){
      if ( !this.equals(p) ) {
         Fnety += this.calcForceExertedByY(p);
      }
     }
     return Fnety;
   }

   public void update(double dt,double fx,double fy){
    /**
     * determines how much the forces exerted on the planet will cause that planet to accelerate
     * @param   dt    in a small period of time
     * @param   fx    force on direction x
     * @param   fy    force on direction y
     */
    
      this.xxVel = this.xxVel + ( fx / this.mass ) * dt;
      this.yyVel = this.yyVel + ( fy / this.mass ) * dt;
      this.xxPos = this.xxPos + dt * xxVel;
      this.yyPos = this.yyPos + dt * yyVel;
   }

   public void draw(){
    /**
     * uses the StdDraw API mentioned above to draw the Planet’s image at the Planet’s position.
     * return nothing and take in no parameters
     */
    StdDraw.picture( this.xxPos, this.yyPos , "images/" + this.imgFileName);
   }
  }


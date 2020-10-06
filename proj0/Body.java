public class Body{
	private double xxPos;
	private double yyPos;
	private double xxVel;
	private double yyVel;
	private double mass;
	private String imgFileName;
	private static final double G = 6.67e-11;

	public Body(double xP,double yP,double xV,double yV,double m,String img){
		xxPos = xP;
		yyPos = yP;
		xxVel = xV;
		yyVel = yV;
		mass = m;
		imgFileName = new String(img);
	}
	public Body(Body b){
		this.xxPos = b.xxPos;
		this.yyPos = b.yyPos;
		this.xxVel = b.xxVel;
		this.yyVel = b.yyVel;
		this.mass = b.mass;
		this.imgFileName = new String(b.imgFileName);
	}
	public double calcDistance(Body supply){
		double xmin = this.xxPos-supply.xxPos;
		double ymin = this.yyPos-supply.yyPos;
		if(xmin<0)	xmin=-xmin;
		if(ymin<0)  ymin=-ymin;
		double twoDistance = Math.pow(xmin,2)+Math.pow(ymin,2);
		return Math.pow(twoDistance,0.5);
	}
	public double calcForceExertedBy(Body supply){
		return G*(this.mass*supply.mass)/Math.pow(this.calcDistance(supply),2);
	}
	public double calcForceExertedByX(Body supply){
		double F = this.calcForceExertedBy(supply);
		double dx = supply.xxPos-this.xxPos;
		double r = this.calcDistance(supply);
		return F*dx/r;
	}
	public double calcForceExertedByY(Body supply){
		double F = this.calcForceExertedBy(supply);
		double dx = supply.yyPos-this.yyPos;
		double r = this.calcDistance(supply);
		return F*dx/r;	
	}	
	public double calcNetForceExertedByX(Body[] allbodys){
		double NetFx=0;
		for (Body item : allbodys){
			if(!this.equals(item)){
				NetFx += this.calcForceExertedByX(item);
			}
		}
		return NetFx;
	}
	public double calcNetForceExertedByY(Body[] allbodys){
		double NetFy=0;
		for (Body item : allbodys){
			if(!this.equals(item)){
				NetFy += this.calcForceExertedByY(item);
			}
		}
		return NetFy;
	}
	public void update(double dt,double fx,double fy){
		double ax = fx/this.mass;
		double ay = fy/this.mass;
		double newxVel = this.xxVel+dt*ax;
		double newyVel = this.yyVel+dt*ay;
		double newxPos = this.xxPos+dt*newxVel;
		double newyPos = this.yyPos+dt*newyVel;
		this.xxPos = newxPos;
		this.yyPos = newyPos;
	}

}
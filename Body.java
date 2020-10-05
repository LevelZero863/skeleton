public class Body{
	public double xxPos;
	public double yyPos;
	public double xxVel;
	public double yyVel;
	public double mass;
	public String imgFileName;
	private static final G = 6.67e-11;

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
		double twoDistance = Math.sqrt(this.yyPos-supply.yyPos)+Math.sqrt(this.xxPos-supply.xxPos);
		return Math.pow(twoDistance,0.5);
	}
	public double calcForceExertdedBy(Body supply){
		return G*(this.mass*supply.mass)/Math.pow(this.calcDistance(supply),2);
	}
	public double calcForceExertdedByX(Body supply){
		double F = this.calcForceExertdedBy(supply);
		double dx = supply.xxPos-this.xxPos;
		double r = this.calcDistance(supply);
		return F*dx/r;
	}
	public double calcForceExertdedByY(Body supply){
		double F = this.calcForceExertdedBy(supply);
		double dx = supply.yyPos-this.yyPos;
		double r = this.calcDistance(supply);
		return F*dx/r;	
	}	
	public double calcNetForceExertedByX(Body[] allbodys){
		double NetFx=0;
		for (Body item : allbodys){
			if(!this.equals(item)){
				NetFx += this.calcForceExertdedByX(item);
			}
		}
		return NetFx;
	}
	public double calcNetForceExertedByY(Body[] allbodys){
		double NetFy=0;
		for (Body item : allbodys){
			if(!this.equals(item)){
				NetFy += this.calcForceExertdedByY(item);
			}
		}
		return NetFx;
	}
	public void update(double dt,double fx,double fy){
		double ax = fx/this.mass;
		double ay = fy/this.amss;
		double newxVel = this.xxVel+dt*ax;
		double newyVel = this.yyVel+dt*ay;
		double newPos = this.xxPos+dt*newxVel;
		double newPos = this.yyPos+dt*newyVel;
		this.xxPos = newxVel;
		this.yyPos = newyVel;
	}

}
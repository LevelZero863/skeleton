import java.util.ArrayList;
public class NBody{
	public static double readRadius(String fileName){
		In in = new In(fileName);
		int N = in.readInt();
		double radius = in.readDouble();
		return radius;
	}
	public static Body[] readBodies(String fileName){
		In in = new In(fileName);
		int N = in.readInt();
		double radius = in.readDouble();
		String planet;
		Body init = new Body(0,0,0,0,0,"");
		Body[] body = new Body[N];
		for (int n=0;n<N;n++)
			body[n] = new Body(init);
		for(int i=0;i<N;i++){
			body[i].xxPos =  in.readDouble();
			body[i].yyPos =  in.readDouble();
			body[i].xxVel =  in.readDouble();
			body[i].yyVel =  in.readDouble();
			body[i].mass  =  in.readDouble();	
			planet = new String(in.readString());
			body[i].imgFileName = planet;
		}
		return body;
	}
	public static void main(String args[]){
		double T = Double.valueOf(args[0].toString());
		double dt = Double.valueOf(args[1].toString());
		String filename = new String(args[2]);
		double Radius = NBody.readRadius(filename);
		Body[] bodies = NBody.readBodies(filename);
		
		double time = 0;
		ArrayList<Double> xForces = new ArrayList<Double>();
		ArrayList<Double> yForces = new ArrayList<Double>();
		int i = 0;
		while(time <= T){
			for (Body b : bodies){
				double xf = b.calcNetForceExertedByX(bodies);
				double yf = b.calcNetForceExertedByY(bodies);
				xForces.add(xf);
				yForces.add(yf);
			}
			for(Body b : bodies ){
				b.update(dt,xForces.get(i),yForces.get(i));
				i = i+1;
			}
			//Draw background
			StdDraw.enableDoubleBuffering();
			StdDraw.setScale(-Radius,Radius);
			StdDraw.clear();
			StdDraw.picture(0,0,"E:/allCodes/skeleton/proj0/images/starfield.jpg");

			//Draw Bodies
			for(Body b : bodies){
				b.draw();
			}
			StdDraw.show();
		    StdDraw.pause(10);
			time += dt;
		} 

		

	}

}
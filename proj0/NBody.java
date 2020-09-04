import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class NBody {
    public static double readRadius(String fileName){
        In in = new In(fileName);
        double number = in.readDouble();
        return in.readDouble();
    }
    // public static Planet[] readPlanets(String fileName){
    //     In in = new In(fileName);
    //     Planet [] result = new Planet[5];
    //     in.readLine();
    //     in.readLine();
    //     int count = 0;
    //     while(!in.isEmpty() && count < result.length)){
    //         result[count] = new Planet(in.readDouble(),in.readDouble(),in.readDouble(),
    //                                     in.readDouble(),in.readDouble(),in.readString());
    //         count ++;
    //     }
    //     return result;
    // }
    public static Planet[] readPlanets(String filename) {
        In in = new In(filename);
        in.readLine();
        in.readLine();
    
        List<Planet> planets = new ArrayList<>();
        while (!in.isEmpty()) {
          double xxPos;
          try {
            xxPos = in.readDouble();
          } catch (InputMismatchException e) {
            /* EOF */
            break;
          }
          double yyPos = in.readDouble();
          double xxVel = in.readDouble();
          double yyVel = in.readDouble();
          double mass = in.readDouble();
          String imgFileName = in.readString();
    
          Planet p = new Planet(xxPos, yyPos, xxVel, yyVel, mass, imgFileName);
          planets.add(p);
        }
    
        /* Init size can be stored in a variable */
        /* convert it back to array type */
        Planet[] results = new Planet[planets.size()];
        for (int i = 0; i < planets.size(); ++i) {
          results[i] = planets.get(i);
        }
    
        return results;
      }
      public static void main(String[] args) {
          double T = Double.parseDouble(args[0]);
          double dt = Double.parseDouble(args[1]);
          String filename = args[2];
          double R = readRadius(filename);
          Planet [] planets = readPlanets(filename);

          drawBackground(R);
          drawPlanets(planets);
          StdDraw.show();
          StdDraw.enableDoubleBuffering();
          double time = 0;
        while(time < T){
          double [] xForces = new double[planets.length];
          double [] yForces = new double[planets.length];
          for(int i = 0; i<planets.length;i++){
            Planet p1 = planets[i];
            xForces[i] = p1.calcNetForceExertedByX(planets);
            yForces[i] = p1.calcNetForceExertedByY(planets);
          }
          for(int i = 0;i<planets.length;i++){
            planets[i].update(time, xForces[i], yForces[i]);
            drawBackground(R);
            drawPlanets(planets);
            StdDraw.show();
            StdDraw.pause(10);
          }
          time+=dt;
        }
        StdOut.printf("%d\n", planets.length);
        StdOut.printf("%.2e\n", R);
        for (int i = 0; i < planets.length; i++) {
        StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                  planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
                  planets[i].yyVel, planets[i].mass, planets[i].imgFileName);   
}
        }

        public static void drawBackground(double R){
        StdDraw.setScale(-R,R);
        StdDraw.clear();
        StdDraw.picture(0,0,"images/starfield.jpg");        
      }

      public static void drawPlanets(Planet[] p){
        for(Planet pp : p){
          pp.draw();
        }
      }
    }

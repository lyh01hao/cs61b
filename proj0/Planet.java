
public class Planet {
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;
    private static final double G = 6.67e-11;

    public Planet(double xP, double yP, double xV,
                  double yV, double m, String img){
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }
    public Planet(Planet p){
        this.xxPos = p.xxPos;
        this.yyPos = p.yyPos;
        this.xxVel = p.xxVel;
        this.yyVel = p.yyVel;
        this.mass = p.mass;
        this.imgFileName = p.imgFileName;
    }
    public double calcDistance(Planet p){
        return Math.sqrt((this.xxPos-p.xxPos)*(this.xxPos-p.xxPos)+(this.yyPos-p.yyPos)*(this.yyPos-p.yyPos));
    }
    public double calcForceExertedBy(Planet p){
        return G*this.mass*p.mass/(calcDistance(p)*calcDistance(p));
    }
    public double calcForceExertedByX(Planet p){
        double dx = p.xxPos - this.xxPos;
        double r = calcDistance(p);
        double f = calcForceExertedBy(p);
        return f/r*dx;
    }
    public double calcForceExertedByY(Planet p){
        double dy = p.yyPos - this.yyPos;
        double r = calcDistance(p);
        double f = calcForceExertedBy(p);
        return f/r*dy;
    }
    public double calcNetForceExertedByX(Planet []planets){
        double net = 0;
        for(Planet pp:planets){
            if(pp.equals(this)){
                continue;
            }
            net += calcForceExertedByX(pp);
        }
        return net;
    }
    public double calcNetForceExertedByY(Planet []planets){
        double net = 0;
        for(Planet pp:planets){
            if(pp.equals(this)){
                continue;
            }
            net += calcForceExertedByY(pp);
        }
        return net;
    }
    public void update(double time, double xxForce, double yyForce){
        double xAcceleration = xxForce / this.mass;
        double yAcceleration = yyForce / this.mass;
        this.xxVel += xAcceleration * time;
        this.yyVel += yAcceleration * time;
        this.xxPos += time * this.xxVel;
        this.yyPos += time * this.yyVel;
    }
    public void draw (){
        StdDraw.picture(this.xxPos, this.yyPos, "images/" + this.imgFileName);
    }
}

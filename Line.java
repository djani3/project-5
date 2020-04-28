
public class Line{
	Point a;Point b;Boolean vali;
	Line(Point x,Point y){a=x;b=y;validi();}
	
	public void validi() {
		if(a.x==b.x ||a.y==b.y)vali=true;
		else vali = false;
	}
	
}
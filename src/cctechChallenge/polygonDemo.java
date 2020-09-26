package cctechChallenge;

public class polygonDemo 
{ 
	static int INF = 10000; 

	static class Point { 
		double x; 
		double y; 

		public Point(double x, double y) { 
			this.x = x; 
			this.y = y; 
		} 
	}; 

	static boolean onLine(Point p, Point q, Point r) { 
		if (q.x <= Math.max(p.x, r.x) && 
			q.x >= Math.min(p.x, r.x) && 
			q.y <= Math.max(p.y, r.y) && 
			q.y >= Math.min(p.y, r.y)) 
		{ 
			return true; 
		} 
		return false; 
	} 

	static int exsistence(Point p, Point q, Point r) { 
		double val = (q.y - p.y) * (r.x - q.x) 
				- (q.x - p.x) * (r.y - q.y); 

		if (val == 0) { 
			return 0;  
		} 
		return (val > 0) ? 1 : 2;
	} 

	static boolean doCollide(Point p1, Point q1, 
							Point p2, Point q2) { 

		int o1 = exsistence(p1, q1, p2); 
		int o2 = exsistence(p1, q1, q2); 
		int o3 = exsistence(p2, q2, p1); 
		int o4 = exsistence(p2, q2, q1); 

		if (o1 != o2 && o3 != o4) { 
			return true; 
		} 
 
		if (o1 == 0 && onLine(p1, p2, q1)) { 
			return true; 
		} 
 
		if (o2 == 0 && onLine(p1, q2, q1)) { 
			return true; 
		} 

		if (o3 == 0 && onLine(p2, p1, q2)) { 
			return true; 
		} 

		if (o4 == 0 && onLine(p2, q1, q2)) { 
			return true; 
		} 

		return false; 
	} 

	static boolean insidePoint(Point polygon[], int n, Point p) { 
		if (n < 3) { 
			return false; 
		} 

		Point extreme = new Point(INF, p.y); 

		int count = 0, i = 0; 
		do
		{ 
			int next = (i + 1) % n; 

			if (doCollide(polygon[i], polygon[next], p, extreme)) { 
				if (exsistence(polygon[i], p, polygon[next]) == 0) { 
					return onLine(polygon[i], p, polygon[next]); 
				} 
				count++; 
			} 
			i = next; 
		} while (i != 0); 

		return (count%2 == 1);
	} 

	public static void main(String[] args) 
	{ 
		int n;
		Point p;
		
		Point polygon1[] = {new Point(1, 0), 
				new Point(8, 3), 
				new Point(8, 8), 
				new Point(1, 5)}; 
		p = new Point(3,5); 
		n = polygon1.length; 
		
		if (insidePoint(polygon1, n, p)) { 
			System.out.println("True"); 
		} 
		else{ 
			System.out.println("False"); 
		} 
		
		Point polygon2[] = {new Point(-3, 2), 
				new Point(-2,-0.8), 
				new Point(0, 1.2), 
				new Point(2.2, 0),
				new Point(2,4.5)}; 
		p = new Point(0, 0); 
		n = polygon2.length; 
		
		if (insidePoint(polygon2, n, p)) {
			System.out.println("True"); 
		}
		else{ 
			System.out.println("False"); 
		} 
		
	} 
} 

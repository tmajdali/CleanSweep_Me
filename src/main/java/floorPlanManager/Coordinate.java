package main.java.floorPlanManager;


public class Coordinate {
	private int x;
	private int y;
	private String xy;
	
	public Coordinate(){
		this.x =0;
		this.y = 0;
		this.xy = "0,0";
	}
	public void setString(String xy){
		this.xy =xy;
		String[] temp = xy.split(",");
		this.x = Integer.parseInt(temp[0]);
		this.y = Integer.parseInt(temp[1]);
		
	}
	
	public void setX(int x){
		this.x = x;
		String[] temp = this.xy.split(",");
		this.xy = x+ "," + temp[1];
		
	}
	public void setY(int y){
		this.y = y;
		String[] temp = this.xy.split(",");
		this.xy = temp[0]+ "," + y;
	}
	
	public int getX(){
		return this.x;
	}
	public int getY(){
		return this.y;
	}
	public String getStringXY(){
		return this.xy;
	}
	
	public boolean isSameCoordinate(Coordinate o){
		if(this.x == o.getX()&& this.y == o.getY()){
			return true;
		}
		else{
		return false;
		}
		
	}

	

	
}

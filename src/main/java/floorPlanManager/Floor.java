package main.java.floorPlanManager;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Set;

public class Floor {
	private Hashtable<String,Tile> floorPlan;
	private Coordinate currentLocation;
	private HashSet<String> chargeStations;
	private static Floor fl = null;
	public static synchronized Floor getInstance(){
		if(fl==null){
			fl = new Floor();
		}
		return fl;
	}
	private Floor(){
		floorPlan = new Hashtable<String,Tile>();
		currentLocation = new Coordinate();
		chargeStations = new HashSet<String>();
		
	}
	public boolean isChargeStation(Coordinate co){
		return this.floorPlan.get(co.getStringXY()).getChargeStation();
	}
	
	public Set<String> getAllCoordinates(){
		return this.floorPlan.keySet();
	}
	public void setChargeStationLocation(Coordinate co){
		this.chargeStations.add(co.getStringXY());
		
	}
	public HashSet<String> getChargeStaionLocation(){
		return this.chargeStations;
	}
	public void loadFloorPlan(Hashtable<String,Tile> flp){
		this.floorPlan = flp;
	}
	public void updateTile(String location, Tile tile){
		this.floorPlan.remove(location);
		this.floorPlan.put(location, tile);
	}
	
	public void setCurrentLocation(int x, int y){
		this.currentLocation.setX(x);
		this.currentLocation.setY(y);
	}
	public Coordinate getCurrentLocation(){
		return this.currentLocation;
	}
	
	public int getSurfaceType(Coordinate co){
		return this.floorPlan.get(co.getStringXY()).getSurfaceType();
		
	}
	
	public HashSet<String> getOpenNeighbor(Coordinate co){
		HashSet<String> temp = new HashSet<String>();
		if(this.floorPlan.get(co.getStringXY()).getFrontPath()==1){
			String up = co.getX()+"," + (co.getY()+1);
			temp.add(up);
		}
		if(this.floorPlan.get(co.getStringXY()).getRightPath()==1){
			String right = (co.getX()+1) +"," + co.getY();
			temp.add(right);
		}
		if(this.floorPlan.get(co.getStringXY()).getBackPath()==1){
			String down = co.getX()+"," +(co.getY()-1);
			temp.add(down);
		}
		if(this.floorPlan.get(co.getStringXY()).getLeftPath()==1){
			String left = (co.getX()-1)+"," + co.getY();
			temp.add(left);
		}
		//System.out.println("open :" + temp);
		return temp;
		
	}
	public HashSet<String> getUnkownNeighbor(Coordinate co){
		HashSet<String> temp = new HashSet<String>();
		

		if(this.floorPlan.get(co.getStringXY()).getFrontPath()==0){
			String up = co.getX()+"," + (co.getY()+1);
		//	System.out.println(up);
			temp.add(up);
		}
		if(this.floorPlan.get(co.getStringXY()).getRightPath()==0){
			String right = (co.getX()+1) +"," + co.getY();
			System.out.println(right);
			temp.add(right);
		}
		if(this.floorPlan.get(co.getStringXY()).getBackPath()==0){
			String down = co.getX()+"," +(co.getY()-1);
			System.out.println(down);
			temp.add(down);
		}
		if(this.floorPlan.get(co.getStringXY()).getLeftPath()==0){
			String left = (co.getX()-1)+"," + co.getY();
			System.out.println(left);
			temp.add(left);
		}
		//System.out.println(co.getStringXY());
		//System.out.println(temp);
		return temp;
		
	}
	public void setDirt(Coordinate co, int dm){
		floorPlan.get(co.getStringXY()).setDirtAmount(dm);
	}
	public boolean isDirty(Coordinate co){
		if(floorPlan.get(co.getStringXY()).getDirtAmount()==0){
			return true;
		}
		return false;
	}
	public void updatePath(String coor,String orientation,int update){
		switch(orientation){
		case "front": this.floorPlan.get(coor).setFrontPath(update);;
		case "back": this.floorPlan.get(coor).setBackPath(update);;
		case "right": this.floorPlan.get(coor).setRightPath(update);;
		case "left": this.floorPlan.get(coor).setLeftPath(update);;
		}
		
	}
	public HashSet<String> getObstacleNeighbor(Coordinate co){
		HashSet<String> temp = new HashSet<String>();
		if(this.floorPlan.get(co.getStringXY()).getFrontPath()==2){
			String up = co.getX()+"," + (co.getY()+1);
			temp.add(up);
		}
		if(this.floorPlan.get(co.getStringXY()).getRightPath()==2){
			String right = (co.getX()+1) +"," + co.getY();
			temp.add(right);
		}
		if(this.floorPlan.get(co.getStringXY()).getBackPath()==2){
			String down = co.getX()+"," +(co.getY()-1);
			temp.add(down);
		}
		if(this.floorPlan.get(co.getStringXY()).getLeftPath()==2){
			String left = (co.getX()-1)+"," + co.getY();
			temp.add(left);
		}
		return temp;
		
	}
	

}

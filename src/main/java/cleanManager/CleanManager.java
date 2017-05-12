package main.java.cleanManager;

import java.util.Set;

import main.java.floorPlanManager.Coordinate;
import main.java.floorPlanManager.FloorPlanManager;

public class CleanManager {
	private static CleanManager clm = null;
	private int loadlvl;
	private CleanManager(){	
		this.loadlvl = 50;
	}
	public static synchronized CleanManager getInstance(){
		if(clm==null){
			clm = new CleanManager();
		}
		return clm;
	}
	public int getLoad(){
		return this.loadlvl;
		
	}
	public void removeLoad(){
		this.loadlvl --;
	}
	public void dumpLoad(){
		this.loadlvl = 50;
	}
	public boolean isFull(){
		if(this.loadlvl==50){
			return true;
		}
		else{
			return false;
		}
	}
	public boolean isAllCleaned(){
		FloorPlanManager fpm = FloorPlanManager.getInstance();
		Set<String> allLocation = fpm.getAllCoordinates();
		for(String location: allLocation){
			Coordinate co = new Coordinate();
			co.setString(location);
			if(fpm.isDirty(co)){
				return false;
			}
			
		}
		
		return true;
	}
}

package main.java.floorPlanManager;
/*FloorType:
 * 1 represent bare floor, 2 represent low-pile,
 * 3 represent high-pile
 * path:
 * 0 = unknown
 * 1 = open
 * 2 = obstacle
 * 4 = stairs 
 * dirtAmount
 * 0 = dirty
 * 1 = clean
 */

import main.java.powermanagerment.FloorType;

public class Tile {
	private int surfaceType;
	private FloorType floorType;
	private int cellWeight;
	private int frontPath;
	private int backPath;
	private int leftPath;
	private int rightPath;
	private boolean isChargeStation;
	private int dirtAmount;
	
	public Tile(int surfaceType, int frontPath, int rightPath,
		int backPath,int leftPath,boolean isChargeStation,int dirtAmount){
		setSurfaceType(surfaceType);
		setFrontPath(frontPath);
		setBackPath(backPath);
		setLeftPath(leftPath);
		setRightPath(rightPath);
		setChargeStation(isChargeStation);
		setDirtAmount(dirtAmount);	
		
	}
	public int getCellWeight(){
		
		if(surfaceType == 3)
			cellWeight = 3;
		else if(surfaceType == 2)
			cellWeight = 2;
		else if(surfaceType == 1)
			cellWeight =1;
		
		return cellWeight;
	}
	
	public void setSurfaceType(int type){
		this.surfaceType = type;
	}
	public int getSurfaceType(){
		return surfaceType;
//		if(surfaceType == 3)
//		return FloorType.HIGH_PILE;
//		else if(surfaceType == 2)
//			return FloorType.LOW_PILE;
//		else
//			return FloorType.BARE;
	}
	
	public void setFrontPath(int fp){
		this.frontPath = fp;
	}
	public int getFrontPath(){
		return frontPath;
	}
	
	public void setBackPath(int bp){
		this.backPath = bp;
	}
	public int getBackPath(){
		return backPath;
	}
	
	public void setLeftPath(int lp){
		this.leftPath = lp;
	}
	public int getLeftPath(){
		return leftPath;
	}
	
	public void setRightPath(int rp){
		this.rightPath = rp;
	}
	public int getRightPath(){
		return rightPath;
	}
	
	public void setChargeStation(boolean cs){
		this.isChargeStation = cs;
	}
	public boolean getChargeStation(){
		return isChargeStation;
	}
	
	public void setDirtAmount(int dm){
		this.dirtAmount =  dm;
	}
	public int getDirtAmount(){
		return dirtAmount;
	}
	

}

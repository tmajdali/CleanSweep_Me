package main.java.powermanagerment;

public class CellWeight {
	private int surfaceType;
	private FloorType floorType;
	private static CellWeight instance;

	private int cellWeight;
	public CellWeight(){
		setSurfaceType(surfaceType);
		setFloorType(floorType);
		setCellWeight(cellWeight);	
	}

	public void setCellWeight(int cellWeight) {
		this.cellWeight = cellWeight;
	}

	public void setFloorType(FloorType floorType) {
		this.setFloorType(floorType);
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
	public FloorType getSurfaceType(){
		if(surfaceType == 3)
			return FloorType.HIGH_PILE;
		else if(surfaceType == 2)
			return FloorType.LOW_PILE;
		else
			return FloorType.BARE;
	}
	public static CellWeight getInstance() {
		if (instance == null) {
			instance = new CellWeight();
		}
		return instance;
	}

}

package main.java.powermanagerment;

public class PowerManagement {
	private int remainingBattery;
	private int maxBattery;

	public PowerManagement(VacuumManager manager){
		maxBattery = 100;
		remainingBattery = 100;
	}
	public void setRemainingBattery(int remainingBattery){
		if(remainingBattery>= 0 && remainingBattery <=100)
			this.remainingBattery = remainingBattery;
	}
	public int getRemainingBattery(){
		return remainingBattery;
	}
	public void consumeBattery(int amount){
		this.remainingBattery -= amount;

	}

	public int getBattery(){
		return BatteryState.getInstance().getBattery();
	}
	public void setBattery(int battery){
		BatteryState.getInstance().setBattery(battery);	
	}
	public int getCellWeight() {
		return CellWeight.getInstance().getCellWeight();
	}
	public void setCellWeight(int cellWeight) {
		CellWeight.getInstance().setCellWeight(cellWeight);;
	}
}

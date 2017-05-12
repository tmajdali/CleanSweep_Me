package main.java.powermanagerment;

public class VacuumManager {
	private BatteryState batteryState;

	public VacuumManager(){
		batteryState = new BatteryState();
	}
	public BatteryState getBattery(){
		return batteryState;
	}
}

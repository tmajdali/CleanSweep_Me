package main.java.powermanagerment;

public class BatteryState {
	private int battery;

	private static BatteryState instance;
	public BatteryState(){
		this.setBattery(100);
	}

	public int getBattery() {
		return battery;
	}

	public void setBattery(int battery) {
		this.battery = battery;
	}
	public static BatteryState getInstance() {
		if (instance == null) {
			instance = new BatteryState();
		}
		return instance;
	}

}

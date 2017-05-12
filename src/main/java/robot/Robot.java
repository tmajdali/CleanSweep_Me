package main.java.robot;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Stack;

import main.java.cleanManager.CleanManager;
import main.java.floorPlanManager.Coordinate;
import main.java.floorPlanManager.FloorPlanManager;
import main.java.floorPlanManager.Vertex;
import main.java.powermanagerment.PowerManagement;
import main.java.powermanagerment.VacuumManager;

public class Robot {
	
	private FloorPlanManager fpm = FloorPlanManager.getInstance();
	private Coordinate currentLocation;
	private String chargeStation = "0,0";
	private Stack<String> stack;

	private ArrayList<String> visited;
	private CleanManager clm = CleanManager.getInstance();
	private VacuumManager vac = new VacuumManager();
	private PowerManagement pwm = new PowerManagement(vac);

	public Robot() {

		stack = new Stack<String>();
		visited = new ArrayList<String>();
	}

	public void start() {

		currentLocation = fpm.getCurrentLocation();
		if (fpm.isChargeStation(currentLocation)) {
			fpm.setChargeStationLocation(currentLocation);
		}
		// System.out.println(currentLocation.getStringXY());
		for (String co : fpm.getOpenNeighbor(currentLocation)) {
			stack.push(co);

		}

		while (!stack.isEmpty()) {

			String nextStep = stack.pop();
			String current = currentLocation.getStringXY();
			// currentLocation.setString(nextStep);

			fpm.setCurrentLocation(currentLocation.getX(), currentLocation.getY());
			if (!visited.contains(nextStep)) {
				
				System.out.println("---------------------------");
				if (!enoughPower()) {
				
					System.out.println("not enough Power going back to station" + "----remaining power: " + pwm.getRemainingBattery());

					LinkedList<Vertex> path = fpm.getPath(currentLocation.getStringXY(), chargeStation);
					String tempNext;
					for (Vertex ver : path) {
						
						tempNext = ver.getName();
						
						System.out.println("Cleaner moving to: " + ver);
						pwm.consumeBattery((int) fpm.getCostPath(current, tempNext));
						current = ver.getName();
						currentLocation.setString(current);
						
						System.out.println("Cleaner now at: " + currentLocation.getStringXY());

					}
					pwm.setRemainingBattery(100);

				}

				// System.out.println(fpm.getCostPath(current, nextStep));;
				
				System.out.println("Battery State: " + pwm.getRemainingBattery());
		
				System.out.println("Cleaner now at: " + current);
				
				// System.out.println("going from " + current + " to " +
				// nextStep);
				LinkedList<Vertex> paths = fpm.getPath(current, nextStep);
				if (paths != null) {
					paths.pop();
					String tempNext;
					for (Vertex ver : paths) {
						tempNext = ver.getName();
						System.out.println("Cleaner moving to: " + ver);
						
						pwm.consumeBattery((int) fpm.getCostPath(current, tempNext));
						current = ver.getName();
						currentLocation.setString(current);
						System.out.println("Cleaner now at: " + currentLocation.getStringXY());
						
					}
				}
				// System.out.println("im at " +nextStep);
				if (fpm.isDirty(currentLocation)) {
					clm.removeLoad();
					pwm.consumeBattery(fpm.getSurfaceType(currentLocation));
					System.out.println("cleaning at " + nextStep);
					
					fpm.setDirt(currentLocation, 1);
					System.out.println("All Cleaned: " + clm.isAllCleaned());
					
					System.out.println("---------------------------");
					
				}
				// System.out.println("I am at " +
				// fpm.getCurrentLocation().getStringXY());
				getAvailablePath();

				visited.add(nextStep);

			}

		}

	}

	private boolean enoughPower() {
		int powerLevel = pwm.getRemainingBattery();

		if (powerLevel >= fpm.getCostPath(currentLocation.getStringXY(), chargeStation)) {
			System.out.println("enough power");
			
			return true;
		}
		return false;
	}

	private void getAvailablePath() {
		HashSet<String> locations = fpm.getOpenNeighbor(currentLocation);
		for (String location : locations) {
			// System.out.println(location);
			stack.push(location);

		}

	}

	

}

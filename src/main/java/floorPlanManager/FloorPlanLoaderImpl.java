package main.java.floorPlanManager;

import java.io.File;
import java.util.Hashtable;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class FloorPlanLoaderImpl implements FloorPlanLoader {
	Hashtable<String,Tile> info = new Hashtable<String,Tile>();
	@Override
	public Hashtable<String, Tile> loadFloorPlan(String file) {

		try{
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();

			File xml = new File("src/main/java/floorPlanManager/"+ file);
			Document doc = db.parse(xml);
			doc.getDocumentElement().normalize();

			NodeList floorPlan = doc.getDocumentElement().getChildNodes();
			for (int i = 0; i < floorPlan.getLength(); i++) {

				if (floorPlan.item(i).getNodeType() == Node.TEXT_NODE) {
					continue;
				}

				String entryName = floorPlan.item(i).getNodeName();
				if (!entryName.equals("Tile")) {
					System.err.println("Unexpected node found: " + entryName);

				}


				// Get a node attribute
				NamedNodeMap aMap = floorPlan.item(i).getAttributes();
				String tileName = aMap.getNamedItem("Name").getNodeValue();
				//System.out.println(tileName);

				// Get a named nodes
				Element elem = (Element) floorPlan.item(i);
				String surfaceType = elem.getElementsByTagName("Surface").item(0).getTextContent();
				String backPath = elem.getElementsByTagName("Back").item(0).getTextContent();
				String frontPath = elem.getElementsByTagName("Front").item(0).getTextContent();
				String rightPath = elem.getElementsByTagName("Right").item(0).getTextContent();
				String isChargeStation = elem.getElementsByTagName("ChargeStation").item(0).getTextContent();
				String leftPath = elem.getElementsByTagName("Left").item(0).getTextContent();
				boolean charge;
				if(isChargeStation.equals("0")){
					charge = false;}
				else{
					charge = true;
				}
				String dirtAmount = elem.getElementsByTagName("Dirt").item(0).getTextContent();

				Tile tile = new Tile(Integer.parseInt(surfaceType),Integer.parseInt(frontPath),Integer.parseInt(rightPath),Integer.parseInt(backPath),Integer.parseInt(leftPath),charge,Integer.parseInt(dirtAmount));
				//System.out.println(charge);
				info.put(tileName, tile);


			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return info;
	}
	public Hashtable<String,Tile> loadSample(){

		Tile tile1 = new Tile(1, 0, 1, 1, 0, false, 0);
		Tile tile2 = new Tile(2, 0, 1, 1, 1, false, 0);
		Tile tile3 = new Tile(3, 0, 0, 1, 1, false, 0);
		Tile tile4 = new Tile(1, 1, 1, 1, 0, false, 0);
		Tile tile5 = new Tile(2, 1, 1, 1, 1, false, 0);
		Tile tile6 = new Tile(3, 1, 0, 1, 1, false, 0);
		Tile tile7 = new Tile(1, 1, 1, 0, 0, false, 0);
		Tile tile8 = new Tile(2, 1, 1, 0, 1, false, 0);
		Tile tile9 = new Tile(3, 1, 0, 0, 1, false, 0);

		this.info.put("0,2", tile1);
		this.info.put("1,2", tile2);
		this.info.put("2,2", tile3);
		this.info.put("0,1", tile4);
		this.info.put("1,1", tile5);
		this.info.put("2,1", tile6);
		this.info.put("0,0", tile7);
		this.info.put("1,0", tile8);
		this.info.put("2,0", tile9);
		return this.info;

	}

	


}




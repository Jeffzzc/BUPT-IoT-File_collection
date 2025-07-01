package service;

import java.util.Map;

import datautil.JSONController;
import domain.Config;
import domain.DataSource;
import domain.Stock;

/**
*  The ConfigService class is a control class for the configs.
*  Title      : ConfigService.java
*  <p>
*  Description:
*  <p>
*  The ConfigService class is a control class for the configs
*  <p>
*  All current naming rules are hardwired into this class
*  @author  Tianyi Liu
*  @version 1.1
*/
public class ConfigService {
	private String pricefilename;
	private String stockfilename;
	private String recfilename;
	private DataSource ds;
	
	/**
	 * The constructor of the ConfigService
	 * @param pricefilename The name of the price file
	 * @param stockfilename The name of the stock file
	 * @param recfilename   The name of the rec file
	 * @param ds            The datasource object
	 */
	public ConfigService(String pricefilename, String stockfilename, String recfilename, DataSource ds) {
		this.pricefilename=pricefilename;
		this.stockfilename=stockfilename;
		this.recfilename=recfilename;
		JSONController json=new JSONController(this.pricefilename);
		Map<String, Double> pricemp=(Map<String, Double>) json.read(Map.class);
		Config nowconf=new Config();
		nowconf.setChashuaddPirce(pricemp.get("Chashu"));
		nowconf.setEggaddPirce(pricemp.get("Egg"));
		nowconf.setShootsaddPirce(pricemp.get("Shoots"));
		nowconf.setNoriaddPirce(pricemp.get("Nori"));
		nowconf.setNoodlePirce(pricemp.get("Ramen"));
		
		json=new JSONController(this.stockfilename);
		Map<String, Integer> stockmp = (Map<String, Integer>) json.read(Map.class);
		Stock sck=new Stock();
		int []soup=new int[3];
		int []noodles=new int[3];
		int []springonion=new int[3];
		soup[0]=stockmp.get("Soup0");
		soup[1]=stockmp.get("Soup1");
		soup[2]=stockmp.get("Soup2");
		noodles[0]=stockmp.get("Noodles0");
		noodles[1]=stockmp.get("Noodles1");
		noodles[2]=stockmp.get("Noodles2");
		springonion[0]=stockmp.get("Spring onion0");
		springonion[1]=stockmp.get("Spring onion1");
		springonion[2]=stockmp.get("Spring onion2");
		sck.setSoup(soup);
		sck.setNoodles(noodles);
		sck.setSpringonion(springonion);
		sck.setBoiledegg(stockmp.get("Boiled egg"));
		sck.setChashu(stockmp.get("Chashu"));
		sck.setNori(stockmp.get("Nori"));
		sck.setNoriadd(stockmp.get("Extra Nori"));
		sck.setChashuadd(stockmp.get("Extra Chashu"));
		sck.setEggadd(stockmp.get("Extra boiled egg"));
		sck.setShootsadd(stockmp.get("Bamboo shoots"));
		ds.setStock(sck);
		
		json=new JSONController(this.recfilename);
		Map<String, String> recmp = (Map<String, String>) json.read(Map.class);
		String rec=recmp.get("addon");
		switch (rec) {
		case("Extra Nori"):
			nowconf.setRec(1);
			break;
		case("Bamboo shoots"):
			nowconf.setRec(3);
			break;
		case("Extra boiled egg"):
			nowconf.setRec(2);
			break;
		case("Extra Chashu"):
			nowconf.setRec(4);
			break;
		}
		ds.setConfig(nowconf);
	}
	
	
	
}

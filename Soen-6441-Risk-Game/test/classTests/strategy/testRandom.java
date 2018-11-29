package classTests.strategy;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import model.gameplay.Phase;
import model.gameplay.Player;
import model.gameplay.strategy.Random;
import model.gameplay.strategy.Human;
import model.map.Continent;
import model.map.Country;
import model.map.Map;

/**
 * this test case is to test the random pattern
 * @author Yueshuai
 *
 */
public class testRandom {

	Continent con1,con2;
	Country cty1,cty2,cty3,cty4;
	Player p1,p2;
	Map map = new Map();
	@Before
	public void Before() {
		con1 = new Continent("",3);
		con2 = new Continent("",2);
		cty1 = new Country("");
		cty2 = new Country("");
		cty3 = new Country("");
		cty4 = new Country("");
		p1 = new Player(1, 5, map, new Random());
		p2 = new Player(2, 3, map, new Random());
		map.continents.add(con1);
		map.continents.add(con2);
		map.countries.add(cty1);
		map.countries.add(cty2);
		map.countries.add(cty3);
		map.countries.add(cty4);
		con1.addCountry(cty1);
		con2.addCountry(cty2);
		con1.addCountry(cty3);
		con2.addCountry(cty4);
		map.players.add(p1);
		map.players.add(p2);
		
		p1.setArmies(10);
		p2.setArmies(20);
	}
	/**
	 * test the ramdom reinforcement when player takes the whole continent
	 */
	@Test
	public void testReinforce() {
		p1.ownedCountries.add(cty1);
		cty1.setPlayer(p1);
		p1.ownedCountries.add(cty3);
		cty3.setPlayer(p1);
		p1.ownedCountries.add(cty2);
		cty2.setPlayer(p1);
		p2.ownedCountries.add(cty4);
		cty4.setPlayer(p2);
		
		cty1.linkTo(cty2);
		cty2.linkTo(cty1);
		
		cty1.linkTo(cty3);
		cty3.linkTo(cty1);
		
		cty4.linkTo(cty2);
		cty2.linkTo(cty4);
		
		cty1.setArmyNumber(1);
		cty2.setArmyNumber(2);
		cty3.setArmyNumber(3);
		cty4.setArmyNumber(4);
		Phase phase  = new Phase();
		map.setPhase(phase);
		
		//test the number of armies before random reinforce
		assertEquals(14,cty1.getArmyNumber()+cty3.getArmyNumber()+p1.getArmies());
		assertEquals(26,cty2.getArmyNumber()+cty4.getArmyNumber()+p2.getArmies());
		
		assertEquals(10,p1.getArmies());
		assertEquals(20,p2.getArmies());
		assertEquals(1,cty1.getArmyNumber());
		assertEquals(3,cty3.getArmyNumber());
		
		p1.reinforce();
		
		assertEquals(18,cty1.getArmyNumber()+cty3.getArmyNumber()+p1.getArmies());
		assertEquals(10,p1.getArmies());
		assertEquals(20,p2.getArmies());
		//assertEquals(2,cty2.getArmyNumber());
		
	}
	/*
	@Test
	public void testAttack() {
		p1.ownedCountries.add(cty1);
		cty1.setPlayer(p1);
		
		p2.ownedCountries.add(cty2);
		cty2.setPlayer(p2);
		p2.ownedCountries.add(cty3);
		cty3.setPlayer(p2);
		p2.ownedCountries.add(cty4);
		cty4.setPlayer(p2);
		
		cty1.linkTo(cty3);
		cty3.linkTo(cty1);
		
		cty1.linkTo(cty2);
		cty2.linkTo(cty1);
		
		cty2.linkTo(cty4);
		cty4.linkTo(cty2);
		
		Phase phase  = new Phase();
		map.setPhase(phase);
		
		assertTrue(p1.owns(cty1.getNumber()));
		assertFalse(p1.owns(cty2.getNumber()));
		assertFalse(p1.owns(cty3.getNumber()));
		assertFalse(p1.owns(cty4.getNumber()));
		
		p1.attack();
		assertTrue(p1.owns(cty1.getNumber()));
		assertTrue(p1.owns(cty2.getNumber()));
		assertTrue(p1.owns(cty3.getNumber()));
		assertFalse(p1.owns(cty4.getNumber()));
	}*/

}

package de.hsmainz.cs.semgis.arqextension.test.unit;

import static org.junit.Assert.assertEquals;

import org.apache.jena.sparql.expr.NodeValue;
import org.junit.jupiter.api.Test;

import de.hsmainz.cs.semgis.arqextension.unit.NauticalMileToMeter;

public class NauticalMileToMeterTest {

	@Test
	public void testNauticalMileToMeter() {
        NodeValue unitamount = NodeValue.makeDouble(1);
		NauticalMileToMeter instance=new NauticalMileToMeter();
        NodeValue expResult = NodeValue.makeDouble(1852);
        NodeValue result = instance.exec(unitamount);
        System.out.println(result);
        assertEquals(expResult, result);
	}
	
}

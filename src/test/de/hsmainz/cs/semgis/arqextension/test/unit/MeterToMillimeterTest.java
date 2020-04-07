package de.hsmainz.cs.semgis.arqextension.test.unit;

import static org.junit.Assert.assertEquals;

import org.apache.jena.sparql.expr.NodeValue;
import org.junit.jupiter.api.Test;

import de.hsmainz.cs.semgis.arqextension.unit.MeterToMillimeter;

public class MeterToMillimeterTest {

	@Test
	public void testMeterToMillimeter() {
        NodeValue unitamount = NodeValue.makeDouble(1.);
        MeterToMillimeter instance=new MeterToMillimeter();
        NodeValue expResult = NodeValue.makeDouble(1000);
        NodeValue result = instance.exec(unitamount);
        System.out.println(result);
        assertEquals(expResult, result);
	}
	
}

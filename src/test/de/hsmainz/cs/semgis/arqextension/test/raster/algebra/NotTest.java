package de.hsmainz.cs.semgis.arqextension.test.raster.algebra;

import static org.junit.Assert.assertEquals;

import org.apache.jena.sparql.expr.NodeValue;
import org.junit.jupiter.api.Test;

import de.hsmainz.cs.semgis.arqextension.raster.algebra.Not;
import de.hsmainz.cs.semgis.arqextension.test.util.SampleRasters;
import io.github.galbiston.geosparql_jena.implementation.datatype.raster.CovJSONDatatype;

public class NotTest extends SampleRasters {

	public static final String combinedRasterLiteral="";
	
	@Test
	public void testNot() {
        NodeValue covLiteral = NodeValue.makeNode(rasterLiteral1, CovJSONDatatype.INSTANCE);
        Not instance=new Not();
        NodeValue expResult = NodeValue.makeNode(combinedRasterLiteral, CovJSONDatatype.INSTANCE);
        NodeValue result = instance.exec(covLiteral);
        assertEquals(expResult, result);
	}

}

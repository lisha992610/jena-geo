package de.hsmainz.cs.semgis.arqextension.polygon.attribute;

import org.apache.jena.datatypes.DatatypeFormatException;
import org.apache.jena.sparql.expr.ExprEvalException;
import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase1;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.Polygon;
import org.locationtech.jts.geomgraph.GeometryGraph;
import org.locationtech.jts.operation.valid.ConnectedInteriorTester;

import io.github.galbiston.geosparql_jena.implementation.GeometryWrapper;

public class HasConnectedInteriorTest extends FunctionBase1 {

		@Override
		public NodeValue exec(NodeValue v) {
	        try {
	            GeometryWrapper geometry = GeometryWrapper.extract(v);
	            Geometry geom = geometry.getParsingGeometry();
	            if (geom instanceof Polygon) {    	
	            	ConnectedInteriorTester tester=new ConnectedInteriorTester(new GeometryGraph(0,geom));
	            	return NodeValue.makeBoolean(tester.isInteriorsConnected());
	            }
	            return NodeValue.FALSE;
	        } catch (DatatypeFormatException ex) {
	            throw new ExprEvalException(ex.getMessage(), ex);
	        }
		}	
}

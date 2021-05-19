package de.hsmainz.cs.semgis.arqextension.point.constructor;

import java.math.BigInteger;
import java.util.Arrays;

import org.apache.jena.datatypes.DatatypeFormatException;
import org.apache.jena.sparql.expr.ExprEvalException;
import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase2;
import org.locationtech.jts.geom.Geometry;

import io.github.galbiston.geosparql_jena.implementation.GeometryWrapper;
import io.github.galbiston.geosparql_jena.implementation.GeometryWrapperFactory;
import io.github.galbiston.geosparql_jena.implementation.datatype.WKTDatatype;
import io.github.galbiston.geosparql_jena.implementation.parsers.wkt.WKTReader;

/**
 * Makes a Geometry from WKT with the given SRID. If SRID is not given, it defaults to 0.
 *
 */
public class MPointFromText  extends FunctionBase2 {

	@Override
	public NodeValue exec(NodeValue arg0,NodeValue arg1) {
        try {
            String wktstring=arg0.getString();
            BigInteger srid=arg1.getInteger();
            WKTReader wktreader=WKTReader.extract(wktstring);
            Geometry geom=wktreader.getGeometry();     
            if("MULTIPOINT".equals(geom.getGeometryType().toUpperCase())){
            	GeometryWrapper pointWrapper = GeometryWrapperFactory.createMultiPoint(Arrays.asList(geom.getCoordinates()), "<http://www.opengis.net/def/crs/EPSG/0/"+srid!=null?srid.toString():geom.getSRID()+">", WKTDatatype.URI);	
                return pointWrapper.asNodeValue();
            }else {
            	throw new ExprEvalException("WKT does not represent a MultiPoint", null);
            }
            
        } catch (DatatypeFormatException ex) {
            throw new ExprEvalException(ex.getMessage(), ex);
        }
	}

}

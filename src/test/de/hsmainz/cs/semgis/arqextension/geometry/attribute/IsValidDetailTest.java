package de.hsmainz.cs.semgis.arqextension.geometry.attribute;

import io.github.galbiston.geosparql_jena.implementation.GeometryWrapperFactory;
import io.github.galbiston.geosparql_jena.implementation.datatype.WKTDatatype;
import org.apache.jena.sparql.expr.NodeValue;
import org.junit.Test;
import org.locationtech.jts.geom.Coordinate;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class IsValidDetailTest {

    @Test
    public NodeValue TestIsValidDetail(){

        List<Coordinate> coords=new LinkedList<Coordinate>();
        coords.add(new Coordinate(743238, 2967416));
        coords.add(new Coordinate(743238, 2967450));
        coords.add(new Coordinate(743265, 2967450));
        coords.add(new Coordinate(743265.625, 2967416));
        coords.add(new Coordinate(743238, 2967416));
        NodeValue geom2 = GeometryWrapperFactory.createPolygon(coords, WKTDatatype.URI).asNodeValue();

        IsValidDetail instance = new IsValidDetail();
        NodeValue result = instance.exec(geom2);
        System.out.println(result.toString());
        return result;
    }

}
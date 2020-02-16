package de.hsmainz.cs.semgis.arqextension.aggregate;

import java.util.Arrays;

import org.apache.jena.graph.Node;
import org.apache.jena.sparql.engine.binding.Binding;
import org.apache.jena.sparql.expr.Expr;
import org.apache.jena.sparql.expr.ExprList;
import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.expr.aggregate.Accumulator;
import org.apache.jena.sparql.expr.aggregate.AccumulatorExpr;
import org.apache.jena.sparql.expr.aggregate.Aggregator;
import org.apache.jena.sparql.expr.aggregate.AggregatorBase;
import org.apache.jena.sparql.function.FunctionEnv;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.Envelope;
import org.locationtech.jts.geom.Geometry;

import io.github.galbiston.geosparql_jena.implementation.GeometryWrapper;
import io.github.galbiston.geosparql_jena.implementation.GeometryWrapperFactory;
import io.github.galbiston.geosparql_jena.implementation.datatype.WKTDatatype;

public class BoundingBox extends AggregatorBase {

	protected BoundingBox(Expr expr) {
		super("BBOX", true, expr);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Aggregator copy(ExprList exprs) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean equals(Aggregator other, boolean bySyntax) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Accumulator createAccumulator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Node getValueEmpty() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	private static class AccBBOX extends AccumulatorExpr
    {
        // Non-empty case but still can be nothing because the expression may be undefined.
        private NodeValue maxXSoFar = null ;     
        private NodeValue maxYSoFar = null ;
        private NodeValue maxZSoFar = null ;
        private NodeValue minXSoFar = null ;
        private NodeValue minYSoFar = null ;
        private NodeValue minZSoFar = null ;

        public AccBBOX(Expr expr) { super(expr, false) ; }

        static final boolean DEBUG = false ;

        @Override
        public void accumulate(NodeValue nv, Binding binding, FunctionEnv functionEnv)
        { 
        	GeometryWrapper geometry = GeometryWrapper.extract(nv);
            Geometry geo=geometry.getXYGeometry();
        	NodeValue localMaxX=NodeValue.makeDouble(geo.getEnvelopeInternal().getMaxX());
        	NodeValue localMaxY=NodeValue.makeDouble(geo.getEnvelopeInternal().getMaxY());
        	NodeValue localMinX=NodeValue.makeDouble(geo.getEnvelopeInternal().getMinX());
        	NodeValue localMinY=NodeValue.makeDouble(geo.getEnvelopeInternal().getMinY());
        	if(minXSoFar==null) {
        		minXSoFar=localMinX;
        	}
            int x = NodeValue.compareAlways(maxXSoFar, localMaxX) ;
            if ( x < 0 )
                maxXSoFar = localMaxX ;
            x = NodeValue.compareAlways(maxYSoFar, localMaxY) ;
            if ( x < 0 )
                maxYSoFar = localMaxY ;
            x = NodeValue.compareAlways(maxYSoFar, localMinX) ;
            if ( x > 0 )
                minXSoFar = localMinX ;
            x = NodeValue.compareAlways(maxYSoFar, localMinY) ;
            if ( x > 0 )
                minYSoFar = localMinY ;
        }

        @Override
        protected void accumulateError(Binding binding, FunctionEnv functionEnv)
        {}

        @Override
        public NodeValue getAccValue()
        { 
        	Envelope env=new Envelope(new Coordinate(minXSoFar.getDouble(), minYSoFar.getDouble()), new Coordinate(maxXSoFar.getDouble(),maxYSoFar.getDouble()));
        	return GeometryWrapperFactory.createPolygon(env, "http://www.opengis.net/ont/geosparql#wktLiteral").asNodeValue();
        }
    }

}

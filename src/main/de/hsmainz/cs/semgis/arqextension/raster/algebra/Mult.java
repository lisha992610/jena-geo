package de.hsmainz.cs.semgis.arqextension.raster.algebra;

import java.awt.image.BufferedImage;
import java.awt.image.DataBuffer;
import java.awt.image.WritableRaster;
import java.awt.image.renderable.ParameterBlock;
import java.util.LinkedList;
import java.util.List;

import javax.media.jai.JAI;
import javax.media.jai.RenderedOp;

import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase2;
import org.apache.sis.coverage.SampleDimension;
import org.apache.sis.coverage.grid.GridCoverage;
import org.apache.sis.internal.coverage.BufferedGridCoverage;

import io.github.galbiston.geosparql_jena.implementation.datatype.raster.CoverageWrapper;

public class Mult extends FunctionBase2  {

	@Override
	public NodeValue exec(NodeValue v1, NodeValue v2) {
		CoverageWrapper wrapper=CoverageWrapper.extract(v1);
		GridCoverage raster=wrapper.getXYGeometry();
		CoverageWrapper wrapper2=CoverageWrapper.extract(v2);
		GridCoverage raster2=wrapper2.getXYGeometry();
		Integer rd1 = 0, rd2 = 0;
		 ParameterBlock pbSubtracted = new ParameterBlock(); 
	     pbSubtracted.addSource(raster.render(raster.getGridGeometry().getExtent())); 
	     pbSubtracted.addSource(raster2.render(raster2.getGridGeometry().getExtent())); 
	     RenderedOp subtractedImage = JAI.create("multiply",pbSubtracted);
			/*
			 * final GridGeometry grid = new
			 * GridGeometry(raster.getGridGeometry().getExtent(), PixelInCell.CELL_CENTER,
			 * MathTransforms.identity(2),
			 * raster.getGridGeometry().getCoordinateReferenceSystem());
			 * 
			 * final MathTransform1D toUnits = (MathTransform1D) MathTransforms.linear(0.5,
			 * 100);
			 */
			final SampleDimension sd = new SampleDimension.Builder().setName("t")
					.addQuantitative(
							(raster.getSampleDimensions().get(rd1).getName() + " * "
									+ raster2.getSampleDimensions().get(rd2).getName()).toString(),
							raster.getSampleDimensions().get(0).getMeasurementRange().get(),
							raster.getSampleDimensions().get(0).getTransferFunction().get(),
							raster.getSampleDimensions().get(0).getUnits().get())
					.build();
			
			List<SampleDimension>sds=new LinkedList<SampleDimension>();
			sds.add(sd);
			/*
			 * Create the grid coverage, gets its image and set values directly as short
			 * integers.
			 */
			BufferedGridCoverage coverage = new BufferedGridCoverage(raster2.getGridGeometry(),
					sds, DataBuffer.TYPE_SHORT);
			WritableRaster rasterr = ((BufferedImage) coverage.render(null)).getRaster();
			rasterr.setRect(subtractedImage.getSourceImage(0).getData());
			return CoverageWrapper.createGeometry(coverage, wrapper.getSrsURI(), wrapper.getRasterDatatypeURI())
					.asNodeValue();	     
	     
	} 
	

}

import de.hsmainz.cs.semgis.arqextension.geometry.attribute.Area;
import de.hsmainz.cs.semgis.arqextension.geometry.attribute.IsValidDetail;
import io.github.galbiston.geosparql_jena.implementation.GeometryWrapperFactory;
import io.github.galbiston.geosparql_jena.implementation.datatype.WKTDatatype;
import org.apache.jena.sparql.expr.NodeValue;
import org.locationtech.jts.geom.Coordinate;

import java.util.LinkedList;
import java.util.List;
//import static org.junit.Assert.assertEquals;
public class Tutorial_isValid {

    public static void main(String[] args){
        /*
        List<Coordinate> coords=new LinkedList<Coordinate>();
        coords.add(new Coordinate(82139.823, 454220.351, 130.672));
        coords.add(new Coordinate(82140.059, 454219.694, 130.672));
        coords.add(new Coordinate(82140.059, 454219.694, -0.526));
        coords.add(new Coordinate(82139.823, 454220.351,-0.526));
        coords.add(new Coordinate(82139.823, 454220.351, 130.672));
        NodeValue geom2 = GeometryWrapperFactory.createPolygon(coords, WKTDatatype.URI).asNodeValue();

        IsValidDetail instance = new IsValidDetail();
        NodeValue result = instance.exec(geom2);
        System.out.println(result.toString());
        */
        checkValidTest();
        /*
        Area instance=new Area();
        List<Coordinate> coords=new LinkedList<Coordinate>();
        coords.add(new Coordinate(743238, 2967416));
        coords.add(new Coordinate(743238, 2967450));
        coords.add(new Coordinate(743265, 2967450));
        coords.add(new Coordinate(743265.625, 2967416));
        coords.add(new Coordinate(743238, 2967416));
        NodeValue geom2 = GeometryWrapperFactory.createPolygon(coords, WKTDatatype.URI).asNodeValue();
        NodeValue expResult = NodeValue.makeDouble(928.625);
        NodeValue result = instance.exec(geom2);
        //assertEquals(expResult, result);*/
    }

    public static void checkValidTest(){
        String blaze_geometry = "385378.016138475#5819494.72358936#36.5200004577637#385378.143842939#5819493.07912378#36.5200004577637#" +
                "385378.786499313#5819484.76944437#36.5200004577637#385317.300349907#5819479.76855148#36.5200004577637#" +
                "385316.14659639#5819479.67454114#36.5200004577637#385316.343807439#5819477.03355724#36.5200004577637#" +
                "385301.703458502#5819476.19301668#36.5200004577637#385300.950074006#5819486.31327541#36.5200004577637#" +
                "385307.09120739#5819486.77136495#36.5200004577637#385307.460985192#5819481.81075803#36.5200004577637#" +
                "385308.693746104#5819481.90326643#36.5200004577637#385308.45066795#5819485.1666353#36.5200004577637#" +
                "385316.810961469#5819485.78848991#36.5200004577637#385316.490861222#5819489.71917047#36.5200004577637#" +
                "385324.704941389#5819490.38683337#36.5200004577637#385324.526018414#5819492.5952832#36.5200004577637#" +
                "385330.77917557#5819493.10311801#36.5200004577637#385330.959341237#5819490.89586548#36.5200004577637#" +
                "385344.583365563#5819492.00379023#36.5200004577637#385344.403522196#5819494.21195249#36.5200004577637#" +
                "385350.971202767#5819494.74706581#36.5200004577637#385351.151327801#5819492.53767831#36.5200004577637#" +
                "385364.669308965#5819493.63754988#36.5200004577637#385364.489178164#5819495.84663344#36.5200004577637#" +
                "385371.115215387#5819496.38551926#36.5200004577637#385371.294125703#5819494.17645895#36.5200004577637#" +
                "385378.016138475#5819494.72358936#36.5200004577637";
        String noValid_geometry = "82120.313 454255.538 130.672,82120.672 454254.897 130.672,82120.672 454254.897 -0.526," +
                "82120.313 454255.538 -0.526,82120.313 454255.538 130.672";
        String valid_geometry = "82106.765 454221.659 -0.526,82107.759 454227.024 -0.526,82108.99 454233.752 -0.526,82109.056 454234.11 -0.526," +
                "82109.301 454235.449 -0.526,82110.361 454241.194 -0.526,82110.597 454242.533 -0.526,82111.667 454248.272 -0.526,82111.79 454248.937 -0.526," +
                "82111.586 454248.97 -0.526,82112.523 454254.297 -0.526,82112.779 454254.933 -0.526,82113.133 454255.521 -0.526,82113.576 454256.045 -0.526," +
                "82114.095 454256.492 -0.526,82114.679 454256.852 -0.526,82115.312 454257.115 -0.526,82115.979 454257.275 -0.526,82116.679 454257.347 -0.526," +
                "82117.38 454257.306 -0.526,82118.066 454257.153 -0.526,82118.719 454256.891 -0.526,82119.321 454256.528 -0.526,82119.857 454256.073 -0.526," +
                "82120.313 454255.538 -0.526,82120.672 454254.897 -0.526,82120.41 454254.77 -0.526,82139.425 454221.057 -0.526,82139.823 454220.351 -0.526," +
                "82140.059 454219.694 -0.526,82140.175 454219.006 -0.526,82140.168 454218.309 -0.526,82140.038 454217.623 -0.526,82139.79 454216.971 -0.526," +
                "82139.43 454216.373 -0.526,82138.971 454215.848 -0.526,82138.429 454215.447 -0.526,82137.847 454215.151 -0.526,82137.222 454214.961 -0.526," +
                "82136.574 454214.882 -0.526,82135.922 454214.916 -0.526,82134.585 454215.166 -0.526,82132.371 454215.483 -0.526,82131.031 454215.731 -0.526," +
                "82130.68 454215.789 -0.526,82125.286 454216.795 -0.526,82123.94 454217.036 -0.526,82118.21 454218.086 -0.526,82116.869 454218.345 -0.526," +
                "82111.134 454219.407 -0.526,82109.79 454219.651 -0.526,82107.919 454219.98 -0.526,82106.507 454220.236 -0.526,82106.765 454221.659 -0.526";

        System.out.println(checkValid_BLAZE(blaze_geometry));
        System.out.println(checkValid_POSTGIS(valid_geometry));
        System.out.println(checkValid_POSTGIS(noValid_geometry));
    }
    public static String checkValid_BLAZE(String geometry){
        String[] pointXYZList = geometry.split("#");
        List<Coordinate> coords=new LinkedList<Coordinate>();
        if (pointXYZList.length % 3 == 0 ){
            // 3d coordinates
            for (int i = 0; i < pointXYZList.length; i = i+3){
                coords.add(new Coordinate(Double.valueOf(pointXYZList[i]), Double.valueOf(pointXYZList[i+1]) , Double.valueOf(pointXYZList[i+2])));
            }
            //Double.valueOf(arrOfStr[(k*3)-1]));
        }else{
            // invalid coordinates
        }
        NodeValue geom2 = GeometryWrapperFactory.createPolygon(coords, WKTDatatype.URI).asNodeValue();

        IsValidDetail instance = new IsValidDetail();
        NodeValue result = instance.exec(geom2);

        return result.toString();
    }
    public static String checkValid_POSTGIS(String st_geometry){
        String[] pointXYZList = st_geometry.split(",");
        List<Coordinate> coords=new LinkedList<Coordinate>();

        for (int i = 0; i < pointXYZList.length; ++i){
            String[] pointXYZ = pointXYZList[i].split(" ");
            coords.add(new Coordinate(Double.valueOf(pointXYZ[0]), Double.valueOf(pointXYZ[1]) , Double.valueOf(pointXYZ[2])));
        }

        NodeValue geom2 = GeometryWrapperFactory.createPolygon(coords, WKTDatatype.URI).asNodeValue();

        IsValidDetail instance = new IsValidDetail();
        NodeValue result = instance.exec(geom2);
        return result.toString();
    }
}

package com.pawin.hospital.bo;

import org.osgeo.proj4j.CRSFactory;
import org.osgeo.proj4j.CoordinateReferenceSystem;
import org.osgeo.proj4j.CoordinateTransform;
import org.osgeo.proj4j.CoordinateTransformFactory;
import org.osgeo.proj4j.ProjCoordinate;
import org.osgeo.proj4j.CRSFactory;
import org.osgeo.proj4j.CoordinateReferenceSystem;
import org.osgeo.proj4j.CoordinateTransform;
import org.osgeo.proj4j.CoordinateTransformFactory;
import org.osgeo.proj4j.ProjCoordinate;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TransCoord {
	
	public ProjCoordinate transform(String X, String Y) {

	    //parse to Double
	    Double dblLon = Double.parseDouble(X);
	    Double dblLat = Double.parseDouble(Y);
	    CoordinateTransformFactory ctFactory = new CoordinateTransformFactory();
	    CRSFactory factory = new CRSFactory();
	    CoordinateReferenceSystem grs80 = factory.createFromName("EPSG:5179"); 
	    CoordinateReferenceSystem wgs84 = factory.createFromName("EPSG:4326");
	    CoordinateTransform trans = ctFactory.createTransform(grs80, wgs84);
		
	    ProjCoordinate beforeCoord = new ProjCoordinate(dblLon, dblLat);
	    ProjCoordinate afterCoord = new ProjCoordinate();
	    
	    //변환된 좌표 
	    System.out.println(afterCoord.x + "," + afterCoord.y);
	    
	    return trans.transform(beforeCoord, afterCoord);
	  }
	  
}

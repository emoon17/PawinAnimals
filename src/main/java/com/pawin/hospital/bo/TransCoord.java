package com.pawin.hospital.bo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.osgeo.proj4j.BasicCoordinateTransform;
import org.osgeo.proj4j.CRSFactory;
import org.osgeo.proj4j.CoordinateReferenceSystem;
import org.osgeo.proj4j.ProjCoordinate;
import org.springframework.stereotype.Service;

@Service	
public class TransCoord {
	
	public List<Map<Object, Object>> transform(String X, String Y) {
		List<Map<Object, Object>> transCoordList = new ArrayList<>();
		Map<Object, Object> transCoordMap = new HashMap<>();
		
		double x = Double.parseDouble(X);
		double y = Double.parseDouble(Y);
		CRSFactory factory = new CRSFactory();
		CoordinateReferenceSystem srcCrs = factory.createFromName("EPSG:5181");
		CoordinateReferenceSystem dstCrs = factory.createFromName("EPSG:4326");
		BasicCoordinateTransform transform = new BasicCoordinateTransform(srcCrs, dstCrs);
		ProjCoordinate srcCoord = new ProjCoordinate(x, y);
		ProjCoordinate dstCoord = new ProjCoordinate();
		transform.transform(srcCoord, dstCoord); // 좌표 변환
		
		
		transCoordMap.put("x", dstCoord.x);
		transCoordMap.put("y", dstCoord.y);
		transCoordList.add(transCoordMap);
		
		
	    return transCoordList;
	  }
	  
}

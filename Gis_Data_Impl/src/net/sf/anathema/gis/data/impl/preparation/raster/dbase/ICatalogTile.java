package net.sf.anathema.gis.data.impl.preparation.raster.dbase;

import com.vividsolutions.jts.geom.Envelope;

public interface ICatalogTile {

  public String getRelativeFileName();
  
  public Envelope getEnvelope();
}
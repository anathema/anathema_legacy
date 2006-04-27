package net.sf.anathema.gis.data.impl.preparation.raster;

import net.sf.anathema.gis.data.impl.preparation.raster.dbase.ICatalogTile;

import com.vividsolutions.jts.geom.Envelope;

public class CatalogTile implements ICatalogTile {
  
  private final String fileName;
  private final Envelope envelope;

  public CatalogTile(String fileName, Envelope envelope) {
    this.fileName = fileName;
    this.envelope = envelope;
  }

  public String getRelativeFileName() {
    return fileName;
  }

  public Envelope getEnvelope() {
    return envelope;
  }
}
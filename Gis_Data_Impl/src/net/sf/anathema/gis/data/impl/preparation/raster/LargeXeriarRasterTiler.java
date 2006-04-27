package net.sf.anathema.gis.data.impl.preparation.raster;

import gis.gistermserver.raster.tiler.RasterTileInfo;
import gis.gistermserver.raster.tiler.RasterTileSplitter;

import java.awt.Dimension;

import net.sf.anathema.gis.data.IGisDataDirectory;

import com.vividsolutions.jts.geom.Envelope;

public class LargeXeriarRasterTiler extends AbstractRasterTiler {

  public LargeXeriarRasterTiler(IGisDataDirectory gisDataDirectory) {
    super(gisDataDirectory);
  }

  private static final Envelope XERIAR_ENVELOPE = new Envelope(2, 15362, 282, 9498);
  private static final Dimension LARGE_XERIAR_SIZE = new Dimension(7680, 4608);

  @Override
  protected String getNameBase() {
    return "Xeriar";
  }

  @Override
  protected RasterTileInfo[] createTileInfos() {
    Envelope envelope = XERIAR_ENVELOPE;
    Dimension imageSize = LARGE_XERIAR_SIZE;
    double suggestedTileWorldWidth = 1500;
    double suggestedTileWorldHeight = 1500;
    return RasterTileSplitter.createTileInfos(envelope, imageSize, suggestedTileWorldWidth, suggestedTileWorldHeight);
  }
}
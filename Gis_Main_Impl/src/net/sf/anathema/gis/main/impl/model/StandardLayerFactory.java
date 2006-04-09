package net.sf.anathema.gis.main.impl.model;

import gis.gisterm.gcore.GenericLayer;
import gis.gisterm.gcore.WorldBox;
import gis.gisterm.map.RasterCatalogLayer;
import gis.gisterm.map.layer.datasource.RasterCatalogLayerDataProvider;

import java.io.File;

import net.sf.anathema.gis.main.model.layerfactory.IStandardLayerFactory;
import net.sf.anathema.gis.main.model.layerfactory.LayerCreationException;
import de.disy.gis.gisterm.image.FileWorldImageTile;
import de.disy.gis.gisterm.image.IWorldImageTile;
import de.disy.gis.gisterm.imagecatalog.ImageCatalog;
import de.disy.gis.gisterm.imagecatalog.ImageCatalogFactory;
import de.disy.gis.gisterm.imagecatalog.ImageCatalogQuery;
import de.disy.gis.gisterm.map.scale.IScaleRange;

public class StandardLayerFactory implements IStandardLayerFactory {

  public GenericLayer createXeriarRasterLayer() throws LayerCreationException {
    //return createPngXeria();
    return createJpegXeria();
  }

  private GenericLayer createPngXeria() throws LayerCreationException {
    try {
      return ImageCatalogFactory.createUnmonitoredImageCatalogLayer(new File("../Anathema/gisdata/Xeria PNG/"));
    }
    catch (Exception e) {
      throw new LayerCreationException(e);
    }
  }

  private GenericLayer createJpegXeria() throws LayerCreationException {
    try {
      RasterCatalogLayer catalogLayer = new RasterCatalogLayer();
      RasterCatalogLayerDataProvider rasterDataProvider = catalogLayer.getRasterCatalogLayerDataProvider();
      ImageCatalogQuery query = new ImageCatalogQuery(new File("../Anathema/gisdata/Creation Xeria.icat").toURL()
          .toExternalForm(), IScaleRange.MIN_VALUE, IScaleRange.MAX_VALUE);
      File xeriaFile = new File("../Anathema/gisdata/Xeria/creation-large-1-7.jpg"); //$NON-NLS-1$
      FileWorldImageTile imageTile = new FileWorldImageTile(xeriaFile, new WorldBox(1.52, 7024.22, 11708.55, 0));
      IWorldImageTile[] worldTiles = new IWorldImageTile[] { imageTile };
      query.setCatalog(new ImageCatalog(worldTiles));
      rasterDataProvider.setQuery(query);
      catalogLayer.setName("Xeriar");
      return catalogLayer;
    }
    catch (Exception e) {
      throw new LayerCreationException("An error occured loading the Xeriar raster layer.", e); //$NON-NLS-1$
    }
  }
}
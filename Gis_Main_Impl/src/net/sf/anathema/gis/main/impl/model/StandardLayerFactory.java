package net.sf.anathema.gis.main.impl.model;

import gis.gisterm.gcore.GenericLayer;
import gis.gisterm.map.RasterCatalogLayer;
import gis.gisterm.map.layer.datasource.RasterCatalogLayerDataProvider;

import java.io.File;

import javax.swing.JPopupMenu;

import net.disy.commons.core.progress.NullProgressMonitor;
import net.sf.anathema.gis.data.IGisDataDirectory;
import net.sf.anathema.gis.main.model.layerfactory.IStandardLayerFactory;
import net.sf.anathema.gis.main.model.layerfactory.LayerCreationException;
import net.sf.anathema.lib.logging.Logger;
import de.disy.gis.gisterm.imagecatalog.ImageCatalogQuery;
import de.disy.gis.gisterm.imagecatalog.layer.IImageCatalogLayerCreationStrategy;
import de.disy.gis.gisterm.imagecatalog.layer.IImageCatalogProperties;
import de.disy.gis.gisterm.imagecatalog.layer.ImageCatalogLayerCreationStrategy;
import de.disy.gis.gisterm.map.layer.edit.ILayerGraphicsEditStrategy;
import de.disy.gis.gisterm.map.layer.sketch.AbstractSketchLayer;
import de.disy.gis.gisterm.map.scale.IScaleRange;
import de.disy.gis.gisterm.map.theme.ITheme;
import de.disy.gis.gisterm.pro.map.layer.ILayerPopupFactoryExtension;
import de.disy.gis.gisterm.pro.map.layer.popup.IMapLayerPopupMenuContext;
import de.disy.gisterm.pro.sketchlayer.edit.SketchLayerGraphicsEditStrategy;

public class StandardLayerFactory implements IStandardLayerFactory {

  private static final Logger logger = Logger.getLogger(StandardLayerFactory.class);
  private final IGisDataDirectory gisDataDirectory;

  public StandardLayerFactory(IGisDataDirectory gisDataDirectory) {
    this.gisDataDirectory = gisDataDirectory;
  }

  private final ILayerPopupFactoryExtension rasterLayerPopupMenuFactory = new ILayerPopupFactoryExtension() {
    public JPopupMenu createPopupMenu(ITheme theme, IMapLayerPopupMenuContext menuContext) {
      return null;
    }
  };
  private final ILayerPopupFactoryExtension sketchLayerPopupMenuFactory = new ILayerPopupFactoryExtension() {
    public JPopupMenu createPopupMenu(ITheme theme, IMapLayerPopupMenuContext menuContext) {
      return null;
    }
  };

  public GenericLayer createXeriarRasterLayer() throws LayerCreationException {
    if (!gisDataDirectory.canRead()) {
      return null;
    }
    try {
      File xeriarDirectory = new File(gisDataDirectory.getDirectory(), "raster/Xeriar00/"); //$NON-NLS-1$
      if (!xeriarDirectory.exists()) {
        logger.warn("No xeriar layer found at " + xeriarDirectory.getCanonicalPath()); //$NON-NLS-1$
        return null;
      }
      File catalogFile = new File(xeriarDirectory, "catalog.dbf"); //$NON-NLS-1$
      return createXeriar(catalogFile);
    }
    catch (Exception e) {
      throw new LayerCreationException("An error occured loading the Xeriar raster layer.", e); //$NON-NLS-1$
    }
  }

  private GenericLayer createXeriar(final File dbfFile) throws Exception {
    RasterCatalogLayer catalogLayer = new RasterCatalogLayer() {
      @Override
      public void legendLayerChanged() {
        if (getLayerPanel() != null) {
          getLayerPanel().setPopupMenuFactory(rasterLayerPopupMenuFactory);
        }
        super.legendLayerChanged();
      }
    };
    RasterCatalogLayerDataProvider rasterDataProvider = catalogLayer.getRasterCatalogLayerDataProvider();
    IImageCatalogProperties properties = new IImageCatalogProperties() {
      public String getAbsoluteImagePath() {
        return dbfFile.getAbsolutePath();
      }

      public String getCatalogName() {
        return "Creation Xeriar";
      }

      public double getInitialMaxScale() {
        return IScaleRange.MAX_VALUE;
      }

      public double getInitialMinScale() {
        return IScaleRange.MIN_VALUE;
      }

      public String getMmlSaveString() {
        throw new UnsupportedOperationException();
      }
    };
    IImageCatalogLayerCreationStrategy layerCreationStrategy = new ImageCatalogLayerCreationStrategy(properties);
    layerCreationStrategy.initialize(NullProgressMonitor.getInstance());
    ImageCatalogQuery query = new ImageCatalogQuery(layerCreationStrategy);
    rasterDataProvider.setQuery(query);
    catalogLayer.setName(properties.getCatalogName());
    return catalogLayer;
  }

  public GenericLayer createSketchLayer() {
    AbstractSketchLayer sketchLayer = new AbstractSketchLayer() {
      @Override
      protected ILayerGraphicsEditStrategy createGraphicsEditStrategy() {
        return new SketchLayerGraphicsEditStrategy(getGraphicsObjectList());
      }

      @Override
      public void legendLayerChanged() {
        if (getLayerPanel() != null) {
          getLayerPanel().setPopupMenuFactory(sketchLayerPopupMenuFactory);
        }
        super.legendLayerChanged();
      }
    };
    return sketchLayer;
  }
}
package net.sf.anathema.gis.main.impl.model;

import gis.gisterm.gcore.GenericLayer;
import gis.gisterm.map.RasterCatalogLayer;
import gis.gisterm.map.layer.datasource.RasterCatalogLayerDataProvider;

import java.awt.Component;
import java.io.File;

import net.disy.commons.core.progress.NullProgressMonitor;
import net.disy.commons.swing.filechooser.SmartFileChooser;
import net.disy.commons.swing.filechooser.filefilter.ExtensionFileFilter;
import net.disy.commons.swing.filechooser.result.FileChooserOpenResult;
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
import de.disy.gis.gisterm.map.layer.sketch.ISketchPropertiesFactory;
import de.disy.gis.gisterm.map.layer.sketch.ProtoTypeSketchPropertiesFactory;
import de.disy.gis.gisterm.map.layer.sketch.SketchObjectFactory;
import de.disy.gis.gisterm.map.scale.IScaleRange;
import de.disy.gisterm.pro.sketchlayer.edit.SketchLayerGraphicsEditStrategy;
import de.disy.lib.gui.filechooser.IFileProvider;
import de.disy.lib.gui.filechooser.configuration.OneFileFilterOpenConfiguration;

public class StandardLayerFactory implements IStandardLayerFactory {

  private static final Logger logger = Logger.getLogger(StandardLayerFactory.class);
  private final IGisDataDirectory gisDataDirectory;

  public StandardLayerFactory(IGisDataDirectory gisDataDirectory) {
    this.gisDataDirectory = gisDataDirectory;
  }

  @Override
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
    RasterCatalogLayer catalogLayer = new RasterCatalogLayer();
    RasterCatalogLayerDataProvider rasterDataProvider = catalogLayer.getRasterCatalogLayerDataProvider();
    IImageCatalogProperties properties = new IImageCatalogProperties() {
      @Override
      public String getAbsoluteImagePath() {
        return dbfFile.getAbsolutePath();
      }

      @Override
      public String getCatalogName() {
        return "Creation Xeriar";
      }

      @Override
      public double getInitialMaxScale() {
        return IScaleRange.MAX_VALUE;
      }

      @Override
      public double getInitialMinScale() {
        return IScaleRange.MIN_VALUE;
      }

      @Override
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

  @Override
  public GenericLayer createSketchLayer() {
    AbstractSketchLayer sketchLayer = new AbstractSketchLayer() {
      {
        IFileProvider fileProvider = new IFileProvider() {
          @Override
          public File getFile(Component parent) {
            OneFileFilterOpenConfiguration configuration = new OneFileFilterOpenConfiguration(
                false,
                ExtensionFileFilter.GIF_FILE_FILTER);
            SmartFileChooser fileChooser = SmartFileChooser.getInstance();
            FileChooserOpenResult result = fileChooser.performOpenFileChooser(parent, configuration);
            if (result.isCanceled()) {
              return null;
            }
            return result.getSelectedFile();
          }
        };
        ISketchPropertiesFactory propertiesFactory = new ProtoTypeSketchPropertiesFactory(fileProvider);
        setSketchObjectFactory(new SketchObjectFactory(propertiesFactory));
      }

      @Override
      protected ILayerGraphicsEditStrategy createGraphicsEditStrategy() {
        return new SketchLayerGraphicsEditStrategy(getGraphicsObjectList());
      }
    };
    return sketchLayer;
  }
}
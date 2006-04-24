package net.sf.anathema.gis.main.impl.model;

import gis.gisterm.gcore.GenericLayer;
import gis.gisterm.map.RasterCatalogLayer;
import gis.gisterm.map.layer.datasource.RasterCatalogLayerDataProvider;

import java.io.File;

import javax.swing.JPopupMenu;

import net.disy.commons.core.progress.NullProgressMonitor;
import net.sf.anathema.gis.main.model.layerfactory.IStandardLayerFactory;
import net.sf.anathema.gis.main.model.layerfactory.LayerCreationException;
import de.disy.gis.gisterm.imagecatalog.ImageCatalogQuery;
import de.disy.gis.gisterm.imagecatalog.layer.IImageCatalogLayerCreationStrategy;
import de.disy.gis.gisterm.imagecatalog.layer.IImageCatalogProperties;
import de.disy.gis.gisterm.imagecatalog.layer.ImageCatalogLayerCreationStrategy;
import de.disy.gis.gisterm.map.layer.edit.ILayerGraphicsEditStrategy;
import de.disy.gis.gisterm.map.layer.sketch.SketchLayer;
import de.disy.gis.gisterm.map.scale.IScaleRange;
import de.disy.gis.gisterm.map.theme.ITheme;
import de.disy.gis.gisterm.pro.map.layer.ILayerPopupFactoryExtension;
import de.disy.gis.gisterm.pro.map.layer.popup.IMapLayerPopupMenuContext;
import de.disy.gisterm.pro.sketchlayer.edit.SketchLayerGraphicsEditStrategy;

public class StandardLayerFactory implements IStandardLayerFactory {
  private final File repositoryFolder;
  private final ILayerPopupFactoryExtension rasterLayerPopupMenuFactory = new ILayerPopupFactoryExtension() {
    public JPopupMenu createPopupMenu(ITheme theme, IMapLayerPopupMenuContext menuContext) {
      return null;
    }
  };

  public StandardLayerFactory(File repositoryFolder) {
    this.repositoryFolder = repositoryFolder;
    String gisDataPath = new File("../Anathema/gisdata/").getAbsolutePath(); //$NON-NLS-1$
    System.setProperty("ANATHEMA_GISDATA", gisDataPath); //$NON-NLS-1$
  }

  public GenericLayer createXeriarRasterLayer() throws LayerCreationException {
    try {
      File xeriarFile = new File(repositoryFolder, "gisdata/Xeriar.dbf"); //$NON-NLS-1$
      return createXeria(xeriarFile);
    }
    catch (Exception e) {
      throw new LayerCreationException("An error occured loading the Xeriar raster layer.", e); //$NON-NLS-1$
    }
  }

  private GenericLayer createXeria(final File dbfFile) throws Exception {
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
    return new SketchLayer() {
      @Override
      protected void initEditStrategy() {
        ILayerGraphicsEditStrategy editStrategy = new SketchLayerGraphicsEditStrategy(getGraphicsObjectList());
        setGraphicsEditStrategy(editStrategy);
      }
    };
  }
}
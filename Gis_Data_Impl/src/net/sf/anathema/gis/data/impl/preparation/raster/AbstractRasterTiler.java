package net.sf.anathema.gis.data.impl.preparation.raster;

import gis.gistermserver.raster.tiler.IRasterTile;
import gis.gistermserver.raster.tiler.RasterTileInfo;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import net.disy.commons.core.progress.IProgressMonitor;
import net.sf.anathema.gis.data.IGisDataDirectory;
import net.sf.anathema.gis.data.impl.preparation.raster.dbase.ICatalogTile;
import net.sf.anathema.gis.data.impl.preparation.raster.dbase.RasterCatalogToDbaseExporter;
import net.sf.anathema.gis.data.preparation.raster.IRasterTiler;

import org.apache.commons.io.FileUtils;

import de.disy.gis.gisterm.gfx.util.WorldBoxUtilities;
import de.disy.lib.image.ImageEncoder;
import de.disy.lib.io.IOUtilities;
import de.disy.tools.imaging.IImageRepresentation;
import de.disy.tools.imaging.provider.FileImageRepresentationProvider;

public abstract class AbstractRasterTiler implements IRasterTiler {

  private static final String CATALOG_FILENAME = "catalog.dbf"; //$NON-NLS-1$
  private static final String TILES_DIRECTORY_PART = "tiles/"; //$NON-NLS-1$
  private final RasterCatalogToDbaseExporter dbaeExporter = new RasterCatalogToDbaseExporter();
  private final IGisDataDirectory gisDataDirectory;
  
  public AbstractRasterTiler(IGisDataDirectory gisDataDirectory) {
    this.gisDataDirectory = gisDataDirectory;
  }

  // todo vom (27.04.2006) (sieroux): Themenname als Zusatzdatei
  // todo vom (27.04.2006) (sieroux): Index-Raufzählen, wenn die Datei schon existiert
  @Override
  public final void tileRasterTheme(File imageFile, IProgressMonitor monitor) throws IOException {
    if (!gisDataDirectory.canWrite()) {
      throw new IOException("Gisdata directory cannot be written."); //$NON-NLS-1$
    }
    monitor.beginTask("Preparing catalog creation", IProgressMonitor.UNKNOWN);
    try {
      String name = getNameBase() + "00";
      RasterTileInfo[] infos = createTileInfos();
      File catalogDirectory = prepareCatalogDirectory(name);
      File tilesDirectory = prepareTilesDirectory(catalogDirectory);
      monitor.beginTask("Creating image catalog " + name, infos.length);
      ICatalogTile[] catalog = processTiling(monitor, imageFile, infos, tilesDirectory);
      File dbaseFile = new File(catalogDirectory, CATALOG_FILENAME);
      dbaeExporter.writeDbaseFile(dbaseFile, catalog);
    }
    finally {
      monitor.done();
    }
  }

  protected abstract String getNameBase();

  protected abstract RasterTileInfo[] createTileInfos();

  private ICatalogTile[] processTiling(
      IProgressMonitor monitor,
      File imageFile,
      RasterTileInfo[] infos,
      File tilesDirectory) throws IOException, FileNotFoundException {
    IImageRepresentation overallImageRepresentation = createImageRepresentation(imageFile);
    List<ICatalogTile> catalogTiles = new ArrayList<ICatalogTile>();
    for (int index = 0; index < infos.length; index++) {
      RasterTileInfo tileInfo = infos[index];
      IRasterTile rasterTile = tileInfo.createTile(overallImageRepresentation);
      OutputStream outputStream = null;
      try {
        String fileName = "tile" + index + ".jpg"; //$NON-NLS-1$ //$NON-NLS-2$
        BufferedImage image = rasterTile.getTileImageRepresentation().getAsBufferedImage();
        File tileFile = new File(tilesDirectory, fileName);
        outputStream = new FileOutputStream(tileFile);
        ImageEncoder.createJPEG(image, outputStream);
        catalogTiles.add(createCatalogTile(rasterTile, fileName));
        monitor.worked(1);
      }
      finally {
        IOUtilities.close(outputStream);
      }
    }
    return catalogTiles.toArray(new ICatalogTile[catalogTiles.size()]);
  }

  private static CatalogTile createCatalogTile(IRasterTile rasterTile, String fileName) {
    String dbaseFileName = "./" + TILES_DIRECTORY_PART + fileName; //$NON-NLS-1$
    return new CatalogTile(dbaseFileName, WorldBoxUtilities.asEnvelope(rasterTile.getWorldBox()));
  }

  private File prepareCatalogDirectory(String name) throws IOException {
    File catalogDirectory = new File(gisDataDirectory.getDirectory(), "raster/" + name + "/"); //$NON-NLS-1$ //$NON-NLS-2$
    FileUtils.forceMkdir(catalogDirectory);
    return catalogDirectory;
  }

  private File prepareTilesDirectory(File catalogDirectory) throws IOException {
    File targetDirectory = new File(catalogDirectory, TILES_DIRECTORY_PART);
    FileUtils.forceMkdir(targetDirectory);
    return targetDirectory;
  }

  private static IImageRepresentation createImageRepresentation(File imageFile) throws IOException {
    return new FileImageRepresentationProvider(imageFile).getImageRepresentation();
  }
}
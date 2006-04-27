package net.sf.anathema.gis.data.preparation.raster;

import java.io.File;
import java.io.IOException;

import net.disy.commons.core.progress.IProgressMonitor;

public interface IRasterTiler {

  public void tileRasterTheme(File imageFile, IProgressMonitor monitor) throws IOException;
}
package net.sf.anathema.gis.data.preparation.raster;

import java.awt.Component;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import net.disy.commons.core.exception.UnreachableCodeReachedException;
import net.disy.commons.core.message.IMessage;
import net.disy.commons.core.message.Message;
import net.disy.commons.core.progress.IProgressMonitor;
import net.disy.commons.core.progress.IRunnableWithProgress;
import net.disy.commons.swing.action.SmartAction;
import net.disy.commons.swing.dialog.progress.ProgressMonitorDialog;
import net.disy.commons.swing.filechooser.SmartFileChooser;
import net.disy.commons.swing.filechooser.configuration.IFileChooserOpenConfiguration;
import net.disy.commons.swing.filechooser.filefilter.ExtensionFileFilter;
import net.disy.commons.swing.filechooser.result.FileChooserOpenResult;
import de.disy.cadenza.core.message.gui.SwingMessageIndicator;
import de.disy.lib.gui.filechooser.configuration.OneFileFilterOpenConfiguration;

public class RasterTileAction extends SmartAction {

  private final IRasterTiler rasterTiler;

  public RasterTileAction(String name, IRasterTiler rasterTiler) {
    super(name);
    this.rasterTiler = rasterTiler;
  }

  @Override
  protected void execute(Component parentComponent) {
    ExtensionFileFilter fileFilter = ExtensionFileFilter.TIFF_FILE_FILTER;
    IFileChooserOpenConfiguration configuration = new OneFileFilterOpenConfiguration(true, fileFilter);
    SmartFileChooser fileChoosing = SmartFileChooser.getInstance();
    final FileChooserOpenResult result = fileChoosing.performOpenFileChooser(parentComponent, configuration);
    if (result.isCanceled()) {
      return;
    }
    ProgressMonitorDialog progressMonitorDialog = new ProgressMonitorDialog(parentComponent, "Raster tiling");
    try {
      progressMonitorDialog.run(false, new IRunnableWithProgress() {
        @Override
        public void run(IProgressMonitor monitor) throws InterruptedException, InvocationTargetException {
          try {
            rasterTiler.tileRasterTheme(result.getSelectedFile(), monitor);
          }
          catch (IOException e) {
            throw new InvocationTargetException(e);
          }
        }
      });
    }
    catch (InterruptedException e) {
      throw new UnreachableCodeReachedException();
    }
    catch (InvocationTargetException e) {
      IMessage message = new Message("Error occured while tiling.", e.getCause());
      SwingMessageIndicator.showMessage(parentComponent, message);
    }
  }
}
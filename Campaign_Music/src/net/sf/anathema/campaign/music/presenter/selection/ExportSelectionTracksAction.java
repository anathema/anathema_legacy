package net.sf.anathema.campaign.music.presenter.selection;

import net.sf.anathema.campaign.music.export.PlayListExporter;
import net.sf.anathema.campaign.music.model.selection.IMusicSelectionModel;
import net.sf.anathema.framework.message.MessageUtilities;
import net.sf.anathema.framework.presenter.resources.PlatformUI;
import net.sf.anathema.lib.control.IChangeListener;
import net.sf.anathema.lib.gui.action.SmartAction;
import net.sf.anathema.lib.gui.file.FileChoosingUtilities;
import net.sf.anathema.lib.message.Message;
import net.sf.anathema.lib.resources.IResources;
import org.apache.commons.io.IOUtils;

import java.awt.Component;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class ExportSelectionTracksAction extends SmartAction {

  private final IMusicSelectionModel selectionModel;
  private final IResources resources;

  public ExportSelectionTracksAction(IResources resources, IMusicSelectionModel selectionModel) {
    super(new PlatformUI(resources).getSaveIcon());
    this.resources = resources;
    this.selectionModel = selectionModel;
    setToolTipText(resources.getString("Music.Actions.ExportList.Tooltip")); //$NON-NLS-1$
    selectionModel.addCurrentSelectionChangeListener(new IChangeListener() {
      @Override
      public void changeOccurred() {
        updateEnabled();
      }
    });
    updateEnabled();
  }

  private void updateEnabled() {
    setEnabled(selectionModel.getCurrentSelection().getContent().length > 0);
  }

  @Override
  protected void execute(Component parentComponent) {
    File file = FileChoosingUtilities.selectSaveFile(
        parentComponent,
        resources.getString("Music.Actions.ExportList.DefaultFileName")); //$NON-NLS-1$
    if (file == null) {
      return;
    }
    Writer writer = null;
    try {
      writer = new FileWriter(file);
      new PlayListExporter().export(writer, selectionModel.getCurrentSelection().getContent());
    }
    catch (IOException e) {
      Message message = new Message(resources.getString("Errors.MusicDatabase.ExportPlaylist"), e); //$NON-NLS-1$
      MessageUtilities.indicateMessage(getClass(), parentComponent, message);
    }
    finally {
      IOUtils.closeQuietly(writer);
    }
  }
}
package net.sf.anathema.campaign.music.presenter.selection;

import java.awt.Component;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

import net.disy.commons.core.io.IOUtilities;
import net.disy.commons.swing.action.SmartAction;
import net.disy.commons.swing.message.Message;
import net.sf.anathema.campaign.music.export.PlayListExporter;
import net.sf.anathema.campaign.music.model.selection.IMusicSelectionModel;
import net.sf.anathema.framework.message.MessageUtilities;
import net.sf.anathema.lib.control.change.IChangeListener;
import net.sf.anathema.lib.gui.file.FileChoosingUtilities;
import net.sf.anathema.lib.resources.IResources;

public class ExportSelectionTracksAction extends SmartAction {

  private final IMusicSelectionModel selectionModel;
  private final IResources resources;

  public ExportSelectionTracksAction(IResources resources, IMusicSelectionModel selectionModel) {
    super(resources.getImageIcon("tools/Save24.gif")); //$NON-NLS-1$
    this.resources = resources;
    this.selectionModel = selectionModel;
    setToolTipText(resources.getString("Music.Actions.ExportList.Tooltip")); //$NON-NLS-1$
    selectionModel.addCurrentSelectionChangeListener(new IChangeListener() {
      public void changeOccured() {
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
      IOUtilities.close(writer);
    }
  }
}
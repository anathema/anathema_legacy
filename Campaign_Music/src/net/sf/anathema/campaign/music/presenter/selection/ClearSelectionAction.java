package net.sf.anathema.campaign.music.presenter.selection;

import java.awt.Component;

import net.disy.commons.swing.action.SmartAction;
import net.sf.anathema.campaign.music.model.selection.IMusicSelectionModel;
import net.sf.anathema.framework.presenter.resources.BasicUi;
import net.sf.anathema.lib.control.IChangeListener;
import net.sf.anathema.lib.resources.IResources;

public class ClearSelectionAction extends SmartAction {

  private final IMusicSelectionModel selectionModel;

  public ClearSelectionAction(IResources resources, final IMusicSelectionModel selectionModel) {
    super(new BasicUi(resources).getMediumClearIcon());
    setToolTipText(resources.getString("Music.Actions.ClearSelection.Tooltip")); //$NON-NLS-1$
    this.selectionModel = selectionModel;
    selectionModel.addCurrentSelectionChangeListener(new IChangeListener() {
      public void changeOccured() {
        setEnabled(selectionModel.getCurrentSelection().getContent().length != 0);
      }
    });
    setEnabled(selectionModel.getCurrentSelection().getContent().length != 0);
  }

  @Override
  protected void execute(Component parentComponent) {
    selectionModel.clearCurrentSelection();
  }
}
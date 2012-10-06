package net.sf.anathema.campaign.music.presenter.selection;

import net.sf.anathema.campaign.music.model.selection.IMusicSelectionModel;
import net.sf.anathema.framework.presenter.resources.BasicUi;
import net.sf.anathema.lib.control.IChangeListener;
import net.sf.anathema.lib.gui.action.SmartAction;
import net.sf.anathema.lib.resources.IResources;

import java.awt.Component;

public class ClearSelectionAction extends SmartAction {

  private final IMusicSelectionModel selectionModel;

  public ClearSelectionAction(IResources resources, final IMusicSelectionModel selectionModel) {
    super(new BasicUi(resources).getClearIcon());
    setToolTipText(resources.getString("Music.Actions.ClearSelection.Tooltip")); //$NON-NLS-1$
    this.selectionModel = selectionModel;
    selectionModel.addCurrentSelectionChangeListener(new IChangeListener() {
      @Override
      public void changeOccurred() {
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
package net.sf.anathema.campaign.music.presenter.selection;

import net.sf.anathema.campaign.music.model.selection.IMusicSelection;
import net.sf.anathema.campaign.music.model.selection.IMusicSelectionModel;
import net.sf.anathema.framework.presenter.resources.BasicUi;
import net.sf.anathema.lib.gui.action.SmartAction;
import net.sf.anathema.lib.gui.list.actionview.IActionAddableListView;
import net.sf.anathema.lib.resources.IResources;

import java.awt.Component;

public class RetrieveSelectionAction extends SmartAction {

  private final IActionAddableListView<IMusicSelection> selectionListView;
  private final IMusicSelectionModel selectionModel;

  public RetrieveSelectionAction(
      IResources resources,
      final IActionAddableListView<IMusicSelection> selectionListView,
      IMusicSelectionModel selectionModel) {
    super(new BasicUi(resources).getRightArrowIcon());
    this.selectionListView = selectionListView;
    this.selectionModel = selectionModel;
    setToolTipText(resources.getString("Music.Actions.RetrieveSelection.Tooltip")); //$NON-NLS-1$
    selectionListView.addListSelectionListener(new Runnable() {
      @Override
      public void run() {
        setEnabled(selectionListView.getSelectedItems().length != 0);
      }
    });
    setEnabled(selectionListView.getSelectedItems().length != 0);
  }

  @Override
  protected void execute(Component parentComponent) {
    IMusicSelection selectedSelection = selectionListView.getSelectedItems()[0];
    selectionModel.addToCurrentSelection(selectedSelection.getContent());
  }
}
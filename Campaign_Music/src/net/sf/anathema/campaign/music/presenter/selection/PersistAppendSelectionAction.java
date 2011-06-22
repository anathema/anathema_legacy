package net.sf.anathema.campaign.music.presenter.selection;

import java.awt.Component;

import net.sf.anathema.campaign.music.model.selection.IMusicSelection;
import net.sf.anathema.campaign.music.model.selection.IMusicSelectionModel;
import net.sf.anathema.framework.presenter.resources.BasicUi;
import net.sf.anathema.lib.gui.list.actionview.IActionAddableListView;
import net.sf.anathema.lib.resources.IResources;

public class PersistAppendSelectionAction extends AbstractPersistSelectionAction {

  private static final long serialVersionUID = 1258918650740783186L;

  public PersistAppendSelectionAction(
      IActionAddableListView<IMusicSelection> selectionListView,
      IMusicSelectionModel selectionModel,
      IResources resources) {
    super(new BasicUi(resources).getLeftArrowIcon(), resources.getString("Music.Actions.AppendSelection.Tooltip"), //$NON-NLS-1$
        selectionListView,
        selectionModel);
  }

  @Override
  protected void execute(Component parentComponent) {
    IMusicSelection currentSelection = getSelectionModel().getCurrentSelection();
    IMusicSelection persistSelection = getSelectionListView().getSelectedItems()[0];
    persistSelection.addTracks(currentSelection.getContent());
    getSelectionModel().persistSelection(persistSelection);
  }
}

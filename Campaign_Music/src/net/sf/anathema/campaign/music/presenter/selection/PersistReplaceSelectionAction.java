package net.sf.anathema.campaign.music.presenter.selection;

import java.awt.Component;

import net.sf.anathema.campaign.music.model.selection.IMusicSelection;
import net.sf.anathema.campaign.music.model.selection.IMusicSelectionModel;
import net.sf.anathema.campaign.music.presenter.MusicUI;
import net.sf.anathema.lib.gui.list.actionview.IActionAddableListView;
import net.sf.anathema.lib.resources.IResources;

public class PersistReplaceSelectionAction extends AbstractPersistSelectionAction {

  public PersistReplaceSelectionAction(
      IActionAddableListView<IMusicSelection> selectionListView,
      final IMusicSelectionModel selectionModel,
      IResources resources) {
    super(new MusicUI(resources).getReplaceToLeftIcon(), resources.getString("Music.Actions.ReplaceSelection.Tooltip"), //$NON-NLS-1$
        selectionListView,
        selectionModel);
  }

  @Override
  protected void execute(Component parentComponent) {
    IMusicSelection currentSelection = getSelectionModel().getCurrentSelection();
    IMusicSelection persistSelection = getSelectionListView().getSelectedItems()[0];
    persistSelection.clear();
    persistSelection.addTracks(currentSelection.getContent());
    getSelectionModel().persistSelection(persistSelection);
  }
}
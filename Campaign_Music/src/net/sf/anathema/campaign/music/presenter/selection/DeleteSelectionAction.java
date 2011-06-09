package net.sf.anathema.campaign.music.presenter.selection;

import java.awt.Component;

import net.sf.anathema.campaign.music.model.selection.IMusicSelection;
import net.sf.anathema.campaign.music.model.selection.IMusicSelectionModel;
import net.sf.anathema.campaign.music.presenter.util.AbstractListViewSelectionEnabledAction;
import net.sf.anathema.framework.presenter.resources.BasicUi;
import net.sf.anathema.lib.gui.list.actionview.IActionAddableListView;
import net.sf.anathema.lib.resources.IResources;

public class DeleteSelectionAction extends AbstractListViewSelectionEnabledAction<IMusicSelection> {

  private static final long serialVersionUID = 6904362002675382744L;
  private final IMusicSelectionModel selectionModel;

  public DeleteSelectionAction(
      IResources resources,
      IActionAddableListView<IMusicSelection> selectionListView,
      IMusicSelectionModel selectionModel) {
    super(new BasicUi(resources).getRemoveIcon(), selectionListView);
    this.selectionModel = selectionModel;
    setToolTipText(resources.getString("Music.Actions.DeleteMusicSelection.Tooltip")); //$NON-NLS-1$
  }

  @Override
  protected void execute(Component parentComponent) {
    selectionModel.deleteSelection(getSelectedItems()[0]);
  }
}
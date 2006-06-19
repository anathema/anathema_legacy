package net.sf.anathema.campaign.music.presenter.selection;

import java.awt.Component;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import net.disy.commons.swing.action.SmartAction;
import net.sf.anathema.campaign.music.model.selection.IMusicSelection;
import net.sf.anathema.campaign.music.model.selection.IMusicSelectionModel;
import net.sf.anathema.framework.presenter.resources.BasicUi;
import net.sf.anathema.lib.gui.list.actionview.IActionAddableListView;
import net.sf.anathema.lib.resources.IResources;

public class RetrieveSelectionAction extends SmartAction {

  private final IActionAddableListView<IMusicSelection> selectionListView;
  private final IMusicSelectionModel selectionModel;

  public RetrieveSelectionAction(
      IResources resources,
      final IActionAddableListView<IMusicSelection> selectionListView,
      IMusicSelectionModel selectionModel) {
    super(new BasicUi(resources).getDoubleRightArrowIcon());
    this.selectionListView = selectionListView;
    this.selectionModel = selectionModel;
    setToolTipText(resources.getString("Music.Actions.RetrieveSelection.Tooltip")); //$NON-NLS-1$
    selectionListView.addListSelectionListener(new ListSelectionListener() {
      public void valueChanged(ListSelectionEvent e) {
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
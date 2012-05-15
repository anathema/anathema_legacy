package net.sf.anathema.campaign.music.presenter.selection;

import net.sf.anathema.campaign.music.model.selection.IMusicSelection;
import net.sf.anathema.campaign.music.model.selection.IMusicSelectionModel;
import net.sf.anathema.lib.control.IChangeListener;
import net.sf.anathema.lib.gui.action.SmartAction;
import net.sf.anathema.lib.gui.list.actionview.IActionAddableListView;

import javax.swing.Icon;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public abstract class AbstractPersistSelectionAction extends SmartAction {

  private static final long serialVersionUID = -2448358197669530768L;
  private IMusicSelectionModel selectionModel;
  private IActionAddableListView<IMusicSelection> selectionListView;

  public AbstractPersistSelectionAction(
      Icon icon,
      String tooltip,
      IActionAddableListView<IMusicSelection> selectionListView,
      IMusicSelectionModel selectionModel) {
    super(icon);
    this.selectionListView = selectionListView;
    this.selectionModel = selectionModel;
    selectionModel.addCurrentSelectionChangeListener(new IChangeListener() {
      @Override
      public void changeOccurred() {
        updateEnabled();
      }
    });
    selectionListView.addListSelectionListener(new ListSelectionListener() {
      @Override
      public void valueChanged(ListSelectionEvent e) {
        updateEnabled();
      }
    });
    updateEnabled();
    setToolTipText(tooltip);
  }

  protected IActionAddableListView<IMusicSelection> getSelectionListView() {
    return selectionListView;
  }

  protected IMusicSelectionModel getSelectionModel() {
    return selectionModel;
  }

  private void updateEnabled() {
    boolean currentSelectionNonEmpty = selectionModel.getCurrentSelection().getContent().length > 0;
    Object[] selectedSelections = selectionListView.getSelectedItems();
    boolean selectionSelected = selectedSelections.length > 0 && selectedSelections[0] != null;
    setEnabled(currentSelectionNonEmpty && selectionSelected);
  }
}
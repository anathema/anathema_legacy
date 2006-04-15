package net.sf.anathema.charmentry.view;

import javax.swing.ComboBoxEditor;
import javax.swing.Icon;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;

import net.disy.commons.swing.layout.grid.GridDialogLayout;
import net.sf.anathema.character.library.removableentry.presenter.IRemovableEntryView;
import net.sf.anathema.charmentry.presenter.IKeywordView;
import net.sf.anathema.framework.presenter.view.ButtonControlledObjectSelectionView;
import net.sf.anathema.framework.presenter.view.IObjectSelectionView;
import net.sf.anathema.lib.gui.gridlayout.DefaultGridDialogPanel;
import net.sf.anathema.lib.gui.gridlayout.IGridDialogPanel;

public class KeywordView implements IKeywordView {

  private final JPanel dataPanel = new JPanel(new GridDialogLayout(5, false));

  public IObjectSelectionView addObjectSelectionView(
      ComboBoxEditor editor,
      ListCellRenderer renderer,
      String label,
      Icon icon) {
    ButtonControlledObjectSelectionView view = new ButtonControlledObjectSelectionView(renderer, icon, label, editor);
    IGridDialogPanel panel = new DefaultGridDialogPanel();
    view.addComponents(panel);
    dataPanel.add(panel.getContent());
    return view;
  }

  public IRemovableEntryView addEntryView(Icon removeIcon, String string) {
    // TODO Auto-generated method stub
    return null;
  }

  public void removeEntryView(IRemovableEntryView removableView) {
    // TODO Auto-generated method stub

  }

  public JComponent getComponent() {
    // TODO Auto-generated method stub
    return null;
  }

  public boolean needsScrollbar() {
    // TODO Auto-generated method stub
    return false;
  }

}
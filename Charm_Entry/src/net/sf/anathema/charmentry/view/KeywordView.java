package net.sf.anathema.charmentry.view;

import javax.swing.Icon;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;

import net.disy.commons.swing.layout.grid.GridDialogLayout;
import net.sf.anathema.character.library.removableentry.presenter.IRemovableEntryView;
import net.sf.anathema.character.library.removableentry.view.RemovableStringView;
import net.sf.anathema.charmentry.presenter.IKeywordView;
import net.sf.anathema.framework.presenter.view.ButtonControlledObjectSelectionView;
import net.sf.anathema.framework.presenter.view.IObjectSelectionView;
import net.sf.anathema.lib.gui.gridlayout.DefaultGridDialogPanel;
import net.sf.anathema.lib.gui.gridlayout.IGridDialogPanel;

public class KeywordView implements IKeywordView {

  private final JPanel dataPanel = new JPanel(new GridDialogLayout(5, false));
  private final JPanel entryPanel = new JPanel(new GridDialogLayout(2, false));

  public IObjectSelectionView addObjectSelectionView(ListCellRenderer renderer, String label, Icon icon) {
    ButtonControlledObjectSelectionView view = new ButtonControlledObjectSelectionView(renderer, icon, label);
    IGridDialogPanel panel = new DefaultGridDialogPanel();
    view.addComponents(panel);
    dataPanel.add(panel.getContent());
    return view;
  }

  public IRemovableEntryView addEntryView(Icon removeIcon, String string) {
    RemovableStringView view = new RemovableStringView(removeIcon, string);
    view.addContent(entryPanel);
    return view;
  }

  public void removeEntryView(IRemovableEntryView removableView) {
    removableView.delete();
  }

  public JComponent getComponent() {
    dataPanel.add(entryPanel);
    return dataPanel;
  }

  public boolean needsScrollbar() {
    return false;
  }
}
package net.sf.anathema.charmentry.demo.view;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;

import net.disy.commons.swing.layout.grid.GridDialogLayout;
import net.disy.commons.swing.layout.grid.GridDialogLayoutData;
import net.sf.anathema.charmentry.demo.ISimpleSpecialsView;
import net.sf.anathema.framework.presenter.view.ObjectSelectionIntValueView;
import net.sf.anathema.framework.value.IIntValueDisplay;
import net.sf.anathema.lib.gui.selection.IObjectSelectionView;
import net.sf.anathema.lib.gui.selection.ObjectSelectionView;

public class SimpleSpecialsView implements ISimpleSpecialsView {
  private final JPanel content = new JPanel(new GridDialogLayout(1, false));

  public JComponent getContent() {
    return content;
  }

  public void requestFocus() {
    // Nothing to do
  }

  public void dispose() {
    // Nothing to do
  }

  public IIntValueDisplay addIntegerSelectionView(String typeLabel, int maximum) {
    ObjectSelectionIntValueView view = new ObjectSelectionIntValueView(
        typeLabel,
        new DefaultListCellRenderer(),
        maximum);
    content.add(view.getComponent());
    return view;
  }

  public IObjectSelectionView addObjectSelectionView(String labelString, ListCellRenderer renderer, Object[] objects) {
    ObjectSelectionView view = new ObjectSelectionView(labelString, renderer, objects);
    view.addTo(content, new GridDialogLayoutData());
    return view;
  }
}
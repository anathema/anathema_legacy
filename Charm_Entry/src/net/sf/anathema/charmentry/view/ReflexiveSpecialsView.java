package net.sf.anathema.charmentry.view;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

import net.disy.commons.swing.layout.grid.GridDialogLayout;
import net.sf.anathema.charmentry.presenter.view.IReflexiveSpecialsView;
import net.sf.anathema.framework.presenter.view.ObjectSelectionIntValueView;
import net.sf.anathema.framework.value.IIntValueDisplay;

public class ReflexiveSpecialsView implements IReflexiveSpecialsView {

  private final JPanel content = new JPanel(new GridDialogLayout(1, false));

  public IIntValueDisplay addIntegerSelectionView(String typeLabel, int minimum, int maximum) {
    ObjectSelectionIntValueView view = new ObjectSelectionIntValueView(
        typeLabel,
        new DefaultListCellRenderer(),
        minimum,
        maximum);
    content.add(view.getComponent());
    return view;
  }

  public JToggleButton addCheckBoxRow(String label) {
    final JCheckBox box = new JCheckBox(label);
    content.add(box);
    return box;
  }

  public JComponent getContent() {
    return content;
  }

  public void requestFocus() {
    // Nothing to do
  }

  public void dispose() {
    // Nothing to do
  }
}
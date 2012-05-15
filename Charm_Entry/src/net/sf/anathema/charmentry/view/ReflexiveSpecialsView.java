package net.sf.anathema.charmentry.view;

import net.disy.commons.swing.layout.grid.GridDialogLayout;
import net.sf.anathema.charmentry.presenter.view.IReflexiveSpecialsView;
import net.sf.anathema.framework.presenter.view.ObjectSelectionIntValueView;
import net.sf.anathema.framework.value.IIntValueDisplay;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

public class ReflexiveSpecialsView implements IReflexiveSpecialsView {

  private final JPanel content = new JPanel(new GridDialogLayout(1, false));

  @Override
  public IIntValueDisplay addIntegerSelectionView(String typeLabel, int minimum, int maximum) {
    ObjectSelectionIntValueView view = new ObjectSelectionIntValueView(
        typeLabel,
        new DefaultListCellRenderer(),
        minimum,
        maximum);
    content.add(view.getComponent());
    return view;
  }

  @Override
  public JToggleButton addCheckBoxRow(String label) {
    JCheckBox box = new JCheckBox(label);
    content.add(box);
    return box;
  }

  @Override
  public JComponent getContent() {
    return content;
  }

  @Override
  public void requestFocus() {
    // Nothing to do
  }

  @Override
  public void dispose() {
    // Nothing to do
  }
}
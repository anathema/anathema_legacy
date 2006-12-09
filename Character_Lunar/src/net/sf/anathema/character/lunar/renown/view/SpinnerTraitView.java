package net.sf.anathema.character.lunar.renown.view;

import javax.swing.JLabel;
import javax.swing.JPanel;

import net.disy.commons.swing.layout.grid.IDialogComponent;
import net.sf.anathema.framework.value.IIntValueView;
import net.sf.anathema.lib.control.intvalue.IIntValueChangedListener;
import net.sf.anathema.lib.gui.gridlayout.IGridDialogPanel;
import net.sf.anathema.lib.gui.widgets.IntegerSpinner;

public class SpinnerTraitView implements IIntValueView {

  private final String label;
  private final IntegerSpinner spinner = new IntegerSpinner(0);

  public SpinnerTraitView(String label) {
    this.label = label;
    spinner.setPreferredWidth(50);
    spinner.setEditable(false);
  }

  public void addTo(IGridDialogPanel component) {
    component.add(new IDialogComponent() {
      public int getColumnCount() {
        return 2;
      }

      public void fillInto(JPanel panel, int columnCount) {
        addTo(panel);
      }
    });
  }

  /** 2 columns */
  public void addTo(JPanel panel) {
    panel.add(new JLabel(label));
    panel.add(spinner.getComponent());
  }

  public void setValue(int newValue) {
    spinner.setValue(newValue);
  }

  public void addIntValueChangedListener(IIntValueChangedListener listener) {
    spinner.addChangeListener(listener);
  }

  public void removeIntValueChangedListener(IIntValueChangedListener listener) {
    spinner.removeChangeListener(listener);
  }

  public void setMaximum(int maximalValue) {
    spinner.setMaximum(maximalValue);
  }
}
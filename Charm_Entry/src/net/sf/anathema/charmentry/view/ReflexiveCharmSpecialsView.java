package net.sf.anathema.charmentry.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import net.disy.commons.swing.layout.grid.IDialogComponent;
import net.sf.anathema.charmentry.presenter.view.IReflexiveCharmSpecialsView;
import net.sf.anathema.framework.presenter.view.ObjectSelectionIntValueView;
import net.sf.anathema.lib.control.booleanvalue.IBooleanValueChangedListener;
import net.sf.anathema.lib.control.intvalue.IIntValueChangedListener;

public class ReflexiveCharmSpecialsView implements IDialogComponent, IReflexiveCharmSpecialsView {
  private final ObjectSelectionIntValueView stepView;
  private final JCheckBox splitBox;
  private final ObjectSelectionIntValueView defenderView;
  private final JLabel mainLabel;

  public ReflexiveCharmSpecialsView(String headerLabel, String defaultLabel, String defenderLabel, String splitLabel) {
    mainLabel = new JLabel(headerLabel);
    stepView = new ObjectSelectionIntValueView(defaultLabel, new DefaultListCellRenderer(), 10);
    defenderView = new ObjectSelectionIntValueView(defenderLabel, new DefaultListCellRenderer(), 10);
    splitBox = new JCheckBox(splitLabel);
  }

  public void fillInto(JPanel panel, int columnCount) {
    addTo(panel);
  }

  public int getColumnCount() {
    return 4;
  }

  /** Adds 4 columns */
  public void addTo(JPanel panel) {
    panel.add(mainLabel);
    panel.add(stepView.getComponent());
    panel.add(defenderView.getComponent());
    panel.add(splitBox);
  }

  public void setEnabled(boolean enabled) {
    mainLabel.setEnabled(enabled);
    stepView.setEnabled(enabled);
    defenderView.setEnabled(enabled);
    splitBox.setEnabled(enabled);
  }

  public void addSplitListener(final IBooleanValueChangedListener listener) {
    splitBox.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        listener.valueChanged(splitBox.isSelected());
      }
    });
  }

  public void setSplitEnabled(boolean splitEnabled) {
    defenderView.setEnabled(splitEnabled);
    splitBox.setSelected(splitEnabled);
  }

  public void setDefaultStepValue(int step) {
    stepView.setValue(step);
  }

  public void setDefenseStepValue(int defenseStep) {
    defenderView.setValue(defenseStep);
  }

  public void addStepListener(IIntValueChangedListener listener) {
    stepView.addIntValueChangedListener(listener);
  }

  public void addDefenseStepListener(IIntValueChangedListener listener) {
    defenderView.addIntValueChangedListener(listener);
  }
}
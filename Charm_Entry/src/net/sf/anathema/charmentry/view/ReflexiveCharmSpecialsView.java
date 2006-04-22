package net.sf.anathema.charmentry.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import net.sf.anathema.framework.presenter.view.ObjectSelectionIntValueView;
import net.sf.anathema.lib.control.booleanvalue.IBooleanValueChangedListener;
import net.sf.anathema.lib.control.intvalue.IIntValueChangedListener;
import net.sf.anathema.lib.gui.gridlayout.DefaultGridDialogPanel;
import net.sf.anathema.lib.gui.gridlayout.IGridDialogPanel;

public class ReflexiveCharmSpecialsView {
  private final ObjectSelectionIntValueView stepView;
  private final JButton defaultButton;
  private JCheckBox splitBox;
  private ObjectSelectionIntValueView defenderView;
  private JLabel mainLabel;

  public ReflexiveCharmSpecialsView(
      String headerLabel,
      String defaultLabel,
      String defenderLabel,
      String splitLabel,
      String resetLabel) {
    mainLabel = new JLabel(headerLabel);
    stepView = new ObjectSelectionIntValueView(defaultLabel, new DefaultListCellRenderer(), 10);
    defenderView = new ObjectSelectionIntValueView(defenderLabel, new DefaultListCellRenderer(), 10);
    splitBox = new JCheckBox(splitLabel);
    defaultButton = new JButton(resetLabel);
  }

  /** Adds 5 columns */
  public void addTo(JPanel panel) {
    panel.add(mainLabel);
    addView(panel, stepView);
    addView(panel, defenderView);
    panel.add(splitBox);
    panel.add(defaultButton);
  }

  private void addView(JPanel panel, ObjectSelectionIntValueView view) {
    IGridDialogPanel gridPanel = new DefaultGridDialogPanel();
    view.addComponents(gridPanel);
    panel.add(gridPanel.getContent());
  }

  public void setEnabled(boolean enabled) {
    mainLabel.setEnabled(enabled);
    stepView.setEnabled(enabled);
    defenderView.setEnabled(enabled);
    splitBox.setEnabled(enabled);
    defaultButton.setEnabled(enabled);
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

  public void addDefaultButtonListener(ActionListener listener) {
    defaultButton.addActionListener(listener);
  }
}

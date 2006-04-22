package net.sf.anathema.charmentry.view;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import net.sf.anathema.framework.presenter.view.ObjectSelectionIntValueView;
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
}

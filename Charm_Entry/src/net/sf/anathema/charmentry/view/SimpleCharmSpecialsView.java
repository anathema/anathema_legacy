package net.sf.anathema.charmentry.view;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import net.sf.anathema.framework.presenter.view.ObjectSelectionIntValueView;
import net.sf.anathema.lib.gui.gridlayout.DefaultGridDialogPanel;
import net.sf.anathema.lib.gui.gridlayout.IGridDialogPanel;

public class SimpleCharmSpecialsView {
  private final ObjectSelectionIntValueView speedView;
  private final ObjectSelectionIntValueView dvView;
  private final JButton defaultButton;
  private final JLabel mainLabel;

  public SimpleCharmSpecialsView(String headerString, String speedLabel, String dvLabel, String defaultLabel) {
    mainLabel = new JLabel(headerString);
    speedView = new ObjectSelectionIntValueView(speedLabel, new DefaultListCellRenderer(), 10);
    dvView = new ObjectSelectionIntValueView(dvLabel, new DefaultListCellRenderer(), -10);
    defaultButton = new JButton(defaultLabel);
  }

  /** Adds 4 columns */
  public void addTo(JPanel panel) {
    panel.add(mainLabel);
    addView(panel, speedView);
    addView(panel, dvView);
    panel.add(defaultButton);
  }

  private void addView(JPanel panel, ObjectSelectionIntValueView view) {
    IGridDialogPanel gridPanel = new DefaultGridDialogPanel();
    view.addComponents(gridPanel);
    panel.add(gridPanel.getContent());
  }
}
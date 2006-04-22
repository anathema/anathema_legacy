package net.sf.anathema.charmentry.view;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JPanel;

import net.sf.anathema.framework.presenter.view.ObjectSelectionIntValueView;
import net.sf.anathema.lib.gui.gridlayout.DefaultGridDialogPanel;
import net.sf.anathema.lib.gui.gridlayout.IGridDialogPanel;

public class CharmTypeSpecialsView {
  private final ObjectSelectionIntValueView speedView;
  private final ObjectSelectionIntValueView dvView;
  private final ObjectSelectionIntValueView stepView;

  public CharmTypeSpecialsView(String speedLabel, String dvLabel, String stepLabel) {
    speedView = new ObjectSelectionIntValueView(speedLabel, new DefaultListCellRenderer(), 10);
    dvView = new ObjectSelectionIntValueView(dvLabel, new DefaultListCellRenderer(), -10);
    stepView = new ObjectSelectionIntValueView(stepLabel, new DefaultListCellRenderer(), 10);
  }

  /** Adds 3 columns */
  public void addTo(JPanel panel) {
    addView(panel, speedView);
    addView(panel, dvView);
    addView(panel, stepView);
  }

  private void addView(JPanel panel, ObjectSelectionIntValueView view) {
    IGridDialogPanel gridPanel = new DefaultGridDialogPanel();
    view.addComponents(gridPanel);
    panel.add(gridPanel.getContent());
  }
}
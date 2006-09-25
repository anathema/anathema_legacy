package net.sf.anathema.charmentry.view;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JLabel;
import javax.swing.JPanel;

import net.disy.commons.swing.layout.grid.IDialogComponent;
import net.sf.anathema.charmentry.presenter.view.ISimpleCharmSpecialsView;
import net.sf.anathema.framework.presenter.view.ObjectSelectionIntValueView;
import net.sf.anathema.lib.control.intvalue.IIntValueChangedListener;
import net.sf.anathema.lib.gui.gridlayout.DefaultGridDialogPanel;
import net.sf.anathema.lib.gui.gridlayout.IGridDialogPanel;

public class SimpleCharmSpecialsView implements IDialogComponent, ISimpleCharmSpecialsView {
  private final ObjectSelectionIntValueView speedView;
  private final ObjectSelectionIntValueView dvView;
  private final JLabel mainLabel;

  public SimpleCharmSpecialsView(String headerString, String speedLabel, String dvLabel) {
    mainLabel = new JLabel(headerString);
    speedView = new ObjectSelectionIntValueView(speedLabel, new DefaultListCellRenderer(), 10);
    dvView = new ObjectSelectionIntValueView(dvLabel, new DefaultListCellRenderer(), -10);
  }

  public void fillInto(JPanel panel, int columnCount) {
    addTo(panel);
  }

  public int getColumnCount() {
    return 3;
  }

  /** Adds 3 columns */
  public void addTo(JPanel panel) {
    panel.add(mainLabel);
    addView(panel, speedView);
    addView(panel, dvView);
  }

  private void addView(JPanel panel, ObjectSelectionIntValueView view) {
    IGridDialogPanel gridPanel = new DefaultGridDialogPanel();
    view.addComponents(gridPanel);
    panel.add(gridPanel.getComponent());
  }

  public void setEnabled(boolean enabled) {
    mainLabel.setEnabled(enabled);
    speedView.setEnabled(enabled);
    dvView.setEnabled(enabled);
  }

  public void addSpeedValueChangedListener(IIntValueChangedListener listener) {
    speedView.addIntValueChangedListener(listener);
  }

  public void addDefenseValueChangedListener(IIntValueChangedListener listener) {
    dvView.addIntValueChangedListener(listener);
  }

  public void setSpeedValue(int speed) {
    speedView.setValue(speed);
  }

  public void setDefenseValue(int defenseValue) {
    dvView.setValue(defenseValue);
  }
}
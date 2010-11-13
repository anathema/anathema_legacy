package net.sf.anathema.character.equipment.impl.creation.view;

import javax.swing.JComponent;
import javax.swing.JPanel;

import net.disy.commons.swing.layout.grid.IDialogComponent;
import net.sf.anathema.character.equipment.creation.view.IWeaponStatisticsView;
import net.sf.anathema.lib.gui.widgets.IntegerSpinner;
import net.sf.anathema.lib.workflow.container.factory.StandardPanelBuilder;
import net.sf.anathema.lib.workflow.textualdescription.ITextView;

public class WeaponStatisticsView implements IWeaponStatisticsView {

  private static final int COLUMN_COUNT = 30;
  private final StandardPanelBuilder panelBuilder = new StandardPanelBuilder();
  private JPanel content;
  private JComponent focusComponent;

  public JComponent getContent() {
    if (content == null) {
      content = panelBuilder.getUntitledContent();
    }
    return content;
  }

  public ITextView addLineTextView(String label) {
    return panelBuilder.addLineTextView(label, COLUMN_COUNT);
  }

  public IntegerSpinner addIntegerSpinner(String label, int startValue) {
    IntegerSpinner spinner = panelBuilder.addIntegerSpinner(label, startValue);
    if (focusComponent == null) {
      focusComponent = spinner.getComponent();
    }
    return spinner;
  }

  public void requestFocus() {
    if (focusComponent != null) {
      focusComponent.requestFocus();
    }
  }

  public void dispose() {
    //Nothing to do
  }

  public void addDialogComponent(IDialogComponent component) {
    panelBuilder.addDialogComponent(component);
  }
}
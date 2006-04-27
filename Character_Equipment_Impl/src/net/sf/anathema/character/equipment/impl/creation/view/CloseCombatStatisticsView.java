package net.sf.anathema.character.equipment.impl.creation.view;

import javax.swing.JComponent;
import javax.swing.JPanel;

import net.sf.anathema.character.equipment.creation.view.ICloseCombatStatisticsView;
import net.sf.anathema.lib.gui.widgets.IntegerSpinner;
import net.sf.anathema.lib.workflow.container.factory.StandardPanelBuilder;
import net.sf.anathema.lib.workflow.textualdescription.ITextView;

public class CloseCombatStatisticsView implements ICloseCombatStatisticsView {

  private static final int COLUMN_COUNT = 30;
  private final StandardPanelBuilder panelBuilder = new StandardPanelBuilder();
  private JPanel content;

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
    return panelBuilder.addIntegerSpinner(label, startValue);
  }

  public void requestFocus() {
    //Nothing to do
  }

  public void dispose() {
    //Nothing to do
  }
}
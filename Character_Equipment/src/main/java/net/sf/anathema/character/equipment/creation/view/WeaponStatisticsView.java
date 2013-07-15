package net.sf.anathema.character.equipment.creation.view;

import net.miginfocom.layout.CC;
import net.sf.anathema.lib.gui.layout.AdditiveView;
import net.sf.anathema.lib.workflow.container.factory.MigPanelBuilder;
import net.sf.anathema.lib.workflow.textualdescription.ITextView;

import javax.swing.JComponent;

public class WeaponStatisticsView implements IWeaponStatisticsView {
  private static final int COLUMN_COUNT = 30;
  private final MigPanelBuilder panelBuilder = new MigPanelBuilder(4);
  private JComponent content;

  @Override
  public JComponent getContent() {
    if (content == null) {
      content = panelBuilder.getUntitledContent();
    }
    return content;
  }

  @Override
  public ITextView addLineTextView(String label) {
    return panelBuilder.addSpanningLineTextView(label, COLUMN_COUNT);
  }

  @Override
  public void requestFocus() {
    //nothing to do
  }

  @Override
  public void addView(AdditiveView additiveView, CC cc) {
    panelBuilder.addView(additiveView, cc);
  }
}
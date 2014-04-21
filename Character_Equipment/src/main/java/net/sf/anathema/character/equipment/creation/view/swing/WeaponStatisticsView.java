package net.sf.anathema.character.equipment.creation.view.swing;

import net.miginfocom.layout.LC;
import net.miginfocom.swing.MigLayout;
import net.sf.anathema.lib.gui.layout.AdditiveView;
import net.sf.anathema.lib.workflow.textualdescription.ITextView;
import net.sf.anathema.lib.workflow.textualdescription.view.LabelTextView;
import net.sf.anathema.lib.workflow.textualdescription.view.LineTextView;

import javax.swing.JComponent;
import javax.swing.JPanel;

public class WeaponStatisticsView implements IWeaponStatisticsView {
  private static final int COLUMN_COUNT = 30;
  private final JPanel panel = new JPanel(new MigLayout(new LC().wrapAfter(4).fill().insets("2")));


  @Override
  public JComponent getContent() {
    return panel;
  }

  @Override
  public ITextView addLineTextView(String label) {
    LineTextView textView = new LineTextView(COLUMN_COUNT);
    LabelTextView labelTextView = new LabelTextView(label, textView);
    labelTextView.addToMigPanelSpanning(panel);
    return textView;
  }

  @Override
  public void requestFocus() {
    //nothing to do
  }

  @Override
  public void addView(AdditiveView additiveView) {
    additiveView.addTo(panel);
  }
}
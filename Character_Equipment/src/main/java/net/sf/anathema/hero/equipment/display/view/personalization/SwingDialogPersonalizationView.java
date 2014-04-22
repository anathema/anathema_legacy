package net.sf.anathema.hero.equipment.display.view.personalization;

import net.miginfocom.swing.MigLayout;
import net.sf.anathema.framework.swing.IView;
import net.sf.anathema.hero.equipment.display.presenter.EquipmentPersonalizationView;
import net.sf.anathema.lib.workflow.textualdescription.ITextView;
import net.sf.anathema.lib.workflow.textualdescription.view.LabelTextView;
import net.sf.anathema.lib.workflow.textualdescription.view.LineTextView;

import javax.swing.JComponent;
import javax.swing.JPanel;

import static net.sf.anathema.lib.gui.layout.LayoutUtils.fillWithoutInsets;

public class SwingDialogPersonalizationView implements IView, EquipmentPersonalizationView {
  private final JPanel content = new JPanel(new MigLayout(fillWithoutInsets().wrapAfter(2)));

  @Override
  public JComponent getComponent() {
    return content;
  }

  @Override
  public ITextView addEntry(String label) {
    LineTextView textView = new LineTextView(0);
    LabelTextView labelTextView = new LabelTextView(label, textView);
    labelTextView.addToMigPanel(content);
    return textView;
  }

}
package net.sf.anathema.character.main.library.overview;

import net.miginfocom.swing.MigLayout;
import net.sf.anathema.framework.swing.IView;
import net.sf.anathema.lib.workflow.labelledvalue.ILabelledAlotmentView;
import net.sf.anathema.lib.workflow.labelledvalue.IValueView;
import net.sf.anathema.lib.workflow.labelledvalue.view.AbstractLabelledValueView;
import net.sf.anathema.lib.workflow.labelledvalue.view.LabelledAlotmentView;
import net.sf.anathema.lib.workflow.labelledvalue.view.LabelledIntegerValueView;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import java.awt.Font;

import static net.sf.anathema.lib.gui.layout.LayoutUtils.withoutInsets;

public class OverviewCategory implements IOverviewCategory, IView {

  private final JPanel panel = new JPanel(new MigLayout(withoutInsets().wrapAfter(4)));

  public OverviewCategory(JComponent parent, String borderTitle, boolean useSmallFont) {
    TitledBorder titledBorder = new TitledBorder(borderTitle);
    if (useSmallFont) {
      Font newFont = AbstractLabelledValueView.deriveSmallerFont(new BorderFontProvider().getFont());
      titledBorder.setTitleFont(newFont);
    }
    panel.setBorder(titledBorder);
    parent.add(panel);
  }

  @Override
  public JComponent getComponent() {
    return panel;
  }

  @Override
  public ILabelledAlotmentView addAlotmentView(String labelText, int maxValueLength) {
    LabelledAlotmentView view = new LabelledAlotmentView(labelText, 0, 0, maxValueLength);
    view.addTo(panel);
    return view;
  }

  @Override
  public IValueView<Integer> addIntegerValueView(String labelText, int maxValueLength) {
    LabelledIntegerValueView view = new LabelledIntegerValueView(labelText, 0, true, maxValueLength);
    view.addComponents(panel);
    return view;
  }

  @Override
  public IValueView<String> addStringValueView(String labelText) {
    LabelledOverviewStringValueView view = new LabelledOverviewStringValueView(labelText, "");
    view.addComponents(panel);
    return view;
  }
}
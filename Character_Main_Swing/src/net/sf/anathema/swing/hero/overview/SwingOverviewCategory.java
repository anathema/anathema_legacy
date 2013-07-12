package net.sf.anathema.swing.hero.overview;

import net.miginfocom.swing.MigLayout;
import net.sf.anathema.character.main.library.overview.OverviewCategory;
import net.sf.anathema.character.main.view.labelledvalue.ILabelledAllotmentView;
import net.sf.anathema.character.main.view.labelledvalue.IValueView;
import net.sf.anathema.framework.swing.IView;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import java.awt.Font;

import static net.sf.anathema.lib.gui.layout.LayoutUtils.withoutInsets;

public class SwingOverviewCategory implements OverviewCategory, IView {

  private final JPanel panel = new JPanel(new MigLayout(withoutInsets().wrapAfter(4)));

  public SwingOverviewCategory(JComponent parent, String borderTitle, boolean useSmallFont) {
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
  public ILabelledAllotmentView addAlotmentView(String labelText, int maxValueLength) {
    LabelledAllotmentView view = new LabelledAllotmentView(labelText, 0, 0, maxValueLength);
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
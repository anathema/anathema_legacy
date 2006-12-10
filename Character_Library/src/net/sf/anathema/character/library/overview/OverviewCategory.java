package net.sf.anathema.character.library.overview;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import net.disy.commons.swing.layout.grid.GridDialogLayout;
import net.sf.anathema.lib.gui.IView;
import net.sf.anathema.lib.workflow.labelledvalue.ILabelledAlotmentView;
import net.sf.anathema.lib.workflow.labelledvalue.IValueView;
import net.sf.anathema.lib.workflow.labelledvalue.view.AbstractLabelledValueView;
import net.sf.anathema.lib.workflow.labelledvalue.view.LabelledAlotmentView;
import net.sf.anathema.lib.workflow.labelledvalue.view.LabelledIntegerValueView;

public class OverviewCategory implements IOverviewCategory, IView {

  private final JPanel panel = new JPanel(new GridDialogLayout(4, false));
  private final String borderTitle;
  private final boolean useSmallFont;

  public OverviewCategory(String borderTitle, boolean useSmallFont) {
    this.borderTitle = borderTitle;
    this.useSmallFont = useSmallFont;
  }

  public JComponent getComponent() {
    TitledBorder titledBorder = new TitledBorder(borderTitle);
    if (useSmallFont) {
      titledBorder.setTitleFont(AbstractLabelledValueView.deriveSmallerFont(titledBorder.getTitleFont()));
    }
    panel.setBorder(titledBorder);
    return panel;
  }

  public ILabelledAlotmentView addAlotmentView(String labelText, int maxValueLength) {
    LabelledAlotmentView view = new LabelledAlotmentView(labelText, 0, 0, maxValueLength);
    view.addTo(panel);
    return view;
  }

  public IAdditionalAlotmentView addAdditionalAlotmentView(String labelText, int maxValueLength) {
    LabelledAdditionalAlotmentView view = new LabelledAdditionalAlotmentView(labelText, maxValueLength);
    view.addTo(panel);
    return view;
  }

  public IValueView<Integer> addIntegerValueView(String labelText, int maxValueLength) {
    LabelledIntegerValueView view = new LabelledIntegerValueView(labelText, 0, true, maxValueLength);
    view.addComponents(panel, 4);
    return view;
  }

  public IValueView<String> addStringValueView(String labelText) {
    LabelledOverviewStringValueView view = new LabelledOverviewStringValueView(labelText, ""); //$NON-NLS-1$
    view.addComponents(panel, 4);
    return view;
  }
}
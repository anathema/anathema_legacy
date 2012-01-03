package net.sf.anathema.character.library.overview;

import javax.swing.JPanel;

import net.disy.commons.swing.layout.grid.GridDialogLayoutData;
import net.disy.commons.swing.layout.grid.GridDialogLayoutDataFactory;
import net.sf.anathema.lib.workflow.labelledvalue.IValueView;
import net.sf.anathema.lib.workflow.labelledvalue.view.AbstractLabelledValueView;

public class LabelledOverviewStringValueView extends AbstractLabelledValueView implements IValueView<String> {

  public LabelledOverviewStringValueView(String titleText, String valueText) {
    super(titleText, valueText, "Moonshadow", true); //$NON-NLS-1$
  }

  public LabelledOverviewStringValueView(String titleText, String valueText, boolean adjustFontSize) {
    super(titleText, valueText, "Moonshadow", adjustFontSize); //$NON-NLS-1$
  }

  /** GridDialogLayout, 2 columns */
  public void addComponents(JPanel panel) {
    panel.add(titleLabel, createGrabData());
    panel.add(valueLabel, GridDialogLayoutDataFactory.createHorizontalFillNoGrab());
  }

  private GridDialogLayoutData createGrabData() {
    GridDialogLayoutData beginData = new GridDialogLayoutData();
    beginData.setGrabExcessHorizontalSpace(true);
    return beginData;
  }

  public void setValue(String value) {
    setText(value);
  }

  public void addComponents(JPanel panel, int columnCount) {
    panel.add(titleLabel, createGrabData());
    GridDialogLayoutData data = GridDialogLayoutDataFactory.createHorizontalFillNoGrab();
    data.setHorizontalSpan(columnCount - 1);
    panel.add(valueLabel, data);
  }
}
package net.sf.anathema.character.library.overview;

import net.disy.commons.swing.layout.grid.GridDialogLayoutData;
import net.disy.commons.swing.layout.grid.GridDialogLayoutDataFactory;
import net.miginfocom.layout.CC;
import net.sf.anathema.lib.workflow.labelledvalue.IValueView;
import net.sf.anathema.lib.workflow.labelledvalue.view.AbstractLabelledValueView;

import javax.swing.JPanel;

public class LabelledOverviewStringValueView extends AbstractLabelledValueView implements IValueView<String> {
  public LabelledOverviewStringValueView(String titleText, String valueText) {
    super(titleText, valueText, "Moonshadow", true); //$NON-NLS-1$
  }

  public LabelledOverviewStringValueView(String titleText, String valueText, boolean adjustFontSize) {
    super(titleText, valueText, "Moonshadow", adjustFontSize); //$NON-NLS-1$
  }

  /**
   * GridDialogLayout, 2 columns
   */
  public void addComponents(JPanel panel) {
    panel.add(titleLabel, createGrabData());
    panel.add(valueLabel, GridDialogLayoutDataFactory.createHorizontalFillNoGrab());
  }

  public void addComponents(JPanel panel, int columnCount) {
    panel.add(titleLabel, new CC().growX().pushX());
    panel.add(valueLabel, new CC().growX().spanX());
  }

  private GridDialogLayoutData createGrabData() {
    GridDialogLayoutData beginData = new GridDialogLayoutData();
    beginData.setGrabExcessHorizontalSpace(true);
    return beginData;
  }

  @Override
  public void setValue(String value) {
    setText(value);
  }
}
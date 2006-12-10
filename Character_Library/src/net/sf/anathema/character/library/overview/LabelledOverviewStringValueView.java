package net.sf.anathema.character.library.overview;

import javax.swing.JPanel;

import net.disy.commons.swing.layout.GridDialogLayoutDataUtilities;
import net.disy.commons.swing.layout.grid.GridAlignment;
import net.disy.commons.swing.layout.grid.GridDialogLayoutData;
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
    GridDialogLayoutData beginData = new GridDialogLayoutData();
    beginData.setHorizontalAlignment(GridAlignment.BEGINNING);
    beginData.setGrabExcessHorizontalSpace(true);
    panel.add(titleLabel, beginData);
    panel.add(valueLabel, GridDialogLayoutDataUtilities.createHorizontalFillNoGrab());
  }

  public void setValue(String value) {
    setText(value);
  }

  public void addComponents(JPanel panel, int columnCount) {
    GridDialogLayoutData beginData = new GridDialogLayoutData();
    beginData.setHorizontalAlignment(GridAlignment.BEGINNING);
    beginData.setGrabExcessHorizontalSpace(true);
    panel.add(titleLabel, beginData);
    GridDialogLayoutData data = GridDialogLayoutDataUtilities.createHorizontalFillNoGrab();
    data.setHorizontalSpan(columnCount - 1);
    panel.add(valueLabel, data);
  }
}
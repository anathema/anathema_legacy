package net.sf.anathema.lib.workflow.labelledvalue.view;

import javax.swing.JLabel;
import javax.swing.JPanel;

import net.disy.commons.swing.layout.GridDialogLayoutDataUtilities;
import net.disy.commons.swing.layout.grid.GridDialogLayoutData;
import net.disy.commons.swing.layout.grid.IDialogComponent;
import net.sf.anathema.lib.gui.dialogcomponent.grouped.IGridDialogPanelContent;
import net.sf.anathema.lib.gui.gridlayout.IGridDialogPanel;

public class LabelledIntegerValueView extends AbstractLabelledIntegerValueView implements IGridDialogPanelContent {

  public LabelledIntegerValueView(String labelText, int value, boolean adjustFontSize, int maxValueLength) {
    super(labelText, createLengthString(maxValueLength), value, adjustFontSize);
  }

  @Override
  public void addComponents(IGridDialogPanel dialogPanel) {
    dialogPanel.add(new IDialogComponent() {
      public int getColumnCount() {
        return 2;
      }

      public void fillInto(JPanel panel, int columnCount) {
        addComponents(panel);
      }
    });
  }

  /** 2 Columns */
  public void addComponents(JPanel panel) {
    panel.add(titleLabel, GridDialogLayoutData.FILL_HORIZONTAL);
    panel.add(valueLabel, GridDialogLayoutDataUtilities.createHorizontalFillNoGrab());
  }

  public JLabel getValueLabel() {
    return valueLabel;
  }
}
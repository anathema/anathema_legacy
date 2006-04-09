package net.sf.anathema.lib.workflow.labelledvalue.view;

import javax.swing.JLabel;
import javax.swing.JPanel;

import net.disy.commons.swing.layout.grid.GridAlignment;
import net.disy.commons.swing.layout.grid.GridDialogLayoutData;
import net.disy.commons.swing.layout.grid.IDialogComponent;
import net.sf.anathema.lib.gui.dialogcomponent.grouped.IGridDialogPanelContent;
import net.sf.anathema.lib.gui.gridlayout.IGridDialogPanel;
import net.sf.anathema.lib.gui.layout.AnathemaLayoutUtilities;

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
        panel.add(titleLabel, GridDialogLayoutData.FILL_HORIZONTAL);
        GridDialogLayoutData data = AnathemaLayoutUtilities.createAlignedGridDialogData(GridAlignment.FILL);
        data.setHorizontalSpan(columnCount - 1);
        panel.add(valueLabel, data);
      }
    });
  }

  public JLabel getValueLabel() {
    return valueLabel;
  }

  public JLabel getTitleLabel() {
    return titleLabel;
  }
}
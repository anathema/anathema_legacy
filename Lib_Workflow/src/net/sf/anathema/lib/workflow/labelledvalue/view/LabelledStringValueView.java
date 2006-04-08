package net.sf.anathema.lib.workflow.labelledvalue.view;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;

import net.disy.commons.swing.layout.grid.GridAlignment;
import net.disy.commons.swing.layout.grid.GridDialogLayoutData;
import net.disy.commons.swing.layout.grid.IDialogComponent;
import net.sf.anathema.lib.gui.gridlayout.IGridDialogPanel;
import net.sf.anathema.lib.gui.layout.AnathemaLayoutUtilities;
import net.sf.anathema.lib.workflow.labelledvalue.ILabelledValueView;

public class LabelledStringValueView implements ILabelledValueView<String> {

  protected final JLabel titleLabel;
  protected final JLabel valueLabel;

  public LabelledStringValueView(String labelText) {
    this.titleLabel = new JLabel(labelText);
    this.valueLabel = new JLabel();
  }

  public void addComponents(IGridDialogPanel dialogPanel) {
    dialogPanel.add(new IDialogComponent() {
      public int getColumnCount() {
        return 2;
      }

      public void fillInto(JPanel panel, int columnCount) {
        GridDialogLayoutData beginData = new GridDialogLayoutData();
        beginData.setHorizontalAlignment(GridAlignment.BEGINNING);
        beginData.setGrabExcessHorizontalSpace(true);
        panel.add(titleLabel, beginData);

        GridDialogLayoutData endData = AnathemaLayoutUtilities.createAlignedGridDialogData(GridAlignment.FILL);
        endData.setHorizontalSpan(columnCount - 1);
        panel.add(valueLabel, endData);
      }
    });
  }

  public void setTextColor(Color color) {
    titleLabel.setForeground(color);
    valueLabel.setForeground(color);
  }

  public void setFontStyle(int style) {
    titleLabel.setFont(titleLabel.getFont().deriveFont(style));
    valueLabel.setFont(valueLabel.getFont().deriveFont(style));
  }

  public void setValue(String value) {
    setText(value);
  }

  public final void setText(String value) {
    valueLabel.setText(value);
  }
}
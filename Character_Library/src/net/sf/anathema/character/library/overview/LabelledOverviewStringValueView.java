package net.sf.anathema.character.library.overview;

import javax.swing.JPanel;

import net.disy.commons.swing.layout.grid.GridAlignment;
import net.disy.commons.swing.layout.grid.GridDialogLayoutData;
import net.disy.commons.swing.layout.grid.IDialogComponent;
import net.sf.anathema.lib.gui.dialogcomponent.grouped.IGridDialogPanelContent;
import net.sf.anathema.lib.gui.gridlayout.IGridDialogPanel;
import net.sf.anathema.lib.gui.layout.AnathemaLayoutUtilities;
import net.sf.anathema.lib.workflow.labelledvalue.IValueView;
import net.sf.anathema.lib.workflow.labelledvalue.view.AbstractLabelledValueView;

public class LabelledOverviewStringValueView extends AbstractLabelledValueView implements
    IValueView<String>,
    IGridDialogPanelContent {

  public LabelledOverviewStringValueView(String titleText, String valueText) {
    this(titleText, valueText, "Moonshadow"); //$NON-NLS-1$
  }

  public LabelledOverviewStringValueView(String titleText, String valueText, boolean adjustFontSize) {
    super(titleText, valueText, "Moonshadow", adjustFontSize); //$NON-NLS-1$
  }

  public LabelledOverviewStringValueView(String titleText, String valueText, String lengthText) {
    super(titleText, valueText, lengthText, true);
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

  public void setValue(String value) {
    setText(value);
  }
}
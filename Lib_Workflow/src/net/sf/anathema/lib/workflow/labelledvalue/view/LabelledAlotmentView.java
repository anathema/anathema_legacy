package net.sf.anathema.lib.workflow.labelledvalue.view;

import java.util.Collection;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import net.disy.commons.swing.layout.grid.GridAlignment;
import net.disy.commons.swing.layout.grid.GridDialogLayoutData;
import net.disy.commons.swing.layout.grid.IDialogComponent;
import net.sf.anathema.lib.gui.dialogcomponent.grouped.IGridDialogPanelContent;
import net.sf.anathema.lib.gui.gridlayout.IGridDialogPanel;
import net.sf.anathema.lib.gui.layout.AnathemaLayoutUtilities;
import net.sf.anathema.lib.workflow.labelledvalue.ILabelledAlotmentView;

public class LabelledAlotmentView extends AbstractLabelledIntegerValueView implements
    ILabelledAlotmentView,
    IGridDialogPanelContent {

  private final JLabel seperatorLabel;
  protected final JLabel maxPointLabel;

  public LabelledAlotmentView(String labelText, int currentPoints, int maxPoints, int maxValueLength) {
    super(labelText, createLengthString(maxValueLength), currentPoints, true);
    this.maxPointLabel = createLabel(
        String.valueOf(maxPoints),
        createLengthString(maxValueLength),
        SwingConstants.RIGHT,
        true);
    this.seperatorLabel = createLabel("/", "/", SwingConstants.CENTER, true); //$NON-NLS-1$//$NON-NLS-2$
  }

  @Override
  public void addComponents(IGridDialogPanel dialogPanel) {
    dialogPanel.add(new IDialogComponent() {
      public int getColumnCount() {
        return 4;
      }

      public void fillInto(JPanel panel, int columnCount) {
        panel.add(titleLabel, GridDialogLayoutData.FILL_HORIZONTAL);
        panel.add(valueLabel, GridDialogLayoutData.FILL_HORIZONTAL);
        panel.add(seperatorLabel, AnathemaLayoutUtilities.createAlignedGridDialogData(GridAlignment.END));
        panel.add(maxPointLabel, AnathemaLayoutUtilities.createAlignedGridDialogData(GridAlignment.END));
      }
    });
  }

  @Override
  protected Collection<JComponent> getComponents() {
    Collection<JComponent> collection = super.getComponents();
    collection.add(seperatorLabel);
    collection.add(maxPointLabel);
    return collection;
  }

  public void setAlotment(int value) {
    maxPointLabel.setText(String.valueOf(value));
  }
}
package net.sf.anathema.lib.workflow.labelledvalue.view;

import java.util.Collection;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import net.disy.commons.swing.layout.grid.GridAlignment;
import net.disy.commons.swing.layout.grid.GridDialogLayoutData;
import net.sf.anathema.lib.workflow.labelledvalue.ILabelledAlotmentView;

public class LabelledAlotmentView extends AbstractLabelledIntegerValueView implements ILabelledAlotmentView {

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

  /** 4 columns */
  public void addTo(JPanel panel) {
    panel.add(titleLabel, GridDialogLayoutData.FILL_HORIZONTAL);
    panel.add(valueLabel, GridDialogLayoutData.FILL_HORIZONTAL);
    GridDialogLayoutData dialogData = new GridDialogLayoutData();
    dialogData.setHorizontalAlignment(GridAlignment.END);
    panel.add(seperatorLabel, dialogData);
    panel.add(maxPointLabel, dialogData);
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
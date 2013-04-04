package net.sf.anathema.lib.workflow.labelledvalue.view;

import net.miginfocom.layout.CC;
import net.sf.anathema.lib.workflow.labelledvalue.ILabelledAlotmentView;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import java.util.Collection;

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
    this.seperatorLabel = createLabel("/", "/", SwingConstants.CENTER, true);
  }

  public void addTo(JPanel panel) {
    panel.add(titleLabel, new CC().growX().pushX());
    panel.add(valueLabel, new CC().growX());
    panel.add(seperatorLabel, new CC().alignX("right"));
    panel.add(maxPointLabel, new CC().alignX("right"));
  }

  @Override
  protected Collection<JComponent> getComponents() {
    Collection<JComponent> collection = super.getComponents();
    collection.add(seperatorLabel);
    collection.add(maxPointLabel);
    return collection;
  }

  @Override
  public void setAlotment(int value) {
    maxPointLabel.setText(String.valueOf(value));
  }
}
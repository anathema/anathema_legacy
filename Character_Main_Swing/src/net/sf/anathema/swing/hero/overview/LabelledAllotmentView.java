package net.sf.anathema.swing.hero.overview;

import net.miginfocom.layout.CC;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import java.util.Collection;

public class LabelledAllotmentView extends AbstractLabelledIntegerValueView implements
        net.sf.anathema.character.main.view.labelledvalue.LabelledAllotmentView {

  private final JLabel separatorLabel;
  protected final JLabel maxPointLabel;

  public LabelledAllotmentView(String labelText, int currentPoints, int maxPoints, int maxValueLength) {
    super(labelText, createLengthString(maxValueLength), currentPoints, true);
    this.maxPointLabel = createLabel(
        String.valueOf(maxPoints),
        createLengthString(maxValueLength),
        SwingConstants.RIGHT,
        true);
    this.separatorLabel = createLabel("/", "/", SwingConstants.CENTER, true);
  }

  public void addTo(JPanel panel) {
    panel.add(titleLabel, new CC().growX().pushX());
    panel.add(valueLabel, new CC().growX());
    panel.add(separatorLabel, new CC().alignX("right"));
    panel.add(maxPointLabel, new CC().alignX("right"));
  }

  @Override
  protected Collection<JComponent> getComponents() {
    Collection<JComponent> collection = super.getComponents();
    collection.add(separatorLabel);
    collection.add(maxPointLabel);
    return collection;
  }

  @Override
  public void setAllotment(int value) {
    maxPointLabel.setText(String.valueOf(value));
  }
}
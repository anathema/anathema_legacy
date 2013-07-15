package net.sf.anathema.swing.hero.overview;

import net.miginfocom.layout.CC;
import net.sf.anathema.character.main.view.labelledvalue.IValueView;

import javax.swing.JPanel;

public class LabelledOverviewStringValueView extends AbstractLabelledValueView implements IValueView<String> {
  public LabelledOverviewStringValueView(String titleText, String valueText) {
    super(titleText, valueText, "Moonshadow", true);
  }

  public LabelledOverviewStringValueView(String titleText, String valueText, boolean adjustFontSize) {
    super(titleText, valueText, "Moonshadow", adjustFontSize);
  }

  public void addComponents(JPanel panel) {
    panel.add(titleLabel, new CC().growX().pushX());
    panel.add(valueLabel, new CC().growX().spanX());
  }

  @Override
  public void setValue(String value) {
    setText(value);
  }
}
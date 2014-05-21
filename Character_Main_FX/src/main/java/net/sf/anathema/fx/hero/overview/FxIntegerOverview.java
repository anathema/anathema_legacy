package net.sf.anathema.fx.hero.overview;

import javafx.scene.control.Label;
import net.miginfocom.layout.CC;
import net.sf.anathema.character.framework.display.labelledvalue.IValueView;
import net.sf.anathema.framework.ui.FontStyle;
import net.sf.anathema.framework.ui.RGBColor;
import net.sf.anathema.platform.fx.FxThreading;
import org.tbee.javafx.scene.layout.MigPane;

public class FxIntegerOverview implements IValueView<Integer> {
  private final Label titleLabel = new Label();
  private final Label valueLabel = new Label();
  private final FontStyler styler = new FontStyler(titleLabel, valueLabel);

  public FxIntegerOverview(String labelText) {
    titleLabel.setText(labelText);
  }

  public void addTo(final MigPane panel) {
    FxThreading.runOnCorrectThread(new Runnable() {
      @Override
      public void run() {
        panel.add(titleLabel);
        panel.add(valueLabel, new CC().alignX("right").span());
      }
    });
  }

  @Override
  public void setValue(final Integer value) {
    FxThreading.runOnCorrectThread(new Runnable() {
      @Override
      public void run() {
        valueLabel.setText(String.valueOf(value));
      }
    });
  }

  @Override
  public void setTextColor(RGBColor color) {
    styler.setColor(color);
  }

  @Override
  public void setFontStyle(FontStyle style) {
    styler.setStyle(style);
  }
}
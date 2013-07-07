package net.sf.anathema.hero.languages.display.view.overview;

import javafx.scene.control.Label;
import net.miginfocom.layout.CC;
import net.sf.anathema.framework.ui.RGBColor;
import net.sf.anathema.lib.control.legality.FontStyle;
import net.sf.anathema.lib.workflow.labelledvalue.ILabelledAlotmentView;
import net.sf.anathema.platform.fx.FxThreading;
import org.tbee.javafx.scene.layout.MigPane;

public class FxAlotmentOverview implements ILabelledAlotmentView {


  private final Label titleLabel = new Label();
  private final Label valueLabel = new Label();
  private final Label separatorLabel = new Label();
  private final Label alotmentLabel = new Label();
  private final FontStyler styler = new FontStyler(titleLabel, valueLabel, separatorLabel, alotmentLabel);

  public FxAlotmentOverview(String labelText) {
    titleLabel.setText(labelText);
    separatorLabel.setText("/");
  }

  public void addTo(final MigPane panel) {
    FxThreading.runOnCorrectThread(new Runnable() {
      @Override
      public void run() {
        panel.add(titleLabel, new CC().growX().pushX());
        panel.add(valueLabel, new CC().growX());
        panel.add(separatorLabel, new CC().alignX("right"));
        panel.add(alotmentLabel, new CC().alignX("right"));
      }
    });
  }

  @Override
  public void setAlotment(int value) {
    alotmentLabel.setText(String.valueOf(value));
  }

  @Override
  public void setValue(Integer value) {
    valueLabel.setText(String.valueOf(value));
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
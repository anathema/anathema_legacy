package net.sf.anathema.fx.hero.overview;

import com.google.common.base.Joiner;
import javafx.scene.control.Label;
import net.sf.anathema.framework.ui.FontStyle;
import net.sf.anathema.framework.ui.RGBColor;

public class FontStyler {
  private final Label[] labels;
  private RGBColor color = RGBColor.Black;
  private FontStyle style = FontStyle.Plain;

  public FontStyler(Label... labels) {
    this.labels = labels;
  }

  public void setColor(RGBColor color) {
    this.color = color;
    style();
  }

  public void setStyle(FontStyle style) {
    this.style = style;
    style();
  }

  @SuppressWarnings("StringConcatenationInsideStringBufferAppend")
  private void style() {
    StringBuilder styleBuilder = new StringBuilder();
    if (FontStyle.Bold == style) {
      styleBuilder.append("-fx-font-weight:bold;");
    } else {
      styleBuilder.append("-fx-font-weight:normal;");
    }
    String rgbaCode = Joiner.on(",").join(color.red, color.green, color.blue, color.alpha);
    styleBuilder.append("-fx-text-fill:rgba(" + rgbaCode + ");");
    String effectiveStyle = styleBuilder.toString();
    for (Label label : labels) {
      label.setStyle(effectiveStyle);
    }
  }
}

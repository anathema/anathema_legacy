package net.sf.anathema.character.reporting.pdf.rendering.boxes.magic;

import com.itextpdf.text.DocumentException;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.EncodingMetrics;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.PreferredHeight;
import net.sf.anathema.character.reporting.pdf.rendering.extent.Bounds;
import net.sf.anathema.character.reporting.pdf.rendering.graphics.SheetGraphics;

public class PreferredComboHeight implements PreferredHeight {

  @Override
  public float getValue(EncodingMetrics metrics, float width) {
    SheetGraphics graphics = metrics.createSimulateGraphics(width);
    ComboEncoder encoder = new ComboEncoder();
    Bounds maxContentBounds = metrics.createSimulateBounds(width);
    try {
      return encoder.encodeCombos(graphics, metrics.getSession(), maxContentBounds);
    } catch (DocumentException e) {
      return 30;
    }
  }
}

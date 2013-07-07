package net.sf.anathema.hero.combos.sheet.encoder;

import com.itextpdf.text.DocumentException;
import net.sf.anathema.hero.sheet.pdf.encoder.boxes.EncodingMetrics;
import net.sf.anathema.hero.sheet.pdf.encoder.boxes.PreferredHeight;
import net.sf.anathema.hero.sheet.pdf.encoder.Bounds;
import net.sf.anathema.hero.sheet.pdf.encoder.graphics.SheetGraphics;

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

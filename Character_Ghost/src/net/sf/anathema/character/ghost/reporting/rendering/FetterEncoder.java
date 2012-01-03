package net.sf.anathema.character.ghost.reporting.rendering;

import com.lowagie.text.DocumentException;
import net.sf.anathema.character.ghost.fetters.model.Fetter;
import net.sf.anathema.character.ghost.reporting.content.GhostFetterContent;
import net.sf.anathema.character.reporting.pdf.content.ReportContent;
import net.sf.anathema.character.reporting.pdf.content.general.NamedValue;
import net.sf.anathema.character.reporting.pdf.rendering.Bounds;
import net.sf.anathema.character.reporting.pdf.rendering.Position;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.AbstractBoxContentEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.general.traits.PdfTraitEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.graphics.SheetGraphics;

public class FetterEncoder extends AbstractBoxContentEncoder<GhostFetterContent> {

  private final PdfTraitEncoder traitEncoder = PdfTraitEncoder.createSmallTraitEncoder();

  public FetterEncoder() {
    super(GhostFetterContent.class);
  }

  public void encode(SheetGraphics graphics, ReportContent reportContent, Bounds bounds) throws DocumentException {
    GhostFetterContent content = createContent(reportContent);
    float groupSpacing = traitEncoder.getTraitHeight() / 2;
    float x = bounds.x;
    float y = bounds.getMaxY() - 2 * groupSpacing;
    float width = bounds.getWidth() * 1 / 2;
    for (NamedValue fetter : content.getFetters()) {
      Position position = new Position(x, y);
      y -= traitEncoder.encode(graphics, fetter, position, width, content.getTraitMaximum());
      if (y < bounds.getMinY()) {
        y = bounds.getMaxY() - 2 * groupSpacing;
        x += bounds.width / 2;
      }
    }
  }
}

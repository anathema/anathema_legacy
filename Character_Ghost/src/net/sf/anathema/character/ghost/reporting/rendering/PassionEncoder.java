package net.sf.anathema.character.ghost.reporting.rendering;

import com.lowagie.text.DocumentException;
import net.sf.anathema.character.ghost.reporting.content.GhostPassionContent;
import net.sf.anathema.character.reporting.pdf.content.ReportContent;
import net.sf.anathema.character.reporting.pdf.content.general.NamedValue;
import net.sf.anathema.character.reporting.pdf.rendering.extent.Bounds;
import net.sf.anathema.character.reporting.pdf.rendering.extent.Position;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.AbstractBoxContentEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.general.traits.PdfTraitEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.graphics.SheetGraphics;

public class PassionEncoder extends AbstractBoxContentEncoder<GhostPassionContent> {

  private final PdfTraitEncoder traitEncoder = PdfTraitEncoder.createSmallTraitEncoder();

  public PassionEncoder() {
    super(GhostPassionContent.class);
  }

  public void encode(SheetGraphics graphics, ReportContent reportContent, Bounds bounds) throws DocumentException {
    GhostPassionContent content = createContent(reportContent);
    float groupSpacing = traitEncoder.getTraitHeight() / 2;
    float x = bounds.x;
    float y = bounds.getMaxY() - 2 * groupSpacing;
    float width = bounds.getWidth();
    for (NamedValue passion : content.getPrintPassions()) {
      Position position = new Position(x, y);
      y -= traitEncoder.encode(graphics, passion, position, width, content.getTraitMaximum());
    }
  }
}

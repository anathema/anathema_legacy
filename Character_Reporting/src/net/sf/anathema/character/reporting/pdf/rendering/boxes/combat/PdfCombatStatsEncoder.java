package net.sf.anathema.character.reporting.pdf.rendering.boxes.combat;

import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.PdfContentByte;
import net.sf.anathema.character.reporting.pdf.content.ReportContent;
import net.sf.anathema.character.reporting.pdf.rendering.elements.Bounds;
import net.sf.anathema.character.reporting.pdf.rendering.general.SheetGraphics;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.IBoxContentEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.IPdfContentEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.general.table.ITableEncoder;

public class PdfCombatStatsEncoder  implements IBoxContentEncoder {

  private final static float PADDING = 3;

  private final ITableEncoder combatRulesEncoder;
  private final IPdfContentEncoder combatValueEncoder;

  public PdfCombatStatsEncoder(ITableEncoder combatRulesEncoder, IPdfContentEncoder combatValueEncoder) {
    this.combatRulesEncoder = combatRulesEncoder;
    this.combatValueEncoder = combatValueEncoder;
  }

  public String getHeaderKey(ReportContent reportContent) {
    return "Combat"; //$NON-NLS-1$
  }

  public void encode(SheetGraphics graphics, ReportContent reportContent, Bounds bounds) throws DocumentException {
    float height = combatValueEncoder.encode(graphics.getDirectContent(), reportContent.getCharacter(), bounds);
    Bounds ruleBounds = new Bounds(bounds.x, bounds.y, bounds.width,
      bounds.height - height - PADDING);
    encodeRules(graphics.getDirectContent(), reportContent, ruleBounds);
  }

  private void encodeRules(PdfContentByte directContent, ReportContent content, Bounds bounds) throws DocumentException {
    combatRulesEncoder.encodeTable(directContent, content, bounds);
  }

  public boolean hasContent(ReportContent content) {
    return true;
  }
}

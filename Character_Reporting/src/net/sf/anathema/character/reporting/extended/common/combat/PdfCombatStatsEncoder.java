package net.sf.anathema.character.reporting.extended.common.combat;

import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfContentByte;
import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.character.IGenericDescription;
import net.sf.anathema.character.reporting.common.Bounds;
import net.sf.anathema.character.reporting.common.encoder.IPdfContentEncoder;
import net.sf.anathema.character.reporting.common.encoder.IPdfTableEncoder;
import net.sf.anathema.character.reporting.extended.common.IPdfContentBoxEncoder;
import net.sf.anathema.character.reporting.extended.util.AbstractPdfEncoder;

public class PdfCombatStatsEncoder extends AbstractPdfEncoder implements IPdfContentBoxEncoder {

  private final static float PADDING = 3;

  private final BaseFont baseFont;
  private final IPdfTableEncoder combatRulesEncoder;
  private final IPdfContentEncoder combatValueEncoder;

  @Override
  protected BaseFont getBaseFont() {
    return baseFont;
  }

  public PdfCombatStatsEncoder(IPdfTableEncoder combatRulesEncoder, IPdfContentEncoder combatValueEncoder, BaseFont baseFont) {
    this.baseFont = baseFont;
    this.combatRulesEncoder = combatRulesEncoder;
    this.combatValueEncoder = combatValueEncoder;
  }

  public String getHeaderKey(IGenericCharacter character, IGenericDescription description) {
    return "Combat"; //$NON-NLS-1$
  }

  public void encode(PdfContentByte directContent, IGenericCharacter character, IGenericDescription description,
                     Bounds bounds) throws DocumentException {
    float height = combatValueEncoder.encode(directContent, character, bounds);
    Bounds ruleBounds = new Bounds(bounds.x, bounds.y, bounds.width, bounds.height - height - PADDING);
    encodeRules(directContent, character, ruleBounds);
  }

  private void encodeRules(PdfContentByte directContent, IGenericCharacter character, Bounds bounds) throws DocumentException {
    combatRulesEncoder.encodeTable(directContent, character, bounds);
  }

  public boolean hasContent(IGenericCharacter character) {
    return true;
  }
}

package net.sf.anathema.character.reporting.sheet.page;

import net.sf.anathema.character.reporting.sheet.PdfEncodingRegistry;
import net.sf.anathema.character.reporting.sheet.common.IPdfContentBoxEncoder;
import net.sf.anathema.character.reporting.sheet.common.IPdfContentEncoder;
import net.sf.anathema.character.reporting.sheet.common.IPdfVariableContentBoxEncoder;
import net.sf.anathema.character.reporting.sheet.common.PdfHorizontalLineContentEncoder;
import net.sf.anathema.character.reporting.sheet.common.combat.PdfCombatStatsEncoder;
import net.sf.anathema.character.reporting.sheet.first.FirstEditionCombatRulesTableEncoder;
import net.sf.anathema.character.reporting.sheet.first.FirstEditionCombatValueEncoder;
import net.sf.anathema.character.reporting.sheet.first.FirstEditionHealthAndMovementEncoder;
import net.sf.anathema.character.reporting.sheet.pageformat.PdfPageConfiguration;
import net.sf.anathema.character.reporting.sheet.util.IPdfTableEncoder;
import net.sf.anathema.lib.resources.IResources;

import com.lowagie.text.pdf.BaseFont;

public abstract class AbstractFirstEditionPartEncoder implements IPdfPartEncoder {

  private final IResources resources;
  private final BaseFont baseFont;
  private final BaseFont symbolBaseFont;

  public AbstractFirstEditionPartEncoder(IResources resources, BaseFont baseFont, BaseFont symbolBaseFont) {
    this.resources = resources;
    this.baseFont = baseFont;
    this.symbolBaseFont = symbolBaseFont;
  }

  public final IResources getResources() {
    return resources;
  }

  public final BaseFont getBaseFont() {
    return baseFont;
  }

  public final BaseFont getSymbolBaseFont() {
    return symbolBaseFont;
  }

  public final IPdfContentBoxEncoder getCombatStatsEncoder() {
    IPdfContentEncoder valueEncoder = new FirstEditionCombatValueEncoder(resources, baseFont);
    IPdfTableEncoder rulesEncoder = new FirstEditionCombatRulesTableEncoder(resources, baseFont);
    return new PdfCombatStatsEncoder(rulesEncoder, valueEncoder, baseFont);
  }

  public IPdfContentBoxEncoder getSocialCombatEncoder() {
    return new PdfHorizontalLineContentEncoder(1, "MeritsFlaws"); //$NON-NLS-1$
  }

  public IPdfContentBoxEncoder getIntimaciesEncoder(PdfEncodingRegistry registry) {
    return new PdfHorizontalLineContentEncoder(1, "Notes"); //$NON-NLS-1$
  }

  public IPdfContentBoxEncoder getHealthAndMovementEncoder() {
    return new FirstEditionHealthAndMovementEncoder(getResources(), getBaseFont(), symbolBaseFont);
  }

  public IPdfContentBoxEncoder getHealthEncoder() {
    return null;
  }

  public IPdfContentBoxEncoder getMovementEncoder() {
    return null;
  }

  public float getWeaponryHeight() {
    return 129;
  }

  public IPdfVariableContentBoxEncoder[] getAdditionalFirstPageEncoders() {
    return new IPdfVariableContentBoxEncoder[0];
  }
  
  public IPdfVariableContentBoxEncoder[] getAdditionalMagicSidebarEncoders() {
    return new IPdfVariableContentBoxEncoder[0];
  }
  
  public IPdfTableEncoder[] getAdditionalMagicEncoders() {
    return new IPdfTableEncoder[0];
  }

  public IPdfPageEncoder[] getAdditionalPages(PdfPageConfiguration configuration) {
    return new IPdfPageEncoder[0];
  }

  public boolean isEncodeAttributeAsFavorable() {
    return false;
  }
  
  public IPdfContentBoxEncoder getOverdriveEncoder() {
	  return null;
  }
}
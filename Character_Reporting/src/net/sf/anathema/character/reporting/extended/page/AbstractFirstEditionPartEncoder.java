package net.sf.anathema.character.reporting.extended.page;

import com.lowagie.text.pdf.BaseFont;
import net.sf.anathema.character.reporting.encoder.*;
import net.sf.anathema.character.reporting.extended.PdfEncodingRegistry;
import net.sf.anathema.character.reporting.extended.common.IPdfContentBoxEncoder;
import net.sf.anathema.character.reporting.encoder.IPdfContentEncoder;
import net.sf.anathema.character.reporting.encoder.IPdfVariableContentBoxEncoder;
import net.sf.anathema.character.reporting.extended.common.PdfHorizontalLineContentEncoder;
import net.sf.anathema.character.reporting.extended.common.combat.PdfCombatStatsEncoder;
import net.sf.anathema.character.reporting.extended.first.FirstEditionCombatRulesTableEncoder;
import net.sf.anathema.character.reporting.extended.first.FirstEditionCombatValueEncoder;
import net.sf.anathema.character.reporting.extended.first.FirstEditionHealthAndMovementEncoder;
import net.sf.anathema.character.reporting.pageformat.PdfPageConfiguration;
import net.sf.anathema.lib.resources.IResources;

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
package net.sf.anathema.character.reporting.sheet.page;

import net.sf.anathema.character.reporting.sheet.PdfEncodingRegistry;
import net.sf.anathema.character.reporting.sheet.common.IPdfContentEncoder;
import net.sf.anathema.character.reporting.sheet.common.NullPdfContentEncoder;
import net.sf.anathema.character.reporting.sheet.first.FirstEditionCombatRulesTableEncoder;
import net.sf.anathema.character.reporting.sheet.second.SecondEditionCombatStatsEncoder;
import net.sf.anathema.lib.resources.IResources;

import com.lowagie.text.pdf.BaseFont;

public abstract class AbstractFirstEditionPartEncoder implements IPdfPartEncoder {

  private final IResources resources;
  private final BaseFont baseFont;

  public AbstractFirstEditionPartEncoder(IResources resources, BaseFont baseFont) {
    this.resources = resources;
    this.baseFont = baseFont;
  }

  public final IResources getResources() {
    return resources;
  }

  public final BaseFont getBaseFont() {
    return baseFont;
  }

  public final IPdfContentEncoder getCombatStatsEncoder() {
    return new SecondEditionCombatStatsEncoder(resources, baseFont, new FirstEditionCombatRulesTableEncoder(resources, baseFont));
  }

  public IPdfContentEncoder getSocialCombatEncoder() {
    return new NullPdfContentEncoder();
  }

  public IPdfContentEncoder getIntimaciesEncoder(PdfEncodingRegistry registry) {
    return new NullPdfContentEncoder();
  }

  public IPdfContentEncoder getHealthAndMovementEncoder() {
    return new NullPdfContentEncoder("HealthAndMovement"); //$NON-NLS-1$
  }
  
  public float getWeaponryHeight() {
    return 140;
  }
}
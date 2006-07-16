package net.sf.anathema.character.reporting.sheet.page;

import net.sf.anathema.character.reporting.sheet.PdfEncodingRegistry;
import net.sf.anathema.character.reporting.sheet.common.IPdfContentEncoder;
import net.sf.anathema.character.reporting.sheet.second.SecondEditionCombatRulesTableEncoder;
import net.sf.anathema.character.reporting.sheet.second.SecondEditionCombatStatsEncoder;
import net.sf.anathema.character.reporting.sheet.second.SecondEditionHealthAndMovementEncoder;
import net.sf.anathema.character.reporting.sheet.second.SecondEditionSocialCombatStatsEncoder;
import net.sf.anathema.lib.resources.IResources;

import com.lowagie.text.pdf.BaseFont;

public abstract class AbstractSecondEditionPartEncoder implements IPdfPartEncoder {

  private final IResources resources;
  private final BaseFont baseFont;
  private final BaseFont symbolBaseFont;

  public AbstractSecondEditionPartEncoder(IResources resources, BaseFont baseFont, BaseFont symbolBaseFont) {
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

  public final IPdfContentEncoder getCombatStatsEncoder() {
    return new SecondEditionCombatStatsEncoder(resources, baseFont, new SecondEditionCombatRulesTableEncoder(
        resources,
        baseFont));
  }

  public IPdfContentEncoder getSocialCombatEncoder() {
    return new SecondEditionSocialCombatStatsEncoder(resources, baseFont);
  }

  public IPdfContentEncoder getIntimaciesEncoder(PdfEncodingRegistry registry) {
    return registry.getIntimaciesEncoder();
  }

  public IPdfContentEncoder getHealthAndMovementEncoder() {
    return new SecondEditionHealthAndMovementEncoder(resources, baseFont, symbolBaseFont);
  }
}
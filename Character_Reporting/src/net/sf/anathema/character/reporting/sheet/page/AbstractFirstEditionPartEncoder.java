package net.sf.anathema.character.reporting.sheet.page;

import net.sf.anathema.character.reporting.sheet.PdfEncodingRegistry;
import net.sf.anathema.character.reporting.sheet.common.IPdfContentBoxEncoder;
import net.sf.anathema.character.reporting.sheet.common.IPdfContentEncoder;
import net.sf.anathema.character.reporting.sheet.common.NullPdfContentEncoder;
import net.sf.anathema.character.reporting.sheet.common.combat.PdfCombatStatsEncoder;
import net.sf.anathema.character.reporting.sheet.first.FirstEditionCombatRulesTableEncoder;
import net.sf.anathema.character.reporting.sheet.first.FirstEditionCombatValueEncoder;
import net.sf.anathema.character.reporting.sheet.util.IPdfTableEncoder;
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

  public final IPdfContentBoxEncoder getCombatStatsEncoder() {
    IPdfContentEncoder valueEncoder = new FirstEditionCombatValueEncoder(resources, baseFont);
    IPdfTableEncoder rulesEncoder = new FirstEditionCombatRulesTableEncoder(resources, baseFont);
    return new PdfCombatStatsEncoder(rulesEncoder, valueEncoder, baseFont);
  }

  public IPdfContentBoxEncoder getSocialCombatEncoder() {
    return new NullPdfContentEncoder();
  }

  public IPdfContentBoxEncoder getIntimaciesEncoder(PdfEncodingRegistry registry) {
    return new NullPdfContentEncoder();
  }

  public IPdfContentBoxEncoder getHealthAndMovementEncoder() {
    return new NullPdfContentEncoder("HealthAndMovement"); //$NON-NLS-1$
  }

  public float getWeaponryHeight() {
    return 140;
  }
}
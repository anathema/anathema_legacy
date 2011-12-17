package net.sf.anathema.character.reporting.pdf.layout.simple;

import com.lowagie.text.pdf.BaseFont;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.combat.SecondEditionCombatRulesTableEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.combat.SecondEditionCombatValueEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.essence.PdfEssenceEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.health.SecondEditionHealthAndMovementEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.social.SocialCombatStatsEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.IPdfContentBoxEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.IPdfContentEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.page.IPdfPageEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.general.table.IPdfTableEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.page.PdfPageConfiguration;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.combat.PdfCombatStatsEncoder;
import net.sf.anathema.lib.resources.IResources;

public abstract class AbstractSecondEditionPartEncoder implements ISimplePartEncoder {

  private final IResources resources;
  private final BaseFont baseFont;
  private final BaseFont symbolBaseFont;
  private final int essenceMax;

  public AbstractSecondEditionPartEncoder(IResources resources, BaseFont baseFont, BaseFont symbolBaseFont, int essenceMax) {
    this.resources = resources;
    this.baseFont = baseFont;
    this.symbolBaseFont = symbolBaseFont;
    this.essenceMax = essenceMax;
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

  protected int getEssenceMax() {
    return essenceMax;
  }

  public boolean hasSecondPage() {
    return true;
  }

  protected int getFontSize() {
    return FONT_SIZE;
  }

  public IPdfContentBoxEncoder getEssenceEncoder() {
    return new PdfEssenceEncoder(getBaseFont(), getResources(), essenceMax);
  }

  public IPdfContentBoxEncoder getOverdriveEncoder() {
    return null;
  }

  public final IPdfContentBoxEncoder getCombatStatsEncoder() {
    IPdfContentEncoder valueEncoder = new SecondEditionCombatValueEncoder(resources, baseFont);
    IPdfTableEncoder rulesEncoder = new SecondEditionCombatRulesTableEncoder(resources, baseFont);
    return new PdfCombatStatsEncoder(rulesEncoder, valueEncoder, baseFont);
  }

  public IPdfContentBoxEncoder getSocialCombatEncoder() {
    return new SocialCombatStatsEncoder(resources, baseFont);
  }

  public IPdfContentBoxEncoder getIntimaciesEncoder(SimpleEncodingRegistry registry) {
    return registry.getIntimaciesEncoder();
  }

  public IPdfContentBoxEncoder getHealthAndMovementEncoder() {
    return new SecondEditionHealthAndMovementEncoder(resources, baseFont, symbolBaseFont);
  }

  public float getWeaponryHeight() {
    return 102;
  }

  public IPdfPageEncoder[] getAdditionalPages(PdfPageConfiguration configuration) {
    return new IPdfPageEncoder[0];
  }

  public boolean isEncodeAttributeAsFavorable() {
    return false;
  }
}

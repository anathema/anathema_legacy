package net.sf.anathema.character.reporting.sheet.page;

import net.sf.anathema.character.generic.traits.types.OtherTraitType;
import net.sf.anathema.character.reporting.sheet.PdfEncodingRegistry;
import net.sf.anathema.character.reporting.sheet.common.IPdfContentBoxEncoder;
import net.sf.anathema.character.reporting.sheet.common.IPdfContentEncoder;
import net.sf.anathema.character.reporting.sheet.common.PdfDotsEncoder;
import net.sf.anathema.character.reporting.sheet.common.PdfEssenceEncoder;
import net.sf.anathema.character.reporting.sheet.common.combat.PdfCombatStatsEncoder;
import net.sf.anathema.character.reporting.sheet.pageformat.PdfPageConfiguration;
import net.sf.anathema.character.reporting.sheet.second.SecondEditionCombatRulesTableEncoder;
import net.sf.anathema.character.reporting.sheet.second.SecondEditionCombatValueEncoder;
import net.sf.anathema.character.reporting.sheet.second.SecondEditionHealthAndMovementEncoder;
import net.sf.anathema.character.reporting.sheet.second.SecondEditionHealthEncoder;
import net.sf.anathema.character.reporting.sheet.second.SecondEditionMovementEncoder;
import net.sf.anathema.character.reporting.sheet.second.SecondEditionSocialCombatStatsEncoder;
import net.sf.anathema.character.reporting.sheet.util.IPdfTableEncoder;
import net.sf.anathema.lib.resources.IResources;

import com.lowagie.text.pdf.BaseFont;

public abstract class AbstractSecondEditionPartEncoder implements IPdfPartEncoder {

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
  
  protected int getEssenceMax()
  {
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
  
  public IPdfContentBoxEncoder getDotsEncoder(OtherTraitType trait, int traitMax, String traitHeaderKey) {
      return new PdfDotsEncoder(getBaseFont(), getResources(), trait, traitMax, traitHeaderKey);
    }
  
  public IPdfContentBoxEncoder getOverdriveEncoder()
  {
	  return null;
  }

  public final IPdfContentBoxEncoder getCombatStatsEncoder() {
    IPdfContentEncoder valueEncoder = new SecondEditionCombatValueEncoder(resources, baseFont);
    IPdfTableEncoder rulesEncoder = new SecondEditionCombatRulesTableEncoder(resources, baseFont);
    return new PdfCombatStatsEncoder(rulesEncoder, valueEncoder, baseFont);
  }

  public IPdfContentBoxEncoder getSocialCombatEncoder() {
    return new SecondEditionSocialCombatStatsEncoder(resources, baseFont);
  }

  public IPdfContentBoxEncoder getIntimaciesEncoder(PdfEncodingRegistry registry) {
    return registry.getIntimaciesEncoder();
  }

  public IPdfContentBoxEncoder getHealthAndMovementEncoder() {
    return new SecondEditionHealthAndMovementEncoder(resources, baseFont, symbolBaseFont);
  }

  public IPdfContentBoxEncoder getHealthEncoder() {
    return new SecondEditionHealthEncoder(resources, baseFont, symbolBaseFont);
  }

  public IPdfContentBoxEncoder getMovementEncoder() {
    return new SecondEditionMovementEncoder(resources, baseFont, symbolBaseFont);
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
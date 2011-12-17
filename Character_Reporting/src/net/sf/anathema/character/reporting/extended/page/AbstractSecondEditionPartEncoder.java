package net.sf.anathema.character.reporting.extended.page;

import com.lowagie.text.pdf.BaseFont;
import net.sf.anathema.character.generic.traits.types.OtherTraitType;
import net.sf.anathema.character.reporting.extended.PdfEncodingRegistry;
import net.sf.anathema.character.reporting.extended.common.*;
import net.sf.anathema.character.reporting.extended.common.combat.PdfCombatStatsEncoder;
import net.sf.anathema.character.reporting.extended.pageformat.PdfPageConfiguration;
import net.sf.anathema.character.reporting.extended.second.*;
import net.sf.anathema.character.reporting.extended.util.IPdfTableEncoder;
import net.sf.anathema.lib.resources.IResources;

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
	    return new NewPdfEssenceEncoder(getBaseFont(), getResources(), essenceMax);
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
  
  public IPdfVariableContentBoxEncoder[] getAdditionalFirstPageEncoders() {
    return new IPdfVariableContentBoxEncoder[0];
  }

  public IPdfPageEncoder[] getAdditionalPages(PdfPageConfiguration configuration) {
    return new IPdfPageEncoder[0];
  }

  public boolean isEncodeAttributeAsFavorable() {
    return false;
  }
}
package net.sf.anathema.character.reporting.extended.page;

import com.lowagie.text.pdf.BaseFont;
import net.sf.anathema.character.generic.traits.types.OtherTraitType;
import net.sf.anathema.character.reporting.extended.PdfEncodingRegistry;
import net.sf.anathema.character.reporting.extended.common.IPdfContentBoxEncoder;
import net.sf.anathema.character.reporting.extended.common.PdfBackgroundEncoder;
import net.sf.anathema.character.reporting.extended.common.PdfDotsEncoder;
import net.sf.anathema.character.reporting.extended.common.PdfExperienceEncoder;
import net.sf.anathema.lib.resources.IResources;

public class FirstEditionMortalPartEncoder extends AbstractFirstEditionPartEncoder {

  private final PdfEncodingRegistry registry;

  public FirstEditionMortalPartEncoder(IResources resources, BaseFont baseFont, BaseFont symbolBaseFont, PdfEncodingRegistry registry) {
    super(resources, baseFont, symbolBaseFont);
    this.registry = registry;
  }

  public IPdfContentBoxEncoder getAnimaEncoder() {
    return new PdfBackgroundEncoder(getResources(), getBaseFont());
  }

  public IPdfContentBoxEncoder getEssenceEncoder() {
    return new PdfExperienceEncoder(getResources(), getBaseFont());
  }

  public IPdfContentBoxEncoder getDotsEncoder(OtherTraitType trait, int traitMax, String traitHeaderKey) {
    return new PdfDotsEncoder(getBaseFont(), getResources(), trait, traitMax, traitHeaderKey);
  }

  public boolean hasSecondPage() {
    return false;
  }

  public boolean hasMagicPage() {
    return false;
  }

  public IPdfContentBoxEncoder getGreatCurseEncoder() {
    return registry.getLinguisticsEncoder(); //No Great Curse for Mortals
  }
}

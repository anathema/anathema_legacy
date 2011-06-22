package net.sf.anathema.character.reporting.sheet.page;

import net.sf.anathema.character.reporting.sheet.PdfEncodingRegistry;
import net.sf.anathema.character.reporting.sheet.common.IPdfContentBoxEncoder;
import net.sf.anathema.character.reporting.sheet.common.PdfBackgroundEncoder;
import net.sf.anathema.character.reporting.sheet.common.PdfExperienceEncoder;
import net.sf.anathema.lib.resources.IResources;

import com.lowagie.text.pdf.BaseFont;

public class FirstEditionMortalPartEncoder extends AbstractFirstEditionPartEncoder {

  private final PdfEncodingRegistry registry;

  public FirstEditionMortalPartEncoder(
      IResources resources,
      BaseFont baseFont,
      BaseFont symbolBaseFont,
      PdfEncodingRegistry registry) {
    super(resources, baseFont, symbolBaseFont);
    this.registry = registry;
  }

  public IPdfContentBoxEncoder getAnimaEncoder() {
    return new PdfBackgroundEncoder(getResources(), getBaseFont());
  }

  public IPdfContentBoxEncoder getEssenceEncoder() {
    return new PdfExperienceEncoder(getResources(), getBaseFont());
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
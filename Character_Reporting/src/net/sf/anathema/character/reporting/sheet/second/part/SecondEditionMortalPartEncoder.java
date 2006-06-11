package net.sf.anathema.character.reporting.sheet.second.part;

import net.sf.anathema.character.reporting.sheet.common.IPdfContentEncoder;
import net.sf.anathema.character.reporting.sheet.common.PdfExperienceEncoder;
import net.sf.anathema.character.reporting.sheet.page.IPdfPartEncoder;
import net.sf.anathema.lib.resources.IResources;

import com.lowagie.text.pdf.BaseFont;

public class SecondEditionMortalPartEncoder implements IPdfPartEncoder {
  
  private final IResources resources;
  private final BaseFont baseFont;

  public SecondEditionMortalPartEncoder(IResources resources, BaseFont baseFont) {
    this.resources = resources;
    this.baseFont = baseFont;
  }

  public IPdfContentEncoder getEssenceEncoder() {
    return new PdfExperienceEncoder(resources, baseFont);
  }

  public boolean hasSecondPage() {
    return false;
  }
}
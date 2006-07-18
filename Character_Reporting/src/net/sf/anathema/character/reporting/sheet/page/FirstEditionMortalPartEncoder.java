package net.sf.anathema.character.reporting.sheet.page;

import net.sf.anathema.character.reporting.sheet.common.IPdfContentBoxEncoder;
import net.sf.anathema.character.reporting.sheet.common.PdfBackgroundEncoder;
import net.sf.anathema.character.reporting.sheet.common.PdfExperienceEncoder;
import net.sf.anathema.character.reporting.sheet.common.PdfHorizontalLineContentEncoder;
import net.sf.anathema.lib.resources.IResources;

import com.lowagie.text.pdf.BaseFont;

public class FirstEditionMortalPartEncoder extends AbstractFirstEditionPartEncoder {

  public FirstEditionMortalPartEncoder(IResources resources, BaseFont baseFont, BaseFont symbolBaseFont) {
    super(resources, baseFont, symbolBaseFont);
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

  public IPdfContentBoxEncoder getGreatCurseEncoder() {
    return new PdfHorizontalLineContentEncoder(2, "Languages"); //$NON-NLS-1$
  }
}
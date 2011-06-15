package net.sf.anathema.character.reporting.sheet.second.part;

import net.sf.anathema.character.reporting.sheet.common.IPdfContentBoxEncoder;
import net.sf.anathema.character.reporting.sheet.page.AbstractSecondEditionPartEncoder;
import net.sf.anathema.lib.resources.IResources;

import com.lowagie.text.pdf.BaseFont;

public class SecondEditionMortalPartEncoder extends AbstractSecondEditionPartEncoder {

  public SecondEditionMortalPartEncoder(
      IResources resources,
      BaseFont baseFont,
      BaseFont symbolBaseFont) {
    super(resources, baseFont, symbolBaseFont, 3);
  }

  public IPdfContentBoxEncoder getAnimaEncoder() {
    return null;
  }
  
  public IPdfContentBoxEncoder getGreatCurseEncoder()
  {
	return null;
  }

  public boolean hasMagicPage() {
	return false;
  }
}
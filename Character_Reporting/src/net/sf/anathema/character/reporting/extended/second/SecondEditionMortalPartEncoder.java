package net.sf.anathema.character.reporting.extended.second;

import com.lowagie.text.pdf.BaseFont;
import net.sf.anathema.character.reporting.common.encoder.IPdfContentBoxEncoder;
import net.sf.anathema.character.reporting.extended.page.AbstractSecondEditionPartEncoder;
import net.sf.anathema.lib.resources.IResources;

public class SecondEditionMortalPartEncoder extends AbstractSecondEditionPartEncoder {

  public SecondEditionMortalPartEncoder(IResources resources, BaseFont baseFont, BaseFont symbolBaseFont) {
    super(resources, baseFont, symbolBaseFont, 3);
  }

  public IPdfContentBoxEncoder getAnimaEncoder() {
    return null;
  }

  public IPdfContentBoxEncoder getGreatCurseEncoder() {
    return null;
  }

  public boolean hasMagicPage() {
    return false;
  }
}

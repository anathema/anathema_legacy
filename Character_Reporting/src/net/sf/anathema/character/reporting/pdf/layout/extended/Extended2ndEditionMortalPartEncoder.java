package net.sf.anathema.character.reporting.pdf.layout.extended;

import com.lowagie.text.pdf.BaseFont;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.IBoxContentEncoder;
import net.sf.anathema.lib.resources.IResources;

public class Extended2ndEditionMortalPartEncoder extends AbstractSecondEditionPartEncoder {

  public Extended2ndEditionMortalPartEncoder(IResources resources, BaseFont baseFont) {
    super(resources, baseFont, 3);
  }

  public IBoxContentEncoder getAnimaEncoder() {
    return null;
  }

  public IBoxContentEncoder getGreatCurseEncoder() {
    return null;
  }

  public boolean hasMagicPage() {
    return false;
  }
}

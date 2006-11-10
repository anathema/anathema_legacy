package net.sf.anathema.character.reporting.sheet.second;

import net.sf.anathema.character.generic.impl.rules.ExaltedEdition;
import net.sf.anathema.character.generic.rules.IExaltedEdition;
import net.sf.anathema.character.reporting.sheet.common.movement.AbstractHealthAndMovementEncoder;
import net.sf.anathema.character.reporting.sheet.util.IPdfTableEncoder;
import net.sf.anathema.lib.resources.IResources;

import com.lowagie.text.pdf.BaseFont;

public class SecondEditionHealthAndMovementEncoder extends AbstractHealthAndMovementEncoder {

  public SecondEditionHealthAndMovementEncoder(IResources resources, BaseFont baseFont, BaseFont symbolBaseFont) {
    super(resources, baseFont, symbolBaseFont);
  }

  @Override
  protected final IPdfTableEncoder createTableEncoder() {
    return new SecondEditionHealthAndMovemenTableEncoder(getResources(), getBaseFont());
  }

  @Override
  protected final IExaltedEdition getEdition() {
    return ExaltedEdition.SecondEdition;
  }
}
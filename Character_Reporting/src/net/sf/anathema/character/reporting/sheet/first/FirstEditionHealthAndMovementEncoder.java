package net.sf.anathema.character.reporting.sheet.first;

import net.sf.anathema.character.generic.impl.rules.ExaltedEdition;
import net.sf.anathema.character.reporting.sheet.common.movement.AbstractHealthAndMovementEncoder;
import net.sf.anathema.character.reporting.sheet.second.SecondEditionHealthAndMovemenTableEncoder;
import net.sf.anathema.character.reporting.sheet.util.IPdfTableEncoder;
import net.sf.anathema.lib.resources.IResources;

import com.lowagie.text.pdf.BaseFont;

public class FirstEditionHealthAndMovementEncoder extends AbstractHealthAndMovementEncoder {

  public FirstEditionHealthAndMovementEncoder(IResources resources, BaseFont baseFont, BaseFont symbolBaseFont) {
    super(resources, baseFont, symbolBaseFont);
  }

  @Override
  protected final ExaltedEdition getEdition() {
    return ExaltedEdition.FirstEdition;
  }

  @Override
  protected IPdfTableEncoder createTableEncoder() {
    return new SecondEditionHealthAndMovemenTableEncoder(getResources(), getBaseFont());
  }
}
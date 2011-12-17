package net.sf.anathema.character.reporting.extended.second;

import com.lowagie.text.pdf.BaseFont;
import net.sf.anathema.character.generic.impl.rules.ExaltedEdition;
import net.sf.anathema.character.generic.rules.IExaltedEdition;
import net.sf.anathema.character.reporting.common.encoder.IPdfTableEncoder;
import net.sf.anathema.character.reporting.extended.common.movement.AbstractHealthAndMovementEncoder;
import net.sf.anathema.lib.resources.IResources;

public class SecondEditionHealthAndMovementEncoder extends AbstractHealthAndMovementEncoder {

  public SecondEditionHealthAndMovementEncoder(IResources resources, BaseFont baseFont, BaseFont symbolBaseFont) {
    super(resources, baseFont, symbolBaseFont);
  }

  @Override
  protected IPdfTableEncoder createTableEncoder() {
    return new SecondEditionHealthAndMovementTableEncoder(getResources(), getBaseFont());
  }

  @Override
  protected final IExaltedEdition getEdition() {
    return ExaltedEdition.SecondEdition;
  }
}

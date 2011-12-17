package net.sf.anathema.character.reporting.extended.first;

import com.lowagie.text.pdf.BaseFont;
import net.sf.anathema.character.generic.impl.rules.ExaltedEdition;
import net.sf.anathema.character.reporting.extended.common.movement.AbstractHealthAndMovementEncoder;
import net.sf.anathema.character.reporting.encoder.IPdfTableEncoder;
import net.sf.anathema.lib.resources.IResources;

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
    return new FirstEditionHealthAndMovementTableEncoder(getResources(), getBaseFont());
  }
}
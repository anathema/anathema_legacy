package net.sf.anathema.character.reporting.pdf.rendering.boxes.health;

import com.lowagie.text.pdf.BaseFont;
import net.sf.anathema.character.generic.impl.rules.ExaltedEdition;
import net.sf.anathema.character.reporting.pdf.rendering.general.table.ITableEncoder;
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
  protected ITableEncoder createTableEncoder() {
    return new FirstEditionHealthAndMovementTableEncoder(getResources(), getBaseFont());
  }
}

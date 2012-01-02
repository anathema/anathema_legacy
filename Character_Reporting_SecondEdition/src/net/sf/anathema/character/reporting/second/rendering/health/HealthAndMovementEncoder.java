package net.sf.anathema.character.reporting.second.rendering.health;

import net.sf.anathema.character.generic.impl.rules.ExaltedEdition;
import net.sf.anathema.character.generic.rules.IExaltedEdition;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.health.AbstractHealthAndMovementEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.general.table.ITableEncoder;
import net.sf.anathema.lib.resources.IResources;

public class HealthAndMovementEncoder extends AbstractHealthAndMovementEncoder {

  public HealthAndMovementEncoder(IResources resources) {
    super(resources);
  }

  @Override
  protected ITableEncoder createTableEncoder() {
    return new HealthAndMovementTableEncoder(getResources());
  }

  @Override
  protected final IExaltedEdition getEdition() {
    return ExaltedEdition.SecondEdition;
  }
}

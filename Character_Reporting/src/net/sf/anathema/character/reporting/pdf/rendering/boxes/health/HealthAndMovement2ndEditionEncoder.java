package net.sf.anathema.character.reporting.pdf.rendering.boxes.health;

import net.sf.anathema.character.generic.impl.rules.ExaltedEdition;
import net.sf.anathema.character.generic.rules.IExaltedEdition;
import net.sf.anathema.character.reporting.pdf.rendering.general.table.ITableEncoder;
import net.sf.anathema.lib.resources.IResources;

public class HealthAndMovement2ndEditionEncoder extends AbstractHealthAndMovementEncoder {

  public HealthAndMovement2ndEditionEncoder(IResources resources) {
    super(resources);
  }

  @Override
  protected ITableEncoder createTableEncoder() {
    return new HealthAndMovement2ndEditionTableEncoder(getResources());
  }

  @Override
  protected final IExaltedEdition getEdition() {
    return ExaltedEdition.SecondEdition;
  }
}

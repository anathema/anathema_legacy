package net.sf.anathema.hero.health.sheet;

import net.sf.anathema.hero.sheet.pdf.encoder.table.ITableEncoder;
import net.sf.anathema.framework.environment.Resources;

public class HealthAndMovementEncoder extends AbstractHealthAndMovementEncoder {

  public HealthAndMovementEncoder(Resources resources) {
    super(resources);
  }

  @Override
  protected ITableEncoder createTableEncoder() {
    return new HealthAndMovementTableEncoder(getResources());
  }
}
package net.sf.anathema.character.reporting.second.rendering.health;

import net.sf.anathema.character.reporting.pdf.content.BasicContent;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.BoxContentEncoderFactory;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.health.AbstractHealthAndMovementEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.IBoxContentEncoder;
import net.sf.anathema.lib.resources.IResources;
import net.sf.anathema.lib.util.Identificate;

public class HealthAndMovementBoxEncoderFactory extends Identificate implements BoxContentEncoderFactory {

  public HealthAndMovementBoxEncoderFactory() {
    super(AbstractHealthAndMovementEncoder.ID);
  }

  @Override
  public IBoxContentEncoder create(IResources resources) {
    return new HealthAndMovementEncoder(resources);
  }

  @Override
  public boolean supports(BasicContent content) {
    return content.isSecondEdition();
  }
}

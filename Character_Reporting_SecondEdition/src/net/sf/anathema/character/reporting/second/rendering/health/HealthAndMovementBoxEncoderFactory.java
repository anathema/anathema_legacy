package net.sf.anathema.character.reporting.second.rendering.health;

import net.sf.anathema.character.reporting.pdf.content.BasicContent;
import net.sf.anathema.character.reporting.pdf.rendering.EncoderIds;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.AbstractEncoderFactory;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.RegisteredEncoderFactory;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.ContentEncoder;
import net.sf.anathema.lib.resources.IResources;

@RegisteredEncoderFactory
public class HealthAndMovementBoxEncoderFactory extends AbstractEncoderFactory {

  public HealthAndMovementBoxEncoderFactory() {
    super(EncoderIds.HEALTH_AND_MOVEMENT);
  }

  @Override
  public ContentEncoder create(IResources resources, BasicContent content) {
    return new HealthAndMovementEncoder(resources);
  }

  @Override
  public boolean supports(BasicContent content) {
    return content.isSecondEdition();
  }
}

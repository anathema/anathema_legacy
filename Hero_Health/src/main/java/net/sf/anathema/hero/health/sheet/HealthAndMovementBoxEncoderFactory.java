package net.sf.anathema.hero.health.sheet;

import net.sf.anathema.hero.sheet.pdf.content.BasicContent;
import net.sf.anathema.hero.sheet.pdf.encoder.EncoderIds;
import net.sf.anathema.hero.sheet.pdf.encoder.boxes.AbstractEncoderFactory;
import net.sf.anathema.hero.sheet.pdf.encoder.boxes.ContentEncoder;
import net.sf.anathema.hero.sheet.pdf.encoder.boxes.RegisteredEncoderFactory;
import net.sf.anathema.lib.resources.Resources;

@RegisteredEncoderFactory
public class HealthAndMovementBoxEncoderFactory extends AbstractEncoderFactory {

  public HealthAndMovementBoxEncoderFactory() {
    super(EncoderIds.HEALTH_AND_MOVEMENT);
  }

  @Override
  public ContentEncoder create(Resources resources, BasicContent content) {
    return new HealthAndMovementEncoder(resources);
  }

  @Override
  public boolean supports(BasicContent content) {
    return true;
  }
}

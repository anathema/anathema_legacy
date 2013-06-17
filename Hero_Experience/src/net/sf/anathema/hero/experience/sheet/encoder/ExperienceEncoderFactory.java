package net.sf.anathema.hero.experience.sheet.encoder;

import net.sf.anathema.character.reporting.pdf.content.BasicContent;
import net.sf.anathema.character.reporting.pdf.rendering.EncoderIds;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.AbstractEncoderFactory;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.RegisteredEncoderFactory;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.ContentEncoder;
import net.sf.anathema.lib.resources.Resources;

@RegisteredEncoderFactory
public class ExperienceEncoderFactory extends AbstractEncoderFactory {

  public ExperienceEncoderFactory() {
    super(EncoderIds.EXPERIENCE);
  }

  @Override
  public ContentEncoder create(Resources resources, BasicContent content) {
    return new ExperienceEncoder();
  }

  @Override
  public boolean supports(BasicContent content) {
    return true;
  }
}

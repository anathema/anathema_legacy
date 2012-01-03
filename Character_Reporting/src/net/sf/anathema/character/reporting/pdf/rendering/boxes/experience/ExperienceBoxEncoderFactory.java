package net.sf.anathema.character.reporting.pdf.rendering.boxes.experience;

import net.sf.anathema.character.reporting.pdf.content.BasicContent;
import net.sf.anathema.character.reporting.pdf.rendering.EncoderIds;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.AbstractBoxContentEncoderFactory;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.IBoxContentEncoder;
import net.sf.anathema.lib.resources.IResources;

public class ExperienceBoxEncoderFactory extends AbstractBoxContentEncoderFactory {

  public ExperienceBoxEncoderFactory() {
    super(EncoderIds.EXPERIENCE);
  }

  @Override
  public IBoxContentEncoder create(IResources resources, BasicContent content) {
    return new ExperienceBoxContentEncoder();
  }

  @Override
  public boolean supports(BasicContent content) {
    return true;
  }
}

package net.sf.anathema.character.reporting.pdf.rendering.boxes.experience;

import net.sf.anathema.character.reporting.pdf.content.BasicContent;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.AbstractBoxContentEncoderFactory;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.IBoxContentEncoder;
import net.sf.anathema.lib.resources.IResources;

public class ExperienceBoxEncoderFactory extends AbstractBoxContentEncoderFactory {

  public final static String ID = ExperienceBoxEncoderFactory.class.getName();

  public ExperienceBoxEncoderFactory() {
    super(ID);
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

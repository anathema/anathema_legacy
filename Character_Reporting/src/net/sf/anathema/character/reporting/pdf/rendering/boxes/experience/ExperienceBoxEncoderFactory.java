package net.sf.anathema.character.reporting.pdf.rendering.boxes.experience;

import net.sf.anathema.character.reporting.pdf.content.BasicContent;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.BoxContentEncoderFactory;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.IBoxContentEncoder;
import net.sf.anathema.lib.resources.IResources;
import net.sf.anathema.lib.util.Identificate;

public class ExperienceBoxEncoderFactory extends Identificate implements BoxContentEncoderFactory {

  public final static String ID = ExperienceBoxEncoderFactory.class.getName();

  public ExperienceBoxEncoderFactory() {
    super(ID);
  }

  @Override
  public IBoxContentEncoder create(IResources resources) {
    return new ExperienceBoxContentEncoder();
  }

  @Override
  public boolean supports(BasicContent content) {
    return true;
  }
}

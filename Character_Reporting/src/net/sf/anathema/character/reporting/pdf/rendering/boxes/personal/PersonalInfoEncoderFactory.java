package net.sf.anathema.character.reporting.pdf.rendering.boxes.personal;

import net.sf.anathema.character.reporting.pdf.content.BasicContent;
import net.sf.anathema.character.reporting.pdf.rendering.EncoderIds;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.GlobalEncoderFactory;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.ContentEncoder;
import net.sf.anathema.lib.resources.IResources;

public class PersonalInfoEncoderFactory extends GlobalEncoderFactory {

  public PersonalInfoEncoderFactory() {
    super(EncoderIds.PERSONAL_INFO);
  }

  @Override
  public ContentEncoder create(IResources resources, BasicContent content) {
    return new PersonalInfoEncoder(resources);
  }
}

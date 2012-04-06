package net.sf.anathema.character.reporting.pdf.rendering.boxes.personal;

import net.sf.anathema.character.reporting.pdf.content.BasicContent;
import net.sf.anathema.character.reporting.pdf.rendering.EncoderIds;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.GlobalEncoderFactory;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.RegisteredEncoderFactory;
import net.sf.anathema.lib.resources.IResources;

@RegisteredEncoderFactory
public class PersonalInfoEncoderFactory extends GlobalEncoderFactory {

  public PersonalInfoEncoderFactory() {
    super(EncoderIds.PERSONAL_INFO);
    setPreferredHeight(new PreferredPersonalInfoHeight());
  }

  @Override
  public PersonalInfoEncoder create(IResources resources, BasicContent content) {
    return new PersonalInfoEncoder(resources);
  }

}

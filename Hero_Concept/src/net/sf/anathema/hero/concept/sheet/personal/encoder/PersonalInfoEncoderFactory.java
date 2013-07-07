package net.sf.anathema.hero.concept.sheet.personal.encoder;

import net.sf.anathema.character.reporting.pdf.content.BasicContent;
import net.sf.anathema.character.reporting.pdf.rendering.EncoderIds;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.GlobalEncoderFactory;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.RegisteredEncoderFactory;
import net.sf.anathema.lib.resources.Resources;

@RegisteredEncoderFactory
public class PersonalInfoEncoderFactory extends GlobalEncoderFactory {

  public PersonalInfoEncoderFactory() {
    super(EncoderIds.PERSONAL_INFO);
    setPreferredHeight(new PreferredPersonalInfoHeight());
  }

  @Override
  public PersonalInfoEncoder create(Resources resources, BasicContent content) {
    return new PersonalInfoEncoder(resources);
  }
}

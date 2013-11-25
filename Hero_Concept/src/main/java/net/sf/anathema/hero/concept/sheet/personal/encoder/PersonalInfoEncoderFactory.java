package net.sf.anathema.hero.concept.sheet.personal.encoder;

import net.sf.anathema.framework.environment.Resources;
import net.sf.anathema.hero.sheet.pdf.content.BasicContent;
import net.sf.anathema.hero.sheet.pdf.encoder.EncoderIds;
import net.sf.anathema.hero.sheet.pdf.encoder.boxes.GlobalEncoderFactory;

@SuppressWarnings("UnusedDeclaration")
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

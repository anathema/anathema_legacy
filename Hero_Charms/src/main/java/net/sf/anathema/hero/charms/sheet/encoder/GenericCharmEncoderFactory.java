package net.sf.anathema.hero.charms.sheet.encoder;

import net.sf.anathema.framework.environment.Resources;
import net.sf.anathema.hero.sheet.pdf.content.BasicContent;
import net.sf.anathema.hero.sheet.pdf.encoder.EncoderIds;
import net.sf.anathema.hero.sheet.pdf.encoder.boxes.AbstractEncoderFactory;
import net.sf.anathema.hero.sheet.pdf.encoder.boxes.ContentEncoder;

@SuppressWarnings("UnusedDeclaration")
public class GenericCharmEncoderFactory extends AbstractEncoderFactory {

  public GenericCharmEncoderFactory() {
    super(EncoderIds.GENERIC_CHARMS);
    setPreferredHeight(new PreferredGenericCharmHeight());
  }

  @Override
  public ContentEncoder create(Resources resources, BasicContent content) {
    return new GenericCharmEncoder(resources);
  }

  @Override
  public boolean supports(BasicContent content) {
    return content.isEssenceUser();
  }
}

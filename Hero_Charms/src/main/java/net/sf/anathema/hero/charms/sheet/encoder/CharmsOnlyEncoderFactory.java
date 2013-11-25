package net.sf.anathema.hero.charms.sheet.encoder;

import net.sf.anathema.framework.environment.Resources;
import net.sf.anathema.hero.charms.sheet.content.CharmsOnlyContent;
import net.sf.anathema.hero.sheet.pdf.content.BasicContent;
import net.sf.anathema.hero.sheet.pdf.encoder.EncoderIds;
import net.sf.anathema.hero.sheet.pdf.encoder.boxes.AbstractEncoderFactory;
import net.sf.anathema.hero.sheet.pdf.encoder.boxes.ContentEncoder;

@SuppressWarnings("UnusedDeclaration")
public class CharmsOnlyEncoderFactory extends AbstractEncoderFactory{

  public CharmsOnlyEncoderFactory() {
    super(EncoderIds.CHARMS_ONLY);
  }

  @Override
  public ContentEncoder create(Resources resources, BasicContent content) {
    return new ExtendedMagicEncoder<>(resources, CharmsOnlyContent.class, false, "Charms");
  }

  @Override
  public boolean supports(BasicContent content) {
    return content.isEssenceUser();
  }
}

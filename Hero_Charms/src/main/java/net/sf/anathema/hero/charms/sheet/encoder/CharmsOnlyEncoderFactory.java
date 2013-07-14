package net.sf.anathema.hero.charms.sheet.encoder;

import net.sf.anathema.hero.charms.sheet.content.CharmsOnlyContent;
import net.sf.anathema.hero.magic.sheet.encoder.ExtendedMagicEncoder;
import net.sf.anathema.hero.sheet.pdf.content.BasicContent;
import net.sf.anathema.hero.sheet.pdf.encoder.EncoderIds;
import net.sf.anathema.hero.sheet.pdf.encoder.boxes.AbstractEncoderFactory;
import net.sf.anathema.hero.sheet.pdf.encoder.boxes.ContentEncoder;
import net.sf.anathema.hero.sheet.pdf.encoder.boxes.RegisteredEncoderFactory;
import net.sf.anathema.lib.resources.Resources;

@RegisteredEncoderFactory
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

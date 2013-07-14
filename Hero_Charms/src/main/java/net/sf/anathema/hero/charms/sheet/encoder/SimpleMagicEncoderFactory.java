package net.sf.anathema.hero.charms.sheet.encoder;

import net.sf.anathema.hero.sheet.pdf.content.BasicContent;
import net.sf.anathema.hero.sheet.pdf.encoder.EncoderIds;
import net.sf.anathema.hero.sheet.pdf.encoder.boxes.AbstractEncoderFactory;
import net.sf.anathema.hero.sheet.pdf.encoder.boxes.ContentEncoder;
import net.sf.anathema.hero.sheet.pdf.encoder.boxes.RegisteredEncoderFactory;
import net.sf.anathema.lib.resources.Resources;

@RegisteredEncoderFactory
public class SimpleMagicEncoderFactory extends AbstractEncoderFactory{

  public SimpleMagicEncoderFactory() {
    super(EncoderIds.CHARMS_AND_SORCERY);
  }

  @Override
  public ContentEncoder create(Resources resources, BasicContent content) {
    return new SimpleMagicEncoder();
  }

  @Override
  public boolean supports(BasicContent content) {
    return content.isEssenceUser();
  }
}

package net.sf.anathema.hero.intimacies.sheet.encoder;

import net.sf.anathema.hero.sheet.pdf.content.BasicContent;
import net.sf.anathema.hero.sheet.pdf.encoder.EncoderIds;
import net.sf.anathema.hero.sheet.pdf.encoder.boxes.AbstractEncoderFactory;
import net.sf.anathema.hero.sheet.pdf.encoder.boxes.ContentEncoder;
import net.sf.anathema.hero.sheet.pdf.encoder.boxes.RegisteredEncoderFactory;
import net.sf.anathema.lib.resources.Resources;

@RegisteredEncoderFactory
public class ExtendedIntimaciesEncoderFactory extends AbstractEncoderFactory {

  public ExtendedIntimaciesEncoderFactory() {
    super(EncoderIds.INTIMACIES_EXTENDED);
  }

  @Override
  public ContentEncoder create(Resources resources, BasicContent content) {
    return new ExtendedIntimaciesEncoder();
  }

  @Override
  public boolean supports(BasicContent content) {
    return true;
  }
}

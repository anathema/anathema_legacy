package net.sf.anathema.hero.intimacies.sheet.encoder;

import net.sf.anathema.framework.environment.Resources;
import net.sf.anathema.hero.sheet.pdf.content.BasicContent;
import net.sf.anathema.hero.sheet.pdf.encoder.EncoderIds;
import net.sf.anathema.hero.sheet.pdf.encoder.boxes.AbstractEncoderFactory;
import net.sf.anathema.hero.sheet.pdf.encoder.boxes.ContentEncoder;

@SuppressWarnings("UnusedDeclaration")
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

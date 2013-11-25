package net.sf.anathema.hero.spiritual.sheet.willpower.encoder;

import net.sf.anathema.framework.environment.Resources;
import net.sf.anathema.hero.sheet.pdf.content.BasicContent;
import net.sf.anathema.hero.sheet.pdf.encoder.EncoderIds;
import net.sf.anathema.hero.sheet.pdf.encoder.boxes.ContentEncoder;
import net.sf.anathema.hero.sheet.pdf.encoder.boxes.GlobalEncoderFactory;

@SuppressWarnings("UnusedDeclaration")
public class ExtendedWillpowerEncoderFactory extends GlobalEncoderFactory {

  public ExtendedWillpowerEncoderFactory() {
    super(EncoderIds.WILLPOWER_EXTENDED);
  }

  @Override
  public ContentEncoder create(Resources resources, BasicContent content) {
    return new ExtendedWillpowerEncoder();
  }
}

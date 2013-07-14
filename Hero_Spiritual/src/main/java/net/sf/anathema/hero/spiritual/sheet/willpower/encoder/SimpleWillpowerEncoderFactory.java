package net.sf.anathema.hero.spiritual.sheet.willpower.encoder;

import net.sf.anathema.hero.sheet.pdf.content.BasicContent;
import net.sf.anathema.hero.sheet.pdf.encoder.EncoderIds;
import net.sf.anathema.hero.sheet.pdf.encoder.boxes.ContentEncoder;
import net.sf.anathema.hero.sheet.pdf.encoder.boxes.GlobalEncoderFactory;
import net.sf.anathema.hero.sheet.pdf.encoder.boxes.RegisteredEncoderFactory;
import net.sf.anathema.lib.resources.Resources;

@RegisteredEncoderFactory
public class SimpleWillpowerEncoderFactory extends GlobalEncoderFactory {

  public SimpleWillpowerEncoderFactory() {
    super(EncoderIds.WILLPOWER_SIMPLE);
  }

  @Override
  public ContentEncoder create(Resources resources, BasicContent content) {
    return new SimpleWillpowerEncoder();
  }
}

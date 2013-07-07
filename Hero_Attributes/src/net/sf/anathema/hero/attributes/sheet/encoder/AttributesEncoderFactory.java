package net.sf.anathema.hero.attributes.sheet.encoder;

import net.sf.anathema.hero.sheet.pdf.content.BasicContent;
import net.sf.anathema.hero.sheet.pdf.encoder.EncoderIds;
import net.sf.anathema.hero.sheet.pdf.encoder.boxes.AbstractEncoderFactory;
import net.sf.anathema.hero.sheet.pdf.encoder.boxes.RegisteredEncoderFactory;
import net.sf.anathema.hero.sheet.pdf.encoder.general.box.ContentEncoder;
import net.sf.anathema.lib.resources.Resources;

@RegisteredEncoderFactory
public class AttributesEncoderFactory extends AbstractEncoderFactory {

  public AttributesEncoderFactory() {
    super(EncoderIds.ATTRIBUTES);
  }

  @Override
  public ContentEncoder create(Resources resources, BasicContent content) {
    return new AttributesEncoder();
  }

  @Override
  public boolean supports(BasicContent content) {
    return true;
  }
}

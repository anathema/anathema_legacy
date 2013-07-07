package net.sf.anathema.hero.othertraits.sheet.virtues.encoder;

import net.sf.anathema.hero.sheet.pdf.content.BasicContent;
import net.sf.anathema.hero.sheet.pdf.encoder.EncoderIds;
import net.sf.anathema.hero.sheet.pdf.encoder.boxes.GlobalEncoderFactory;
import net.sf.anathema.hero.sheet.pdf.encoder.boxes.RegisteredEncoderFactory;
import net.sf.anathema.hero.sheet.pdf.encoder.boxes.ContentEncoder;
import net.sf.anathema.lib.resources.Resources;

@RegisteredEncoderFactory
public class VirtueEncoderFactory extends GlobalEncoderFactory {

  public VirtueEncoderFactory() {
    super(EncoderIds.VIRTUES);
  }

  @Override
  public ContentEncoder create(Resources resources, BasicContent content) {
    return new VirtueEncoder();
  }
}

package net.sf.anathema.hero.othertraits.sheet.virtues.encoder;

import net.sf.anathema.character.reporting.pdf.content.BasicContent;
import net.sf.anathema.character.reporting.pdf.rendering.EncoderIds;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.GlobalEncoderFactory;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.RegisteredEncoderFactory;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.ContentEncoder;
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

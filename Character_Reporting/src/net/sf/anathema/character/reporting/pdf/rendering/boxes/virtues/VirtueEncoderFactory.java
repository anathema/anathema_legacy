package net.sf.anathema.character.reporting.pdf.rendering.boxes.virtues;

import net.sf.anathema.character.reporting.pdf.content.BasicContent;
import net.sf.anathema.character.reporting.pdf.rendering.EncoderIds;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.GlobalEncoderFactory;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.ContentEncoder;
import net.sf.anathema.lib.resources.IResources;

public class VirtueEncoderFactory extends GlobalEncoderFactory {

  public VirtueEncoderFactory() {
    super(EncoderIds.VIRTUES);
  }

  @Override
  public ContentEncoder create(IResources resources, BasicContent content) {
    return new VirtueEncoder();
  }
}

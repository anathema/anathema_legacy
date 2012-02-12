package net.sf.anathema.character.reporting.pdf.rendering.boxes.willpower;

import net.sf.anathema.character.reporting.pdf.content.BasicContent;
import net.sf.anathema.character.reporting.pdf.rendering.EncoderIds;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.GlobalEncoderFactory;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.RegisteredEncoderFactory;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.ContentEncoder;
import net.sf.anathema.lib.resources.IResources;

@RegisteredEncoderFactory
public class ExtendedWillpowerEncoderFactory extends GlobalEncoderFactory {

  public ExtendedWillpowerEncoderFactory() {
    super(EncoderIds.WILLPOWER_EXTENDED);
  }

  @Override
  public ContentEncoder create(IResources resources, BasicContent content) {
    return new ExtendedWillpowerEncoder();
  }
}

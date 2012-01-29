package net.sf.anathema.character.reporting.pdf.rendering.boxes.willpower;

import net.sf.anathema.character.reporting.pdf.content.BasicContent;
import net.sf.anathema.character.reporting.pdf.rendering.EncoderIds;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.GlobalEncoderFactory;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.RegisteredEncoderFactory;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.ContentEncoder;
import net.sf.anathema.lib.resources.IResources;

@RegisteredEncoderFactory
public class SimpleWillpowerEncoderFactory extends GlobalEncoderFactory {

  public SimpleWillpowerEncoderFactory() {
    super(EncoderIds.WILLPOWER_SIMPLE);
  }

  @Override
  public ContentEncoder create(IResources resources, BasicContent content) {
    return new SimpleWillpowerEncoder();
  }
}

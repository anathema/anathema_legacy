package net.sf.anathema.character.reporting.pdf.rendering.boxes.backgrounds;

import net.sf.anathema.character.reporting.pdf.content.BasicContent;
import net.sf.anathema.character.reporting.pdf.rendering.EncoderIds;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.AbstractEncoderFactory;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.RegisteredEncoderFactory;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.ContentEncoder;
import net.sf.anathema.lib.resources.IResources;

@RegisteredEncoderFactory
public class BackgroundsEncoderFactory extends AbstractEncoderFactory {

  public BackgroundsEncoderFactory() {
    super(EncoderIds.BACKGROUNDS);
  }

  @Override
  public ContentEncoder create(IResources resources, BasicContent content) {
    return new BackgroundsEncoder(resources);
  }

  @Override
  public boolean supports(BasicContent content) {
    return true;
  }
}

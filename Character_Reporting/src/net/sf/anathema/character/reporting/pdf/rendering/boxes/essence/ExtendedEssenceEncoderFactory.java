package net.sf.anathema.character.reporting.pdf.rendering.boxes.essence;

import net.sf.anathema.character.reporting.pdf.content.BasicContent;
import net.sf.anathema.character.reporting.pdf.rendering.EncoderIds;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.AbstractEncoderFactory;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.RegisteredEncoderFactory;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.ContentEncoder;
import net.sf.anathema.lib.resources.IResources;

@RegisteredEncoderFactory
public class ExtendedEssenceEncoderFactory extends AbstractEncoderFactory {

  public ExtendedEssenceEncoderFactory() {
    super(EncoderIds.ESSENCE_EXTENDED);
  }

  @Override
  public ContentEncoder create(IResources resources, BasicContent content) {
    return new ExtendedEssenceEncoder();
  }

  @Override
  public boolean supports(BasicContent content) {
    return content.isEssenceUser();
  }
}

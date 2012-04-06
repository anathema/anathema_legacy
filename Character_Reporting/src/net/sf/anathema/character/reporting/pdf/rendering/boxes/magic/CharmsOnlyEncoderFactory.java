package net.sf.anathema.character.reporting.pdf.rendering.boxes.magic;

import net.sf.anathema.character.reporting.pdf.content.BasicContent;
import net.sf.anathema.character.reporting.pdf.content.magic.CharmsOnlyContent;
import net.sf.anathema.character.reporting.pdf.rendering.EncoderIds;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.AbstractEncoderFactory;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.RegisteredEncoderFactory;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.ContentEncoder;
import net.sf.anathema.lib.resources.IResources;

@RegisteredEncoderFactory
public class CharmsOnlyEncoderFactory extends AbstractEncoderFactory{

  public CharmsOnlyEncoderFactory() {
    super(EncoderIds.CHARMS_ONLY);
  }

  @Override
  public ContentEncoder create(IResources resources, BasicContent content) {
    return new ExtendedMagicEncoder<CharmsOnlyContent>(resources, CharmsOnlyContent.class, false, "Charms");
  }

  @Override
  public boolean supports(BasicContent content) {
    return content.isEssenceUser();
  }
}

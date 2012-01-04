package net.sf.anathema.character.solar.reporting.rendering;

import net.sf.anathema.character.generic.type.CharacterType;
import net.sf.anathema.character.reporting.pdf.content.BasicContent;
import net.sf.anathema.character.reporting.pdf.rendering.EncoderIds;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.AbstractEncoderFactory;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.ContentEncoder;
import net.sf.anathema.lib.resources.IResources;

public class VirtueFlawEncoderFactory extends AbstractEncoderFactory {

  public VirtueFlawEncoderFactory() {
    super(EncoderIds.GREAT_CURSE);
  }

  @Override
  public ContentEncoder create(IResources resources, BasicContent content) {
    return new VirtueFlawEncoder();
  }

  @Override
  public boolean supports(BasicContent content) {
    return content.isOfType(CharacterType.SOLAR);
  }
}

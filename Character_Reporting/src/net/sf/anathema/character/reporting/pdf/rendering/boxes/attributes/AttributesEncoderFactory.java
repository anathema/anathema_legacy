package net.sf.anathema.character.reporting.pdf.rendering.boxes.attributes;

import net.sf.anathema.character.generic.type.CharacterType;
import net.sf.anathema.character.reporting.pdf.content.BasicContent;
import net.sf.anathema.character.reporting.pdf.rendering.EncoderIds;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.AbstractBoxContentEncoderFactory;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.IBoxContentEncoder;
import net.sf.anathema.lib.resources.IResources;

public class AttributesEncoderFactory extends AbstractBoxContentEncoderFactory {

  public AttributesEncoderFactory() {
    super(EncoderIds.ATTRIBUTES);
  }

  @Override
  public IBoxContentEncoder create(IResources resources, BasicContent content) {
    return new AttributesEncoder(resources, content.isOfType(CharacterType.LUNAR));
  }

  @Override
  public boolean supports(BasicContent content) {
    return true;
  }
}

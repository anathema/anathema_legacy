package net.sf.anathema.character.reporting.pdf.rendering.boxes.essence;

import net.sf.anathema.character.generic.impl.traits.EssenceTemplate;
import net.sf.anathema.character.generic.traits.types.OtherTraitType;
import net.sf.anathema.character.reporting.pdf.content.BasicContent;
import net.sf.anathema.character.reporting.pdf.rendering.EncoderIds;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.AbstractEncoderFactory;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.RegisteredEncoderFactory;
import net.sf.anathema.character.reporting.pdf.rendering.general.DotBoxContentEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.ContentEncoder;
import net.sf.anathema.lib.resources.IResources;

@RegisteredEncoderFactory
public class EssenceDotEncoderFactory extends AbstractEncoderFactory {

  public EssenceDotEncoderFactory() {
    super(EncoderIds.ESSENCE_DOTS);
  }

  @Override
  public ContentEncoder create(IResources resources, BasicContent content) {
    return new DotBoxContentEncoder(OtherTraitType.Essence, EssenceTemplate.SYSTEM_ESSENCE_MAX, resources, "Essence");
  }

  @Override
  public boolean supports(BasicContent content) {
    return true;
  }
}

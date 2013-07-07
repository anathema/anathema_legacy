package net.sf.anathema.hero.othertraits.sheet.essence.encoder;

import net.sf.anathema.character.generic.impl.traits.EssenceTemplate;
import net.sf.anathema.character.generic.traits.types.OtherTraitType;
import net.sf.anathema.character.reporting.pdf.content.BasicContent;
import net.sf.anathema.character.reporting.pdf.rendering.EncoderIds;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.AbstractEncoderFactory;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.RegisteredEncoderFactory;
import net.sf.anathema.hero.traits.sheet.encoder.DotBoxContentEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.ContentEncoder;
import net.sf.anathema.lib.resources.Resources;

@RegisteredEncoderFactory
public class EssenceDotEncoderFactory extends AbstractEncoderFactory {

  public EssenceDotEncoderFactory() {
    super(EncoderIds.ESSENCE_DOTS);
  }

  @Override
  public ContentEncoder create(Resources resources, BasicContent content) {
    return new DotBoxContentEncoder(OtherTraitType.Essence, EssenceTemplate.SYSTEM_ESSENCE_MAX, resources, "Essence");
  }

  @Override
  public boolean supports(BasicContent content) {
    return true;
  }
}

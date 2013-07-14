package net.sf.anathema.hero.spiritual.sheet.essence.encoder;

import net.sf.anathema.character.main.traits.EssenceTemplate;
import net.sf.anathema.character.main.traits.types.OtherTraitType;
import net.sf.anathema.hero.sheet.pdf.content.BasicContent;
import net.sf.anathema.hero.sheet.pdf.encoder.EncoderIds;
import net.sf.anathema.hero.sheet.pdf.encoder.boxes.AbstractEncoderFactory;
import net.sf.anathema.hero.sheet.pdf.encoder.boxes.ContentEncoder;
import net.sf.anathema.hero.sheet.pdf.encoder.boxes.RegisteredEncoderFactory;
import net.sf.anathema.hero.traits.sheet.encoder.DotBoxContentEncoder;
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

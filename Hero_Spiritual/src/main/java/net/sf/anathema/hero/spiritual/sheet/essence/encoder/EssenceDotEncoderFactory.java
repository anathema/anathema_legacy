package net.sf.anathema.hero.spiritual.sheet.essence.encoder;

import net.sf.anathema.character.main.traits.SystemConstants;
import net.sf.anathema.character.main.traits.types.OtherTraitType;
import net.sf.anathema.framework.environment.Resources;
import net.sf.anathema.hero.sheet.pdf.content.BasicContent;
import net.sf.anathema.hero.sheet.pdf.encoder.EncoderIds;
import net.sf.anathema.hero.sheet.pdf.encoder.boxes.AbstractEncoderFactory;
import net.sf.anathema.hero.sheet.pdf.encoder.boxes.ContentEncoder;
import net.sf.anathema.hero.traits.sheet.encoder.DotBoxContentEncoder;

@SuppressWarnings("UnusedDeclaration")
public class EssenceDotEncoderFactory extends AbstractEncoderFactory {

  public EssenceDotEncoderFactory() {
    super(EncoderIds.ESSENCE_DOTS);
  }

  @Override
  public ContentEncoder create(Resources resources, BasicContent content) {
    return new DotBoxContentEncoder(OtherTraitType.Essence, SystemConstants.SYSTEM_ESSENCE_MAX, resources, "Essence");
  }

  @Override
  public boolean supports(BasicContent content) {
    return true;
  }
}

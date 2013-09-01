package net.sf.anathema.hero.spells.sheet.encoder;

import net.sf.anathema.hero.charms.sheet.encoder.ExtendedMagicEncoder;
import net.sf.anathema.hero.sheet.pdf.content.BasicContent;
import net.sf.anathema.hero.sheet.pdf.encoder.EncoderIds;
import net.sf.anathema.hero.sheet.pdf.encoder.boxes.AbstractEncoderFactory;
import net.sf.anathema.hero.sheet.pdf.encoder.boxes.ContentEncoder;
import net.sf.anathema.hero.sheet.pdf.encoder.boxes.RegisteredEncoderFactory;
import net.sf.anathema.hero.spells.sheet.content.SpellsOnlyContent;
import net.sf.anathema.framework.environment.Resources;

@RegisteredEncoderFactory
public class SpellsOnlyEncoderFactory extends AbstractEncoderFactory{

  public SpellsOnlyEncoderFactory() {
    super(EncoderIds.SPELLS_ONLY);
  }

  @Override
  public ContentEncoder create(Resources resources, BasicContent content) {
    return new ExtendedMagicEncoder<>(resources, SpellsOnlyContent.class, false, "Magic");
  }

  @Override
  public boolean supports(BasicContent content) {
    return content.isEssenceUser();
  }
}

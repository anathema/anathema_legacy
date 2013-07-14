package net.sf.anathema.hero.abilities.sheet.encoder;

import net.sf.anathema.hero.abilities.sheet.content.AbilitiesContent;
import net.sf.anathema.hero.sheet.pdf.content.BasicContent;
import net.sf.anathema.hero.sheet.pdf.encoder.EncoderIds;
import net.sf.anathema.hero.sheet.pdf.encoder.boxes.ContentEncoder;
import net.sf.anathema.hero.sheet.pdf.encoder.boxes.GlobalEncoderFactory;
import net.sf.anathema.hero.sheet.pdf.encoder.boxes.RegisteredEncoderFactory;
import net.sf.anathema.hero.traits.sheet.content.PdfTraitEncoder;
import net.sf.anathema.hero.traits.sheet.encoder.FavorableTraitContentEncoder;
import net.sf.anathema.lib.resources.Resources;

@RegisteredEncoderFactory
public class AbilitiesWithCraftAndSpecialtiesEncoderFactory extends GlobalEncoderFactory {

  public AbilitiesWithCraftAndSpecialtiesEncoderFactory() {
    super(EncoderIds.ABILITIES_WITH_SPECIALTIES);
  }

  @Override
  public ContentEncoder create(Resources resources, BasicContent content) {
    FavorableTraitContentEncoder<AbilitiesContent> encoder = new FavorableTraitContentEncoder<>(AbilitiesContent.class);
    PdfTraitEncoder traitEncoder = encoder.getTraitEncoder();
    encoder.addNamedTraitEncoder(new SpecialtiesEncoder(resources, traitEncoder, 9));
    return encoder;
  }
}

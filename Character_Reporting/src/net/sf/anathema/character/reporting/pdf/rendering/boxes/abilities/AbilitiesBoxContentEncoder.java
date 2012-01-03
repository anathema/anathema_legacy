package net.sf.anathema.character.reporting.pdf.rendering.boxes.abilities;

import net.sf.anathema.character.reporting.pdf.content.abilities.AbilitiesContent;
import net.sf.anathema.character.reporting.pdf.rendering.general.traits.FavorableTraitBoxContentEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.general.traits.PdfTraitEncoder;
import net.sf.anathema.lib.resources.IResources;

public class AbilitiesBoxContentEncoder {

  public static FavorableTraitBoxContentEncoder createWithCraftsOnly(IResources resources, int craftCount) {
    FavorableTraitBoxContentEncoder encoder = new FavorableTraitBoxContentEncoder(AbilitiesContent.class);
    PdfTraitEncoder traitEncoder = encoder.getTraitEncoder();
    CraftEncoder craftEncoder = new CraftEncoder(resources, traitEncoder, craftCount);
    encoder.addNamedTraitEncoder(craftEncoder);
    return encoder;
  }

  public static FavorableTraitBoxContentEncoder createWithSpecialtiesOnly(IResources resources, int specialtyCount) {
    FavorableTraitBoxContentEncoder encoder = new FavorableTraitBoxContentEncoder(AbilitiesContent.class);
    PdfTraitEncoder traitEncoder = encoder.getTraitEncoder();
    encoder.addNamedTraitEncoder(new SpecialtiesEncoder(resources, traitEncoder, specialtyCount));
    return encoder;
  }

  public static FavorableTraitBoxContentEncoder createWithCraftsAndSpecialties(IResources resources, int craftCount, int specialtyCount) {
    FavorableTraitBoxContentEncoder encoder = new FavorableTraitBoxContentEncoder(AbilitiesContent.class);
    PdfTraitEncoder traitEncoder = encoder.getTraitEncoder();
    encoder.addNamedTraitEncoder(new CraftEncoder(resources, traitEncoder, craftCount));
    encoder.addNamedTraitEncoder(new SpecialtiesEncoder(resources, traitEncoder, specialtyCount));
    return encoder;
  }
}

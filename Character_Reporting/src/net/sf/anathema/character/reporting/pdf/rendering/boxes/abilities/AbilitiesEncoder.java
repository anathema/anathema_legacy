package net.sf.anathema.character.reporting.pdf.rendering.boxes.abilities;

import net.sf.anathema.character.reporting.pdf.content.abilities.AbilitiesContent;
import net.sf.anathema.character.reporting.pdf.rendering.general.traits.FavorableTraitContentEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.general.traits.PdfTraitEncoder;
import net.sf.anathema.lib.resources.IResources;

public class AbilitiesEncoder {

  public static FavorableTraitContentEncoder<AbilitiesContent> createWithCraftsOnly(IResources resources, int craftCount) {
    FavorableTraitContentEncoder<AbilitiesContent> encoder = new FavorableTraitContentEncoder<AbilitiesContent>(AbilitiesContent.class);
    PdfTraitEncoder traitEncoder = encoder.getTraitEncoder();
    CraftEncoder craftEncoder = new CraftEncoder(resources, traitEncoder, craftCount);
    encoder.addNamedTraitEncoder(craftEncoder);
    return encoder;
  }

  public static FavorableTraitContentEncoder<AbilitiesContent> createWithSpecialtiesOnly(IResources resources, int specialtyCount) {
    FavorableTraitContentEncoder<AbilitiesContent> encoder = new FavorableTraitContentEncoder<AbilitiesContent>(AbilitiesContent.class);
    PdfTraitEncoder traitEncoder = encoder.getTraitEncoder();
    encoder.addNamedTraitEncoder(new SpecialtiesEncoder(resources, traitEncoder, specialtyCount));
    return encoder;
  }

  public static FavorableTraitContentEncoder<AbilitiesContent> createWithCraftsAndSpecialties(IResources resources, int craftCount, int specialtyCount) {
    FavorableTraitContentEncoder<AbilitiesContent> encoder = new FavorableTraitContentEncoder<AbilitiesContent>(AbilitiesContent.class);
    PdfTraitEncoder traitEncoder = encoder.getTraitEncoder();
    encoder.addNamedTraitEncoder(new CraftEncoder(resources, traitEncoder, craftCount));
    encoder.addNamedTraitEncoder(new SpecialtiesEncoder(resources, traitEncoder, specialtyCount));
    return encoder;
  }
}

package net.sf.anathema.character.reporting.pdf.rendering.boxes.abilities;

import net.sf.anathema.character.reporting.pdf.content.abilities.AbilitiesContent;
import net.sf.anathema.character.reporting.pdf.rendering.general.traits.FavorableTraitContentEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.general.traits.PdfTraitEncoder;
import net.sf.anathema.lib.resources.Resources;

public class AbilitiesEncoder {

  public static FavorableTraitContentEncoder<AbilitiesContent> createWithCraftsOnly(Resources resources, int craftCount) {
    FavorableTraitContentEncoder<AbilitiesContent> encoder = new FavorableTraitContentEncoder<>(AbilitiesContent.class);
    PdfTraitEncoder traitEncoder = encoder.getTraitEncoder();
    CraftEncoder craftEncoder = new CraftEncoder(resources, traitEncoder, craftCount);
    encoder.addNamedTraitEncoder(craftEncoder);
    return encoder;
  }
}
package net.sf.anathema.character.reporting.pdf.rendering.boxes.abilities;

import com.lowagie.text.pdf.BaseFont;
import net.sf.anathema.character.reporting.pdf.content.ReportContent;
import net.sf.anathema.character.reporting.pdf.content.abilities.AbilitiesContent;
import net.sf.anathema.character.reporting.pdf.content.traits.FavorableTraitContent;
import net.sf.anathema.character.reporting.pdf.rendering.general.traits.FavorableTraitBoxContentEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.general.traits.PdfTraitEncoder;
import net.sf.anathema.lib.resources.IResources;

public class AbilitiesBoxContentEncoder {

  public static FavorableTraitBoxContentEncoder createWithCraftsOnly(BaseFont baseFont, IResources resources, int essenceMax, int craftCount) {
    FavorableTraitBoxContentEncoder encoder = new FavorableTraitBoxContentEncoder(AbilitiesContent.class);
    PdfTraitEncoder traitEncoder = encoder.getTraitEncoder();
    CraftEncoder craftEncoder = new CraftEncoder(resources, baseFont, traitEncoder, essenceMax, craftCount);
    encoder.addNamedTraitEncoder(craftEncoder);
    return encoder;
  }

  public static FavorableTraitBoxContentEncoder createWithSpecialtiesOnly(BaseFont baseFont, IResources resources, int essenceMax, int specialtyCount) {
    FavorableTraitBoxContentEncoder encoder = new FavorableTraitBoxContentEncoder(AbilitiesContent.class);
    PdfTraitEncoder traitEncoder = encoder.getTraitEncoder();
    encoder.addNamedTraitEncoder(new SpecialtiesEncoder(resources, baseFont, traitEncoder, specialtyCount));
    return encoder;
  }

  public static FavorableTraitBoxContentEncoder createWithCraftsAndSpecialties(BaseFont baseFont, IResources resources, int essenceMax, int craftCount,
    int specialtyCount) {
    FavorableTraitBoxContentEncoder encoder = new FavorableTraitBoxContentEncoder(AbilitiesContent.class);
    PdfTraitEncoder traitEncoder = encoder.getTraitEncoder();
    encoder.addNamedTraitEncoder(new CraftEncoder(resources, baseFont, traitEncoder, essenceMax, craftCount));
    encoder.addNamedTraitEncoder(new SpecialtiesEncoder(resources, baseFont, traitEncoder, specialtyCount));
    return encoder;
  }
}

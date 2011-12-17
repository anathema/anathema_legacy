package net.sf.anathema.character.reporting.sheet.common;

import java.util.ArrayList;
import java.util.List;

import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfContentByte;

import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.framework.configuration.AnathemaCharacterPreferences;
import net.sf.anathema.character.generic.traits.INamedGenericTrait;
import net.sf.anathema.character.generic.traits.types.AbilityType;
import net.sf.anathema.character.reporting.sheet.util.PdfTraitEncoder;
import net.sf.anathema.character.reporting.stats.*;
import net.sf.anathema.character.reporting.util.Position;
import net.sf.anathema.lib.resources.IResources;

public class CraftEncoder extends AbstractNamedTraitEncoder {

  private final int essenceMax;

  public CraftEncoder(IResources resources, BaseFont baseFont, PdfTraitEncoder encoder, int essenceMax) {
    super(resources, baseFont, encoder, 9);
    this.essenceMax = essenceMax;
  }

  public int encode(PdfContentByte directContent, IGenericCharacter character, Position position, float width) {
    String title = getResources().getString("Sheet.AbilitySubHeader.Crafts"); //$NON-NLS-1$
    INamedGenericTrait[] traits = character.getSubTraits(AbilityType.Craft);
    if (!AnathemaCharacterPreferences.getDefaultPreferences().printZeroCrafts()) {
      List<INamedGenericTrait> nonZeroCrafts = new ArrayList<INamedGenericTrait>();
      for (INamedGenericTrait trait : traits) {
        if (trait.getCurrentValue() != 0) {
          nonZeroCrafts.add(trait);
        }
      }
      traits = nonZeroCrafts.toArray(new INamedGenericTrait[nonZeroCrafts.size()]);
    }
    IValuedTraitReference[] crafts = getTraitReferences(traits, AbilityType.Craft);
    return drawNamedTraitSection(directContent, title, crafts, position, width, essenceMax);
  }
}
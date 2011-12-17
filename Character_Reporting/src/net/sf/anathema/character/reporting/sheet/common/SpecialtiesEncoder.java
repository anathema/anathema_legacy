package net.sf.anathema.character.reporting.sheet.common;

import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfContentByte;
import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.generic.traits.groups.IIdentifiedTraitTypeGroup;
import net.sf.anathema.character.reporting.common.Position;
import net.sf.anathema.character.reporting.common.stats.IValuedTraitReference;
import net.sf.anathema.character.reporting.common.encoder.PdfTraitEncoder;
import net.sf.anathema.lib.resources.IResources;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SpecialtiesEncoder extends AbstractNamedTraitEncoder {

  public SpecialtiesEncoder(IResources resources, BaseFont baseFont, PdfTraitEncoder encoder, int specialtyCount) {
    super(resources, baseFont, encoder, specialtyCount);
  }

  public int encode(PdfContentByte directContent, IGenericCharacter character, Position position, float width) {
    String title = getResources().getString("Sheet.AbilitySubHeader.Specialties"); //$NON-NLS-1$
    List<IValuedTraitReference> references = new ArrayList<IValuedTraitReference>();
    for (IIdentifiedTraitTypeGroup group : character.getAbilityTypeGroups()) {
      for (ITraitType traitType : group.getAllGroupTypes()) {
        Collections.addAll(references, getTraitReferences(character.getSpecialties(traitType), traitType));
      }
    }
    IValuedTraitReference[] specialties = references.toArray(new IValuedTraitReference[references.size()]);
    return drawNamedTraitSection(directContent, title, specialties, position, width, 3);
  }
}

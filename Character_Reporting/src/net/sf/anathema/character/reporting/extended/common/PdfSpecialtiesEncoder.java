package net.sf.anathema.character.reporting.extended.common;

import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfContentByte;
import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.character.IGenericDescription;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.generic.traits.groups.IIdentifiedTraitTypeGroup;
import net.sf.anathema.character.reporting.extended.util.PdfTraitEncoder;
import net.sf.anathema.character.reporting.common.pageformat.IVoidStateFormatConstants;
import net.sf.anathema.character.reporting.common.stats.IValuedTraitReference;
import net.sf.anathema.character.reporting.common.Bounds;
import net.sf.anathema.character.reporting.common.Position;
import net.sf.anathema.lib.resources.IResources;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class PdfSpecialtiesEncoder extends AbstractNamedTraitEncoder implements IPdfContentBoxEncoder {

  public PdfSpecialtiesEncoder(IResources resources, BaseFont baseFont) {
    super(resources, baseFont, PdfTraitEncoder.createSmallTraitEncoder(baseFont));
  }

  public String getHeaderKey(IGenericCharacter character, IGenericDescription description) {
    return "Specialties"; //$NON-NLS-1$
  }

  public void encode(PdfContentByte directContent, IGenericCharacter character, IGenericDescription description, Bounds bounds) {
    List<IValuedTraitReference> references = new ArrayList<IValuedTraitReference>();
    for (IIdentifiedTraitTypeGroup group : character.getAbilityTypeGroups()) {
      for (ITraitType traitType : group.getAllGroupTypes()) {
        Collections.addAll(references, getTraitReferences(character.getSpecialties(traitType), traitType));
      }
    }
    IValuedTraitReference[] specialties = references.toArray(new IValuedTraitReference[references.size()]);
    
    int lineCount = getLineCount(null, bounds.height);
    IValuedTraitReference[] leftSpecialties = Arrays.copyOfRange(specialties,
                                                                 0, Math.min(specialties.length, lineCount));
    IValuedTraitReference[] rightSpecialties = Arrays.copyOfRange(specialties,
                                                                  leftSpecialties.length, specialties.length);
    
    float columnWidth = (bounds.width - IVoidStateFormatConstants.PADDING) / 2f;
    float columnHeight = bounds.height - IVoidStateFormatConstants.TEXT_PADDING / 2f;
    float yPosition = bounds.getMaxY() - IVoidStateFormatConstants.BARE_LINE_HEIGHT;
    
    float leftPosition = bounds.getMinX();
    drawNamedTraitSection(directContent, null, leftSpecialties,
                          new Position(leftPosition, yPosition),
                          columnWidth, columnHeight, 3);

    float rightPosition = leftPosition + columnWidth + IVoidStateFormatConstants.PADDING;
    drawNamedTraitSection(directContent, null, rightSpecialties,
                          new Position(rightPosition, yPosition),
                          columnWidth, columnHeight, 3);
  }
  
  public boolean hasContent(IGenericCharacter character) {
    return true;
  }
}

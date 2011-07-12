package net.sf.anathema.character.reporting.sheet.common;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.generic.traits.groups.IIdentifiedTraitTypeGroup;
import net.sf.anathema.character.reporting.sheet.pageformat.IVoidStateFormatConstants;
import net.sf.anathema.character.reporting.sheet.util.PdfTraitEncoder;
import net.sf.anathema.character.reporting.util.Bounds;
import net.sf.anathema.character.reporting.util.Position;
import net.sf.anathema.lib.resources.IResources;

import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfContentByte;

public class PdfSpecialtiesEncoder extends AbstractNamedTraitEncoder implements IPdfContentBoxEncoder  {

  public PdfSpecialtiesEncoder(IResources resources, BaseFont baseFont, int lineCount) {
    super(resources, baseFont, PdfTraitEncoder.createSmallTraitEncoder(baseFont), lineCount);
  }

  public String getHeaderKey() {
    return "Specialties"; //$NON-NLS-1$
  }
  
  public void encode(PdfContentByte directContent, IGenericCharacter character, Bounds bounds) {
    encode(directContent, character, new Position(bounds.getMinX(), bounds.getMaxY()), bounds.width);
  }

  public int encode(PdfContentByte directContent, IGenericCharacter character, Position position, float width) {
    List<IValuedTraitReference> references = new ArrayList<IValuedTraitReference>();
    for (IIdentifiedTraitTypeGroup group : character.getAbilityTypeGroups()) {
      for (ITraitType traitType : group.getAllGroupTypes()) {
        Collections.addAll(references, getTraitReferences(character.getSpecialties(traitType), traitType));
      }
    }
    IValuedTraitReference[] specialties = references.toArray(new IValuedTraitReference[references.size()]);
    

    IValuedTraitReference[] leftSpecialties = Arrays.copyOfRange(specialties,
                                                                 0, Math.min(specialties.length, getLineCount()));
    IValuedTraitReference[] rightSpecialties = Arrays.copyOfRange(specialties,
                                                                  leftSpecialties.length, specialties.length);
    
    /*
    IValuedTraitReference[] leftSpecialties = new IValuedTraitReference[(references.size() + 1) / 2];
    IValuedTraitReference[] rightSpecialties = new IValuedTraitReference[references.size() / 2];
    for (int i = 0; i < references.size(); i++) {
      if (i % 2 == 0)
      {
        leftSpecialties[i / 2] = references.get(i);
      }
      else
      {
        rightSpecialties[i / 2] = references.get(i);
      }
    }
    */
    
    float columnWidth = (width - IVoidStateFormatConstants.PADDING) / 2f;
    float yPosition = position.y - IVoidStateFormatConstants.LINE_HEIGHT;
    
    float leftPosition = position.x;
    drawNamedTraitSection(directContent, null, leftSpecialties,
                          new Position(leftPosition, yPosition), columnWidth, 3);

    float rightPosition = leftPosition + columnWidth + IVoidStateFormatConstants.PADDING;
    return drawNamedTraitSection(directContent, null, rightSpecialties,
                                 new Position(rightPosition, yPosition), columnWidth, 3);
  }
  
  public boolean hasContent(IGenericCharacter character)
  {
    return true;
  }
}
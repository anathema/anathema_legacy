package net.sf.anathema.character.reporting.pdf.rendering.boxes.abilities;

import com.lowagie.text.pdf.BaseFont;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.generic.traits.groups.IIdentifiedTraitTypeGroup;
import net.sf.anathema.character.reporting.pdf.content.ReportContent;
import net.sf.anathema.character.reporting.pdf.content.stats.IValuedTraitReference;
import net.sf.anathema.character.reporting.pdf.rendering.Bounds;
import net.sf.anathema.character.reporting.pdf.rendering.Position;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.ContentEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.general.traits.AbstractNamedTraitEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.general.traits.PdfTraitEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.graphics.SheetGraphics;
import net.sf.anathema.character.reporting.pdf.rendering.page.IVoidStateFormatConstants;
import net.sf.anathema.lib.resources.IResources;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ExtendedSpecialtiesEncoder extends AbstractNamedTraitEncoder implements ContentEncoder {

  public ExtendedSpecialtiesEncoder(IResources resources, BaseFont baseFont) {
    super(resources, PdfTraitEncoder.createSmallTraitEncoder());
  }

  public String getHeaderKey(ReportContent content) {
    return "Specialties"; //$NON-NLS-1$
  }

  public void encode(SheetGraphics graphics, ReportContent reportContent, Bounds bounds) {
    List<IValuedTraitReference> references = new ArrayList<IValuedTraitReference>();
    for (IIdentifiedTraitTypeGroup group : reportContent.getCharacter().getAbilityTypeGroups()) {
      for (ITraitType traitType : group.getAllGroupTypes()) {
        Collections.addAll(references, getTraitReferences(reportContent.getCharacter().getSpecialties(traitType), traitType));
      }
    }
    IValuedTraitReference[] specialties = references.toArray(new IValuedTraitReference[references.size()]);

    int lineCount = getLineCount(null, bounds.height);
    IValuedTraitReference[] leftSpecialties = Arrays.copyOfRange(specialties, 0, Math.min(specialties.length, lineCount));
    IValuedTraitReference[] rightSpecialties = Arrays.copyOfRange(specialties, leftSpecialties.length, specialties.length);

    float columnWidth = (bounds.width - IVoidStateFormatConstants.PADDING) / 2f;
    float columnHeight = bounds.height - IVoidStateFormatConstants.TEXT_PADDING / 2f;
    float yPosition = bounds.getMaxY() - IVoidStateFormatConstants.BARE_LINE_HEIGHT;

    float leftPosition = bounds.getMinX();
    drawNamedTraitSection(graphics, null, leftSpecialties, new Position(leftPosition, yPosition), columnWidth, columnHeight, 3);

    float rightPosition = leftPosition + columnWidth + IVoidStateFormatConstants.PADDING;
    drawNamedTraitSection(graphics, null, rightSpecialties, new Position(rightPosition, yPosition), columnWidth, columnHeight, 3);
  }

  public boolean hasContent(ReportContent content) {
    return true;
  }
}

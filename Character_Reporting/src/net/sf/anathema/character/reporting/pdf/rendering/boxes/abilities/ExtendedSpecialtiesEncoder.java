package net.sf.anathema.character.reporting.pdf.rendering.boxes.abilities;

import com.lowagie.text.pdf.BaseFont;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.generic.traits.groups.IIdentifiedTraitTypeGroup;
import net.sf.anathema.character.reporting.pdf.content.ReportContent;
import net.sf.anathema.character.reporting.pdf.content.stats.IValuedTraitReference;
import net.sf.anathema.character.reporting.pdf.rendering.elements.Position;
import net.sf.anathema.character.reporting.pdf.rendering.general.PdfGraphics;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.IBoxContentEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.general.traits.AbstractNamedTraitEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.general.traits.PdfTraitEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.page.IVoidStateFormatConstants;
import net.sf.anathema.lib.resources.IResources;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ExtendedSpecialtiesEncoder extends AbstractNamedTraitEncoder implements IBoxContentEncoder {

  public ExtendedSpecialtiesEncoder(IResources resources, BaseFont baseFont) {
    super(resources, baseFont, PdfTraitEncoder.createSmallTraitEncoder(baseFont));
  }

  public String getHeaderKey(ReportContent reportContent) {
    return "Specialties"; //$NON-NLS-1$
  }

  public void encode(PdfGraphics graphics, ReportContent reportContent) {
    List<IValuedTraitReference> references = new ArrayList<IValuedTraitReference>();
    for (IIdentifiedTraitTypeGroup group : reportContent.getCharacter().getAbilityTypeGroups()) {
      for (ITraitType traitType : group.getAllGroupTypes()) {
        Collections.addAll(references, getTraitReferences(reportContent.getCharacter().getSpecialties(traitType), traitType));
      }
    }
    IValuedTraitReference[] specialties = references.toArray(new IValuedTraitReference[references.size()]);

    int lineCount = getLineCount(null, graphics.getBounds().height);
    IValuedTraitReference[] leftSpecialties = Arrays.copyOfRange(specialties, 0, Math.min(specialties.length, lineCount));
    IValuedTraitReference[] rightSpecialties = Arrays.copyOfRange(specialties, leftSpecialties.length, specialties.length);

    float columnWidth = (graphics.getBounds().width - IVoidStateFormatConstants.PADDING) / 2f;
    float columnHeight = graphics.getBounds().height - IVoidStateFormatConstants.TEXT_PADDING / 2f;
    float yPosition = graphics.getBounds().getMaxY() - IVoidStateFormatConstants.BARE_LINE_HEIGHT;

    float leftPosition = graphics.getBounds().getMinX();
    drawNamedTraitSection(graphics.getDirectContent(), null, leftSpecialties, new Position(leftPosition, yPosition), columnWidth, columnHeight, 3);

    float rightPosition = leftPosition + columnWidth + IVoidStateFormatConstants.PADDING;
    drawNamedTraitSection(graphics.getDirectContent(), null, rightSpecialties, new Position(rightPosition, yPosition), columnWidth, columnHeight, 3);
  }

  public boolean hasContent(ReportContent content) {
    return true;
  }
}

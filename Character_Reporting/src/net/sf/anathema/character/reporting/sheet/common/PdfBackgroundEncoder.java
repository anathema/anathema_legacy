package net.sf.anathema.character.reporting.sheet.common;

import static net.sf.anathema.character.reporting.sheet.pageformat.IVoidStateFormatConstants.LINE_HEIGHT;
import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.impl.backgrounds.CustomizedBackgroundTemplate;
import net.sf.anathema.character.generic.traits.IGenericTrait;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.reporting.sheet.util.PdfTraitEncoder;
import net.sf.anathema.character.reporting.util.Bounds;
import net.sf.anathema.character.reporting.util.Position;
import net.sf.anathema.lib.resources.IResources;

import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfContentByte;

public class PdfBackgroundEncoder implements IPdfContentBoxEncoder {

  private final IResources resources;
  private final PdfTraitEncoder traitEncoder;

  public PdfBackgroundEncoder(IResources resources, BaseFont baseFont) {
    this.resources = resources;
    this.traitEncoder = PdfTraitEncoder.createSmallTraitEncoder(baseFont);
  }
  
  public String getHeaderKey() {
    return "Backgrounds"; //$NON-NLS-1$
  }

  public void encode(PdfContentByte directContent, IGenericCharacter character, Bounds bounds) throws DocumentException {
    float yPosition = bounds.getMaxY() - LINE_HEIGHT;
    for (IGenericTrait background : character.getBackgrounds()) {
      if (yPosition < bounds.getMinY()) {
        return;
      }
      ITraitType backgroundType = background.getType();
      String backgroundName = getBackgroundName(backgroundType);
      Position position = new Position(bounds.x, yPosition);
      int value = background.getCurrentValue();
      traitEncoder.encodeWithText(directContent, backgroundName, position, bounds.width, value, 5);
      yPosition -= LINE_HEIGHT;
    }
    encodeEmptyLines(directContent, bounds, yPosition);
  }

  private String getBackgroundName(ITraitType backgroundType) {
    String backgroundId = backgroundType.getId();
    if (backgroundType instanceof CustomizedBackgroundTemplate) {
      return backgroundId;
    }
    return resources.getString("BackgroundType.Name." + backgroundType.getId()); //$NON-NLS-1$
  }

  private void encodeEmptyLines(PdfContentByte directContent, Bounds bounds, float yPosition) {
    while (yPosition > bounds.getMinY()) {
      Position position = new Position(bounds.x, yPosition);
      traitEncoder.encodeWithLine(directContent, position, bounds.width, 0, 5);
      yPosition -= LINE_HEIGHT;
    }
  }
}
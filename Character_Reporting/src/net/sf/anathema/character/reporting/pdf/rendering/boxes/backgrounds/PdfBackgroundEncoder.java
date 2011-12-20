package net.sf.anathema.character.reporting.pdf.rendering.boxes.backgrounds;

import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.BaseFont;
import net.sf.anathema.character.generic.backgrounds.IBackgroundInfo;
import net.sf.anathema.character.generic.framework.configuration.AnathemaCharacterPreferences;
import net.sf.anathema.character.generic.traits.IGenericTrait;
import net.sf.anathema.character.reporting.pdf.content.ReportContent;
import net.sf.anathema.character.reporting.pdf.rendering.elements.Bounds;
import net.sf.anathema.character.reporting.pdf.rendering.elements.Position;
import net.sf.anathema.character.reporting.pdf.rendering.general.SheetGraphics;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.IBoxContentEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.general.traits.PdfTraitEncoder;
import net.sf.anathema.lib.resources.IResources;

import static net.sf.anathema.character.reporting.pdf.rendering.page.IVoidStateFormatConstants.LINE_HEIGHT;

public class PdfBackgroundEncoder implements IBoxContentEncoder {
  // TODO: Give this and PdfIntimacyEncoder a common base class, which may be more broadly useful.

  private final IResources resources;
  private final PdfTraitEncoder traitEncoder;

  public PdfBackgroundEncoder(IResources resources, BaseFont baseFont) {
    this.resources = resources;
    this.traitEncoder = PdfTraitEncoder.createSmallTraitEncoder();
  }

  public String getHeaderKey(ReportContent content) {
    return "Backgrounds"; //$NON-NLS-1$
  }

  public void encode(SheetGraphics graphics, ReportContent reportContent, Bounds bounds) throws DocumentException {
    float yPosition = bounds.getMaxY() - LINE_HEIGHT;
    boolean printZeroBackgrounds = AnathemaCharacterPreferences.getDefaultPreferences().printZeroBackgrounds();
    for (IGenericTrait background : reportContent.getCharacter().getBackgrounds()) {
      if (yPosition < bounds.getMinY()) {
        return;
      }
      if (!printZeroBackgrounds && background.getCurrentValue() == 0) {
        continue;
      }
      String backgroundName = getBackgroundName((IBackgroundInfo) background);
      Position position = new Position(bounds.x, yPosition);
      int value = background.getCurrentValue();
      traitEncoder.encodeWithText(graphics, backgroundName, position, bounds.width, value, 5);
      yPosition -= LINE_HEIGHT;
    }
    encodeEmptyLines(graphics, bounds, yPosition);
  }

  private String getBackgroundName(IBackgroundInfo background) {
    String backgroundName = background.getName(resources);
    String description = background.getDescription();
    return backgroundName + (description != null ? " (" + description + ")" : "");
  }

  private void encodeEmptyLines(SheetGraphics graphics, Bounds bounds, float yPosition) {
    while (yPosition > bounds.getMinY()) {
      Position position = new Position(bounds.x, yPosition);
      traitEncoder.encodeWithLine(graphics, position, bounds.width, 0, 5);
      yPosition -= LINE_HEIGHT;
    }
  }

  public boolean hasContent(ReportContent content) {
    return true;
  }
}

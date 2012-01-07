package net.sf.anathema.character.reporting.pdf.rendering.boxes.backgrounds;

import com.lowagie.text.DocumentException;
import net.sf.anathema.character.generic.backgrounds.IBackgroundInfo;
import net.sf.anathema.character.generic.framework.configuration.AnathemaCharacterPreferences;
import net.sf.anathema.character.generic.traits.IGenericTrait;
import net.sf.anathema.character.reporting.pdf.content.ReportContent;
import net.sf.anathema.character.reporting.pdf.rendering.extent.Bounds;
import net.sf.anathema.character.reporting.pdf.rendering.extent.Position;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.ContentEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.general.traits.PdfTraitEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.graphics.SheetGraphics;
import net.sf.anathema.lib.resources.IResources;

import static net.sf.anathema.character.reporting.pdf.rendering.page.IVoidStateFormatConstants.LINE_HEIGHT;

public class BackgroundsEncoder implements ContentEncoder {
  public static final int Maximum_Number_Of_Background_Dots = 6;
  // TODO: Give this and PdfIntimacyEncoder a common base class, which may be more broadly useful.

  private final IResources resources;
  private final PdfTraitEncoder traitEncoder;

  public BackgroundsEncoder(IResources resources) {
    this.resources = resources;
    this.traitEncoder = PdfTraitEncoder.createSmallTraitEncoder();
  }

  @Override
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
      traitEncoder.encodeWithText(graphics, backgroundName, position, bounds.width, value, Maximum_Number_Of_Background_Dots);
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
      traitEncoder.encodeWithLine(graphics, position, bounds.width, 0, Maximum_Number_Of_Background_Dots);
      yPosition -= LINE_HEIGHT;
    }
  }

  @Override
  public String getHeader(ReportContent content) {
    return resources.getString("Sheet.Header.Backgrounds");
  }

  @Override
  public boolean hasContent(ReportContent content) {
    return true;
  }
}

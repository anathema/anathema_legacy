package net.sf.anathema.character.reporting.pdf.rendering.boxes.experience;

import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.BaseFont;
import net.sf.anathema.character.reporting.pdf.content.ReportContent;
import net.sf.anathema.character.reporting.pdf.rendering.elements.Bounds;
import net.sf.anathema.character.reporting.pdf.rendering.general.SheetGraphics;
import net.sf.anathema.character.reporting.pdf.rendering.general.PdfTextEncodingUtilities;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.IBoxContentEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.page.IVoidStateFormatConstants;
import net.sf.anathema.lib.resources.IResources;

import java.text.MessageFormat;

public class PdfExperienceEncoder implements IBoxContentEncoder {

  private static final int FONT_SIZE = IVoidStateFormatConstants.FONT_SIZE;
  private final Font font;
  private final IResources resources;

  public PdfExperienceEncoder(IResources resources, BaseFont baseFont) {
    this.resources = resources;
    this.font = PdfTextEncodingUtilities.createFont(baseFont, FONT_SIZE);
  }

  public String getHeaderKey(ReportContent reportContent) {
    return "Experience"; //$NON-NLS-1$
  }

  public void encode(SheetGraphics graphics, ReportContent reportContent, Bounds bounds) throws DocumentException {
    int totalPoints = reportContent.getCharacter().getTotalExperiencePoints();
    int spentPoints = reportContent.getCharacter().getSpentExperiencePoints();
    String experienceMessage = resources.getString("Sheet.Experience.MessageFormat"); //$NON-NLS-1$
    String experienceText = MessageFormat.format(experienceMessage, totalPoints, spentPoints, totalPoints - spentPoints);
    Phrase phrase = new Phrase(experienceText, font);
    PdfTextEncodingUtilities.encodeText(graphics.getDirectContent(), phrase, bounds, FONT_SIZE + 4);
  }

  public boolean hasContent(ReportContent content) {
    return true;
  }
}

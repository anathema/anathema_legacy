package net.sf.anathema.character.lunar.reporting.rendering;

import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.Phrase;
import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.template.TemplateType;
import net.sf.anathema.character.generic.type.CharacterType;
import net.sf.anathema.character.reporting.pdf.content.ReportContent;
import net.sf.anathema.character.reporting.pdf.rendering.extent.Bounds;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.ContentEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.graphics.SheetGraphics;
import net.sf.anathema.character.reporting.pdf.rendering.page.IVoidStateFormatConstants;
import net.sf.anathema.lib.resources.IResources;
import net.sf.anathema.lib.util.Identificate;

import static net.sf.anathema.character.reporting.pdf.rendering.page.IVoidStateFormatConstants.COMMENT_FONT_SIZE;

public class SecondEditionPowersEncoder implements ContentEncoder {
  private static final String TERRIFYING_BEASTMAN_ALTERATION = "Lunar.TerrifyingBeastmanAlteration";
  private Font font;
  private float lineHeight = IVoidStateFormatConstants.LINE_HEIGHT - 2;
  private static final String POWER_BASE = "Sheet.Lunar.Powers.";
  private final IResources resources;
  private final boolean isHorizontal;
  private int tellMDV;

  private static final TemplateType castelessType = new TemplateType(CharacterType.LUNAR, new Identificate("Casteless")); //$NON-NLS-1$

  public SecondEditionPowersEncoder(IResources resources, boolean isHorizontal) {
    this.resources = resources;
    this.isHorizontal = isHorizontal;
  }

  @Override
  public void encode(SheetGraphics graphics, ReportContent reportContent, Bounds bounds) {
    tellMDV = hasTBA(reportContent.getCharacter()) ? 8 : 12;

    int offsetX = 0, offsetY = isHorizontal ? 0 : 5;
    font = graphics.createTableFont();

    if (isHorizontal) {
      bounds = new Bounds(bounds.x, bounds.y, bounds.width / 2, bounds.height);
      font.setSize(COMMENT_FONT_SIZE);
      lineHeight -= 2;
    }

    try {
      offsetY += writePowerNotes(graphics, "Shapeshifting", bounds, offsetX, offsetY);
      if (!reportContent.getCharacter().getTemplate().getTemplateType().equals(castelessType)) {
        offsetY += writePowerNotes(graphics, "Tattoos", bounds, offsetX, offsetY);
      }

      if (isHorizontal) {

        bounds = new Bounds(bounds.x + bounds.width, bounds.y, bounds.width, bounds.height);
        offsetY = 0;
      }

      offsetY += writePowerNotes(graphics, "Tell", bounds, offsetX, offsetY);
    } catch (DocumentException e) {
    }
  }

  private boolean hasTBA(IGenericCharacter character) {
    for (ICharm charm : character.getLearnedCharms()) {
      if (charm.getId().equals(TERRIFYING_BEASTMAN_ALTERATION)) {
        return true;
      }
    }
    return false;
  }

  private int writePowerNotes(SheetGraphics graphics, String power, Bounds bounds, int offsetX, int offsetY) throws DocumentException {
    Font boldFont = new Font(font);
    boldFont.setStyle(Font.BOLD);
    String text = resources.getString(POWER_BASE + power);
    Phrase phrase = new Phrase(text, boldFont);
    int index = 0;
    int totalHeight = 0;
    while (!text.startsWith("##")) {
      Bounds newBounds = new Bounds(bounds.x + offsetX, bounds.y, bounds.width - offsetX, bounds.height - offsetY - totalHeight);
      totalHeight += graphics.createSimpleColumn(newBounds).withLeading(lineHeight).andTextPart(phrase).encode().getLinesWritten() * lineHeight;
      text = resources.getString(POWER_BASE + power + (++index));
      text = text.replace("TELLMDV", "" + tellMDV);
      phrase = new Phrase(text, font);
    }
    if (!isHorizontal) {
      Bounds newBounds = new Bounds(bounds.x + offsetX, bounds.y + bounds.height - offsetY - totalHeight, bounds.x - offsetX, lineHeight);
      totalHeight += graphics.createSimpleColumn(newBounds).withLeading(lineHeight).andTextPart(new Phrase(" ", font)).encode().getLinesWritten() * lineHeight;
    }
    return totalHeight;
  }

  @Override
  public String getHeader(ReportContent content) {
    return resources.getString("Sheet.Header.Lunar.Powers");
  }

  @Override
  public boolean hasContent(ReportContent content) {
    return true;
  }
}

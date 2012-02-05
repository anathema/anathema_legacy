package net.sf.anathema.character.reporting.pdf.rendering.boxes.anima;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Phrase;
import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.rules.IExaltedEdition;
import net.sf.anathema.character.generic.type.ICharacterType;
import net.sf.anathema.character.reporting.pdf.content.ReportContent;
import net.sf.anathema.character.reporting.pdf.rendering.extent.Bounds;
import net.sf.anathema.character.reporting.pdf.rendering.extent.Position;
import net.sf.anathema.character.reporting.pdf.rendering.general.HorizontalLineEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.general.ListUtils;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.ContentEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.general.table.ITableEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.graphics.SheetGraphics;
import net.sf.anathema.character.reporting.pdf.rendering.page.IVoidStateFormatConstants;
import net.sf.anathema.lib.resources.IResources;

public class GenericAnimaEncoder implements ContentEncoder {

  private final float fontSize;
  private final float lineHeight;
  private final IResources resources;
  private final ITableEncoder tableEncoder;

  public GenericAnimaEncoder(IResources resources, float fontSize, ITableEncoder encoder) {
    this.resources = resources;
    this.fontSize = fontSize;
    this.lineHeight = fontSize * 1.5f;
    this.tableEncoder = encoder;
  }

  @Override
  public void encode(SheetGraphics graphics, ReportContent reportContent, Bounds bounds) throws DocumentException {
    float powerHeight = bounds.getHeight() - AnimaTableEncoder.TABLE_HEIGHT - IVoidStateFormatConstants.TEXT_PADDING / 2f;
    Bounds animaPowerBounds = new Bounds(bounds.getMinX(), bounds.getMaxY() - powerHeight, bounds.getWidth(), powerHeight);
    encodeAnimaPowers(graphics, reportContent.getCharacter(), animaPowerBounds);

    Bounds animaTableBounds = new Bounds(bounds.getMinX(), bounds.getMinY(), bounds.getWidth(), AnimaTableEncoder.TABLE_HEIGHT);
    tableEncoder.encodeTable(graphics, reportContent, animaTableBounds);
  }

  private void encodeAnimaPowers(SheetGraphics graphics, IGenericCharacter character, Bounds bounds) throws DocumentException {
    Phrase phrase = new Phrase("", graphics.createFont(fontSize)); //$NON-NLS-1$
    // Add standard powers for character type
    Chunk symbolChunk = graphics.createSymbolChunk();
    ICharacterType characterType = character.getTemplate().getTemplateType().getCharacterType();
    IExaltedEdition edition = character.getRules().getEdition();
    ListUtils.addBulletedListText(resources, symbolChunk, edition, "Sheet.AnimaPower." + characterType.getId(), phrase, false);  //$NON-NLS-1$
    String casteResourceKey = "Sheet.AnimaPower." + character.getCasteType().getId() + "." + edition.getId(); //$NON-NLS-1$ //$NON-NLS-2$
    if (resources.supportsKey(casteResourceKey)) {
      phrase.add(symbolChunk);
      phrase.add(resources.getString(casteResourceKey) + "\n"); //$NON-NLS-1$
    }
    phrase.add(symbolChunk);
    float yPosition = graphics.createSimpleColumn(bounds).withLeading(lineHeight).andTextPart(phrase).encode().getYLine();
    Position lineStartPosition = new Position((bounds.getMinX() + graphics.getCaretSymbolWidth()), yPosition);
    int lineCount = 1 + (int) ((yPosition - bounds.getMinY()) / lineHeight);
    new HorizontalLineEncoder().encodeLines(graphics, lineStartPosition, bounds.getMinX(), bounds.getMaxX(), lineHeight, lineCount);
  }

  @Override
  public boolean hasContent(ReportContent content) {
    return true;
  }

  @Override
  public String getHeader(ReportContent content) {
    return resources.getString("Sheet.Header.Anima");
  }
}

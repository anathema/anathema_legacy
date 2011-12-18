package net.sf.anathema.character.reporting.pdf.rendering.boxes.virtues;

import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfContentByte;
import net.sf.anathema.character.reporting.pdf.content.ReportContent;
import net.sf.anathema.character.reporting.pdf.content.general.NamedValue;
import net.sf.anathema.character.reporting.pdf.content.virtues.VirtueContent;
import net.sf.anathema.character.reporting.pdf.rendering.elements.Bounds;
import net.sf.anathema.character.reporting.pdf.rendering.elements.Position;
import net.sf.anathema.character.reporting.pdf.rendering.general.AbstractPdfEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.general.PdfGraphics;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.IBoxContentEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.general.traits.PdfTraitEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.page.IVoidStateFormatConstants;
import net.sf.anathema.lib.resources.IResources;

public class VirtueBoxContentEncoder extends AbstractPdfEncoder implements IBoxContentEncoder {

  private final PdfTraitEncoder traitEncoder;
  private final BaseFont baseFont;
  private final IResources resources;

  public VirtueBoxContentEncoder(IResources resources, BaseFont baseFont) {
    this.resources = resources;
    this.baseFont = baseFont;
    this.traitEncoder = PdfTraitEncoder.createSmallTraitEncoder(getBaseFont());
  }

  @Override
  protected BaseFont getBaseFont() {
    return baseFont;
  }

  public void encode(PdfGraphics graphics, ReportContent content, Bounds bounds) throws DocumentException {
    VirtueContent virtueContent = getSubContent(content);
    encodeVirtues(graphics.getDirectContent(), bounds, virtueContent);
  }

  private void encodeVirtues(PdfContentByte directContent, Bounds bounds, VirtueContent virtueContent) {
    float virtuePadding = bounds.width / 8;
    float leftVirtueX = bounds.x + virtuePadding / 2;
    float width = (bounds.width - 2 * virtuePadding) / 2;
    float rightVirtueX = (int) (bounds.x + width + virtuePadding * 1.5);
    float upperY = (int) bounds.getMaxY();
    float centerY = (int) bounds.getCenterY();
    encodeVirtue(directContent, virtueContent.getUpperLeftVirtue(), new Position(leftVirtueX, upperY), width);
    encodeVirtue(directContent, virtueContent.getUpperRightVirtue(), new Position(rightVirtueX, upperY), width);
    encodeVirtue(directContent, virtueContent.getLowerLeftVirtue(), new Position(leftVirtueX, centerY), width);
    encodeVirtue(directContent, virtueContent.getLowerRightVirtue(), new Position(rightVirtueX, centerY), width);
  }

  private void encodeVirtue(PdfContentByte directContent, NamedValue trait, Position position, float width) {
    float yPosition = position.y;
    yPosition -= IVoidStateFormatConstants.LINE_HEIGHT - 3;
    String label = trait.getLabel();
    float labelX = position.x + width / 2;
    drawText(directContent, label, new Position(labelX, yPosition), PdfContentByte.ALIGN_CENTER);
    yPosition -= traitEncoder.getTraitHeight() - 1;
    Position traitPosition = new Position(position.x, yPosition);
    int value = trait.getValue();
    traitEncoder.encodeDotsCenteredAndUngrouped(directContent, traitPosition, width, value, 5);
    yPosition -= traitEncoder.getTraitHeight() - 1;
    traitEncoder.encodeSquaresCenteredAndUngrouped(directContent, new Position(position.x, yPosition), width, 0, 5);
  }

  public boolean hasContent(ReportContent content) {
    return getSubContent(content).hasContent();
  }

  public String getHeaderKey(ReportContent content) {
    return getSubContent(content).getHeaderKey();
  }

  protected VirtueContent getSubContent(ReportContent content) {
    return content.createSubContent(VirtueContent.class);
  }
}

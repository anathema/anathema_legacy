package net.sf.anathema.character.reporting.sheet.common;

import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfContentByte;
import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.character.IGenericDescription;
import net.sf.anathema.character.generic.character.IGenericTraitCollection;
import net.sf.anathema.character.generic.traits.IGenericTrait;
import net.sf.anathema.character.generic.traits.types.VirtueType;
import net.sf.anathema.character.reporting.common.Bounds;
import net.sf.anathema.character.reporting.common.Position;
import net.sf.anathema.character.reporting.common.encoder.AbstractPdfEncoder;
import net.sf.anathema.character.reporting.common.encoder.IPdfContentBoxEncoder;
import net.sf.anathema.character.reporting.common.pageformat.IVoidStateFormatConstants;
import net.sf.anathema.character.reporting.sheet.util.PdfTraitEncoder;
import net.sf.anathema.lib.resources.IResources;

public class PdfVirtueEncoder extends AbstractPdfEncoder implements IPdfContentBoxEncoder {

  private final PdfTraitEncoder traitEncoder;
  private final BaseFont baseFont;
  private final IResources resources;

  public PdfVirtueEncoder(IResources resources, BaseFont baseFont) {
    this.resources = resources;
    this.baseFont = baseFont;
    this.traitEncoder = PdfTraitEncoder.createSmallTraitEncoder(getBaseFont());
  }

  @Override
  protected BaseFont getBaseFont() {
    return baseFont;
  }

  public String getHeaderKey(IGenericCharacter character, IGenericDescription description) {
    return "Virtues"; //$NON-NLS-1$
  }

  public void encode(PdfContentByte directContent, IGenericCharacter character, IGenericDescription description,
                     Bounds bounds) throws DocumentException {
    encodeVirtues(directContent, bounds, character.getTraitCollection());
  }

  public void encodeVirtues(PdfContentByte directContent, Bounds bounds, IGenericTraitCollection collection) {
    float virtuePadding = bounds.width / 8;
    float leftVirtueX = bounds.x + virtuePadding / 2;
    float width = (bounds.width - 2 * virtuePadding) / 2;
    float rightVirtueX = (int) (bounds.x + width + virtuePadding * 1.5);
    float upperY = (int) bounds.getMaxY();
    float centerY = (int) bounds.getCenterY();
    encodeVirtue(directContent, collection.getTrait(VirtueType.Compassion), new Position(leftVirtueX, upperY), width);
    encodeVirtue(directContent, collection.getTrait(VirtueType.Temperance), new Position(rightVirtueX, upperY), width);
    encodeVirtue(directContent, collection.getTrait(VirtueType.Conviction), new Position(leftVirtueX, centerY), width);
    encodeVirtue(directContent, collection.getTrait(VirtueType.Valor), new Position(rightVirtueX, centerY), width);
  }

  private void encodeVirtue(PdfContentByte directContent, IGenericTrait trait, Position position, float width) {
    float yPosition = position.y;
    yPosition -= IVoidStateFormatConstants.LINE_HEIGHT - 3;
    String label = resources.getString(trait.getType().getId());
    float labelX = position.x + width / 2;
    drawText(directContent, label, new Position(labelX, yPosition), PdfContentByte.ALIGN_CENTER);
    yPosition -= traitEncoder.getTraitHeight() - 1;
    Position traitPosition = new Position(position.x, yPosition);
    int value = trait.getCurrentValue();
    traitEncoder.encodeDotsCenteredAndUngrouped(directContent, traitPosition, width, value, 5);
    yPosition -= traitEncoder.getTraitHeight() - 1;
    traitEncoder.encodeSquaresCenteredAndUngrouped(directContent, new Position(position.x, yPosition), width, 0, 5);
  }

  public boolean hasContent(IGenericCharacter character) {
    return true;
  }
}

package net.sf.anathema.character.reporting.sheet.common;

import java.awt.Point;

import net.disy.commons.core.geometry.SmartRectangle;
import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.traits.IGenericTrait;
import net.sf.anathema.character.generic.traits.types.VirtueType;
import net.sf.anathema.character.reporting.sheet.pageformat.IVoidStateFormatConstants;
import net.sf.anathema.character.reporting.sheet.util.AbstractPdfEncoder;
import net.sf.anathema.character.reporting.sheet.util.PdfTraitEncoder;
import net.sf.anathema.lib.resources.IResources;

import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfContentByte;

public class PdfVirtueEncoder extends AbstractPdfEncoder {

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

  public void encodeVirtues(PdfContentByte directContent, IGenericCharacter character, SmartRectangle bounds) {
    int virtuePadding = bounds.width / 8;
    int leftVirtueX = bounds.x + virtuePadding / 2;
    int width = (bounds.width - 2 * virtuePadding) / 2;
    int rightVirtueX = (int) (bounds.x + width + virtuePadding * 1.5);
    int upperY = (int) (bounds.getMaxY());
    int centerY = (int) (bounds.getCenterY());
    encodeVirtue(directContent, character.getTrait(VirtueType.Compassion), new Point(leftVirtueX, upperY), width);
    encodeVirtue(directContent, character.getTrait(VirtueType.Temperance), new Point(rightVirtueX, upperY), width);
    encodeVirtue(directContent, character.getTrait(VirtueType.Conviction), new Point(leftVirtueX, centerY), width);
    encodeVirtue(directContent, character.getTrait(VirtueType.Valor), new Point(rightVirtueX, centerY), width);
  }

  private void encodeVirtue(PdfContentByte directContent, IGenericTrait trait, Point position, int width) {
    int yPosition = position.y;
    yPosition -= IVoidStateFormatConstants.LINE_HEIGHT - 3;
    String label = resources.getString(trait.getType().getId());
    int labelX = position.x + width / 2;
    drawText(directContent, label, new Point(labelX, yPosition), PdfContentByte.ALIGN_CENTER);
    yPosition -= traitEncoder.getTraitHeight() - 1;
    Point traitPosition = new Point(position.x, yPosition);
    int value = trait.getCurrentValue();
    traitEncoder.encodeCenteredAndUngrouped(directContent, traitPosition, width, value, 5);
    yPosition -= traitEncoder.getTraitHeight() - 1;
    traitEncoder.encodeSquaresCenteredAndUngrouped(directContent, new Point(position.x, yPosition), width, 0, 5);
  }
}
package net.sf.anathema.character.reporting.sheet.common;

import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.character.IGenericDescription;
import net.sf.anathema.character.generic.traits.types.OtherTraitType;
import net.sf.anathema.character.reporting.sheet.pageformat.IVoidStateFormatConstants;
import net.sf.anathema.character.reporting.sheet.util.AbstractPdfEncoder;
import net.sf.anathema.character.reporting.sheet.util.PdfTraitEncoder;
import net.sf.anathema.character.reporting.util.Bounds;
import net.sf.anathema.character.reporting.util.Position;
import net.sf.anathema.lib.resources.IResources;
import net.sf.anathema.lib.util.IdentifiedInteger;

import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfContentByte;

public class NewPdfEssenceEncoder extends AbstractPdfEncoder implements
    IPdfVariableContentBoxEncoder {

  private BaseFont baseFont;
  private final IResources resources;
  private PdfTraitEncoder traitEncoder;
  private final int essenceMax;

  public NewPdfEssenceEncoder(BaseFont baseFont, IResources resources,
                              int essenceMax) {
    this.baseFont = baseFont;
    this.resources = resources;
    this.essenceMax = essenceMax;
    this.traitEncoder = PdfTraitEncoder.createMediumTraitEncoder(baseFont);
  }

  @Override
  protected BaseFont getBaseFont() {
    return baseFont;
  }

  public String getHeaderKey(IGenericCharacter character,
                             IGenericDescription description) {
    return "Essence"; //$NON-NLS-1$
  }
  
  private int calculatePoolLines(IGenericCharacter character) {
    int lines = 0;
    if (character.getPersonalPoolValue() > 0) {
      lines++;
    }
    if (character.getPeripheralPoolValue() > 0) {
      lines++;
    }
    for (IdentifiedInteger complexPool : character.getComplexPools()) {
      if (complexPool.getValue() > 0) {
        lines++;
      }
    }
    return lines;
  }

  public float getRequestedHeight(IGenericCharacter character) {
    float height = traitEncoder.getTraitHeight() + IVoidStateFormatConstants.TEXT_PADDING;
    height += calculatePoolLines(character) * IVoidStateFormatConstants.LINE_HEIGHT;
    return height;    
  }

  public void encode(PdfContentByte directContent, IGenericCharacter character,
                     IGenericDescription description, Bounds bounds)
      throws DocumentException {
    float poolHeight = bounds.height - traitEncoder.getTraitHeight()
                       - IVoidStateFormatConstants.TEXT_PADDING;
    float columnWidth = (bounds.width - 3 * IVoidStateFormatConstants.PADDING) / 4f;

    int personalPool = character.getPersonalPoolValue();
    int peripheralPool = character.getPeripheralPoolValue();
    IdentifiedInteger[] complexPools = character.getComplexPools();
    
    int numberOfLines = (personalPool > 0 ? 1 : 0) + (peripheralPool > 0 ? 1 : 0);
    for (IdentifiedInteger pool : complexPools) {
      if (pool.getValue() > 0) {
        numberOfLines++;
      }
    }
    float poolLineHeight = poolHeight / numberOfLines;
    //float offset = (2 - numberOfLines) * (poolLineHeight / 2);
    int value = character.getTraitCollection().getTrait(OtherTraitType.Essence).getCurrentValue();
    Position essencePosition = new Position(bounds.x,
                                            bounds.getMaxY()
                                            - traitEncoder.getTraitHeight());
    //                                      - offset);
    traitEncoder.encodeDotsCenteredAndUngrouped(directContent, essencePosition,
                                                columnWidth, value, essenceMax);

    int line = 1;
    if (personalPool > 0) {
      Position personalPosition = new Position(bounds.x,
                                               essencePosition.y - line * poolLineHeight);
      String personalLabel = resources.getString("Sheet.Essence.PersonalPool"); //$NON-NLS-1$
      encodePool(directContent, personalLabel, personalPool,
                 personalPosition, columnWidth);
      line++;
    }

    if (peripheralPool > 0) {
      Position peripheralPosition = new Position(bounds.x,
                                                 essencePosition.y - line * poolLineHeight);
      String peripheralLabel = resources.getString("Sheet.Essence.PeripheralPool"); //$NON-NLS-1$
      encodePool(directContent, peripheralLabel, peripheralPool,
                 peripheralPosition, columnWidth);
      line++;
    }
    
    for (IdentifiedInteger complexPool : complexPools) {
      String poolId = complexPool.getId();
      int poolValue = complexPool.getValue();
      if (poolValue > 0) {
        Position poolPosition = new Position(bounds.x,
                                             essencePosition.y - line * poolLineHeight);
        String poolLabel = resources.getString("Sheet.Essence." + poolId); //$NON-NLS-1$
        encodePool(directContent, poolLabel, poolValue, poolPosition, columnWidth);
        line++;
      }
    }
  }

  private void encodePool(PdfContentByte directContent, String label,
                          int poolValue, Position poolPosition, float width) {
    drawText(directContent, label, poolPosition, PdfContentByte.ALIGN_LEFT);
    Position rightPosition = new Position(poolPosition.x + width,
                                          poolPosition.y);
    String totalString = poolValue + " " + resources.getString("Sheet.Essence.Total"); //$NON-NLS-1$ //$NON-NLS-2$
    drawText(directContent, totalString, rightPosition,
             PdfContentByte.ALIGN_RIGHT);
  }

  public boolean hasContent(IGenericCharacter character) {
    return true;
  }
}
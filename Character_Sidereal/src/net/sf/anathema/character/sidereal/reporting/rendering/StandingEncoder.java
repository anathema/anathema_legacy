package net.sf.anathema.character.sidereal.reporting.rendering;

import net.sf.anathema.character.generic.character.*;
import net.sf.anathema.character.generic.traits.IGenericTrait;
import net.sf.anathema.character.reporting.pdf.rendering.elements.Bounds;
import net.sf.anathema.character.reporting.pdf.rendering.elements.Position;
import net.sf.anathema.character.reporting.pdf.rendering.general.AbstractPdfEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.IPdfContentBoxEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.general.traits.PdfTraitEncoder;
import net.sf.anathema.character.sidereal.SiderealCharacterModule;
import net.sf.anathema.lib.resources.IResources;

import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfContentByte;

public class StandingEncoder extends AbstractPdfEncoder implements IPdfContentBoxEncoder {

  private final BaseFont baseFont;
  private final IResources resources;
  private final float lineHeight;
  private final PdfTraitEncoder smallTraitEncoder;

  public StandingEncoder(BaseFont baseFont, int fontSize, IResources resources) {
    this.baseFont = baseFont;
    this.lineHeight = fontSize * 1.5f;
    this.resources = resources;
    this.smallTraitEncoder = PdfTraitEncoder.createSmallTraitEncoder(baseFont);
  }

  public void encode(PdfContentByte directContent, IGenericCharacter character, IGenericDescription description, Bounds bounds) throws DocumentException {
    int yPosition = (int) (bounds.getMaxY() - lineHeight);
    drawLabelledContent(
        directContent,
        getLabel("Label.Allegiance"), null, new Position(bounds.x, yPosition), bounds.width); //$NON-NLS-1$
    yPosition -= lineHeight;
    int salary = getBackground(character, SiderealCharacterModule.BACKGROUND_ID_SALARY);
    yPosition -= smallTraitEncoder.encodeWithText(directContent, resources.getString("Sheet.Sidereal.Standing.Salary"), //$NON-NLS-1$
        new Position(bounds.x, yPosition),
        bounds.width,
        salary,
        5);
    int manse = getBackground(character, SiderealCharacterModule.BACKGROUND_ID_CELESTIAL_MANSE);
    smallTraitEncoder.encodeWithText(directContent, resources.getString("Sheet.Sidereal.Standing.Manse"), new Position( //$NON-NLS-1$
        bounds.x,
        yPosition), bounds.width, manse, 5);
  }

  private int getBackground(IGenericCharacter character, String id) {
    int backgroundValue = 0;
    for (IGenericTrait background : character.getBackgrounds()) {
      if (background.getType().getId().equals(id)) {
        backgroundValue = background.getCurrentValue();
      }
    }
    return backgroundValue;
  }

  public String getHeaderKey(IGenericCharacter character, IGenericDescription description) {
    return "Sidereal.Standing"; //$NON-NLS-1$
  }

  protected final String getLabel(String key) {
    return resources.getString("Sheet.Sidereal.Standing." + key) + ":"; //$NON-NLS-1$ //$NON-NLS-2$
  }

  @Override
  protected BaseFont getBaseFont() {
    return baseFont;
  }
  
  public boolean hasContent(IGenericCharacter character)
  {
	  return true;
  }
}

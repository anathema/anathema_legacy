package net.sf.anathema.character.sidereal.reporting;

import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.character.IGenericDescription;
import net.sf.anathema.character.reporting.sheet.common.IPdfContentBoxEncoder;
import net.sf.anathema.character.reporting.sheet.page.IPdfPageEncoder;
import net.sf.anathema.character.reporting.sheet.pageformat.PdfPageConfiguration;
import net.sf.anathema.character.reporting.sheet.util.PdfBoxEncoder;
import net.sf.anathema.character.reporting.util.Bounds;
import net.sf.anathema.lib.resources.IResources;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfContentByte;

public class SiderealDetailsPageEncoder implements IPdfPageEncoder {

  private final int essenceMax;
  private final IResources resources;
  private final BaseFont baseFont;
  private final BaseFont symbolBaseFont;
  private final PdfBoxEncoder boxEncoder;
  private final PdfPageConfiguration configuration;

  public SiderealDetailsPageEncoder(
      IResources resources,
      int essenceMax,
      BaseFont baseFont,
      BaseFont symbolBaseFont,
      PdfPageConfiguration configuration) {
    this.resources = resources;
    this.essenceMax = essenceMax;
    this.baseFont = baseFont;
    this.symbolBaseFont = symbolBaseFont;
    this.configuration = configuration;
    this.boxEncoder = new PdfBoxEncoder(resources, baseFont);
  }

  public void encode(
      Document document,
      PdfContentByte directContent,
      IGenericCharacter character,
      IGenericDescription description) throws DocumentException {
    int distanceFromTop = 0;
    encodeColleges(directContent, character, distanceFromTop);
    // ARCANE FATE INFO BOX
    // GAINING PARADOX INFO BOX
    // RESPLENDENT DESTINIES FILL-IN-BOX
    // CURRENT SALARY
    // ALLEGIANCE-FILL-IN-BOX
    // CONVENTION MEMBERSHIP
    // FAVORS OWED
    // ASTROLOGY OVERVIEW
    // MAKING DESTINY-OVERVIEW
  }

  private int encodeColleges(PdfContentByte directContent, IGenericCharacter character, int distanceFromTop)
      throws DocumentException {
    int height = 312;
    Bounds boxBounds = configuration.getFirstColumnRectangle(distanceFromTop, height, 1);
    IPdfContentBoxEncoder encoder = new SiderealCollegeEncoder(baseFont, resources, essenceMax);
    boxEncoder.encodeBox(directContent, encoder, character, boxBounds);
    return height;
  }
}
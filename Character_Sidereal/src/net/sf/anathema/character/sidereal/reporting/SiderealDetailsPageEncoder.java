package net.sf.anathema.character.sidereal.reporting;

import static net.sf.anathema.character.reporting.sheet.pageformat.IVoidStateFormatConstants.PADDING;
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
  private final int fontSize;

  public SiderealDetailsPageEncoder(
      IResources resources,
      int essenceMax,
      BaseFont baseFont,
      BaseFont symbolBaseFont,
      int fontSize,
      PdfPageConfiguration configuration) {
    this.resources = resources;
    this.essenceMax = essenceMax;
    this.baseFont = baseFont;
    this.symbolBaseFont = symbolBaseFont;
    this.fontSize = fontSize;
    this.configuration = configuration;
    this.boxEncoder = new PdfBoxEncoder(resources, baseFont);
  }

  public void encode(
      Document document,
      PdfContentByte directContent,
      IGenericCharacter character,
      IGenericDescription description) throws DocumentException {
    int distanceFromTop = 0;
    distanceFromTop += encodeColleges(directContent, character, distanceFromTop);
    distanceFromTop += PADDING;
    distanceFromTop += encodeParadox(directContent, character, distanceFromTop);
    distanceFromTop += PADDING;
    distanceFromTop += encodeArcaneFate(directContent, character, distanceFromTop);
    // RESPLENDENT DESTINIES FILL-IN-BOX
    // STANDING: ALLEGIANCE / CURRENT SALARY / CELESTIAL MANSE
    // ACQUAINTANCES-FILL-IN
    // SIFU-FILL-IN
    // CONVENTION MEMBERSHIP-FILL-IN
    // CONNECTIONS-FILL-IN
    // FAVORS OWED
    // ASTROLOGY INFO BOX
    // MAKING DESTINY INFO BOX
  }

  private int encodeParadox(PdfContentByte directContent, IGenericCharacter character, int distanceFromTop)
      throws DocumentException {
    int height = 170;
    Bounds boxBounds = configuration.getFirstColumnRectangle(distanceFromTop, height, 1);
    IPdfContentBoxEncoder encoder = new ParadoxInfoEncoder(baseFont, symbolBaseFont, fontSize, resources);
    boxEncoder.encodeBox(directContent, encoder, character, boxBounds);
    return height;
  }

  private int encodeArcaneFate(PdfContentByte directContent, IGenericCharacter character, int distanceFromTop)
      throws DocumentException {
    int height = 145;
    Bounds boxBounds = configuration.getFirstColumnRectangle(distanceFromTop, height, 1);
    IPdfContentBoxEncoder encoder = new ArcaneFateInfoEncoder(baseFont, symbolBaseFont, fontSize, resources);
    boxEncoder.encodeBox(directContent, encoder, character, boxBounds);
    return height;
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
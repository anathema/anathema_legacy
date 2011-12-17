package net.sf.anathema.character.sidereal.reporting;

import static net.sf.anathema.character.reporting.pageformat.IVoidStateFormatConstants.PADDING;
import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.character.IGenericDescription;
import net.sf.anathema.character.generic.impl.rules.ExaltedEdition;
import net.sf.anathema.character.reporting.sheet.common.IPdfContentBoxEncoder;
import net.sf.anathema.character.reporting.sheet.common.PdfHorizontalLineContentEncoder;
import net.sf.anathema.character.reporting.sheet.page.IPdfPageEncoder;
import net.sf.anathema.character.reporting.pageformat.PdfPageConfiguration;
import net.sf.anathema.character.reporting.sheet.util.PdfBoxEncoder;
import net.sf.anathema.character.reporting.util.Bounds;
import net.sf.anathema.lib.resources.IResources;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfContentByte;

public class FirstEditionSiderealDetailsPageEncoder implements IPdfPageEncoder {

  private final static float COLLEGE_HEIGHT = 312;
  private final static float DESTINY_HEIGHT = (COLLEGE_HEIGHT - PADDING) / 2;
  private final static float THIRD_BLOCK_HEIGHT = 145;
  private final static float STANDING_HEIGHT = 45;
  private final int essenceMax;
  private final IResources resources;
  private final BaseFont baseFont;
  private final BaseFont symbolBaseFont;
  private final PdfBoxEncoder boxEncoder;
  private final PdfPageConfiguration configuration;
  private final int fontSize;

  public FirstEditionSiderealDetailsPageEncoder(
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
    distanceFromTop += encodeColleges(directContent, character, description, distanceFromTop);
    distanceFromTop += PADDING;
    distanceFromTop += encodeAstrology(directContent, character, description, distanceFromTop);
    distanceFromTop += PADDING;
    distanceFromTop += encodeArcaneFate(directContent, character, description, distanceFromTop);
    distanceFromTop += PADDING;
    float remainingHeight = configuration.getContentHeight() - distanceFromTop;
    encodeConnections(directContent, character, description, remainingHeight, distanceFromTop);

    int centerDistanceFromTop = 0;
    centerDistanceFromTop += encodeResplendentDestiny(
        directContent,
        getCenterDestinyBounds(centerDistanceFromTop),
        character, description);
    centerDistanceFromTop += PADDING;
    centerDistanceFromTop += encodeResplendentDestiny(
        directContent,
        getCenterDestinyBounds(centerDistanceFromTop),
        character, description);
    centerDistanceFromTop += PADDING;
    centerDistanceFromTop += encodeResplendentDestiny(
        directContent,
        getCenterDestinyBounds(centerDistanceFromTop),
        character, description);
    centerDistanceFromTop += PADDING;
    centerDistanceFromTop += encodeAcquaintances(directContent, character, description, centerDistanceFromTop);

    int rightDistanceFromTop = 0;
    rightDistanceFromTop += encodeResplendentDestiny(
        directContent,
        getRightDestinyBounds(rightDistanceFromTop),
        character, description);
    rightDistanceFromTop += PADDING;
    rightDistanceFromTop += encodeResplendentDestiny(
        directContent,
        getRightDestinyBounds(rightDistanceFromTop),
        character, description);
    rightDistanceFromTop += PADDING;
    rightDistanceFromTop += encodeParadox(directContent, character, description, rightDistanceFromTop);
    rightDistanceFromTop += PADDING;
    rightDistanceFromTop += encodeStanding(directContent, character, description, rightDistanceFromTop);
    rightDistanceFromTop += PADDING;
    rightDistanceFromTop += encodeConventions(directContent, character, description, rightDistanceFromTop);
    rightDistanceFromTop += PADDING;

  }

  private void encodeConnections(
      PdfContentByte directContent,
      IGenericCharacter character,
      IGenericDescription description,
      float height,
      int distanceFromTop) throws DocumentException {
    Bounds boxBounds = configuration.getFirstColumnRectangle(distanceFromTop, height, 3);
    IPdfContentBoxEncoder encoder = new PdfHorizontalLineContentEncoder(4, "Sidereal.Connections"); //$NON-NLS-1$
    boxEncoder.encodeBox(directContent, encoder, character, description, boxBounds);
  }

  private float encodeAcquaintances(PdfContentByte directContent, IGenericCharacter character, IGenericDescription description, int distanceFromTop)
      throws DocumentException {
    float height = 145;
    Bounds boxBounds = configuration.getSecondColumnRectangle(distanceFromTop, height, 1);
    IPdfContentBoxEncoder encoder = new PdfHorizontalLineContentEncoder(1, "Sidereal.Acquaintances"); //$NON-NLS-1$
    boxEncoder.encodeBox(directContent, encoder, character, description, boxBounds);
    return height;
  }

  private float encodeConventions(PdfContentByte directContent, IGenericCharacter character,IGenericDescription description,  int distanceFromTop)
      throws DocumentException {
    float height = THIRD_BLOCK_HEIGHT - STANDING_HEIGHT - PADDING;
    Bounds boxBounds = configuration.getThirdColumnRectangle(distanceFromTop, height);
    IPdfContentBoxEncoder encoder = new PdfHorizontalLineContentEncoder(2, "Sidereal.Conventions"); //$NON-NLS-1$
    boxEncoder.encodeBox(directContent, encoder, character, description, boxBounds);
    return height;
  }

  private float encodeStanding(PdfContentByte directContent, IGenericCharacter character, IGenericDescription description, int distanceFromTop)
      throws DocumentException {
    float height = STANDING_HEIGHT;
    Bounds boxBounds = configuration.getThirdColumnRectangle(distanceFromTop, height);
    IPdfContentBoxEncoder encoder = new StandingEncoder(baseFont, fontSize, resources);
    boxEncoder.encodeBox(directContent, encoder, character, description, boxBounds);
    return height;
  }

  private float encodeAstrology(PdfContentByte directContent, IGenericCharacter character,IGenericDescription description,  int distanceFromTop)
      throws DocumentException {
    float height = DESTINY_HEIGHT;
    Bounds boxBounds = configuration.getFirstColumnRectangle(distanceFromTop, height, 1);
    IPdfContentBoxEncoder encoder = new AstrologyInfoEncoder(baseFont, resources);
    boxEncoder.encodeBox(directContent, encoder, character, description, boxBounds);
    return height;
  }

  private float encodeResplendentDestiny(PdfContentByte directContent, Bounds boxBounds, IGenericCharacter character, IGenericDescription description)
      throws DocumentException {
    IPdfContentBoxEncoder encoder = new ResplendentDestinyEncoder(baseFont, fontSize, resources);
    boxEncoder.encodeBox(directContent, encoder, character, description, boxBounds);
    return boxBounds.height;
  }

  private Bounds getRightDestinyBounds(int distanceFromTop) {
    return configuration.getThirdColumnRectangle(distanceFromTop, DESTINY_HEIGHT);
  }

  private Bounds getCenterDestinyBounds(int distanceFromTop) {
    return configuration.getSecondColumnRectangle(distanceFromTop, DESTINY_HEIGHT, 1);
  }

  private float encodeParadox(PdfContentByte directContent, IGenericCharacter character,IGenericDescription description,  int distanceFromTop)
      throws DocumentException {
    float height = DESTINY_HEIGHT;
    Bounds boxBounds = configuration.getThirdColumnRectangle(distanceFromTop, height);
    IPdfContentBoxEncoder encoder = new ParadoxInfoEncoder(baseFont, symbolBaseFont,
    		fontSize, resources, ExaltedEdition.FirstEdition);
    boxEncoder.encodeBox(directContent, encoder, character, description, boxBounds);
    return height;
  }

  private float encodeArcaneFate(PdfContentByte directContent, IGenericCharacter character,IGenericDescription description,  int distanceFromTop)
      throws DocumentException {
    float height = THIRD_BLOCK_HEIGHT;
    Bounds boxBounds = configuration.getFirstColumnRectangle(distanceFromTop, height, 1);
    IPdfContentBoxEncoder encoder = new ArcaneFateInfoEncoder(baseFont, symbolBaseFont,
    		fontSize, resources, ExaltedEdition.FirstEdition);
    boxEncoder.encodeBox(directContent, encoder, character, description, boxBounds);
    return height;
  }

  private float encodeColleges(PdfContentByte directContent, IGenericCharacter character, IGenericDescription description, int distanceFromTop)
      throws DocumentException {
    float height = COLLEGE_HEIGHT;
    Bounds boxBounds = configuration.getFirstColumnRectangle(distanceFromTop, height, 1);
    IPdfContentBoxEncoder encoder = new SiderealCollegeEncoder(baseFont, resources, essenceMax);
    boxEncoder.encodeBox(directContent, encoder, character, description, boxBounds);
    return height;
  }
}
package net.sf.anathema.character.sidereal.reporting;

import static net.sf.anathema.character.reporting.sheet.pageformat.IVoidStateFormatConstants.PADDING;
import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.character.IGenericDescription;
import net.sf.anathema.character.generic.impl.rules.ExaltedEdition;
import net.sf.anathema.character.generic.template.TemplateType;
import net.sf.anathema.character.generic.type.CharacterType;
import net.sf.anathema.character.reporting.sheet.common.IPdfContentBoxEncoder;
import net.sf.anathema.character.reporting.sheet.common.PdfHorizontalLineContentEncoder;
import net.sf.anathema.character.reporting.sheet.page.IPdfPageEncoder;
import net.sf.anathema.character.reporting.sheet.pageformat.PdfPageConfiguration;
import net.sf.anathema.character.reporting.sheet.util.PdfBoxEncoder;
import net.sf.anathema.character.reporting.util.Bounds;
import net.sf.anathema.lib.resources.IResources;
import net.sf.anathema.lib.util.Identificate;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfContentByte;

public class SecondEditionSiderealDetailsPageEncoder implements IPdfPageEncoder {

  private final static int COLLEGE_HEIGHT = 312;
  private final static int DESTINY_HEIGHT = (COLLEGE_HEIGHT - PADDING) / 2;
  private final static int PARADOX_HEIGHT = 45;
  private final int essenceMax;
  private final IResources resources;
  private final BaseFont baseFont;
  private final BaseFont symbolBaseFont;
  private final PdfBoxEncoder boxEncoder;
  private final PdfPageConfiguration configuration;
  private final int fontSize;
  
  private static final TemplateType roninType = new TemplateType(CharacterType.SIDEREAL, new Identificate(
  "Ronin")); //$NON-NLS-1$
  private static final TemplateType revisedRoninType = new TemplateType(CharacterType.SIDEREAL, new Identificate(
  "RevisedRonin")); //$NON-NLS-1$
  private static final TemplateType dreamsType = new TemplateType(CharacterType.SIDEREAL, new Identificate(
  "Dreams")); //$NON-NLS-1$
  private static final TemplateType revisedDreamsType = new TemplateType(CharacterType.SIDEREAL, new Identificate(
  "RevisedDreams")); //$NON-NLS-1$

  public SecondEditionSiderealDetailsPageEncoder(
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
	if (isRonin(character)) return;
    int distanceFromTop = 0;
    int collegeHeight = 0;
    collegeHeight = encodeColleges(directContent, character, distanceFromTop);
    encodeAstrology(directContent, character, distanceFromTop);
    distanceFromTop += collegeHeight + PADDING;
    
    //distanceFromTop += PADDING;
    if (!isFirstAge(character))
    	encodeArcaneFate(directContent, character, distanceFromTop);
    encodeConnections(directContent, character, distanceFromTop);
    distanceFromTop += encodeParadoxHelp(directContent, character, distanceFromTop);
    distanceFromTop += PADDING;
    //float remainingHeight = configuration.getContentHeight() - distanceFromTop;
    //

    int centerDistance = distanceFromTop;
    encodeResplendentDestiny(
        directContent,
        getLeftDestinyBounds(distanceFromTop),
        character);
    distanceFromTop += encodeResplendentDestiny(
        directContent,
        getRightDestinyBounds(distanceFromTop),
        character);
    distanceFromTop += PADDING;
    
    centerDistance += encodeParadox(directContent, character, centerDistance);
    centerDistance += PADDING;
    encodeAcquaintances(directContent, character, centerDistance,
    		DESTINY_HEIGHT - PARADOX_HEIGHT - PADDING);

    encodeResplendentDestiny(
        directContent,
        getLeftDestinyBounds(distanceFromTop),
        character);
    encodeResplendentDestiny(
        directContent,
        getCenterDestinyBounds(distanceFromTop),
        character);
    encodeResplendentDestiny(
        directContent,
        getRightDestinyBounds(distanceFromTop),
        character);
    //rightDistanceFromTop += encodeParadox(directContent, character, rightDistanceFromTop);
    //rightDistanceFromTop += encodeStanding(directContent, character, rightDistanceFromTop);
    //rightDistanceFromTop += encodeConventions(directContent, character, rightDistanceFromTop);

  }
  
  private boolean isRonin(IGenericCharacter character)
  {
	  if (character.getTemplate().getTemplateType().getSubType().getId().equals(roninType.getSubType().getId()) ||
		  character.getTemplate().getTemplateType().getSubType().getId().equals(revisedRoninType.getSubType().getId()))
		 return true;
	  return false;
  }
  
  private boolean isFirstAge(IGenericCharacter character)
  {
	  if (character.getTemplate().getTemplateType().getSubType().getId().equals(dreamsType.getSubType().getId()) ||
		  character.getTemplate().getTemplateType().getSubType().getId().equals(revisedDreamsType.getSubType().getId()))
		 return true;
	  return false;
  }

  private void encodeConnections(
      PdfContentByte directContent,
      IGenericCharacter character,
      int distanceFromTop) throws DocumentException {
	  int height = DESTINY_HEIGHT;
    Bounds boxBounds;
    if (isFirstAge(character))
    	boxBounds = configuration.getFirstColumnRectangle(distanceFromTop, height, 2);
    else
    	boxBounds = configuration.getSecondColumnRectangle(distanceFromTop, height, 1);
    IPdfContentBoxEncoder encoder = new PdfHorizontalLineContentEncoder(4, "Sidereal.Connections"); //$NON-NLS-1$
    boxEncoder.encodeBox(directContent, encoder, character, boxBounds);
  }

  private int encodeAcquaintances(PdfContentByte directContent, IGenericCharacter character, int distanceFromTop, int height)
      throws DocumentException {
    Bounds boxBounds = configuration.getSecondColumnRectangle(distanceFromTop, height, 1);
    IPdfContentBoxEncoder encoder = new PdfHorizontalLineContentEncoder(1, "Sidereal.Acquaintances"); //$NON-NLS-1$
    boxEncoder.encodeBox(directContent, encoder, character, boxBounds);
    return height;
  }

  private int encodeParadox(PdfContentByte directContent, IGenericCharacter character, int distanceFromTop)
      throws DocumentException {
    int height = PARADOX_HEIGHT;
    Bounds boxBounds = configuration.getSecondColumnRectangle(distanceFromTop, height, 1);
    IPdfContentBoxEncoder encoder = new SiderealParadoxEncoder(baseFont, symbolBaseFont, resources);
    boxEncoder.encodeBox(directContent, encoder, character, boxBounds);
    return height;
  }

  private int encodeAstrology(PdfContentByte directContent, IGenericCharacter character, int distanceFromTop)
      throws DocumentException {
    int height = COLLEGE_HEIGHT;
    Bounds boxBounds = configuration.getSecondColumnRectangle(distanceFromTop, height, 2);
    IPdfContentBoxEncoder encoder = new SecondEditionAstrologyInfoEncoder(baseFont, resources);
    boxEncoder.encodeBox(directContent, encoder, character, boxBounds);
    return height;
  }

  private int encodeResplendentDestiny(PdfContentByte directContent, Bounds boxBounds, IGenericCharacter character)
      throws DocumentException {
    IPdfContentBoxEncoder encoder = new ResplendentDestinyEncoder(baseFont, fontSize, resources);
    boxEncoder.encodeBox(directContent, encoder, character, boxBounds);
    return (int) boxBounds.height;
  }

  private Bounds getRightDestinyBounds(int distanceFromTop) {
    return configuration.getThirdColumnRectangle(distanceFromTop, DESTINY_HEIGHT);
  }

  private Bounds getCenterDestinyBounds(int distanceFromTop) {
    return configuration.getSecondColumnRectangle(distanceFromTop, DESTINY_HEIGHT, 1);
  }
  
  private Bounds getLeftDestinyBounds(int distanceFromTop) {
	    return configuration.getFirstColumnRectangle(distanceFromTop, DESTINY_HEIGHT, 1);
	  }

  private int encodeParadoxHelp(PdfContentByte directContent, IGenericCharacter character, int distanceFromTop)
      throws DocumentException {
    int height = DESTINY_HEIGHT;
    Bounds boxBounds = configuration.getThirdColumnRectangle(distanceFromTop, height);
    IPdfContentBoxEncoder encoder = new ParadoxInfoEncoder(baseFont, symbolBaseFont,
    		fontSize, resources, ExaltedEdition.SecondEdition);
    boxEncoder.encodeBox(directContent, encoder, character, boxBounds);
    return height;
  }

  private int encodeArcaneFate(PdfContentByte directContent, IGenericCharacter character, int distanceFromTop)
      throws DocumentException {
    int height = DESTINY_HEIGHT;
    Bounds boxBounds = configuration.getFirstColumnRectangle(distanceFromTop, height, 1);
    IPdfContentBoxEncoder encoder = new ArcaneFateInfoEncoder(baseFont, symbolBaseFont,
    		fontSize, resources, ExaltedEdition.SecondEdition);
    boxEncoder.encodeBox(directContent, encoder, character, boxBounds);
    return height;
  }

  private int encodeColleges(PdfContentByte directContent, IGenericCharacter character, int distanceFromTop)
      throws DocumentException {
    int height = COLLEGE_HEIGHT;
    Bounds boxBounds = configuration.getFirstColumnRectangle(distanceFromTop, height, 1);
    IPdfContentBoxEncoder encoder = new SiderealCollegeEncoder(baseFont, resources, essenceMax, ExaltedEdition.SecondEdition);
    boxEncoder.encodeBox(directContent, encoder, character, boxBounds);
    return height;
  }
}
package net.sf.anathema.character.sidereal.reporting.extended;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfContentByte;
import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.character.IGenericDescription;
import net.sf.anathema.character.generic.impl.rules.ExaltedEdition;
import net.sf.anathema.character.generic.template.TemplateType;
import net.sf.anathema.character.generic.type.CharacterType;
import net.sf.anathema.character.reporting.extended.common.IPdfContentBoxEncoder;
import net.sf.anathema.character.reporting.extended.common.PdfHorizontalLineContentEncoder;
import net.sf.anathema.character.reporting.extended.page.IPdfPageEncoder;
import net.sf.anathema.character.reporting.pageformat.PdfPageConfiguration;
import net.sf.anathema.character.reporting.extended.util.PdfBoxEncoder;
import net.sf.anathema.character.reporting.util.Bounds;
import net.sf.anathema.lib.resources.IResources;
import net.sf.anathema.lib.util.Identificate;

import static net.sf.anathema.character.reporting.pageformat.IVoidStateFormatConstants.PADDING;

public class SecondEditionSiderealDetailsPageEncoder implements IPdfPageEncoder {

  private final static float COLLEGE_HEIGHT = 312;
  private final static float DESTINY_HEIGHT = (COLLEGE_HEIGHT - PADDING) / 2;
  private final static float PARADOX_HEIGHT = 45;
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
	  float distanceFromTop = 0;
    float collegeHeight = 0;
    collegeHeight = encodeColleges(directContent, character, description, distanceFromTop);
    encodeAstrology(directContent, character, description, distanceFromTop);
    distanceFromTop += collegeHeight + PADDING;
    
    //distanceFromTop += PADDING;
    if (!isFirstAge(character))
    	encodeArcaneFate(directContent, character, description, distanceFromTop);
    encodeConnections(directContent, character, description, distanceFromTop);
    distanceFromTop += encodeParadoxHelp(directContent, character, description, distanceFromTop);
    distanceFromTop += PADDING;
    //float remainingHeight = configuration.getContentHeight() - distanceFromTop;
    //

    float centerDistance = distanceFromTop;
    encodeResplendentDestiny(
        directContent,
        getLeftDestinyBounds(distanceFromTop),
        character, description);
    distanceFromTop += encodeResplendentDestiny(
        directContent,
        getRightDestinyBounds(distanceFromTop),
        character, description);
    distanceFromTop += PADDING;
    
    centerDistance += encodeParadox(directContent, character, description, centerDistance);
    centerDistance += PADDING;
    encodeAcquaintances(directContent, character, description,
    		centerDistance, DESTINY_HEIGHT - PARADOX_HEIGHT - PADDING);

    encodeResplendentDestiny(
        directContent,
        getLeftDestinyBounds(distanceFromTop),
        character, description);
    encodeResplendentDestiny(
        directContent,
        getCenterDestinyBounds(distanceFromTop),
        character, description);
    encodeResplendentDestiny(
        directContent,
        getRightDestinyBounds(distanceFromTop),
        character, description);
    //rightDistanceFromTop += encodeParadox(directContent, character, rightDistanceFromTop);
    //rightDistanceFromTop += encodeStanding(directContent, character, rightDistanceFromTop);
    //rightDistanceFromTop += encodeConventions(directContent, character, rightDistanceFromTop);

  }
  
  private boolean isRonin(IGenericCharacter character)
  {
      return character.getTemplate().getTemplateType().getSubType().getId().equals(roninType.getSubType().getId()) ||
              character.getTemplate().getTemplateType().getSubType().getId().equals(revisedRoninType.getSubType().getId());
  }
  
  private boolean isFirstAge(IGenericCharacter character)
  {
      return character.getTemplate().getTemplateType().getSubType().getId().equals(dreamsType.getSubType().getId()) ||
              character.getTemplate().getTemplateType().getSubType().getId().equals(revisedDreamsType.getSubType().getId());
  }

  private void encodeConnections(
      PdfContentByte directContent,
      IGenericCharacter character,
      IGenericDescription description, float distanceFromTop) throws DocumentException {
    float height = DESTINY_HEIGHT;
    Bounds boxBounds;
    if (isFirstAge(character))
    	boxBounds = configuration.getFirstColumnRectangle(distanceFromTop, height, 2);
    else
    	boxBounds = configuration.getSecondColumnRectangle(distanceFromTop, height, 1);
    IPdfContentBoxEncoder encoder = new PdfHorizontalLineContentEncoder(4, "Sidereal.Connections"); //$NON-NLS-1$
    boxEncoder.encodeBox(directContent, encoder, character, description, boxBounds);
  }

  private float encodeAcquaintances(PdfContentByte directContent, IGenericCharacter character, IGenericDescription description, float distanceFromTop, float height)
      throws DocumentException {
    Bounds boxBounds = configuration.getSecondColumnRectangle(distanceFromTop, height, 1);
    IPdfContentBoxEncoder encoder = new PdfHorizontalLineContentEncoder(1, "Sidereal.Acquaintances"); //$NON-NLS-1$
    boxEncoder.encodeBox(directContent, encoder, character, description, boxBounds);
    return height;
  }

  private float encodeParadox(PdfContentByte directContent, IGenericCharacter character, IGenericDescription description, float distanceFromTop)
      throws DocumentException {
    float height = PARADOX_HEIGHT;
    Bounds boxBounds = configuration.getSecondColumnRectangle(distanceFromTop, height, 1);
    IPdfContentBoxEncoder encoder = new SiderealParadoxEncoder(baseFont, symbolBaseFont, resources);
    boxEncoder.encodeBox(directContent, encoder, character, description, boxBounds);
    return height;
  }

  private float encodeAstrology(PdfContentByte directContent, IGenericCharacter character, IGenericDescription description, float distanceFromTop)
      throws DocumentException {
    float height = COLLEGE_HEIGHT;
    Bounds boxBounds = configuration.getSecondColumnRectangle(distanceFromTop, height, 2);
    IPdfContentBoxEncoder encoder = new SecondEditionAstrologyInfoEncoder(baseFont, resources);
    boxEncoder.encodeBox(directContent, encoder, character, description, boxBounds);
    return height;
  }

  private float encodeResplendentDestiny(PdfContentByte directContent, Bounds boxBounds, IGenericCharacter character, IGenericDescription description)
      throws DocumentException {
    IPdfContentBoxEncoder encoder = new ResplendentDestinyEncoder(baseFont, fontSize, resources);
    boxEncoder.encodeBox(directContent, encoder, character, description, boxBounds);
    return boxBounds.height;
  }

  private Bounds getRightDestinyBounds(float distanceFromTop) {
    return configuration.getThirdColumnRectangle(distanceFromTop, DESTINY_HEIGHT);
  }

  private Bounds getCenterDestinyBounds(float distanceFromTop) {
    return configuration.getSecondColumnRectangle(distanceFromTop, DESTINY_HEIGHT, 1);
  }
  
  private Bounds getLeftDestinyBounds(float distanceFromTop) {
	    return configuration.getFirstColumnRectangle(distanceFromTop, DESTINY_HEIGHT, 1);
	  }

  private float encodeParadoxHelp(PdfContentByte directContent, IGenericCharacter character, IGenericDescription description, float distanceFromTop)
      throws DocumentException {
    float height = DESTINY_HEIGHT;
    Bounds boxBounds = configuration.getThirdColumnRectangle(distanceFromTop, height);
    IPdfContentBoxEncoder encoder = new ParadoxInfoEncoder(baseFont, symbolBaseFont,
    		fontSize, resources, ExaltedEdition.SecondEdition);
    boxEncoder.encodeBox(directContent, encoder, character, description, boxBounds);
    return height;
  }

  private float encodeArcaneFate(PdfContentByte directContent, IGenericCharacter character, IGenericDescription description, float distanceFromTop)
      throws DocumentException {
    float height = DESTINY_HEIGHT;
    Bounds boxBounds = configuration.getFirstColumnRectangle(distanceFromTop, height, 1);
    IPdfContentBoxEncoder encoder = new ArcaneFateInfoEncoder(baseFont, symbolBaseFont,
    		fontSize, resources, ExaltedEdition.SecondEdition);
    boxEncoder.encodeBox(directContent, encoder, character, description, boxBounds);
    return height;
  }

  private float encodeColleges(PdfContentByte directContent, IGenericCharacter character, IGenericDescription description, float distanceFromTop)
      throws DocumentException {
    float height = COLLEGE_HEIGHT;
    Bounds boxBounds = configuration.getFirstColumnRectangle(distanceFromTop, height, 1);
    IPdfContentBoxEncoder encoder = new SiderealCollegeEncoder(baseFont, resources, essenceMax, ExaltedEdition.SecondEdition);
    boxEncoder.encodeBox(directContent, encoder, character, description, boxBounds);
    return height;
  }
}
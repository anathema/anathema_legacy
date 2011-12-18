package net.sf.anathema.character.sidereal.reporting.layout;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfContentByte;
import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.character.IGenericDescription;
import net.sf.anathema.character.generic.impl.rules.ExaltedEdition;
import net.sf.anathema.character.generic.template.TemplateType;
import net.sf.anathema.character.generic.type.CharacterType;
import net.sf.anathema.character.reporting.pdf.content.ReportContent;
import net.sf.anathema.character.reporting.pdf.rendering.elements.Bounds;
import net.sf.anathema.character.reporting.pdf.rendering.general.PdfHorizontalLineContentEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.IBoxContentEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.PdfBoxEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.page.IPdfPageEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.page.PdfPageConfiguration;
import net.sf.anathema.character.sidereal.reporting.rendering.ArcaneFateInfoEncoder;
import net.sf.anathema.character.sidereal.reporting.rendering.ParadoxInfoEncoder;
import net.sf.anathema.character.sidereal.reporting.rendering.SiderealParadoxEncoder;
import net.sf.anathema.character.sidereal.reporting.rendering.astrology.SecondEditionAstrologyInfoEncoder;
import net.sf.anathema.character.sidereal.reporting.rendering.colleges.SiderealCollegeEncoder;
import net.sf.anathema.character.sidereal.reporting.rendering.resplendentdestiny.ResplendentDestinyEncoder;
import net.sf.anathema.lib.resources.IResources;
import net.sf.anathema.lib.util.Identificate;

import static net.sf.anathema.character.reporting.pdf.rendering.page.IVoidStateFormatConstants.PADDING;

public class Simple2ndEditionSiderealDetailsPageEncoder implements IPdfPageEncoder {

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

  private static final TemplateType roninType = new TemplateType(CharacterType.SIDEREAL, new Identificate("Ronin")); //$NON-NLS-1$
  private static final TemplateType revisedRoninType = new TemplateType(CharacterType.SIDEREAL, new Identificate("RevisedRonin")); //$NON-NLS-1$
  private static final TemplateType dreamsType = new TemplateType(CharacterType.SIDEREAL, new Identificate("Dreams")); //$NON-NLS-1$
  private static final TemplateType revisedDreamsType = new TemplateType(CharacterType.SIDEREAL, new Identificate("RevisedDreams")); //$NON-NLS-1$

  public Simple2ndEditionSiderealDetailsPageEncoder(IResources resources, int essenceMax, BaseFont baseFont, BaseFont symbolBaseFont, int fontSize,
    PdfPageConfiguration configuration) {
    this.resources = resources;
    this.essenceMax = essenceMax;
    this.baseFont = baseFont;
    this.symbolBaseFont = symbolBaseFont;
    this.fontSize = fontSize;
    this.configuration = configuration;
    this.boxEncoder = new PdfBoxEncoder(resources, baseFont);
  }

  public void encode(Document document, PdfContentByte directContent, ReportContent content) throws 
    DocumentException {
    if (isRonin(content.getCharacter())) {
      return;
    }
    float distanceFromTop = 0;
    float collegeHeight = 0;
    collegeHeight = encodeColleges(directContent, content, distanceFromTop);
    encodeAstrology(directContent, content, distanceFromTop);
    distanceFromTop += collegeHeight + PADDING;

    //distanceFromTop += PADDING;
    if (!isFirstAge(content.getCharacter())) {
      encodeArcaneFate(directContent, content, distanceFromTop);
    }
    encodeConnections(directContent, content, distanceFromTop);
    distanceFromTop += encodeParadoxHelp(directContent, content, distanceFromTop);
    distanceFromTop += PADDING;
    //float remainingHeight = configuration.getContentHeight() - distanceFromTop;
    //

    float centerDistance = distanceFromTop;
    encodeResplendentDestiny(directContent, getLeftDestinyBounds(distanceFromTop), content);
    distanceFromTop += encodeResplendentDestiny(directContent, getRightDestinyBounds(distanceFromTop), content);
    distanceFromTop += PADDING;

    centerDistance += encodeParadox(directContent, content, centerDistance);
    centerDistance += PADDING;
    encodeAcquaintances(directContent, content, centerDistance, DESTINY_HEIGHT - PARADOX_HEIGHT - PADDING);

    encodeResplendentDestiny(directContent, getLeftDestinyBounds(distanceFromTop), content);
    encodeResplendentDestiny(directContent, getCenterDestinyBounds(distanceFromTop), content);
    encodeResplendentDestiny(directContent, getRightDestinyBounds(distanceFromTop), content);
    //rightDistanceFromTop += encodeParadox(directContent, character, rightDistanceFromTop);
    //rightDistanceFromTop += encodeStanding(directContent, character, rightDistanceFromTop);
    //rightDistanceFromTop += encodeConventions(directContent, character, rightDistanceFromTop);

  }

  private boolean isRonin(IGenericCharacter character) {
    if (character.getTemplate().getTemplateType().getSubType().getId().equals(roninType.getSubType().getId()) || character.getTemplate().getTemplateType().getSubType().getId().equals(revisedRoninType.getSubType().getId())) {
      return true;
    }
    return false;
  }

  private boolean isFirstAge(IGenericCharacter character) {
    if (character.getTemplate().getTemplateType().getSubType().getId().equals(dreamsType.getSubType().getId()) || character.getTemplate().getTemplateType().getSubType().getId().equals(revisedDreamsType.getSubType().getId())) {
      return true;
    }
    return false;
  }

  private void encodeConnections(PdfContentByte directContent, ReportContent content, float distanceFromTop) throws DocumentException {
    float height = DESTINY_HEIGHT;
    Bounds boxBounds;
    if (isFirstAge(content.getCharacter())) {
      boxBounds = configuration.getFirstColumnRectangle(distanceFromTop, height, 2);
    }
    else {
      boxBounds = configuration.getSecondColumnRectangle(distanceFromTop, height, 1);
    }
    IBoxContentEncoder encoder = new PdfHorizontalLineContentEncoder(4, "Sidereal.Connections"); //$NON-NLS-1$
    boxEncoder.encodeBox(content, directContent, encoder, boxBounds);
  }

  private float encodeAcquaintances(PdfContentByte directContent, ReportContent content, float distanceFromTop, 
    float height) throws DocumentException {
    Bounds boxBounds = configuration.getSecondColumnRectangle(distanceFromTop, height, 1);
    IBoxContentEncoder encoder = new PdfHorizontalLineContentEncoder(1, "Sidereal.Acquaintances"); //$NON-NLS-1$
    boxEncoder.encodeBox(content, directContent, encoder, boxBounds);
    return height;
  }

  private float encodeParadox(PdfContentByte directContent, ReportContent content, float distanceFromTop) throws DocumentException {
    float height = PARADOX_HEIGHT;
    Bounds boxBounds = configuration.getSecondColumnRectangle(distanceFromTop, height, 1);
    IBoxContentEncoder encoder = new SiderealParadoxEncoder(baseFont, symbolBaseFont, resources);
    boxEncoder.encodeBox(content, directContent, encoder, boxBounds);
    return height;
  }

  private float encodeAstrology(PdfContentByte directContent, ReportContent content, float distanceFromTop) throws DocumentException {
    float height = COLLEGE_HEIGHT;
    Bounds boxBounds = configuration.getSecondColumnRectangle(distanceFromTop, height, 2);
    IBoxContentEncoder encoder = new SecondEditionAstrologyInfoEncoder(baseFont, resources);
    boxEncoder.encodeBox(content, directContent, encoder, boxBounds);
    return height;
  }

  private float encodeResplendentDestiny(PdfContentByte directContent, Bounds boxBounds, ReportContent content) throws DocumentException {
    IBoxContentEncoder encoder = new ResplendentDestinyEncoder(baseFont, fontSize, resources);
    boxEncoder.encodeBox(content, directContent, encoder, boxBounds);
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

  private float encodeParadoxHelp(PdfContentByte directContent, ReportContent content, float distanceFromTop) throws DocumentException {
    float height = DESTINY_HEIGHT;
    Bounds boxBounds = configuration.getThirdColumnRectangle(distanceFromTop, height);
    IBoxContentEncoder encoder = new ParadoxInfoEncoder(baseFont, symbolBaseFont, fontSize, resources, ExaltedEdition.SecondEdition);
    boxEncoder.encodeBox(content, directContent, encoder, boxBounds);
    return height;
  }

  private float encodeArcaneFate(PdfContentByte directContent, ReportContent content, float distanceFromTop) throws DocumentException {
    float height = DESTINY_HEIGHT;
    Bounds boxBounds = configuration.getFirstColumnRectangle(distanceFromTop, height, 1);
    IBoxContentEncoder encoder = new ArcaneFateInfoEncoder(baseFont, symbolBaseFont, fontSize, resources, ExaltedEdition.SecondEdition);
    boxEncoder.encodeBox(content, directContent, encoder, boxBounds);
    return height;
  }

  private float encodeColleges(PdfContentByte directContent, ReportContent content, float distanceFromTop) throws DocumentException {
    float height = COLLEGE_HEIGHT;
    Bounds boxBounds = configuration.getFirstColumnRectangle(distanceFromTop, height, 1);
    IBoxContentEncoder encoder = new SiderealCollegeEncoder(baseFont, resources, essenceMax, ExaltedEdition.SecondEdition);
    boxEncoder.encodeBox(content, directContent, encoder, boxBounds);
    return height;
  }
}

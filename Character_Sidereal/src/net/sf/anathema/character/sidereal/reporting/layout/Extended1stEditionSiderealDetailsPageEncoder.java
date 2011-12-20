package net.sf.anathema.character.sidereal.reporting.layout;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfContentByte;
import net.sf.anathema.character.generic.impl.rules.ExaltedEdition;
import net.sf.anathema.character.reporting.pdf.content.ReportContent;
import net.sf.anathema.character.reporting.pdf.rendering.elements.Bounds;
import net.sf.anathema.character.reporting.pdf.rendering.general.PdfHorizontalLineContentEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.IBoxContentEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.PdfBoxEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.general.traits.FavorableTraitBoxContentEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.page.IPdfPageEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.page.PdfPageConfiguration;
import net.sf.anathema.character.sidereal.reporting.content.colleges.SiderealCollegeContent;
import net.sf.anathema.character.sidereal.reporting.rendering.ArcaneFateInfoEncoder;
import net.sf.anathema.character.sidereal.reporting.rendering.AstrologyInfoEncoder;
import net.sf.anathema.character.sidereal.reporting.rendering.ParadoxInfoEncoder;
import net.sf.anathema.character.sidereal.reporting.rendering.StandingEncoder;
import net.sf.anathema.character.sidereal.reporting.rendering.resplendentdestiny.ResplendentDestinyEncoder;
import net.sf.anathema.lib.resources.IResources;

import static net.sf.anathema.character.reporting.pdf.rendering.page.IVoidStateFormatConstants.PADDING;

public class Extended1stEditionSiderealDetailsPageEncoder implements IPdfPageEncoder {

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

  public Extended1stEditionSiderealDetailsPageEncoder(IResources resources, int essenceMax, BaseFont baseFont, BaseFont symbolBaseFont, 
    int fontSize, PdfPageConfiguration configuration) {
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
    int distanceFromTop = 0;
    distanceFromTop += encodeColleges(directContent, content, distanceFromTop);
    distanceFromTop += PADDING;
    distanceFromTop += encodeAstrology(directContent, content, distanceFromTop);
    distanceFromTop += PADDING;
    distanceFromTop += encodeArcaneFate(directContent, content, distanceFromTop);
    distanceFromTop += PADDING;
    float remainingHeight = configuration.getContentHeight() - distanceFromTop;
    encodeConnections(directContent, content, remainingHeight, distanceFromTop);

    int centerDistanceFromTop = 0;
    centerDistanceFromTop += encodeResplendentDestiny(directContent, getCenterDestinyBounds(centerDistanceFromTop), content);
    centerDistanceFromTop += PADDING;
    centerDistanceFromTop += encodeResplendentDestiny(directContent, getCenterDestinyBounds(centerDistanceFromTop), content);
    centerDistanceFromTop += PADDING;
    centerDistanceFromTop += encodeResplendentDestiny(directContent, getCenterDestinyBounds(centerDistanceFromTop), content);
    centerDistanceFromTop += PADDING;
    centerDistanceFromTop += encodeAcquaintances(directContent, content, centerDistanceFromTop);

    int rightDistanceFromTop = 0;
    rightDistanceFromTop += encodeResplendentDestiny(directContent, getRightDestinyBounds(rightDistanceFromTop), content);
    rightDistanceFromTop += PADDING;
    rightDistanceFromTop += encodeResplendentDestiny(directContent, getRightDestinyBounds(rightDistanceFromTop), content);
    rightDistanceFromTop += PADDING;
    rightDistanceFromTop += encodeParadox(directContent, content, rightDistanceFromTop);
    rightDistanceFromTop += PADDING;
    rightDistanceFromTop += encodeStanding(directContent, content, rightDistanceFromTop);
    rightDistanceFromTop += PADDING;
    rightDistanceFromTop += encodeConventions(directContent, content, rightDistanceFromTop);
    rightDistanceFromTop += PADDING;

  }

  private void encodeConnections(PdfContentByte directContent, ReportContent content, float height, 
    int distanceFromTop) throws DocumentException {
    Bounds boxBounds = configuration.getFirstColumnRectangle(distanceFromTop, height, 3);
    IBoxContentEncoder encoder = new PdfHorizontalLineContentEncoder(4, "Sidereal.Connections"); //$NON-NLS-1$
    boxEncoder.encodeBox(content, directContent, encoder, boxBounds);
  }

  private float encodeAcquaintances(PdfContentByte directContent, ReportContent content, 
    int distanceFromTop) throws DocumentException {
    float height = 145;
    Bounds boxBounds = configuration.getSecondColumnRectangle(distanceFromTop, height, 1);
    IBoxContentEncoder encoder = new PdfHorizontalLineContentEncoder(1, "Sidereal.Acquaintances"); //$NON-NLS-1$
    boxEncoder.encodeBox(content, directContent, encoder, boxBounds);
    return height;
  }

  private float encodeConventions(PdfContentByte directContent, ReportContent content, 
    int distanceFromTop) throws DocumentException {
    float height = THIRD_BLOCK_HEIGHT - STANDING_HEIGHT - PADDING;
    Bounds boxBounds = configuration.getThirdColumnRectangle(distanceFromTop, height);
    IBoxContentEncoder encoder = new PdfHorizontalLineContentEncoder(2, "Sidereal.Conventions"); //$NON-NLS-1$
    boxEncoder.encodeBox(content, directContent, encoder, boxBounds);
    return height;
  }

  private float encodeStanding(PdfContentByte directContent, ReportContent content, 
    int distanceFromTop) throws DocumentException {
    float height = STANDING_HEIGHT;
    Bounds boxBounds = configuration.getThirdColumnRectangle(distanceFromTop, height);
    IBoxContentEncoder encoder = new StandingEncoder(fontSize, resources);
    boxEncoder.encodeBox(content, directContent, encoder, boxBounds);
    return height;
  }

  private float encodeAstrology(PdfContentByte directContent, ReportContent content, int distanceFromTop) throws DocumentException {
    float height = DESTINY_HEIGHT;
    Bounds boxBounds = configuration.getFirstColumnRectangle(distanceFromTop, height, 1);
    IBoxContentEncoder encoder = new AstrologyInfoEncoder(baseFont, resources);
    boxEncoder.encodeBox(content, directContent, encoder, boxBounds);
    return height;
  }

  private float encodeResplendentDestiny(PdfContentByte directContent, Bounds boxBounds, ReportContent content) throws DocumentException {
    IBoxContentEncoder encoder = new ResplendentDestinyEncoder(fontSize, resources);
    boxEncoder.encodeBox(content, directContent, encoder, boxBounds);
    return boxBounds.height;
  }

  private Bounds getRightDestinyBounds(int distanceFromTop) {
    return configuration.getThirdColumnRectangle(distanceFromTop, DESTINY_HEIGHT);
  }

  private Bounds getCenterDestinyBounds(int distanceFromTop) {
    return configuration.getSecondColumnRectangle(distanceFromTop, DESTINY_HEIGHT, 1);
  }

  private float encodeParadox(PdfContentByte directContent, ReportContent content, int distanceFromTop) throws DocumentException {
    float height = DESTINY_HEIGHT;
    Bounds boxBounds = configuration.getThirdColumnRectangle(distanceFromTop, height);
    IBoxContentEncoder encoder = new ParadoxInfoEncoder(baseFont, symbolBaseFont, fontSize, resources, ExaltedEdition.FirstEdition);
    boxEncoder.encodeBox(content, directContent, encoder, boxBounds);
    return height;
  }

  private float encodeArcaneFate(PdfContentByte directContent, ReportContent content, int distanceFromTop) throws DocumentException {
    float height = THIRD_BLOCK_HEIGHT;
    Bounds boxBounds = configuration.getFirstColumnRectangle(distanceFromTop, height, 1);
    IBoxContentEncoder encoder = new ArcaneFateInfoEncoder(baseFont, symbolBaseFont, fontSize, resources, ExaltedEdition.FirstEdition);
    boxEncoder.encodeBox(content, directContent, encoder, boxBounds);
    return height;
  }

  private float encodeColleges(PdfContentByte directContent, ReportContent content, int distanceFromTop) throws DocumentException {
    float height = COLLEGE_HEIGHT;
    Bounds boxBounds = configuration.getFirstColumnRectangle(distanceFromTop, height, 1);
    IBoxContentEncoder encoder = new FavorableTraitBoxContentEncoder(SiderealCollegeContent.class);
    boxEncoder.encodeBox(content, directContent, encoder, boxBounds);
    return height;
  }
}

package net.sf.anathema.character.sidereal.reporting.layout;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import net.sf.anathema.character.generic.impl.rules.ExaltedEdition;
import net.sf.anathema.character.reporting.pdf.content.ReportContent;
import net.sf.anathema.character.reporting.pdf.rendering.Bounds;
import net.sf.anathema.character.reporting.pdf.rendering.general.HorizontalLineBoxContentEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.ContentEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.PdfBoxEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.general.traits.FavorableTraitBoxContentEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.graphics.SheetGraphics;
import net.sf.anathema.character.reporting.pdf.rendering.page.PageConfiguration;
import net.sf.anathema.character.reporting.pdf.rendering.page.PageEncoder;
import net.sf.anathema.character.sidereal.reporting.content.SiderealCollegeContent;
import net.sf.anathema.character.sidereal.reporting.rendering.ArcaneFateInfoEncoder;
import net.sf.anathema.character.sidereal.reporting.rendering.AstrologyInfoEncoder;
import net.sf.anathema.character.sidereal.reporting.rendering.ParadoxInfoEncoder;
import net.sf.anathema.character.sidereal.reporting.rendering.StandingEncoder;
import net.sf.anathema.character.sidereal.reporting.rendering.resplendentdestiny.ResplendentDestinyEncoder;
import net.sf.anathema.lib.resources.IResources;

import static net.sf.anathema.character.reporting.pdf.rendering.page.IVoidStateFormatConstants.PADDING;

public class Sidereal1stEditionDetailsPageEncoder implements PageEncoder {

  private final static float COLLEGE_HEIGHT = 312;
  private final static float DESTINY_HEIGHT = (COLLEGE_HEIGHT - PADDING) / 2;
  private final static float THIRD_BLOCK_HEIGHT = 145;
  private final static float STANDING_HEIGHT = 45;
  private final IResources resources;
  private final PdfBoxEncoder boxEncoder;
  private final PageConfiguration configuration;
  private final int fontSize;

  public Sidereal1stEditionDetailsPageEncoder(IResources resources, int fontSize, PageConfiguration configuration) {
    this.resources = resources;
    this.fontSize = fontSize;
    this.configuration = configuration;
    this.boxEncoder = new PdfBoxEncoder(resources);
  }

  public void encode(Document document, SheetGraphics graphics, ReportContent content) throws DocumentException {
    int distanceFromTop = 0;
    distanceFromTop += encodeColleges(graphics, content, distanceFromTop);
    distanceFromTop += PADDING;
    distanceFromTop += encodeAstrology(graphics, content, distanceFromTop);
    distanceFromTop += PADDING;
    distanceFromTop += encodeArcaneFate(graphics, content, distanceFromTop);
    distanceFromTop += PADDING;
    float remainingHeight = configuration.getContentHeight() - distanceFromTop;
    encodeConnections(graphics, content, remainingHeight, distanceFromTop);

    int centerDistanceFromTop = 0;
    centerDistanceFromTop += encodeResplendentDestiny(graphics, getCenterDestinyBounds(centerDistanceFromTop), content);
    centerDistanceFromTop += PADDING;
    centerDistanceFromTop += encodeResplendentDestiny(graphics, getCenterDestinyBounds(centerDistanceFromTop), content);
    centerDistanceFromTop += PADDING;
    centerDistanceFromTop += encodeResplendentDestiny(graphics, getCenterDestinyBounds(centerDistanceFromTop), content);
    centerDistanceFromTop += PADDING;
    centerDistanceFromTop += encodeAcquaintances(graphics, content, centerDistanceFromTop);

    int rightDistanceFromTop = 0;
    rightDistanceFromTop += encodeResplendentDestiny(graphics, getRightDestinyBounds(rightDistanceFromTop), content);
    rightDistanceFromTop += PADDING;
    rightDistanceFromTop += encodeResplendentDestiny(graphics, getRightDestinyBounds(rightDistanceFromTop), content);
    rightDistanceFromTop += PADDING;
    rightDistanceFromTop += encodeParadox(graphics, content, rightDistanceFromTop);
    rightDistanceFromTop += PADDING;
    rightDistanceFromTop += encodeStanding(graphics, content, rightDistanceFromTop);
    rightDistanceFromTop += PADDING;
    rightDistanceFromTop += encodeConventions(graphics, content, rightDistanceFromTop);
    rightDistanceFromTop += PADDING;

  }

  private void encodeConnections(SheetGraphics graphics, ReportContent content, float height, int distanceFromTop) throws DocumentException {
    Bounds boxBounds = configuration.getFirstColumnRectangle(distanceFromTop, height, 3);
    ContentEncoder encoder = new HorizontalLineBoxContentEncoder(4, resources, "Sidereal.Connections"); //$NON-NLS-1$
    boxEncoder.encodeBox(content, graphics, encoder, boxBounds);
  }

  private float encodeAcquaintances(SheetGraphics graphics, ReportContent content, int distanceFromTop) throws DocumentException {
    float height = 145;
    Bounds boxBounds = configuration.getSecondColumnRectangle(distanceFromTop, height, 1);
    ContentEncoder encoder = new HorizontalLineBoxContentEncoder(1, resources, "Sidereal.Acquaintances"); //$NON-NLS-1$
    boxEncoder.encodeBox(content, graphics, encoder, boxBounds);
    return height;
  }

  private float encodeConventions(SheetGraphics graphics, ReportContent content, int distanceFromTop) throws DocumentException {
    float height = THIRD_BLOCK_HEIGHT - STANDING_HEIGHT - PADDING;
    Bounds boxBounds = configuration.getThirdColumnRectangle(distanceFromTop, height);
    ContentEncoder encoder = new HorizontalLineBoxContentEncoder(2, resources, "Sidereal.Conventions"); //$NON-NLS-1$
    boxEncoder.encodeBox(content, graphics, encoder, boxBounds);
    return height;
  }

  private float encodeStanding(SheetGraphics graphics, ReportContent content, int distanceFromTop) throws DocumentException {
    float height = STANDING_HEIGHT;
    Bounds boxBounds = configuration.getThirdColumnRectangle(distanceFromTop, height);
    ContentEncoder encoder = new StandingEncoder(fontSize, resources);
    boxEncoder.encodeBox(content, graphics, encoder, boxBounds);
    return height;
  }

  private float encodeAstrology(SheetGraphics graphics, ReportContent content, int distanceFromTop) throws DocumentException {
    float height = DESTINY_HEIGHT;
    Bounds boxBounds = configuration.getFirstColumnRectangle(distanceFromTop, height, 1);
    ContentEncoder encoder = new AstrologyInfoEncoder(resources);
    boxEncoder.encodeBox(content, graphics, encoder, boxBounds);
    return height;
  }

  private float encodeResplendentDestiny(SheetGraphics graphics, Bounds boxBounds, ReportContent content) throws DocumentException {
    ContentEncoder encoder = new ResplendentDestinyEncoder(fontSize, resources);
    boxEncoder.encodeBox(content, graphics, encoder, boxBounds);
    return boxBounds.height;
  }

  private Bounds getRightDestinyBounds(int distanceFromTop) {
    return configuration.getThirdColumnRectangle(distanceFromTop, DESTINY_HEIGHT);
  }

  private Bounds getCenterDestinyBounds(int distanceFromTop) {
    return configuration.getSecondColumnRectangle(distanceFromTop, DESTINY_HEIGHT, 1);
  }

  private float encodeParadox(SheetGraphics graphics, ReportContent content, int distanceFromTop) throws DocumentException {
    float height = DESTINY_HEIGHT;
    Bounds boxBounds = configuration.getThirdColumnRectangle(distanceFromTop, height);
    ContentEncoder encoder = new ParadoxInfoEncoder(fontSize, resources, ExaltedEdition.FirstEdition);
    boxEncoder.encodeBox(content, graphics, encoder, boxBounds);
    return height;
  }

  private float encodeArcaneFate(SheetGraphics graphics, ReportContent content, int distanceFromTop) throws DocumentException {
    float height = THIRD_BLOCK_HEIGHT;
    Bounds boxBounds = configuration.getFirstColumnRectangle(distanceFromTop, height, 1);
    ContentEncoder encoder = new ArcaneFateInfoEncoder(fontSize, resources, ExaltedEdition.FirstEdition);
    boxEncoder.encodeBox(content, graphics, encoder, boxBounds);
    return height;
  }

  private float encodeColleges(SheetGraphics graphics, ReportContent content, int distanceFromTop) throws DocumentException {
    float height = COLLEGE_HEIGHT;
    Bounds boxBounds = configuration.getFirstColumnRectangle(distanceFromTop, height, 1);
    ContentEncoder encoder = new FavorableTraitBoxContentEncoder(SiderealCollegeContent.class);
    boxEncoder.encodeBox(content, graphics, encoder, boxBounds);
    return height;
  }
}

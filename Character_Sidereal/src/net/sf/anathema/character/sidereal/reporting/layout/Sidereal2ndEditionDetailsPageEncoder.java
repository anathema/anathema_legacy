package net.sf.anathema.character.sidereal.reporting.layout;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.template.TemplateType;
import net.sf.anathema.character.generic.type.CharacterType;
import net.sf.anathema.character.reporting.pdf.content.ReportSession;
import net.sf.anathema.character.reporting.pdf.rendering.extent.Bounds;
import net.sf.anathema.character.reporting.pdf.rendering.general.HorizontalLineBoxContentEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.ContentEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.PdfBoxEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.general.traits.FavorableTraitBoxContentEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.graphics.SheetGraphics;
import net.sf.anathema.character.reporting.pdf.rendering.page.PageConfiguration;
import net.sf.anathema.character.reporting.pdf.rendering.page.PageEncoder;
import net.sf.anathema.character.sidereal.reporting.content.SiderealCollegeContent;
import net.sf.anathema.character.sidereal.reporting.rendering.ArcaneFateInfoEncoder;
import net.sf.anathema.character.sidereal.reporting.rendering.ParadoxInfoEncoder;
import net.sf.anathema.character.sidereal.reporting.rendering.astrology.SecondEditionAstrologyInfoEncoder;
import net.sf.anathema.character.sidereal.reporting.rendering.greatcurse.ParadoxEncoder;
import net.sf.anathema.character.sidereal.reporting.rendering.resplendentdestiny.ResplendentDestinyEncoder;
import net.sf.anathema.lib.resources.IResources;
import net.sf.anathema.lib.util.Identificate;

import static net.sf.anathema.character.reporting.pdf.rendering.page.IVoidStateFormatConstants.PADDING;

public class Sidereal2ndEditionDetailsPageEncoder implements PageEncoder {

  private final static float COLLEGE_HEIGHT = 312;
  private final static float DESTINY_HEIGHT = (COLLEGE_HEIGHT - PADDING) / 2;
  private final static float PARADOX_HEIGHT = 45;
  private final IResources resources;
  private final PdfBoxEncoder boxEncoder;
  private final PageConfiguration configuration;
  private final int fontSize;

  private static final TemplateType roninType = new TemplateType(CharacterType.SIDEREAL,
          new Identificate("Ronin")); //$NON-NLS-1$
  private static final TemplateType revisedRoninType = new TemplateType(CharacterType.SIDEREAL,
          new Identificate("RevisedRonin")); //$NON-NLS-1$
  private static final TemplateType dreamsType = new TemplateType(CharacterType.SIDEREAL,
          new Identificate("Dreams")); //$NON-NLS-1$
  private static final TemplateType revisedDreamsType = new TemplateType(CharacterType.SIDEREAL,
          new Identificate("RevisedDreams")); //$NON-NLS-1$

  public Sidereal2ndEditionDetailsPageEncoder(IResources resources, int fontSize, PageConfiguration configuration) {
    this.resources = resources;
    this.fontSize = fontSize;
    this.configuration = configuration;
    this.boxEncoder = new PdfBoxEncoder();
  }

  @Override
  public void encode(Document document, SheetGraphics graphics, ReportSession session) throws DocumentException {
    if (isRonin(session.getCharacter())) {
      return;
    }
    float distanceFromTop = 0;
    float collegeHeight = encodeColleges(graphics, session, distanceFromTop);
    encodeAstrology(graphics, session, distanceFromTop);
    distanceFromTop += collegeHeight + PADDING;

    if (!isFirstAge(session.getCharacter())) {
      encodeArcaneFate(graphics, session, distanceFromTop);
    }
    encodeConnections(graphics, session, distanceFromTop);
    distanceFromTop += encodeParadoxHelp(graphics, session, distanceFromTop);
    distanceFromTop += PADDING;

    float centerDistance = distanceFromTop;
    encodeResplendentDestiny(graphics, getLeftDestinyBounds(distanceFromTop), session);
    distanceFromTop += encodeResplendentDestiny(graphics, getRightDestinyBounds(distanceFromTop), session);
    distanceFromTop += PADDING;

    centerDistance += encodeParadox(graphics, session, centerDistance);
    centerDistance += PADDING;
    encodeAcquaintances(graphics, session, centerDistance, DESTINY_HEIGHT - PARADOX_HEIGHT - PADDING);

    encodeResplendentDestiny(graphics, getLeftDestinyBounds(distanceFromTop), session);
    encodeResplendentDestiny(graphics, getCenterDestinyBounds(distanceFromTop), session);
    encodeResplendentDestiny(graphics, getRightDestinyBounds(distanceFromTop), session);
  }

  private boolean isRonin(IGenericCharacter character) {
    return character.getTemplate().getTemplateType().getSubType().getId().equals(
            roninType.getSubType().getId()) || character.getTemplate().getTemplateType().getSubType().getId().equals(
            revisedRoninType.getSubType().getId());
  }

  private boolean isFirstAge(IGenericCharacter character) {
    return character.getTemplate().getTemplateType().getSubType().getId().equals(
            dreamsType.getSubType().getId()) || character.getTemplate().getTemplateType().getSubType().getId().equals(
            revisedDreamsType.getSubType().getId());
  }

  private void encodeConnections(SheetGraphics graphics, ReportSession session,
                                 float distanceFromTop) throws DocumentException {
    float height = DESTINY_HEIGHT;
    Bounds boxBounds;
    if (isFirstAge(session.getCharacter())) {
      boxBounds = configuration.getFirstColumnRectangle(distanceFromTop, height, 2);
    } else {
      boxBounds = configuration.getSecondColumnRectangle(distanceFromTop, height, 1);
    }
    ContentEncoder encoder = new HorizontalLineBoxContentEncoder(4, resources, "Sidereal.Connections"); //$NON-NLS-1$
    boxEncoder.encodeBox(session, graphics, encoder, boxBounds);
  }

  private float encodeAcquaintances(SheetGraphics graphics, ReportSession session, float distanceFromTop,
                                    float height) throws DocumentException {
    Bounds boxBounds = configuration.getSecondColumnRectangle(distanceFromTop, height, 1);
    ContentEncoder encoder = new HorizontalLineBoxContentEncoder(1, resources, "Sidereal.Acquaintances"); //$NON-NLS-1$
    boxEncoder.encodeBox(session, graphics, encoder, boxBounds);
    return height;
  }

  private float encodeParadox(SheetGraphics graphics, ReportSession session,
                              float distanceFromTop) throws DocumentException {
    float height = PARADOX_HEIGHT;
    Bounds boxBounds = configuration.getSecondColumnRectangle(distanceFromTop, height, 1);
    ContentEncoder encoder = new ParadoxEncoder(resources);
    boxEncoder.encodeBox(session, graphics, encoder, boxBounds);
    return height;
  }

  private float encodeAstrology(SheetGraphics graphics, ReportSession session,
                                float distanceFromTop) throws DocumentException {
    float height = COLLEGE_HEIGHT;
    Bounds boxBounds = configuration.getSecondColumnRectangle(distanceFromTop, height, 2);
    ContentEncoder encoder = new SecondEditionAstrologyInfoEncoder(resources);
    boxEncoder.encodeBox(session, graphics, encoder, boxBounds);
    return height;
  }

  private float encodeResplendentDestiny(SheetGraphics graphics, Bounds boxBounds,
                                         ReportSession session) throws DocumentException {
    ContentEncoder encoder = new ResplendentDestinyEncoder(fontSize, resources);
    boxEncoder.encodeBox(session, graphics, encoder, boxBounds);
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

  private float encodeParadoxHelp(SheetGraphics graphics, ReportSession session,
                                  float distanceFromTop) throws DocumentException {
    float height = DESTINY_HEIGHT;
    Bounds boxBounds = configuration.getThirdColumnRectangle(distanceFromTop, height);
    ContentEncoder encoder = new ParadoxInfoEncoder(fontSize, resources);
    boxEncoder.encodeBox(session, graphics, encoder, boxBounds);
    return height;
  }

  private float encodeArcaneFate(SheetGraphics graphics, ReportSession session,
                                 float distanceFromTop) throws DocumentException {
    float height = DESTINY_HEIGHT;
    Bounds boxBounds = configuration.getFirstColumnRectangle(distanceFromTop, height, 1);
    ContentEncoder encoder = new ArcaneFateInfoEncoder(fontSize, resources);
    boxEncoder.encodeBox(session, graphics, encoder, boxBounds);
    return height;
  }

  private float encodeColleges(SheetGraphics graphics, ReportSession session,
                               float distanceFromTop) throws DocumentException {
    float height = COLLEGE_HEIGHT;
    Bounds boxBounds = configuration.getFirstColumnRectangle(distanceFromTop, height, 1);
    ContentEncoder encoder = new FavorableTraitBoxContentEncoder(SiderealCollegeContent.class);
    boxEncoder.encodeBox(session, graphics, encoder, boxBounds);
    return height;
  }
}

package net.sf.anathema.character.lunar.reporting.layout;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import net.sf.anathema.character.equipment.impl.reporting.rendering.panoply.ArmourEncoder;
import net.sf.anathema.character.equipment.impl.reporting.rendering.panoply.ArmourTableEncoder;
import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.IMagic;
import net.sf.anathema.character.lunar.reporting.content.equipment.LunarArmourContent;
import net.sf.anathema.character.lunar.reporting.rendering.SecondEditionLunarSpiritFormEncoder;
import net.sf.anathema.character.lunar.reporting.rendering.SecondEditionPowersEncoder;
import net.sf.anathema.character.lunar.reporting.rendering.beastform.SecondEditionDBTCombatEncoder;
import net.sf.anathema.character.lunar.reporting.rendering.beastform.SecondEditionLunarDBTFormEncoder;
import net.sf.anathema.character.lunar.reporting.rendering.health.SecondEditionLunarHealthAndMovementEncoder;
import net.sf.anathema.character.lunar.reporting.rendering.heartsblood.SecondEditionLunarHeartsBloodEncoder;
import net.sf.anathema.character.lunar.reporting.rendering.knacks.KnackEncoder;
import net.sf.anathema.character.reporting.pdf.content.ReportSession;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.EncoderRegistry;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.EncodingMetrics;
import net.sf.anathema.character.reporting.pdf.rendering.extent.Bounds;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.BoxBoundsFactory;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.ContentEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.PdfBoxEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.general.table.ITableEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.graphics.SheetGraphics;
import net.sf.anathema.character.reporting.pdf.rendering.page.PageConfiguration;
import net.sf.anathema.character.reporting.pdf.rendering.page.PageEncoder;
import net.sf.anathema.lib.resources.IResources;

import static net.sf.anathema.character.lunar.reporting.rendering.EncoderIds.ARSENAL_LUNAR;
import static net.sf.anathema.character.reporting.pdf.rendering.page.IVoidStateFormatConstants.PADDING;

public class Lunar2ndEditionAdditionalPageEncoder implements PageEncoder {
  private final IResources resources;

  private final PageConfiguration pageConfiguration;
  private final PdfBoxEncoder boxEncoder;
  private EncoderRegistry encoderRegistry;

  public Lunar2ndEditionAdditionalPageEncoder(EncoderRegistry encoderRegistry, IResources resources, PageConfiguration pageConfiguration) {
    this.encoderRegistry = encoderRegistry;
    this.resources = resources;
    this.pageConfiguration = pageConfiguration;
    this.boxEncoder = new PdfBoxEncoder();
  }

  public void encode(Document document, SheetGraphics graphics, ReportSession session) throws DocumentException {
    int firstSet = 0, secondSet = 0;
    boolean DBT = hasDBT(session.getCharacter());
    firstSet += encodeSpiritForms(graphics, session, firstSet, DBT);
    if (DBT) {
      firstSet += PADDING;
      secondSet = firstSet;
      firstSet += encodeArsenal(graphics, session, firstSet) + PADDING;
      firstSet += encodePanoply(graphics, session, firstSet, 80) + PADDING;
      firstSet += encodeMovementAndHealth(graphics, session, firstSet, 99);

      secondSet += encodeCombatStats(graphics, session, secondSet) + PADDING;
      encodePowers(graphics, session, secondSet, firstSet - secondSet, false);
    } else {
      encodePowers(graphics, session, secondSet, firstSet, true);
    }

    firstSet += PADDING;

    int remaining = (int) (pageConfiguration.getContentHeight() - firstSet);
    encodeKnacks(graphics, session, firstSet, remaining);
    encodeAnimalForms(graphics, session, firstSet, remaining);
  }

  private boolean hasDBT(IGenericCharacter character) {
    for (IMagic magic : character.getAllLearnedMagic()) {
      if (magic instanceof ICharm && magic.getId().equals("Lunar.DeadlyBeastmanTransformation")) {
        return true;
      }
    }
    return false;
  }

  private int encodeSpiritForms(SheetGraphics graphics, ReportSession session, int distanceFromTop, boolean DBT) throws DocumentException {
    int attributeHeight = 80;
    Bounds spiritBounds = pageConfiguration
            .getColumnRectangle((float) distanceFromTop, (float) attributeHeight, 1, PageConfiguration.Offset(0));
    Bounds beastBounds = pageConfiguration
            .getColumnRectangle((float) distanceFromTop, (float) attributeHeight, 2, PageConfiguration.Offset(1));
    SecondEditionLunarSpiritFormEncoder spiritEncoder = new SecondEditionLunarSpiritFormEncoder(resources);
    SecondEditionLunarDBTFormEncoder beastEncoder = new SecondEditionLunarDBTFormEncoder(resources);
    boxEncoder.encodeBox(session, graphics, spiritEncoder, spiritBounds);
    if (DBT) {
      boxEncoder.encodeBox(session, graphics, beastEncoder, beastBounds);
    }
    return attributeHeight;
  }

  private float encodeArsenal(SheetGraphics graphics, ReportSession session, float distanceFromTop) throws DocumentException {
    EncodingMetrics metrics = EncodingMetrics.From(graphics, session);
    float contentWidth = BoxBoundsFactory.getContentWidth(pageConfiguration, 2);
    float height = encoderRegistry.getPreferredHeight(metrics, contentWidth, ARSENAL_LUNAR);
    ContentEncoder weaponryEncoder = encoderRegistry.createEncoder(resources, session, ARSENAL_LUNAR);
    Bounds bounds = pageConfiguration.getColumnRectangle(distanceFromTop, height, 2, PageConfiguration.Offset(0));
    boxEncoder.encodeBox(session, graphics, weaponryEncoder, bounds);
    return height;
  }

  private float encodePanoply(SheetGraphics graphics, ReportSession session, float distanceFromTop, float height) throws DocumentException {
    Bounds bounds = pageConfiguration.getColumnRectangle(distanceFromTop, height, 2, PageConfiguration.Offset(0));
    ITableEncoder armourTableEncoder = new ArmourTableEncoder(LunarArmourContent.class);
    ContentEncoder contentEncoder = new ArmourEncoder(resources, armourTableEncoder);
    boxEncoder.encodeBox(session, graphics, contentEncoder, bounds);
    return height;
  }

  private float encodeMovementAndHealth(SheetGraphics graphics, ReportSession session, float distanceFromTop, float height) throws DocumentException {
    Bounds bounds = pageConfiguration.getColumnRectangle(distanceFromTop, height, 2, PageConfiguration.Offset(0));
    ContentEncoder encoder = new SecondEditionLunarHealthAndMovementEncoder(resources);
    boxEncoder.encodeBox(session, graphics, encoder, bounds);
    return height;
  }

  private float encodeCombatStats(SheetGraphics graphics, ReportSession session, float distanceFromTop) throws DocumentException {
    int height = 64;
    Bounds bounds =
            pageConfiguration.getColumnRectangle(distanceFromTop, (float) height, 1, PageConfiguration.Offset(2));
    SecondEditionDBTCombatEncoder encoder = new SecondEditionDBTCombatEncoder(resources);
    boxEncoder.encodeBox(session, graphics, encoder, bounds);
    return height;
  }

  private void encodePowers(SheetGraphics graphics, ReportSession session, float distanceFromTop, float height, boolean isHorizontal) throws DocumentException {
    Bounds bounds = isHorizontal ? pageConfiguration
            .getColumnRectangle(distanceFromTop, height, 2, PageConfiguration.Offset(1)) : pageConfiguration
            .getColumnRectangle(distanceFromTop, height, 1, PageConfiguration.Offset(2));
    SecondEditionPowersEncoder encoder = new SecondEditionPowersEncoder(resources, isHorizontal);
    boxEncoder.encodeBox(session, graphics, encoder, bounds);
  }

  private void encodeKnacks(SheetGraphics graphics, ReportSession session, float distanceFromTop, int height) throws DocumentException {
    Bounds bounds =
            pageConfiguration.getColumnRectangle(distanceFromTop, (float) height, 1, PageConfiguration.Offset(0));
    ContentEncoder encoder = new KnackEncoder();
    boxEncoder.encodeBox(session, graphics, encoder, bounds);
  }

  private void encodeAnimalForms(SheetGraphics graphics, ReportSession session, float distanceFromTop, float height) throws DocumentException {
    Bounds bounds = pageConfiguration.getColumnRectangle(distanceFromTop, height, 2, PageConfiguration.Offset(1));
    ContentEncoder encoder = new SecondEditionLunarHeartsBloodEncoder(resources);
    boxEncoder.encodeBox(session, graphics, encoder, bounds);
  }
}

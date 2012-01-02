package net.sf.anathema.character.lunar.reporting.layout;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import net.sf.anathema.character.equipment.impl.reporting.rendering.ArmourEncoder;
import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.IMagic;
import net.sf.anathema.character.lunar.reporting.rendering.SecondEditionLunarSpiritFormEncoder;
import net.sf.anathema.character.lunar.reporting.rendering.SecondEditionPowersEncoder;
import net.sf.anathema.character.lunar.reporting.rendering.beastform.SecondEditionDBTCombatEncoder;
import net.sf.anathema.character.lunar.reporting.rendering.beastform.SecondEditionLunarDBTFormEncoder;
import net.sf.anathema.character.lunar.reporting.rendering.equipment.LunarEquipmentEncoders;
import net.sf.anathema.character.lunar.reporting.rendering.health.SecondEditionLunarHealthAndMovementEncoder;
import net.sf.anathema.character.lunar.reporting.rendering.heartsblood.SecondEditionLunarHeartsBloodEncoder;
import net.sf.anathema.character.lunar.reporting.rendering.knacks.KnackEncoder;
import net.sf.anathema.character.reporting.pdf.content.ReportContent;
import net.sf.anathema.character.reporting.pdf.layout.IWeaponryPartEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.Bounds;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.BoxContentEncoderRegistry;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.IBoxContentEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.PdfBoxEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.general.table.ITableEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.graphics.SheetGraphics;
import net.sf.anathema.character.reporting.pdf.rendering.page.IPdfPageEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.page.PdfPageConfiguration;
import net.sf.anathema.lib.resources.IResources;

import static net.sf.anathema.character.reporting.pdf.rendering.boxes.EncoderAttributeType.PreferredHeight;
import static net.sf.anathema.character.reporting.pdf.rendering.page.IVoidStateFormatConstants.PADDING;

public class Lunar2ndEditionAdditionalPageEncoder implements IPdfPageEncoder {
  private final IResources resources;

  private final PdfPageConfiguration pageConfiguration;
  private final PdfBoxEncoder boxEncoder;
  private BoxContentEncoderRegistry encoderRegistry;
  private final IWeaponryPartEncoder partEncoder;

  public Lunar2ndEditionAdditionalPageEncoder(BoxContentEncoderRegistry encoderRegistry, IWeaponryPartEncoder partEncoder, IResources resources,
    PdfPageConfiguration pageConfiguration) {
    this.encoderRegistry = encoderRegistry;
    this.partEncoder = partEncoder;
    this.resources = resources;
    this.pageConfiguration = pageConfiguration;
    this.boxEncoder = new PdfBoxEncoder(resources);
  }

  public void encode(Document document, SheetGraphics graphics, ReportContent content) throws DocumentException {
    int firstSet = 0, secondSet = 0;
    boolean DBT = hasDBT(content.getCharacter());
    firstSet += encodeSpiritForms(graphics, content, firstSet, DBT);
    if (DBT) {
      firstSet += PADDING;
      secondSet = firstSet;
      firstSet += encodeArsenal(graphics, content, firstSet) + PADDING;
      firstSet += encodePanoply(graphics, content, firstSet, 80) + PADDING;
      firstSet += encodeMovementAndHealth(graphics, content, firstSet, 99);

      secondSet += encodeCombatStats(graphics, content, secondSet) + PADDING;
      encodePowers(graphics, content, secondSet, firstSet - secondSet, false);
    }
    else {
      encodePowers(graphics, content, secondSet, firstSet, true);
    }

    firstSet += PADDING;

    int remaining = (int) (pageConfiguration.getContentHeight() - firstSet);
    encodeKnacks(graphics, content, firstSet, remaining);
    encodeAnimalForms(graphics, content, firstSet, remaining);
  }

  private boolean hasDBT(IGenericCharacter character) {
    for (IMagic magic : character.getAllLearnedMagic()) {
      if (magic instanceof ICharm && magic.getId().equals("Lunar.DeadlyBeastmanTransformation")) {
        return true;
      }
    }
    return false;
  }

  private int encodeSpiritForms(SheetGraphics graphics, ReportContent content, int distanceFromTop, boolean DBT) throws DocumentException {
    int attributeHeight = 80;
    Bounds spiritBounds = pageConfiguration.getFirstColumnRectangle(distanceFromTop, attributeHeight, 1);
    Bounds beastBounds = pageConfiguration.getSecondColumnRectangle(distanceFromTop, attributeHeight, 2);
    SecondEditionLunarSpiritFormEncoder spiritEncoder = new SecondEditionLunarSpiritFormEncoder(resources);
    SecondEditionLunarDBTFormEncoder beastEncoder = new SecondEditionLunarDBTFormEncoder(resources);
    boxEncoder.encodeBox(content, graphics, spiritEncoder, spiritBounds);
    if (DBT) {
      boxEncoder.encodeBox(content, graphics, beastEncoder, beastBounds);
    }
    return attributeHeight;
  }

  private float encodeArsenal(SheetGraphics graphics, ReportContent content, float distanceFromTop) throws DocumentException {
    float height = encoderRegistry.getValue(LunarWeaponryEncoderFactory.ID, PreferredHeight, content);
    IBoxContentEncoder weaponryEncoder = encoderRegistry.createEncoder(LunarWeaponryEncoderFactory.ID, resources, content);
    Bounds bounds = pageConfiguration.getFirstColumnRectangle(distanceFromTop, height, 2);
    boxEncoder.encodeBox(content, graphics, weaponryEncoder, bounds);
    return height;
  }

  private float encodePanoply(SheetGraphics graphics, ReportContent content, float distanceFromTop, float height) throws DocumentException {
    Bounds bounds = pageConfiguration.getFirstColumnRectangle(distanceFromTop, height, 2);
    ITableEncoder armourTableEncoder = LunarEquipmentEncoders.CreateArmourEncoder();
    IBoxContentEncoder contentEncoder = new ArmourEncoder(armourTableEncoder);
    boxEncoder.encodeBox(content, graphics, contentEncoder, bounds);
    return height;
  }

  private float encodeMovementAndHealth(SheetGraphics graphics, ReportContent content, float distanceFromTop,
    float height) throws DocumentException {
    Bounds bounds = pageConfiguration.getFirstColumnRectangle(distanceFromTop, height, 2);
    IBoxContentEncoder encoder = new SecondEditionLunarHealthAndMovementEncoder(resources);
    boxEncoder.encodeBox(content, graphics, encoder, bounds);
    return height;
  }

  private float encodeCombatStats(SheetGraphics graphics, ReportContent content, float distanceFromTop) throws DocumentException {
    int height = 64;
    Bounds bounds = pageConfiguration.getThirdColumnRectangle(distanceFromTop, height);
    SecondEditionDBTCombatEncoder encoder = new SecondEditionDBTCombatEncoder(resources);
    boxEncoder.encodeBox(content, graphics, encoder, bounds);
    return height;
  }

  private void encodePowers(SheetGraphics graphics, ReportContent content, float distanceFromTop, float height, boolean isHorizontal)
    throws DocumentException {
    Bounds bounds = isHorizontal ? pageConfiguration.getSecondColumnRectangle(distanceFromTop, height, 2) :
                    pageConfiguration.getThirdColumnRectangle(distanceFromTop, height);
    SecondEditionPowersEncoder encoder = new SecondEditionPowersEncoder(resources, isHorizontal);
    boxEncoder.encodeBox(content, graphics, encoder, bounds);
  }

  private void encodeKnacks(SheetGraphics graphics, ReportContent content, float distanceFromTop, int height) throws DocumentException {
    Bounds bounds = pageConfiguration.getFirstColumnRectangle(distanceFromTop, height, 1);
    IBoxContentEncoder encoder = new KnackEncoder();
    boxEncoder.encodeBox(content, graphics, encoder, bounds);
  }

  private void encodeAnimalForms(SheetGraphics graphics, ReportContent content, float distanceFromTop, float height) throws DocumentException {
    Bounds bounds = pageConfiguration.getSecondColumnRectangle(distanceFromTop, height, 2);
    IBoxContentEncoder encoder = new SecondEditionLunarHeartsBloodEncoder(resources);
    boxEncoder.encodeBox(content, graphics, encoder, bounds);
  }
}

package net.sf.anathema.character.lunar.reporting.layout;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.BaseFont;
import net.sf.anathema.character.equipment.impl.reporting.WeaponryTableEncoder;
import net.sf.anathema.character.equipment.impl.reporting.ArmourEncoder;
import net.sf.anathema.character.equipment.impl.reporting.WeaponryEncoder;
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
import net.sf.anathema.character.reporting.pdf.layout.simple.ISimplePartEncoder;
import net.sf.anathema.character.reporting.pdf.layout.simple.SimpleEncodingRegistry;
import net.sf.anathema.character.reporting.pdf.rendering.Bounds;
import net.sf.anathema.character.reporting.pdf.rendering.graphics.SheetGraphics;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.IBoxContentEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.PdfBoxEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.page.IPdfPageEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.page.PdfPageConfiguration;
import net.sf.anathema.lib.resources.IResources;

import static net.sf.anathema.character.reporting.pdf.rendering.page.IVoidStateFormatConstants.PADDING;

public class Simple2ndEditionLunarAdditionalPageEncoder implements IPdfPageEncoder {
  public static final int CONTENT_HEIGHT = 755;
  private final IResources resources;
  private final BaseFont baseFont;
  private final BaseFont symbolFont;

  private final PdfPageConfiguration pageConfiguration;
  private final PdfBoxEncoder boxEncoder;
  private final ISimplePartEncoder partEncoder;

  public Simple2ndEditionLunarAdditionalPageEncoder(ISimplePartEncoder partEncoder, SimpleEncodingRegistry registry, IResources resources,
    int essenceMax, PdfPageConfiguration pageConfiguration) {
    this.partEncoder = partEncoder;
    this.baseFont = registry.getBaseFont();
    this.symbolFont = registry.getSymbolBaseFont();
    this.resources = resources;
    this.pageConfiguration = pageConfiguration;
    this.boxEncoder = new PdfBoxEncoder(resources, baseFont);
  }

  public void encode(Document document, SheetGraphics graphics, ReportContent content) throws
    DocumentException {
    int firstSet = 0, secondSet = 0;
    boolean DBT = hasDBT(content.getCharacter());
    firstSet += encodeSpiritForms(graphics, content, firstSet, DBT);
    if (DBT) {
      firstSet += PADDING;
      secondSet = firstSet;
      firstSet += encodeArsenel(graphics, content, firstSet, partEncoder.getWeaponryHeight()) + PADDING;
      firstSet += encodePanopoly(graphics, content, firstSet, 80) + PADDING;
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
      if (magic instanceof ICharm && ((ICharm) magic).getId().equals("Lunar.DeadlyBeastmanTransformation")) {
        return true;
      }
    }
    return false;
  }

  private int encodeSpiritForms(SheetGraphics graphics, ReportContent content, int distanceFromTop,
    boolean DBT) throws DocumentException {
    int attributeHeight = 80;
    float smallWidth = pageConfiguration.getColumnWidth();
    Bounds spiritBounds = pageConfiguration.getFirstColumnRectangle(distanceFromTop, attributeHeight, 1);
    Bounds beastBounds = pageConfiguration.getSecondColumnRectangle(distanceFromTop, attributeHeight, 2);
    SecondEditionLunarSpiritFormEncoder spiritEncoder = new SecondEditionLunarSpiritFormEncoder(baseFont, resources,
      boxEncoder.calculateInsettedWidth(smallWidth));
    SecondEditionLunarDBTFormEncoder beastEncoder = new SecondEditionLunarDBTFormEncoder(baseFont, resources,
      boxEncoder.calculateInsettedWidth(smallWidth));
    boxEncoder.encodeBox(content, graphics, spiritEncoder, spiritBounds);
    if (DBT) {
      boxEncoder.encodeBox(content, graphics, beastEncoder,beastBounds);
    }
    return attributeHeight;
  }

  private float encodeArsenel(SheetGraphics graphics, ReportContent content, float distanceFromTop,
    float height) throws DocumentException {
    Bounds bounds = pageConfiguration.getFirstColumnRectangle(distanceFromTop, height, 2);
    WeaponryTableEncoder weaponTableEncoder = LunarEquipmentEncoders.CreateWeaponryEncoder(baseFont);
    IBoxContentEncoder weaponryEncoder = new WeaponryEncoder(resources, baseFont, weaponTableEncoder);
    boxEncoder.encodeBox(content, graphics, weaponryEncoder, bounds);
    return height;
  }

  private float encodePanopoly(SheetGraphics graphics, ReportContent content, float distanceFromTop,
    float height) throws DocumentException {
    Bounds bounds = pageConfiguration.getFirstColumnRectangle(distanceFromTop, height, 2);
    IBoxContentEncoder contentEncoder = new ArmourEncoder(resources, baseFont, LunarEquipmentEncoders.CreateArmourEncoder(baseFont));
    boxEncoder.encodeBox(content, graphics, contentEncoder, bounds);
    return height;
  }

  private float encodeMovementAndHealth(SheetGraphics graphics, ReportContent content,
    float distanceFromTop, float height) throws DocumentException {
    Bounds bounds = pageConfiguration.getFirstColumnRectangle(distanceFromTop, height, 2);
    IBoxContentEncoder encoder = new SecondEditionLunarHealthAndMovementEncoder(resources, baseFont, symbolFont, content.getCharacter());
    boxEncoder.encodeBox(content, graphics, encoder, bounds);
    return height;
  }

  private float encodeCombatStats(SheetGraphics graphics, ReportContent content,
    float distanceFromTop) throws DocumentException {
    int height = 64;
    Bounds bounds = pageConfiguration.getThirdColumnRectangle(distanceFromTop, height);
    SecondEditionDBTCombatEncoder encoder = new SecondEditionDBTCombatEncoder(resources, baseFont);
    boxEncoder.encodeBox(content, graphics, encoder, bounds);
    return height;
  }

  private void encodePowers(SheetGraphics graphics, ReportContent content, float distanceFromTop,
    float height, boolean isHorizontal) throws DocumentException {
    Bounds bounds = isHorizontal ? pageConfiguration.getSecondColumnRectangle(distanceFromTop, height,
      2) : pageConfiguration.getThirdColumnRectangle(distanceFromTop, height);
    SecondEditionPowersEncoder encoder = new SecondEditionPowersEncoder(resources, baseFont, isHorizontal);
    boxEncoder.encodeBox(content, graphics, encoder, bounds);
  }

  private void encodeKnacks(SheetGraphics graphics, ReportContent content, float distanceFromTop,
    int height) throws DocumentException {
    Bounds bounds = pageConfiguration.getFirstColumnRectangle(distanceFromTop, height, 1);
    IBoxContentEncoder encoder = new KnackEncoder(baseFont);
    boxEncoder.encodeBox(content, graphics, encoder, bounds);
  }

  private void encodeAnimalForms(SheetGraphics graphics, ReportContent content, float distanceFromTop,
    float height) throws DocumentException {
    Bounds bounds = pageConfiguration.getSecondColumnRectangle(distanceFromTop, height, 2);
    IBoxContentEncoder encoder = new SecondEditionLunarHeartsBloodEncoder(baseFont, resources);
    boxEncoder.encodeBox(content, graphics, encoder, bounds);
  }
}

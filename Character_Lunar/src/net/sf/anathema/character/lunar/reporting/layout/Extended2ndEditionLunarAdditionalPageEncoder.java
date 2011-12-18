package net.sf.anathema.character.lunar.reporting.layout;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfContentByte;
import net.sf.anathema.character.equipment.impl.reporting.ArmourEncoder;
import net.sf.anathema.character.equipment.impl.reporting.WeaponryEncoder;
import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.IMagic;
import net.sf.anathema.character.lunar.reporting.rendering.SecondEditionLunarSpiritFormEncoder;
import net.sf.anathema.character.lunar.reporting.rendering.SecondEditionPowersEncoder;
import net.sf.anathema.character.lunar.reporting.rendering.beastform.SecondEditionDBTCombatEncoder;
import net.sf.anathema.character.lunar.reporting.rendering.beastform.SecondEditionLunarDBTFormEncoder;
import net.sf.anathema.character.lunar.reporting.rendering.equipment.LunarArmourTableEncoder;
import net.sf.anathema.character.lunar.reporting.rendering.equipment.LunarWeaponTableEncoder;
import net.sf.anathema.character.lunar.reporting.rendering.health.SecondEditionLunarHealthAndMovementEncoder;
import net.sf.anathema.character.lunar.reporting.rendering.heartsblood.SecondEditionLunarHeartsBloodEncoder;
import net.sf.anathema.character.lunar.reporting.rendering.knacks.KnackEncoder;
import net.sf.anathema.character.reporting.pdf.content.ReportContent;
import net.sf.anathema.character.reporting.pdf.layout.extended.ExtendedEncodingRegistry;
import net.sf.anathema.character.reporting.pdf.layout.extended.IExtendedPartEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.elements.Bounds;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.IBoxContentEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.PdfBoxEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.general.table.ITableEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.page.IPdfPageEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.page.PdfPageConfiguration;
import net.sf.anathema.lib.resources.IResources;

import static net.sf.anathema.character.reporting.pdf.rendering.page.IVoidStateFormatConstants.PADDING;

public class Extended2ndEditionLunarAdditionalPageEncoder implements IPdfPageEncoder {
  public static final int CONTENT_HEIGHT = 755;
  private final IResources resources;
  private final BaseFont baseFont;
  private final BaseFont symbolFont;

  private final PdfPageConfiguration pageConfiguration;
  private final PdfBoxEncoder boxEncoder;
  private final IExtendedPartEncoder partEncoder;

  public Extended2ndEditionLunarAdditionalPageEncoder(IExtendedPartEncoder partEncoder, ExtendedEncodingRegistry registry, IResources resources,
    int essenceMax, PdfPageConfiguration pageConfiguration) {
    this.partEncoder = partEncoder;
    this.baseFont = registry.getBaseFont();
    this.symbolFont = registry.getSymbolBaseFont();
    this.resources = resources;
    this.pageConfiguration = pageConfiguration;
    this.boxEncoder = new PdfBoxEncoder(resources, baseFont);
  }

  public void encode(Document document, PdfContentByte directContent, ReportContent content) throws
    DocumentException {
    int firstSet = 0, secondSet = 0;
    boolean DBT = hasDBT(content.getCharacter());
    firstSet += encodeSpiritForms(directContent, content, firstSet, DBT);
    if (DBT) {
      firstSet += PADDING;
      secondSet = firstSet;
      firstSet += encodeArsenel(directContent, content, firstSet, partEncoder.getWeaponryHeight()) + PADDING;
      firstSet += encodePanopoly(directContent, content, firstSet, 80) + PADDING;
      firstSet += encodeMovementAndHealth(directContent, content, firstSet, 99);

      secondSet += encodeCombatStats(directContent, content, secondSet) + PADDING;
      encodePowers(directContent, content, secondSet, firstSet - secondSet, false);
    }
    else {
      encodePowers(directContent, content, secondSet, firstSet, true);
    }

    firstSet += PADDING;

    int remaining = (int) (pageConfiguration.getContentHeight() - firstSet);
    encodeKnacks(directContent, content, firstSet, remaining);
    encodeAnimalForms(directContent, content, firstSet, remaining);
  }

  private boolean hasDBT(IGenericCharacter character) {
    for (IMagic magic : character.getAllLearnedMagic()) {
      if (magic instanceof ICharm && magic.getId().equals("Lunar.DeadlyBeastmanTransformation")) {
        return true;
      }
    }
    return false;
  }

  private int encodeSpiritForms(PdfContentByte directContent, ReportContent content, int distanceFromTop, boolean DBT) throws DocumentException {
    int attributeHeight = 80;
    float smallWidth = pageConfiguration.getColumnWidth();
    Bounds spiritBounds = pageConfiguration.getFirstColumnRectangle(distanceFromTop, attributeHeight, 1);
    Bounds beastBounds = pageConfiguration.getSecondColumnRectangle(distanceFromTop, attributeHeight, 2);
    SecondEditionLunarSpiritFormEncoder spiritEncoder = new SecondEditionLunarSpiritFormEncoder(baseFont, resources,
      boxEncoder.calculateInsettedWidth(smallWidth));
    SecondEditionLunarDBTFormEncoder beastEncoder = new SecondEditionLunarDBTFormEncoder(baseFont, resources,
      boxEncoder.calculateInsettedWidth(smallWidth));
    boxEncoder.encodeBox(content, directContent, spiritEncoder, spiritBounds);
    if (DBT) {
      boxEncoder.encodeBox(content, directContent, beastEncoder, beastBounds);
    }
    return attributeHeight;
  }

  private float encodeArsenel(PdfContentByte directContent, ReportContent content, float distanceFromTop,
    float height) throws DocumentException {
    Bounds bounds = pageConfiguration.getFirstColumnRectangle(distanceFromTop, height, 2);
    IBoxContentEncoder weaponryEncoder = new WeaponryEncoder(resources, baseFont, new LunarWeaponTableEncoder(baseFont, resources, content.getCharacter()));
    boxEncoder.encodeBox(content, directContent, weaponryEncoder, bounds);
    return height;
  }

  private float encodePanopoly(PdfContentByte directContent, ReportContent content, float distanceFromTop,
    float height) throws DocumentException {
    Bounds bounds = pageConfiguration.getFirstColumnRectangle(distanceFromTop, height, 2);
    ITableEncoder armourTableEncoder = new LunarArmourTableEncoder(baseFont, resources);
    IBoxContentEncoder contentEncoder = new ArmourEncoder(resources, baseFont, armourTableEncoder);
    boxEncoder.encodeBox(content, directContent, contentEncoder, bounds);
    return height;
  }

  private float encodeMovementAndHealth(PdfContentByte directContent, ReportContent content,
    float distanceFromTop, float height) throws DocumentException {
    Bounds bounds = pageConfiguration.getFirstColumnRectangle(distanceFromTop, height, 2);
    IBoxContentEncoder encoder = new SecondEditionLunarHealthAndMovementEncoder(resources, baseFont, symbolFont, content.getCharacter());
    boxEncoder.encodeBox(content, directContent, encoder, bounds);
    return height;
  }

  private float encodeCombatStats(PdfContentByte directContent, ReportContent content,
    float distanceFromTop) throws DocumentException {
    int height = 64;
    Bounds bounds = pageConfiguration.getThirdColumnRectangle(distanceFromTop, height);
    SecondEditionDBTCombatEncoder encoder = new SecondEditionDBTCombatEncoder(resources, baseFont);
    boxEncoder.encodeBox(content, directContent, encoder, bounds);
    return height;
  }

  private void encodePowers(PdfContentByte directContent, ReportContent content, float distanceFromTop,
    float height, boolean isHorizontal) throws DocumentException {
    Bounds bounds = isHorizontal ? pageConfiguration.getSecondColumnRectangle(distanceFromTop, height,
      2) : pageConfiguration.getThirdColumnRectangle(distanceFromTop, height);
    SecondEditionPowersEncoder encoder = new SecondEditionPowersEncoder(resources, baseFont, isHorizontal);
    boxEncoder.encodeBox(content, directContent, encoder, bounds);
  }

  private void encodeKnacks(PdfContentByte directContent, ReportContent content, float distanceFromTop,
    int height) throws DocumentException {
    Bounds bounds = pageConfiguration.getFirstColumnRectangle(distanceFromTop, height, 1);
    IBoxContentEncoder encoder = new KnackEncoder(resources, baseFont);
    boxEncoder.encodeBox(content, directContent, encoder, bounds);
  }

  private void encodeAnimalForms(PdfContentByte directContent, ReportContent content, float distanceFromTop,
    float height) throws DocumentException {
    Bounds bounds = pageConfiguration.getSecondColumnRectangle(distanceFromTop, height, 2);
    IBoxContentEncoder encoder = new SecondEditionLunarHeartsBloodEncoder(baseFont, resources);
    boxEncoder.encodeBox(content, directContent, encoder, bounds);
  }
}

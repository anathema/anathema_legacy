package net.sf.anathema.character.lunar.reporting;

import static net.sf.anathema.character.reporting.sheet.pageformat.IVoidStateFormatConstants.PADDING;
import net.disy.commons.core.util.StringUtilities;
import net.sf.anathema.character.equipment.impl.reporting.ArmourEncoder;
import net.sf.anathema.character.equipment.impl.reporting.WeaponryEncoder;
import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.character.IGenericDescription;
import net.sf.anathema.character.lunar.reporting.heartsblood.FirstEditionLunarHeartsBloodEncoder;
import net.sf.anathema.character.reporting.sheet.PdfEncodingRegistry;
import net.sf.anathema.character.reporting.sheet.common.IPdfContentBoxEncoder;
import net.sf.anathema.character.reporting.sheet.common.PdfAbilitiesEncoder;
import net.sf.anathema.character.reporting.sheet.common.PdfVirtueEncoder;
import net.sf.anathema.character.reporting.sheet.common.PdfWillpowerEncoder;
import net.sf.anathema.character.reporting.sheet.page.IPdfPageEncoder;
import net.sf.anathema.character.reporting.sheet.page.IPdfPartEncoder;
import net.sf.anathema.character.reporting.sheet.pageformat.IVoidStateFormatConstants;
import net.sf.anathema.character.reporting.sheet.pageformat.PdfPageConfiguration;
import net.sf.anathema.character.reporting.sheet.second.SecondEditionPersonalInfoEncoder;
import net.sf.anathema.character.reporting.sheet.util.PdfBoxEncoder;
import net.sf.anathema.character.reporting.sheet.util.PdfHeaderBoxEncoder;
import net.sf.anathema.character.reporting.util.Bounds;
import net.sf.anathema.lib.resources.IResources;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfContentByte;

public class FirstEditionLunarBeastformPageEncoder implements IPdfPageEncoder {
  public static final int CONTENT_HEIGHT = 755;
  private final IResources resources;
  private final int essenceMax;
  private final BaseFont baseFont;
  private final BaseFont symbolFont;

  private static final int ANIMA_HEIGHT = 128;
  private static final int VIRTUE_HEIGHT = 72;
  private final PdfPageConfiguration pageConfiguration;
  private final PdfBoxEncoder boxEncoder;
  private final IPdfPartEncoder partEncoder;

  public FirstEditionLunarBeastformPageEncoder(
      IPdfPartEncoder partEncoder,
      PdfEncodingRegistry registry,
      IResources resources,
      int essenceMax,
      PdfPageConfiguration pageConfiguration) {
    this.partEncoder = partEncoder;
    this.baseFont = registry.getBaseFont();
    this.essenceMax = essenceMax;
    this.resources = resources;
    this.pageConfiguration = pageConfiguration;
    this.boxEncoder = new PdfBoxEncoder(resources, baseFont);
    this.symbolFont = registry.getSymbolBaseFont();
  }

  public void encode(
      Document document,
      PdfContentByte directContent,
      IGenericCharacter character,
      IGenericDescription description) throws DocumentException {
    int distanceFromTop = 0;
    final int firstRowHeight = 51;
    encodePersonalInfo(directContent, character, description, distanceFromTop, firstRowHeight);
    encodeEssence(directContent, character, distanceFromTop, firstRowHeight);

    distanceFromTop += firstRowHeight + PADDING;

    float abilityStartHeight = encodeAttributes(directContent, character, distanceFromTop) + distanceFromTop + PADDING;
    encodeAnima(directContent, character, distanceFromTop, ANIMA_HEIGHT);
    float freeSpace = getOverlapFreeSpaceHeight();
    distanceFromTop += freeSpace;
    distanceFromTop += PADDING;
    encodeVirtues(directContent, distanceFromTop, VIRTUE_HEIGHT, character);
    distanceFromTop += calculateBoxIncrement(VIRTUE_HEIGHT);

    float animalFormHeight = encodeAnimalForms(directContent, character, distanceFromTop, 115);
    float willpowerHeight = encodeWillpower(directContent, character, distanceFromTop, 43);
    float willpowerIncrement = calculateBoxIncrement(willpowerHeight);
    distanceFromTop += willpowerIncrement;
    float faceHeight = encodeFace(directContent, character, distanceFromTop, animalFormHeight - willpowerIncrement);
    distanceFromTop += calculateBoxIncrement(faceHeight);
    float weaponryHeight = encodeWeaponry(directContent, character, distanceFromTop, partEncoder.getWeaponryHeight());
    distanceFromTop += calculateBoxIncrement(weaponryHeight);
    float armourHeight = encodeArmourAndSoak(directContent, character, distanceFromTop, 80);
    distanceFromTop += calculateBoxIncrement(armourHeight);
    float healthHeight = encodeMovementAndHealth(directContent, character, distanceFromTop, 99);
    distanceFromTop += calculateBoxIncrement(healthHeight);
    float remainingHeight = FirstEditionLunarBeastformPageEncoder.CONTENT_HEIGHT - distanceFromTop;
    encodeCombatStats(directContent, character, distanceFromTop, remainingHeight);
    encodeAbilities(directContent, character, abilityStartHeight, remainingHeight + PADDING);
    encodeGifts(directContent, character, distanceFromTop, remainingHeight);
  }

  private void encodeGifts(
      PdfContentByte directContent,
      IGenericCharacter character,
      int distanceFromTop,
      float remainingHeight) throws DocumentException {
    Bounds bounds = pageConfiguration.getFirstColumnRectangle(distanceFromTop, remainingHeight, 1);
    IPdfContentBoxEncoder encoder = new GiftEncoder(baseFont, resources);
    boxEncoder.encodeBox(directContent, encoder, character, bounds);
  }

  private float getOverlapFreeSpaceHeight() {
    return ANIMA_HEIGHT - VIRTUE_HEIGHT - PADDING;
  }

  private float encodeEssence(
      PdfContentByte directContent,
      IGenericCharacter character,
      float distanceFromTop,
      float height) throws DocumentException {
    Bounds essenceBounds = pageConfiguration.getThirdColumnRectangle(distanceFromTop, height);
    IPdfContentBoxEncoder encoder = partEncoder.getEssenceEncoder();
    boxEncoder.encodeBox(directContent, encoder, character, essenceBounds);
    return height;
  }

  private String getHeaderLabel(String key) {
    return resources.getString("Sheet.Header." + key); //$NON-NLS-1$
  }

  private void encodePersonalInfo(
      PdfContentByte directContent,
      IGenericCharacter character,
      IGenericDescription description,
      int distanceFromTop,
      final int firstRowHeight) {
    Bounds infoBounds = pageConfiguration.getFirstColumnRectangle(distanceFromTop, firstRowHeight, 2);
    String name = description.getName();
    String title = StringUtilities.isNullOrTrimEmpty(name) ? getHeaderLabel("PersonalInfo") : name; //$NON-NLS-1$
    Bounds infoContentBounds = boxEncoder.encodeBox(directContent, infoBounds, title);
    encodePersonalInfos(directContent, character, description, infoContentBounds);
  }

  private void encodeAbilities(
      PdfContentByte directContent,
      IGenericCharacter character,
      float distanceFromTop,
      float remainingHeightRequired) throws DocumentException {
    float abilitiesHeight = CONTENT_HEIGHT - distanceFromTop - remainingHeightRequired;
    Bounds boxBounds = pageConfiguration.getFirstColumnRectangle(distanceFromTop, abilitiesHeight, 1);
    IPdfContentBoxEncoder encoder = PdfAbilitiesEncoder.createWithSpecialtiesOnly(baseFont, resources, essenceMax, 11);
    boxEncoder.encodeBox(directContent, encoder, character, boxBounds);
  }

  private int encodeAttributes(PdfContentByte directContent, IGenericCharacter character, int distanceFromTop)
      throws DocumentException {
    int attributeHeight = 128;
    Bounds attributeBounds = pageConfiguration.getFirstColumnRectangle(distanceFromTop, attributeHeight, 2);
    float smallWidth = pageConfiguration.getColumnWidth();
    BeastformAttributeBoxEncoder beastBoxEncoder = new BeastformAttributeBoxEncoder(
        baseFont,
        smallWidth,
        getOverlapFreeSpaceHeight());
    FirstEditionLunarBeastformAttributesEncoder encoder = new FirstEditionLunarBeastformAttributesEncoder(
        baseFont,
        resources,
        boxEncoder.calculateInsettedWidth(smallWidth));
    new PdfHeaderBoxEncoder(baseFont).encodeHeaderBox(
        directContent,
        attributeBounds,
        resources.getString("Sheet.Header." + encoder.getHeaderKey())); //$NON-NLS-1$
    boxEncoder.encodeBox(directContent, encoder, beastBoxEncoder, character, attributeBounds);
    return attributeHeight;
  }

  private float calculateBoxIncrement(float height) {
    return height + IVoidStateFormatConstants.PADDING;
  }

  private void encodeAnima(
      PdfContentByte directContent,
      IGenericCharacter character,
      float distanceFromTop,
      float height) throws DocumentException {
    Bounds animaBounds = pageConfiguration.getThirdColumnRectangle(distanceFromTop, height);
    IPdfContentBoxEncoder encoder = partEncoder.getAnimaEncoder();
    boxEncoder.encodeBox(directContent, encoder, character, animaBounds);
  }

  private float encodeArmourAndSoak(
      PdfContentByte directContent,
      IGenericCharacter character,
      float distanceFromTop,
      float height) throws DocumentException {
    Bounds bounds = pageConfiguration.getSecondColumnRectangle(distanceFromTop, height, 2);
    IPdfContentBoxEncoder contentEncoder = new ArmourEncoder(resources, baseFont, new LunarArmourTableEncoder(
        baseFont,
        resources));
    boxEncoder.encodeBox(directContent, contentEncoder, character, bounds);
    return height;
  }

  private float encodeAnimalForms(
      PdfContentByte directContent,
      IGenericCharacter character,
      float distanceFromTop,
      float height) throws DocumentException {
    Bounds bounds = pageConfiguration.getThirdColumnRectangle(distanceFromTop, height);
    IPdfContentBoxEncoder encoder = new FirstEditionLunarHeartsBloodEncoder(baseFont, resources);
    boxEncoder.encodeBox(directContent, encoder, character, bounds);
    return height;
  }

  private float encodeCombatStats(
      PdfContentByte directContent,
      IGenericCharacter character,
      float distanceFromTop,
      float height) throws DocumentException {
    Bounds bounds = pageConfiguration.getSecondColumnRectangle(distanceFromTop, height, 2);
    FirstEditionDBTCombatEncoder encoder = new FirstEditionDBTCombatEncoder(resources, baseFont);
    boxEncoder.encodeBox(directContent, encoder, character, bounds);
    return height;
  }

  private float encodeMovementAndHealth(
      PdfContentByte directContent,
      IGenericCharacter character,
      float distanceFromTop,
      float height) throws DocumentException {
    Bounds bounds = pageConfiguration.getSecondColumnRectangle(distanceFromTop, height, 2);
    IPdfContentBoxEncoder encoder = new FirstEditionLunarHealthAndMovementEncoder(
    		resources, baseFont, symbolFont, character);
    boxEncoder.encodeBox(directContent, encoder, character, bounds);
    return height;
  }

  private void encodePersonalInfos(
      PdfContentByte directContent,
      IGenericCharacter character,
      IGenericDescription description,
      Bounds infoBounds) {
    SecondEditionPersonalInfoEncoder encoder = new SecondEditionPersonalInfoEncoder(baseFont, resources);
    encoder.encodePersonalInfos(directContent, character, description, infoBounds);
  }

  private float encodeVirtues(
      PdfContentByte directContent,
      float distanceFromTop,
      float height,
      IGenericCharacter character) throws DocumentException {
    Bounds bounds = pageConfiguration.getSecondColumnRectangle(distanceFromTop, height, 1);
    IPdfContentBoxEncoder encoder = new PdfVirtueEncoder(resources, baseFont);
    boxEncoder.encodeBox(directContent, encoder, character, bounds);
    return height;
  }

  private float encodeWeaponry(
      PdfContentByte directContent,
      IGenericCharacter character,
      float distanceFromTop,
      float height) throws DocumentException {
    Bounds bounds = pageConfiguration.getSecondColumnRectangle(distanceFromTop, height, 2);
    IPdfContentBoxEncoder weaponryEncoder = new WeaponryEncoder(resources, baseFont,
    		new LunarWeaponTableEncoder(baseFont, resources, character));
    boxEncoder.encodeBox(directContent, weaponryEncoder, character, bounds);
    return height;
  }

  private float encodeWillpower(
      PdfContentByte directContent,
      IGenericCharacter character,
      float distanceFromTop,
      float height) throws DocumentException {
    Bounds willpowerBounds = pageConfiguration.getSecondColumnRectangle(distanceFromTop, height, 1);
    boxEncoder.encodeBox(directContent, new PdfWillpowerEncoder(baseFont), character, willpowerBounds);
    return height;
  }

  private float encodeFace(
      PdfContentByte directContent,
      IGenericCharacter character,
      float distanceFromTop,
      float height) throws DocumentException {
    Bounds bounds = pageConfiguration.getSecondColumnRectangle(distanceFromTop, height, 1);
    IPdfContentBoxEncoder encoder = new LunarFaceEncoder(baseFont, resources);
    boxEncoder.encodeBox(directContent, encoder, character, bounds);
    return height;
  }
}
package net.sf.anathema.character.lunar.reporting.extended;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfContentByte;
import net.disy.commons.core.util.StringUtilities;
import net.sf.anathema.character.equipment.impl.reporting.extended.ArmourEncoder;
import net.sf.anathema.character.equipment.impl.reporting.extended.WeaponryEncoder;
import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.character.IGenericDescription;
import net.sf.anathema.character.reporting.common.encoder.IPdfContentBoxEncoder;
import net.sf.anathema.character.reporting.common.encoder.IPdfPageEncoder;
import net.sf.anathema.character.reporting.extended.ExtendedEncodingRegistry;
import net.sf.anathema.character.reporting.extended.common.PdfAbilitiesEncoder;
import net.sf.anathema.character.reporting.extended.common.PdfVirtueEncoder;
import net.sf.anathema.character.reporting.extended.common.PdfWillpowerEncoder;
import net.sf.anathema.character.reporting.extended.page.IExtendedPartEncoder;
import net.sf.anathema.character.reporting.common.pageformat.IVoidStateFormatConstants;
import net.sf.anathema.character.reporting.common.pageformat.PdfPageConfiguration;
import net.sf.anathema.character.reporting.extended.second.SecondEditionPersonalInfoEncoder;
import net.sf.anathema.character.reporting.common.encoder.IPdfTableEncoder;
import net.sf.anathema.character.reporting.extended.util.PdfBoxEncoder;
import net.sf.anathema.character.reporting.extended.util.PdfHeaderBoxEncoder;
import net.sf.anathema.character.reporting.common.Bounds;
import net.sf.anathema.lib.resources.IResources;

import static net.sf.anathema.character.reporting.common.pageformat.IVoidStateFormatConstants.PADDING;

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
  private final IExtendedPartEncoder partEncoder;

  public FirstEditionLunarBeastformPageEncoder(
      IExtendedPartEncoder partEncoder,
      ExtendedEncodingRegistry registry,
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
    encodeEssence(directContent, character, description, distanceFromTop, firstRowHeight);

    distanceFromTop += firstRowHeight + PADDING;

    float abilityStartHeight = encodeAttributes(directContent, character, description, distanceFromTop) + distanceFromTop + PADDING;
    encodeAnima(directContent, character, description, distanceFromTop, ANIMA_HEIGHT);
    float freeSpace = getOverlapFreeSpaceHeight();
    distanceFromTop += freeSpace;
    distanceFromTop += PADDING;
    encodeVirtues(directContent, distanceFromTop, VIRTUE_HEIGHT, character, description);
    distanceFromTop += calculateBoxIncrement(VIRTUE_HEIGHT);

    float animalFormHeight = encodeAnimalForms(directContent, character, description, distanceFromTop, 115);
    float willpowerHeight = encodeWillpower(directContent, character, description, distanceFromTop, 43);
    float willpowerIncrement = calculateBoxIncrement(willpowerHeight);
    distanceFromTop += willpowerIncrement;
    float faceHeight = encodeFace(directContent, character, description, distanceFromTop, animalFormHeight - willpowerIncrement);
    distanceFromTop += calculateBoxIncrement(faceHeight);
    float weaponryHeight = encodeWeaponry(directContent, character, description, distanceFromTop, partEncoder.getWeaponryHeight());
    distanceFromTop += calculateBoxIncrement(weaponryHeight);
    float armourHeight = encodeArmourAndSoak(directContent, character, description, distanceFromTop, 80);
    distanceFromTop += calculateBoxIncrement(armourHeight);
    float healthHeight = encodeMovementAndHealth(directContent, character, description, distanceFromTop, 99);
    distanceFromTop += calculateBoxIncrement(healthHeight);
    float remainingHeight = FirstEditionLunarBeastformPageEncoder.CONTENT_HEIGHT - distanceFromTop;
    encodeCombatStats(directContent, character, description, distanceFromTop, remainingHeight);
    encodeAbilities(directContent, character, description, abilityStartHeight, remainingHeight + PADDING);
    encodeGifts(directContent, character, description, distanceFromTop, remainingHeight);
  }

  private void encodeGifts(
      PdfContentByte directContent,
      IGenericCharacter character,
      IGenericDescription description,
      int distanceFromTop,
      float remainingHeight) throws DocumentException {
    Bounds bounds = pageConfiguration.getFirstColumnRectangle(distanceFromTop, remainingHeight, 1);
    IPdfContentBoxEncoder encoder = new GiftEncoder(baseFont, resources);
    boxEncoder.encodeBox(directContent, encoder, character, description, bounds);
  }

  private float getOverlapFreeSpaceHeight() {
    return ANIMA_HEIGHT - VIRTUE_HEIGHT - PADDING;
  }

  private float encodeEssence(
      PdfContentByte directContent,
      IGenericCharacter character,
      IGenericDescription description,
      float distanceFromTop,
      float height) throws DocumentException {
    Bounds essenceBounds = pageConfiguration.getThirdColumnRectangle(distanceFromTop, height);
    IPdfContentBoxEncoder encoder = partEncoder.getEssenceEncoder();
    boxEncoder.encodeBox(directContent, encoder, character, description, essenceBounds);
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
      IGenericDescription description,
      float distanceFromTop,
      float remainingHeightRequired) throws DocumentException {
    float abilitiesHeight = CONTENT_HEIGHT - distanceFromTop - remainingHeightRequired;
    Bounds boxBounds = pageConfiguration.getFirstColumnRectangle(distanceFromTop, abilitiesHeight, 1);
    IPdfContentBoxEncoder encoder = PdfAbilitiesEncoder.createWithSpecialtiesOnly(baseFont, resources, essenceMax, 11);
    boxEncoder.encodeBox(directContent, encoder, character, description, boxBounds);
  }

  private int encodeAttributes(PdfContentByte directContent,
                               IGenericCharacter character, IGenericDescription description,
                               int distanceFromTop)
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
        resources.getString("Sheet.Header." + encoder.getHeaderKey(character, description))); //$NON-NLS-1$
    boxEncoder.encodeBox(directContent, encoder, beastBoxEncoder, character, description, attributeBounds);
    return attributeHeight;
  }

  private float calculateBoxIncrement(float height) {
    return height + IVoidStateFormatConstants.PADDING;
  }

  private void encodeAnima(
      PdfContentByte directContent,
      IGenericCharacter character,
      IGenericDescription description,
      float distanceFromTop,
      float height) throws DocumentException {
    Bounds animaBounds = pageConfiguration.getThirdColumnRectangle(distanceFromTop, height);
    IPdfContentBoxEncoder encoder = partEncoder.getAnimaEncoder();
    boxEncoder.encodeBox(directContent, encoder, character, description, animaBounds);
  }

  private float encodeArmourAndSoak(
      PdfContentByte directContent,
      IGenericCharacter character,
      IGenericDescription description,
      float distanceFromTop,
      float height) throws DocumentException {
    Bounds bounds = pageConfiguration.getSecondColumnRectangle(distanceFromTop, height, 2);
    IPdfTableEncoder armourEncoder = new LunarArmourTableEncoder(baseFont, resources);
    IPdfContentBoxEncoder contentEncoder = new ArmourEncoder(resources, baseFont, armourEncoder);
    boxEncoder.encodeBox(directContent, contentEncoder, character, description, bounds);
    return height;
  }

  private float encodeAnimalForms(
      PdfContentByte directContent,
      IGenericCharacter character,
      IGenericDescription description,
      float distanceFromTop,
      float height) throws DocumentException {
    Bounds bounds = pageConfiguration.getThirdColumnRectangle(distanceFromTop, height);
    IPdfContentBoxEncoder encoder = new FirstEditionLunarHeartsBloodEncoder(baseFont, resources);
    boxEncoder.encodeBox(directContent, encoder, character, description, bounds);
    return height;
  }

  private float encodeCombatStats(
      PdfContentByte directContent,
      IGenericCharacter character,
      IGenericDescription description,
      float distanceFromTop,
      float height) throws DocumentException {
    Bounds bounds = pageConfiguration.getSecondColumnRectangle(distanceFromTop, height, 2);
    FirstEditionDBTCombatEncoder encoder = new FirstEditionDBTCombatEncoder(resources, baseFont);
    boxEncoder.encodeBox(directContent, encoder, character, description, bounds);
    return height;
  }

  private float encodeMovementAndHealth(
      PdfContentByte directContent,
      IGenericCharacter character,
      IGenericDescription description,
      float distanceFromTop,
      float height) throws DocumentException {
    Bounds bounds = pageConfiguration.getSecondColumnRectangle(distanceFromTop, height, 2);
    IPdfContentBoxEncoder encoder = new FirstEditionLunarHealthAndMovementEncoder(
    		resources, baseFont, symbolFont, character);
    boxEncoder.encodeBox(directContent, encoder, character, description, bounds);
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
      IGenericCharacter character,
      IGenericDescription description) throws DocumentException {
    Bounds bounds = pageConfiguration.getSecondColumnRectangle(distanceFromTop, height, 1);
    IPdfContentBoxEncoder encoder = new PdfVirtueEncoder(resources, baseFont);
    boxEncoder.encodeBox(directContent, encoder, character, description, bounds);
    return height;
  }

  private float encodeWeaponry(
      PdfContentByte directContent,
      IGenericCharacter character,
      IGenericDescription description,
      float distanceFromTop,
      float height) throws DocumentException {
    Bounds bounds = pageConfiguration.getSecondColumnRectangle(distanceFromTop, height, 2);
    IPdfContentBoxEncoder weaponryEncoder = new WeaponryEncoder(resources, baseFont,
    		new LunarWeaponTableEncoder(baseFont, resources, character));
    boxEncoder.encodeBox(directContent, weaponryEncoder, character, description, bounds);
    return height;
  }

  private float encodeWillpower(
      PdfContentByte directContent,
      IGenericCharacter character,
      IGenericDescription description,
      float distanceFromTop,
      float height) throws DocumentException {
    Bounds willpowerBounds = pageConfiguration.getSecondColumnRectangle(distanceFromTop, height, 1);
    boxEncoder.encodeBox(directContent, new PdfWillpowerEncoder(baseFont), character, description, willpowerBounds);
    return height;
  }

  private float encodeFace(
      PdfContentByte directContent,
      IGenericCharacter character,
      IGenericDescription description,
      float distanceFromTop,
      float height) throws DocumentException {
    Bounds bounds = pageConfiguration.getSecondColumnRectangle(distanceFromTop, height, 1);
    IPdfContentBoxEncoder encoder = new LunarFaceEncoder(baseFont, resources);
    boxEncoder.encodeBox(directContent, encoder, character, description, bounds);
    return height;
  }
}

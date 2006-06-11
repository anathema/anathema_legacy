package net.sf.anathema.character.reporting.sheet.page;

import static net.sf.anathema.character.reporting.sheet.pageformat.IVoidStateFormatConstants.PADDING;
import net.disy.commons.core.util.StringUtilities;
import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.character.IGenericDescription;
import net.sf.anathema.character.generic.character.IGenericTraitCollection;
import net.sf.anathema.character.generic.template.abilities.IGroupedTraitType;
import net.sf.anathema.character.generic.type.CharacterType;
import net.sf.anathema.character.reporting.sheet.SecondEditionEncodingRegistry;
import net.sf.anathema.character.reporting.sheet.common.IPdfContentEncoder;
import net.sf.anathema.character.reporting.sheet.common.PdfAbilitiesEncoder;
import net.sf.anathema.character.reporting.sheet.common.PdfAttributesEncoder;
import net.sf.anathema.character.reporting.sheet.common.PdfVirtueEncoder;
import net.sf.anathema.character.reporting.sheet.common.PdfWillpowerEncoder;
import net.sf.anathema.character.reporting.sheet.pageformat.IVoidStateFormatConstants;
import net.sf.anathema.character.reporting.sheet.pageformat.PdfPageConfiguration;
import net.sf.anathema.character.reporting.sheet.second.SecondEditionCombatStatsEncoder;
import net.sf.anathema.character.reporting.sheet.second.SecondEditionHealthAndMovementEncoder;
import net.sf.anathema.character.reporting.sheet.second.SecondEditionPersonalInfoEncoder;
import net.sf.anathema.character.reporting.sheet.second.SecondEditionSocialCombatStatsEncoder;
import net.sf.anathema.character.reporting.sheet.util.PdfBoxEncoder;
import net.sf.anathema.character.reporting.sheet.util.PdfTextEncodingUtilities;
import net.sf.anathema.character.reporting.util.Bounds;
import net.sf.anathema.lib.resources.IResources;

import com.lowagie.text.Anchor;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfContentByte;

public class PdfFirstPageEncoder implements IPdfPageEncoder {
  public static final int CONTENT_HEIGHT = 755;
  private final IResources resources;
  private final int essenceMax;
  private final BaseFont baseFont;

  private static final int ANIMA_HEIGHT = 128;
  private final PdfPageConfiguration pageConfiguration;
  private final PdfBoxEncoder boxEncoder;
  private final SecondEditionEncodingRegistry registry;
  private final IPdfPartEncoder partEncoder;

  public PdfFirstPageEncoder(
      IPdfPartEncoder partEncoder,
      SecondEditionEncodingRegistry registry,
      IResources resources,
      int essenceMax,
      PdfPageConfiguration pageConfiguration) {
    this.partEncoder = partEncoder;
    this.baseFont = registry.getBaseFont();
    this.essenceMax = essenceMax;
    this.resources = resources;
    this.registry = registry;
    this.pageConfiguration = pageConfiguration;
    this.boxEncoder = new PdfBoxEncoder(resources, baseFont);
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

    encodeFirstColumn(directContent, character, distanceFromTop);
    encodeAnima(directContent, character, distanceFromTop, ANIMA_HEIGHT);
    float virtueHeight = encodeVirtues(directContent, character, distanceFromTop, 72);
    distanceFromTop += calculateBoxIncrement(virtueHeight);
    float greatCurseHeigth = ANIMA_HEIGHT - virtueHeight - IVoidStateFormatConstants.PADDING;
    encodeGreatCurse(directContent, character, distanceFromTop, greatCurseHeigth);
    distanceFromTop += calculateBoxIncrement(greatCurseHeigth);
    float socialCombatHeight = encodeSocialCombatStats(directContent, character, distanceFromTop, 115);
    float willpowerHeight = encodeWillpower(directContent, character, distanceFromTop, 43);
    float willpowerIncrement = calculateBoxIncrement(willpowerHeight);
    distanceFromTop += willpowerIncrement;
    float intimaciesHeight = encodeIntimacies(directContent, character, distanceFromTop, socialCombatHeight
        - willpowerIncrement);
    distanceFromTop += calculateBoxIncrement(intimaciesHeight);
    float weaponryHeight = encodeWeaponry(directContent, character, distanceFromTop, 113);
    distanceFromTop += calculateBoxIncrement(weaponryHeight);
    float armourHeight = encodeArmourAndSoak(directContent, character, distanceFromTop, 69);
    distanceFromTop += calculateBoxIncrement(armourHeight);
    float healthHeight = encodeMovementAndHealth(directContent, character, distanceFromTop, 99);
    distanceFromTop += calculateBoxIncrement(healthHeight);
    float remainingHeight = PdfFirstPageEncoder.CONTENT_HEIGHT - distanceFromTop;
    encodeCombatStats(directContent, character, distanceFromTop, remainingHeight);
    encodeCopyright(directContent);
  }

  private void encodeCopyright(PdfContentByte directContent) throws DocumentException {
    int lineHeight = IVoidStateFormatConstants.COMMENT_FONT_SIZE + 2;
    Font copyrightFont = new Font(baseFont, IVoidStateFormatConstants.COMMENT_FONT_SIZE);
    float copyrightHeight = pageConfiguration.getPageHeight() - pageConfiguration.getContentHeight();
    Bounds firstColumnBounds = pageConfiguration.getFirstColumnRectangle(CONTENT_HEIGHT, copyrightHeight, 1);
    Anchor voidstatePhrase = new Anchor("Inspired by Voidstate\nhttp://www.voidstate.com", copyrightFont); //$NON-NLS-1$
    voidstatePhrase.setReference("http://www.voidstate.com"); //$NON-NLS-1$
    PdfTextEncodingUtilities.encodeText(directContent, voidstatePhrase, firstColumnBounds, lineHeight);
    Anchor anathemaPhrase = new Anchor("Created with Anathema \u00A92006\nhttp://anathema.sf.net", copyrightFont); //$NON-NLS-1$
    anathemaPhrase.setReference("http://anathema.sf.net"); //$NON-NLS-1$
    Bounds anathemaBounds = pageConfiguration.getSecondColumnRectangle(CONTENT_HEIGHT, copyrightHeight, 1);
    PdfTextEncodingUtilities.encodeText(directContent, anathemaPhrase, anathemaBounds, lineHeight, Element.ALIGN_CENTER);
    Anchor whitewolfPhrase = new Anchor("Exalted \u00A92006 by White Wolf, Inc.\nhttp://www.white-wolf.com", //$NON-NLS-1$
        copyrightFont);
    whitewolfPhrase.setReference("http://www.white-wolf.com"); //$NON-NLS-1$
    Bounds whitewolfBounds = pageConfiguration.getThirdColumnRectangle(CONTENT_HEIGHT, copyrightHeight);
    PdfTextEncodingUtilities.encodeText(
        directContent,
        whitewolfPhrase,
        whitewolfBounds,
        lineHeight,
        Element.ALIGN_RIGHT);
  }

  private float encodeEssence(
      PdfContentByte directContent,
      IGenericCharacter character,
      float distanceFromTop,
      float height) throws DocumentException {
    Bounds essenceBounds = pageConfiguration.getThirdColumnRectangle(distanceFromTop, height);
    IPdfContentEncoder encoder = partEncoder.getEssenceEncoder();
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

  private void encodeFirstColumn(PdfContentByte directContent, IGenericCharacter character, int distanceFromTop) {
    int attributeHeight = encodeAttributes(directContent, character, distanceFromTop);
    encodeAbilities(directContent, character, attributeHeight + PADDING);
  }

  private void encodeAbilities(PdfContentByte directContent, IGenericCharacter character, int distanceFromTop) {
    int abilitiesHeight = CONTENT_HEIGHT - distanceFromTop;
    Bounds boxBounds = pageConfiguration.getFirstColumnRectangle(distanceFromTop, abilitiesHeight, 1);
    Bounds contentBounds = boxEncoder.encodeBox(directContent, boxBounds, getHeaderLabel("Abilities")); //$NON-NLS-1$
    encodeAbilities(directContent, character, contentBounds);
  }

  private int encodeAttributes(PdfContentByte directContent, IGenericCharacter character, int distanceFromTop) {
    int attributeHeight = 128;
    Bounds attributeBounds = pageConfiguration.getFirstColumnRectangle(distanceFromTop, attributeHeight, 1);
    Bounds attributesContentBounds = boxEncoder.encodeBox(directContent, attributeBounds, getHeaderLabel("Attributes")); //$NON-NLS-1$
    IGroupedTraitType[] attributeGroups = character.getTemplate().getAttributeGroups();
    encodeAttributes(directContent, attributesContentBounds, attributeGroups, character);
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
    CharacterType characterType = character.getTemplate().getTemplateType().getCharacterType();
    Bounds animaBounds = pageConfiguration.getThirdColumnRectangle(distanceFromTop, height);
    IPdfContentEncoder encoder = registry.getAnimaEncoder(characterType);
    boxEncoder.encodeBox(directContent, encoder, character, animaBounds);
  }

  private float encodeArmourAndSoak(
      PdfContentByte directContent,
      IGenericCharacter character,
      float distanceFromTop,
      float height) throws DocumentException {
    Bounds bounds = pageConfiguration.getSecondColumnRectangle(distanceFromTop, height, 2);
    IPdfContentEncoder contentEncoder = registry.getArmourContentEncoder();
    boxEncoder.encodeBox(directContent, contentEncoder, character, bounds);
    return height;
  }

  private float encodeSocialCombatStats(
      PdfContentByte directContent,
      IGenericCharacter character,
      float distanceFromTop,
      float height) throws DocumentException {
    Bounds bounds = pageConfiguration.getThirdColumnRectangle(distanceFromTop, height);
    IPdfContentEncoder encoder = new SecondEditionSocialCombatStatsEncoder(resources, baseFont);
    boxEncoder.encodeBox(directContent, encoder, character, bounds);
    return height;
  }

  private float encodeCombatStats(
      PdfContentByte directContent,
      IGenericCharacter character,
      float distanceFromTop,
      float height) throws DocumentException {
    Bounds bounds = pageConfiguration.getSecondColumnRectangle(distanceFromTop, height, 2);
    IPdfContentEncoder encoder = new SecondEditionCombatStatsEncoder(resources, baseFont);
    boxEncoder.encodeBox(directContent, encoder, character, bounds);
    return height;
  }

  private float encodeMovementAndHealth(
      PdfContentByte directContent,
      IGenericCharacter character,
      float distanceFromTop,
      float height) throws DocumentException {
    Bounds bounds = pageConfiguration.getSecondColumnRectangle(distanceFromTop, height, 2);
    IPdfContentEncoder encoder = new SecondEditionHealthAndMovementEncoder(
        resources,
        baseFont,
        registry.getSymbolBaseFont());
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
      IGenericCharacter character,
      float distanceFromTop,
      float height) {
    Bounds bounds = pageConfiguration.getSecondColumnRectangle(distanceFromTop, height, 1);
    String header = resources.getString("Sheet.Header.Virtues"); //$NON-NLS-1$
    Bounds contentBounds = boxEncoder.encodeBox(directContent, bounds, header);
    new PdfVirtueEncoder(resources, baseFont).encodeVirtues(directContent, character, contentBounds);
    return height;
  }

  private float encodeWeaponry(
      PdfContentByte directContent,
      IGenericCharacter character,
      float distanceFromTop,
      float height) throws DocumentException {
    Bounds bounds = pageConfiguration.getSecondColumnRectangle(distanceFromTop, height, 2);
    IPdfContentEncoder weaponryEncoder = registry.getWeaponContentEncoder();
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

  private float encodeGreatCurse(
      PdfContentByte directContent,
      IGenericCharacter character,
      float distanceFromTop,
      float height) throws DocumentException {
    Bounds bounds = pageConfiguration.getSecondColumnRectangle(distanceFromTop, height, 1);
    CharacterType characterType = character.getTemplate().getTemplateType().getCharacterType();
    IPdfContentEncoder encoder = registry.getGreatCurseEncoder(characterType);
    boxEncoder.encodeBox(directContent, encoder, character, bounds);
    return height;
  }

  private float encodeIntimacies(
      PdfContentByte directContent,
      IGenericCharacter character,
      float distanceFromTop,
      float height) throws DocumentException {
    Bounds bounds = pageConfiguration.getSecondColumnRectangle(distanceFromTop, height, 1);
    IPdfContentEncoder encoder = registry.getIntimaciesEncoder();
    boxEncoder.encodeBox(directContent, encoder, character, bounds);
    return height;
  }

  private void encodeAbilities(PdfContentByte directContent, IGenericCharacter character, Bounds contentBounds) {
    PdfAbilitiesEncoder encoder = new PdfAbilitiesEncoder(baseFont, resources, essenceMax);
    encoder.encodeAbilities(directContent, character, contentBounds);
  }

  private final void encodeAttributes(
      PdfContentByte directContent,
      Bounds contentBounds,
      IGroupedTraitType[] attributeGroups,
      IGenericTraitCollection traitCollection) {
    PdfAttributesEncoder encoder = new PdfAttributesEncoder(baseFont, resources, essenceMax);
    encoder.encodeAttributes(directContent, contentBounds, attributeGroups, traitCollection);
  }
}
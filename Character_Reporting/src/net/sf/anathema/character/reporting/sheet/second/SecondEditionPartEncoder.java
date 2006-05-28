package net.sf.anathema.character.reporting.sheet.second;

import java.io.IOException;

import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.reporting.sheet.SecondEditionEncodingRegistry;
import net.sf.anathema.character.reporting.sheet.common.AbstractPdfPartEncoder;
import net.sf.anathema.character.reporting.sheet.common.IPdfContentEncoder;
import net.sf.anathema.character.reporting.sheet.common.PdfVirtueEncoder;
import net.sf.anathema.character.reporting.sheet.common.PdfWillpowerEncoder;
import net.sf.anathema.character.reporting.sheet.common.anima.PdfAnimaEncoder;
import net.sf.anathema.character.reporting.sheet.page.PdfFirstPageEncoder;
import net.sf.anathema.character.reporting.sheet.pageformat.IVoidStateFormatConstants;
import net.sf.anathema.character.reporting.sheet.pageformat.PdfPageConfiguration;
import net.sf.anathema.character.reporting.sheet.util.PdfBoxEncoder;
import net.sf.anathema.character.reporting.util.Bounds;
import net.sf.anathema.lib.resources.IResources;

import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.PdfContentByte;

public class SecondEditionPartEncoder extends AbstractPdfPartEncoder {

  private static final int ANIMA_HEIGHT = 128;
  private final PdfPageConfiguration pageConfiguration;
  private final PdfBoxEncoder boxEncoder;
  private final SecondEditionEncodingRegistry registry;

  public SecondEditionPartEncoder(
      SecondEditionEncodingRegistry registry,
      IResources resources,
      int essenceMax,
      PdfPageConfiguration pageConfiguration) {
    super(registry.getBaseFont(), resources, essenceMax);
    this.registry = registry;
    this.pageConfiguration = pageConfiguration;
    this.boxEncoder = new PdfBoxEncoder(getBaseFont());
  }

  private float calculateBoxIncrement(float height) {
    return height + IVoidStateFormatConstants.PADDING;
  }

  private void encodeAnima(
      PdfContentByte directContent,
      IGenericCharacter character,
      float distanceFromTop,
      float height) throws DocumentException, IOException {
    Bounds animaBounds = pageConfiguration.getThirdColumnRectangle(distanceFromTop, height);
    encodeContent(
        directContent,
        new PdfAnimaEncoder(getResources(), getBaseFont(), registry.getSymbolBaseFont()),
        character,
        animaBounds,
        "Anima"); //$NON-NLS-1$
  }

  private float encodeArmourAndSoak(
      PdfContentByte directContent,
      IGenericCharacter character,
      float distanceFromTop,
      float height) throws DocumentException, IOException {
    Bounds bounds = pageConfiguration.getSecondColumnRectangle(distanceFromTop, height, 2);
    IPdfContentEncoder contentEncoder = registry.getArmourContentEncoder();
    encodeContent(directContent, contentEncoder, character, bounds, "ArmourSoak"); //$NON-NLS-1$
    return height;
  }

  private float encodeSocialCombatStats(
      PdfContentByte directContent,
      IGenericCharacter character,
      float distanceFromTop,
      float height) throws DocumentException, IOException {
    Bounds bounds = pageConfiguration.getThirdColumnRectangle(distanceFromTop, height);
    IPdfContentEncoder encoder = new SecondEditionSocialCombatStatsEncoder(getResources(), getBaseFont());
    encodeContent(directContent, encoder, character, bounds, "SocialCombat"); //$NON-NLS-1$
    return height;
  }

  private float encodeCombatStats(
      PdfContentByte directContent,
      IGenericCharacter character,
      float distanceFromTop,
      float height) throws DocumentException, IOException {
    Bounds bounds = pageConfiguration.getSecondColumnRectangle(distanceFromTop, height, 2);
    IPdfContentEncoder encoder = new SecondEditionCombatStatsEncoder(getResources(), getBaseFont());
    encodeContent(directContent, encoder, character, bounds, "Combat"); //$NON-NLS-1$
    return height;
  }

  private void encodeContent(
      PdfContentByte directContent,
      IPdfContentEncoder encoder,
      IGenericCharacter character,
      Bounds bounds,
      String headerId) throws DocumentException, IOException {
    String animaHeader = getResources().getString("Sheet.Header." + headerId); //$NON-NLS-1$
    Bounds contentBounds = boxEncoder.encodeBox(directContent, bounds, animaHeader);
    encoder.encode(directContent, character, contentBounds);
  }

  public void encodeEditionSpecificFirstPagePart(
      PdfContentByte directContent,
      IGenericCharacter character,
      float distanceFromTop) throws DocumentException, IOException {
    encodeAnima(directContent, character, distanceFromTop, ANIMA_HEIGHT);
    float virtueHeight = 75;
    float virtueRest = ANIMA_HEIGHT - virtueHeight - IVoidStateFormatConstants.PADDING;
    distanceFromTop += calculateBoxIncrement(virtueRest);
    encodeVirtues(directContent, character, distanceFromTop, virtueHeight);
    distanceFromTop += calculateBoxIncrement(virtueHeight);
    float socialCombatHeight = encodeSocialCombatStats(directContent, character, distanceFromTop, 115);
    float willpowerHeight = encodeWillpower(directContent, character, distanceFromTop, 43);
    float willpowerIncrement = calculateBoxIncrement(willpowerHeight);
    distanceFromTop += willpowerIncrement;
    float intimaciesHeight = encodeIntimacies(directContent, character, distanceFromTop, socialCombatHeight - willpowerIncrement);
    distanceFromTop += calculateBoxIncrement(intimaciesHeight);
    float weaponryHeight = encodeWeaponry(directContent, character, distanceFromTop, 113);
    distanceFromTop += calculateBoxIncrement(weaponryHeight);
    float armourHeight = encodeArmourAndSoak(directContent, character, distanceFromTop, 69);
    distanceFromTop += calculateBoxIncrement(armourHeight);
    float healthHeight = encodeMovementAndHealth(directContent, character, distanceFromTop, 99);
    distanceFromTop += calculateBoxIncrement(healthHeight);
    float remainingHeight = PdfFirstPageEncoder.CONTENT_HEIGHT - distanceFromTop;
    encodeCombatStats(directContent, character, distanceFromTop, remainingHeight);
  }

  private float encodeMovementAndHealth(
      PdfContentByte directContent,
      IGenericCharacter character,
      float distanceFromTop,
      float height) throws DocumentException, IOException {
    Bounds bounds = pageConfiguration.getSecondColumnRectangle(distanceFromTop, height, 2);
    IPdfContentEncoder encoder = new SecondEditionHealthAndMovementEncoder(
        getResources(),
        getBaseFont(),
        registry.getSymbolBaseFont());
    encodeContent(directContent, encoder, character, bounds, "MovementHealth"); //$NON-NLS-1$
    return height;
  }

  public void encodePersonalInfos(PdfContentByte directContent, IGenericCharacter character, Bounds infoBounds) {
    SecondEditionPersonalInfoEncoder encoder = new SecondEditionPersonalInfoEncoder(getBaseFont(), getResources());
    encoder.encodePersonalInfos(directContent, character, infoBounds);
  }

  private void encodeVirtues(
      PdfContentByte directContent,
      IGenericCharacter character,
      float distanceFromTop,
      float virtueHeight) {
    Bounds virtueBounds = pageConfiguration.getSecondColumnRectangle(distanceFromTop, virtueHeight, 1);
    String virtueHeader = getResources().getString("Sheet.Header.Virtues"); //$NON-NLS-1$
    Bounds contentBounds = boxEncoder.encodeBox(directContent, virtueBounds, virtueHeader);
    new PdfVirtueEncoder(getResources(), getBaseFont()).encodeVirtues(directContent, character, contentBounds);
  }

  private float encodeWeaponry(
      PdfContentByte directContent,
      IGenericCharacter character,
      float distanceFromTop,
      float height) throws DocumentException, IOException {
    Bounds bounds = pageConfiguration.getSecondColumnRectangle(distanceFromTop, height, 2);
    IPdfContentEncoder weaponryEncoder = registry.getWeaponContentEncoder();
    encodeContent(directContent, weaponryEncoder, character, bounds, "Weapons"); //$NON-NLS-1$
    return height;
  }

  private float encodeWillpower(
      PdfContentByte directContent,
      IGenericCharacter character,
      float distanceFromTop,
      float height) throws DocumentException, IOException {
    Bounds willpowerBounds = pageConfiguration.getSecondColumnRectangle(distanceFromTop, height, 1);
    encodeContent(directContent, new PdfWillpowerEncoder(getBaseFont()), character, willpowerBounds, "Willpower"); //$NON-NLS-1$
    return height;
  }

  private float encodeIntimacies(
      PdfContentByte directContent,
      IGenericCharacter character,
      float distanceFromTop,
      float height) throws DocumentException, IOException {
    Bounds bounds = pageConfiguration.getSecondColumnRectangle(distanceFromTop, height, 1);
    IPdfContentEncoder encoder = registry.getIntimaciesEncoder();
    encodeContent(directContent, encoder, character, bounds, "Intimacies"); //$NON-NLS-1$
    return height;
  }
}
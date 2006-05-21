package net.sf.anathema.character.reporting.sheet.second;

import java.io.IOException;

import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.reporting.sheet.common.AbstractPdfPartEncoder;
import net.sf.anathema.character.reporting.sheet.common.IPdfContentEncoder;
import net.sf.anathema.character.reporting.sheet.common.PdfVirtueEncoder;
import net.sf.anathema.character.reporting.sheet.common.PdfWillpowerEncoder;
import net.sf.anathema.character.reporting.sheet.common.anima.PdfAnimaEncoder;
import net.sf.anathema.character.reporting.sheet.page.PdfFirstPageEncoder;
import net.sf.anathema.character.reporting.sheet.pageformat.IVoidStateFormatConstants;
import net.sf.anathema.character.reporting.sheet.pageformat.PdfPageConfiguration;
import net.sf.anathema.character.reporting.sheet.second.equipment.SecondEditionWeaponryEncoder;
import net.sf.anathema.character.reporting.sheet.util.PdfBoxEncoder;
import net.sf.anathema.character.reporting.util.Bounds;
import net.sf.anathema.lib.resources.IResources;

import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.PdfContentByte;

public class SecondEditionPartEncoder extends AbstractPdfPartEncoder {

  private static final int ANIMA_HEIGHT = 128;
  private final PdfPageConfiguration pageConfiguration;
  private final PdfBoxEncoder boxEncoder;

  public SecondEditionPartEncoder(IResources resources, int essenceMax, PdfPageConfiguration pageConfiguration)
      throws DocumentException,
      IOException {
    super(resources, essenceMax);
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
    encodeContent(directContent, new PdfAnimaEncoder(getResources(), getBaseFont()), character, animaBounds, "Anima"); //$NON-NLS-1$
  }

  private float encodeArmorAndSoak(PdfContentByte directContent, float distanceFromTop, float height) {
    Bounds bounds = pageConfiguration.getSecondColumnRectangle(distanceFromTop, height, 2);
    String header = getResources().getString("Sheet.Header.ArmorySoak"); //$NON-NLS-1$
    boxEncoder.encodeBox(directContent, bounds, header);
    return height;
  }

  private float encodeCombatStats(PdfContentByte directContent, float distanceFromTop, float height) {
    Bounds bounds = pageConfiguration.getSecondColumnRectangle(distanceFromTop, height, 2);
    String header = getResources().getString("Sheet.Header.Combat"); //$NON-NLS-1$
    boxEncoder.encodeBox(directContent, bounds, header);
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
    float willpowerHeight = ANIMA_HEIGHT - virtueHeight - IVoidStateFormatConstants.PADDING;
    encodeWillpower(directContent, character, distanceFromTop, willpowerHeight);
    distanceFromTop += calculateBoxIncrement(willpowerHeight);
    encodeVirtues(directContent, character, distanceFromTop, virtueHeight);
    distanceFromTop += calculateBoxIncrement(virtueHeight);

    float weaponryHeight = encodeWeaponry(directContent, character, distanceFromTop, 150);
    distanceFromTop += calculateBoxIncrement(weaponryHeight);
    float armourHeight = encodeArmorAndSoak(directContent, distanceFromTop, 75);
    distanceFromTop += calculateBoxIncrement(armourHeight);
    float healthHeight = encodeMovementAndHealth(directContent, distanceFromTop, 125);
    distanceFromTop += calculateBoxIncrement(healthHeight);
    float combatHeight = PdfFirstPageEncoder.CONTENT_HEIGHT - distanceFromTop;
    encodeCombatStats(directContent, distanceFromTop, combatHeight);
  }

  private float encodeMovementAndHealth(PdfContentByte directContent, float distanceFromTop, float height) {
    Bounds bounds = pageConfiguration.getSecondColumnRectangle(distanceFromTop, height, 2);
    String header = getResources().getString("Sheet.Header.MovementHealth"); //$NON-NLS-1$
    boxEncoder.encodeBox(directContent, bounds, header);
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
    String header = getResources().getString("Sheet.Header.Weaponry"); //$NON-NLS-1$
    IPdfContentEncoder weaponryEncoder = new SecondEditionWeaponryEncoder(getBaseFont(), getResources());
    encodeContent(directContent, weaponryEncoder, character, bounds, header);
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
}
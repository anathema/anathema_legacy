package net.sf.anathema.character.reporting.sheet.second;

import java.io.IOException;

import net.disy.commons.core.geometry.SmartRectangle;
import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.reporting.sheet.common.AbstractPdfPartEncoder;
import net.sf.anathema.character.reporting.sheet.common.PdfVirtueEncoder;
import net.sf.anathema.character.reporting.sheet.common.PdfWillpowerEncoder;
import net.sf.anathema.character.reporting.sheet.page.PdfFirstPageEncoder;
import net.sf.anathema.character.reporting.sheet.pageformat.IVoidStateFormatConstants;
import net.sf.anathema.character.reporting.sheet.pageformat.PdfPageConfiguration;
import net.sf.anathema.character.reporting.sheet.util.PdfBoxEncoder;
import net.sf.anathema.lib.resources.IResources;

import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.PdfContentByte;

public class SecondEditionPartEncoder extends AbstractPdfPartEncoder {

  private static final int ANIMA_HEIGHT = 128;
  private final PdfPageConfiguration pageConfiguration = new PdfPageConfiguration();
  private final PdfBoxEncoder boxEncoder;

  public SecondEditionPartEncoder(IResources resources, int essenceMax) throws DocumentException, IOException {
    super(resources, essenceMax);
    this.boxEncoder = new PdfBoxEncoder(getBaseFont());
  }

  public void encodePersonalInfos(PdfContentByte directContent, IGenericCharacter character, SmartRectangle infoBounds) {
    SecondEditionPersonalInfoEncoder encoder = new SecondEditionPersonalInfoEncoder(getBaseFont(), getResources());
    encoder.encodePersonalInfos(directContent, character, infoBounds);
  }

  public void encodeEditionSpecificFirstPagePart(
      PdfContentByte directContent,
      IGenericCharacter character,
      int distanceFromTop) {
    int virtueHeight = 75;
    encodeAnima(directContent, distanceFromTop, ANIMA_HEIGHT);
    encodeVirtues(directContent, character, distanceFromTop, virtueHeight);
    distanceFromTop += virtueHeight + IVoidStateFormatConstants.PADDING;
    int willpowerHeight = ANIMA_HEIGHT - virtueHeight - IVoidStateFormatConstants.PADDING;
    encodeWillpower(directContent, character, distanceFromTop, willpowerHeight);
    distanceFromTop += calculateBoxIncrement(willpowerHeight);
    int weaponryHeight = encodeWeaponry(directContent, distanceFromTop, 150);
    distanceFromTop += calculateBoxIncrement(weaponryHeight);
    int armourHeight = encodeArmorAndSoak(directContent, distanceFromTop, 75);
    distanceFromTop += calculateBoxIncrement(armourHeight);
    int healthHeight = encodeMovementAndHealth(directContent, distanceFromTop, 125);
    distanceFromTop += calculateBoxIncrement(healthHeight);
    int combatHeight = PdfFirstPageEncoder.CONTENT_HEIGHT - distanceFromTop; 
    encodeCombatStats(directContent, distanceFromTop, combatHeight);
  }

  private int calculateBoxIncrement(int height) {
    return height + IVoidStateFormatConstants.PADDING;
  }

  private int encodeCombatStats(PdfContentByte directContent, int distanceFromTop, int height) {
    SmartRectangle bounds = pageConfiguration.getSecondColumnRectangle(distanceFromTop, height, 2);
    String header = getResources().getString("Sheet.Header.Combat"); //$NON-NLS-1$
    boxEncoder.encodeBox(directContent, bounds, header);
    return height;
  }

  private int encodeWeaponry(PdfContentByte directContent, int distanceFromTop, int height) {
    SmartRectangle bounds = pageConfiguration.getSecondColumnRectangle(distanceFromTop, height, 2);
    String header = getResources().getString("Sheet.Header.Weaponry"); //$NON-NLS-1$
    boxEncoder.encodeBox(directContent, bounds, header);
    return height;
  }

  private int encodeArmorAndSoak(PdfContentByte directContent, int distanceFromTop, int height) {
    SmartRectangle bounds = pageConfiguration.getSecondColumnRectangle(distanceFromTop, height, 2);
    String header = getResources().getString("Sheet.Header.ArmorySoak"); //$NON-NLS-1$
    boxEncoder.encodeBox(directContent, bounds, header);
    return height;
  }

  private int encodeMovementAndHealth(PdfContentByte directContent, int distanceFromTop, int height) {
    SmartRectangle bounds = pageConfiguration.getSecondColumnRectangle(distanceFromTop, height, 2);
    String header = getResources().getString("Sheet.Header.MovementHealth"); //$NON-NLS-1$
    boxEncoder.encodeBox(directContent, bounds, header);
    return height;
  }

  private void encodeAnima(PdfContentByte directContent, int distanceFromTop, int animaHeight) {
    SmartRectangle animaBounds = pageConfiguration.getThirdColumnRectangle(distanceFromTop, animaHeight);
    String willpowerHeader = getResources().getString("Sheet.Header.Anima"); //$NON-NLS-1$
    boxEncoder.encodeBox(directContent, animaBounds, willpowerHeader);
  }

  private int encodeWillpower(PdfContentByte directContent, IGenericCharacter character, int distanceFromTop, int height) {
    SmartRectangle willpowerBounds = pageConfiguration.getSecondColumnRectangle(distanceFromTop, height, 1);
    String willpowerHeader = getResources().getString("Sheet.Header.Willpower"); //$NON-NLS-1$
    SmartRectangle contentBounds = boxEncoder.encodeBox(directContent, willpowerBounds, willpowerHeader);
    new PdfWillpowerEncoder(getBaseFont()).encodeWillpower(directContent, character, contentBounds);
    return height;
  }

  private void encodeVirtues(
      PdfContentByte directContent,
      IGenericCharacter character,
      int distanceFromTop,
      int virtueHeight) {
    SmartRectangle virtueBounds = pageConfiguration.getSecondColumnRectangle(distanceFromTop, virtueHeight, 1);
    String virtueHeader = getResources().getString("Sheet.Header.Virtues"); //$NON-NLS-1$
    SmartRectangle contentBounds = boxEncoder.encodeBox(directContent, virtueBounds, virtueHeader);
    new PdfVirtueEncoder(getResources(), getBaseFont()).encodeVirtues(directContent, character, contentBounds);
  }
}
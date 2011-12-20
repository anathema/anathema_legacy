package net.sf.anathema.character.reporting.pdf.layout.extended;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.PdfContentByte;
import net.sf.anathema.character.generic.impl.traits.EssenceTemplate;
import net.sf.anathema.character.generic.traits.types.OtherTraitType;
import net.sf.anathema.character.reporting.pdf.content.ReportContent;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.abilities.ExtendedSpecialtiesEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.abilities.AbilitiesBoxContentEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.attributes.PdfAttributesEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.backgrounds.PdfBackgroundEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.experience.PdfExperienceEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.personal.ExtendedPersonalInfoBoxEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.virtues.VirtueBoxContentEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.IBoxContentEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.IVariableBoxContentEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.page.IVoidStateFormatConstants;
import net.sf.anathema.character.reporting.pdf.rendering.page.PdfPageConfiguration;
import net.sf.anathema.lib.resources.IResources;

public class NewPdfFirstPageEncoder extends AbstractPdfPageEncoder {
  private final int essenceMax;

  public NewPdfFirstPageEncoder(IExtendedPartEncoder partEncoder, ExtendedEncodingRegistry registry, IResources resources, int essenceMax,
    PdfPageConfiguration pageConfiguration) {
    super(partEncoder, registry, resources, pageConfiguration);
    this.essenceMax = essenceMax;
  }

  public void encode(Document document, PdfContentByte directContent, ReportContent content) throws

    DocumentException {
    float distanceFromTop = 0;
    float firstRowHeight = encodePersonalInfo(directContent, content, distanceFromTop, getContentHeight());
    distanceFromTop += calculateBoxIncrement(firstRowHeight);

    // First column - top-down
    float firstDistanceFromTop = distanceFromTop;
    float attributeHeight = encodeAttributes(directContent, content, firstDistanceFromTop, 117);
    firstDistanceFromTop += calculateBoxIncrement(attributeHeight);
    float abilityHeight = encodeAbilities(directContent, content, firstDistanceFromTop, 415);
    firstDistanceFromTop += calculateBoxIncrement(abilityHeight);
    // First column - fill in (bottom-up) with specialties
    float specialtyHeight = getContentHeight() - firstDistanceFromTop;
    encodeSpecialties(directContent, content, firstDistanceFromTop, specialtyHeight);

    // Second column - top-down
    float secondDistanceFromTop = distanceFromTop;
    float virtueHeight = encodeVirtues(directContent, content, secondDistanceFromTop, 72);
    float virtueIncrement = calculateBoxIncrement(virtueHeight);
    secondDistanceFromTop += virtueIncrement;
    float greatCurseHeight = encodeGreatCurse(directContent, content, secondDistanceFromTop, 85);
    if (greatCurseHeight > 0) {
      secondDistanceFromTop += calculateBoxIncrement(greatCurseHeight);
    }
    // Second column - fill in (bottom-up) with mutations, merits & flaws, intimacies
    float secondBottom = getContentHeight() - calculateBoxIncrement(specialtyHeight);
    float reservedHeight = (secondBottom - secondDistanceFromTop) / 4f - IVoidStateFormatConstants.PADDING;
    if (hasMutations(content)) {
      float mutationsHeight = encodeMutations(directContent, content, secondBottom - reservedHeight, reservedHeight);
      secondBottom -= calculateBoxIncrement(mutationsHeight);
    }
    if (hasMeritsAndFlaws(content)) {
      float meritsHeight = encodeMeritsAndFlaws(directContent, content, secondBottom - reservedHeight, reservedHeight);
      secondBottom -= calculateBoxIncrement(meritsHeight);
    }
    encodeIntimacies(directContent, content, secondDistanceFromTop, secondBottom - secondDistanceFromTop);

    // Third column - top-down (lining up with virtues)
    float thirdDistanceFromTop = distanceFromTop;
    float dotsHeight = 30;
    encodeEssenceDots(directContent, content, thirdDistanceFromTop, dotsHeight);
    encodeWillpowerDots(directContent, content, thirdDistanceFromTop + virtueHeight - dotsHeight, dotsHeight);
    thirdDistanceFromTop += virtueIncrement;
    // Third column - bottom-up
    float thirdBottom = getContentHeight();
    float experienceHeight = encodeExperience(directContent, content, thirdBottom - 30, 30);
    float experienceIncrement = calculateBoxIncrement(experienceHeight);
    thirdBottom -= experienceIncrement;
    float languageHeight = specialtyHeight - experienceIncrement;
    languageHeight = encodeLinguistics(directContent, content, thirdBottom - languageHeight, languageHeight);
    thirdBottom -= calculateBoxIncrement(languageHeight);
    float additionalIncrement = encodeAdditional(directContent, content, thirdDistanceFromTop, thirdBottom);
    thirdBottom -= additionalIncrement;
    // Third column - fill in (bottom-up) with backgrounds
    encodeBackgrounds(directContent, content, thirdDistanceFromTop, thirdBottom - thirdDistanceFromTop);

    encodeCopyright(directContent);
  }

  private float encodeEssenceDots(PdfContentByte directContent, ReportContent content, float distanceFromTop,
    float height) throws DocumentException {
    return encodeFixedBox(directContent, content, getPartEncoder().getDotsEncoder(OtherTraitType.Essence, EssenceTemplate.SYSTEM_ESSENCE_MAX,
      "Essence"), 3, 1, distanceFromTop, height);
  }

  private float encodePersonalInfo(PdfContentByte directContent, ReportContent content, float distanceFromTop,
    float maxHeight) throws DocumentException {
    return encodeVariableBox(directContent, content, new ExtendedPersonalInfoBoxEncoder(getResources()), 1, 3, distanceFromTop,
      maxHeight);
  }

  private float encodeAbilities(PdfContentByte directContent, ReportContent content, float distanceFromTop, float height) throws DocumentException {
    return encodeFixedBox(directContent, content, AbilitiesBoxContentEncoder.createWithCraftsOnly(getBaseFont(), getResources(), essenceMax, -1), 1, 1,
      distanceFromTop, height);
  }

  private float encodeSpecialties(PdfContentByte directContent, ReportContent content, float distanceFromTop,
    float height) throws DocumentException {
    return encodeFixedBox(directContent, content, new ExtendedSpecialtiesEncoder(getResources(), getBaseFont()), 1, 2, distanceFromTop, height);
  }

  private float encodeAttributes(PdfContentByte directContent, ReportContent content, float distanceFromTop, float height) throws DocumentException {
    return encodeFixedBox(directContent, content, new PdfAttributesEncoder(getBaseFont(), getResources(), essenceMax,
      getPartEncoder().isEncodeAttributeAsFavorable()), 1, 1, distanceFromTop, height);
  }

  private float encodeBackgrounds(PdfContentByte directContent, ReportContent content, float distanceFromTop,
    float height) throws DocumentException {
    return encodeFixedBox(directContent, content, new PdfBackgroundEncoder(getResources(), getBaseFont()), 3, 1, distanceFromTop, height);
  }

  private float encodeVirtues(PdfContentByte directContent, ReportContent content, float distanceFromTop, float height) throws DocumentException {
    return encodeFixedBox(directContent, content, new VirtueBoxContentEncoder(), 2, 1, distanceFromTop, height);
  }

  private float encodeWillpowerDots(PdfContentByte directContent, ReportContent content, float distanceFromTop,
    float height) throws DocumentException {
    return encodeFixedBox(directContent, content, getPartEncoder().getDotsEncoder(OtherTraitType.Willpower, 10, "Willpower"), 3, 1,
      distanceFromTop, height);
  }

  private float encodeGreatCurse(PdfContentByte directContent, ReportContent content, float distanceFromTop, float height) throws DocumentException {
    IBoxContentEncoder encoder = getPartEncoder().getGreatCurseEncoder();
    if (encoder != null) {
      return encodeFixedBox(directContent, content, encoder, 2, 1, distanceFromTop, height);
    }
    else {
      return 0;
    }
  }

  private float encodeIntimacies(PdfContentByte directContent, ReportContent content, float distanceFromTop, float height) throws DocumentException {
    return encodeFixedBox(directContent, content, getPartEncoder().getIntimaciesEncoder(getRegistry()), 2, 1, distanceFromTop, height);
  }

  private float encodeLinguistics(PdfContentByte directContent, ReportContent content, float distanceFromTop,
    float height) throws DocumentException {
    return encodeFixedBox(directContent, content, getRegistry().getLinguisticsEncoder(), 3, 1, distanceFromTop, height);
  }

  private float encodeExperience(PdfContentByte directContent, ReportContent content, float distanceFromTop, float height) throws DocumentException {
    return encodeFixedBox(directContent, content, new PdfExperienceEncoder(getResources(), getBaseFont()), 3, 1, distanceFromTop, height);
  }

  private boolean hasMutations(ReportContent content) {
    return getRegistry().getMutationsEncoder().hasContent(content);
  }

  private boolean hasMeritsAndFlaws(ReportContent content) {
    return getRegistry().getMeritsAndFlawsEncoder().hasContent(content);
  }

  private float encodeMutations(PdfContentByte directContent, ReportContent content, float distanceFromTop, float height) throws DocumentException {
    return encodeFixedBox(directContent, content, getRegistry().getMutationsEncoder(), 2, 1, distanceFromTop, height);
  }

  private float encodeMeritsAndFlaws(PdfContentByte directContent, ReportContent content, float distanceFromTop,
    float height) throws DocumentException {
    return encodeFixedBox(directContent, content, getRegistry().getMeritsAndFlawsEncoder(), 2, 1, distanceFromTop, height);
  }

  private float encodeAdditional(PdfContentByte directContent, ReportContent content, float distanceFromTop, float bottom) throws DocumentException {
    float increment = 0;
    for (IVariableBoxContentEncoder encoder : getPartEncoder().getAdditionalFirstPageEncoders()) {
      float height = encodeVariableBoxBottom(directContent, content, encoder, 3, 1, bottom, bottom - distanceFromTop - increment);
      increment += calculateBoxIncrement(height);
    }
    return increment;
  }
}

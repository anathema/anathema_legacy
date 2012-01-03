package net.sf.anathema.character.reporting.pdf.layout.extended;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import net.sf.anathema.character.generic.impl.traits.EssenceTemplate;
import net.sf.anathema.character.generic.traits.types.OtherTraitType;
import net.sf.anathema.character.reporting.pdf.content.ReportContent;
import net.sf.anathema.character.reporting.pdf.rendering.EncoderIds;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.EncoderRegistry;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.abilities.AbilitiesBoxContentEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.abilities.ExtendedSpecialtiesEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.backgrounds.PdfBackgroundEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.experience.ExperienceBoxContentEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.personal.PersonalInfoBoxEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.virtues.VirtueBoxContentEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.ContentEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.IVariableContentEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.graphics.SheetGraphics;
import net.sf.anathema.character.reporting.pdf.rendering.page.IVoidStateFormatConstants;
import net.sf.anathema.character.reporting.pdf.rendering.page.PdfPageConfiguration;
import net.sf.anathema.lib.resources.IResources;

public class Extended2ndEditionFirstPageEncoder extends AbstractPdfPageEncoder {
  private EncoderRegistry encoderRegistry;

  public Extended2ndEditionFirstPageEncoder(EncoderRegistry encoderRegistry, IExtendedPartEncoder partEncoder, ExtendedEncodingRegistry registry,
    IResources resources, PdfPageConfiguration pageConfiguration) {
    super(partEncoder, registry, resources, pageConfiguration);
    this.encoderRegistry = encoderRegistry;
  }

  @Override
  public void encode(Document document, SheetGraphics graphics, ReportContent content) throws

    DocumentException {
    float distanceFromTop = 0;
    float firstRowHeight = encodePersonalInfo(graphics, content, distanceFromTop, getContentHeight());
    distanceFromTop += calculateBoxIncrement(firstRowHeight);

    // First column - top-down
    float firstDistanceFromTop = distanceFromTop;
    float attributeHeight = encodeAttributes(graphics, content, firstDistanceFromTop, 117);
    firstDistanceFromTop += calculateBoxIncrement(attributeHeight);
    float abilityHeight = encodeAbilities(graphics, content, firstDistanceFromTop, 415);
    firstDistanceFromTop += calculateBoxIncrement(abilityHeight);
    // First column - fill in (bottom-up) with specialties
    float specialtyHeight = getContentHeight() - firstDistanceFromTop;
    encodeSpecialties(graphics, content, firstDistanceFromTop, specialtyHeight);

    // Second column - top-down
    float secondDistanceFromTop = distanceFromTop;
    float virtueHeight = encodeVirtues(graphics, content, secondDistanceFromTop, 72);
    float virtueIncrement = calculateBoxIncrement(virtueHeight);
    secondDistanceFromTop += virtueIncrement;
    float greatCurseHeight = encodeGreatCurse(graphics, content, secondDistanceFromTop, 85);
    if (greatCurseHeight > 0) {
      secondDistanceFromTop += calculateBoxIncrement(greatCurseHeight);
    }
    // Second column - fill in (bottom-up) with mutations, merits & flaws, intimacies
    float secondBottom = getContentHeight() - calculateBoxIncrement(specialtyHeight);
    float reservedHeight = (secondBottom - secondDistanceFromTop) / 4f - IVoidStateFormatConstants.PADDING;
    if (hasMutations(content)) {
      float mutationsHeight = encodeMutations(graphics, content, secondBottom - reservedHeight, reservedHeight);
      secondBottom -= calculateBoxIncrement(mutationsHeight);
    }
    if (hasMeritsAndFlaws(content)) {
      float meritsHeight = encodeMeritsAndFlaws(graphics, content, secondBottom - reservedHeight, reservedHeight);
      secondBottom -= calculateBoxIncrement(meritsHeight);
    }
    encodeIntimacies(graphics, content, secondDistanceFromTop, secondBottom - secondDistanceFromTop);

    // Third column - top-down (lining up with virtues)
    float thirdDistanceFromTop = distanceFromTop;
    float dotsHeight = 30;
    encodeEssenceDots(graphics, content, thirdDistanceFromTop, dotsHeight);
    encodeWillpowerDots(graphics, content, thirdDistanceFromTop + virtueHeight - dotsHeight, dotsHeight);
    thirdDistanceFromTop += virtueIncrement;
    // Third column - bottom-up
    float thirdBottom = getContentHeight();
    float experienceHeight = encodeExperience(graphics, content, thirdBottom - 30, 30);
    float experienceIncrement = calculateBoxIncrement(experienceHeight);
    thirdBottom -= experienceIncrement;
    float languageHeight = specialtyHeight - experienceIncrement;
    languageHeight = encodeLinguistics(graphics, content, thirdBottom - languageHeight, languageHeight);
    thirdBottom -= calculateBoxIncrement(languageHeight);
    float additionalIncrement = encodeAdditional(graphics, content, thirdDistanceFromTop, thirdBottom);
    thirdBottom -= additionalIncrement;
    // Third column - fill in (bottom-up) with backgrounds
    encodeBackgrounds(graphics, content, thirdDistanceFromTop, thirdBottom - thirdDistanceFromTop);

    encodeCopyright(graphics);
  }

  private float encodeEssenceDots(SheetGraphics graphics, ReportContent content, float distanceFromTop, float height) throws DocumentException {
    return encodeFixedBox(graphics, content, getPartEncoder().getDotsEncoder(OtherTraitType.Essence, EssenceTemplate.SYSTEM_ESSENCE_MAX, "Essence"),
      3, 1, distanceFromTop, height);
  }

  private float encodePersonalInfo(SheetGraphics graphics, ReportContent content, float distanceFromTop, float maxHeight) throws DocumentException {
    return encodeVariableBox(graphics, content, new PersonalInfoBoxEncoder(getResources()), 1, 3, distanceFromTop, maxHeight);
  }

  private float encodeAbilities(SheetGraphics graphics, ReportContent content, float distanceFromTop, float height) throws DocumentException {
    return encodeFixedBox(graphics, content, AbilitiesBoxContentEncoder.createWithCraftsOnly(getResources(), -1), 1, 1, distanceFromTop, height);
  }

  private float encodeSpecialties(SheetGraphics graphics, ReportContent content, float distanceFromTop, float height) throws DocumentException {
    return encodeFixedBox(graphics, content, new ExtendedSpecialtiesEncoder(getResources(), getBaseFont()), 1, 2, distanceFromTop, height);
  }

  private float encodeAttributes(SheetGraphics graphics, ReportContent content, float distanceFromTop, float height) throws DocumentException {
    ContentEncoder encoder = encoderRegistry.createEncoder(getResources(), content, EncoderIds.ATTRIBUTES);
    return encodeFixedBox(graphics, content, encoder, 1, 1, distanceFromTop, height);
  }

  private float encodeBackgrounds(SheetGraphics graphics, ReportContent content, float distanceFromTop, float height) throws DocumentException {
    return encodeFixedBox(graphics, content, new PdfBackgroundEncoder(getResources()), 3, 1, distanceFromTop, height);
  }

  private float encodeVirtues(SheetGraphics graphics, ReportContent content, float distanceFromTop, float height) throws DocumentException {
    return encodeFixedBox(graphics, content, new VirtueBoxContentEncoder(), 2, 1, distanceFromTop, height);
  }

  private float encodeWillpowerDots(SheetGraphics graphics, ReportContent content, float distanceFromTop, float height) throws DocumentException {
    return encodeFixedBox(graphics, content, getPartEncoder().getDotsEncoder(OtherTraitType.Willpower, 10, "Willpower"), 3, 1, distanceFromTop,
      height);
  }

  private float encodeGreatCurse(SheetGraphics graphics, ReportContent content, float distanceFromTop, float height) throws DocumentException {
    ContentEncoder encoder = getPartEncoder().getGreatCurseEncoder();
    if (encoder != null) {
      return encodeFixedBox(graphics, content, encoder, 2, 1, distanceFromTop, height);
    }
    else {
      return 0;
    }
  }

  private float encodeIntimacies(SheetGraphics graphics, ReportContent content, float distanceFromTop, float height) throws DocumentException {
    ContentEncoder encoder = encoderRegistry.createEncoder(getResources(), content, EncoderIds.INTIMACIES_EXTENDED);
    return encodeFixedBox(graphics, content, encoder, 2, 1, distanceFromTop, height);
  }

  private float encodeLinguistics(SheetGraphics graphics, ReportContent content, float distanceFromTop, float height) throws DocumentException {
    return encodeFixedBox(graphics, content, getRegistry().getLinguisticsEncoder(), 3, 1, distanceFromTop, height);
  }

  private float encodeExperience(SheetGraphics graphics, ReportContent content, float distanceFromTop, float height) throws DocumentException {
    return encodeFixedBox(graphics, content, new ExperienceBoxContentEncoder(), 3, 1, distanceFromTop, height);
  }

  private boolean hasMutations(ReportContent content) {
    return getRegistry().getMutationsEncoder().hasContent(content);
  }

  private boolean hasMeritsAndFlaws(ReportContent content) {
    return encoderRegistry.hasEncoder(EncoderIds.MERITS_AND_FLAWS, content) && getMeritsAndFlawsEncoder(content).hasContent(content);
  }

  private ContentEncoder getMeritsAndFlawsEncoder(ReportContent content) {
    return encoderRegistry.createEncoder(getResources(), content, EncoderIds.MERITS_AND_FLAWS);
  }

  private float encodeMutations(SheetGraphics graphics, ReportContent content, float distanceFromTop, float height) throws DocumentException {
    return encodeFixedBox(graphics, content, getRegistry().getMutationsEncoder(), 2, 1, distanceFromTop, height);
  }

  private float encodeMeritsAndFlaws(SheetGraphics graphics, ReportContent content, float distanceFromTop, float height) throws DocumentException {
    ContentEncoder encoder = getMeritsAndFlawsEncoder(content);
    return encodeFixedBox(graphics, content, encoder, 2, 1, distanceFromTop, height);
  }

  private float encodeAdditional(SheetGraphics graphics, ReportContent content, float distanceFromTop, float bottom) throws DocumentException {
    float increment = 0;
    for (IVariableContentEncoder encoder : getPartEncoder().getAdditionalFirstPageEncoders()) {
      float height = encodeVariableBoxBottom(graphics, content, encoder, 3, 1, bottom, bottom - distanceFromTop - increment);
      increment += calculateBoxIncrement(height);
    }
    return increment;
  }
}

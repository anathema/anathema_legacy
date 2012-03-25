package net.sf.anathema.character.reporting.pdf.layout.extended;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import net.sf.anathema.character.generic.impl.traits.EssenceTemplate;
import net.sf.anathema.character.generic.traits.types.OtherTraitType;
import net.sf.anathema.character.reporting.pdf.content.ReportSession;
import net.sf.anathema.character.reporting.pdf.rendering.EncoderIds;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.EncoderRegistry;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.abilities.AbilitiesEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.abilities.ExtendedSpecialtiesEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.backgrounds.BackgroundsEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.experience.ExperienceContentEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.personal.PersonalInfoEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.virtues.VirtueEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.ContentEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.IVariableContentEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.graphics.SheetGraphics;
import net.sf.anathema.character.reporting.pdf.rendering.page.IVoidStateFormatConstants;
import net.sf.anathema.character.reporting.pdf.rendering.page.PageConfiguration;
import net.sf.anathema.lib.resources.IResources;

public class ExtendedFirstPageEncoder extends AbstractExtendedPdfPageEncoder {
  private EncoderRegistry encoderRegistry;

  public ExtendedFirstPageEncoder(EncoderRegistry encoderRegistry, IExtendedPartEncoder partEncoder,
          IResources resources, PageConfiguration pageConfiguration) {
    super(partEncoder, resources, pageConfiguration);
    this.encoderRegistry = encoderRegistry;
  }

  @Override
  public void encode(Document document, SheetGraphics graphics, ReportSession session) throws

          DocumentException {
    float distanceFromTop = 0;
    float firstRowHeight = encodePersonalInfo(graphics, session, distanceFromTop, getContentHeight());
    distanceFromTop += calculateBoxIncrement(firstRowHeight);

    // First column - top-down
    float firstDistanceFromTop = distanceFromTop;
    float attributeHeight = encodeAttributes(graphics, session, firstDistanceFromTop, 117);
    firstDistanceFromTop += calculateBoxIncrement(attributeHeight);
    float abilityHeight = encodeAbilities(graphics, session, firstDistanceFromTop, 415);
    firstDistanceFromTop += calculateBoxIncrement(abilityHeight);
    // First column - fill in (bottom-up) with specialties
    float specialtyHeight = getContentHeight() - firstDistanceFromTop;
    encodeSpecialties(graphics, session, firstDistanceFromTop, specialtyHeight);

    // Second column - top-down
    float secondDistanceFromTop = distanceFromTop;
    float virtueHeight = encodeVirtues(graphics, session, secondDistanceFromTop, 72);
    float virtueIncrement = calculateBoxIncrement(virtueHeight);
    secondDistanceFromTop += virtueIncrement;
    float greatCurseHeight = encodeGreatCurse(graphics, session, secondDistanceFromTop, 85);
    if (greatCurseHeight > 0) {
      secondDistanceFromTop += calculateBoxIncrement(greatCurseHeight);
    }
    // Second column - fill in (bottom-up) with mutations, merits & flaws, intimacies
    float secondBottom = getContentHeight() - calculateBoxIncrement(specialtyHeight);
    float reservedHeight = (secondBottom - secondDistanceFromTop) / 4f - IVoidStateFormatConstants.PADDING;
    if (hasMutations(session)) {
      float mutationsHeight = encodeMutations(graphics, session, secondBottom - reservedHeight, reservedHeight);
      secondBottom -= calculateBoxIncrement(mutationsHeight);
    }
    if (hasMeritsAndFlaws(session)) {
      float meritsHeight = encodeMeritsAndFlaws(graphics, session, secondBottom - reservedHeight, reservedHeight);
      secondBottom -= calculateBoxIncrement(meritsHeight);
    }
    encodeIntimacies(graphics, session, secondDistanceFromTop, secondBottom - secondDistanceFromTop);

    // Third column - top-down (lining up with virtues)
    float thirdDistanceFromTop = distanceFromTop;
    float dotsHeight = 30;
    encodeEssenceDots(graphics, session, thirdDistanceFromTop, dotsHeight);
    encodeWillpowerDots(graphics, session, thirdDistanceFromTop + virtueHeight - dotsHeight, dotsHeight);
    thirdDistanceFromTop += virtueIncrement;
    // Third column - bottom-up
    float thirdBottom = getContentHeight();
    float experienceHeight = encodeExperience(graphics, session, thirdBottom - 30, 30);
    float experienceIncrement = calculateBoxIncrement(experienceHeight);
    thirdBottom -= experienceIncrement;
    float languageHeight = specialtyHeight - experienceIncrement;
    languageHeight = encodeLinguistics(graphics, session, thirdBottom - languageHeight, languageHeight);
    thirdBottom -= calculateBoxIncrement(languageHeight);
    float additionalIncrement = encodeAdditional(graphics, session, thirdDistanceFromTop, thirdBottom);
    thirdBottom -= additionalIncrement;
    // Third column - fill in (bottom-up) with backgrounds
    encodeBackgrounds(graphics, session, thirdDistanceFromTop, thirdBottom - thirdDistanceFromTop);

    encodeCopyright(graphics);
  }

  private float encodeEssenceDots(SheetGraphics graphics, ReportSession session, float distanceFromTop, float height) throws DocumentException {
    return encodeFixedBox(graphics, session, getPartEncoder().getDotsEncoder(OtherTraitType.Essence, EssenceTemplate.SYSTEM_ESSENCE_MAX, "Essence"), 3, 1, distanceFromTop, height);
  }

  private float encodePersonalInfo(SheetGraphics graphics, ReportSession session, float distanceFromTop, float maxHeight) throws DocumentException {
    return encodeVariableBox(graphics, session, new PersonalInfoEncoder(getResources()), 1, 3, distanceFromTop, maxHeight);
  }

  private float encodeAbilities(SheetGraphics graphics, ReportSession session, float distanceFromTop, float height) throws DocumentException {
    return encodeFixedBox(graphics, session, AbilitiesEncoder.createWithCraftsOnly(getResources(), -1), 1, 1, distanceFromTop, height);
  }

  private float encodeSpecialties(SheetGraphics graphics, ReportSession session, float distanceFromTop, float height) throws DocumentException {
    return encodeFixedBox(graphics, session, new ExtendedSpecialtiesEncoder(getResources()), 1, 2, distanceFromTop, height);
  }

  private float encodeAttributes(SheetGraphics graphics, ReportSession session, float distanceFromTop, float height) throws DocumentException {
    ContentEncoder encoder = encoderRegistry.createEncoder(getResources(), session, EncoderIds.ATTRIBUTES);
    return encodeFixedBox(graphics, session, encoder, 1, 1, distanceFromTop, height);
  }

  private float encodeBackgrounds(SheetGraphics graphics, ReportSession session, float distanceFromTop, float height) throws DocumentException {
    return encodeFixedBox(graphics, session, new BackgroundsEncoder(getResources()), 3, 1, distanceFromTop, height);
  }

  private float encodeVirtues(SheetGraphics graphics, ReportSession session, float distanceFromTop, float height) throws DocumentException {
    return encodeFixedBox(graphics, session, new VirtueEncoder(), 2, 1, distanceFromTop, height);
  }

  private float encodeWillpowerDots(SheetGraphics graphics, ReportSession session, float distanceFromTop, float height) throws DocumentException {
    return encodeFixedBox(graphics, session, getPartEncoder().getDotsEncoder(OtherTraitType.Willpower, 10, "Willpower"), 3, 1, distanceFromTop, height);
  }

  private float encodeGreatCurse(SheetGraphics graphics, ReportSession session, float distanceFromTop, float height) throws DocumentException {
    ContentEncoder encoder = getPartEncoder().getGreatCurseEncoder(encoderRegistry, session);
    if (encoder != null) {
      return encodeFixedBox(graphics, session, encoder, 2, 1, distanceFromTop, height);
    } else {
      return 0;
    }
  }

  private float encodeIntimacies(SheetGraphics graphics, ReportSession session, float distanceFromTop, float height) throws DocumentException {
    ContentEncoder encoder = encoderRegistry.createEncoder(getResources(), session, EncoderIds.INTIMACIES_EXTENDED);
    return encodeFixedBox(graphics, session, encoder, 2, 1, distanceFromTop, height);
  }

  private float encodeLinguistics(SheetGraphics graphics, ReportSession session, float distanceFromTop, float height) throws DocumentException {
    ContentEncoder linguisticsEncoder = encoderRegistry.createEncoder(getResources(), session, EncoderIds.LANGUAGES);
    return encodeFixedBox(graphics, session, linguisticsEncoder, 3, 1, distanceFromTop, height);
  }

  private float encodeExperience(SheetGraphics graphics, ReportSession session, float distanceFromTop, float height) throws DocumentException {
    return encodeFixedBox(graphics, session, new ExperienceContentEncoder(), 3, 1, distanceFromTop, height);
  }

  private boolean hasMutations(ReportSession session) {
    return getMutationsEncoder(session).hasContent(session);
  }

  private boolean hasMeritsAndFlaws(ReportSession session) {
    return encoderRegistry.hasEncoder(EncoderIds.MERITS_AND_FLAWS, session) && getMeritsAndFlawsEncoder(session).hasContent(
            session);
  }

  private ContentEncoder getMeritsAndFlawsEncoder(ReportSession session) {
    return encoderRegistry.createEncoder(getResources(), session, EncoderIds.MERITS_AND_FLAWS);
  }

  private float encodeMutations(SheetGraphics graphics, ReportSession session, float distanceFromTop, float height) throws DocumentException {
    ContentEncoder mutationsEncoder = getMutationsEncoder(session);
    return encodeFixedBox(graphics, session, mutationsEncoder, 2, 1, distanceFromTop, height);
  }

  private ContentEncoder getMutationsEncoder(ReportSession session) {
    return encoderRegistry.createEncoder(getResources(), session, EncoderIds.MUTATIONS);
  }

  private float encodeMeritsAndFlaws(SheetGraphics graphics, ReportSession session, float distanceFromTop, float height) throws DocumentException {
    ContentEncoder encoder = getMeritsAndFlawsEncoder(session);
    return encodeFixedBox(graphics, session, encoder, 2, 1, distanceFromTop, height);
  }

  private float encodeAdditional(SheetGraphics graphics, ReportSession session, float distanceFromTop, float bottom) throws DocumentException {
    float increment = 0;
    for (IVariableContentEncoder encoder : getPartEncoder().getAdditionalFirstPageEncoders()) {
      float height = encodeVariableBoxBottom(graphics, session, encoder, 3, 1, bottom, bottom - distanceFromTop - increment);
      increment += calculateBoxIncrement(height);
    }
    return increment;
  }
}

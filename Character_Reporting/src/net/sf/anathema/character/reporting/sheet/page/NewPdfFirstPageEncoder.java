package net.sf.anathema.character.reporting.sheet.page;

import net.disy.commons.core.util.StringUtilities;
import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.character.IGenericDescription;
import net.sf.anathema.character.generic.impl.traits.EssenceTemplate;
import net.sf.anathema.character.generic.traits.types.OtherTraitType;
import net.sf.anathema.character.generic.type.ICharacterType;
import net.sf.anathema.character.reporting.sheet.PdfEncodingRegistry;
import net.sf.anathema.character.reporting.sheet.common.IPdfContentBoxEncoder;
import net.sf.anathema.character.reporting.sheet.common.PdfAbilitiesEncoder;
import net.sf.anathema.character.reporting.sheet.common.PdfAttributesEncoder;
import net.sf.anathema.character.reporting.sheet.common.PdfBackgroundEncoder;
import net.sf.anathema.character.reporting.sheet.common.PdfExperienceEncoder;
import net.sf.anathema.character.reporting.sheet.common.PdfSpecialtiesEncoder;
import net.sf.anathema.character.reporting.sheet.common.PdfVirtueEncoder;
import net.sf.anathema.character.reporting.sheet.pageformat.IVoidStateFormatConstants;
import net.sf.anathema.character.reporting.sheet.pageformat.PdfPageConfiguration;
import net.sf.anathema.character.reporting.sheet.second.NewSecondEditionPersonalInfoEncoder;
import net.sf.anathema.character.reporting.util.Bounds;
import net.sf.anathema.lib.resources.IResources;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.PdfContentByte;

public class NewPdfFirstPageEncoder extends AbstractPdfPageEncoder {
  private final int essenceMax;

  public NewPdfFirstPageEncoder(IPdfPartEncoder partEncoder,
                                PdfEncodingRegistry registry,
                                IResources resources, int essenceMax,
                                PdfPageConfiguration pageConfiguration) {
    super(partEncoder, registry, resources, pageConfiguration);
    this.essenceMax = essenceMax;
  }

  public void encode(Document document, PdfContentByte directContent,
                     IGenericCharacter character,
                     IGenericDescription description) throws DocumentException {
    ICharacterType characterType = character.getTemplate().getTemplateType().getCharacterType();

    float distanceFromTop = 0;
    float firstRowHeight = encodePersonalInfo(directContent, character,
                                              description, distanceFromTop,
                                              (characterType.isExaltType() ? 60 : 50));
    distanceFromTop += calculateBoxIncrement(firstRowHeight);

    // First column - top-down
    float firstDistanceFromTop = distanceFromTop;
    float attributeHeight = encodeAttributes(directContent, character,
                                             firstDistanceFromTop, 117);
    firstDistanceFromTop += calculateBoxIncrement(attributeHeight);
    float abilityHeight = encodeAbilities(directContent, character,
                                          firstDistanceFromTop, 415);
    firstDistanceFromTop += calculateBoxIncrement(abilityHeight);
    // First column - fill in (bottom-up) with specialties
    float specialtyHeight = CONTENT_HEIGHT - firstDistanceFromTop;
    encodeSpecialties(directContent, character, firstDistanceFromTop,
                      specialtyHeight);
    // TODO: put in the specialties box

    // Second column - top-down
    float secondDistanceFromTop = distanceFromTop;
    float virtueHeight = encodeVirtues(directContent, secondDistanceFromTop,
                                       72, character);
    float virtueIncrement = calculateBoxIncrement(virtueHeight);
    secondDistanceFromTop += virtueIncrement;
    if (characterType.isExaltType()) {
      float actualGreatCurseHeight = encodeGreatCurse(directContent, character,
                                                      secondDistanceFromTop, 85);
      secondDistanceFromTop += calculateBoxIncrement(actualGreatCurseHeight);
    }
    // Second column - fill in (bottom-up) with mutations, merits & flaws, intimacies
    float secondBottom = CONTENT_HEIGHT - calculateBoxIncrement(specialtyHeight);
    float reservedHeight = (secondBottom - secondDistanceFromTop) / 4f - IVoidStateFormatConstants.PADDING;
    if (hasMutations(character)) {
      float mutationsHeight = encodeMutations(directContent, character, 2,
                                              secondBottom - reservedHeight, reservedHeight);
      secondBottom -= calculateBoxIncrement(mutationsHeight);
    }
    if (hasMeritsAndFlaws(character)) {
      float meritsHeight = encodeMeritsAndFlaws(directContent, character, 2,
                                                secondBottom - reservedHeight, reservedHeight);
      secondBottom -= calculateBoxIncrement(meritsHeight);
    }
    encodeIntimacies(directContent, character,
                     secondDistanceFromTop, secondBottom - secondDistanceFromTop);

    // Third column - top-down (lining up with virtues)
    float thirdDistanceFromTop = distanceFromTop;
    float dotsHeight = 30;
    encodeEssenceDots(directContent, character,
                      thirdDistanceFromTop, dotsHeight);
    encodeWillpowerDots(directContent, character,
                        thirdDistanceFromTop + virtueHeight - dotsHeight, dotsHeight);
    thirdDistanceFromTop += virtueIncrement;
    // Third column - bottom-up
    float thirdBottom = CONTENT_HEIGHT;
    float experienceHeight = encodeExperience(directContent, character,
                                              thirdBottom - 30, 30);
    float experienceIncrement = calculateBoxIncrement(experienceHeight);
    thirdBottom -= experienceIncrement;
    float languageHeight = specialtyHeight - experienceIncrement;
    languageHeight = encodeLinguistics(directContent, character,
                                       thirdBottom - languageHeight, languageHeight);
    thirdBottom -= calculateBoxIncrement(languageHeight);
    // Third column - fill in (bottom-up) with backgrounds
    encodeBackgrounds(directContent, character, thirdDistanceFromTop,
                      thirdBottom - thirdDistanceFromTop);

    encodeCopyright(directContent);
  }

  private float encodeEssenceDots(PdfContentByte directContent,
                                  IGenericCharacter character,
                                  float distanceFromTop, float height)
      throws DocumentException {
    Bounds essenceBounds = calculateBounds(3, 1, distanceFromTop, height);
    IPdfContentBoxEncoder encoder = getPartEncoder().getDotsEncoder(OtherTraitType.Essence,
                                                                    EssenceTemplate.SYSTEM_ESSENCE_MAX,
                                                                    "Essence");
    getBoxEncoder().encodeBox(directContent, encoder, character, essenceBounds);
    return height;
  }

  // TODO: Clean up; this is written as an exception, with no
  //       obvious exceptional behavior.
  private float encodePersonalInfo(PdfContentByte directContent,
                                   IGenericCharacter character,
                                   IGenericDescription description,
                                   float distanceFromTop,
                                   float height) {
    Bounds infoBounds = calculateBounds(1, 3, distanceFromTop, height);
    String name = description.getName();
    String title = StringUtilities.isNullOrTrimEmpty(name) ? getHeaderLabel("PersonalInfo") : name; //$NON-NLS-1$
    Bounds infoContentBounds = getBoxEncoder().encodeBox(directContent,
                                                         infoBounds, title);
    NewSecondEditionPersonalInfoEncoder encoder = new NewSecondEditionPersonalInfoEncoder(getBaseFont(),
                                                                                          getResources());
    encoder.encodePersonalInfos(directContent, character,
                                description, infoContentBounds);
    return height;
  }

  private float encodeAbilities(PdfContentByte directContent,
                                IGenericCharacter character,
                                float distanceFromTop, float height)
      throws DocumentException {
    Bounds boxBounds = calculateBounds(1, 1, distanceFromTop, height);
    IPdfContentBoxEncoder encoder = PdfAbilitiesEncoder.createWithCraftsOnly(getBaseFont(),
                                                                             getResources(),
                                                                             essenceMax);
    getBoxEncoder().encodeBox(directContent, encoder, character, boxBounds);
    return height;
  }

  private float encodeSpecialties(PdfContentByte directContent,
                                  IGenericCharacter character,
                                  float distanceFromTop, float height)
      throws DocumentException {
    Bounds boxBounds = calculateBounds(1, 2, distanceFromTop, height);
    IPdfContentBoxEncoder encoder = new PdfSpecialtiesEncoder(getResources(),
                                                              getBaseFont(),
                                                              11);
    getBoxEncoder().encodeBox(directContent, encoder, character, boxBounds);
    return height;
  }

  private float encodeAttributes(PdfContentByte directContent,
                                 IGenericCharacter character,
                                 float distanceFromTop, float height)
      throws DocumentException {
    Bounds attributeBounds = calculateBounds(1, 1, distanceFromTop, height);
    IPdfContentBoxEncoder encoder = new PdfAttributesEncoder(getBaseFont(),
                                                             getResources(),
                                                             essenceMax,
                                                             getPartEncoder().isEncodeAttributeAsFavorable());
    getBoxEncoder().encodeBox(directContent, encoder, character,
                              attributeBounds);
    return height;
  }

  private float encodeBackgrounds(PdfContentByte directContent,
                                  IGenericCharacter character,
                                  float distanceFromTop, float height)
      throws DocumentException {
    Bounds bounds = calculateBounds(3, 1, distanceFromTop, height);
    IPdfContentBoxEncoder contentEncoder = new PdfBackgroundEncoder(getResources(),
                                                                    getBaseFont());
    getBoxEncoder().encodeBox(directContent, contentEncoder, character, bounds);
    return height;
  }

  private float encodeVirtues(PdfContentByte directContent,
                              float distanceFromTop, float height,
                              IGenericCharacter character)
      throws DocumentException {
    Bounds bounds = calculateBounds(2, 1, distanceFromTop, height);
    IPdfContentBoxEncoder encoder = new PdfVirtueEncoder(getResources(),
                                                         getBaseFont());
    getBoxEncoder().encodeBox(directContent, encoder, character, bounds);
    return height;
  }

  private float encodeWillpowerDots(PdfContentByte directContent,
                                    IGenericCharacter character,
                                    float distanceFromTop, float height)
      throws DocumentException {
    Bounds bounds = calculateBounds(3, 1, distanceFromTop, height);
    IPdfContentBoxEncoder encoder = getPartEncoder().getDotsEncoder(OtherTraitType.Willpower,
                                                                    10, "Willpower");
    getBoxEncoder().encodeBox(directContent, encoder, character, bounds);
    return height;
  }

  private float encodeGreatCurse(PdfContentByte directContent,
                                 IGenericCharacter character,
                                 float distanceFromTop, float height)
      throws DocumentException {
    Bounds bounds = calculateBounds(2, 1, distanceFromTop, height);
    IPdfContentBoxEncoder encoder = getPartEncoder().getGreatCurseEncoder();
    if (encoder != null) {
      getBoxEncoder().encodeBox(directContent, encoder, character, bounds);
      return height;
    }
    else
      return 0;
  }

  private float encodeIntimacies(PdfContentByte directContent,
                                 IGenericCharacter character,
                                 float distanceFromTop, float height)
      throws DocumentException {
    Bounds bounds = calculateBounds(2, 1, distanceFromTop, height);
    IPdfContentBoxEncoder encoder = getPartEncoder().getIntimaciesEncoder(getRegistry());
    getBoxEncoder().encodeBox(directContent, encoder, character, bounds);
    return height;
  }

  private float encodeLinguistics(PdfContentByte directContent,
                                  IGenericCharacter character,
                                  float distanceFromTop, float height)
      throws DocumentException {
    Bounds bounds = calculateBounds(3, 1, distanceFromTop, height);
    IPdfContentBoxEncoder encoder = getRegistry().getLinguisticsEncoder();
    getBoxEncoder().encodeBox(directContent, encoder, character, bounds);
    return height;
  }

  private float encodeExperience(PdfContentByte directContent,
                                 IGenericCharacter character,
                                 float distanceFromTop, float height)
      throws DocumentException {
    Bounds bounds = calculateBounds(3, 1, distanceFromTop, height);
    IPdfContentBoxEncoder encoder = new PdfExperienceEncoder(getResources(),
                                                             getBaseFont());
    getBoxEncoder().encodeBox(directContent, encoder, character, bounds);
    return height;
  }

  private boolean hasMutations(IGenericCharacter character) {
    return getRegistry().getMutationsEncoder().hasContent(character);
  }

  private boolean hasMeritsAndFlaws(IGenericCharacter character) {
    return getRegistry().getMeritsAndFlawsEncoder().hasContent(character);
  }

  private float encodeMutations(PdfContentByte directContent,
                                IGenericCharacter character, int column,
                                float distanceFromTop, float height)
      throws DocumentException {
    Bounds bounds = calculateBounds(column, 1, distanceFromTop, height);
    IPdfContentBoxEncoder encoder = getRegistry().getMutationsEncoder();
    getBoxEncoder().encodeBox(directContent, encoder, character, bounds);
    return height;
  }

  private float encodeMeritsAndFlaws(PdfContentByte directContent,
                                     IGenericCharacter character, int column,
                                     float distanceFromTop, float height)
      throws DocumentException {
    Bounds bounds = calculateBounds(column, 1, distanceFromTop, height);
    IPdfContentBoxEncoder encoder = getRegistry().getMeritsAndFlawsEncoder();
    getBoxEncoder().encodeBox(directContent, encoder, character, bounds);
    return height;
  }
}
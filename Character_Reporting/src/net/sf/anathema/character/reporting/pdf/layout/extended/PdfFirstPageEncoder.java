package net.sf.anathema.character.reporting.pdf.layout.extended;

import com.lowagie.text.Anchor;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfContentByte;
import net.disy.commons.core.util.StringUtilities;
import net.sf.anathema.character.reporting.pdf.content.ReportContent;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.abilities.AbilitiesBoxContentEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.attributes.PdfAttributesEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.backgrounds.PdfBackgroundEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.experience.ExperienceBoxContentEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.personal.ExtendedPersonalInfoEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.virtues.VirtueBoxContentEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.willpower.SimpleWillpowerBoxContentEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.elements.Bounds;
import net.sf.anathema.character.reporting.pdf.rendering.general.PdfHorizontalLineContentEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.general.PdfTextEncodingUtilities;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.IBoxContentEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.PdfBoxEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.page.IPdfPageEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.page.IVoidStateFormatConstants;
import net.sf.anathema.character.reporting.pdf.rendering.page.PdfPageConfiguration;
import net.sf.anathema.lib.resources.IResources;

import static net.sf.anathema.character.reporting.pdf.rendering.page.IVoidStateFormatConstants.PADDING;

public class PdfFirstPageEncoder implements IPdfPageEncoder {
  public static final float CONTENT_HEIGHT = 755;
  private final IResources resources;
  private final int essenceMax;
  private final BaseFont baseFont;

  private static final float ANIMA_HEIGHT = 128;
  private final PdfPageConfiguration pageConfiguration;
  private final PdfBoxEncoder boxEncoder;
  private final ExtendedEncodingRegistry registry;
  private final IExtendedPartEncoder partEncoder;

  public PdfFirstPageEncoder(IExtendedPartEncoder partEncoder, ExtendedEncodingRegistry registry, IResources resources, int essenceMax,
    PdfPageConfiguration pageConfiguration) {
    this.partEncoder = partEncoder;
    this.baseFont = registry.getBaseFont();
    this.essenceMax = essenceMax;
    this.resources = resources;
    this.registry = registry;
    this.pageConfiguration = pageConfiguration;
    this.boxEncoder = new PdfBoxEncoder(resources, baseFont);
  }

  public void encode(Document document, PdfContentByte directContent, ReportContent content) throws

    DocumentException {
    int distanceFromTop = 0;
    final int firstRowHeight = 51;
    encodePersonalInfo(directContent, content, distanceFromTop, firstRowHeight);
    encodeEssence(directContent, content, distanceFromTop, firstRowHeight);

    distanceFromTop += firstRowHeight + PADDING;

    encodeFirstColumn(directContent, content, distanceFromTop);

    float extraBoxTop = distanceFromTop;
    int extraBoxHeight = 115;
    /*float actualOverdriveHeight = encodeOverdrive(directContent, character, distanceFromTop, 43);
    float overdriveIncrement = actualOverdriveHeight != 0 ? calculateBoxIncrement(actualOverdriveHeight) : 0;
    backgroundTop += overdriveIncrement;*/
    float actualAnimaHeight = encodeAnima(directContent, content, extraBoxTop, ANIMA_HEIGHT);
    float animaIncrement = actualAnimaHeight != 0 ? calculateBoxIncrement(actualAnimaHeight) : 0;
    extraBoxTop += actualAnimaHeight != 0 ? animaIncrement : 0;
    extraBoxHeight += actualAnimaHeight != 0 ? -3 * ANIMA_HEIGHT / 4 : ANIMA_HEIGHT;

    float virtueHeight = encodeVirtues(directContent, content, distanceFromTop, 72);
    float virtueIncrement = calculateBoxIncrement(virtueHeight);
    distanceFromTop += virtueIncrement;
    float estimatedGreatCurseHeight = actualAnimaHeight - virtueHeight - IVoidStateFormatConstants.PADDING;
    float actualGreatCurseHeight = encodeGreatCurse(directContent, content, distanceFromTop, estimatedGreatCurseHeight);
    float greatCurseIncrement = actualGreatCurseHeight != 0 ? calculateBoxIncrement(actualGreatCurseHeight) : 0;
    distanceFromTop += greatCurseIncrement;
    extraBoxHeight += greatCurseIncrement;

    //float socialCombatHeight = encodeSocialCombatStats(directContent, character, distanceFromTop, 115);
    float willpowerHeight = encodeWillpower(directContent, content, distanceFromTop, 43);
    float willpowerIncrement = calculateBoxIncrement(willpowerHeight);
    distanceFromTop += willpowerIncrement;
    extraBoxHeight += willpowerIncrement;

    float intimaciesSpace = extraBoxHeight + animaIncrement - virtueIncrement - greatCurseIncrement - willpowerIncrement;
    float intimaciesHeight = encodeIntimacies(directContent, content, distanceFromTop, intimaciesSpace);
    distanceFromTop += calculateBoxIncrement(intimaciesHeight);

    float lastRowHeight = firstRowHeight;
    float remainingHeight = PdfFirstPageEncoder.CONTENT_HEIGHT - distanceFromTop - lastRowHeight;
    float frameHeight = remainingHeight / 2;
    float boxHeight = frameHeight - PADDING;
    int boxPosition = distanceFromTop;
    int boxId = 0;

    if (hasMutations(content)) {
      encodeMutations(directContent, content, getColumn(boxId), getPosition(boxPosition, frameHeight, boxId++), boxHeight);
    }
    if (hasMeritsAndFlaws(content)) {
      encodeMeritsAndFlaws(directContent, content, getColumn(boxId), getPosition(boxPosition, frameHeight, boxId++), boxHeight);
    }
    if (hasThaumaturgy(content)) {
      encodeThaumaturgy(directContent, content, getColumn(boxId), getPosition(boxPosition, frameHeight, boxId++), boxHeight);
    }

    //odd case... extend the background box here, I don't know what else to do
    if (boxId == 1) {
      extraBoxHeight += boxHeight + PADDING;
    }

    encodeNotes(directContent, content, boxId, boxPosition, frameHeight, boxHeight);

    //if (hasOverDrive...)
    encodeBackgrounds(directContent, content, extraBoxTop, extraBoxHeight);

    distanceFromTop += remainingHeight;
    encodeLinguistics(directContent, content, distanceFromTop, lastRowHeight);
    encodeExperience(directContent, content, distanceFromTop, lastRowHeight);

    /*float weaponryHeight = encodeWeaponry(directContent, character, distanceFromTop, partEncoder.getWeaponryHeight());
 distanceFromTop += calculateBoxIncrement(weaponryHeight);
 float armourHeight = encodeArmourAndSoak(directContent, character, distanceFromTop, 80);
 distanceFromTop += calculateBoxIncrement(armourHeight);
 float healthHeight = encodeMovementAndHealth(directContent, character, distanceFromTop, 99);
 distanceFromTop += calculateBoxIncrement(healthHeight);
 float remainingHeight = PdfFirstPageEncoder.CONTENT_HEIGHT - distanceFromTop;
 encodeCombatStats(directContent, character, distanceFromTop, remainingHeight);*/
    encodeCopyright(directContent);
  }

  private int getColumn(int boxId) {
    return boxId % 2 == 1 ? 3 : 2;
  }

  private float getPosition(int position, float height, int id) {
    return position + (id > 1 ? height : 0);
  }

  private void encodeCopyright(PdfContentByte directContent) throws DocumentException {
    int lineHeight = IVoidStateFormatConstants.COMMENT_FONT_SIZE + 2;
    Font copyrightFont = new Font(baseFont, IVoidStateFormatConstants.COMMENT_FONT_SIZE);
    float copyrightHeight = pageConfiguration.getPageHeight() - pageConfiguration.getContentHeight();
    Bounds firstColumnBounds = pageConfiguration.getFirstColumnRectangle(CONTENT_HEIGHT, copyrightHeight, 1);
    Anchor voidstatePhrase = new Anchor("Inspired by Voidstate\nhttp://www.voidstate.com", copyrightFont); //$NON-NLS-1$
    voidstatePhrase.setReference("http://www.voidstate.com"); //$NON-NLS-1$
    PdfTextEncodingUtilities.encodeText(directContent, voidstatePhrase, firstColumnBounds, lineHeight);

    // TODO: Eliminate these hard-coded copyright dates; these should be in a properties file or something.
    Anchor anathemaPhrase = new Anchor("Created with Anathema \u00A92011\nhttp://anathema.sf.net", copyrightFont); //$NON-NLS-1$
    anathemaPhrase.setReference("http://anathema.sf.net"); //$NON-NLS-1$
    Bounds anathemaBounds = pageConfiguration.getSecondColumnRectangle(CONTENT_HEIGHT, copyrightHeight, 1);
    PdfTextEncodingUtilities.encodeText(directContent, anathemaPhrase, anathemaBounds, lineHeight, Element.ALIGN_CENTER);
    Anchor whitewolfPhrase = new Anchor("Exalted \u00A92011 by White Wolf, Inc.\nhttp://www.white-wolf.com", //$NON-NLS-1$
      copyrightFont);
    whitewolfPhrase.setReference("http://www.white-wolf.com"); //$NON-NLS-1$

    Bounds whitewolfBounds = pageConfiguration.getThirdColumnRectangle(CONTENT_HEIGHT, copyrightHeight);
    PdfTextEncodingUtilities.encodeText(directContent, whitewolfPhrase, whitewolfBounds, lineHeight, Element.ALIGN_RIGHT);
  }

  private float encodeEssence(PdfContentByte directContent, ReportContent content, float distanceFromTop, float height) throws DocumentException {
    Bounds essenceBounds = pageConfiguration.getThirdColumnRectangle(distanceFromTop, height);
    IBoxContentEncoder encoder = partEncoder.getEssenceEncoder();
    boxEncoder.encodeBox(content, directContent, encoder, essenceBounds);
    return height;
  }

  private String getHeaderLabel(String key) {
    return resources.getString("Sheet.Header." + key); //$NON-NLS-1$
  }

  private void encodePersonalInfo(PdfContentByte directContent, ReportContent content, int distanceFromTop, final int firstRowHeight) {
    Bounds infoBounds = pageConfiguration.getFirstColumnRectangle(distanceFromTop, firstRowHeight, 2);
    String name = content.getDescription().getName();
    String title = StringUtilities.isNullOrTrimEmpty(name) ? getHeaderLabel("PersonalInfo") : name; //$NON-NLS-1$
    Bounds infoContentBounds = boxEncoder.encodeBox(directContent, infoBounds, title);
    encodePersonalInfos(directContent, content, infoContentBounds);
  }

  private void encodeFirstColumn(PdfContentByte directContent, ReportContent content, int distanceFromTop) throws DocumentException {
    float attributeHeight = encodeAttributes(directContent, content, distanceFromTop);
    encodeAbilities(directContent, content, distanceFromTop + attributeHeight + PADDING);
  }

  private void encodeAbilities(PdfContentByte directContent, ReportContent content, float distanceFromTop) throws DocumentException {
    float abilitiesHeight = CONTENT_HEIGHT - distanceFromTop;
    Bounds boxBounds = pageConfiguration.getFirstColumnRectangle(distanceFromTop, abilitiesHeight, 1);
    IBoxContentEncoder encoder = AbilitiesBoxContentEncoder.createWithCraftsAndSpecialties(baseFont, resources, essenceMax, 9, 9);
    boxEncoder.encodeBox(content, directContent, encoder, boxBounds);
  }

  private float encodeAttributes(PdfContentByte directContent, ReportContent content, int distanceFromTop) throws DocumentException {
    float attributeHeight = 128;
    Bounds attributeBounds = pageConfiguration.getFirstColumnRectangle(distanceFromTop, attributeHeight, 1);
    IBoxContentEncoder encoder = new PdfAttributesEncoder(baseFont, resources, essenceMax, partEncoder.isEncodeAttributeAsFavorable());
    boxEncoder.encodeBox(content, directContent, encoder, attributeBounds);
    return attributeHeight;
  }

  private float calculateBoxIncrement(float height) {
    return height + IVoidStateFormatConstants.PADDING;
  }

  private float encodeAnima(PdfContentByte directContent, ReportContent content, float distanceFromTop, float height) throws DocumentException {
    Bounds animaBounds = pageConfiguration.getThirdColumnRectangle(distanceFromTop, height);
    IBoxContentEncoder encoder = partEncoder.getAnimaEncoder();
    if (encoder != null) {
      boxEncoder.encodeBox(content, directContent, encoder, animaBounds);
      return ANIMA_HEIGHT;
    }
    return 0;
  }

  private float encodeBackgrounds(PdfContentByte directContent, ReportContent content, float distanceFromTop,
    float height) throws DocumentException {
    Bounds bounds = pageConfiguration.getThirdColumnRectangle(distanceFromTop, height);
    IBoxContentEncoder contentEncoder = new PdfBackgroundEncoder(resources, baseFont);
    boxEncoder.encodeBox(content, directContent, contentEncoder, bounds);
    return height;
  }

  private void encodePersonalInfos(PdfContentByte directContent, ReportContent content, Bounds infoBounds) {
    ExtendedPersonalInfoEncoder encoder = new ExtendedPersonalInfoEncoder(baseFont, resources);
    encoder.encodePersonalInfos(directContent, content, infoBounds);
  }

  private float encodeVirtues(PdfContentByte directContent, ReportContent content, float distanceFromTop, float height) throws DocumentException {
    Bounds bounds = pageConfiguration.getSecondColumnRectangle(distanceFromTop, height, 1);
    IBoxContentEncoder encoder = new VirtueBoxContentEncoder();
    boxEncoder.encodeBox(content, directContent, encoder, bounds);
    return height;
  }

  private float encodeWillpower(PdfContentByte directContent, ReportContent content, float distanceFromTop, float height) throws DocumentException {
    Bounds willpowerBounds = pageConfiguration.getSecondColumnRectangle(distanceFromTop, height, 1);
    boxEncoder.encodeBox(content, directContent, new SimpleWillpowerBoxContentEncoder(baseFont), willpowerBounds);
    return height;
  }

  private float encodeGreatCurse(PdfContentByte directContent, ReportContent content, float distanceFromTop, float height) throws DocumentException {
    Bounds bounds = pageConfiguration.getSecondColumnRectangle(distanceFromTop, height, 1);
    IBoxContentEncoder encoder = partEncoder.getGreatCurseEncoder();
    if (encoder != null) {
      boxEncoder.encodeBox(content, directContent, encoder, bounds);
      return height;
    }
    else {
      return 0;
    }
  }

  /*private float encodeOverdrive(
          PdfContentByte directContent,
          IGenericCharacter character,
          IGenericDescription description,
          float distanceFromTop,
          float height) throws DocumentException {
        Bounds bounds = pageConfiguration.getSecondColumnRectangle(distanceFromTop, height, 1);
        IPdfContentBoxEncoder encoder = partEncoder.getOverdriveEncoder();
        if (encoder != null)
        {
            boxEncoder.encodeBox(directContent, encoder, content, bounds);
            return height;
        }
        else
            return 0;
      }*/

  private float encodeIntimacies(PdfContentByte directContent, ReportContent content, float distanceFromTop, float height) throws DocumentException {
    Bounds bounds = pageConfiguration.getSecondColumnRectangle(distanceFromTop, height, 1);
    IBoxContentEncoder encoder = partEncoder.getIntimaciesEncoder(registry);
    boxEncoder.encodeBox(content, directContent, encoder, bounds);
    return height;
  }

  private void encodeLinguistics(PdfContentByte directContent, ReportContent content, float distanceFromTop, float height) throws DocumentException {
    Bounds bounds = pageConfiguration.getSecondColumnRectangle(distanceFromTop, height, 1);
    IBoxContentEncoder encoder = registry.getLinguisticsEncoder();
    boxEncoder.encodeBox(content, directContent, encoder, bounds);
  }

  private void encodeExperience(PdfContentByte directContent, ReportContent content, float distanceFromTop, float height) throws DocumentException {
    Bounds bounds = pageConfiguration.getThirdColumnRectangle(distanceFromTop, height);
    IBoxContentEncoder encoder = new ExperienceBoxContentEncoder();
    boxEncoder.encodeBox(content, directContent, encoder, bounds);
  }

  private void encodeNotes(PdfContentByte directContent, ReportContent content, int boxId, float distanceFromTop, float frameHeight,
    float height) throws DocumentException {
    Bounds bounds = null;
    int columns = 1;
    switch (boxId) {
      case 0:
        bounds = pageConfiguration.getSecondColumnRectangle(distanceFromTop, 2 * height, 2);
        break;
      case 1:
      case 2:
        bounds = pageConfiguration.getSecondColumnRectangle(distanceFromTop + frameHeight, height, 2);
        columns = 2;
        break;
      case 3:
        bounds = pageConfiguration.getThirdColumnRectangle(distanceFromTop + frameHeight, height);
        break;
      case 4:
        return;
    }
    IBoxContentEncoder encoder = new PdfHorizontalLineContentEncoder(columns, "Notes");
    boxEncoder.encodeBox(content, directContent, encoder, bounds);
  }

  private boolean hasMutations(ReportContent content) {
    return registry.getMutationsEncoder().hasContent(content);
  }

  private boolean hasMeritsAndFlaws(ReportContent content) {
    return registry.getMeritsAndFlawsEncoder().hasContent(content);
  }

  private boolean hasThaumaturgy(ReportContent content) {
    return registry.getThaumaturgyEncoder().hasContent(content);
  }

  private void encodeMutations(PdfContentByte directContent, ReportContent content, int column, float distanceFromTop,
    float height) throws DocumentException {
    Bounds bounds = column == 2 ? pageConfiguration.getSecondColumnRectangle(distanceFromTop, height,
      1) : pageConfiguration.getThirdColumnRectangle(distanceFromTop, height);
    IBoxContentEncoder encoder = registry.getMutationsEncoder();
    boxEncoder.encodeBox(content, directContent, encoder, bounds);
  }

  private void encodeMeritsAndFlaws(PdfContentByte directContent, ReportContent content, int column, float distanceFromTop,
    float height) throws DocumentException {
    Bounds bounds = column == 2 ? pageConfiguration.getSecondColumnRectangle(distanceFromTop, height,
      1) : pageConfiguration.getThirdColumnRectangle(distanceFromTop, height);
    IBoxContentEncoder encoder = registry.getMeritsAndFlawsEncoder();
    boxEncoder.encodeBox(content, directContent, encoder, bounds);
  }

  private void encodeThaumaturgy(PdfContentByte directContent, ReportContent content, int column, float distanceFromTop,
    float height) throws DocumentException {
    Bounds bounds = column == 2 ? pageConfiguration.getSecondColumnRectangle(distanceFromTop, height,
      1) : pageConfiguration.getThirdColumnRectangle(distanceFromTop, height);
    IBoxContentEncoder encoder = registry.getThaumaturgyEncoder();
    boxEncoder.encodeBox(content, directContent, encoder, bounds);
  }
}

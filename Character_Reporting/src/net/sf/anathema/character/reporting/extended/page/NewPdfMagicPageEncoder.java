package net.sf.anathema.character.reporting.extended.page;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.PdfContentByte;
import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.character.IGenericDescription;
import net.sf.anathema.character.generic.impl.rules.ExaltedEdition;
import net.sf.anathema.character.generic.magic.IGenericCombo;
import net.sf.anathema.character.generic.magic.IMagicStats;
import net.sf.anathema.character.generic.template.magic.ICharmTemplate;
import net.sf.anathema.character.generic.template.magic.ISpellMagicTemplate;
import net.sf.anathema.character.reporting.common.Bounds;
import net.sf.anathema.character.reporting.common.boxes.magic.PdfComboEncoder;
import net.sf.anathema.character.reporting.common.boxes.magic.PdfGenericCharmEncoder;
import net.sf.anathema.character.reporting.common.boxes.magic.PdfMagicEncoder;
import net.sf.anathema.character.reporting.common.encoder.IPdfTableEncoder;
import net.sf.anathema.character.reporting.common.encoder.IPdfVariableContentBoxEncoder;
import net.sf.anathema.character.reporting.common.pageformat.IVoidStateFormatConstants;
import net.sf.anathema.character.reporting.common.pageformat.PdfPageConfiguration;
import net.sf.anathema.character.reporting.extended.ExtendedEncodingRegistry;
import net.sf.anathema.character.reporting.extended.common.NewPdfWillpowerEncoder;
import net.sf.anathema.character.reporting.extended.common.PdfInitiationEncoder;
import net.sf.anathema.lib.resources.IResources;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NewPdfMagicPageEncoder extends AbstractPdfPageEncoder {

  public NewPdfMagicPageEncoder(IExtendedPartEncoder partEncoder, ExtendedEncodingRegistry encodingRegistry, IResources resources,
                                PdfPageConfiguration configuration) {
    super(partEncoder, encodingRegistry, resources, configuration);
  }

  public void encode(Document document, PdfContentByte directContent, IGenericCharacter character, IGenericDescription description) throws





























































                                                                                                                                    DocumentException {
    float distanceFromTop = 0;

    // Essence & Willpower
    float essenceHeight = encodeEssence(directContent, character, description, distanceFromTop, getContentHeight() - distanceFromTop);
    float willpowerHeight = encodeWillpower(directContent, character, description, distanceFromTop, 96.625f);
    distanceFromTop += calculateBoxIncrement(Math.max(essenceHeight, willpowerHeight));

    boolean restartPage = false;

    // Magic page (if necessary) [for non-Charm magics]
    ISpellMagicTemplate spellTemplate = character.getTemplate().getMagicTemplate().getSpellMagic();
    boolean needsMagic = spellTemplate.knowsSorcery(character.getLearnedCharms());
    if (!needsMagic) {
      for (IPdfTableEncoder tableEncoder : getRegistry().getAdditionalMagicEncoders()) {
        if (tableEncoder.hasContent(character)) {
          needsMagic = true;
          break;
        }
      }
    }
    if (needsMagic) {
      // Right-hand side: Magic
      encodeMagic(directContent, character, description, distanceFromTop, getContentHeight() - distanceFromTop);

      // Left-hand side: Magic Sidebars (e.g. Thaumaturgical Degrees), Initiations, Notes
      float sidebarHeight = encodeSidebars(directContent, character, description, distanceFromTop, getContentHeight() - distanceFromTop);
      if (sidebarHeight != 0) {
        distanceFromTop += calculateBoxIncrement(sidebarHeight);
      }
      float initiationHeight = encodeInitiations(directContent, character, description, distanceFromTop, getContentHeight() - distanceFromTop);
      if (initiationHeight != 0) {
        distanceFromTop += calculateBoxIncrement(initiationHeight);
      }
      encodeNotes(directContent, character, description, "EnlightenmentNotes", 1, 1, distanceFromTop, getContentHeight() - distanceFromTop, 1);

      restartPage = true;
    }

    // Charm pages (if necessary)
    ICharmTemplate charmTemplate = character.getTemplate().getMagicTemplate().getCharmTemplate();
    if (charmTemplate.canLearnCharms(character.getRules())) {
      if (restartPage) {
        document.newPage();
        distanceFromTop = 0;
        restartPage = false;
      }

      // Combos, Anima, & Generic Charms
      float animaHeight = 0;
      if (hasAnima()) {
        animaHeight = encodeAnima(directContent, character, description, distanceFromTop, 128f);
      }
      float comboHeight = encodeCombos(directContent, character, distanceFromTop, animaHeight, hasAnima());
      if (comboHeight > 0) {
        distanceFromTop += comboHeight + IVoidStateFormatConstants.PADDING;
      }

      if (character.getTemplate().getEdition() == ExaltedEdition.SecondEdition) {
        float genericCharmsHeight = encodeGenericCharms(directContent, character, description, distanceFromTop, getContentHeight() - distanceFromTop);
        if (genericCharmsHeight != 0) {
          distanceFromTop += genericCharmsHeight + IVoidStateFormatConstants.PADDING;
        }
      }

      // Charms, with overflow pages
      float remainingHeight = getPageConfiguration().getContentHeight() - distanceFromTop;
      List<IMagicStats> printCharms = PdfMagicEncoder.collectPrintCharms(character);
      encodeCharms(directContent, printCharms, distanceFromTop, remainingHeight);
      while (!printCharms.isEmpty()) {
        encodeCopyright(directContent);
        document.newPage();
        encodeCharms(directContent, printCharms, 0, getPageConfiguration().getContentHeight());
      }
      encodeCopyright(directContent);
    }
  }

  private float encodeEssence(PdfContentByte directContent, IGenericCharacter character, IGenericDescription description, float distanceFromTop, float height) throws DocumentException {
    return encodeVariableBox(directContent, character, description, (IPdfVariableContentBoxEncoder) getPartEncoder().getEssenceEncoder(), 1, 2, distanceFromTop, height);
  }

  private float encodeWillpower(PdfContentByte directContent, IGenericCharacter character, IGenericDescription description, float distanceFromTop, float height) throws DocumentException {
    return encodeFixedBox(directContent, character, description, new NewPdfWillpowerEncoder(getResources(), getBaseFont(), getBaseFont()), 3, 1, distanceFromTop, height);
  }

  private float encodeCombos(PdfContentByte directContent, IGenericCharacter character, float distanceFromTop, float fixedHeight, boolean sharesRow) throws DocumentException {
    List<IGenericCombo> combos = new ArrayList<IGenericCombo>(Arrays.asList(character.getCombos()));
    PdfComboEncoder comboEncoder = new PdfComboEncoder(getResources(), getBaseFont());
    if (sharesRow) {
      float rowHeight = comboEncoder.encodeFixedCombos(directContent, combos, calculateBounds(1, 2, distanceFromTop, fixedHeight));
      float rowIncrement = calculateBoxIncrement(rowHeight);
      distanceFromTop += rowIncrement;

      Bounds restOfPage = calculateBounds(1, 3, distanceFromTop, getPageConfiguration().getContentHeight() - distanceFromTop);
      float overflowHeight = comboEncoder.encodeCombos(directContent, combos, restOfPage, true);
      if (overflowHeight > 0) {
        return calculateBoxIncrement(rowHeight) + overflowHeight;
      }
      else {
        return rowHeight;
      }
    }
    else {
      Bounds restOfPage = calculateBounds(1, 3, distanceFromTop, getPageConfiguration().getContentHeight() - distanceFromTop);
      return comboEncoder.encodeCombos(directContent, combos, restOfPage, false);
    }
  }

  private float encodeAnima(PdfContentByte directContent, IGenericCharacter character, IGenericDescription description, float distanceFromTop, float height) throws DocumentException {
    return encodeFixedBox(directContent, character, description, getPartEncoder().getAnimaEncoder(), 3, 1, distanceFromTop, height);
  }

  private float encodeGenericCharms(PdfContentByte directContent, IGenericCharacter character, IGenericDescription description, float distanceFromTop, float maxHeight) throws DocumentException {
    IPdfVariableContentBoxEncoder encoder = new PdfGenericCharmEncoder(getResources(), getBaseFont());
    if (encoder.hasContent(character)) {
      return encodeVariableBox(directContent, character, description, encoder, 1, 3, distanceFromTop, maxHeight);
    }
    else {
      return 0f;
    }
  }

  private float encodeMagic(PdfContentByte directContent, IGenericCharacter character, IGenericDescription description, float distanceFromTop, float height) throws DocumentException {
    return encodeFixedBox(directContent, character, description, new PdfMagicEncoder(getResources(), getBaseFont(), PdfMagicEncoder.collectPrintSpells(character), getRegistry().getAdditionalMagicEncoders(), true, "Magic"), //$NON-NLS-1$
                          2, 2, distanceFromTop, height);
  }

  private float encodeSidebars(PdfContentByte directContent, IGenericCharacter character, IGenericDescription description, float distanceFromTop, float maxHeight) throws DocumentException {
    float height = 0;
    for (IPdfVariableContentBoxEncoder sidebar : getRegistry().getAdditionalMagicSidebarEncoders()) {
      float sidebarHeight = encodeVariableBox(directContent, character, description, sidebar, 1, 1, distanceFromTop, maxHeight - height);
      if (sidebarHeight != 0) {
        height += calculateBoxIncrement(sidebarHeight);
        distanceFromTop += calculateBoxIncrement(sidebarHeight);
      }
    }

    if (height != 0) {
      height = removeBoxIncrement(height);
    }
    return height;
  }

  private float encodeInitiations(PdfContentByte directContent, IGenericCharacter character, IGenericDescription description, float distanceFromTop, float maxHeight) throws DocumentException {
    return encodeVariableBox(directContent, character, description, new PdfInitiationEncoder(getResources(), getBaseFont()), 1, 1, distanceFromTop, maxHeight);
  }

  private float encodeCharms(PdfContentByte directContent, List<IMagicStats> printCharms, float distanceFromTop, float height) throws DocumentException {
    return encodeFixedBox(directContent, null, null, new PdfMagicEncoder(getResources(), getBaseFont(), printCharms), 1, 3, distanceFromTop, height);
  }

  private boolean hasAnima() {
    return getPartEncoder().getAnimaEncoder() != null;
  }
}

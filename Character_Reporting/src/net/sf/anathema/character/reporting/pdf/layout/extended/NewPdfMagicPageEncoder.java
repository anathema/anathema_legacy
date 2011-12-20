package net.sf.anathema.character.reporting.pdf.layout.extended;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.PdfContentByte;
import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.impl.rules.ExaltedEdition;
import net.sf.anathema.character.generic.magic.IGenericCombo;
import net.sf.anathema.character.generic.magic.IMagicStats;
import net.sf.anathema.character.generic.template.magic.ICharmTemplate;
import net.sf.anathema.character.generic.template.magic.ISpellMagicTemplate;
import net.sf.anathema.character.reporting.pdf.content.ReportContent;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.initiation.PdfInitiationEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.magic.PdfComboEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.magic.PdfGenericCharmEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.magic.PdfMagicEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.willpower.ExtendedWillpowerBoxContentEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.elements.Bounds;
import net.sf.anathema.character.reporting.pdf.rendering.general.SheetGraphics;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.IVariableBoxContentEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.general.table.ITableEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.page.IVoidStateFormatConstants;
import net.sf.anathema.character.reporting.pdf.rendering.page.PdfPageConfiguration;
import net.sf.anathema.lib.resources.IResources;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NewPdfMagicPageEncoder extends AbstractPdfPageEncoder {

  public NewPdfMagicPageEncoder(IExtendedPartEncoder partEncoder, ExtendedEncodingRegistry encodingRegistry, IResources resources,
    PdfPageConfiguration configuration) {
    super(partEncoder, encodingRegistry, resources, configuration);
  }

  public void encode(Document document, SheetGraphics graphics, ReportContent content) throws DocumentException {
    float distanceFromTop = 0;
    IGenericCharacter character = content.getCharacter();
    // Essence & Willpower
    float essenceHeight = encodeEssence(graphics, content, distanceFromTop, getContentHeight() - distanceFromTop);
    float willpowerHeight = encodeWillpower(graphics, content, distanceFromTop, 96.625f);
    distanceFromTop += calculateBoxIncrement(Math.max(essenceHeight, willpowerHeight));

    boolean restartPage = false;

    // Magic page (if necessary) [for non-Charm magics]
    ISpellMagicTemplate spellTemplate = character.getTemplate().getMagicTemplate().getSpellMagic();
    boolean needsMagic = spellTemplate.knowsSorcery(character.getLearnedCharms());
    if (!needsMagic) {
      for (ITableEncoder tableEncoder : getRegistry().getAdditionalMagicEncoders()) {
        if (tableEncoder.hasContent(content)) {
          needsMagic = true;
          break;
        }
      }
    }
    if (needsMagic) {
      // Right-hand side: Magic
      encodeMagic(graphics, content, distanceFromTop, getContentHeight() - distanceFromTop);

      // Left-hand side: Magic Sidebars (e.g. Thaumaturgical Degrees), Initiations, Notes
      float sidebarHeight = encodeSidebars(graphics, content, distanceFromTop, getContentHeight() - distanceFromTop);
      if (sidebarHeight != 0) {
        distanceFromTop += calculateBoxIncrement(sidebarHeight);
      }
      float initiationHeight = encodeInitiations(graphics, content, distanceFromTop, getContentHeight() - distanceFromTop);
      if (initiationHeight != 0) {
        distanceFromTop += calculateBoxIncrement(initiationHeight);
      }
      encodeNotes(graphics, content, "EnlightenmentNotes", 1, 1, distanceFromTop, getContentHeight() - distanceFromTop, 1);

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
        animaHeight = encodeAnima(graphics, content, distanceFromTop, 128f);
      }
      float comboHeight = encodeCombos(graphics, character, distanceFromTop, animaHeight, hasAnima());
      if (comboHeight > 0) {
        distanceFromTop += comboHeight + IVoidStateFormatConstants.PADDING;
      }

      if (character.getTemplate().getEdition() == ExaltedEdition.SecondEdition) {
        float genericCharmsHeight = encodeGenericCharms(graphics, content, distanceFromTop, getContentHeight() - distanceFromTop);
        if (genericCharmsHeight != 0) {
          distanceFromTop += genericCharmsHeight + IVoidStateFormatConstants.PADDING;
        }
      }

      // Charms, with overflow pages
      float remainingHeight = getPageConfiguration().getContentHeight() - distanceFromTop;
      List<IMagicStats> printCharms = PdfMagicEncoder.collectPrintCharms(content);
      encodeCharms(graphics, content, printCharms, distanceFromTop, remainingHeight);
      while (!printCharms.isEmpty()) {
        encodeCopyright(graphics);
        document.newPage();
        encodeCharms(graphics, content, printCharms, 0, getPageConfiguration().getContentHeight());
      }
      encodeCopyright(graphics);
    }
  }

  private float encodeEssence(SheetGraphics graphics, ReportContent content, float distanceFromTop, float height) throws DocumentException {
    return encodeVariableBox(graphics, content, (IVariableBoxContentEncoder) getPartEncoder().getEssenceEncoder(), 1, 2, distanceFromTop,
      height);
  }

  private float encodeWillpower(SheetGraphics graphics, ReportContent content, float distanceFromTop, float height) throws DocumentException {
    return encodeFixedBox(graphics, content, new ExtendedWillpowerBoxContentEncoder(), 3, 1,
      distanceFromTop, height);
  }

  private float encodeCombos(SheetGraphics graphics, IGenericCharacter character, float distanceFromTop, float fixedHeight,
    boolean sharesRow) throws DocumentException {
    List<IGenericCombo> combos = new ArrayList<IGenericCombo>(Arrays.asList(character.getCombos()));
    PdfComboEncoder comboEncoder = new PdfComboEncoder(getResources(), getBaseFont());
    if (sharesRow) {
      float rowHeight = comboEncoder.encodeFixedCombos(graphics, combos, calculateBounds(1, 2, distanceFromTop, fixedHeight));
      float rowIncrement = calculateBoxIncrement(rowHeight);
      distanceFromTop += rowIncrement;

      Bounds restOfPage = calculateBounds(1, 3, distanceFromTop, getPageConfiguration().getContentHeight() - distanceFromTop);
      float overflowHeight = comboEncoder.encodeCombos(graphics, combos, restOfPage, true);
      if (overflowHeight > 0) {
        return calculateBoxIncrement(rowHeight) + overflowHeight;
      }
      else {
        return rowHeight;
      }
    }
    else {
      Bounds restOfPage = calculateBounds(1, 3, distanceFromTop, getPageConfiguration().getContentHeight() - distanceFromTop);
      return comboEncoder.encodeCombos(graphics, combos, restOfPage, false);
    }
  }

  private float encodeAnima(SheetGraphics graphics, ReportContent content, float distanceFromTop, float height) throws DocumentException {
    return encodeFixedBox(graphics, content, getPartEncoder().getAnimaEncoder(), 3, 1, distanceFromTop, height);
  }

  private float encodeGenericCharms(SheetGraphics graphics, ReportContent content, float distanceFromTop,
    float maxHeight) throws DocumentException {
    IVariableBoxContentEncoder encoder = new PdfGenericCharmEncoder(getResources(), getBaseFont());
    if (encoder.hasContent(content)) {
      return encodeVariableBox(graphics, content, encoder, 1, 3, distanceFromTop, maxHeight);
    }
    else {
      return 0f;
    }
  }

  private float encodeMagic(SheetGraphics graphics, ReportContent content, float distanceFromTop, float height) throws DocumentException {
    return encodeFixedBox(graphics, content, new PdfMagicEncoder(getResources(), getBaseFont(), PdfMagicEncoder.collectPrintSpells(content),
      getRegistry().getAdditionalMagicEncoders(), true, "Magic"), //$NON-NLS-1$
      2, 2, distanceFromTop, height);
  }

  private float encodeSidebars(SheetGraphics graphics, ReportContent content, float distanceFromTop,
    float maxHeight) throws DocumentException {
    float height = 0;
    for (IVariableBoxContentEncoder sidebar : getRegistry().getAdditionalMagicSidebarEncoders()) {
      float sidebarHeight = encodeVariableBox(graphics, content, sidebar, 1, 1, distanceFromTop, maxHeight - height);
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

  private float encodeInitiations(SheetGraphics graphics, ReportContent content, float distanceFromTop,
    float maxHeight) throws DocumentException {
    return encodeVariableBox(graphics, content, new PdfInitiationEncoder(getResources(), getBaseFont()), 1, 1, distanceFromTop, maxHeight);
  }

  private float encodeCharms(SheetGraphics graphics, ReportContent content, List<IMagicStats> printCharms, float distanceFromTop,
    float height) throws DocumentException {
    return encodeFixedBox(graphics, content, new PdfMagicEncoder(getResources(), getBaseFont(), printCharms), 1, 3, distanceFromTop, height);
  }

  private boolean hasAnima() {
    return getPartEncoder().getAnimaEncoder() != null;
  }
}

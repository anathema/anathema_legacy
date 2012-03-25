package net.sf.anathema.character.reporting.pdf.layout.extended;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.impl.rules.ExaltedEdition;
import net.sf.anathema.character.generic.magic.IGenericCombo;
import net.sf.anathema.character.generic.magic.IMagicStats;
import net.sf.anathema.character.generic.template.magic.ICharmTemplate;
import net.sf.anathema.character.generic.template.magic.ISpellMagicTemplate;
import net.sf.anathema.character.reporting.pdf.content.ReportSession;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.initiation.PdfInitiationEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.magic.ExtendedComboEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.magic.ExtendedMagicEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.magic.GenericCharmEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.willpower.ExtendedWillpowerEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.extent.Bounds;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.IVariableContentEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.graphics.SheetGraphics;
import net.sf.anathema.character.reporting.pdf.rendering.page.IVoidStateFormatConstants;
import net.sf.anathema.character.reporting.pdf.rendering.page.PageConfiguration;
import net.sf.anathema.lib.resources.IResources;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ExtendedMagicPageEncoder extends AbstractExtendedPdfPageEncoder {

  public ExtendedMagicPageEncoder(IExtendedPartEncoder partEncoder, IResources resources,
          PageConfiguration configuration) {
    super(partEncoder, resources, configuration);
  }

  @Override
  public void encode(Document document, SheetGraphics graphics, ReportSession session) throws DocumentException {
    float distanceFromTop = 0;
    IGenericCharacter character = session.getCharacter();
    // Essence & Willpower
    float essenceHeight = encodeEssence(graphics, session, distanceFromTop, getContentHeight() - distanceFromTop);
    float willpowerHeight = encodeWillpower(graphics, session, distanceFromTop, 96.625f);
    distanceFromTop += calculateBoxIncrement(Math.max(essenceHeight, willpowerHeight));

    boolean restartPage = false;

    // Magic page (if necessary) [for non-Charm magics]
    ISpellMagicTemplate spellTemplate = character.getTemplate().getMagicTemplate().getSpellMagic();
    boolean needsMagic = spellTemplate.knowsSorcery(character.getLearnedCharms());
     if (needsMagic) {
      // Right-hand side: Magic
      encodeMagic(graphics, session, distanceFromTop, getContentHeight() - distanceFromTop);

      // Left-hand side: Magic Sidebars (e.g. Thaumaturgical Degrees), Initiations, Notes
      float sidebarHeight = encodeSidebars(graphics, session, distanceFromTop, getContentHeight() - distanceFromTop);
      if (sidebarHeight != 0) {
        distanceFromTop += calculateBoxIncrement(sidebarHeight);
      }
      float initiationHeight =
              encodeInitiations(graphics, session, distanceFromTop, getContentHeight() - distanceFromTop);
      if (initiationHeight != 0) {
        distanceFromTop += calculateBoxIncrement(initiationHeight);
      }
      encodeNotes(graphics, session, "EnlightenmentNotes", 1, 1, distanceFromTop, getContentHeight() - distanceFromTop,
              1);

      restartPage = true;
    }

    // Charm pages (if necessary)
    ICharmTemplate charmTemplate = character.getTemplate().getMagicTemplate().getCharmTemplate();
    if (charmTemplate.canLearnCharms()) {
      if (restartPage) {
        document.newPage();
        distanceFromTop = 0;
        restartPage = false;
      }

      // Combos, Anima, & Generic Charms
      float animaHeight = 0;
      if (hasAnima(session)) {
        animaHeight = encodeAnima(graphics, session, distanceFromTop, 128f);
      }
      float comboHeight = encodeCombos(graphics, character, distanceFromTop, animaHeight, hasAnima(session));
      if (comboHeight > 0) {
        distanceFromTop += comboHeight + IVoidStateFormatConstants.PADDING;
      }

      if (character.getTemplate().getEdition() == ExaltedEdition.SecondEdition) {
        float genericCharmsHeight =
                encodeGenericCharms(graphics, session, distanceFromTop, getContentHeight() - distanceFromTop);
        if (genericCharmsHeight != 0) {
          distanceFromTop += genericCharmsHeight + IVoidStateFormatConstants.PADDING;
        }
      }

      // Charms, with overflow pages
      float remainingHeight = getPageConfiguration().getContentHeight() - distanceFromTop;
      List<IMagicStats> printCharms = ExtendedMagicEncoder.collectPrintCharms(session);
      encodeCharms(graphics, session, printCharms, distanceFromTop, remainingHeight);
      while (!printCharms.isEmpty()) {
        encodeCopyright(graphics);
        document.newPage();
        encodeCharms(graphics, session, printCharms, 0, getPageConfiguration().getContentHeight());
      }
      encodeCopyright(graphics);
    }
  }

  private float encodeEssence(SheetGraphics graphics, ReportSession session, float distanceFromTop, float height)
          throws DocumentException {
    return encodeVariableBox(graphics, session, (IVariableContentEncoder) getPartEncoder().getEssenceEncoder(), 1, 2,
            distanceFromTop, height);
  }

  private float encodeWillpower(SheetGraphics graphics, ReportSession session, float distanceFromTop, float height)
          throws DocumentException {
    return encodeFixedBox(graphics, session, new ExtendedWillpowerEncoder(), 3, 1, distanceFromTop, height);
  }

  private float encodeCombos(SheetGraphics graphics, IGenericCharacter character, float distanceFromTop,
          float fixedHeight, boolean sharesRow) throws DocumentException {
    List<IGenericCombo> combos = new ArrayList<IGenericCombo>(Arrays.asList(character.getCombos()));
    ExtendedComboEncoder comboEncoder = new ExtendedComboEncoder(getResources());
    if (sharesRow) {
      float rowHeight =
              comboEncoder.encodeFixedCombos(graphics, combos, calculateBounds(1, 2, distanceFromTop, fixedHeight));
      float rowIncrement = calculateBoxIncrement(rowHeight);
      distanceFromTop += rowIncrement;

      Bounds restOfPage =
              calculateBounds(1, 3, distanceFromTop, getPageConfiguration().getContentHeight() - distanceFromTop);
      float overflowHeight = comboEncoder.encodeCombos(graphics, combos, restOfPage, true);
      if (overflowHeight > 0) {
        return calculateBoxIncrement(rowHeight) + overflowHeight;
      } else {
        return rowHeight;
      }
    } else {
      Bounds restOfPage =
              calculateBounds(1, 3, distanceFromTop, getPageConfiguration().getContentHeight() - distanceFromTop);
      return comboEncoder.encodeCombos(graphics, combos, restOfPage, false);
    }
  }

  private float encodeAnima(SheetGraphics graphics, ReportSession session, float distanceFromTop, float height)
          throws DocumentException {
    return encodeFixedBox(graphics, session, getPartEncoder().getAnimaEncoder(session), 3, 1, distanceFromTop, height);
  }

  private float encodeGenericCharms(SheetGraphics graphics, ReportSession session, float distanceFromTop,
          float maxHeight) throws DocumentException {
    IVariableContentEncoder encoder = new GenericCharmEncoder(getResources());
    if (encoder.hasContent(session)) {
      return encodeVariableBox(graphics, session, encoder, 1, 3, distanceFromTop, maxHeight);
    } else {
      return 0f;
    }
  }

  private float encodeMagic(SheetGraphics graphics, ReportSession session, float distanceFromTop, float height)
          throws DocumentException {
    return encodeFixedBox(graphics, session,
            new ExtendedMagicEncoder(getResources(), ExtendedMagicEncoder.collectPrintSpells(session),true, "Magic"),
            2, 2, distanceFromTop, height);
  }

  private float encodeSidebars(SheetGraphics graphics, ReportSession session, float distanceFromTop, float maxHeight)
          throws DocumentException {
    float height = 0;
    for (IVariableContentEncoder sidebar : getAdditionalMagicSidebarEncoders()) {
      float sidebarHeight = encodeVariableBox(graphics, session, sidebar, 1, 1, distanceFromTop, maxHeight - height);
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

  private List<IVariableContentEncoder> getAdditionalMagicSidebarEncoders() {
    //Returned an empty list from the registry
    //return getRegistry().getAdditionalMagicSidebarEncoders();
    return Collections.emptyList();
  }

  private float encodeInitiations(SheetGraphics graphics, ReportSession session, float distanceFromTop, float maxHeight)
          throws DocumentException {
    return encodeVariableBox(graphics, session, new PdfInitiationEncoder(getResources(), graphics), 1, 1,
            distanceFromTop, maxHeight);
  }

  private float encodeCharms(SheetGraphics graphics, ReportSession session, List<IMagicStats> printCharms,
          float distanceFromTop, float height) throws DocumentException {
    return encodeFixedBox(graphics, session, new ExtendedMagicEncoder(getResources(), printCharms), 1, 3, distanceFromTop,
            height);
  }

  private boolean hasAnima(ReportSession session) {
    return getPartEncoder().getAnimaEncoder(session) != null;
  }
}

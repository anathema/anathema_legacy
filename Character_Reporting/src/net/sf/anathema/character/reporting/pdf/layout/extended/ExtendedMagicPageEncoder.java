package net.sf.anathema.character.reporting.pdf.layout.extended;

import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.template.magic.ICharmTemplate;
import net.sf.anathema.character.generic.template.magic.ISpellMagicTemplate;
import net.sf.anathema.character.reporting.pdf.content.ReportSession;
import net.sf.anathema.character.reporting.pdf.content.magic.CharmsOnlyContent;
import net.sf.anathema.character.reporting.pdf.layout.Sheet;
import net.sf.anathema.character.reporting.pdf.layout.SheetPage;
import net.sf.anathema.character.reporting.pdf.layout.field.LayoutField;
import net.sf.anathema.character.reporting.pdf.rendering.EncoderIds;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.EncoderRegistry;
import net.sf.anathema.character.reporting.pdf.rendering.general.CopyrightEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.graphics.SheetGraphics;
import net.sf.anathema.character.reporting.pdf.rendering.page.PageConfiguration;
import net.sf.anathema.character.reporting.pdf.rendering.page.PageEncoder;

import static net.sf.anathema.character.reporting.pdf.rendering.EncoderIds.ANIMA;
import static net.sf.anathema.character.reporting.pdf.rendering.EncoderIds.CHARMS_ONLY;
import static net.sf.anathema.character.reporting.pdf.rendering.EncoderIds.COMBOS;
import static net.sf.anathema.character.reporting.pdf.rendering.EncoderIds.ESSENCE_EXTENDED;
import static net.sf.anathema.character.reporting.pdf.rendering.EncoderIds.ESSENCE_REGAIN;
import static net.sf.anathema.character.reporting.pdf.rendering.EncoderIds.GENERIC_CHARMS;
import static net.sf.anathema.character.reporting.pdf.rendering.EncoderIds.SPELLS_ONLY;
import static net.sf.anathema.character.reporting.pdf.rendering.EncoderIds.WILLPOWER_EXTENDED;

public class ExtendedMagicPageEncoder implements PageEncoder {

  public static final float FIRST_ROW_HEIGHT = 96.625f;
  public static final float COMBO_HEIGHT = 128f;
  private EncoderRegistry encoderRegistry;
  private PageConfiguration pageConfiguration;

  public ExtendedMagicPageEncoder(EncoderRegistry encoderRegistry, PageConfiguration configuration) {
    this.encoderRegistry = encoderRegistry;
    this.pageConfiguration = configuration;
  }

  @Override
  public void encode(Sheet sheet, SheetGraphics graphics, ReportSession session) {
    SheetPage page = sheet.startPortraitPage(graphics, session);
    LayoutField essence = page.place(ESSENCE_EXTENDED).atStartOf(page).withHeight(FIRST_ROW_HEIGHT).now();
    LayoutField regain = page.place(ESSENCE_REGAIN).rightOf(essence).withSameHeight().now();
    LayoutField willpower = page.place(WILLPOWER_EXTENDED).rightOf(regain).withSameHeight().now();

    if (needsMagic(session)) {
      page.place(SPELLS_ONLY).below(essence).fillToBottomOfPage().andColumnSpan(3).now();
    }
    if (canLearnCharms(session)) {
      boolean hasAnima = hasAnima(session);
      int comboColumnSpan = hasAnima ? 2 : 3;
      LayoutField combos;
      if (needsMagic(session)) {
        page = sheet.startPortraitPage(graphics, session);
        combos = page.place(COMBOS).atStartOf(page).withHeight(COMBO_HEIGHT).andColumnSpan(comboColumnSpan).now();
      } else {
        combos = page.place(COMBOS).below(essence).withHeight(COMBO_HEIGHT).andColumnSpan(comboColumnSpan).now();
      }
      if (hasAnima) {
        page.place(ANIMA).rightOf(combos).withSameHeight().now();
      }
      LayoutField genericCharms = page.place(GENERIC_CHARMS).below(combos).withPreferredHeight().andColumnSpan(3).now();
      page.place(CHARMS_ONLY).below(genericCharms).fillToBottomOfPage().andColumnSpan(3).now();
      new CopyrightEncoder(pageConfiguration).encodeCopyright(graphics);
      encodeAdditionalCharmPages(sheet, graphics, session);
    }
  }

  private boolean needsMagic(ReportSession session) {
    IGenericCharacter character = session.getCharacter();
    ISpellMagicTemplate spellTemplate = character.getTemplate().getMagicTemplate().getSpellMagic();
    return spellTemplate.knowsSorcery(character.getLearnedCharms());
  }

  private boolean canLearnCharms(ReportSession session) {
    IGenericCharacter character = session.getCharacter();
    ICharmTemplate charmTemplate = character.getTemplate().getMagicTemplate().getCharmTemplate();
    return charmTemplate.canLearnCharms();
  }

  private void encodeAdditionalCharmPages(Sheet sheet, SheetGraphics graphics, ReportSession session) {
    CharmsOnlyContent charmContent = session.createContent(CharmsOnlyContent.class);
    while (charmContent.hasUnprintedCharms()) {
      SheetPage page = sheet.startPortraitPage(graphics, session);
      page.place(EncoderIds.CHARMS_ONLY).atStartOf(page).fillToBottomOfPage().andColumnSpan(3).now();
      new CopyrightEncoder(pageConfiguration).encodeCopyright(graphics);
    }
  }
  private boolean hasAnima(ReportSession session) {
    return encoderRegistry.hasEncoder(ANIMA, session);
  }
}

package net.sf.anathema.character.reporting.pdf.layout.extended;

import com.itextpdf.text.DocumentException;
import net.sf.anathema.character.reporting.pdf.content.ReportSession;
import net.sf.anathema.character.reporting.pdf.layout.Sheet;
import net.sf.anathema.character.reporting.pdf.layout.SheetPage;
import net.sf.anathema.character.reporting.pdf.layout.field.LayoutField;
import net.sf.anathema.character.reporting.pdf.rendering.graphics.SheetGraphics;
import net.sf.anathema.character.reporting.pdf.rendering.page.PageConfiguration;
import net.sf.anathema.lib.resources.IResources;

import static net.sf.anathema.character.reporting.pdf.rendering.EncoderIds.ABILITIES_WITH_CRAFTS;
import static net.sf.anathema.character.reporting.pdf.rendering.EncoderIds.ATTRIBUTES;
import static net.sf.anathema.character.reporting.pdf.rendering.EncoderIds.BACKGROUNDS;
import static net.sf.anathema.character.reporting.pdf.rendering.EncoderIds.ESSENCE_DOTS;
import static net.sf.anathema.character.reporting.pdf.rendering.EncoderIds.EXPERIENCE;
import static net.sf.anathema.character.reporting.pdf.rendering.EncoderIds.GREAT_CURSE;
import static net.sf.anathema.character.reporting.pdf.rendering.EncoderIds.INTIMACIES_EXTENDED;
import static net.sf.anathema.character.reporting.pdf.rendering.EncoderIds.LANGUAGES;
import static net.sf.anathema.character.reporting.pdf.rendering.EncoderIds.MUTATIONS;
import static net.sf.anathema.character.reporting.pdf.rendering.EncoderIds.PERSONAL_INFO;
import static net.sf.anathema.character.reporting.pdf.rendering.EncoderIds.SPECIALTIES;
import static net.sf.anathema.character.reporting.pdf.rendering.EncoderIds.VIRTUES;
import static net.sf.anathema.character.reporting.pdf.rendering.EncoderIds.WILLPOWER_DOTS;
import static net.sf.anathema.character.reporting.pdf.rendering.EncoderIds.YOZI_LIST;

public class ExtendedFirstPageEncoder extends AbstractExtendedPdfPageEncoder {

  public ExtendedFirstPageEncoder(IResources resources, PageConfiguration pageConfiguration) {
    super(resources, pageConfiguration);
  }

  @Override
  public void encode(Sheet sheet, SheetGraphics graphics, ReportSession session) throws
          DocumentException {
    SheetPage page = sheet.startPortraitPage(graphics, session);
    LayoutField personalInfo = page.place(PERSONAL_INFO).atStartOf(page).withPreferredHeight().andColumnSpan(3).now();
    LayoutField attributes = page.place(ATTRIBUTES).below(personalInfo).withHeight(117).now();
    LayoutField abilities = page.place(ABILITIES_WITH_CRAFTS).below(attributes).withHeight(415).now();
    LayoutField specialities = page.place(SPECIALTIES).below(abilities).fillToBottomOfPage().andColumnSpan(2).now();

    LayoutField virtues = page.place(VIRTUES).rightOf(attributes).withHeight(72).now();
    LayoutField greatCurse = page.placeOptional(GREAT_CURSE).below(virtues).withHeight(85).now();
    LayoutField mutations = page.placeOptional(MUTATIONS).below(greatCurse).withHeight(40).now();
    LayoutField intimacies = page.place(INTIMACIES_EXTENDED).below(mutations).alignBottomTo(abilities).now();

    LayoutField essence = page.place(ESSENCE_DOTS).rightOf(virtues).withHeight(30).now();
    LayoutField willpower = page.place(WILLPOWER_DOTS).below(essence).alignBottomTo(virtues).now();
    LayoutField yoziList = page.placeOptional(YOZI_LIST).below(willpower).withPreferredHeight().now();
    LayoutField backgrounds = page.place(BACKGROUNDS).below(yoziList).alignBottomTo(intimacies).now();
    LayoutField experience = page.place(EXPERIENCE).below(backgrounds).withHeight(30).now();
    LayoutField languages = page.place(LANGUAGES).below(experience).fillToBottomOfPage().now();
    encodeCopyright(graphics);
  }
}

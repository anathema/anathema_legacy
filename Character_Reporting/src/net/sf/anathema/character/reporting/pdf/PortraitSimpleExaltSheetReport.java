package net.sf.anathema.character.reporting.pdf;

import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfWriter;
import net.sf.anathema.character.main.framework.ICharacterGenerics;
import net.sf.anathema.character.reporting.CharacterReportingModuleObject;
import net.sf.anathema.character.reporting.pdf.content.ReportContentRegistry;
import net.sf.anathema.character.reporting.pdf.content.ReportSession;
import net.sf.anathema.character.reporting.pdf.layout.Sheet;
import net.sf.anathema.character.reporting.pdf.layout.simple.FirstPageEncoder;
import net.sf.anathema.character.reporting.pdf.layout.simple.SecondPageEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.EncoderRegistry;
import net.sf.anathema.character.reporting.pdf.rendering.graphics.SheetGraphics;
import net.sf.anathema.character.reporting.pdf.rendering.page.PageConfiguration;
import net.sf.anathema.character.reporting.pdf.rendering.page.PageEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.pages.PageRegistry;
import net.sf.anathema.framework.itemdata.model.ItemData;
import net.sf.anathema.framework.module.preferences.PageSizePreference;
import net.sf.anathema.framework.reporting.ReportException;
import net.sf.anathema.framework.reporting.pdf.AbstractPdfReport;
import net.sf.anathema.framework.reporting.pdf.PageSize;
import net.sf.anathema.framework.repository.Item;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.lib.resources.Resources;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PortraitSimpleExaltSheetReport extends AbstractPdfReport {

  private final Resources resources;
  private final PageSizePreference pageSizePreference;
  private CharacterReportingModuleObject moduleObject;

  public PortraitSimpleExaltSheetReport(Resources resources, ICharacterGenerics characterGenerics, PageSizePreference pageSizePreference) {
    this.resources = resources;
    this.pageSizePreference = pageSizePreference;
    this.moduleObject = new CharacterReportingModuleObject(characterGenerics.getInstantiater(), resources);
  }

  @Override
  public String toString() {
    return resources.getString("CharacterModule.Reporting.Sheet.Name");
  }

  @Override
  public void performPrint(Item item, Document document, PdfWriter writer) throws ReportException {
    PageSize pageSize = pageSizePreference.getPageSize();
    Hero hero = (Hero) item.getItemData();
    PdfContentByte directContent = writer.getDirectContent();
    PageConfiguration configuration = PageConfiguration.ForPortrait(pageSize);
    try {
      List<PageEncoder> encoderList = new ArrayList<>();
      encoderList.add(new FirstPageEncoder(configuration));
      ReportSession session = new ReportSession(getContentRegistry(), hero);
      Collections.addAll(encoderList, findAdditionalPages(pageSize, session));
      encoderList.add(new SecondPageEncoder());
      Sheet sheet = new Sheet(document, getEncoderRegistry(), resources, pageSize);
      for (PageEncoder encoder : encoderList) {
        SheetGraphics graphics = SheetGraphics.WithHelvetica(directContent);
        encoder.encode(sheet, graphics, session);
      }
    } catch (Exception e) {
      throw new ReportException(e);
    }
  }

  private PageEncoder[] findAdditionalPages(PageSize pageSize, ReportSession session) {
    PageRegistry additionalPageRegistry = getReportingModuleObject().getAdditionalPageRegistry();
    return additionalPageRegistry.createEncoders(pageSize, getEncoderRegistry(), resources, session);
  }

  private EncoderRegistry getEncoderRegistry() {
    return getReportingModuleObject().getEncoderRegistry();
  }

  private CharacterReportingModuleObject getReportingModuleObject() {
    return moduleObject;
  }

  private ReportContentRegistry getContentRegistry() {
    CharacterReportingModuleObject moduleObject = getReportingModuleObject();
    return moduleObject.getContentRegistry();
  }

  @Override
  public boolean supports(Item item) {
    if (item == null) {
      return false;
    }
    ItemData itemData = item.getItemData();
    if (!(itemData instanceof Hero)) {
      return false;
    }
    Hero hero = (Hero) itemData;
    return hero.getTemplate().getTemplateType().getCharacterType().isEssenceUser();
  }
}

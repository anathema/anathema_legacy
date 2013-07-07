package net.sf.anathema.character.reporting.pdf;

import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfWriter;
import net.sf.anathema.character.main.framework.HeroEnvironment;
import net.sf.anathema.character.main.Character;
import net.sf.anathema.character.reporting.CharacterReportingModuleObject;
import net.sf.anathema.character.reporting.pdf.content.ReportContentRegistry;
import net.sf.anathema.character.reporting.pdf.content.ReportSession;
import net.sf.anathema.character.reporting.pdf.layout.Sheet;
import net.sf.anathema.character.reporting.pdf.layout.simple.MortalPageEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.EncoderRegistry;
import net.sf.anathema.character.reporting.pdf.rendering.graphics.SheetGraphics;
import net.sf.anathema.character.reporting.pdf.rendering.page.PageConfiguration;
import net.sf.anathema.character.reporting.pdf.rendering.page.PageEncoder;
import net.sf.anathema.framework.itemdata.model.ItemData;
import net.sf.anathema.framework.module.preferences.PageSizePreference;
import net.sf.anathema.framework.reporting.ReportException;
import net.sf.anathema.framework.reporting.pdf.AbstractPdfReport;
import net.sf.anathema.framework.reporting.pdf.PageSize;
import net.sf.anathema.framework.repository.Item;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.lib.resources.Resources;

public class PortraitSimpleMortalSheetReport extends AbstractPdfReport {

  private final Resources resources;
  private final PageSizePreference pageSizePreference;
  private CharacterReportingModuleObject reportingModuleObject;

  public PortraitSimpleMortalSheetReport(Resources resources, HeroEnvironment characterGenerics, PageSizePreference pageSizePreference) {
    this.resources = resources;
    this.pageSizePreference = pageSizePreference;
    this.reportingModuleObject = new CharacterReportingModuleObject(characterGenerics.getObjectFactory(), resources);
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
      PageEncoder encoder = new MortalPageEncoder(configuration);
      SheetGraphics graphics = SheetGraphics.WithHelvetica(directContent);
      ReportSession session = new ReportSession(getContentRegistry(), hero);
      Sheet sheet = new Sheet(document, getEncoderRegistry(), resources, pageSize);
      encoder.encode(sheet, graphics, session);
    } catch (Exception e) {
      throw new ReportException(e);
    }
  }

  private EncoderRegistry getEncoderRegistry() {
    return getReportingModuleObject().getEncoderRegistry();
  }

  private ReportContentRegistry getContentRegistry() {
    CharacterReportingModuleObject moduleObject = getReportingModuleObject();
    return moduleObject.getContentRegistry();
  }

  private CharacterReportingModuleObject getReportingModuleObject() {
    return reportingModuleObject;
  }

  @Override
  public boolean supports(Item item) {
    if (item == null) {
      return false;
    }
    ItemData itemData = item.getItemData();
    if (!(itemData instanceof Character)) {
      return false;
    }
    Character character = (Character) itemData;
    return !character.getTemplate().getTemplateType().getCharacterType().isEssenceUser();
  }
}

package net.sf.anathema.character.reporting.pdf;

import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfWriter;
import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.framework.ICharacterGenerics;
import net.sf.anathema.character.impl.util.GenericCharacterUtilities;
import net.sf.anathema.character.model.ICharacter;
import net.sf.anathema.character.reporting.CharacterReportingModuleObject;
import net.sf.anathema.character.reporting.pdf.content.ReportContentRegistry;
import net.sf.anathema.character.reporting.pdf.content.ReportSession;
import net.sf.anathema.character.reporting.pdf.layout.Sheet;
import net.sf.anathema.character.reporting.pdf.layout.simple.MortalPageEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.EncoderRegistry;
import net.sf.anathema.character.reporting.pdf.rendering.graphics.SheetGraphics;
import net.sf.anathema.character.reporting.pdf.rendering.page.PageConfiguration;
import net.sf.anathema.character.reporting.pdf.rendering.page.PageEncoder;
import net.sf.anathema.framework.itemdata.model.IItemData;
import net.sf.anathema.framework.module.preferences.PageSizePreference;
import net.sf.anathema.framework.reporting.ReportException;
import net.sf.anathema.framework.reporting.pdf.AbstractPdfReport;
import net.sf.anathema.framework.reporting.pdf.PageSize;
import net.sf.anathema.framework.repository.IItem;
import net.sf.anathema.lib.resources.Resources;

public class PortraitSimpleMortalSheetReport extends AbstractPdfReport {

  private final Resources resources;
  private final PageSizePreference pageSizePreference;
  private CharacterReportingModuleObject reportingModuleObject;

  public PortraitSimpleMortalSheetReport(Resources resources, ICharacterGenerics characterGenerics,
          PageSizePreference pageSizePreference) {
    this.resources = resources;
    this.pageSizePreference = pageSizePreference;
    this.reportingModuleObject = new CharacterReportingModuleObject(characterGenerics.getInstantiater(), resources);
  }

  @Override
  public String toString() {
    return resources.getString("CharacterModule.Reporting.Sheet.Name");
  }

  @Override
  public void performPrint(IItem item, Document document, PdfWriter writer) throws ReportException {
    PageSize pageSize = pageSizePreference.getPageSize();
    ICharacter stattedCharacter = (ICharacter) item.getItemData();
    PdfContentByte directContent = writer.getDirectContent();
    PageConfiguration configuration = PageConfiguration.ForPortrait(pageSize);
    try {
      IGenericCharacter character = GenericCharacterUtilities.createGenericCharacter(stattedCharacter);
      PageEncoder encoder = new MortalPageEncoder(configuration);
      SheetGraphics graphics = SheetGraphics.WithHelvetica(directContent);
      ReportSession session = new ReportSession(getContentRegistry(), character);
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
  public boolean supports(IItem item) {
    if (item == null) {
      return false;
    }
    IItemData itemData = item.getItemData();
    if (!(itemData instanceof ICharacter)) {
      return false;
    }
    ICharacter character = (ICharacter) itemData;
    return !character.getTemplate().getTemplateType().getCharacterType().isEssenceUser();
  }
}

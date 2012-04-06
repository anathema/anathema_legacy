package net.sf.anathema.character.reporting.pdf;

import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfWriter;
import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.character.IGenericDescription;
import net.sf.anathema.character.generic.framework.ICharacterGenerics;
import net.sf.anathema.character.generic.framework.module.object.ICharacterModuleObjectMap;
import net.sf.anathema.character.impl.generic.GenericDescription;
import net.sf.anathema.character.impl.util.GenericCharacterUtilities;
import net.sf.anathema.character.model.ICharacter;
import net.sf.anathema.character.reporting.CharacterReportingModule;
import net.sf.anathema.character.reporting.CharacterReportingModuleObject;
import net.sf.anathema.character.reporting.pdf.content.ReportContentRegistry;
import net.sf.anathema.character.reporting.pdf.content.ReportSession;
import net.sf.anathema.character.reporting.pdf.layout.Sheet;
import net.sf.anathema.character.reporting.pdf.layout.landscape.FirstPageEncoder;
import net.sf.anathema.character.reporting.pdf.layout.landscape.SecondPageEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.EncoderRegistry;
import net.sf.anathema.character.reporting.pdf.rendering.graphics.SheetGraphics;
import net.sf.anathema.character.reporting.pdf.rendering.page.PageEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.pages.PageRegistry;
import net.sf.anathema.framework.itemdata.model.IItemData;
import net.sf.anathema.framework.module.preferences.PageSizePreference;
import net.sf.anathema.framework.reporting.ReportException;
import net.sf.anathema.framework.reporting.pdf.AbstractPdfReport;
import net.sf.anathema.framework.reporting.pdf.PageSize;
import net.sf.anathema.framework.repository.IItem;
import net.sf.anathema.lib.resources.IResources;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LandscapeExaltSheetReport extends AbstractPdfReport {
  private IResources resources;
  private ICharacterGenerics characterGenerics;
  private PageSizePreference pageSizePreference;

  public LandscapeExaltSheetReport(IResources resources, ICharacterGenerics characterGenerics,
          PageSizePreference pageSizePreference) {
    this.resources = resources;
    this.characterGenerics = characterGenerics;
    this.pageSizePreference = pageSizePreference;
  }

  @Override
  public String toString() {
    return resources.getString("CharacterModule.Reporting.LandscapeSheet.Name");
  }

  @Override
  public void performPrint(IItem item, Document document, PdfWriter writer) throws ReportException {
    PageSize pageSize = pageSizePreference.getPageSize();
    PdfContentByte directContent = writer.getDirectContent();
    try {
      ReportSession session = createSession(item);
      Sheet sheet = new Sheet(document, getEncoderRegistry(), resources, pageSize);
      for (PageEncoder encoder : collectPageEncoders(pageSize, session)) {
        SheetGraphics graphics = SheetGraphics.WithHelvetica(directContent);
        encoder.encode(sheet, graphics, session);
      }
    } catch (Exception e) {
      throw new ReportException(e);
    }
  }

  private ReportSession createSession(IItem item) {
    ICharacter character = (ICharacter) item.getItemData();
    IGenericCharacter genericCharacter = GenericCharacterUtilities.createGenericCharacter(character.getStatistics());
    IGenericDescription description = new GenericDescription(character.getDescription());
    return new ReportSession(getContentRegistry(), genericCharacter, description);
  }

  private List<PageEncoder> collectPageEncoders(PageSize pageSize, ReportSession session) {
    List<PageEncoder> encoderList = new ArrayList<PageEncoder>();
    encoderList.add(new FirstPageEncoder());
    encoderList.add(new SecondPageEncoder(getEncoderRegistry(), resources));
    Collections.addAll(encoderList, findAdditionalPages(pageSize, session));
    return encoderList;
  }

  private PageEncoder[] findAdditionalPages(PageSize pageSize, ReportSession session) {
    PageRegistry additionalPageRegistry = getReportingModuleObject().getAdditionalPageRegistry();
    return additionalPageRegistry.createEncoders(pageSize, getEncoderRegistry(), resources, session);
  }

  private EncoderRegistry getEncoderRegistry() {
    return getReportingModuleObject().getEncoderRegistry();
  }

  private CharacterReportingModuleObject getReportingModuleObject() {
    ICharacterModuleObjectMap moduleObjectMap = characterGenerics.getModuleObjectMap();
    return moduleObjectMap.getModuleObject(CharacterReportingModule.class);
  }

  private ReportContentRegistry getContentRegistry() {
    CharacterReportingModuleObject moduleObject = getReportingModuleObject();
    return moduleObject.getContentRegistry();
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
    if (!character.hasStatistics()) {
      return false;
    }
    return character.getStatistics().getCharacterTemplate().getTemplateType().getCharacterType().isEssenceUser();
  }
}

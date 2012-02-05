package net.sf.anathema.character.impl.reporting;

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
import net.sf.anathema.character.reporting.pdf.content.ReportContent;
import net.sf.anathema.character.reporting.pdf.content.ReportContentRegistry;
import net.sf.anathema.character.reporting.pdf.layout.simple.FirstPageEncoder;
import net.sf.anathema.character.reporting.pdf.layout.simple.SecondPageEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.EncoderRegistry;
import net.sf.anathema.character.reporting.pdf.rendering.graphics.SheetGraphics;
import net.sf.anathema.character.reporting.pdf.rendering.page.PageConfiguration;
import net.sf.anathema.character.reporting.pdf.rendering.page.PageEncoder;
import net.sf.anathema.framework.itemdata.model.IItemData;
import net.sf.anathema.framework.reporting.ReportException;
import net.sf.anathema.framework.reporting.pdf.AbstractPdfReport;
import net.sf.anathema.framework.repository.IItem;
import net.sf.anathema.lib.resources.IResources;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.text.MessageFormat.format;

public class SimpleExaltSheetReport extends AbstractPdfReport {

  private final IResources resources;
  private final ICharacterGenerics characterGenerics;
  private final PageSize pageSize;

  public SimpleExaltSheetReport(IResources resources, ICharacterGenerics characterGenerics, PageSize pageSize) {
    this.resources = resources;
    this.characterGenerics = characterGenerics;
    this.pageSize = pageSize;
  }

  @Override
  public String toString() {
    String name = resources.getString("CharacterModule.Reporting.SecondEdition.Name");
    String pageFormat = resources.getString("PageSize." + pageSize.name());
    return format("{0} ({1})", name, pageFormat);
  }

  @Override
  public void performPrint(IItem item, Document document, PdfWriter writer) throws ReportException {
    ICharacter stattedCharacter = (ICharacter) item.getItemData();
    document.setPageSize(pageSize.getRectangle());
    document.open();
    PdfContentByte directContent = writer.getDirectContent();
    PageConfiguration configuration = PageConfiguration.create(pageSize.getRectangle());
    try {
      IGenericCharacter character = GenericCharacterUtilities.createGenericCharacter(stattedCharacter.getStatistics());
      IGenericDescription description = new GenericDescription(stattedCharacter.getDescription());
      List<PageEncoder> encoderList = new ArrayList<PageEncoder>();
      encoderList.add(new FirstPageEncoder(getEncoderRegistry(), resources, configuration));
      ReportContent content = new ReportContent(getContentRegistry(), character, description);
      Collections.addAll(encoderList, findAdditionalPages(configuration, content));
      encoderList.add(new SecondPageEncoder(getEncoderRegistry(), resources, configuration));
      boolean isFirstPrinted = false;
      for (PageEncoder encoder : encoderList) {
        if (isFirstPrinted) {
          document.newPage();
        } else {
          isFirstPrinted = true;
        }
        SheetGraphics graphics = SheetGraphics.WithSymbolBaseFontInCodepage1252(directContent);
        encoder.encode(document, graphics, content);
      }
    } catch (Exception e) {
      throw new ReportException(e);
    }
  }

  private PageEncoder[] findAdditionalPages(PageConfiguration configuration, ReportContent content) {
    return getReportingModuleObject().getAdditionalPageRegistry().createEncoders(configuration, getEncoderRegistry(), resources, content);
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
    return character.getStatistics().getCharacterTemplate().getTemplateType().getCharacterType().isExaltType();
  }
}

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
import net.sf.anathema.character.reporting.pdf.layout.simple.MortalPageEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.EncoderRegistry;
import net.sf.anathema.character.reporting.pdf.rendering.graphics.SheetGraphics;
import net.sf.anathema.character.reporting.pdf.rendering.page.PageConfiguration;
import net.sf.anathema.character.reporting.pdf.rendering.page.PageEncoder;
import net.sf.anathema.framework.itemdata.model.IItemData;
import net.sf.anathema.framework.reporting.IITextReport;
import net.sf.anathema.framework.reporting.ReportException;
import net.sf.anathema.framework.repository.IItem;
import net.sf.anathema.lib.resources.IResources;

public class SimpleMortalSheetReport implements IITextReport {

  private final IResources resources;
  private final ICharacterGenerics characterGenerics;
  private final PageSize pageSize;

  public SimpleMortalSheetReport(IResources resources, ICharacterGenerics characterGenerics, PageSize pageSize) {
    this.resources = resources;
    this.characterGenerics = characterGenerics;
    this.pageSize = pageSize;
  }

  @Override
  public String toString() {
    return resources.getString("CharacterModule.Reporting.SecondEdition.Name") + " (" + resources.getString("PageSize." + pageSize.name()) +
            ")"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
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
      PageEncoder encoder = new MortalPageEncoder(getEncoderRegistry(), resources, configuration);
      SheetGraphics graphics = SheetGraphics.WithSymbolBaseFontInCodepage1252(directContent);
      ReportContent content = new ReportContent(getContentRegistry(), character, description);
      encoder.encode(document, graphics, content);
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
    ICharacterModuleObjectMap moduleObjectMap = characterGenerics.getModuleObjectMap();
    return moduleObjectMap.getModuleObject(CharacterReportingModule.class);
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
    return !character.getStatistics().getCharacterTemplate().getTemplateType().getCharacterType().isExaltType();
  }
}

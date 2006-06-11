package net.sf.anathema.character.impl.reporting;

import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.character.IGenericDescription;
import net.sf.anathema.character.generic.framework.ICharacterGenerics;
import net.sf.anathema.character.generic.framework.module.object.ICharacterModuleObjectMap;
import net.sf.anathema.character.generic.impl.rules.ExaltedEdition;
import net.sf.anathema.character.generic.type.CharacterType;
import net.sf.anathema.character.impl.generic.GenericDescription;
import net.sf.anathema.character.impl.util.GenericCharacterUtilities;
import net.sf.anathema.character.model.ICharacter;
import net.sf.anathema.character.reporting.CharacterReportingModule;
import net.sf.anathema.character.reporting.CharacterReportingModuleObject;
import net.sf.anathema.character.reporting.sheet.SecondEditionEncodingRegistry;
import net.sf.anathema.character.reporting.sheet.page.PdfFirstPageEncoder;
import net.sf.anathema.character.reporting.sheet.page.PdfSecondPageEncoder;
import net.sf.anathema.character.reporting.sheet.pageformat.PdfPageConfiguration;
import net.sf.anathema.framework.itemdata.model.IItemData;
import net.sf.anathema.framework.reporting.ReportException;
import net.sf.anathema.framework.reporting.itext.IITextReport;
import net.sf.anathema.framework.repository.IItem;
import net.sf.anathema.lib.resources.IResources;

import com.lowagie.text.Document;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfWriter;

public class SecondEditionSheetReport implements IITextReport {

  private final IResources resources;
  private final ICharacterGenerics characterGenerics;
  private final PageSize pageSize;

  public SecondEditionSheetReport(IResources resources, ICharacterGenerics characterGenerics, PageSize pageSize) {
    this.resources = resources;
    this.characterGenerics = characterGenerics;
    this.pageSize = pageSize;
  }

  @Override
  public String toString() {
    return resources.getString("CharacterModule.Reporting.SecondEdition.Name") + " (" + resources.getString("PageSize." + pageSize.name()) + ")"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
  }

  public void performPrint(IItem item, Document document, PdfWriter writer) throws ReportException {
    ICharacter stattedCharacter = (ICharacter) item.getItemData();
    document.setPageSize(pageSize.getRectangle());
    document.open();
    PdfContentByte directContent = writer.getDirectContent();
    IGenericDescription description = new GenericDescription(stattedCharacter.getDescription());
    IGenericCharacter character = GenericCharacterUtilities.createGenericCharacter(stattedCharacter.getStatistics());
    PdfPageConfiguration configuration = PdfPageConfiguration.create(pageSize.getRectangle());
    ICharacterModuleObjectMap moduleObjectMap = characterGenerics.getModuleObjectMap();
    CharacterReportingModuleObject moduleObject = moduleObjectMap.getModuleObject(CharacterReportingModule.class);
    SecondEditionEncodingRegistry encodingRegistry = moduleObject.getSecondEditionEncodingRegistry();
    try {
      PdfFirstPageEncoder partEncoder = new PdfFirstPageEncoder(encodingRegistry, resources, 7, configuration);
      partEncoder.encode(document, directContent, character, description);
      if (character.getTemplate().getTemplateType().getCharacterType() == CharacterType.MORTAL) {
        return;
      }
      document.newPage();
      new PdfSecondPageEncoder(resources, encodingRegistry.getBaseFont(), configuration).encode(
          document,
          directContent,
          character,
          description);

    }
    catch (Exception e) {
      throw new ReportException(e);
    }
  }

  public boolean supports(IItem item) {
    if (item == null) {
      return false;
    }
    IItemData itemData = item.getItemData();
    if (!(itemData instanceof ICharacter)) {
      return false;
    }
    ICharacter character = (ICharacter) itemData;
    return character.hasStatistics()
        && character.getStatistics().getRules().getEdition() == ExaltedEdition.SecondEdition;
  }
}
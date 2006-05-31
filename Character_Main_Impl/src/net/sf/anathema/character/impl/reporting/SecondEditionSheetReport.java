package net.sf.anathema.character.impl.reporting;

import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.character.IGenericDescription;
import net.sf.anathema.character.generic.framework.ICharacterGenerics;
import net.sf.anathema.character.generic.framework.module.object.ICharacterModuleObjectMap;
import net.sf.anathema.character.generic.impl.rules.ExaltedEdition;
import net.sf.anathema.character.impl.generic.GenericDescription;
import net.sf.anathema.character.impl.util.GenericCharacterUtilities;
import net.sf.anathema.character.model.ICharacter;
import net.sf.anathema.character.reporting.CharacterReportingModule;
import net.sf.anathema.character.reporting.CharacterReportingModuleObject;
import net.sf.anathema.character.reporting.sheet.SecondEditionEncodingRegistry;
import net.sf.anathema.character.reporting.sheet.page.PdfFirstPageEncoder;
import net.sf.anathema.character.reporting.sheet.page.PdfSecondPageEncoder;
import net.sf.anathema.character.reporting.sheet.pageformat.PdfPageConfiguration;
import net.sf.anathema.character.reporting.sheet.second.SecondEditionPartEncoder;
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

  public SecondEditionSheetReport(IResources resources, ICharacterGenerics characterGenerics) {
    this.resources = resources;
    this.characterGenerics = characterGenerics;
  }

  @Override
  public String toString() {
    return resources.getString("CharacterModule.Reporting.SecondEdition.Name"); //$NON-NLS-1$
  }

  public void performPrint(IItem item, Document document, PdfWriter writer) throws ReportException {
    ICharacter stattedCharacter = (ICharacter) item.getItemData();
    document.open();
    PdfContentByte directContent = writer.getDirectContent();
    IGenericDescription description = new GenericDescription(stattedCharacter.getDescription());
    IGenericCharacter character = GenericCharacterUtilities.createGenericCharacter(stattedCharacter.getStatistics());
    PdfPageConfiguration configuration = PdfPageConfiguration.create(document.getPageSize());
    ICharacterModuleObjectMap moduleObjectMap = characterGenerics.getModuleObjectMap();
    CharacterReportingModuleObject moduleObject = moduleObjectMap.getModuleObject(CharacterReportingModule.class);
    SecondEditionEncodingRegistry encodingRegistry = moduleObject.getSecondEditionEncodingRegistry();
    SecondEditionPartEncoder partEncoder = new SecondEditionPartEncoder(encodingRegistry, resources, 7, configuration);
    try {
      new PdfFirstPageEncoder(partEncoder, configuration).encode(directContent, character, description);
      document.newPage();
      new PdfSecondPageEncoder(resources, partEncoder.getBaseFont(), configuration).encode(directContent, character, description);
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
package net.sf.anathema.character.impl.reporting;

import com.lowagie.text.Document;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfWriter;
import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.character.IGenericDescription;
import net.sf.anathema.character.generic.framework.ICharacterGenerics;
import net.sf.anathema.character.generic.framework.module.object.ICharacterModuleObjectMap;
import net.sf.anathema.character.generic.impl.rules.ExaltedEdition;
import net.sf.anathema.character.generic.rules.IExaltedEdition;
import net.sf.anathema.character.generic.template.ICharacterTemplate;
import net.sf.anathema.character.generic.traits.types.OtherTraitType;
import net.sf.anathema.character.generic.type.ICharacterType;
import net.sf.anathema.character.impl.generic.GenericDescription;
import net.sf.anathema.character.impl.util.GenericCharacterUtilities;
import net.sf.anathema.character.model.ICharacter;
import net.sf.anathema.character.reporting.CharacterReportingModule;
import net.sf.anathema.character.reporting.CharacterReportingModuleObject;
import net.sf.anathema.character.reporting.pdf.content.ReportContent;
import net.sf.anathema.character.reporting.pdf.content.ReportContentRegistry;
import net.sf.anathema.character.reporting.pdf.layout.extended.Extended1stEditionFirstPageEncoder;
import net.sf.anathema.character.reporting.pdf.layout.extended.ExtendedEncodingRegistry;
import net.sf.anathema.character.reporting.pdf.layout.extended.ExtendedFirstPageEncoder;
import net.sf.anathema.character.reporting.pdf.layout.extended.ExtendedMagic1stEditionPageEncoder;
import net.sf.anathema.character.reporting.pdf.layout.extended.ExtendedMagicPageEncoder;
import net.sf.anathema.character.reporting.pdf.layout.extended.ExtendedSecondPageEncoder;
import net.sf.anathema.character.reporting.pdf.layout.extended.IExtendedPartEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.BoxContentEncoderRegistry;
import net.sf.anathema.character.reporting.pdf.rendering.graphics.SheetGraphics;
import net.sf.anathema.character.reporting.pdf.rendering.page.IPdfPageEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.page.PdfPageConfiguration;
import net.sf.anathema.framework.itemdata.model.IItemData;
import net.sf.anathema.framework.reporting.IITextReport;
import net.sf.anathema.framework.reporting.ReportException;
import net.sf.anathema.framework.repository.IItem;
import net.sf.anathema.lib.resources.IResources;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ExtendedSheetReport implements IITextReport {

  private final IResources resources;
  private final ICharacterGenerics characterGenerics;
  private final PageSize pageSize;

  public ExtendedSheetReport(IResources resources, ICharacterGenerics characterGenerics, PageSize pageSize) {
    this.resources = resources;
    this.characterGenerics = characterGenerics;
    this.pageSize = pageSize;
  }

  @Override
  public String toString() {
    return "Extended Sheet" + " (" + resources.getString("PageSize." + pageSize.name()) + ")";
  }

  public void performPrint(IItem item, Document document, PdfWriter writer) throws ReportException {
    ICharacter stattedCharacter = (ICharacter) item.getItemData();
    document.setPageSize(pageSize.getRectangle());
    document.open();
    PdfContentByte directContent = writer.getDirectContent();
    PdfPageConfiguration configuration = PdfPageConfiguration.create(pageSize.getRectangle());
    ExtendedEncodingRegistry encodingRegistry = getEncodingRegistry();
    SheetGraphics graphics = new SheetGraphics(directContent, encodingRegistry.getBaseFont(), encodingRegistry.getSymbolBaseFont());
    try {
      int traitMax = Math.max(5, getEssenceMax(stattedCharacter));
      IExtendedPartEncoder partEncoder = getPartEncoder(stattedCharacter);
      IGenericCharacter character = GenericCharacterUtilities.createGenericCharacter(stattedCharacter.getStatistics());
      IGenericDescription description = new GenericDescription(stattedCharacter.getDescription());
      IExaltedEdition edition = character.getRules().getEdition();

      List<IPdfPageEncoder> encoderList = new ArrayList<IPdfPageEncoder>();
      if (edition == ExaltedEdition.FirstEdition) {
        encoderList
          .add(new Extended1stEditionFirstPageEncoder(getEncoderRegistry(), partEncoder, encodingRegistry, resources, traitMax, configuration));
      }
      if (edition == ExaltedEdition.SecondEdition) {
        encoderList.add(new ExtendedFirstPageEncoder(partEncoder, encodingRegistry, resources, traitMax, configuration));
        encoderList.add(new ExtendedSecondPageEncoder(getEncoderRegistry(), partEncoder, encodingRegistry, resources, configuration));
      }
      Collections.addAll(encoderList, partEncoder.getAdditionalPages(configuration));
      if (edition == ExaltedEdition.SecondEdition) {
        encoderList.add(new ExtendedMagicPageEncoder(partEncoder, encodingRegistry, resources, configuration));
      }
      else if (partEncoder.hasMagicPage()) {
        encoderList.add(
          new ExtendedMagic1stEditionPageEncoder(partEncoder, encodingRegistry, resources, configuration, edition != ExaltedEdition.FirstEdition));
      }
      boolean firstPagePrinted = false;
      for (IPdfPageEncoder encoder : encoderList) {
        if (firstPagePrinted) {
          document.newPage();
        }
        else {
          firstPagePrinted = true;
        }
        ReportContent content = new ReportContent(getContentRegistry(), character, description);
        encoder.encode(document, graphics, content);
      }
    } catch (Exception e) {
      throw new ReportException(e);
    }
  }

  private int getEssenceMax(ICharacter character) {
    return character.getStatistics().getTraitConfiguration().getTrait(OtherTraitType.Essence).getMaximalValue();
  }

  private IExtendedPartEncoder getPartEncoder(ICharacter character) {
    ExtendedEncodingRegistry encodingRegistry = getEncodingRegistry();
    ICharacterTemplate characterTemplate = character.getStatistics().getCharacterTemplate();
    ICharacterType characterType = characterTemplate.getTemplateType().getCharacterType();
    IExaltedEdition edition = characterTemplate.getEdition();
    return encodingRegistry.getPartEncoder(characterType, edition);
  }

  private ExtendedEncodingRegistry getEncodingRegistry() {
    return getReportingModuleObject().getExtendedEncodingRegistry();
  }

  private BoxContentEncoderRegistry getEncoderRegistry() {
    return getReportingModuleObject().getEncoderRegistry();
  }

  private ReportContentRegistry getContentRegistry() {
    return getReportingModuleObject().getReportContentRegistry();
  }

  private CharacterReportingModuleObject getReportingModuleObject() {
    ICharacterModuleObjectMap moduleObjectMap = characterGenerics.getModuleObjectMap();
    return moduleObjectMap.getModuleObject(CharacterReportingModule.class);
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
    if (!character.hasStatistics()) {
      return false;
    }
    return getPartEncoder(character) != null;
  }
}

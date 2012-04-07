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
import net.sf.anathema.character.reporting.pdf.layout.extended.ExtendedFirstPageEncoder;
import net.sf.anathema.character.reporting.pdf.layout.extended.ExtendedMagicPageEncoder;
import net.sf.anathema.character.reporting.pdf.layout.extended.ExtendedSecondPageEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.EncoderRegistry;
import net.sf.anathema.character.reporting.pdf.rendering.graphics.SheetGraphics;
import net.sf.anathema.character.reporting.pdf.rendering.page.PageConfiguration;
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

public class ExtendedSheetReport extends AbstractPdfReport {

  private final IResources resources;
  private final ICharacterGenerics characterGenerics;
  private final PageSizePreference pageSizePreference;

  public ExtendedSheetReport(IResources resources, ICharacterGenerics characterGenerics,
          PageSizePreference pageSizePreference) {
    this.resources = resources;
    this.characterGenerics = characterGenerics;
    this.pageSizePreference = pageSizePreference;
  }

  @Override
  public String toString() {
    return "Extended Sheet";
  }

  @Override
  public void performPrint(IItem item, Document document, PdfWriter writer) throws ReportException {
    PageSize pageSize = pageSizePreference.getPageSize();
    ICharacter stattedCharacter = (ICharacter) item.getItemData();
    PdfContentByte directContent = writer.getDirectContent();
    PageConfiguration configuration = PageConfiguration.ForPortrait(pageSize);
    SheetGraphics graphics = SheetGraphics.WithHelvetica(directContent);
    try {
      IGenericCharacter character = GenericCharacterUtilities.createGenericCharacter(stattedCharacter.getStatistics());
      IGenericDescription description = new GenericDescription(stattedCharacter.getDescription());

      ReportSession session = new ReportSession(getContentRegistry(), character, description);
      List<PageEncoder> encoderList = new ArrayList<PageEncoder>();
      encoderList.add(new ExtendedFirstPageEncoder(configuration));
      encoderList.add(new ExtendedSecondPageEncoder(configuration));
      Collections.addAll(encoderList, findAdditionalPages(pageSize, session));
      encoderList.add(new ExtendedMagicPageEncoder(getEncoderRegistry(), configuration));
      Sheet sheet = new Sheet(document, getEncoderRegistry(), resources, pageSize);
      for (PageEncoder encoder : encoderList) {
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

  private ReportContentRegistry getContentRegistry() {
    return getReportingModuleObject().getContentRegistry();
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
    return character.hasStatistics();
  }
}

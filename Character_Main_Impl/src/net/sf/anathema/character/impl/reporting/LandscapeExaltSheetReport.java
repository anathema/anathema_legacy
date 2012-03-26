package net.sf.anathema.character.impl.reporting;

import com.itextpdf.text.Document;
import com.itextpdf.text.RectangleReadOnly;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfWriter;
import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.character.IGenericDescription;
import net.sf.anathema.character.generic.framework.ICharacterGenerics;
import net.sf.anathema.character.generic.framework.module.object.ICharacterModuleObjectMap;
import net.sf.anathema.character.impl.generic.GenericDescription;
import net.sf.anathema.character.model.ICharacter;
import net.sf.anathema.character.reporting.CharacterReportingModule;
import net.sf.anathema.character.reporting.CharacterReportingModuleObject;
import net.sf.anathema.character.reporting.pdf.content.ReportContentRegistry;
import net.sf.anathema.character.reporting.pdf.content.ReportSession;
import net.sf.anathema.character.reporting.pdf.layout.landscape.FirstPageEncoder;
import net.sf.anathema.character.reporting.pdf.layout.landscape.SecondPageEncoder;
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
import net.sf.anathema.lib.resources.IResources;

import java.util.ArrayList;
import java.util.List;

import static net.sf.anathema.character.impl.util.GenericCharacterUtilities.createGenericCharacter;

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
    ICharacter character = (ICharacter) item.getItemData();
    document.setPageSize(new RectangleReadOnly(pageSize.getPortraitRectangle().getHeight(),
            pageSize.getPortraitRectangle().getWidth()));
    document.open();
    PdfContentByte directContent = writer.getDirectContent();
    PageConfiguration configuration = PageConfiguration.ForLandscape(pageSize);
    try {
      IGenericCharacter genericCharacter = createGenericCharacter(character.getStatistics());
      IGenericDescription description = new GenericDescription(character.getDescription());
      List<PageEncoder> encoderList = new ArrayList<PageEncoder>();
      encoderList.add(new FirstPageEncoder(getEncoderRegistry(), resources, configuration));
      encoderList.add(new SecondPageEncoder(pageSize, getEncoderRegistry(), resources, configuration));
      ReportSession session = new ReportSession(getContentRegistry(), genericCharacter, description);
      boolean isFirstPrinted = false;
      for (PageEncoder encoder : encoderList) {
        if (isFirstPrinted) {
          document.newPage();
        } else {
          isFirstPrinted = true;
        }
        SheetGraphics graphics = SheetGraphics.WithHelvetica(directContent);
        encoder.encode(document, graphics, session);
      }
    } catch (Exception e) {
      throw new ReportException(e);
    }
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

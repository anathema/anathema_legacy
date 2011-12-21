package net.sf.anathema.character.db.reporting;

import com.lowagie.text.Element;
import net.sf.anathema.character.db.virtueflaw.DbVirtueFlawTemplate;
import net.sf.anathema.character.generic.character.*;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.library.virtueflaw.model.IVirtueFlaw;
import net.sf.anathema.character.library.virtueflaw.presenter.IVirtueFlawModel;
import net.sf.anathema.character.reporting.pdf.content.ReportContent;
import net.sf.anathema.character.reporting.pdf.rendering.Bounds;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.virtueflaw.VirtueFlawBoxEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.graphics.SheetGraphics;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.IBoxContentEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.general.table.TableEncodingUtilities;
import net.sf.anathema.character.reporting.pdf.rendering.page.IVoidStateFormatConstants;
import net.sf.anathema.lib.resources.IResources;

import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.BaseFont;

public class SecondEditionDbGreatCurseEncoder implements IBoxContentEncoder {

  private final VirtueFlawBoxEncoder traitEncoder;
  private final Font font;
  private final IResources resources;

  public SecondEditionDbGreatCurseEncoder(BaseFont baseFont, IResources resources) {
    this.resources = resources;
    this.font = createFont(baseFont);
    this.traitEncoder = new VirtueFlawBoxEncoder();
  }

  private Font createFont(BaseFont baseFont) {
    return TableEncodingUtilities.createFont(baseFont);
  }

  public void encode(SheetGraphics graphics, ReportContent reportContent, Bounds bounds) throws DocumentException {
	IVirtueFlaw virtueFlaw = ((IVirtueFlawModel) reportContent.getCharacter().getAdditionalModel(DbVirtueFlawTemplate.TEMPLATE_ID)).getVirtueFlaw();
    Bounds textBounds = traitEncoder.encode(graphics, bounds, virtueFlaw.getLimitTrait().getCurrentValue());
    float leading = IVoidStateFormatConstants.LINE_HEIGHT - 2;
    String virtue;
    ITraitType rootVirtue = virtueFlaw.getRoot();
    if ((rootVirtue != null)) {
      virtue = resources.getString(rootVirtue.getId());
    }
    else {
      virtue = resources.getString("Sheet.GreatCurse.UnknownVirtue"); //$NON-NLS-1$
    }
    String aspect = getAspectString(reportContent.getCharacter(), rootVirtue != null);
    String message = resources.getString("Sheet.GreatCurse.Message.SecondEdition", virtue, aspect); //$NON-NLS-1$
    Phrase phrase = new Phrase(message, font);
    graphics.encodeText(phrase, textBounds, leading);
  }

  private String getAspectString(IGenericCharacter character, boolean isRootSelected) {
    String casteTypeString = character.getCasteType().getId();
    if (casteTypeString != null) {
      String casteType = resources.getString("Caste." + casteTypeString); //$NON-NLS-1$
      return resources.getString("Sheet.GreatCurse.AspectMessage", casteType); //$NON-NLS-1$
    }
    if (isRootSelected) {
      return resources.getString("Sheet.GreatCurse.UnknownAspectKnownVirtue"); //$NON-NLS-1$
    }
    return resources.getString("Sheet.GreatCurse.UnknownAspectUnknownVirtue"); //$NON-NLS-1$
  }

  public String getHeaderKey(ReportContent content) {
    return "GreatCurse.Dragon-Blooded"; //$NON-NLS-1$
  }
  
  public boolean hasContent(ReportContent content)
  {
	  return true;
  }
}

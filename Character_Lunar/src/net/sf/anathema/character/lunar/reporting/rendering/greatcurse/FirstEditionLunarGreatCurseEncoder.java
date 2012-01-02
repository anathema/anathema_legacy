package net.sf.anathema.character.lunar.reporting.rendering.greatcurse;

import com.lowagie.text.Chunk;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.Phrase;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.library.virtueflaw.model.IVirtueFlaw;
import net.sf.anathema.character.library.virtueflaw.presenter.IVirtueFlawModel;
import net.sf.anathema.character.lunar.virtueflaw.LunarVirtueFlawTemplate;
import net.sf.anathema.character.reporting.pdf.content.ReportContent;
import net.sf.anathema.character.reporting.pdf.rendering.Bounds;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.virtueflaw.VirtueFlawBoxEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.IBoxContentEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.graphics.SheetGraphics;
import net.sf.anathema.character.reporting.pdf.rendering.page.IVoidStateFormatConstants;
import net.sf.anathema.lib.resources.IResources;

public class FirstEditionLunarGreatCurseEncoder implements IBoxContentEncoder {

  private final VirtueFlawBoxEncoder traitEncoder = new VirtueFlawBoxEncoder();
  private final IResources resources;

  public FirstEditionLunarGreatCurseEncoder(IResources resources) {
    this.resources = resources;
  }

  private Font createNameFont(SheetGraphics graphics) {
    Font newFont = createFont(graphics);
    newFont.setStyle(Font.BOLD);
    return newFont;
  }

  private Font createFont(SheetGraphics graphics) {
    return graphics.createTableFont();
  }

  public void encode(SheetGraphics graphics, ReportContent reportContent, Bounds bounds) throws DocumentException {
    IVirtueFlaw virtueFlaw =
      ((IVirtueFlawModel) reportContent.getCharacter().getAdditionalModel(LunarVirtueFlawTemplate.TEMPLATE_ID)).getVirtueFlaw();
    Bounds textBounds = traitEncoder.encode(graphics, bounds, virtueFlaw.getLimitTrait().getCurrentValue());
    float leading = IVoidStateFormatConstants.LINE_HEIGHT - 2;
    Phrase phrase = new Phrase();
    String virtue;
    Font font = createFont(graphics);
    ITraitType rootVirtue = virtueFlaw.getRoot();
    if (rootVirtue != null) {
      String name = virtueFlaw.getName().getText();
      phrase.add(new Chunk(name + "\n", createNameFont(graphics))); //$NON-NLS-1$
      virtue = resources.getString(rootVirtue.getId());
    }
    else {
      virtue = resources.getString("Sheet.GreatCurse.Lunar.Virtue"); //$NON-NLS-1$
      phrase.add(graphics.createSymbolChunk());
    }
    phrase.add(new Chunk(resources.getString("Sheet.GreatCurse.Lunar.GainMessage", virtue) + "\n", font)); //$NON-NLS-1$//$NON-NLS-2$
    if (rootVirtue == null) {
      phrase.add(graphics.createSymbolChunk());
      phrase.add(new Chunk(resources.getString("Sheet.GreatCurse.Lunar.Rules"), font)); //$NON-NLS-1$
    }
    graphics.createSimpleColumn(textBounds).withLeading(leading).andTextPart(phrase).encode();
  }

  public String getHeaderKey(ReportContent content) {
    return "GreatCurse.Lunar"; //$NON-NLS-1$
  }

  public boolean hasContent(ReportContent content) {
    return true;
  }
}

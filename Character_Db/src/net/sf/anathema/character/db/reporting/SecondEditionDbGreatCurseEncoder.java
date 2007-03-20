package net.sf.anathema.character.db.reporting;

import net.sf.anathema.character.db.virtueflaw.DbVirtueFlawTemplate;
import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.library.virtueflaw.model.IVirtueFlaw;
import net.sf.anathema.character.library.virtueflaw.presenter.IVirtueFlawModel;
import net.sf.anathema.character.reporting.sheet.common.IPdfContentBoxEncoder;
import net.sf.anathema.character.reporting.sheet.common.PdfEncodingUtilities;
import net.sf.anathema.character.reporting.sheet.pageformat.IVoidStateFormatConstants;
import net.sf.anathema.character.reporting.sheet.util.PdfTextEncodingUtilities;
import net.sf.anathema.character.reporting.sheet.util.TableEncodingUtilities;
import net.sf.anathema.character.reporting.sheet.util.VirtueFlawBoxEncoder;
import net.sf.anathema.character.reporting.util.Bounds;
import net.sf.anathema.lib.resources.IResources;

import com.lowagie.text.Chunk;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfContentByte;

public class SecondEditionDbGreatCurseEncoder implements IPdfContentBoxEncoder {

  private final VirtueFlawBoxEncoder traitEncoder;
  private final Chunk symbolChunk;
  private final Font font;
  private final IResources resources;

  public SecondEditionDbGreatCurseEncoder(BaseFont baseFont, BaseFont symbolBaseFont, IResources resources) {
    this.resources = resources;
    this.font = createFont(baseFont);
    this.traitEncoder = new VirtueFlawBoxEncoder(baseFont);
    this.symbolChunk = PdfEncodingUtilities.createCaretSymbolChunk(symbolBaseFont);
  }

  private Font createFont(BaseFont baseFont) {
    return TableEncodingUtilities.createFont(baseFont);
  }

  public void encode(PdfContentByte directContent, IGenericCharacter character, Bounds bounds) throws DocumentException {
    Bounds textBounds = traitEncoder.encode(directContent, bounds);
    IVirtueFlaw virtueFlaw = ((IVirtueFlawModel) character.getAdditionalModel(DbVirtueFlawTemplate.TEMPLATE_ID)).getVirtueFlaw();
    int leading = IVoidStateFormatConstants.LINE_HEIGHT - 2;
    String virtue;
    ITraitType rootVirtue = virtueFlaw.getRoot();
    boolean isRootSelected = rootVirtue != null;
    if (isRootSelected) {
      virtue = resources.getString(rootVirtue.getId());
    }
    else {
      virtue = resources.getString("Sheet.GreatCurse.UnknownVirtue"); //$NON-NLS-1$
    }
    String aspect = getAspectString(character, isRootSelected);
    String message = resources.getString("Sheet.GreatCurse.Message.SecondEditon", virtue, aspect); //$NON-NLS-1$
    Phrase phrase = new Phrase(message, font);
    PdfTextEncodingUtilities.encodeText(directContent, phrase, textBounds, leading);
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

  public String getHeaderKey() {
    return "GreatCurse.Dragon-Blooded"; //$NON-NLS-1$
  }
}
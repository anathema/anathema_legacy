package net.sf.anathema.character.reporting.extended.common.magic;

import com.lowagie.text.Chunk;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfContentByte;
import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.character.IGenericDescription;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.spells.CircleType;
import net.sf.anathema.character.generic.template.magic.ISpellMagicTemplate;
import net.sf.anathema.character.reporting.extended.common.IPdfVariableContentBoxEncoder;
import net.sf.anathema.character.reporting.pageformat.IVoidStateFormatConstants;
import net.sf.anathema.character.reporting.extended.util.AbstractPdfEncoder;
import net.sf.anathema.character.reporting.extended.util.PdfTextEncodingUtilities;
import net.sf.anathema.character.reporting.util.Bounds;
import net.sf.anathema.lib.resources.IResources;

import java.awt.*;

public class PdfInitiationEncoder extends AbstractPdfEncoder
    implements IPdfVariableContentBoxEncoder {
  
  private BaseFont baseFont;
  private Font textFont;
  private Font headerFont;
  private final IResources resources;

  public PdfInitiationEncoder(IResources resources, BaseFont baseFont) {
    this.baseFont = baseFont;
    this.textFont = new Font(baseFont, IVoidStateFormatConstants.FONT_SIZE - 0.5f, Font.NORMAL, Color.BLACK);
    this.headerFont = new Font(textFont);
    this.headerFont.setStyle(Font.BOLD);
    this.resources = resources;
  }

  protected BaseFont getBaseFont() {
    return baseFont;
  }

  public String getHeaderKey(IGenericCharacter character,
                             IGenericDescription description) {
    return "Initiations"; //$NON-NLS-1$
  }

  public float getRequestedHeight(IGenericCharacter character, float width) {
    ISpellMagicTemplate spellMagicTemplate = character.getTemplate().getMagicTemplate().getSpellMagic();
    ICharm[] knownCharms = character.getLearnedCharms();
    
    float height = 0;
    for (CircleType circle : CircleType.values()) {
      if (spellMagicTemplate.knowsSpellMagic(knownCharms, circle)) {
        // TODO: Take the sacrifice into account
        height += IVoidStateFormatConstants.LINE_HEIGHT;
      }
    }
    if (height != 0) {
      height += IVoidStateFormatConstants.TEXT_PADDING;
    }
    return height;
  }

  public void encode(PdfContentByte directContent, IGenericCharacter character,
                     IGenericDescription description, Bounds bounds)
      throws DocumentException {
    ISpellMagicTemplate spellMagicTemplate = character.getTemplate().getMagicTemplate().getSpellMagic();
    ICharm[] knownCharms = character.getLearnedCharms();
    
    Phrase phrase = new Phrase();
    for (CircleType circle : CircleType.values()) {
      if (spellMagicTemplate.knowsSpellMagic(knownCharms, circle)) {
        Chunk prefix = new Chunk(resources.getString("Initiation." + circle.getId()) + ": ", //$NON-NLS-1$ //$NON-NLS-2$
                                 headerFont);
        phrase.add(prefix);
        // TODO: Actually show the sacrifice! (or at least a blank line or two)
        phrase.add(new Chunk("\n", textFont)); //$NON-NLS-1$
      }
    }
    PdfTextEncodingUtilities.encodeText(directContent, phrase, bounds,
                                        IVoidStateFormatConstants.LINE_HEIGHT);
  }

  protected boolean knowsCharm(String charm, IGenericCharacter character) {
    ICharm[] knownCharms = character.getLearnedCharms();
    for (ICharm knownCharm : knownCharms)
        if (charm.equals(knownCharm.getId()))
          return true;
      return false;
  }

  public boolean hasContent(IGenericCharacter character) {
    // TODO: Implement!
    return true;
  }
}
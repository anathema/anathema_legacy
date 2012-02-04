package net.sf.anathema.character.reporting.pdf.rendering.boxes.initiation;

import com.lowagie.text.Chunk;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.Phrase;
import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.spells.CircleType;
import net.sf.anathema.character.generic.template.magic.ISpellMagicTemplate;
import net.sf.anathema.character.reporting.pdf.content.ReportContent;
import net.sf.anathema.character.reporting.pdf.rendering.extent.Bounds;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.IVariableContentEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.graphics.SheetGraphics;
import net.sf.anathema.character.reporting.pdf.rendering.page.IVoidStateFormatConstants;
import net.sf.anathema.lib.resources.IResources;

public class PdfInitiationEncoder implements IVariableContentEncoder {

  private Font textFont;
  private Font headerFont;
  private final IResources resources;

  public PdfInitiationEncoder(IResources resources, SheetGraphics graphics) {
    this.textFont = graphics.createTableFont();
    this.headerFont = new Font(textFont);
    this.headerFont.setStyle(Font.BOLD);
    this.resources = resources;
  }

  @Override
  public float getRequestedHeight(SheetGraphics graphics, ReportContent content, float width) {
    IGenericCharacter character = content.getCharacter();
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

  @Override
  public void encode(SheetGraphics graphics, ReportContent reportContent, Bounds bounds) throws DocumentException {
    ISpellMagicTemplate spellMagicTemplate = reportContent.getCharacter().getTemplate().getMagicTemplate().getSpellMagic();
    ICharm[] knownCharms = reportContent.getCharacter().getLearnedCharms();

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
    graphics.createSimpleColumn(bounds).withLeading(IVoidStateFormatConstants.LINE_HEIGHT).andTextPart(phrase).encode();
  }

  protected boolean knowsCharm(String charm, IGenericCharacter character) {
    ICharm[] knownCharms = character.getLearnedCharms();
    for (ICharm knownCharm : knownCharms) {
      if (charm.equals(knownCharm.getId())) {
        return true;
      }
    }
    return false;
  }

  @Override
  public String getHeader(ReportContent content) {
    return resources.getString("Sheet.Header.Initiations");
  }

  @Override
  public boolean hasContent(ReportContent content) {
    // TODO: Implement!
    return true;
  }
}

package net.sf.anathema.character.reporting.pdf.rendering.boxes.health;

import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.BaseFont;
import net.sf.anathema.lib.resources.IResources;

public class SecondEditionHealthTableEncoder extends AbstractHealthTableEncoder {

  public SecondEditionHealthTableEncoder(IResources resources, BaseFont baseFont) {
    super(resources, baseFont);
  }

  @Override
  protected final Phrase createIncapacitatedComment() {
    return new Phrase(getResources().getString("Sheet.Movement.Comment.Mobility"), getCommentFont()); //$NON-NLS-1$
  }
}

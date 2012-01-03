package net.sf.anathema.character.reporting.pdf.rendering.boxes.notes;

import net.sf.anathema.character.reporting.pdf.content.BasicContent;
import net.sf.anathema.character.reporting.pdf.rendering.EncoderIds;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.GlobalBoxContentEncoderFactory;
import net.sf.anathema.character.reporting.pdf.rendering.general.HorizontalLineBoxContentEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.IBoxContentEncoder;
import net.sf.anathema.lib.resources.IResources;

public class NotesEncoderFactory extends GlobalBoxContentEncoderFactory {

  public NotesEncoderFactory() {
    super(EncoderIds.NOTES);
  }

  @Override
  public IBoxContentEncoder create(IResources resources, BasicContent content) {
    return new HorizontalLineBoxContentEncoder(1, "Notes"); //$NON-NLS-1$
  }
}

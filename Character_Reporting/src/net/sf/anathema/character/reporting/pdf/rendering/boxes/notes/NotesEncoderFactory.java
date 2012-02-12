package net.sf.anathema.character.reporting.pdf.rendering.boxes.notes;

import net.sf.anathema.character.reporting.pdf.content.BasicContent;
import net.sf.anathema.character.reporting.pdf.rendering.EncoderIds;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.GlobalEncoderFactory;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.RegisteredEncoderFactory;
import net.sf.anathema.character.reporting.pdf.rendering.general.HorizontalLineBoxContentEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.ContentEncoder;
import net.sf.anathema.lib.resources.IResources;

@RegisteredEncoderFactory
public class NotesEncoderFactory extends GlobalEncoderFactory {

  public NotesEncoderFactory() {
    super(EncoderIds.NOTES);
  }

  @Override
  public ContentEncoder create(IResources resources, BasicContent content) {
    return new HorizontalLineBoxContentEncoder(1, resources, "Notes"); //$NON-NLS-1$
  }
}

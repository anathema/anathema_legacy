package net.sf.anathema.hero.sheet.pdf.encoder.boxes.notes;

import net.sf.anathema.hero.sheet.pdf.content.BasicContent;
import net.sf.anathema.hero.sheet.pdf.encoder.EncoderIds;
import net.sf.anathema.hero.sheet.pdf.encoder.boxes.GlobalEncoderFactory;
import net.sf.anathema.hero.sheet.pdf.encoder.boxes.RegisteredEncoderFactory;
import net.sf.anathema.hero.sheet.pdf.encoder.general.HorizontalLineBoxContentEncoder;
import net.sf.anathema.hero.sheet.pdf.encoder.general.box.ContentEncoder;
import net.sf.anathema.lib.resources.Resources;

@RegisteredEncoderFactory
public class NotesEncoderFactory extends GlobalEncoderFactory {

  public NotesEncoderFactory() {
    super(EncoderIds.NOTES);
  }

  @Override
  public ContentEncoder create(Resources resources, BasicContent content) {
    return new HorizontalLineBoxContentEncoder(1, resources, "Notes");
  }
}

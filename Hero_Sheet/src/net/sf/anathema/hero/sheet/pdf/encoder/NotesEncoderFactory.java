package net.sf.anathema.hero.sheet.pdf.encoder;

import net.sf.anathema.hero.sheet.pdf.content.BasicContent;
import net.sf.anathema.hero.sheet.pdf.encoder.boxes.ContentEncoder;
import net.sf.anathema.hero.sheet.pdf.encoder.boxes.GlobalEncoderFactory;
import net.sf.anathema.hero.sheet.pdf.encoder.boxes.HorizontalLineBoxContentEncoder;
import net.sf.anathema.hero.sheet.pdf.encoder.boxes.RegisteredEncoderFactory;
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

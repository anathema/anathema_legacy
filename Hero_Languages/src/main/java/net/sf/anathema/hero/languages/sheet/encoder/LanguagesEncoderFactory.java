package net.sf.anathema.hero.languages.sheet.encoder;

import net.sf.anathema.hero.sheet.pdf.content.BasicContent;
import net.sf.anathema.hero.sheet.pdf.encoder.EncoderIds;
import net.sf.anathema.hero.sheet.pdf.encoder.boxes.ContentEncoder;
import net.sf.anathema.hero.sheet.pdf.encoder.boxes.GlobalEncoderFactory;
import net.sf.anathema.hero.sheet.pdf.encoder.boxes.RegisteredEncoderFactory;
import net.sf.anathema.lib.resources.Resources;

@RegisteredEncoderFactory
public class LanguagesEncoderFactory extends GlobalEncoderFactory {

  public LanguagesEncoderFactory() {
    super(EncoderIds.LANGUAGES);
  }

  @Override
  public ContentEncoder create(Resources resources, BasicContent content) {
    return new LanguagesEncoder();
  }
}

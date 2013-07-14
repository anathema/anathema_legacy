package net.sf.anathema.hero.sheet.pdf.page;

import net.sf.anathema.framework.reporting.pdf.PageSize;
import net.sf.anathema.hero.sheet.pdf.content.BasicContent;
import net.sf.anathema.hero.sheet.pdf.encoder.boxes.EncoderRegistry;
import net.sf.anathema.lib.resources.Resources;

public interface PageFactory {

  PageEncoder[] create(EncoderRegistry encoderRegistry, Resources resources, PageSize pageSize);

  boolean supports(BasicContent content);
}

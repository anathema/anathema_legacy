package net.sf.anathema.character.reporting.pdf.rendering.pages;

import net.sf.anathema.character.reporting.pdf.content.BasicContent;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.EncoderRegistry;
import net.sf.anathema.character.reporting.pdf.rendering.page.PageEncoder;
import net.sf.anathema.framework.reporting.pdf.PageSize;
import net.sf.anathema.lib.resources.IResources;

public interface PageFactory {

  PageEncoder[] create(EncoderRegistry encoderRegistry, IResources resources, PageSize pageSize);

  boolean supports(BasicContent content);
}

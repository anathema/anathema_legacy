package net.sf.anathema.character.lunar.reporting.layout;

import net.sf.anathema.character.generic.type.CharacterType;
import net.sf.anathema.character.reporting.pdf.content.BasicContent;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.EncoderRegistry;
import net.sf.anathema.character.reporting.pdf.rendering.page.PageEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.page.PageConfiguration;
import net.sf.anathema.character.reporting.pdf.rendering.pages.PageFactory;
import net.sf.anathema.lib.resources.IResources;

public class LunarBeastform1stEditionPageFactory implements PageFactory {

  @Override
  public PageEncoder[] create(EncoderRegistry encoderRegistry, IResources resources, PageConfiguration configuration) {
    return new PageEncoder[] { new LunarBeastform1stEditionPageEncoder(encoderRegistry, resources, configuration) };
  }

  @Override
  public boolean supports(BasicContent content) {
    return content.isFirstEdition() && content.isOfType(CharacterType.LUNAR);
  }
}

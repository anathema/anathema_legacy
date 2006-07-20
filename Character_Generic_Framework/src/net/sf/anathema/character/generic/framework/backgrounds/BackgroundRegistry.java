package net.sf.anathema.character.generic.framework.backgrounds;

import net.disy.commons.core.util.Ensure;
import net.sf.anathema.character.generic.backgrounds.IBackgroundTemplate;
import net.sf.anathema.character.generic.impl.backgrounds.TemplateTypeBackgroundTemplate;
import net.sf.anathema.lib.registry.IdentificateRegistry;

public class BackgroundRegistry extends IdentificateRegistry<IBackgroundTemplate> {

  @Override
  public void add(IBackgroundTemplate... newBackgrounds) {
    for (IBackgroundTemplate background : newBackgrounds) {
      String backgroundId = background.getId();
      if (idRegistered(backgroundId)) {
        IBackgroundTemplate registeredBackground = getById(backgroundId);
        String message = "Duplicated background with id " + backgroundId; //$NON-NLS-1$
        Ensure.ensureArgumentTrue(message, registeredBackground instanceof TemplateTypeBackgroundTemplate);
        Ensure.ensureArgumentTrue(message, background instanceof TemplateTypeBackgroundTemplate);
        ((TemplateTypeBackgroundTemplate) registeredBackground).addContent((TemplateTypeBackgroundTemplate) background);
      }
      else {
        super.add(background);
      }
    }
  }
}
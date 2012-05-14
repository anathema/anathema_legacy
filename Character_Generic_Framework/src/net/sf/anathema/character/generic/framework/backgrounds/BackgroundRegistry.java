package net.sf.anathema.character.generic.framework.backgrounds;

import com.google.common.base.Preconditions;
import net.disy.commons.core.util.ContractFailedException;
import net.sf.anathema.character.generic.backgrounds.IBackgroundTemplate;
import net.sf.anathema.character.generic.impl.backgrounds.CharacterTypeBackgroundTemplate;
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
        if (registeredBackground instanceof TemplateTypeBackgroundTemplate) {
          Preconditions.checkArgument(background instanceof TemplateTypeBackgroundTemplate, message);
          ((TemplateTypeBackgroundTemplate) registeredBackground).addContent((TemplateTypeBackgroundTemplate) background);
        }
        else if (registeredBackground instanceof CharacterTypeBackgroundTemplate) {
          Preconditions.checkArgument(background instanceof CharacterTypeBackgroundTemplate, message);
          ((CharacterTypeBackgroundTemplate) registeredBackground).addContent((CharacterTypeBackgroundTemplate) background);
        }
        else {
          throw new ContractFailedException(message);
        }
      }
      else {
        super.add(background);
      }
    }
  }
}
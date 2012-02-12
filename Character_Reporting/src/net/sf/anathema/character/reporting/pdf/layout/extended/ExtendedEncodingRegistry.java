package net.sf.anathema.character.reporting.pdf.layout.extended;

import net.sf.anathema.character.generic.rules.IExaltedEdition;
import net.sf.anathema.character.generic.type.ICharacterType;
import net.sf.anathema.initialization.InitializationException;
import net.sf.anathema.initialization.Instantiater;
import net.sf.anathema.lib.logging.Logger;
import net.sf.anathema.lib.resources.IResources;

import java.util.Collection;
import java.util.Collections;

public class ExtendedEncodingRegistry {

  private Instantiater instantiater;
  private IResources resources;

  public ExtendedEncodingRegistry(Instantiater instantiater) {
    this.instantiater = instantiater;
  }

  public IExtendedPartEncoder getPartEncoder(ICharacterType type, IExaltedEdition edition) {
    Collection<IExtendedPartEncoder> encoders = createEncoders();
    for (IExtendedPartEncoder encoder : encoders) {
      RegisteredPartEncoder annotation = encoder.getClass().getAnnotation(RegisteredPartEncoder.class);
      if (annotation.characterType() == type && annotation.edition() == edition) {
        return encoder;
      }
    }
    return null;
  }

  private Collection<IExtendedPartEncoder> createEncoders() {
    try {
      return instantiater.instantiateAll(RegisteredPartEncoder.class, resources);
    } catch (InitializationException e) {
      Logger.getLogger(ExtendedEncodingRegistry.class).error("Could not instantiate part encoders.", e);
      return Collections.emptyList();
    }
  }

  public void setResources(IResources resources) {
    this.resources = resources;
  }
}
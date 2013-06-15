package net.sf.anathema.character.presenter.initializers;

import net.sf.anathema.character.generic.framework.CharacterGenericsExtractor;
import net.sf.anathema.character.model.CharacterModelGroup;
import net.sf.anathema.framework.IApplicationModel;
import net.sf.anathema.initialization.Instantiater;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Initializers {

  private final IApplicationModel applicationModel;
  private final Instantiater instantiater;

  public Initializers(IApplicationModel applicationModel) {
    this.instantiater = CharacterGenericsExtractor.getGenerics(applicationModel).getInstantiater();
    this.applicationModel = applicationModel;
  }

  public List<CharacterModelInitializer> getInOrderFor(CharacterModelGroup group) {
    ArrayList<CharacterModelInitializer> initializers = new ArrayList<>();
    Collection<CharacterModelInitializer> collection = instantiater.instantiateOrdered(RegisteredInitializer.class, applicationModel);
    for (CharacterModelInitializer initializer : collection) {
      CharacterModelGroup targetGroup = initializer.getClass().getAnnotation(RegisteredInitializer.class).value();
      if (targetGroup.equals(group)) {
        initializers.add(initializer);
      }
    }
    return initializers;
  }
}
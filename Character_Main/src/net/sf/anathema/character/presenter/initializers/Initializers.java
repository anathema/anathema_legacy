package net.sf.anathema.character.presenter.initializers;

import net.sf.anathema.character.model.CharacterModelGroup;
import net.sf.anathema.framework.IApplicationModel;

import java.util.ArrayList;
import java.util.List;

public class Initializers {

  private IApplicationModel applicationModel;

  public Initializers(IApplicationModel applicationModel) {
    this.applicationModel = applicationModel;
  }

  public List<CoreModelInitializer> getInOrderFor(CharacterModelGroup group) {
    ArrayList<CoreModelInitializer> initializers = new ArrayList<>();
    switch (group) {
      case Outline: {
        initializers.add(new DescriptionInitializer());
        initializers.add(new ConceptInitializer());
        break;
      }
      case NaturalTraits: {
        initializers.add(new AttributesInitializer());
        initializers.add(new AbilitiesInitializer());
        break;
      }
      case SpiritualTraits: {
        initializers.add(new AdvantagesInitializer());
        break;
      }
      case Magic: {
        initializers.add(new CharmInitializer(applicationModel));
        initializers.add(new ComboInitializer(applicationModel));
        initializers.add(new SorceryInitializer(applicationModel));
        initializers.add(new NecromancyInitializer(applicationModel));
        break;
      }
      case Miscellaneous: {
        break;
      }
    }
    return initializers;
  }
}
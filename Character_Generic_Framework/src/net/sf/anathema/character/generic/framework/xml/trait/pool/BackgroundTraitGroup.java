package net.sf.anathema.character.generic.framework.xml.trait.pool;

import net.sf.anathema.character.generic.backgrounds.IBackgroundTemplate;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.generic.traits.groups.ITraitTypeGroup;
import net.sf.anathema.lib.registry.IIdentificateRegistry;

public class BackgroundTraitGroup implements ITraitTypeGroup {

  private final IIdentificateRegistry<IBackgroundTemplate> registry;

  public BackgroundTraitGroup(IIdentificateRegistry<IBackgroundTemplate> registry) {
    this.registry = registry;
  }

  public boolean contains(ITraitType traitType) {
    return registry.idRegistered(traitType.getId());
  }

  public ITraitType[] getAllGroupTypes() {
    return registry.getAll().toArray(new ITraitType[0]);
  }

  public ITraitType getById(String typeId) {
    return registry.getById(typeId);
  }
}
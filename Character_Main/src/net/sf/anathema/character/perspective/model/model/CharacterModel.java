package net.sf.anathema.character.perspective.model.model;

import net.sf.anathema.character.perspective.DescriptiveFeatures;
import net.sf.anathema.character.perspective.LoadedDescriptiveFeatures;
import net.sf.anathema.framework.repository.IItem;

public class CharacterModel {

  private DescriptiveFeatures descriptiveFeatures;
  private IItem item;

  public CharacterModel(DescriptiveFeatures descriptiveFeatures) {
    this.descriptiveFeatures = descriptiveFeatures;
  }

  public CharacterModel(CharacterIdentifier identifier, IItem item) {
    this.descriptiveFeatures = new LoadedDescriptiveFeatures(identifier, item);
    this.item = item;
  }

  public DescriptiveFeatures getDescriptiveFeatures() {
    return descriptiveFeatures;
  }

  public void setItem(IItem item) {
    this.item = item;
    this.descriptiveFeatures = new LoadedDescriptiveFeatures(descriptiveFeatures.getIdentifier(), item);
  }

  public boolean isLoaded() {
    return item != null;
  }

  public IItem getItem() {
    return item;
  }
}

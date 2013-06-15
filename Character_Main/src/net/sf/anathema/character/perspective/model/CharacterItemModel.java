package net.sf.anathema.character.perspective.model;

import net.sf.anathema.character.model.ICharacter;
import net.sf.anathema.character.perspective.DescriptiveFeatures;
import net.sf.anathema.character.perspective.LoadedDescriptiveFeatures;
import net.sf.anathema.framework.repository.IItem;
import net.sf.anathema.framework.repository.IItemListener;
import net.sf.anathema.lib.control.IChangeListener;
import org.jmock.example.announcer.Announcer;

public class CharacterItemModel {

  private DescriptiveFeatures descriptiveFeatures;
  private IItem item;
  private final Announcer<IChangeListener> featuresChangeAnnouncer = Announcer.to(IChangeListener.class);

  public CharacterItemModel(DescriptiveFeatures descriptiveFeatures) {
    this.descriptiveFeatures = descriptiveFeatures;
  }

  public CharacterItemModel(CharacterIdentifier identifier, IItem item) {
    setItem(identifier, item);
  }

  public DescriptiveFeatures getDescriptiveFeatures() {
    return descriptiveFeatures;
  }

  public void setItem(IItem item) {
    CharacterIdentifier identifier = descriptiveFeatures.getIdentifier();
    setItem(identifier, item);
  }

  private void setItem(CharacterIdentifier identifier, IItem item) {
    this.item = item;
    this.descriptiveFeatures = new LoadedDescriptiveFeatures(identifier, item);
    item.addItemListener(new IItemListener() {
      @Override
      public void printNameChanged(String newName) {
        featuresChangeAnnouncer.announce().changeOccurred();
      }
    });
    ICharacter character = (ICharacter) item.getItemData();
    character.getCharacterConcept().getCaste().addChangeListener(new AnnouncingChangeListener());
    item.addDirtyListener(new AnnouncingChangeListener());
  }

  public boolean isLoaded() {
    return item != null;
  }

  public IItem getItem() {
    return item;
  }

  public void whenFeaturesChange(IChangeListener listener) {
    featuresChangeAnnouncer.addListener(listener);
  }

  private class AnnouncingChangeListener implements IChangeListener {
    @Override
    public void changeOccurred() {
      featuresChangeAnnouncer.announce().changeOccurred();
    }
  }
}

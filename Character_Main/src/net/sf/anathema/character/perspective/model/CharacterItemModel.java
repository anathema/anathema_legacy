package net.sf.anathema.character.perspective.model;

import net.sf.anathema.character.main.model.concept.HeroConceptFetcher;
import net.sf.anathema.character.perspective.DescriptiveFeatures;
import net.sf.anathema.character.perspective.LoadedDescriptiveFeatures;
import net.sf.anathema.framework.repository.IItem;
import net.sf.anathema.framework.repository.IItemListener;
import net.sf.anathema.framework.repository.Item;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.lib.control.IChangeListener;
import org.jmock.example.announcer.Announcer;

public class CharacterItemModel {

  private DescriptiveFeatures descriptiveFeatures;
  private Item item;
  private final Announcer<IChangeListener> featuresChangeAnnouncer = Announcer.to(IChangeListener.class);

  public CharacterItemModel(DescriptiveFeatures descriptiveFeatures) {
    this.descriptiveFeatures = descriptiveFeatures;
  }

  public CharacterItemModel(CharacterIdentifier identifier, Item item) {
    setItem(identifier, item);
  }

  public DescriptiveFeatures getDescriptiveFeatures() {
    return descriptiveFeatures;
  }

  public void setItem(Item item) {
    CharacterIdentifier identifier = descriptiveFeatures.getIdentifier();
    setItem(identifier, item);
  }

  private void setItem(CharacterIdentifier identifier, Item item) {
    this.item = item;
    this.descriptiveFeatures = new LoadedDescriptiveFeatures(identifier, item);
    item.addItemListener(new IItemListener() {
      @Override
      public void printNameChanged(String newName) {
        featuresChangeAnnouncer.announce().changeOccurred();
      }
    });
    Hero hero = (Hero) item.getItemData();
    HeroConceptFetcher.fetch(hero).getCaste().addChangeListener(new AnnouncingChangeListener());
    item.getChangeManagement().addDirtyListener(new AnnouncingChangeListener());
  }

  public boolean isLoaded() {
    return item != null;
  }

  public Item getItem() {
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

package net.sf.anathema.hero.perspective.model;

import net.sf.anathema.hero.concept.HeroConceptFetcher;
import net.sf.anathema.hero.description.HeroDescriptionFetcher;
import net.sf.anathema.hero.perspective.DescriptiveFeatures;
import net.sf.anathema.hero.perspective.LoadedDescriptiveFeatures;
import net.sf.anathema.framework.repository.Item;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.lib.control.ChangeListener;
import net.sf.anathema.lib.control.ObjectValueListener;
import org.jmock.example.announcer.Announcer;

public class CharacterItemModel {

  private DescriptiveFeatures descriptiveFeatures;
  private Item item;
  private final Announcer<ChangeListener> featuresChangeAnnouncer = Announcer.to(ChangeListener.class);

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
    Hero hero = (Hero) item.getItemData();
    HeroConceptFetcher.fetch(hero).getCaste().addChangeListener(new AnnouncingChangeListener());
    HeroDescriptionFetcher.fetch(hero).getName().addTextChangedListener(new ObjectValueListener<String>() {
      @Override
      public void valueChanged(String newValue) {
        featuresChangeAnnouncer.announce().changeOccurred();
      }
    });
    item.getChangeManagement().addDirtyListener(new AnnouncingChangeListener());
  }

  public boolean isLoaded() {
    return item != null;
  }

  public Item getItem() {
    return item;
  }

  public void whenFeaturesChange(ChangeListener listener) {
    featuresChangeAnnouncer.addListener(listener);
  }

  private class AnnouncingChangeListener implements ChangeListener {
    @Override
    public void changeOccurred() {
      featuresChangeAnnouncer.announce().changeOccurred();
    }
  }
}

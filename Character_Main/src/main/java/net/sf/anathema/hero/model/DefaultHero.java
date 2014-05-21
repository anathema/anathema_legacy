package net.sf.anathema.hero.model;

import net.sf.anathema.hero.template.HeroTemplate;
import net.sf.anathema.hero.model.change.ChangeAnnouncer;
import net.sf.anathema.hero.model.change.ChangeAnnouncerImpl;
import net.sf.anathema.lib.util.Identifier;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class DefaultHero implements Hero {

  private final ChangeAnnouncer changeAnnouncer = new ChangeAnnouncerImpl();
  private Map<String, HeroModel> modelsById = new HashMap<>();
  private boolean fullyLoaded = false;
  private final HeroTemplate template;
  private List<HeroModel> orderedModels = new ArrayList<>();

  public DefaultHero(HeroTemplate template) {
    this.template = template;
  }

  @Override
  public HeroTemplate getTemplate() {
    return template;
  }

  @Override
  public ChangeAnnouncer getChangeAnnouncer() {
    return changeAnnouncer;
  }

  @Override
  public <M extends HeroModel> M getModel(Identifier id) {
    return (M) modelsById.get(id.getId());
  }

  @Override
  public boolean isFullyLoaded() {
    return fullyLoaded;
  }

  public void setFullyLoaded(boolean fullyLoaded) {
    this.fullyLoaded = fullyLoaded;
  }

  public void addModel(HeroModel model) {
    orderedModels.add(model);
    modelsById.put(model.getId().getId(), model);
  }

  @Override
  public Iterator<HeroModel> iterator() {
    return orderedModels.iterator();
  }
}

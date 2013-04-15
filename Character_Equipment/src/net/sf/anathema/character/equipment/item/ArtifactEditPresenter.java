package net.sf.anathema.character.equipment.item;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import net.sf.anathema.character.equipment.item.events.UserInput;
import net.sf.anathema.character.equipment.item.events.ViewShouldShow;
import net.sf.anathema.lib.util.Identifier;

import static net.sf.anathema.character.equipment.item.events.ArtifactEvents.Stats_Name;

public class ArtifactEditPresenter {
  private final MutableArtifactStats stats;
  private final EventBus eventBus;

  public ArtifactEditPresenter(MutableArtifactStats stats, EventBus eventBus) {
    this.stats = stats;
    this.eventBus = eventBus;
  }

  public void initPresentation() {
    postStats();
  }

  private void postStats() {
    eventBus.post(new ViewShouldShow(Stats_Name, stats.getId()));
    eventBus.register(new Object() {
      @Subscribe
      public void userEnteredSomething(UserInput input) {
        if (input.is(Stats_Name)) {
          stats.setName(new Identifier(input.as(String.class)));
        }
      }
    });
  }
}

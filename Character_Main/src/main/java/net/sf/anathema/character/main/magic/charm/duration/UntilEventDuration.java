package net.sf.anathema.character.main.magic.charm.duration;

import com.google.common.base.Preconditions;
import net.sf.anathema.lib.lang.ReflectionEqualsObject;
import net.sf.anathema.lib.resources.Resources;

public class UntilEventDuration extends ReflectionEqualsObject implements Duration {

  private final String event;

  public UntilEventDuration(String event) {
    Preconditions.checkNotNull(event);
    this.event = event;
  }

  public String getEvent() {
    return event;
  }

  @Override
  public String getText(Resources resources) {
    String eventText = resources.getString("Charm.Event." + getEvent());
    return resources.getString("Charm.UntilEvent", eventText);
  }
}
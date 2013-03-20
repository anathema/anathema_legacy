package net.sf.anathema.character.generic.magic.charms.duration;

import com.google.common.base.Preconditions;
import net.sf.anathema.lib.lang.ReflectionEqualsObject;
import net.sf.anathema.lib.resources.IStringResourceHandler;

public class UntilEventDuration extends ReflectionEqualsObject implements IDuration {

  private final String event;

  public UntilEventDuration(String event) {
    Preconditions.checkNotNull(event);
    this.event = event;
  }

  public String getEvent() {
    return event;
  }

  @Override
  public String getText(IStringResourceHandler resources) {
    String eventText = resources.getString("Charm.Event." + getEvent()); //$NON-NLS-1$
    return resources.getString("Charm.UntilEvent", new Object[] { eventText }); //$NON-NLS-1$
  }
}
package net.sf.anathema.character.generic.magic.charms.duration;

import net.disy.commons.core.util.Ensure;
import net.sf.anathema.lib.lang.ReflectionEqualsObject;
import net.sf.anathema.lib.resources.IStringResourceHandler;

public class UntilEventDuration extends ReflectionEqualsObject implements IDuration {

  private final String event;

  public UntilEventDuration(String event) {
    Ensure.ensureArgumentNotNull(event);
    this.event = event;
  }

  public String getEvent() {
    return event;
  }

  public void accept(IDurationVisitor visitor) {
    visitor.acceptUntilEventDuration(this);
  }

  public String getText(IStringResourceHandler resources) {
    final String eventText = resources.getString("Charm.Event." + getEvent()); //$NON-NLS-1$
    return resources.getString("Charm.UntilEvent", new Object[] { eventText }); //$NON-NLS-1$
  }
}
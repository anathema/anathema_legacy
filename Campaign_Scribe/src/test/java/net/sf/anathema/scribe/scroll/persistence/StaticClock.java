package net.sf.anathema.scribe.scroll.persistence;

import java.util.Date;

public class StaticClock implements Clock {
  private Date date;

  public StaticClock(Date date) {
    this.date = date;
  }

  @Override
  public long getCurrentTimeInMillis() {
    return date.getTime();
  }
}

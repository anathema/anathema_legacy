package net.sf.anathema.scribe.scroll.persistence;

public class SystemClock implements Clock {
  @Override
  public long getCurrentTimeInMillis() {
    return System.currentTimeMillis();
  }
}

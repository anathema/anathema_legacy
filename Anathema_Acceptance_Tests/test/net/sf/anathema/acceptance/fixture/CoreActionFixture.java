package net.sf.anathema.acceptance.fixture;

import fit.ActionFixture;

public class CoreActionFixture extends ActionFixture {

  @Override
  public void start() throws Throwable {
    super.start();
    actor.summary = summary;
  }
}
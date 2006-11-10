package net.sf.anathema.acceptance.fixture.character.intimacies;

public class SelectIntimaciesFixture extends AbstractIntimaciesRowEntryFixture {

  public String name;

  @Override
  public void enterRow() throws Exception {
    getModel().setCurrentName(name);
    getModel().commitSelection();
  }
}

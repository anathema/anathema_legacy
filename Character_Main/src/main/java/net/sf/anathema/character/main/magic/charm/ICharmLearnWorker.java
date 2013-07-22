package net.sf.anathema.character.main.magic.charm;

public interface ICharmLearnWorker extends ICharmLearnArbitrator {

  void forget(CharmImpl charm);
}
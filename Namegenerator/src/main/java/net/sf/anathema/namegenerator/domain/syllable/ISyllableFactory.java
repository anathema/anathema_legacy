package net.sf.anathema.namegenerator.domain.syllable;

public interface ISyllableFactory {

  String createToken(String lastSyllable);
}
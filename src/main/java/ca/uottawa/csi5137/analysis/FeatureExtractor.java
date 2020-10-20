package ca.uottawa.csi5137.analysis;

import ca.uottawa.csi5137.type.Features;
import de.tudarmstadt.ukp.dkpro.core.api.segmentation.type.Sentence;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.jcas.JCas;
import org.uimafit.component.JCasAnnotator_ImplBase;

import static org.apache.uima.fit.util.JCasUtil.select;

public class FeatureExtractor extends JCasAnnotator_ImplBase {

    @Override
    public void process(JCas aJCas) throws AnalysisEngineProcessException {

        for (Sentence sentence : select(aJCas, Sentence.class)) {
            Features features = new Features(aJCas);

            features.setBegin(sentence.getBegin());
            features.setEnd(sentence.getEnd());

            features.setPosition(getPosition(sentence));
            features.setSentLenInTokens(getSentLenInTokens(sentence));
            features.setNumPunct(getNumPunct(sentence));
            features.setNumPrecedingNP(getNumPrecedingNP(sentence));
            features.setNumFollowingNP(getNumFollowingNP(sentence));
            features.setFollowsPrepPhrase(getFollowsPrepPhrase(sentence));
            features.setFourPosTagsPrecedingFollowing(getFourPosTagsPrecedingFollowing(sentence));
            features.setFollowedByVBG(getFollowedByVBG(sentence));
            features.setFollowedByPrep(getFollowedByPrep(sentence));
            features.setNumFollowingAdj(getNumFollowingAdj(sentence));
            features.setFollowsVerb(getFollowsVerb(sentence));
            features.setFollowedByVerb(getFollowedByVerb(sentence));
            features.setFollowedByAdj(getFollowedByAdj(sentence));
            features.setFollowedByNPAdj(getFollowedByNPAdj(sentence));
            features.setNumTokensPrecedingFollowingInfinitiveVerb(getNumTokensPrecedingFollowingInfinitiveVerb(sentence));
            features.setNumTokensUntilNextPrep(getNumTokensUntilNextPrep(sentence));
            features.setFollowedBySeqAdjNP(getFollowedBySeqAdjNP(sentence));
            features.setDepRelType(getDepRelType(sentence));
            features.setNextFollowingVerbInWeather(getNextFollowingVerbInWeather(sentence));
            features.setNextFollowingVerbInCognition(getNextFollowingVerbInCognition(sentence));

            features.addToIndexes();
        }

    }

    public int getPosition(Sentence sentence) {
        return 0;
    }

    public int getSentLenInTokens(Sentence sentence) {
        return 0;
    }

    public int getNumPunct(Sentence sentence) {
        return 0;
    }

    public int getNumPrecedingNP(Sentence sentence) {
        return 0;
    }

    public int getNumFollowingNP(Sentence sentence) {
        return 0;
    }

    public boolean getFollowsPrepPhrase(Sentence sentence) {
        return false;
    }

    public String getFourPosTagsPrecedingFollowing(Sentence sentence) {
        return null;
    }

    public boolean getFollowedByVBG(Sentence sentence) {
        return false;
    }

    public boolean getFollowedByPrep(Sentence sentence) {
        return false;
    }

    public int getNumFollowingAdj(Sentence sentence) {
        return 0;
    }

    public boolean getFollowsVerb(Sentence sentence) {
        return false;
    }

    public boolean getFollowedByVerb(Sentence sentence) {
        return false;
    }

    public boolean getFollowedByAdj(Sentence sentence) {
        return false;
    }

    public boolean getFollowedByNPAdj(Sentence sentence) {
        return false;
    }

    public int getNumTokensPrecedingFollowingInfinitiveVerb(Sentence sentence) {
        return 0;
    }

    public int getNumTokensUntilNextPrep(Sentence sentence) {
        return 0;
    }

    public boolean getFollowedBySeqAdjNP(Sentence sentence) {
        return false;
    }

    public String getDepRelType(Sentence sentence) {
        return "null";
    }

    public boolean getNextFollowingVerbInWeather(Sentence sentence) {
        return false;
    }

    public boolean getNextFollowingVerbInCognition(Sentence sentence) {
        return false;
    }
}

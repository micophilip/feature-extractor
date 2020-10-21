package ca.uottawa.csi5137.analysis;

import ca.uottawa.csi5137.type.Features;
import de.tudarmstadt.ukp.dkpro.core.api.segmentation.type.Sentence;
import de.tudarmstadt.ukp.dkpro.core.api.segmentation.type.Token;
import de.tudarmstadt.ukp.dkpro.core.api.syntax.type.chunk.Chunk;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.jcas.JCas;
import org.uimafit.component.JCasAnnotator_ImplBase;

import java.util.List;

import static org.apache.uima.fit.util.JCasUtil.select;
import static org.apache.uima.fit.util.JCasUtil.selectCovered;

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
            features.setNumPrecedingNP(getNumPrecedingNP(sentence, aJCas));
            features.setNumFollowingNP(getNumFollowingNP(sentence, aJCas));
            features.setFollowsPrepPhrase(getFollowsPrepPhrase(sentence, aJCas));
            features.setFourPosTagsPrecedingFollowing(getFourPosTagsPrecedingFollowing(sentence, aJCas));
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
        int position = 1;
        for (Token token : selectCovered(Token.class, sentence)) {
            if ("it".equalsIgnoreCase(token.getText())) {
                return position;
            } else {
                position++;
            }
        }
        return -1;
    }

    public int getSentLenInTokens(Sentence sentence) {
        return selectCovered(Token.class, sentence).size();
    }

    public int getNumPunct(Sentence sentence) {
        int punctuationMarks = 0;
        for (Token token : selectCovered(Token.class, sentence)) {
            if ("PUNCT".equalsIgnoreCase(token.getPos().getCoarseValue())) {
                punctuationMarks++;
            }
        }
        return punctuationMarks;
    }

    public int getNumPrecedingNP(Sentence sentence, JCas aJCas) {
        int positionOfIt = 0;
        int precedingNPs = 0;
        int sentenceBegin = sentence.getBegin();
        for (Token token : selectCovered(Token.class, sentence)) {
            if ("it".equalsIgnoreCase(token.getText())) {
                positionOfIt = token.getBegin();
                break;
            }
        }

        Sentence chunked = new Sentence(aJCas, sentenceBegin, positionOfIt);

        for (Chunk chunk : selectCovered(Chunk.class, chunked)) {
            if ("NP".equalsIgnoreCase(chunk.getChunkValue())) {
                precedingNPs++;
            }
        }
        return precedingNPs;
    }

    public int getNumFollowingNP(Sentence sentence, JCas aJCas) {
        int positionOfIt = 0;
        int followingNPs = 0;
        int sentenceEnd = sentence.getEnd();
        for (Token token : selectCovered(Token.class, sentence)) {
            if ("it".equalsIgnoreCase(token.getText())) {
                positionOfIt = token.getEnd();
                break;
            }
        }

        Sentence chunked = new Sentence(aJCas, positionOfIt, sentenceEnd);

        for (Chunk chunk : selectCovered(Chunk.class, chunked)) {
            if ("NP".equalsIgnoreCase(chunk.getChunkValue())) {
                followingNPs++;
            }
        }
        return followingNPs;
    }

    public boolean getFollowsPrepPhrase(Sentence sentence, JCas aJCas) {
        int positionOfIt = 0;
        int sentenceBegin = sentence.getBegin();

        for (Token token : selectCovered(Token.class, sentence)) {
            if ("it".equalsIgnoreCase(token.getText())) {
                positionOfIt = token.getBegin();
                break;
            }
        }

        // 'it' is the first token in the sentence
        if (sentenceBegin == positionOfIt) {
            return false;
        } else {
            Sentence chunked = new Sentence(aJCas, sentenceBegin, positionOfIt);
            List<Chunk> chunks = selectCovered(Chunk.class, chunked);
            Chunk lastChunk = chunks.get(chunks.size() - 1);
            return "PP".equalsIgnoreCase(lastChunk.getChunkValue());
        }
    }

    public String getFourPosTagsPrecedingFollowing(Sentence sentence, JCas aJCas) {
        int beginIt = 0;
        int endIt = 0;
        StringBuilder fourPosTagsBeforeAfter = new StringBuilder();
        String separator = "-";
        String abs = "ABS";
        List<Token> tokens = selectCovered(Token.class, sentence);
        for (Token token : tokens) {
            if ("it".equalsIgnoreCase(token.getText())) {
                beginIt = token.getBegin();
                endIt = token.getEnd();
                break;
            }
        }
        Sentence preceding = new Sentence(aJCas, sentence.getBegin(), beginIt);
        Sentence following = new Sentence(aJCas, endIt, sentence.getEnd());

        List<Token> precedingTokens = selectCovered(Token.class, preceding);
        List<Token> followingTokens = selectCovered(Token.class, following);
        int precedingTokensSize = precedingTokens.size();
        int followingTokensSize = followingTokens.size();

        for (int i = 0; i < 4; i++) {
            if (i < precedingTokensSize) {
                Token t = precedingTokens.get(precedingTokensSize - i - 1);
                fourPosTagsBeforeAfter.append(t.getPos().getCoarseValue());
            } else {
                fourPosTagsBeforeAfter.append(abs);
            }
            fourPosTagsBeforeAfter.append(separator);
        }

        for (int i = 0; i < 4; i++) {
            if (i < followingTokensSize) {
                Token t = followingTokens.get(i);
                fourPosTagsBeforeAfter.append(t.getPos().getCoarseValue());
            } else {
                fourPosTagsBeforeAfter.append(abs);
            }
            if (i != 3) {
                fourPosTagsBeforeAfter.append(separator);
            }
        }

        return fourPosTagsBeforeAfter.toString();
    }

    public boolean getFollowedByVBG(Sentence sentence) {

        boolean foundIt = false;
        boolean followedByVBG = false;

        for (Token token : selectCovered(Token.class, sentence)) {
            if (foundIt) {
                followedByVBG = "VBG".equalsIgnoreCase(token.getPosValue());
                break;
            }
            if ("it".equalsIgnoreCase(token.getText())) {
                foundIt = true;
            }
        }
        return followedByVBG;
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

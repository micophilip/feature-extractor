package ca.uottawa.csi5137.analysis;

import ca.uottawa.csi5137.type.Features;
import de.tudarmstadt.ukp.dkpro.core.api.segmentation.type.Sentence;
import de.tudarmstadt.ukp.dkpro.core.api.segmentation.type.Token;
import de.tudarmstadt.ukp.dkpro.core.api.syntax.type.chunk.Chunk;
import de.tudarmstadt.ukp.dkpro.core.api.syntax.type.dependency.Dependency;
import net.sf.extjwnl.JWNLException;
import net.sf.extjwnl.data.IndexWord;
import net.sf.extjwnl.data.POS;
import net.sf.extjwnl.data.Synset;
import net.sf.extjwnl.dictionary.Dictionary;
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
            features.setFollowedByNPAdj(getFollowedByNPAdj(sentence, aJCas));
            features.setNumTokensPrecedingFollowingInfinitiveVerb(getNumTokensPrecedingFollowingInfinitiveVerb(sentence));
            features.setNumTokensUntilNextPrep(getNumTokensUntilNextPrep(sentence));
            features.setFollowedBySeqAdjNP(getFollowedBySeqAdjNP(sentence, aJCas));
            features.setDepRelType(getDepRelType(sentence));
            try {
                features.setNextFollowingVerbInWeather(getNextFollowingVerbInWeather(sentence));
                features.setNextFollowingVerbInCognition(getNextFollowingVerbInCognition(sentence));
            } catch (JWNLException e) {
                e.printStackTrace();
            }

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
            if (chunks.size() == 0) {
                return false;
            } else {
                Chunk lastChunk = chunks.get(chunks.size() - 1);
                return "PP".equalsIgnoreCase(lastChunk.getChunkValue());
            }
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
        boolean foundIt = false;
        boolean followedByPrep = false;

        for (Token token : selectCovered(Token.class, sentence)) {
            if (foundIt) {
                followedByPrep = "IN".equalsIgnoreCase(token.getPosValue());
                break;
            }
            if ("it".equalsIgnoreCase(token.getText())) {
                foundIt = true;
            }
        }

        return followedByPrep;
    }

    public int getNumFollowingAdj(Sentence sentence) {
        boolean foundIt = false;
        int numFollowingAdj = 0;

        for (Token token : selectCovered(Token.class, sentence)) {
            if (foundIt && "JJ".equalsIgnoreCase(token.getPosValue())) {
                numFollowingAdj++;
            }
            if ("it".equalsIgnoreCase(token.getText())) {
                foundIt = true;
            }
        }

        return numFollowingAdj;
    }

    public boolean getFollowsVerb(Sentence sentence) {
        boolean foundIt = false;
        boolean followsVerb = false;
        Token previousToken = null;

        for (Token token : selectCovered(Token.class, sentence)) {
            if (!foundIt && "it".equalsIgnoreCase(token.getText())) {
                foundIt = true;
            }

            if (foundIt && previousToken != null) {
                followsVerb = "VERB".equalsIgnoreCase(previousToken.getPos().getCoarseValue());
                break;
            }

            previousToken = token;
        }
        return followsVerb;
    }

    public boolean getFollowedByVerb(Sentence sentence) {
        boolean foundIt = false;
        boolean followedByVerb = false;

        for (Token token : selectCovered(Token.class, sentence)) {
            if (foundIt) {
                followedByVerb = "VERB".equalsIgnoreCase(token.getPos().getCoarseValue());
                break;
            }
            if ("it".equalsIgnoreCase(token.getText())) {
                foundIt = true;
            }
        }

        return followedByVerb;
    }

    public boolean getFollowedByAdj(Sentence sentence) {
        boolean foundIt = false;
        boolean followedByAdj = false;

        for (Token token : selectCovered(Token.class, sentence)) {
            if (foundIt) {
                followedByAdj = "JJ".equalsIgnoreCase(token.getPosValue());
                break;
            }
            if ("it".equalsIgnoreCase(token.getText())) {
                foundIt = true;
            }
        }

        return followedByAdj;
    }

    public boolean getFollowedByNPAdj(Sentence sentence, JCas aJCas) {
        int positionOfIt = 0;
        boolean npContainsAdj = false;

        for (Token token : selectCovered(Token.class, sentence)) {
            if ("it".equalsIgnoreCase(token.getText())) {
                positionOfIt = token.getEnd();
                break;
            }
        }

        Sentence chunked = new Sentence(aJCas, positionOfIt, sentence.getEnd());
        List<Chunk> chunks = selectCovered(Chunk.class, chunked);
        if (chunks.size() > 0) {
            Chunk firstChunk = chunks.get(0);
            if ("NP".equalsIgnoreCase(firstChunk.getChunkValue())) {
                Sentence tokenizedChunk = new Sentence(aJCas, firstChunk.getBegin(), firstChunk.getEnd());
                for (Token tokenInNP : selectCovered(Token.class, tokenizedChunk)) {
                    if ("JJ".equalsIgnoreCase(tokenInNP.getPosValue())) {
                        npContainsAdj = true;
                        break;
                    }
                }
            }
        }

        return npContainsAdj;
    }

    public int getNumTokensPrecedingFollowingInfinitiveVerb(Sentence sentence) {

        boolean foundIt = false;
        int tokensTillInfinitiveVerb = 0;
        boolean foundInfinitiveVerb = false;
        boolean foundTo = false;

        for (Token token : selectCovered(Token.class, sentence)) {
            if (foundIt) {
                tokensTillInfinitiveVerb++;
                if ("TO".equalsIgnoreCase(token.getPosValue())) {
                    foundTo = true;
                    continue;
                }
            }

            if (foundTo) {
                if ("VB".equalsIgnoreCase(token.getPosValue())) {
                    foundInfinitiveVerb = true;
                    break;
                }
            }

            if (!foundIt && "it".equalsIgnoreCase(token.getText())) {
                foundIt = true;
            }
        }

        if (foundInfinitiveVerb) return tokensTillInfinitiveVerb - 2;   // Do not count the infinitive verb itself
        else return 0;

    }

    public int getNumTokensUntilNextPrep(Sentence sentence) {
        boolean foundIt = false;
        int tokensUntilNextPrep = 0;
        boolean foundPrep = false;

        for (Token token : selectCovered(Token.class, sentence)) {

            if (foundIt) {
                if ("IN".equalsIgnoreCase(token.getPosValue())) {
                    foundPrep = true;
                    break;
                }
                tokensUntilNextPrep++;
            }


            if (!foundIt && "it".equalsIgnoreCase(token.getText())) {
                foundIt = true;
            }
        }

        if (foundPrep) return tokensUntilNextPrep;
        else return 0;
    }

    public boolean getFollowedBySeqAdjNP(Sentence sentence, JCas aJCas) {

        int positionOfAdj = 0;
        boolean foundIt = false;

        for (Token token : selectCovered(Token.class, sentence)) {
            if (foundIt) {
                if ("JJ".equalsIgnoreCase(token.getPosValue())) {
                    positionOfAdj = token.getEnd();
                    break;
                } else {
                    return false;
                }
            } else if ("it".equalsIgnoreCase(token.getText())) {
                foundIt = true;
            }
        }

        Sentence chunked = new Sentence(aJCas, positionOfAdj, sentence.getEnd());

        List<Chunk> followingChunks = selectCovered(Chunk.class, chunked);
        if (followingChunks.size() == 0) {
            return false;
        } else {
            Chunk followingChunk = followingChunks.get(0);
            return "NP".equalsIgnoreCase(followingChunk.getChunkValue());
        }

    }

    public String getDepRelType(Sentence sentence) {
        for (Dependency dependency : selectCovered(Dependency.class, sentence)) {
            if ("it".equalsIgnoreCase(dependency.getGovernor().getText()))
                System.out.println(String.format("it is the governor of %s", dependency.getDependent().getText()));
            if ("it".equalsIgnoreCase(dependency.getDependent().getText())) {
                return dependency.getDependencyType();
            }
        }

        return "ABS";
    }

    public boolean getNextFollowingVerbInWeather(Sentence sentence) throws JWNLException {

        boolean foundIt = false;
        Dictionary dictionary = Dictionary.getDefaultResourceInstance();

        for (Token token : selectCovered(Token.class, sentence)) {

            if (foundIt) {
                if ("VERB".equalsIgnoreCase(token.getPos().getCoarseValue())) {
                    IndexWord iw = dictionary.lookupIndexWord(POS.VERB, token.getText());
                    if (iw != null && iw.getSenses().size() > 0) {
                        for (Synset synset : iw.getSenses()) {
                            return "verb.weather".equalsIgnoreCase(synset.getLexFileName());
                        }
                    } else {
                        return false;
                    }
                } else {
                    return false;
                }
            } else if ("it".equalsIgnoreCase(token.getText())) {
                foundIt = true;
            }
        }

        return false;
    }

    public boolean getNextFollowingVerbInCognition(Sentence sentence) throws JWNLException {
        boolean foundIt = false;
        Dictionary dictionary = Dictionary.getDefaultResourceInstance();

        for (Token token : selectCovered(Token.class, sentence)) {

            if (foundIt) {
                if ("VERB".equalsIgnoreCase(token.getPos().getCoarseValue())) {
                    IndexWord iw = dictionary.lookupIndexWord(POS.VERB, token.getText());
                    if (iw != null && iw.getSenses().size() > 0) {
                        for (Synset synset : iw.getSenses()) {
                            return "verb.cognition".equalsIgnoreCase(synset.getLexFileName());
                        }
                    } else {
                        return false;
                    }
                } else {
                    return false;
                }
            } else if ("it".equalsIgnoreCase(token.getText())) {
                foundIt = true;
            }
        }

        return false;
    }
}

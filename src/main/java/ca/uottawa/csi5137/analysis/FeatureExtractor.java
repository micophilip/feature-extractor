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
            List<Token> tokens = selectCovered(Token.class, sentence);
            for (Token token : tokens) {
                if ("it".equalsIgnoreCase(token.getText())) {
                    Features features = new Features(aJCas);
                    features.setBegin(sentence.getBegin());
                    features.setEnd(sentence.getEnd());
                    features.setPosition(getPosition(sentence, token.getBegin()));
                    features.setSentLenInTokens(getSentLenInTokens(sentence));
                    features.setNumPunct(getNumPunct(sentence));
                    features.setNumPrecedingNP(getNumPrecedingNP(sentence, aJCas, token.getBegin()));
                    features.setNumFollowingNP(getNumFollowingNP(sentence, aJCas, token.getEnd()));
                    features.setFollowsPrepPhrase(getFollowsPrepPhrase(sentence, aJCas, token.getBegin()));
                    features.setFourPosTagsPrecedingFollowing(getFourPosTagsPrecedingFollowing(sentence, aJCas, token.getBegin()));
                    features.setFollowedByVBG(getFollowedByVBG(sentence, token.getBegin()));
                    features.setFollowedByPrep(getFollowedByPrep(sentence, token.getBegin()));
                    features.setNumFollowingAdj(getNumFollowingAdj(sentence, token.getBegin()));
                    features.setFollowsVerb(getFollowsVerb(sentence, token.getBegin()));
                    features.setFollowedByVerb(getFollowedByVerb(sentence, token.getBegin()));
                    features.setFollowedByAdj(getFollowedByAdj(sentence, token.getBegin()));
                    features.setFollowedByNPAdj(getFollowedByNPAdj(sentence, aJCas, token.getBegin()));
                    features.setNumTokensPrecedingFollowingInfinitiveVerb(getNumTokensPrecedingFollowingInfinitiveVerb(sentence, token.getBegin()));
                    features.setNumTokensUntilNextPrep(getNumTokensUntilNextPrep(sentence, token.getBegin()));
                    features.setFollowedBySeqAdjNP(getFollowedBySeqAdjNP(sentence, aJCas, token.getBegin()));
                    features.setDepRelType(getDepRelType(sentence, token.getBegin()));
                    try {
                        features.setNextFollowingVerbInWeather(getNextFollowingVerbInWeather(sentence, token.getBegin()));
                        features.setNextFollowingVerbInCognition(getNextFollowingVerbInCognition(sentence, token.getBegin()));
                    } catch (JWNLException e) {
                        e.printStackTrace();
                    }
                    features.addToIndexes();
                }
            }

        }

    }

    /**
     * F1
     * @param sentence
     * @param tokenBegin
     * @return
     */
    public int getPosition(Sentence sentence, int tokenBegin) {
        int position = 1;
        for (Token token : selectCovered(Token.class, sentence)) {
            if ("it".equalsIgnoreCase(token.getText()) && token.getBegin() == tokenBegin) {
                return position;
            } else {
                position++;
            }
        }
        return -1;
    }

    /**
     * F2
     * @param sentence
     * @return
     */
    public int getSentLenInTokens(Sentence sentence) {
        return selectCovered(Token.class, sentence).size();
    }

    /**
     * F3
     * @param sentence
     * @return
     */
    public int getNumPunct(Sentence sentence) {
        int punctuationMarks = 0;
        List<Token> tokens = selectCovered(Token.class, sentence);
        for (int i = 0; i < tokens.size(); i++) {
            Token token = tokens.get(i);
            Token previousToken = (i - 1) >= 0 ? tokens.get(i - 1) : null;
            Token priorToken = (i - 2) >= 0 ? tokens.get(i - 2) : null;
            if (".".equalsIgnoreCase(token.getText()) && previousToken != null && priorToken != null
                    && ".".equalsIgnoreCase(previousToken.getText()) && ".".equalsIgnoreCase(priorToken.getText())) {
                punctuationMarks--;  // Handle ellipsis as one punctuation mark according to Prof's post on Piazza
            } else if ("PUNCT".equalsIgnoreCase(token.getPos().getCoarseValue())) {
                punctuationMarks++;
            }
        }

        return punctuationMarks;
    }

    /**
     * F4
     * @param sentence
     * @param aJCas
     * @param tokenBegin
     * @return
     */
    public int getNumPrecedingNP(Sentence sentence, JCas aJCas, int tokenBegin) {
        int positionOfIt = 0;
        int precedingNPs = 0;
        int sentenceBegin = sentence.getBegin();
        for (Token token : selectCovered(Token.class, sentence)) {
            if ("it".equalsIgnoreCase(token.getText()) && token.getBegin() == tokenBegin) {
                positionOfIt = token.getBegin();
                break;
            }
        }

        Sentence chunked = new Sentence(aJCas, sentenceBegin, positionOfIt);

        for (Chunk chunk : selectCovered(Chunk.class, chunked)) {
            // Check for NC - Non-recursive noun phrase (atomic) as per assignment requirement
            if ("NC".equalsIgnoreCase(chunk.getType().getShortName())) {
                precedingNPs++;
            }
        }
        return precedingNPs;
    }

    /**
     * F5
     * @param sentence
     * @param aJCas
     * @param tokenEnd
     * @return
     */
    public int getNumFollowingNP(Sentence sentence, JCas aJCas, int tokenEnd) {
        int positionOfIt = 0;
        int followingNPs = 0;
        int sentenceEnd = sentence.getEnd();
        for (Token token : selectCovered(Token.class, sentence)) {
            if ("it".equalsIgnoreCase(token.getText()) && token.getEnd() == tokenEnd) {
                positionOfIt = token.getEnd();
                break;
            }
        }

        Sentence chunked = new Sentence(aJCas, positionOfIt, sentenceEnd);

        for (Chunk chunk : selectCovered(Chunk.class, chunked)) {
            // Check for NC - Non-recursive noun phrase (atomic) as per assignment requirement
            if ("NC".equalsIgnoreCase(chunk.getType().getShortName())) {
                followingNPs++;
            }
        }
        return followingNPs;
    }

    /**
     * F6
     * @param sentence
     * @param aJCas
     * @param tokenBegin
     * @return
     */
    public boolean getFollowsPrepPhrase(Sentence sentence, JCas aJCas, int tokenBegin) {
        int positionOfIt = 0;
        int sentenceBegin = sentence.getBegin();

        for (Token token : selectCovered(Token.class, sentence)) {
            if ("it".equalsIgnoreCase(token.getText()) && token.getBegin() == tokenBegin) {
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

    /**
     * F7
     * @param sentence
     * @param aJCas
     * @param tokenBegin
     * @return
     */
    public String getFourPosTagsPrecedingFollowing(Sentence sentence, JCas aJCas, int tokenBegin) {
        int beginIt = 0;
        int endIt = 0;
        StringBuilder fourPosTagsBeforeAfter = new StringBuilder();
        String separator = "-";
        String abs = "ABS";
        List<Token> tokens = selectCovered(Token.class, sentence);
        for (Token token : tokens) {
            if ("it".equalsIgnoreCase(token.getText()) && token.getBegin() == tokenBegin) {
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

        for (int i = 3; i >= 0; i--) {
            if (precedingTokensSize <= i) {
                fourPosTagsBeforeAfter.append(abs);
            } else {
                Token t = precedingTokens.get(precedingTokensSize - i - 1);
                fourPosTagsBeforeAfter.append(t.getPos().getCoarseValue());
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
            if (i != 3) fourPosTagsBeforeAfter.append(separator);
        }

        return fourPosTagsBeforeAfter.toString();
    }

    /**
     * F8
     * @param sentence
     * @param tokenBegin
     * @return
     */
    public boolean getFollowedByVBG(Sentence sentence, int tokenBegin) {

        boolean foundIt = false;

        for (Token token : selectCovered(Token.class, sentence)) {
            if (foundIt && "VBG".equalsIgnoreCase(token.getPosValue())) {
                return true;
            }
            if ("it".equalsIgnoreCase(token.getText()) && token.getBegin() == tokenBegin) {
                foundIt = true;
            }
        }
        return false;
    }

    /**
     * F9
     * @param sentence
     * @param tokenBegin
     * @return
     */
    public boolean getFollowedByPrep(Sentence sentence, int tokenBegin) {
        boolean foundIt = false;

        for (Token token : selectCovered(Token.class, sentence)) {
            if (foundIt && "IN".equalsIgnoreCase(token.getPosValue())) {
                return true;
            }
            if ("it".equalsIgnoreCase(token.getText()) && token.getBegin() == tokenBegin) {
                foundIt = true;
            }
        }

        return false;
    }

    /**
     * F10
     * @param sentence
     * @param tokenBegin
     * @return
     */
    public int getNumFollowingAdj(Sentence sentence, int tokenBegin) {
        boolean foundIt = false;
        int numFollowingAdj = 0;

        for (Token token : selectCovered(Token.class, sentence)) {
            if (foundIt && "JJ".equalsIgnoreCase(token.getPosValue())) {
                numFollowingAdj++;
            }
            if ("it".equalsIgnoreCase(token.getText()) && token.getBegin() == tokenBegin) {
                foundIt = true;
            }
        }

        return numFollowingAdj;
    }

    /**
     * F11
     * @param sentence
     * @param tokenBegin
     * @return
     */
    public boolean getFollowsVerb(Sentence sentence, int tokenBegin) {
        boolean foundIt = false;
        boolean foundAVerb = false;

        for (Token token : selectCovered(Token.class, sentence)) {
            if (!foundIt && "it".equalsIgnoreCase(token.getText()) && token.getBegin() == tokenBegin) {
                foundIt = true;
            }

            if (foundAVerb && foundIt) return true;

            if (!foundIt && !foundAVerb) {
                foundAVerb = "VERB".equalsIgnoreCase(token.getPos().getCoarseValue());
            }

        }
        return false;
    }

    /**
     * F12
     * @param sentence
     * @param tokenBegin
     * @return
     */
    public boolean getFollowedByVerb(Sentence sentence, int tokenBegin) {
        boolean foundIt = false;

        for (Token token : selectCovered(Token.class, sentence)) {
            if (foundIt && "VERB".equalsIgnoreCase(token.getPos().getCoarseValue())) {
                return true;
            }
            if ("it".equalsIgnoreCase(token.getText()) && token.getBegin() == tokenBegin) {
                foundIt = true;
            }
        }

        return false;
    }

    /**
     * F13
     * @param sentence
     * @param tokenBegin
     * @return
     */
    public boolean getFollowedByAdj(Sentence sentence, int tokenBegin) {
        boolean foundIt = false;

        for (Token token : selectCovered(Token.class, sentence)) {
            if (foundIt && "JJ".equalsIgnoreCase(token.getPosValue())) {
                return true;
            }
            if ("it".equalsIgnoreCase(token.getText()) && token.getBegin() == tokenBegin) {
                foundIt = true;
            }
        }

        return false;
    }

    /**
     * F14
     * @param sentence
     * @param aJCas
     * @param tokenBegin
     * @return
     */
    public boolean getFollowedByNPAdj(Sentence sentence, JCas aJCas, int tokenBegin) {
        int positionOfIt = 0;

        for (Token token : selectCovered(Token.class, sentence)) {
            if ("it".equalsIgnoreCase(token.getText()) && token.getBegin() == tokenBegin) {
                positionOfIt = token.getEnd();
                break;
            }
        }

        Sentence chunked = new Sentence(aJCas, positionOfIt, sentence.getEnd());
        List<Chunk> chunks = selectCovered(Chunk.class, chunked);

        for (Chunk chunk : chunks) {
            if ("NP".equalsIgnoreCase(chunk.getChunkValue())) {
                Sentence tokenizedChunk = new Sentence(aJCas, chunk.getBegin(), chunk.getEnd());
                for (Token tokenInNP : selectCovered(Token.class, tokenizedChunk)) {
                    if ("JJ".equalsIgnoreCase(tokenInNP.getPosValue())) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    /**
     * F15
     * @param sentence
     * @param tokenBegin
     * @return
     */
    public int getNumTokensPrecedingFollowingInfinitiveVerb(Sentence sentence, int tokenBegin) {

        boolean foundIt = false;
        int tokensTillInfinitiveVerb = 0;
        boolean foundInfinitiveVerb = false;

        List<Token> tokens = selectCovered(Token.class, sentence);

        for (int i = 0; i < tokens.size(); i++) {
            if ("it".equalsIgnoreCase(tokens.get(i).getText()) && tokens.get(i).getBegin() == tokenBegin) {
                foundIt = true;
            }

            if (foundIt) {
                if ("VB".equalsIgnoreCase(tokens.get(i).getPosValue())) {
                    if (i - 1 >= 0 && "TO".equalsIgnoreCase(tokens.get(i - 1).getPosValue())) {
                        foundInfinitiveVerb = true;
                        break;
                    }
                }
                tokensTillInfinitiveVerb++;
            }
        }

        if (foundInfinitiveVerb) return tokensTillInfinitiveVerb - 2;   // Do not count the infinitive verb itself
        else return 0;

    }

    /**
     * F16
     * @param sentence
     * @param tokenBegin
     * @return
     */
    public int getNumTokensUntilNextPrep(Sentence sentence, int tokenBegin) {
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


            if (!foundIt && "it".equalsIgnoreCase(token.getText()) && token.getBegin() == tokenBegin) {
                foundIt = true;
            }
        }

        if (foundPrep) return tokensUntilNextPrep;
        else return 0;
    }

    /**
     * F17
     * @param sentence
     * @param aJCas
     * @param tokenBegin
     * @return
     */
    public boolean getFollowedBySeqAdjNP(Sentence sentence, JCas aJCas, int tokenBegin) {

        int positionOfAdj = -1;
        boolean foundIt = false;

        for (Token token : selectCovered(Token.class, sentence)) {
            if (foundIt && "JJ".equalsIgnoreCase(token.getPosValue())) {
                positionOfAdj = token.getEnd();
                break;
            } else if ("it".equalsIgnoreCase(token.getText()) && token.getBegin() == tokenBegin) {
                foundIt = true;
            }
        }

        if (positionOfAdj < 0) return false;    // No adjective found following 'it'

        Sentence chunked = new Sentence(aJCas, positionOfAdj, sentence.getEnd());

        List<Chunk> followingChunks = selectCovered(Chunk.class, chunked);
        if (followingChunks.size() == 0) {
            return false;
        } else {
            Chunk followingChunk = followingChunks.get(0);
            return "NP".equalsIgnoreCase(followingChunk.getChunkValue());
        }

    }

    /**
     * F18
     * @param sentence
     * @param tokenBegin
     * @return
     */
    public String getDepRelType(Sentence sentence, int tokenBegin) {
        String result = "ABS";
        int closestSoFar = Integer.MAX_VALUE;
        for (Dependency dependency : selectCovered(Dependency.class, sentence)) {
            if ("it".equalsIgnoreCase(dependency.getDependent().getText()) && dependency.getDependent().getBegin() == tokenBegin) {
                int distance = Math.abs(dependency.getDependent().getBegin() - dependency.getGovernor().getBegin());
                if (distance < closestSoFar) {
                    result = dependency.getDependencyType();
                    closestSoFar = distance;
                }
            }
        }

        return result;
    }

    /**
     * F19
     * @param sentence
     * @param tokenBegin
     * @return
     * @throws JWNLException
     */
    public boolean getNextFollowingVerbInWeather(Sentence sentence, int tokenBegin) throws JWNLException {

        boolean foundIt = false;
        Dictionary dictionary = Dictionary.getDefaultResourceInstance();

        for (Token token : selectCovered(Token.class, sentence)) {

            if (foundIt) {
                if ("VERB".equalsIgnoreCase(token.getPos().getCoarseValue())) {
                    IndexWord iw = dictionary.lookupIndexWord(POS.VERB, token.getText());
                    if (iw != null && iw.getSenses().size() > 0) {
                        for (Synset synset : iw.getSenses()) {
                            if ("verb.weather".equalsIgnoreCase(synset.getLexFileName())) {
                                return true;
                            }
                        }
                    } else {
                        return false;
                    }
                } else {
                    return false;
                }
            } else if ("it".equalsIgnoreCase(token.getText()) && token.getBegin() == tokenBegin) {
                foundIt = true;
            }
        }

        return false;
    }

    /**
     * F20
     * @param sentence
     * @param tokenBegin
     * @return
     * @throws JWNLException
     */
    public boolean getNextFollowingVerbInCognition(Sentence sentence, int tokenBegin) throws JWNLException {
        boolean foundIt = false;
        Dictionary dictionary = Dictionary.getDefaultResourceInstance();

        for (Token token : selectCovered(Token.class, sentence)) {

            if (foundIt) {
                if ("VERB".equalsIgnoreCase(token.getPos().getCoarseValue())) {
                    IndexWord iw = dictionary.lookupIndexWord(POS.VERB, token.getText());
                    if (iw != null && iw.getSenses().size() > 0) {
                        for (Synset synset : iw.getSenses()) {
                            if ("verb.cognition".equalsIgnoreCase(synset.getLexFileName())) {
                                return true;
                            }
                        }
                    } else {
                        return false;
                    }
                } else {
                    return false;
                }
            } else if ("it".equalsIgnoreCase(token.getText()) && token.getBegin() == tokenBegin) {
                foundIt = true;
            }
        }

        return false;
    }
}

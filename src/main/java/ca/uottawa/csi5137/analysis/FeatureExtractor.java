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

    public int getSentLenInTokens(Sentence sentence) {
        return selectCovered(Token.class, sentence).size();
    }

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

    public boolean getFollowedByVBG(Sentence sentence, int tokenBegin) {

        boolean foundIt = false;
        boolean followedByVBG = false;

        for (Token token : selectCovered(Token.class, sentence)) {
            if (foundIt) {
                followedByVBG = "VBG".equalsIgnoreCase(token.getPosValue());
                break;
            }
            if ("it".equalsIgnoreCase(token.getText()) && token.getBegin() == tokenBegin) {
                foundIt = true;
            }
        }
        return followedByVBG;
    }

    public boolean getFollowedByPrep(Sentence sentence, int tokenBegin) {
        boolean foundIt = false;
        boolean followedByPrep = false;

        for (Token token : selectCovered(Token.class, sentence)) {
            if (foundIt) {
                followedByPrep = "IN".equalsIgnoreCase(token.getPosValue());
                break;
            }
            if ("it".equalsIgnoreCase(token.getText()) && token.getBegin() == tokenBegin) {
                foundIt = true;
            }
        }

        return followedByPrep;
    }

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

    public boolean getFollowsVerb(Sentence sentence, int tokenBegin) {
        boolean foundIt = false;
        boolean followsVerb = false;
        Token previousToken = null;

        for (Token token : selectCovered(Token.class, sentence)) {
            if (!foundIt && "it".equalsIgnoreCase(token.getText()) && token.getBegin() == tokenBegin) {
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

    public boolean getFollowedByVerb(Sentence sentence, int tokenBegin) {
        boolean foundIt = false;
        boolean followedByVerb = false;

        for (Token token : selectCovered(Token.class, sentence)) {
            if (foundIt) {
                followedByVerb = "VERB".equalsIgnoreCase(token.getPos().getCoarseValue());
                break;
            }
            if ("it".equalsIgnoreCase(token.getText()) && token.getBegin() == tokenBegin) {
                foundIt = true;
            }
        }

        return followedByVerb;
    }

    public boolean getFollowedByAdj(Sentence sentence, int tokenBegin) {
        boolean foundIt = false;
        boolean followedByAdj = false;

        for (Token token : selectCovered(Token.class, sentence)) {
            if (foundIt) {
                followedByAdj = "JJ".equalsIgnoreCase(token.getPosValue());
                break;
            }
            if ("it".equalsIgnoreCase(token.getText()) && token.getBegin() == tokenBegin) {
                foundIt = true;
            }
        }

        return followedByAdj;
    }

    public boolean getFollowedByNPAdj(Sentence sentence, JCas aJCas, int tokenBegin) {
        int positionOfIt = 0;
        boolean npContainsAdj = false;

        for (Token token : selectCovered(Token.class, sentence)) {
            if ("it".equalsIgnoreCase(token.getText()) && token.getBegin() == tokenBegin) {
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

    public int getNumTokensPrecedingFollowingInfinitiveVerb(Sentence sentence, int tokenBegin) {

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

            if (!foundIt && "it".equalsIgnoreCase(token.getText()) && token.getBegin() == tokenBegin) {
                foundIt = true;
            }
        }

        if (foundInfinitiveVerb) return tokensTillInfinitiveVerb - 2;   // Do not count the infinitive verb itself
        else return 0;

    }

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

    public boolean getFollowedBySeqAdjNP(Sentence sentence, JCas aJCas, int tokenBegin) {

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
            } else if ("it".equalsIgnoreCase(token.getText()) && token.getBegin() == tokenBegin) {
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

    public String getDepRelType(Sentence sentence, int tokenBegin) {
        for (Dependency dependency : selectCovered(Dependency.class, sentence)) {
            if ("it".equalsIgnoreCase(dependency.getDependent().getText()) && dependency.getDependent().getBegin() == tokenBegin) {
                return dependency.getDependencyType();
            }
        }

        return "ABS";
    }

    public boolean getNextFollowingVerbInWeather(Sentence sentence, int tokenBegin) throws JWNLException {

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
            } else if ("it".equalsIgnoreCase(token.getText()) && token.getBegin() == tokenBegin) {
                foundIt = true;
            }
        }

        return false;
    }

    public boolean getNextFollowingVerbInCognition(Sentence sentence, int tokenBegin) throws JWNLException {
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
            } else if ("it".equalsIgnoreCase(token.getText()) && token.getBegin() == tokenBegin) {
                foundIt = true;
            }
        }

        return false;
    }
}

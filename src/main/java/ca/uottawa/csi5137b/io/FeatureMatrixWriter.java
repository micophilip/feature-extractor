package ca.uottawa.csi5137b.io;

import ca.uottawa.csi5137b.type.Features;
import de.tudarmstadt.ukp.dkpro.core.api.metadata.type.DocumentMetaData;
import de.tudarmstadt.ukp.dkpro.core.api.segmentation.type.Sentence;
import org.apache.uima.UimaContext;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ResourceInitializationException;
import org.uimafit.component.JCasConsumer_ImplBase;
import org.uimafit.descriptor.ConfigurationParameter;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import static org.apache.uima.fit.util.JCasUtil.select;
import static org.apache.uima.fit.util.JCasUtil.selectCovered;

public class FeatureMatrixWriter extends JCasConsumer_ImplBase {

    public static final String PARAM_PATH = "path";
    @ConfigurationParameter(name = PARAM_PATH, mandatory = true,
            description = "Path to output")
    private String path;

    StringBuilder featureMatrix;

    @Override
    public void initialize(final UimaContext context) throws ResourceInitializationException {
        super.initialize(context);
        featureMatrix = new StringBuilder();
    }

    @Override
    public void process(JCas aJCas) throws AnalysisEngineProcessException {
        DocumentMetaData metadata = DocumentMetaData.get(aJCas);
        String cls = metadata.getDocumentTitle().substring(0, metadata.getDocumentTitle().indexOf(".")).toUpperCase();

        for (Sentence sentence : select(aJCas, Sentence.class)) {
            for (Features feature : selectCovered(Features.class, sentence)) {
                featureMatrix.append(feature.getPosition() + "," + feature.getSentLenInTokens() + ","
                        + feature.getNumPunct() + "," + feature.getNumPrecedingNP() + ","
                        + feature.getNumFollowingNP() + "," + feature.getFollowsPrepPhrase() + ","
                        + feature.getFourPosTagsPrecedingFollowing() + "," + feature.getFollowedByVBG() + ","
                        + feature.getFollowedByPrep() + "," + feature.getNumFollowingAdj() + ","
                        + feature.getFollowsVerb() + "," + feature.getFollowedByVerb() + ","
                        + feature.getFollowedByAdj() + "," + feature.getFollowedByNPAdj() + ","
                        + feature.getNumTokensPrecedingFollowingInfinitiveVerb() + "," + feature.getNumTokensUntilNextPrep() + ","
                        + feature.getFollowedBySeqAdjNP() + "," + feature.getDepRelType() + ","
                        + feature.getNextFollowingVerbInWeather() + "," + feature.getNextFollowingVerbInCognition() + ","
                        + cls);
                featureMatrix.append("\n");
            }
        }
    }

    /**
     * Write feature matrix to a CSV file
     *
     * @param content
     */
    public void writeToFile(String content) {
        StringBuilder header = new StringBuilder();
        header.append("position,");
        header.append("sentLenInTokens,");
        header.append("numPunct,");
        header.append("numPrecedingNP,");
        header.append("numFollowingNP,");
        header.append("followsPrepPhrase,");
        header.append("posTagBefore4,");
        header.append("posTagBefore3,");
        header.append("posTagBefore2,");
        header.append("posTagBefore1,");
        header.append("posTagAfter1,");
        header.append("posTagAfter2,");
        header.append("posTagAfter3,");
        header.append("posTagAfter4,");
        header.append("followedByVBG,");
        header.append("followedByPrep,");
        header.append("numFollowingAdj,");
        header.append("followsVerb,");
        header.append("followedByVerb,");
        header.append("followedByAdj,");
        header.append("followedByNPAdj,");
        header.append("numTokensPrecedingFollowingInfinitiveVerb,");
        header.append("numTokensUntilNextPrep,");
        header.append("followedBySeqAdjNP,");
        header.append("depRelType,");
        header.append("nextFollowingVerbInWeather,");
        header.append("nextFollowingVerbInCognition,");
        header.append("class");

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path + "output.csv"))) {
            writer.write(header.toString());
            writer.write("\n");
            writer.write(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeToArff(String content) {
        StringBuilder header = new StringBuilder();
        header.append("@relation CLAUSEANAPH-NOMANAPH\n\n");

        header.append("@attribute position numeric\n");
        header.append("@attribute sentLenInTokens numeric\n");
        header.append("@attribute numPunct numeric\n");
        header.append("@attribute numPrecedingNP numeric\n");
        header.append("@attribute numFollowingNP numeric\n");
        header.append("@attribute followsPrepPhrase {true, false}\n");
        header.append("@attribute posTagBefore4 {ADJ, ABS, DET, PUNCT, PROPN, VERB, ADP, NOUN, CONJ, PRON, ADV, NUM, INTJ, X}\n");
        header.append("@attribute posTagBefore3 {NOUN, ABS, ADJ, CONJ, PROPN, VERB, ADV, PRON, ADP, DET, PUNCT, NUM}\n");
        header.append("@attribute posTagBefore2 {DET, ABS, NOUN, VERB, ADV, ADP, PRON, INTJ, CONJ, ADJ, PROPN, PUNCT}\n");
        header.append("@attribute posTagBefore1 {VERB, ABS, ADP, PUNCT, ADV, NOUN, PRON, CONJ, DET, ADJ, PROPN}\n");
        header.append("@attribute posTagAfter1 {PUNCT, VERB, ADV, DET, ADP, CONJ, ADJ, NOUN, PRON}\n");
        header.append("@attribute posTagAfter2 {PRON, ADV, ABS, ADP, ADJ, NUM, DET, PUNCT, VERB, PROPN, CONJ, NOUN, INTJ}\n");
        header.append("@attribute posTagAfter3 {VERB, ADJ, ABS, NOUN, DET, ADP, PUNCT, PRON, CONJ, ADV, NUM, PROPN, INTJ}\n");
        header.append("@attribute posTagAfter4 {VERB, NOUN, ABS, PUNCT, DET, PRON, ADP, NUM, CONJ, ADJ, ADV, PROPN, X}\n");
        header.append("@attribute followedByVBG {true, false}\n");
        header.append("@attribute followedByPrep {true, false}\n");
        header.append("@attribute numFollowingAdj numeric\n");
        header.append("@attribute followsVerb {true, false}\n");
        header.append("@attribute followedByVerb {true, false}\n");
        header.append("@attribute followedByAdj {true, false}\n");
        header.append("@attribute followedByNPAdj {true, false}\n");
        header.append("@attribute numTokensPrecedingFollowingInfinitiveVerb numeric\n");
        header.append("@attribute numTokensUntilNextPrep numeric\n");
        header.append("@attribute followedBySeqAdjNP {true, false}\n");
        header.append("@attribute depRelType {dobj, nsubj, prep_of, prep_at, pobj, prep_to, prep_in, iobj, nsubjpass, prep_near, prep_for, dep, parataxis, prep_on, prep_with, prep_about, advmod, root, prep_across, prep_from, prep_before, prep_on_top_of, prep_against, xcomp}\n");
        header.append("@attribute nextFollowingVerbInWeather {true, false}\n");
        header.append("@attribute nextFollowingVerbInCognition {true, false}\n");

        header.append("@attribute class {CLAUSEANAPH, NOMANAPH}\n\n");
        header.append("@data");

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path + "dataset.arff"))) {
            writer.write(header.toString());
            writer.write("\n");
            writer.write(content);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void collectionProcessComplete() {
        String data = featureMatrix.toString();

        writeToFile(data);
        writeToArff(data);

        System.out.println("Created feature matrix successfully...");
    }


}

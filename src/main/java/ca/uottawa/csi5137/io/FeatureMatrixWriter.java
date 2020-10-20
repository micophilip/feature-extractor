package ca.uottawa.csi5137.io;

import ca.uottawa.csi5137.type.Features;
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
            description = "Path to the CSV")
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
                        + feature.getNextFollowingVerbInWeather() + "," + feature.getNextFollowingVerbInCognition() + ",");
            }
            featureMatrix.append(cls + "\n");
        }
    }

    /**
     * Write feature matrix to a CSV file
     *
     * @param content
     */
    public void writeToFile(String content) {
        StringBuilder header = new StringBuilder();
        header.append("position,sentLenInTokens,numPunct,numPrecedingNP,numFollowingNP,followsPrepPhrase,fourPosTagsPrecedingFollowing,followedByVBG,followedByPrep,numFollowingAdj,followsVerb,followedByVerb,followedByAdj,followedByNPAdj,numTokensPrecedingFollowingInfinitiveVerb,numTokensUntilNextPrep,followedBySeqAdjNP,depRelType,nextFollowingVerbInWeather,nextFollowingVerbInCognition,class");

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path + "output.csv"))) {
            writer.write(header.toString());
            writer.write("\n");
            writer.write(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void collectionProcessComplete() {
        String data = featureMatrix.toString().replaceAll("true", "1").replaceAll("false", "0");

        writeToFile(data);

        System.out.println("Created feature matrix CSV successfully...");
    }


}

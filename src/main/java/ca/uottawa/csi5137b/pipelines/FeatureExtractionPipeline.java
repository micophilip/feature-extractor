package ca.uottawa.csi5137b.pipelines;

import ca.uottawa.csi5137b.analysis.FeatureExtractor;
import ca.uottawa.csi5137b.io.FeatureMatrixWriter;
import de.tudarmstadt.ukp.dkpro.core.io.text.TextReader;
import de.tudarmstadt.ukp.dkpro.core.opennlp.OpenNlpChunker;
import de.tudarmstadt.ukp.dkpro.core.opennlp.OpenNlpPosTagger;
import de.tudarmstadt.ukp.dkpro.core.stanfordnlp.StanfordParser;
import de.tudarmstadt.ukp.dkpro.core.tokit.BreakIteratorSegmenter;
import org.apache.uima.UIMAException;
import org.apache.uima.analysis_engine.AnalysisEngineDescription;
import org.apache.uima.collection.CollectionReaderDescription;
import org.apache.uima.fit.pipeline.SimplePipeline;

import java.io.IOException;

import static org.apache.uima.fit.factory.AnalysisEngineFactory.createEngineDescription;
import static org.apache.uima.fit.factory.CollectionReaderFactory.createReaderDescription;
import static org.uimafit.factory.AnalysisEngineFactory.createPrimitiveDescription;

public class FeatureExtractionPipeline {

    static String INPUT_PATH = "src/main/resources/data/";
    static String OUTPUT_PATH = "src/main/resources/io/";

    public static void main(String[] args) throws IOException, UIMAException {

        CollectionReaderDescription reader = createReaderDescription(TextReader.class,
                TextReader.PARAM_SOURCE_LOCATION, INPUT_PATH, TextReader.PARAM_PATTERNS, "*.txt",
                TextReader.PARAM_LANGUAGE, "en");

        // Tokenizer
        AnalysisEngineDescription tokenizer = createEngineDescription(BreakIteratorSegmenter.class,
                BreakIteratorSegmenter.PARAM_SPLIT_AT_APOSTROPHE, true);

        // POS-Tagger
        AnalysisEngineDescription posTagger = createEngineDescription(OpenNlpPosTagger.class);

        // Chunker
        AnalysisEngineDescription chunker = createEngineDescription(OpenNlpChunker.class);

        // Dependency Parser
        AnalysisEngineDescription parser = createEngineDescription(StanfordParser.class);

        // Feature Extractor
        AnalysisEngineDescription extractor = createEngineDescription(FeatureExtractor.class);

        // Feature Matrix Writer
        AnalysisEngineDescription featureWriter = createPrimitiveDescription(FeatureMatrixWriter.class,
                FeatureMatrixWriter.PARAM_PATH, OUTPUT_PATH);

        SimplePipeline.runPipeline(reader, tokenizer, posTagger, chunker, parser, extractor, featureWriter);

    }


}

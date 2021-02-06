<p align="center">
  <a href="https://github.com/micophilip/feature-extractor/blob/master/LICENSE" alt="License">
    <img src=https://img.shields.io/apm/l/vim-mode?style=flat-square/>
  </a>
</p>

# Feature Extraction using WEKA & UIMA

This project extracts features around the "it" pronoun from text.
The features can then be fed to WEKA binary classification models to predict
whether a specific instance of "it" is clause (referring to a clause) or nominal
(referring to a specific noun phrase) anaphoric.

## Setup and execution

1. This project was built and run using Java&trade; SE Runtime Environment (build 13+33)
2. Build `pom.xml` using Maven to get all dependencies.
3. Run `main` function in `src/main/java/ca/uottawa/csi5137b/pipelines/FeatureExtractionPipeline.java`
4. The output file will be placed in the same `io` folder along with the `input.txt`.

## Paths

### Output:
`src/main/resources/io/output.csv`

### Input:
Without labels, both classes merged into one file after removing duplicates and cleanup:
`src/main/resources/io/input.txt`

Split by label:
`src/main/resources/data/clause.txt` and `src/main/resources/data/nominal.txt`

## Acknowledgment

CSI 5137B Course at U Ottawa
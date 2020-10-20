

/* First created by JCasGen Mon Oct 19 22:33:38 EDT 2020 */
package ca.uottawa.csi5137.type;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.tcas.Annotation;


/** 
 * Updated by JCasGen Mon Oct 19 22:33:38 EDT 2020
 * XML source: /Users/mico/Documents/mcs/csi5137b/assignment_2/feature-extractor/target/jcasgen/typesystem.xml
 * @generated */
public class Features extends Annotation {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(Features.class);
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int type = typeIndexID;
  /** @generated
   * @return index of the type  
   */
  @Override
  public              int getTypeIndexID() {return typeIndexID;}
 
  /** Never called.  Disable default constructor
   * @generated */
  protected Features() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated
   * @param addr low level Feature Structure reference
   * @param type the type of this Feature Structure 
   */
  public Features(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated
   * @param jcas JCas to which this Feature Structure belongs 
   */
  public Features(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated
   * @param jcas JCas to which this Feature Structure belongs
   * @param begin offset to the begin spot in the SofA
   * @param end offset to the end spot in the SofA 
  */  
  public Features(JCas jcas, int begin, int end) {
    super(jcas);
    setBegin(begin);
    setEnd(end);
    readObject();
  }   

  /** 
   * <!-- begin-user-doc -->
   * Write your own initialization here
   * <!-- end-user-doc -->
   *
   * @generated modifiable 
   */
  private void readObject() {/*default - does nothing empty block */}
     
 
    
  //*--------------*
  //* Feature: position

  /** getter for position - gets F1-The position of “it” in the sentence considering the number of tokens, e.g., “it” is the first token in the sentence “It was raining!”, so the value of this feature for our example sentence is 1.
   * @generated
   * @return value of the feature 
   */
  public int getPosition() {
    if (Features_Type.featOkTst && ((Features_Type)jcasType).casFeat_position == null)
      jcasType.jcas.throwFeatMissing("position", "ca.uottawa.csi5137.type.Features");
    return jcasType.ll_cas.ll_getIntValue(addr, ((Features_Type)jcasType).casFeatCode_position);}
    
  /** setter for position - sets F1-The position of “it” in the sentence considering the number of tokens, e.g., “it” is the first token in the sentence “It was raining!”, so the value of this feature for our example sentence is 1. 
   * @generated
   * @param v value to set into the feature 
   */
  public void setPosition(int v) {
    if (Features_Type.featOkTst && ((Features_Type)jcasType).casFeat_position == null)
      jcasType.jcas.throwFeatMissing("position", "ca.uottawa.csi5137.type.Features");
    jcasType.ll_cas.ll_setIntValue(addr, ((Features_Type)jcasType).casFeatCode_position, v);}    
   
    
  //*--------------*
  //* Feature: sentLenInTokens

  /** getter for sentLenInTokens - gets F2-The length of the sentence in terms of tokens, e.g., “It was raining!” has four tokens, so the value is 4.
   * @generated
   * @return value of the feature 
   */
  public int getSentLenInTokens() {
    if (Features_Type.featOkTst && ((Features_Type)jcasType).casFeat_sentLenInTokens == null)
      jcasType.jcas.throwFeatMissing("sentLenInTokens", "ca.uottawa.csi5137.type.Features");
    return jcasType.ll_cas.ll_getIntValue(addr, ((Features_Type)jcasType).casFeatCode_sentLenInTokens);}
    
  /** setter for sentLenInTokens - sets F2-The length of the sentence in terms of tokens, e.g., “It was raining!” has four tokens, so the value is 4. 
   * @generated
   * @param v value to set into the feature 
   */
  public void setSentLenInTokens(int v) {
    if (Features_Type.featOkTst && ((Features_Type)jcasType).casFeat_sentLenInTokens == null)
      jcasType.jcas.throwFeatMissing("sentLenInTokens", "ca.uottawa.csi5137.type.Features");
    jcasType.ll_cas.ll_setIntValue(addr, ((Features_Type)jcasType).casFeatCode_sentLenInTokens, v);}    
   
    
  //*--------------*
  //* Feature: numPunct

  /** getter for numPunct - gets F3-The number of punctuations, e.g., “It was raining!” has one punctuation mark, so the value is 1.
   * @generated
   * @return value of the feature 
   */
  public int getNumPunct() {
    if (Features_Type.featOkTst && ((Features_Type)jcasType).casFeat_numPunct == null)
      jcasType.jcas.throwFeatMissing("numPunct", "ca.uottawa.csi5137.type.Features");
    return jcasType.ll_cas.ll_getIntValue(addr, ((Features_Type)jcasType).casFeatCode_numPunct);}
    
  /** setter for numPunct - sets F3-The number of punctuations, e.g., “It was raining!” has one punctuation mark, so the value is 1. 
   * @generated
   * @param v value to set into the feature 
   */
  public void setNumPunct(int v) {
    if (Features_Type.featOkTst && ((Features_Type)jcasType).casFeat_numPunct == null)
      jcasType.jcas.throwFeatMissing("numPunct", "ca.uottawa.csi5137.type.Features");
    jcasType.ll_cas.ll_setIntValue(addr, ((Features_Type)jcasType).casFeatCode_numPunct, v);}    
   
    
  //*--------------*
  //* Feature: numPrecedingNP

  /** getter for numPrecedingNP - gets F4-The number of preceding noun phrases (that is: how many noun phrases come before a certain instance of “it”?) For F4, we count only the atomic noun phrases.
   * @generated
   * @return value of the feature 
   */
  public int getNumPrecedingNP() {
    if (Features_Type.featOkTst && ((Features_Type)jcasType).casFeat_numPrecedingNP == null)
      jcasType.jcas.throwFeatMissing("numPrecedingNP", "ca.uottawa.csi5137.type.Features");
    return jcasType.ll_cas.ll_getIntValue(addr, ((Features_Type)jcasType).casFeatCode_numPrecedingNP);}
    
  /** setter for numPrecedingNP - sets F4-The number of preceding noun phrases (that is: how many noun phrases come before a certain instance of “it”?) For F4, we count only the atomic noun phrases. 
   * @generated
   * @param v value to set into the feature 
   */
  public void setNumPrecedingNP(int v) {
    if (Features_Type.featOkTst && ((Features_Type)jcasType).casFeat_numPrecedingNP == null)
      jcasType.jcas.throwFeatMissing("numPrecedingNP", "ca.uottawa.csi5137.type.Features");
    jcasType.ll_cas.ll_setIntValue(addr, ((Features_Type)jcasType).casFeatCode_numPrecedingNP, v);}    
   
    
  //*--------------*
  //* Feature: numFollowingNP

  /** getter for numFollowingNP - gets F5-The number of noun phrases that follow a given instance of “it”. Like in F4, F5 is concerned with atomic noun phrases.
   * @generated
   * @return value of the feature 
   */
  public int getNumFollowingNP() {
    if (Features_Type.featOkTst && ((Features_Type)jcasType).casFeat_numFollowingNP == null)
      jcasType.jcas.throwFeatMissing("numFollowingNP", "ca.uottawa.csi5137.type.Features");
    return jcasType.ll_cas.ll_getIntValue(addr, ((Features_Type)jcasType).casFeatCode_numFollowingNP);}
    
  /** setter for numFollowingNP - sets F5-The number of noun phrases that follow a given instance of “it”. Like in F4, F5 is concerned with atomic noun phrases. 
   * @generated
   * @param v value to set into the feature 
   */
  public void setNumFollowingNP(int v) {
    if (Features_Type.featOkTst && ((Features_Type)jcasType).casFeat_numFollowingNP == null)
      jcasType.jcas.throwFeatMissing("numFollowingNP", "ca.uottawa.csi5137.type.Features");
    jcasType.ll_cas.ll_setIntValue(addr, ((Features_Type)jcasType).casFeatCode_numFollowingNP, v);}    
   
    
  //*--------------*
  //* Feature: followsPrepPhrase

  /** getter for followsPrepPhrase - gets F6-Does the pronoun “it” immediately follow a prepositional phrase? (Yes/No)
   * @generated
   * @return value of the feature 
   */
  public boolean getFollowsPrepPhrase() {
    if (Features_Type.featOkTst && ((Features_Type)jcasType).casFeat_followsPrepPhrase == null)
      jcasType.jcas.throwFeatMissing("followsPrepPhrase", "ca.uottawa.csi5137.type.Features");
    return jcasType.ll_cas.ll_getBooleanValue(addr, ((Features_Type)jcasType).casFeatCode_followsPrepPhrase);}
    
  /** setter for followsPrepPhrase - sets F6-Does the pronoun “it” immediately follow a prepositional phrase? (Yes/No) 
   * @generated
   * @param v value to set into the feature 
   */
  public void setFollowsPrepPhrase(boolean v) {
    if (Features_Type.featOkTst && ((Features_Type)jcasType).casFeat_followsPrepPhrase == null)
      jcasType.jcas.throwFeatMissing("followsPrepPhrase", "ca.uottawa.csi5137.type.Features");
    jcasType.ll_cas.ll_setBooleanValue(addr, ((Features_Type)jcasType).casFeatCode_followsPrepPhrase, v);}    
   
    
  //*--------------*
  //* Feature: fourPosTagsPrecedingFollowing

  /** getter for fourPosTagsPrecedingFollowing - gets F7-The part-of-speech (POS) tags of the four tokens immediately preceding and the four tokens immediately succeeding a given instance of “it”. If there are less than four tokens before/after, assign a special value, e.g., ABS (absent) to the missing POS tags.
   * @generated
   * @return value of the feature 
   */
  public String getFourPosTagsPrecedingFollowing() {
    if (Features_Type.featOkTst && ((Features_Type)jcasType).casFeat_fourPosTagsPrecedingFollowing == null)
      jcasType.jcas.throwFeatMissing("fourPosTagsPrecedingFollowing", "ca.uottawa.csi5137.type.Features");
    return jcasType.ll_cas.ll_getStringValue(addr, ((Features_Type)jcasType).casFeatCode_fourPosTagsPrecedingFollowing);}
    
  /** setter for fourPosTagsPrecedingFollowing - sets F7-The part-of-speech (POS) tags of the four tokens immediately preceding and the four tokens immediately succeeding a given instance of “it”. If there are less than four tokens before/after, assign a special value, e.g., ABS (absent) to the missing POS tags. 
   * @generated
   * @param v value to set into the feature 
   */
  public void setFourPosTagsPrecedingFollowing(String v) {
    if (Features_Type.featOkTst && ((Features_Type)jcasType).casFeat_fourPosTagsPrecedingFollowing == null)
      jcasType.jcas.throwFeatMissing("fourPosTagsPrecedingFollowing", "ca.uottawa.csi5137.type.Features");
    jcasType.ll_cas.ll_setStringValue(addr, ((Features_Type)jcasType).casFeatCode_fourPosTagsPrecedingFollowing, v);}    
   
    
  //*--------------*
  //* Feature: followedByVBG

  /** getter for followedByVBG - gets F8-Is the occurrence of “it” followed by an -ing form of a verb? (Yes/No)
   * @generated
   * @return value of the feature 
   */
  public boolean getFollowedByVBG() {
    if (Features_Type.featOkTst && ((Features_Type)jcasType).casFeat_followedByVBG == null)
      jcasType.jcas.throwFeatMissing("followedByVBG", "ca.uottawa.csi5137.type.Features");
    return jcasType.ll_cas.ll_getBooleanValue(addr, ((Features_Type)jcasType).casFeatCode_followedByVBG);}
    
  /** setter for followedByVBG - sets F8-Is the occurrence of “it” followed by an -ing form of a verb? (Yes/No) 
   * @generated
   * @param v value to set into the feature 
   */
  public void setFollowedByVBG(boolean v) {
    if (Features_Type.featOkTst && ((Features_Type)jcasType).casFeat_followedByVBG == null)
      jcasType.jcas.throwFeatMissing("followedByVBG", "ca.uottawa.csi5137.type.Features");
    jcasType.ll_cas.ll_setBooleanValue(addr, ((Features_Type)jcasType).casFeatCode_followedByVBG, v);}    
   
    
  //*--------------*
  //* Feature: followedByPrep

  /** getter for followedByPrep - gets F9-Is the occurrence of “it” followed by a preposition? (Yes/No)
   * @generated
   * @return value of the feature 
   */
  public boolean getFollowedByPrep() {
    if (Features_Type.featOkTst && ((Features_Type)jcasType).casFeat_followedByPrep == null)
      jcasType.jcas.throwFeatMissing("followedByPrep", "ca.uottawa.csi5137.type.Features");
    return jcasType.ll_cas.ll_getBooleanValue(addr, ((Features_Type)jcasType).casFeatCode_followedByPrep);}
    
  /** setter for followedByPrep - sets F9-Is the occurrence of “it” followed by a preposition? (Yes/No) 
   * @generated
   * @param v value to set into the feature 
   */
  public void setFollowedByPrep(boolean v) {
    if (Features_Type.featOkTst && ((Features_Type)jcasType).casFeat_followedByPrep == null)
      jcasType.jcas.throwFeatMissing("followedByPrep", "ca.uottawa.csi5137.type.Features");
    jcasType.ll_cas.ll_setBooleanValue(addr, ((Features_Type)jcasType).casFeatCode_followedByPrep, v);}    
   
    
  //*--------------*
  //* Feature: numFollowingAdj

  /** getter for numFollowingAdj - gets F10-The number of adjectives that follow the occurrence of “it” in the sentence.
   * @generated
   * @return value of the feature 
   */
  public int getNumFollowingAdj() {
    if (Features_Type.featOkTst && ((Features_Type)jcasType).casFeat_numFollowingAdj == null)
      jcasType.jcas.throwFeatMissing("numFollowingAdj", "ca.uottawa.csi5137.type.Features");
    return jcasType.ll_cas.ll_getIntValue(addr, ((Features_Type)jcasType).casFeatCode_numFollowingAdj);}
    
  /** setter for numFollowingAdj - sets F10-The number of adjectives that follow the occurrence of “it” in the sentence. 
   * @generated
   * @param v value to set into the feature 
   */
  public void setNumFollowingAdj(int v) {
    if (Features_Type.featOkTst && ((Features_Type)jcasType).casFeat_numFollowingAdj == null)
      jcasType.jcas.throwFeatMissing("numFollowingAdj", "ca.uottawa.csi5137.type.Features");
    jcasType.ll_cas.ll_setIntValue(addr, ((Features_Type)jcasType).casFeatCode_numFollowingAdj, v);}    
   
    
  //*--------------*
  //* Feature: followsVerb

  /** getter for followsVerb - gets F11-Is the pronoun “it” preceded by a verb? (Yes/No)
   * @generated
   * @return value of the feature 
   */
  public boolean getFollowsVerb() {
    if (Features_Type.featOkTst && ((Features_Type)jcasType).casFeat_followsVerb == null)
      jcasType.jcas.throwFeatMissing("followsVerb", "ca.uottawa.csi5137.type.Features");
    return jcasType.ll_cas.ll_getBooleanValue(addr, ((Features_Type)jcasType).casFeatCode_followsVerb);}
    
  /** setter for followsVerb - sets F11-Is the pronoun “it” preceded by a verb? (Yes/No) 
   * @generated
   * @param v value to set into the feature 
   */
  public void setFollowsVerb(boolean v) {
    if (Features_Type.featOkTst && ((Features_Type)jcasType).casFeat_followsVerb == null)
      jcasType.jcas.throwFeatMissing("followsVerb", "ca.uottawa.csi5137.type.Features");
    jcasType.ll_cas.ll_setBooleanValue(addr, ((Features_Type)jcasType).casFeatCode_followsVerb, v);}    
   
    
  //*--------------*
  //* Feature: followedByVerb

  /** getter for followedByVerb - gets F12-Is the pronoun “it” followed by a verb? (Yes/No)
   * @generated
   * @return value of the feature 
   */
  public boolean getFollowedByVerb() {
    if (Features_Type.featOkTst && ((Features_Type)jcasType).casFeat_followedByVerb == null)
      jcasType.jcas.throwFeatMissing("followedByVerb", "ca.uottawa.csi5137.type.Features");
    return jcasType.ll_cas.ll_getBooleanValue(addr, ((Features_Type)jcasType).casFeatCode_followedByVerb);}
    
  /** setter for followedByVerb - sets F12-Is the pronoun “it” followed by a verb? (Yes/No) 
   * @generated
   * @param v value to set into the feature 
   */
  public void setFollowedByVerb(boolean v) {
    if (Features_Type.featOkTst && ((Features_Type)jcasType).casFeat_followedByVerb == null)
      jcasType.jcas.throwFeatMissing("followedByVerb", "ca.uottawa.csi5137.type.Features");
    jcasType.ll_cas.ll_setBooleanValue(addr, ((Features_Type)jcasType).casFeatCode_followedByVerb, v);}    
   
    
  //*--------------*
  //* Feature: followedByAdj

  /** getter for followedByAdj - gets F13-Is the pronoun “it” followed by an adjective? (Yes/No)
   * @generated
   * @return value of the feature 
   */
  public boolean getFollowedByAdj() {
    if (Features_Type.featOkTst && ((Features_Type)jcasType).casFeat_followedByAdj == null)
      jcasType.jcas.throwFeatMissing("followedByAdj", "ca.uottawa.csi5137.type.Features");
    return jcasType.ll_cas.ll_getBooleanValue(addr, ((Features_Type)jcasType).casFeatCode_followedByAdj);}
    
  /** setter for followedByAdj - sets F13-Is the pronoun “it” followed by an adjective? (Yes/No) 
   * @generated
   * @param v value to set into the feature 
   */
  public void setFollowedByAdj(boolean v) {
    if (Features_Type.featOkTst && ((Features_Type)jcasType).casFeat_followedByAdj == null)
      jcasType.jcas.throwFeatMissing("followedByAdj", "ca.uottawa.csi5137.type.Features");
    jcasType.ll_cas.ll_setBooleanValue(addr, ((Features_Type)jcasType).casFeatCode_followedByAdj, v);}    
   
    
  //*--------------*
  //* Feature: followedByNPAdj

  /** getter for followedByNPAdj - gets F14-True if there is a noun phrase coming after the pronoun “it” and that noun phrase contains an adjective, otherwise false.
   * @generated
   * @return value of the feature 
   */
  public boolean getFollowedByNPAdj() {
    if (Features_Type.featOkTst && ((Features_Type)jcasType).casFeat_followedByNPAdj == null)
      jcasType.jcas.throwFeatMissing("followedByNPAdj", "ca.uottawa.csi5137.type.Features");
    return jcasType.ll_cas.ll_getBooleanValue(addr, ((Features_Type)jcasType).casFeatCode_followedByNPAdj);}
    
  /** setter for followedByNPAdj - sets F14-True if there is a noun phrase coming after the pronoun “it” and that noun phrase contains an adjective, otherwise false. 
   * @generated
   * @param v value to set into the feature 
   */
  public void setFollowedByNPAdj(boolean v) {
    if (Features_Type.featOkTst && ((Features_Type)jcasType).casFeat_followedByNPAdj == null)
      jcasType.jcas.throwFeatMissing("followedByNPAdj", "ca.uottawa.csi5137.type.Features");
    jcasType.ll_cas.ll_setBooleanValue(addr, ((Features_Type)jcasType).casFeatCode_followedByNPAdj, v);}    
   
    
  //*--------------*
  //* Feature: numTokensPrecedingFollowingInfinitiveVerb

  /** getter for numTokensPrecedingFollowingInfinitiveVerb - gets F15-The number of tokens coming before the following infinitive verb (if there is one), otherwise 0.
   * @generated
   * @return value of the feature 
   */
  public int getNumTokensPrecedingFollowingInfinitiveVerb() {
    if (Features_Type.featOkTst && ((Features_Type)jcasType).casFeat_numTokensPrecedingFollowingInfinitiveVerb == null)
      jcasType.jcas.throwFeatMissing("numTokensPrecedingFollowingInfinitiveVerb", "ca.uottawa.csi5137.type.Features");
    return jcasType.ll_cas.ll_getIntValue(addr, ((Features_Type)jcasType).casFeatCode_numTokensPrecedingFollowingInfinitiveVerb);}
    
  /** setter for numTokensPrecedingFollowingInfinitiveVerb - sets F15-The number of tokens coming before the following infinitive verb (if there is one), otherwise 0. 
   * @generated
   * @param v value to set into the feature 
   */
  public void setNumTokensPrecedingFollowingInfinitiveVerb(int v) {
    if (Features_Type.featOkTst && ((Features_Type)jcasType).casFeat_numTokensPrecedingFollowingInfinitiveVerb == null)
      jcasType.jcas.throwFeatMissing("numTokensPrecedingFollowingInfinitiveVerb", "ca.uottawa.csi5137.type.Features");
    jcasType.ll_cas.ll_setIntValue(addr, ((Features_Type)jcasType).casFeatCode_numTokensPrecedingFollowingInfinitiveVerb, v);}    
   
    
  //*--------------*
  //* Feature: numTokensUntilNextPrep

  /** getter for numTokensUntilNextPrep - gets F16-The number of tokens that appear between the pronoun “it” and the first following preposition (if there is a following preposition), otherwise 0.
   * @generated
   * @return value of the feature 
   */
  public int getNumTokensUntilNextPrep() {
    if (Features_Type.featOkTst && ((Features_Type)jcasType).casFeat_numTokensUntilNextPrep == null)
      jcasType.jcas.throwFeatMissing("numTokensUntilNextPrep", "ca.uottawa.csi5137.type.Features");
    return jcasType.ll_cas.ll_getIntValue(addr, ((Features_Type)jcasType).casFeatCode_numTokensUntilNextPrep);}
    
  /** setter for numTokensUntilNextPrep - sets F16-The number of tokens that appear between the pronoun “it” and the first following preposition (if there is a following preposition), otherwise 0. 
   * @generated
   * @param v value to set into the feature 
   */
  public void setNumTokensUntilNextPrep(int v) {
    if (Features_Type.featOkTst && ((Features_Type)jcasType).casFeat_numTokensUntilNextPrep == null)
      jcasType.jcas.throwFeatMissing("numTokensUntilNextPrep", "ca.uottawa.csi5137.type.Features");
    jcasType.ll_cas.ll_setIntValue(addr, ((Features_Type)jcasType).casFeatCode_numTokensUntilNextPrep, v);}    
   
    
  //*--------------*
  //* Feature: followedBySeqAdjNP

  /** getter for followedBySeqAdjNP - gets F17-True if there a sequence “adjective + noun phrase” following the pronoun “it”, and false otherwise.
   * @generated
   * @return value of the feature 
   */
  public boolean getFollowedBySeqAdjNP() {
    if (Features_Type.featOkTst && ((Features_Type)jcasType).casFeat_followedBySeqAdjNP == null)
      jcasType.jcas.throwFeatMissing("followedBySeqAdjNP", "ca.uottawa.csi5137.type.Features");
    return jcasType.ll_cas.ll_getBooleanValue(addr, ((Features_Type)jcasType).casFeatCode_followedBySeqAdjNP);}
    
  /** setter for followedBySeqAdjNP - sets F17-True if there a sequence “adjective + noun phrase” following the pronoun “it”, and false otherwise. 
   * @generated
   * @param v value to set into the feature 
   */
  public void setFollowedBySeqAdjNP(boolean v) {
    if (Features_Type.featOkTst && ((Features_Type)jcasType).casFeat_followedBySeqAdjNP == null)
      jcasType.jcas.throwFeatMissing("followedBySeqAdjNP", "ca.uottawa.csi5137.type.Features");
    jcasType.ll_cas.ll_setBooleanValue(addr, ((Features_Type)jcasType).casFeatCode_followedBySeqAdjNP, v);}    
   
    
  //*--------------*
  //* Feature: depRelType

  /** getter for depRelType - gets F18-The dependency relation type with the closest word to which “it” is associated as a dependent.
   * @generated
   * @return value of the feature 
   */
  public String getDepRelType() {
    if (Features_Type.featOkTst && ((Features_Type)jcasType).casFeat_depRelType == null)
      jcasType.jcas.throwFeatMissing("depRelType", "ca.uottawa.csi5137.type.Features");
    return jcasType.ll_cas.ll_getStringValue(addr, ((Features_Type)jcasType).casFeatCode_depRelType);}
    
  /** setter for depRelType - sets F18-The dependency relation type with the closest word to which “it” is associated as a dependent. 
   * @generated
   * @param v value to set into the feature 
   */
  public void setDepRelType(String v) {
    if (Features_Type.featOkTst && ((Features_Type)jcasType).casFeat_depRelType == null)
      jcasType.jcas.throwFeatMissing("depRelType", "ca.uottawa.csi5137.type.Features");
    jcasType.ll_cas.ll_setStringValue(addr, ((Features_Type)jcasType).casFeatCode_depRelType, v);}    
   
    
  //*--------------*
  //* Feature: nextFollowingVerbInWeather

  /** getter for nextFollowingVerbInWeather - gets F19-True if the immediately following verb belongs to the category “weather adjectives”, and false otherwise.
   * @generated
   * @return value of the feature 
   */
  public boolean getNextFollowingVerbInWeather() {
    if (Features_Type.featOkTst && ((Features_Type)jcasType).casFeat_nextFollowingVerbInWeather == null)
      jcasType.jcas.throwFeatMissing("nextFollowingVerbInWeather", "ca.uottawa.csi5137.type.Features");
    return jcasType.ll_cas.ll_getBooleanValue(addr, ((Features_Type)jcasType).casFeatCode_nextFollowingVerbInWeather);}
    
  /** setter for nextFollowingVerbInWeather - sets F19-True if the immediately following verb belongs to the category “weather adjectives”, and false otherwise. 
   * @generated
   * @param v value to set into the feature 
   */
  public void setNextFollowingVerbInWeather(boolean v) {
    if (Features_Type.featOkTst && ((Features_Type)jcasType).casFeat_nextFollowingVerbInWeather == null)
      jcasType.jcas.throwFeatMissing("nextFollowingVerbInWeather", "ca.uottawa.csi5137.type.Features");
    jcasType.ll_cas.ll_setBooleanValue(addr, ((Features_Type)jcasType).casFeatCode_nextFollowingVerbInWeather, v);}    
   
    
  //*--------------*
  //* Feature: nextFollowingVerbInCognition

  /** getter for nextFollowingVerbInCognition - gets F20-True if the immediately following verb belongs to the category of cognitive verbs, and false otherwise.
   * @generated
   * @return value of the feature 
   */
  public boolean getNextFollowingVerbInCognition() {
    if (Features_Type.featOkTst && ((Features_Type)jcasType).casFeat_nextFollowingVerbInCognition == null)
      jcasType.jcas.throwFeatMissing("nextFollowingVerbInCognition", "ca.uottawa.csi5137.type.Features");
    return jcasType.ll_cas.ll_getBooleanValue(addr, ((Features_Type)jcasType).casFeatCode_nextFollowingVerbInCognition);}
    
  /** setter for nextFollowingVerbInCognition - sets F20-True if the immediately following verb belongs to the category of cognitive verbs, and false otherwise. 
   * @generated
   * @param v value to set into the feature 
   */
  public void setNextFollowingVerbInCognition(boolean v) {
    if (Features_Type.featOkTst && ((Features_Type)jcasType).casFeat_nextFollowingVerbInCognition == null)
      jcasType.jcas.throwFeatMissing("nextFollowingVerbInCognition", "ca.uottawa.csi5137.type.Features");
    jcasType.ll_cas.ll_setBooleanValue(addr, ((Features_Type)jcasType).casFeatCode_nextFollowingVerbInCognition, v);}    
  }

    
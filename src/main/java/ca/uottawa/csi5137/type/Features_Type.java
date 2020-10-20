
/* First created by JCasGen Mon Oct 19 21:11:57 EDT 2020 */
package ca.uottawa.csi5137.type;

import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.cas.impl.TypeImpl;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.impl.FeatureImpl;
import org.apache.uima.cas.Feature;
import org.apache.uima.jcas.tcas.Annotation_Type;

/** 
 * Updated by JCasGen Mon Oct 19 21:11:57 EDT 2020
 * @generated */
public class Features_Type extends Annotation_Type {
  /** @generated */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = Features.typeIndexID;
  /** @generated 
     @modifiable */
  @SuppressWarnings ("hiding")
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("ca.uottawa.csi5137.type.Features");
 
  /** @generated */
  final Feature casFeat_position;
  /** @generated */
  final int     casFeatCode_position;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public int getPosition(int addr) {
        if (featOkTst && casFeat_position == null)
      jcas.throwFeatMissing("position", "ca.uottawa.csi5137.type.Features");
    return ll_cas.ll_getIntValue(addr, casFeatCode_position);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setPosition(int addr, int v) {
        if (featOkTst && casFeat_position == null)
      jcas.throwFeatMissing("position", "ca.uottawa.csi5137.type.Features");
    ll_cas.ll_setIntValue(addr, casFeatCode_position, v);}
    
  
 
  /** @generated */
  final Feature casFeat_sentLenInTokens;
  /** @generated */
  final int     casFeatCode_sentLenInTokens;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public int getSentLenInTokens(int addr) {
        if (featOkTst && casFeat_sentLenInTokens == null)
      jcas.throwFeatMissing("sentLenInTokens", "ca.uottawa.csi5137.type.Features");
    return ll_cas.ll_getIntValue(addr, casFeatCode_sentLenInTokens);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setSentLenInTokens(int addr, int v) {
        if (featOkTst && casFeat_sentLenInTokens == null)
      jcas.throwFeatMissing("sentLenInTokens", "ca.uottawa.csi5137.type.Features");
    ll_cas.ll_setIntValue(addr, casFeatCode_sentLenInTokens, v);}
    
  
 
  /** @generated */
  final Feature casFeat_numPunct;
  /** @generated */
  final int     casFeatCode_numPunct;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public int getNumPunct(int addr) {
        if (featOkTst && casFeat_numPunct == null)
      jcas.throwFeatMissing("numPunct", "ca.uottawa.csi5137.type.Features");
    return ll_cas.ll_getIntValue(addr, casFeatCode_numPunct);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setNumPunct(int addr, int v) {
        if (featOkTst && casFeat_numPunct == null)
      jcas.throwFeatMissing("numPunct", "ca.uottawa.csi5137.type.Features");
    ll_cas.ll_setIntValue(addr, casFeatCode_numPunct, v);}
    
  
 
  /** @generated */
  final Feature casFeat_numPrecedingNP;
  /** @generated */
  final int     casFeatCode_numPrecedingNP;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public int getNumPrecedingNP(int addr) {
        if (featOkTst && casFeat_numPrecedingNP == null)
      jcas.throwFeatMissing("numPrecedingNP", "ca.uottawa.csi5137.type.Features");
    return ll_cas.ll_getIntValue(addr, casFeatCode_numPrecedingNP);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setNumPrecedingNP(int addr, int v) {
        if (featOkTst && casFeat_numPrecedingNP == null)
      jcas.throwFeatMissing("numPrecedingNP", "ca.uottawa.csi5137.type.Features");
    ll_cas.ll_setIntValue(addr, casFeatCode_numPrecedingNP, v);}
    
  
 
  /** @generated */
  final Feature casFeat_numFollowingNP;
  /** @generated */
  final int     casFeatCode_numFollowingNP;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public int getNumFollowingNP(int addr) {
        if (featOkTst && casFeat_numFollowingNP == null)
      jcas.throwFeatMissing("numFollowingNP", "ca.uottawa.csi5137.type.Features");
    return ll_cas.ll_getIntValue(addr, casFeatCode_numFollowingNP);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setNumFollowingNP(int addr, int v) {
        if (featOkTst && casFeat_numFollowingNP == null)
      jcas.throwFeatMissing("numFollowingNP", "ca.uottawa.csi5137.type.Features");
    ll_cas.ll_setIntValue(addr, casFeatCode_numFollowingNP, v);}
    
  
 
  /** @generated */
  final Feature casFeat_followsPrepPhrase;
  /** @generated */
  final int     casFeatCode_followsPrepPhrase;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public boolean getFollowsPrepPhrase(int addr) {
        if (featOkTst && casFeat_followsPrepPhrase == null)
      jcas.throwFeatMissing("followsPrepPhrase", "ca.uottawa.csi5137.type.Features");
    return ll_cas.ll_getBooleanValue(addr, casFeatCode_followsPrepPhrase);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setFollowsPrepPhrase(int addr, boolean v) {
        if (featOkTst && casFeat_followsPrepPhrase == null)
      jcas.throwFeatMissing("followsPrepPhrase", "ca.uottawa.csi5137.type.Features");
    ll_cas.ll_setBooleanValue(addr, casFeatCode_followsPrepPhrase, v);}
    
  
 
  /** @generated */
  final Feature casFeat_fourPosTagsPrecedingFollowing;
  /** @generated */
  final int     casFeatCode_fourPosTagsPrecedingFollowing;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public int getFourPosTagsPrecedingFollowing(int addr) {
        if (featOkTst && casFeat_fourPosTagsPrecedingFollowing == null)
      jcas.throwFeatMissing("fourPosTagsPrecedingFollowing", "ca.uottawa.csi5137.type.Features");
    return ll_cas.ll_getRefValue(addr, casFeatCode_fourPosTagsPrecedingFollowing);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setFourPosTagsPrecedingFollowing(int addr, int v) {
        if (featOkTst && casFeat_fourPosTagsPrecedingFollowing == null)
      jcas.throwFeatMissing("fourPosTagsPrecedingFollowing", "ca.uottawa.csi5137.type.Features");
    ll_cas.ll_setRefValue(addr, casFeatCode_fourPosTagsPrecedingFollowing, v);}
    
  
 
  /** @generated */
  final Feature casFeat_followedByVBG;
  /** @generated */
  final int     casFeatCode_followedByVBG;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public boolean getFollowedByVBG(int addr) {
        if (featOkTst && casFeat_followedByVBG == null)
      jcas.throwFeatMissing("followedByVBG", "ca.uottawa.csi5137.type.Features");
    return ll_cas.ll_getBooleanValue(addr, casFeatCode_followedByVBG);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setFollowedByVBG(int addr, boolean v) {
        if (featOkTst && casFeat_followedByVBG == null)
      jcas.throwFeatMissing("followedByVBG", "ca.uottawa.csi5137.type.Features");
    ll_cas.ll_setBooleanValue(addr, casFeatCode_followedByVBG, v);}
    
  
 
  /** @generated */
  final Feature casFeat_followedByPrep;
  /** @generated */
  final int     casFeatCode_followedByPrep;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public boolean getFollowedByPrep(int addr) {
        if (featOkTst && casFeat_followedByPrep == null)
      jcas.throwFeatMissing("followedByPrep", "ca.uottawa.csi5137.type.Features");
    return ll_cas.ll_getBooleanValue(addr, casFeatCode_followedByPrep);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setFollowedByPrep(int addr, boolean v) {
        if (featOkTst && casFeat_followedByPrep == null)
      jcas.throwFeatMissing("followedByPrep", "ca.uottawa.csi5137.type.Features");
    ll_cas.ll_setBooleanValue(addr, casFeatCode_followedByPrep, v);}
    
  
 
  /** @generated */
  final Feature casFeat_numFollowingAdj;
  /** @generated */
  final int     casFeatCode_numFollowingAdj;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public int getNumFollowingAdj(int addr) {
        if (featOkTst && casFeat_numFollowingAdj == null)
      jcas.throwFeatMissing("numFollowingAdj", "ca.uottawa.csi5137.type.Features");
    return ll_cas.ll_getIntValue(addr, casFeatCode_numFollowingAdj);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setNumFollowingAdj(int addr, int v) {
        if (featOkTst && casFeat_numFollowingAdj == null)
      jcas.throwFeatMissing("numFollowingAdj", "ca.uottawa.csi5137.type.Features");
    ll_cas.ll_setIntValue(addr, casFeatCode_numFollowingAdj, v);}
    
  
 
  /** @generated */
  final Feature casFeat_followsVerb;
  /** @generated */
  final int     casFeatCode_followsVerb;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public boolean getFollowsVerb(int addr) {
        if (featOkTst && casFeat_followsVerb == null)
      jcas.throwFeatMissing("followsVerb", "ca.uottawa.csi5137.type.Features");
    return ll_cas.ll_getBooleanValue(addr, casFeatCode_followsVerb);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setFollowsVerb(int addr, boolean v) {
        if (featOkTst && casFeat_followsVerb == null)
      jcas.throwFeatMissing("followsVerb", "ca.uottawa.csi5137.type.Features");
    ll_cas.ll_setBooleanValue(addr, casFeatCode_followsVerb, v);}
    
  
 
  /** @generated */
  final Feature casFeat_followedByVerb;
  /** @generated */
  final int     casFeatCode_followedByVerb;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public boolean getFollowedByVerb(int addr) {
        if (featOkTst && casFeat_followedByVerb == null)
      jcas.throwFeatMissing("followedByVerb", "ca.uottawa.csi5137.type.Features");
    return ll_cas.ll_getBooleanValue(addr, casFeatCode_followedByVerb);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setFollowedByVerb(int addr, boolean v) {
        if (featOkTst && casFeat_followedByVerb == null)
      jcas.throwFeatMissing("followedByVerb", "ca.uottawa.csi5137.type.Features");
    ll_cas.ll_setBooleanValue(addr, casFeatCode_followedByVerb, v);}
    
  
 
  /** @generated */
  final Feature casFeat_followedByAdj;
  /** @generated */
  final int     casFeatCode_followedByAdj;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public boolean getFollowedByAdj(int addr) {
        if (featOkTst && casFeat_followedByAdj == null)
      jcas.throwFeatMissing("followedByAdj", "ca.uottawa.csi5137.type.Features");
    return ll_cas.ll_getBooleanValue(addr, casFeatCode_followedByAdj);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setFollowedByAdj(int addr, boolean v) {
        if (featOkTst && casFeat_followedByAdj == null)
      jcas.throwFeatMissing("followedByAdj", "ca.uottawa.csi5137.type.Features");
    ll_cas.ll_setBooleanValue(addr, casFeatCode_followedByAdj, v);}
    
  
 
  /** @generated */
  final Feature casFeat_followedByNPAdj;
  /** @generated */
  final int     casFeatCode_followedByNPAdj;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public boolean getFollowedByNPAdj(int addr) {
        if (featOkTst && casFeat_followedByNPAdj == null)
      jcas.throwFeatMissing("followedByNPAdj", "ca.uottawa.csi5137.type.Features");
    return ll_cas.ll_getBooleanValue(addr, casFeatCode_followedByNPAdj);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setFollowedByNPAdj(int addr, boolean v) {
        if (featOkTst && casFeat_followedByNPAdj == null)
      jcas.throwFeatMissing("followedByNPAdj", "ca.uottawa.csi5137.type.Features");
    ll_cas.ll_setBooleanValue(addr, casFeatCode_followedByNPAdj, v);}
    
  
 
  /** @generated */
  final Feature casFeat_numTokensPrecedingFollowingInfinitiveVerb;
  /** @generated */
  final int     casFeatCode_numTokensPrecedingFollowingInfinitiveVerb;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public int getNumTokensPrecedingFollowingInfinitiveVerb(int addr) {
        if (featOkTst && casFeat_numTokensPrecedingFollowingInfinitiveVerb == null)
      jcas.throwFeatMissing("numTokensPrecedingFollowingInfinitiveVerb", "ca.uottawa.csi5137.type.Features");
    return ll_cas.ll_getIntValue(addr, casFeatCode_numTokensPrecedingFollowingInfinitiveVerb);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setNumTokensPrecedingFollowingInfinitiveVerb(int addr, int v) {
        if (featOkTst && casFeat_numTokensPrecedingFollowingInfinitiveVerb == null)
      jcas.throwFeatMissing("numTokensPrecedingFollowingInfinitiveVerb", "ca.uottawa.csi5137.type.Features");
    ll_cas.ll_setIntValue(addr, casFeatCode_numTokensPrecedingFollowingInfinitiveVerb, v);}
    
  
 
  /** @generated */
  final Feature casFeat_numTokensUntilNextPrep;
  /** @generated */
  final int     casFeatCode_numTokensUntilNextPrep;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public int getNumTokensUntilNextPrep(int addr) {
        if (featOkTst && casFeat_numTokensUntilNextPrep == null)
      jcas.throwFeatMissing("numTokensUntilNextPrep", "ca.uottawa.csi5137.type.Features");
    return ll_cas.ll_getIntValue(addr, casFeatCode_numTokensUntilNextPrep);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setNumTokensUntilNextPrep(int addr, int v) {
        if (featOkTst && casFeat_numTokensUntilNextPrep == null)
      jcas.throwFeatMissing("numTokensUntilNextPrep", "ca.uottawa.csi5137.type.Features");
    ll_cas.ll_setIntValue(addr, casFeatCode_numTokensUntilNextPrep, v);}
    
  
 
  /** @generated */
  final Feature casFeat_followedBySeqAdjNP;
  /** @generated */
  final int     casFeatCode_followedBySeqAdjNP;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public boolean getFollowedBySeqAdjNP(int addr) {
        if (featOkTst && casFeat_followedBySeqAdjNP == null)
      jcas.throwFeatMissing("followedBySeqAdjNP", "ca.uottawa.csi5137.type.Features");
    return ll_cas.ll_getBooleanValue(addr, casFeatCode_followedBySeqAdjNP);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setFollowedBySeqAdjNP(int addr, boolean v) {
        if (featOkTst && casFeat_followedBySeqAdjNP == null)
      jcas.throwFeatMissing("followedBySeqAdjNP", "ca.uottawa.csi5137.type.Features");
    ll_cas.ll_setBooleanValue(addr, casFeatCode_followedBySeqAdjNP, v);}
    
  
 
  /** @generated */
  final Feature casFeat_depRelType;
  /** @generated */
  final int     casFeatCode_depRelType;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public String getDepRelType(int addr) {
        if (featOkTst && casFeat_depRelType == null)
      jcas.throwFeatMissing("depRelType", "ca.uottawa.csi5137.type.Features");
    return ll_cas.ll_getStringValue(addr, casFeatCode_depRelType);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setDepRelType(int addr, String v) {
        if (featOkTst && casFeat_depRelType == null)
      jcas.throwFeatMissing("depRelType", "ca.uottawa.csi5137.type.Features");
    ll_cas.ll_setStringValue(addr, casFeatCode_depRelType, v);}
    
  
 
  /** @generated */
  final Feature casFeat_nextFollowingVerbInWeather;
  /** @generated */
  final int     casFeatCode_nextFollowingVerbInWeather;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public boolean getNextFollowingVerbInWeather(int addr) {
        if (featOkTst && casFeat_nextFollowingVerbInWeather == null)
      jcas.throwFeatMissing("nextFollowingVerbInWeather", "ca.uottawa.csi5137.type.Features");
    return ll_cas.ll_getBooleanValue(addr, casFeatCode_nextFollowingVerbInWeather);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setNextFollowingVerbInWeather(int addr, boolean v) {
        if (featOkTst && casFeat_nextFollowingVerbInWeather == null)
      jcas.throwFeatMissing("nextFollowingVerbInWeather", "ca.uottawa.csi5137.type.Features");
    ll_cas.ll_setBooleanValue(addr, casFeatCode_nextFollowingVerbInWeather, v);}
    
  
 
  /** @generated */
  final Feature casFeat_nextFollowingVerbInCognition;
  /** @generated */
  final int     casFeatCode_nextFollowingVerbInCognition;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public boolean getNextFollowingVerbInCognition(int addr) {
        if (featOkTst && casFeat_nextFollowingVerbInCognition == null)
      jcas.throwFeatMissing("nextFollowingVerbInCognition", "ca.uottawa.csi5137.type.Features");
    return ll_cas.ll_getBooleanValue(addr, casFeatCode_nextFollowingVerbInCognition);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setNextFollowingVerbInCognition(int addr, boolean v) {
        if (featOkTst && casFeat_nextFollowingVerbInCognition == null)
      jcas.throwFeatMissing("nextFollowingVerbInCognition", "ca.uottawa.csi5137.type.Features");
    ll_cas.ll_setBooleanValue(addr, casFeatCode_nextFollowingVerbInCognition, v);}
    
  



  /** initialize variables to correspond with Cas Type and Features
	 * @generated
	 * @param jcas JCas
	 * @param casType Type 
	 */
  public Features_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_position = jcas.getRequiredFeatureDE(casType, "position", "uima.cas.Integer", featOkTst);
    casFeatCode_position  = (null == casFeat_position) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_position).getCode();

 
    casFeat_sentLenInTokens = jcas.getRequiredFeatureDE(casType, "sentLenInTokens", "uima.cas.Integer", featOkTst);
    casFeatCode_sentLenInTokens  = (null == casFeat_sentLenInTokens) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_sentLenInTokens).getCode();

 
    casFeat_numPunct = jcas.getRequiredFeatureDE(casType, "numPunct", "uima.cas.Integer", featOkTst);
    casFeatCode_numPunct  = (null == casFeat_numPunct) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_numPunct).getCode();

 
    casFeat_numPrecedingNP = jcas.getRequiredFeatureDE(casType, "numPrecedingNP", "uima.cas.Integer", featOkTst);
    casFeatCode_numPrecedingNP  = (null == casFeat_numPrecedingNP) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_numPrecedingNP).getCode();

 
    casFeat_numFollowingNP = jcas.getRequiredFeatureDE(casType, "numFollowingNP", "uima.cas.Integer", featOkTst);
    casFeatCode_numFollowingNP  = (null == casFeat_numFollowingNP) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_numFollowingNP).getCode();

 
    casFeat_followsPrepPhrase = jcas.getRequiredFeatureDE(casType, "followsPrepPhrase", "uima.cas.Boolean", featOkTst);
    casFeatCode_followsPrepPhrase  = (null == casFeat_followsPrepPhrase) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_followsPrepPhrase).getCode();

 
    casFeat_fourPosTagsPrecedingFollowing = jcas.getRequiredFeatureDE(casType, "fourPosTagsPrecedingFollowing", "uima.cas.StringList", featOkTst);
    casFeatCode_fourPosTagsPrecedingFollowing  = (null == casFeat_fourPosTagsPrecedingFollowing) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_fourPosTagsPrecedingFollowing).getCode();

 
    casFeat_followedByVBG = jcas.getRequiredFeatureDE(casType, "followedByVBG", "uima.cas.Boolean", featOkTst);
    casFeatCode_followedByVBG  = (null == casFeat_followedByVBG) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_followedByVBG).getCode();

 
    casFeat_followedByPrep = jcas.getRequiredFeatureDE(casType, "followedByPrep", "uima.cas.Boolean", featOkTst);
    casFeatCode_followedByPrep  = (null == casFeat_followedByPrep) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_followedByPrep).getCode();

 
    casFeat_numFollowingAdj = jcas.getRequiredFeatureDE(casType, "numFollowingAdj", "uima.cas.Integer", featOkTst);
    casFeatCode_numFollowingAdj  = (null == casFeat_numFollowingAdj) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_numFollowingAdj).getCode();

 
    casFeat_followsVerb = jcas.getRequiredFeatureDE(casType, "followsVerb", "uima.cas.Boolean", featOkTst);
    casFeatCode_followsVerb  = (null == casFeat_followsVerb) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_followsVerb).getCode();

 
    casFeat_followedByVerb = jcas.getRequiredFeatureDE(casType, "followedByVerb", "uima.cas.Boolean", featOkTst);
    casFeatCode_followedByVerb  = (null == casFeat_followedByVerb) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_followedByVerb).getCode();

 
    casFeat_followedByAdj = jcas.getRequiredFeatureDE(casType, "followedByAdj", "uima.cas.Boolean", featOkTst);
    casFeatCode_followedByAdj  = (null == casFeat_followedByAdj) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_followedByAdj).getCode();

 
    casFeat_followedByNPAdj = jcas.getRequiredFeatureDE(casType, "followedByNPAdj", "uima.cas.Boolean", featOkTst);
    casFeatCode_followedByNPAdj  = (null == casFeat_followedByNPAdj) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_followedByNPAdj).getCode();

 
    casFeat_numTokensPrecedingFollowingInfinitiveVerb = jcas.getRequiredFeatureDE(casType, "numTokensPrecedingFollowingInfinitiveVerb", "uima.cas.Integer", featOkTst);
    casFeatCode_numTokensPrecedingFollowingInfinitiveVerb  = (null == casFeat_numTokensPrecedingFollowingInfinitiveVerb) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_numTokensPrecedingFollowingInfinitiveVerb).getCode();

 
    casFeat_numTokensUntilNextPrep = jcas.getRequiredFeatureDE(casType, "numTokensUntilNextPrep", "uima.cas.Integer", featOkTst);
    casFeatCode_numTokensUntilNextPrep  = (null == casFeat_numTokensUntilNextPrep) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_numTokensUntilNextPrep).getCode();

 
    casFeat_followedBySeqAdjNP = jcas.getRequiredFeatureDE(casType, "followedBySeqAdjNP", "uima.cas.Boolean", featOkTst);
    casFeatCode_followedBySeqAdjNP  = (null == casFeat_followedBySeqAdjNP) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_followedBySeqAdjNP).getCode();

 
    casFeat_depRelType = jcas.getRequiredFeatureDE(casType, "depRelType", "uima.cas.String", featOkTst);
    casFeatCode_depRelType  = (null == casFeat_depRelType) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_depRelType).getCode();

 
    casFeat_nextFollowingVerbInWeather = jcas.getRequiredFeatureDE(casType, "nextFollowingVerbInWeather", "uima.cas.Boolean", featOkTst);
    casFeatCode_nextFollowingVerbInWeather  = (null == casFeat_nextFollowingVerbInWeather) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_nextFollowingVerbInWeather).getCode();

 
    casFeat_nextFollowingVerbInCognition = jcas.getRequiredFeatureDE(casType, "nextFollowingVerbInCognition", "uima.cas.Boolean", featOkTst);
    casFeatCode_nextFollowingVerbInCognition  = (null == casFeat_nextFollowingVerbInCognition) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_nextFollowingVerbInCognition).getCode();

  }
}



    
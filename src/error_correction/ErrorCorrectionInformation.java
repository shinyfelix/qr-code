package error_correction;

public class ErrorCorrectionInformation {
    public int getCodeWordsAmount(){
        throw new UnsupportedOperationException();
    }
    public int groupsFirstBlock(){
        throw new UnsupportedOperationException();
    }
    public int groupsSecondBlock(){
        throw new UnsupportedOperationException();
    }

    public int codeWordsPerGroup_Block1(){
        throw new UnsupportedOperationException();
    }
    public int codeWordsPerGroup_Block2(){
        return codeWordsPerGroup_Block1()+1;
    }
}

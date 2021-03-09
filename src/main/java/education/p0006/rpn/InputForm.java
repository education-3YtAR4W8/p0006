package education.p0006.rpn;

public class InputForm {
    public Boolean isBadRequest = false;
    public Boolean isInvalidFormatOfItemQuantity = false;

    private void clearErrors(){
        isBadRequest = false;
        isInvalidFormatOfItemQuantity = false;
    }
    public Boolean hasError(){
        return isBadRequest || isInvalidFormatOfItemQuantity;
    }
}

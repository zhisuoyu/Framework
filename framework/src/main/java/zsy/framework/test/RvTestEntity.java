package zsy.framework.test;

public class RvTestEntity {

    public static final int TYPE_TV = 0x00;
    public static final int TYPE_BTN = 0x01;
    public static final int TYPE_ET = 0x02;
    public static final int TYPE_EMPTY = 0x03;


    private int type;
    private String text;
    private String hint;


    public RvTestEntity() {
        type = TYPE_EMPTY;
    }

    public RvTestEntity(String text) {
        this(TYPE_BTN, text);
    }

    public RvTestEntity(int type, String text) {
        this.type = type;
        this.text = text;
    }

    public RvTestEntity(int type, String text, String hint) {
        this.type = type;
        this.text = text;
        this.hint = hint;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getHint() {
        return hint;
    }

    public void setHint(String hint) {
        this.hint = hint;
    }
}

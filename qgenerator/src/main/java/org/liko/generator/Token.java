package org.liko.generator;
import java.io.Serializable;

class Token implements Serializable {
    public int kind;
    public int beginLine;
    public int beginColumn;
    public int endLine;
    public int endColumn;
    public String image;
    public Token next;
    public Token specialToken;

    Token() {
    }

    public String toString() {
        return this.image;
    }

    public static final Token newToken(int ofKind) {
        switch(ofKind) {
            default:
                return new Token();
        }
    }
}
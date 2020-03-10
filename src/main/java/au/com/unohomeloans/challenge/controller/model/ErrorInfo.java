package au.com.unohomeloans.challenge.controller.model;

import lombok.Getter;

@Getter
public class ErrorInfo {

    public final String url;
    public final String ex;

    public ErrorInfo(String url, Exception ex) {
        this.url = url;
        this.ex = ex.getLocalizedMessage();
    }


}

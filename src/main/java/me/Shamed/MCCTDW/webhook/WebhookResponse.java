package me.Shamed.MCCTDW.webhook;

public class WebhookResponse {
    private int statusCode;
    private String response;

    public WebhookResponse(int statusCode, String response){
        this.response=response;
        this.statusCode=statusCode;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public String getResponse() {
        return response;
    }
}

package br.com.rhonline.core.exception;

public class CustomErro {

    public final String name = "CUSTOMERRO";
    private String mensagemUser;
    private String mensagemDeveloper;

    /**
     *
     * @param mensagemUser
     * @param mensagemDeveloper
     */
    public CustomErro(String mensagemUser, String mensagemDeveloper) {
        this.mensagemUser = mensagemUser;
        this.mensagemDeveloper = mensagemDeveloper;
    }

    public String getMensagemUser() {
        return mensagemUser;
    }

    public void setMensagemUser(String mensagemUser) {
        this.mensagemUser = mensagemUser;
    }

    public String getMensagemDeveloper() {
        return mensagemDeveloper;
    }

    public void setMensagemDeveloper(String mensagemDeveloper) {
        this.mensagemDeveloper = mensagemDeveloper;
    }
}

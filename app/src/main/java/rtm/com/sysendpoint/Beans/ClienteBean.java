package rtm.com.sysendpoint.Beans;

public class ClienteBean {

    private String CLICVE;


    private String CLINOM;


    private String CLIRUC;


    private String CLNIDE;


    private String CLTIDE;


    public String getCLICVE() {
        return CLICVE;
    }

    public void setCLICVE(String CLICVE) {
        this.CLICVE = CLICVE;
    }

    public String getCLINOM() {
        return CLINOM;
    }

    public void setCLINOM(String CLINOM) {
        this.CLINOM = CLINOM;
    }

    public String getCLIRUC() {
        return CLIRUC;
    }

    public void setCLIRUC(String CLIRUC) {
        this.CLIRUC = CLIRUC;
    }

    public String getCLNIDE() {
        return CLNIDE;
    }

    public void setCLNIDE(String CLNIDE) {
        this.CLNIDE = CLNIDE;
    }

    public String getCLTIDE() {
        return CLTIDE;
    }

    public void setCLTIDE(String CLTIDE) {
        this.CLTIDE = CLTIDE;
    }

    public ClienteBean() {
    }

    public ClienteBean(String CLICVE, String CLINOM, String CLIRUC, String CLNIDE, String CLTIDE) {
        this.CLICVE = CLICVE;
        this.CLINOM = CLINOM;
        this.CLIRUC = CLIRUC;
        this.CLNIDE = CLNIDE;
        this.CLTIDE = CLTIDE;
    }
}

package fr.xebia.devoxx.hadoop.fr.xebia.devoxx.common;

import org.kohsuke.args4j.Option;

public class CommandOption {

    public static final String USE_CASE = "useCase";
    public static final String WAIT_FOR_COMPLETION = "waitForCompletion";
    public static final String DATE_FROM = "dateFrom";
    public static final String DATE_TO = "dateTo";
    public static final String INPUT_PATH = "inputPath";
    public static final String OUTPUT_PATH = "outputPath";


    private String useCase;
    private boolean waitForCompletion = true;
    private String dateFrom;
    private String dateTo;
    private String inputPath;
    private String outputPath;

    public String getInputPath() {
        return inputPath;
    }

    @Option(name = "-" + INPUT_PATH, required = true, usage = "inputPath")
    public void setInputPath(String inputPath) {
        this.inputPath = inputPath;
    }

    public String getOutputPath() {
        return outputPath;
    }

    @Option(name = "-" + OUTPUT_PATH, required = true, usage = "outputPath")
    public void setOutputPath(String outputPath) {
        this.outputPath = outputPath;
    }

    public String getDateTo() {
        return dateTo;
    }

    @Option(name = "-" + DATE_TO, required = false, usage = "dateTo (yyyy-MM-dd HH:mm)")
    public void setDateTo(String dateTo) {
        this.dateTo = dateTo;
    }

    public String getDateFrom() {
        return dateFrom;
    }

    @Option(name = "-" + DATE_FROM, required = false, usage = "dateFrom (yyyy-MM-dd HH:mm)")
    public void setDateFrom(String dateFrom) {
        this.dateFrom = dateFrom;
    }

    public String getUseCase() {
        return useCase;
    }

    @Option(name = "-" + USE_CASE, required = true, usage = "use case")
    public void setUseCase(String useCase) {
        this.useCase = useCase;
    }

    public boolean isWaitForCompletion() {
        return waitForCompletion;
    }

    @Option(name = "-" + WAIT_FOR_COMPLETION, usage = "lance le job en attendant la réponse ou pas")
    public void setWaitForCompletion(boolean waitForCompletion) {
        this.waitForCompletion = waitForCompletion;
    }
}
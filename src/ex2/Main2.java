package ex2;

interface IReportBuilder {
    void setHeader(String header);
    void setContent(String content);
    void setFooter(String footer);
    Report getReport();
}

class TextReportBuilder implements IReportBuilder {
    private Report report = new Report();

    public void setHeader(String header) {
        report.header = "Header: " + header;
    }

    public void setContent(String content) {
        report.content = "Content: " + content;
    }

    public void setFooter(String footer) {
        report.footer = "Footer: " + footer;
    }

    public Report getReport() {
        return report;
    }
}

class HtmlReportBuilder implements IReportBuilder {
    private Report report = new Report();

    @Override
    public void setHeader(String header) {
        report.header = "<h1>" + header + "</h1>";
    }

    @Override
    public void setContent(String content) {
        report.content = "<p>" + content + "</p>";
    }

    @Override
    public void setFooter(String footer) {
        report.footer = "<footer>" + footer + "</footer>";
    }

    @Override
    public Report getReport() {
        return report;
    }
}





class Report {
    String header;
    String content;
    String footer;

    @Override
    public String toString() {
        return header + "\n" + content + "\n" + footer;
    }
}



class ReportDirector {
    public void constructReport(IReportBuilder builder) {
        builder.setHeader("HTML Report");
        builder.setContent("Этот контент на HTMl формате.");
        builder.setFooter("Footer на HTML формате");
    }
}



public class Main2 {
    public static void main(String[] args) {


        IReportBuilder htmlBuilder = new HtmlReportBuilder();
        ReportDirector director1 = new ReportDirector();
        director1.constructReport(htmlBuilder);
        Report htmlReport = htmlBuilder.getReport();
        System.out.println(htmlReport);


    }
}
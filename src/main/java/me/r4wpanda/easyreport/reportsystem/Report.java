package me.r4wpanda.easyreport.reportsystem;

import org.bukkit.entity.Player;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Report {

    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");

    private Player reportWriter;

    private Player reportedPlayer;

    private Reason reason;

    private String timeOfReport;

    private static List<Report> reportList = new ArrayList<>();

    public Report(Player reportWriter, Player reportedPlayer, Reason reason) {
        this.reportWriter = reportWriter;
        this.reportedPlayer = reportedPlayer;
        this.reason = reason;
        this.timeOfReport = dtf.format((LocalDateTime.now()));
    }

    public static void updateReports(Report report) {
        reportList.add(report);
    }

    public static void removeReport(Report report) {
        reportList.remove(report);
    }

    public static void removeReport(int reportIndex) {
        reportList.remove(reportIndex);
    }

    public Player getReportWriter() {
        return reportWriter;
    }

    public Player getReportedPlayer() {
        return reportedPlayer;
    }

    public Reason getReason() {
        return reason;
    }

    public String getTimeOfReport() {
        return timeOfReport;
    }

    public static List<Report> getReportList() {
        return reportList;
    }

}

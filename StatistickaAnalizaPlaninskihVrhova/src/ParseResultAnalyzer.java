import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Writer;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

@SuppressWarnings("unused")
public class ParseResultAnalyzer {

	private String theHighestMountain = "";
	private int theHighestMountainValue = -1;
	private String mountainWithMostPeaks = "";
	private int mountainWithMostPeaksValue = -1;
	private String mountainWithHighestAvrPeaks = "";
	private int mountainWithHighestAvrPeaksValue = -1;

	private PrintWriter writer;
	private StringBuilder stringBuilder;

	public ParseResultAnalyzer() {
		super();
	}

	public void analyzeAll(ArrayList<ParseResult> parseResults, OutputStream outputStream) {

		writer = new PrintWriter(outputStream);
		stringBuilder = new StringBuilder();

		stringBuilder.append("<html><head></head><body>");
		stringBuilder.append("<table border='1'>");
		stringBuilder.append("<tr><td>Mountain</td><td>Ukupno vrhova</td><td>Prosecna visina</td><td>Medijana</td>")
				.append("<td>Najnizi</td><td></td><td>Najvisi</td><td></td></tr>");

		for (ParseResult parseResult : parseResults) {
			ArrayList<Mountain> planine = parseResult.getPlanine();
			for (Mountain Mountain : planine) {
				analyzeMountain(Mountain);
			}
		}

		stringBuilder.append("</table>");

		stringBuilder.append("<p>Mountain sa najvisim vrhom je ").append(theHighestMountain).append(" (")
				.append(theHighestMountainValue).append(")</p>");
		stringBuilder.append("<p>Mountain sa najvisim vrhovima je ").append(mountainWithHighestAvrPeaks).append(" (")
				.append(mountainWithHighestAvrPeaksValue).append(")</p>");
		stringBuilder.append("<p>Mountain sa najvise vrhova je ").append(mountainWithMostPeaks).append(" (")
				.append(mountainWithMostPeaksValue).append(")</p>");

		stringBuilder.append(ExceptionsHolder.getExceptions());

		stringBuilder.append("</body></html>");
		writer.write(stringBuilder.toString());
		writer.flush();
		writer.close();

	}

	public void analyzeMountain(Mountain mountain) {
		int totalPeaks = 0;
		int totalHeight = 0;
		int avr = 0;
		int median = 0;
		String topPeak = "";
		String lowPeak = "";
		int topPeakValue = 0;
		int lowPeakValue = Integer.MAX_VALUE;

		ArrayList<Peak> peaks = mountain.getPeaks();
		int[] vrhoviValue = new int[peaks.size()];

		totalPeaks = peaks.size();
		if (totalPeaks > mountainWithMostPeaksValue) {
			mountainWithMostPeaksValue = totalPeaks;
			mountainWithMostPeaks = mountain.getName();
		}

		int i = 0;
		for (Peak peak : peaks) {
			int height = peak.getHeight();
			totalHeight += height;
			vrhoviValue[i++] = height;
			if (height > topPeakValue) {
				topPeakValue = height;
				topPeak = peak.getName();
			}
			if (height < lowPeakValue) {
				lowPeakValue = height;
				lowPeak = peak.getName();
			}
		}

		Arrays.sort(vrhoviValue);
		if (vrhoviValue.length % 2 == 0)
			median = ((int) vrhoviValue[vrhoviValue.length / 2] + (int) vrhoviValue[vrhoviValue.length / 2 - 1]) / 2;
		else
			median = (int) vrhoviValue[vrhoviValue.length / 2];

		if (topPeakValue > theHighestMountainValue) {
			theHighestMountainValue = topPeakValue;
			theHighestMountain = mountain.getName();
		}

		avr = totalHeight / totalPeaks;
		if (avr > mountainWithHighestAvrPeaksValue) {
			mountainWithHighestAvrPeaksValue = avr;
			mountainWithHighestAvrPeaks = mountain.getName();
		}

		stringBuilder.append("<tr><td>").append(mountain.getName()).append("</td><td>").append(totalPeaks)
				.append("</td><td>").append(avr).append("</td><td>").append(median).append("</td><td>").append(lowPeak)
				.append("</td><td>").append(lowPeakValue).append("</td><td>").append(topPeak).append("</td><td>")
				.append(topPeakValue).append("</td></tr>");

	}

	public String getTheHighestMountain() {
		return theHighestMountain;
	}

	public String getMountainWithMostPeaks() {
		return mountainWithMostPeaks;
	}

	public String getMountainWithHighestAvrPeaks() {
		return mountainWithHighestAvrPeaks;
	}

}

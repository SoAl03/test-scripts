package matcher;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Stream;

import com.machinezoo.sourceafis.FingerprintMatcher;
import com.machinezoo.sourceafis.FingerprintTemplate;

public class Match_Templates {

	public static void main(String[] args) throws IOException {
		String TemplatesData = "../templates/";
		boolean contactless = false;
		
		FileFinder(TemplatesData, contactless);
		System.out.println("Complete");
	}

	private static void FileFinder(String templatesData, boolean contactless) throws IOException {
		if (contactless) {
			templatesData = templatesData + "contactless/";
		}else {
			templatesData = templatesData + "contactbased/";
		}
		Stream<Path> FileList = Files.find(Paths.get(templatesData), 999, (p, bfa) -> bfa.isRegularFile() && p.getFileName().toString().matches(".*\\.template") );
		Matcher(FileList);
	
	}

	private static void Matcher(Stream<Path> fileList) throws IOException {
		ArrayList<FingerprintTemplate> all = new ArrayList<>();
		ArrayList<String> paths = new ArrayList<>();
		fileList.forEach(p -> {
			try {
				all.add(new FingerprintTemplate(Files.readAllBytes(p)));
				paths.add(p.toString());
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
		for (int i = 0; i < paths.size(); i++ ) {
			FingerprintMatcher matcher = new FingerprintMatcher(all.get(i));
			for (int j = 0; j < paths.size(); j++ ) {
				if (!(paths.get(i).equals(paths.get(j)))) {
					BufferedWriter output = new BufferedWriter(new FileWriter("output.txt", true));
					output.write(paths.get(i) + "; " + paths.get(j) + "; " + matcher.match(all.get(j)) +"\n");
					output.close();
					
				}
			}
		}
	}

}

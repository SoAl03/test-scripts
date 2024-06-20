package extractor;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

import com.machinezoo.sourceafis.FingerprintImage;
import com.machinezoo.sourceafis.FingerprintTemplate;

public class Extract_Minutiae {

	public static void main(String[] args) throws IOException {
		String TestData = "../test_data/";
		String Saving = "../templates/";
		ListFiles(TestData, Saving);
		System.out.println("Complete");
	}
	
	private static void ListFiles(String TestData, String Saving) throws IOException{
		 Stream<Path> FileList = Files.find(Paths.get(TestData), 999, (p, bfa) -> bfa.isRegularFile() && (p.getFileName().toString().matches(".*\\.png") || p.getFileName().toString().matches(".*\\.bmp")));
		 FileList.forEach((p) -> {try {
			Extractor(p, Saving);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}});
	}
	
	private static void Extractor(Path file, String save) throws IOException {
		save = save + Name(file.toString());
		var template = new FingerprintTemplate(new FingerprintImage(Files.readAllBytes(file)));
		var temp = template.toByteArray();
		Files.write(Paths.get(save), temp);
	}
	
	private static String Name(String name) {
		String ret = "";
		if (name.contains("contactbased")) {
			ret = ret + "contactbased/" + subname(name, "scanner") + "_" + subname(name, "material") + "_" + subname(name, "Image") + ".template";
		} else {
			ret = ret + "contactless/" + subname(name, "material") + "_" + subname(name, "Image") + ".template";
		}
		return ret;
	}
	
	private static String subname (String name, String s) {
		return s.substring(0, 1) + name.split(s)[1].split("\\\\")[0];
	}
}

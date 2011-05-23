package poiskat;

import java.io.*;

import org.apache.lucene.document.Field;
import org.apache.lucene.store.*;
import org.apache.lucene.util.Version;
import org.apache.lucene.analysis.*;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.*;

public class Indexador {
	private String FILES_TO_INDEX_DIRECTORY = "";
	private String INDEX_DIRECTORY = "";
	private IndexWriter indice;

	public Indexador(String diretorioIndice, String diretorioArquivos) {
		FILES_TO_INDEX_DIRECTORY = diretorioArquivos;
		INDEX_DIRECTORY = diretorioIndice;

		Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_31);
		IndexWriterConfig config = new IndexWriterConfig(Version.LUCENE_31,
				analyzer);
		config.setOpenMode(IndexWriterConfig.OpenMode.CREATE);
		indice = null;
		try {
			indice = new IndexWriter(new SimpleFSDirectory(new File(
					INDEX_DIRECTORY)), config);
		} catch (Exception e) {
			e.printStackTrace();
		}
		File[] files = Arquivo.getArquivos(FILES_TO_INDEX_DIRECTORY);
		System.out.println("Indexando " + files.length + " arquivos...");
		float count = 0;
		float progress;

		for (File file : files) {
			Document document = new Document();
			if (file.length() <= 524288) {
				try {
					String path = file.getCanonicalPath();
					// Reader reader = new FileReader(file);
					String titulo = HTMLParser.getTitulo(path);
					document.add(new Field("caminho", path, Field.Store.YES,
							Field.Index.NOT_ANALYZED));
					document.add(new Field("titulo", titulo, Field.Store.YES,
							Field.Index.NOT_ANALYZED));
					document.add(new Field("conteudo", HTMLParser
							.getPlainText(path), Field.Store.NO,
							Field.Index.ANALYZED));
					indice.addDocument(document);
					System.out.println(path);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			count++;
			progress = count / files.length * 100f;
			System.out.printf("%2.2f%%\n", progress);
			
		}
		System.out.println("Indexado!\n");
		try {
			indice.optimize();
			indice.close();
		} catch (Exception e) {
		}

	}
}

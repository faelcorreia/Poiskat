package poiskat;

import java.io.File;
import java.io.IOException;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryParser.MultiFieldQueryParser;
import org.apache.lucene.search.*;
import org.apache.lucene.store.*;
import org.apache.lucene.util.Version;

public class MaquinaBusca {
	private String INDEX_DIRECTORY = "";
	private IndexSearcher indexSearcher = null;

	public MaquinaBusca(String _indice) {

		INDEX_DIRECTORY = _indice;
		System.out.print(INDEX_DIRECTORY);
		Directory directory;
		try {
			directory = new SimpleFSDirectory(new File(INDEX_DIRECTORY));
			IndexReader indexReader = IndexReader.open(directory);
			indexSearcher = new IndexSearcher(indexReader);
		} catch (IOException e) {
		}
	}

	public Resultado[] processaConsulta(String stringQuery) {
		Document[] documents = null;
		Resultado[] results = null;
		try {
			Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_31);
			MultiFieldQueryParser queryParser = new MultiFieldQueryParser(Version.LUCENE_31,
					new String[]{"conteudo","titulo","caminho"}, analyzer);
			Query query;
			try {
				query = queryParser.parse(stringQuery);
				TopDocs top = indexSearcher.search(query, 100);
				ScoreDoc docs[] = top.scoreDocs;
				documents = new Document[docs.length];
				for (int i = 0; i < docs.length; i++) {
					documents[i] = indexSearcher.doc(docs[i].doc);
				}
			} catch (org.apache.lucene.queryParser.ParseException e) {
			}
			results = new Resultado[documents.length];
			String titulo;
			String caminho;
			for (int i = 0; i < documents.length; i++) {
				titulo = documents[i].getField("titulo").stringValue();
				caminho = documents[i].getField("caminho").stringValue();
				results[i] = new Resultado(titulo, null, caminho);
			}
		} catch (Exception e) {
		}
		return results;
	}
}

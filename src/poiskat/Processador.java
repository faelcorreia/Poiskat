package poiskat;
import java.io.*;
import java.text.ParseException;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.*;
import org.apache.lucene.store.*;
import org.apache.lucene.util.Version;
import org.apache.lucene.analysis.*;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.*;

public class Processador {
	private String INDEX_DIRECTORY = "";
	private IndexSearcher indexSearcher = null;

	public Processador(String diretorioIndice) {
		INDEX_DIRECTORY = diretorioIndice;
		System.out.print(INDEX_DIRECTORY);
		Directory directory;
		try {
			directory = new SimpleFSDirectory(new File(INDEX_DIRECTORY));
			IndexReader indexReader = IndexReader.open(directory);
			indexSearcher = new IndexSearcher(indexReader);
		} catch (IOException e) {
		}
	}

	public Document[] procurarIndice(String stringQuery) throws IOException,
			ParseException {
		Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_31);
		QueryParser queryParser = new QueryParser(Version.LUCENE_31,
				"conteudo", analyzer);
		Query query;
		try {
			query = queryParser.parse(stringQuery);
			TopDocs top = indexSearcher.search(query, 10);
			ScoreDoc docs[] = top.scoreDocs;
			Document[] documents = new Document[docs.length];
			for (int i = 0; i < docs.length; i++) {
				documents[i] = indexSearcher.doc(docs[i].doc);				
			}
			return documents;
		} catch (org.apache.lucene.queryParser.ParseException e) {}
		return null;
	}
}

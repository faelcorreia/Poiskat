package poiskat;

import java.io.Reader;
import org.htmlparser.*;
import org.htmlparser.tags.*;
import org.htmlparser.util.*;

public class HTMLParser {
	public static String parseHTML(Reader reader) {
		String doc = Arquivo.fileToString(reader);
		return doc
				.replaceAll(
						"<([a-zA-Z][a-zA-Z0-9]*)\b[^>]*>|</([a-zA-Z][a-zA-Z0-9]*)\b[^>]*>",
						" ");
	}

	public static String getPlainText(String url) {
		String str = "";
		try {
			Parser p = new Parser(url);
			NodeIterator node = p.parse(null).elements();
			while (node.hasMoreNodes()) {
				str += node.nextNode().toPlainTextString();
			}
		} catch (Exception e) {
		}
		return str;
	}

	public static String getTitulo(String url) {
		try {
			Parser p = new Parser(url);
			NodeIterator nodeList = p.parse(null).elements();
			NodeIterator nodeList2;
			NodeList nodeAux;
			Node node;
			Node node2;
			while (nodeList.hasMoreNodes()) {
				node = nodeList.nextNode();
				if (node instanceof Html) {
					nodeAux = node.getChildren();
					if (nodeAux != null) {
						nodeList = node.getChildren().elements();
						while (nodeList.hasMoreNodes()) {
							node = nodeList.nextNode();
							if (node instanceof TitleTag) {
								return node.toPlainTextString();
							} else if (node instanceof HeadTag) {
								nodeAux = node.getChildren();
								if (nodeAux != null) {
									nodeList2 = node.getChildren().elements();
									while (nodeList2.hasMoreNodes()) {
										node2 = nodeList2.nextNode();
										if (node2 instanceof TitleTag) {
											if (node2.toPlainTextString() == "") {
												return "Sem Título";
											}
											return node2.toPlainTextString();
										}
									}
								}
							}
						}
					}
				} else if (node instanceof TitleTag) {
					return node.toPlainTextString();
				}
			}
		} catch (ParserException e) {
			e.printStackTrace();
		}
		return "Sem título";
	}
}

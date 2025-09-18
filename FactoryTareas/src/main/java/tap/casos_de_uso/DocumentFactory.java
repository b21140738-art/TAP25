package tap.casos_de_uso;


public class DocumentFactory{
    public static Document createDocument(String extension){
        if(extension.equals("pdf")){
            return new PDFDocument();
        }
        else if(extension.equals("docx")){
            return new DocxDocument();
        }
        throw new UnsupportedOperationException("FORMATO NO ENCONTRADO");
    }
}

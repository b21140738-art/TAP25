package clases.ejemplo1.decorators;

import java.io.*;

//Escritor-lector de datos simple
public class FileDataSource  implements DataSource {
    private String name;

        public FileDataSource(String name) {
            this.name = name;
        }

        @Override
        public void writeData(String data) {
            File file = new File(name);
            File parent = file.getParentFile();
            if (parent != null && !parent.exists()) {
                parent.mkdirs();
            }
            try (OutputStream fos = new FileOutputStream(file)) {
                fos.write(data.getBytes(), 0, data.length());
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }

        @Override
        public String readData() {
            File file = new File(name);
            if (!file.exists()) {
                System.out.println(file.getPath() + " (El sistema no puede encontrar la ruta especificada)");
                return "";
            }
            try (FileReader reader = new FileReader(file)) {
                char[] buffer = new char[(int) file.length()];
                int read = reader.read(buffer);
                if (read > 0) {
                    return new String(buffer, 0, read);
                } else {
                    return "";
                }
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
                return "";
            }
        }
    }

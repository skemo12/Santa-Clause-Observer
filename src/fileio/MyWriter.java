package fileio;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.File;
import java.io.IOException;

public final class MyWriter {

    private String filename;
    private ObjectMapper mapper;

    public MyWriter(final String filename) {

        this.setFilename(filename);
        this.setMapper(new ObjectMapper());
        this.getMapper().configure(SerializationFeature.INDENT_OUTPUT, true);

    }
    /**
     * Writes the output to the JSON file with which the writer was created.
     */
    public void closeJSON(final AnnualChildren annualChildren) {
        try {
            this.getMapper().writeValue(
                    new File(getFilename()), annualChildren);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(final String filename) {
        this.filename = filename;
    }

    public ObjectMapper getMapper() {
        return mapper;
    }

    public void setMapper(final ObjectMapper mapper) {
        this.mapper = mapper;
    }
}
